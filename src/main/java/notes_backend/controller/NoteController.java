package notes_backend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import notes_backend.dto.note.NoteContentResponseDto;
import notes_backend.dto.note.NoteIdResponseDto;
import notes_backend.dto.note.NoteResponseDto;
import notes_backend.dto.note.create.NoteCreateRequestDto;
import notes_backend.dto.note.create.NoteCreateResponseDto;
import notes_backend.dto.note.update.NoteUpdateRequestDto;
import notes_backend.dto.note.update.NoteUpdatedResponseDto;
import notes_backend.service.note.NoteStatsService;
import notes_backend.service.note.crud.NoteService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static notes_backend.constant.PaginationConstants.DEFAULT_PAGE;
import static notes_backend.constant.PaginationConstants.DEFAULT_SIZE;

@RestController
@RequestMapping("/notes")
@RequiredArgsConstructor
@Tag(name = "Note Controller", description = "Handles CRUD operations for user notes")
public class NoteController {

    private final NoteService noteService;
    private final NoteStatsService noteStatsService;

    @PostMapping("/add")
    @Operation(summary = "Create a new note", description = "Adds a new note for a specific user")
    public ResponseEntity<NoteCreateResponseDto> createNoteForUser(
            @RequestBody @Valid NoteCreateRequestDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(noteService.create(dto));
    }

    @GetMapping("/getAll/userId/{userId}/paged")
    @Operation(summary = "Get paginated notes", description = "Returns paginated notes sorted by newest first")
    public ResponseEntity<Page<NoteResponseDto>> getNotesByUserPaged(
            @PathVariable String userId,
            @RequestParam(defaultValue = "" + DEFAULT_PAGE) int page,
            @RequestParam(defaultValue = "" + DEFAULT_SIZE) int size)  {
        return ResponseEntity.ok(noteService.getAllNotesByUserId(userId, page, size));
    }

    @GetMapping("/getAll/tag/{tag}/paged")
    @Operation(
            summary = "Get paginated notes by tag",
            description = "Returns a paginated list of notes filtered by a specific tag. "
                    + "If 'page' or 'size' not provided, defaults to page=0, size=10."
    )
    public ResponseEntity<Page<NoteResponseDto>> getAllByTagPaged(
            @PathVariable notes_backend.entity.note.Tag tag,
            @RequestParam(defaultValue = "" + DEFAULT_PAGE) int page,
            @RequestParam(defaultValue = "" + DEFAULT_SIZE) int size) {

        return ResponseEntity.ok(noteService.getAllByTag(tag, page, size));
    }

    @GetMapping("/getContent/noteId/{noteId}")
    @Operation(summary = "Get note content by note ID",
            description = "Provide note content(text) by note id")
    public ResponseEntity<NoteContentResponseDto> getNoteContent(@PathVariable @Valid String noteId) {
        return ResponseEntity.status(HttpStatus.OK).body(noteService.getNoteContentById(noteId));
    }

    @GetMapping("/getId/userId/{userId}/paged")
    @Operation(
            summary = "Get paginated note IDs by user ID",
            description = "Returns paginated list of note IDs for a specific user, sorted by newest first"
    )
    public ResponseEntity<Page<NoteIdResponseDto>> getAllNoteIdsByUserId(
            @PathVariable @Valid String userId,
            @RequestParam(defaultValue = "" + DEFAULT_PAGE) int page,
            @RequestParam(defaultValue = "" + DEFAULT_SIZE) int size) {
        return ResponseEntity.ok(noteService.getAllNoteIdsByUserId(userId, page, size));
    }

    @PatchMapping("/update/noteId/{noteId}")
    @Operation(summary = "Update note by ID",
            description = "Updates note title, text, or tags for the specified noteId")
    public ResponseEntity<NoteUpdatedResponseDto> updateByNoteId(
            @PathVariable @Valid String noteId,
            @RequestBody @Valid NoteUpdateRequestDto requestDto) {
        return ResponseEntity.ok(noteService.updateNoteById(noteId, requestDto));
    }

    @DeleteMapping("/delete/noteId/{noteId}")
    @Operation(summary = "Delete note by ID", description = "Deletes the note identified by the given noteId")
    public ResponseEntity<Void> deleteByNoteId(@PathVariable @Valid String noteId) {
        noteService.deleteNoteById(noteId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/stats/{noteId}")
    @Operation(
            summary = "Get word statistics for note",
            description = "Returns frequency of unique words in note text sorted descending"
    )
    public ResponseEntity<Map<String, Long>> getWordStatsByNoteId(
            @PathVariable @Valid String noteId) {
        return ResponseEntity.ok(noteStatsService.getWordStatsByNoteId(noteId));
    }
}

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
import notes_backend.service.note.NoteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notes")
@RequiredArgsConstructor
@Tag(name = "Note Controller", description = "Handles CRUD operations for user notes")
public class NoteController {

    private final NoteService noteService;

    @PostMapping("/add")
    @Operation(summary = "Create a new note", description = "Adds a new note for a specific user")
    public ResponseEntity<NoteCreateResponseDto> createNoteForUser(
            @RequestBody @Valid NoteCreateRequestDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(noteService.create(dto));
    }

    @GetMapping("/getAll/userId/{userId}")
    @Operation(summary = "Get notes by user ID",
            description = "Retrieves all notes created by a specific user using userId")
    public ResponseEntity<List<NoteResponseDto>> getAllByUserId(@PathVariable @Valid String userId) {
        return ResponseEntity.ok(noteService.getAllByUserId(userId));
    }

    @GetMapping("/getContent/noteId/{noteId}")
    @Operation(summary = "Get note content by note ID",
            description = "Provide note content(text) by note id")
    public ResponseEntity<NoteContentResponseDto> getNoteContent(@PathVariable @Valid String noteId) {
        return ResponseEntity.status(HttpStatus.OK).body(noteService.getNoteContentById(noteId));
    }

    @GetMapping("/getId/userId/{userId}")
    @Operation(summary = "Get note id",
            description = "Provide list of notes id by user id")
    public ResponseEntity<List<NoteIdResponseDto>> getAllNoteId(@PathVariable @Valid String userId) {
        return ResponseEntity.ok(noteService.getAllNoteIdsByUserId(userId));
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
}

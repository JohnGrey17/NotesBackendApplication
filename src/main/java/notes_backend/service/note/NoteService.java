package notes_backend.service.note;

import jakarta.validation.Valid;
import notes_backend.dto.note.*;
import notes_backend.dto.note.create.NoteCreateRequestDto;
import notes_backend.dto.note.create.NoteCreateResponseDto;
import notes_backend.dto.note.update.NoteUpdateRequestDto;
import notes_backend.dto.note.update.NoteUpdatedResponseDto;

import java.util.List;

public interface NoteService {

    NoteCreateResponseDto create(NoteCreateRequestDto requestDto);

    List<NoteResponseDto> getAllByUserId(String userId);

    NoteUpdatedResponseDto updateNoteById(String noteId, NoteUpdateRequestDto requestDto);

    void deleteNoteById(String noteId);

    NoteContentResponseDto getNoteContentById(String noteId);

    List<NoteIdResponseDto> getAllNoteIdsByUserId(String userId);
}

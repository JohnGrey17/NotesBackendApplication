package notes_backend.service.note.crud;

import notes_backend.dto.note.*;
import notes_backend.dto.note.create.NoteCreateRequestDto;
import notes_backend.dto.note.create.NoteCreateResponseDto;
import notes_backend.dto.note.update.NoteUpdateRequestDto;
import notes_backend.dto.note.update.NoteUpdatedResponseDto;
import notes_backend.entity.note.Tag;
import org.springframework.data.domain.Page;

public interface NoteService {

    NoteCreateResponseDto create(NoteCreateRequestDto requestDto);

    Page<NoteResponseDto> getAllNotesByUserId(String userId, int page, int size);

    public Page<NoteResponseDto> getAllByTag(Tag tag, int page, int size);

    NoteUpdatedResponseDto updateNoteById(String noteId, NoteUpdateRequestDto requestDto);

    void deleteNoteById(String noteId);

    NoteContentResponseDto getNoteContentById(String noteId);

    Page<NoteIdResponseDto> getAllNoteIdsByUserId(String userId, int page, int size);
}

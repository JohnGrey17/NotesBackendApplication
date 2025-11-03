package notes_backend.service.note;

import lombok.RequiredArgsConstructor;
import notes_backend.dto.note.NoteContentResponseDto;
import notes_backend.dto.note.NoteIdResponseDto;
import notes_backend.dto.note.create.NoteCreateRequestDto;
import notes_backend.dto.note.create.NoteCreateResponseDto;
import notes_backend.dto.note.NoteResponseDto;
import notes_backend.dto.note.update.NoteUpdateRequestDto;
import notes_backend.dto.note.update.NoteUpdatedResponseDto;
import notes_backend.entity.note.Note;
import notes_backend.exception.type.NoteException;
import notes_backend.mapper.NoteMapper;
import notes_backend.repository.NoteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoteServiceImpl implements NoteService {

    private final NoteRepository noteRepository;

    private final NoteMapper noteMapper;

    private final NoteUpdater noteUpdater;

    @Override
    public NoteCreateResponseDto create(NoteCreateRequestDto requestDto) {

        Note newNote = noteMapper.toModel(requestDto);
        noteRepository.save(newNote);
        return noteMapper.toCreateResponseDto(newNote);
    }

    @Override
    public List<NoteResponseDto> getAllByUserId(String userId) {
        return noteRepository.findAllByUserId(userId)
                .stream()
                .map(noteMapper::toResponseDto)
                .toList();
    }

    @Override
    public NoteContentResponseDto getNoteContentById(String noteId) {
        Note note = noteRepository.findById(noteId).orElseThrow(() -> new NoteException("Note does not exist"));
        return noteMapper.toNoteContentResponseDto(note);
    }

    @Override
    public List<NoteIdResponseDto> getAllNoteIdsByUserId(String userId) {
        return noteRepository.findAllByUserId(userId)
                .stream()
                .map(noteMapper::toNoteIdResponseDto)
                .toList();
    }

    @Override
    public NoteUpdatedResponseDto updateNoteById(String noteId, NoteUpdateRequestDto dto) {
        Note existingNote = noteRepository.findById(noteId)
                .orElseThrow(() -> new NoteException("Note does not exist"));

        noteUpdater.applyUpdates(existingNote, dto);
        Note savedNote = noteRepository.save(existingNote);
        return noteMapper.toUpdatedResponseDto(savedNote);
    }

    @Override
    public void deleteNoteById(String noteId) {
        Note note = noteRepository.findById(noteId)
                .orElseThrow(() -> new NoteException("Note does not exist"));
        noteRepository.delete(note);
    }
}

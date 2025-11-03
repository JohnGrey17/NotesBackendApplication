package notes_backend.service.note.crud;

import lombok.RequiredArgsConstructor;
import notes_backend.dto.note.NoteContentResponseDto;
import notes_backend.dto.note.NoteIdResponseDto;
import notes_backend.dto.note.create.NoteCreateRequestDto;
import notes_backend.dto.note.create.NoteCreateResponseDto;
import notes_backend.dto.note.NoteResponseDto;
import notes_backend.dto.note.update.NoteUpdateRequestDto;
import notes_backend.dto.note.update.NoteUpdatedResponseDto;
import notes_backend.entity.note.Note;
import notes_backend.entity.note.Tag;
import notes_backend.exception.type.NoteException;
import notes_backend.mapper.NoteMapper;
import notes_backend.repository.NoteRepository;
import notes_backend.service.note.component.NoteUpdater;
import notes_backend.service.note.component.WordStatsCalculator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NoteServiceImpl implements NoteService {

    private final NoteRepository noteRepository;

    private final NoteMapper noteMapper;

    private final NoteUpdater noteUpdater;

    private final WordStatsCalculator wordStatsCalculator;


    @Override
    public NoteCreateResponseDto create(NoteCreateRequestDto requestDto) {

        Note newNote = noteMapper.toModel(requestDto);
        noteRepository.save(newNote);
        return noteMapper.toCreateResponseDto(newNote);
    }

    @Override
    public Page<NoteResponseDto> getAllNotesByUserId(String userId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt"));
        return noteRepository.findAllByUserId(userId, pageable)
                .map(noteMapper::toResponseDto);
    }

    @Override
    public Page<NoteResponseDto> getAllByTag(Tag tag, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt"));

        Page<Note> notesPage = noteRepository.findAllByTagsContaining(tag, pageable);

        if (notesPage.isEmpty()) {
            throw new NoteException("No notes found for tag: " + tag);
        }

        return notesPage.map(noteMapper::toResponseDto);
    }

    @Override
    public NoteContentResponseDto getNoteContentById(String noteId) {
        Note note = noteRepository.findById(noteId).orElseThrow(() -> new NoteException("Note does not exist"));
        return noteMapper.toNoteContentResponseDto(note);
    }

    @Override
    public Page<NoteIdResponseDto> getAllNoteIdsByUserId(String userId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt"));

        return noteRepository.findAllByUserId(userId, pageable)
                .map(noteMapper::toNoteIdResponseDto);
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

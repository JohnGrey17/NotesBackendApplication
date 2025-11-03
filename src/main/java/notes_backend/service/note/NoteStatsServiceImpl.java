package notes_backend.service.note;

import lombok.RequiredArgsConstructor;
import notes_backend.entity.note.Note;
import notes_backend.exception.type.NoteException;
import notes_backend.repository.NoteRepository;
import notes_backend.service.note.component.WordStatsCalculator;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class NoteStatsServiceImpl implements NoteStatsService {

    private final NoteRepository noteRepository;
    private final WordStatsCalculator wordStatsCalculator;

    @Override
    public Map<String, Long> getWordStatsByNoteId(String id) {
        Note note = noteRepository.findById(id)
                .orElseThrow(() -> new NoteException("Note with id " + id + " not found"));

        if (note.getText() == null || note.getText().isBlank()) {
            throw new NoteException("Note text is empty, cannot calculate statistics");
        }

        return wordStatsCalculator.calculate(note.getText());
    }
}

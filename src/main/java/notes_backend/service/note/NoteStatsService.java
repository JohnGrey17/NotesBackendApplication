package notes_backend.service.note;

import java.util.Map;

public interface NoteStatsService {
    Map<String, Long> getWordStatsByNoteId(String id);
}

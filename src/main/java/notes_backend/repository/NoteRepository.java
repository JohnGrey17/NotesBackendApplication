package notes_backend.repository;

import notes_backend.entity.note.Note;
import notes_backend.entity.note.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface NoteRepository extends MongoRepository<Note, String> {

    Page<Note> findAllByUserId(String userId, Pageable pageable);

    Page<Note> findAllByTagsContaining(Tag tag, Pageable pageable);

    Map<String, Long> getWordStatsById(String id);

}

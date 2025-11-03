package notes_backend.dto.note.update;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import notes_backend.entity.note.Tag;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@ToString
public class NoteUpdatedResponseDto {

    private String title;

    private String text;

    private Set<Tag> tags;

    private LocalDateTime updatedAt;
}

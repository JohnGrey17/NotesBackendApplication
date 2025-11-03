package notes_backend.dto.note.update;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import notes_backend.entity.note.Tag;

import java.util.Set;

@Getter
@Setter
@ToString
public class NoteUpdateRequestDto {

    private String title;

    private String text;

    private Set<Tag> tags;
}

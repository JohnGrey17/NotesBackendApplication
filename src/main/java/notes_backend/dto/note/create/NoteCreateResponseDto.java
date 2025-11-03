package notes_backend.dto.note.create;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import notes_backend.entity.note.Tag;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString
public class NoteCreateResponseDto {

    private String id;

    private String title;

    private String text;

    private LocalDateTime createdAt;

    private Set<Tag> tags = new HashSet<>();

}

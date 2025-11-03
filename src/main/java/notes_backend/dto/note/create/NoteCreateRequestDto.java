package notes_backend.dto.note.create;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import notes_backend.entity.note.Tag;


import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class NoteCreateRequestDto {

    @NotBlank
    private String userId;

    @NotBlank
    private String title;

    @NotBlank
    private String text;

    private Set<Tag> tags = new HashSet<>();
}

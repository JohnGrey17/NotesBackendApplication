package notes_backend.dto.note;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class NoteResponseDto {

    private String title;

    private LocalDateTime createdAt;

}

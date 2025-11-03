package notes_backend.dto.user;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class UserRegistrationResponseDto {

    private String id;

    private String userName;
}

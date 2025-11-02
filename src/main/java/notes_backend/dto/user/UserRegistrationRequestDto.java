package notes_backend.dto.user;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import notes_backend.validator.UniqueUserName;

@Getter
@Setter
public class UserRegistrationRequestDto {

    @NotBlank
    @UniqueUserName
    private String userName;
}
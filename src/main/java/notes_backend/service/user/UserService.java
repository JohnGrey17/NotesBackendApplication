package notes_backend.service.user;

import notes_backend.dto.user.UserRegistrationRequestDto;
import notes_backend.dto.user.UserRegistrationResponseDto;
import notes_backend.dto.user.UserResponseDto;
import java.util.List;

public interface UserService {

    UserRegistrationResponseDto addNewUser(UserRegistrationRequestDto requestDto);

    List<UserResponseDto> getAllUsers();

    UserResponseDto getUserByUserName(String userName);

    UserResponseDto getUserById(String userId);
}

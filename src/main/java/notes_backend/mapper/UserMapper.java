package notes_backend.mapper;

import notes_backend.config.MapperConfig;
import notes_backend.dto.user.UserRegistrationRequestDto;
import notes_backend.dto.user.UserRegistrationResponseDto;
import notes_backend.dto.user.UserResponseDto;
import notes_backend.entity.User;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface UserMapper {

    User toModel(UserRegistrationRequestDto userRegistrationRequestDto);

    UserRegistrationResponseDto toRegistrationResponseDto(User user);

    UserResponseDto toUserResponseDto(User user);
}

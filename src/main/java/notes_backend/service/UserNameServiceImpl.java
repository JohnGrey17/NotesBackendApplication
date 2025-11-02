package notes_backend.service;

import lombok.RequiredArgsConstructor;
import notes_backend.dto.user.UserRegistrationRequestDto;
import notes_backend.dto.user.UserRegistrationResponseDto;
import notes_backend.dto.user.UserResponseDto;
import notes_backend.entity.User;
import notes_backend.exception.type.UserException;
import notes_backend.mapper.UserMapper;
import notes_backend.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserNameServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    @Transactional
    public UserRegistrationResponseDto addNewUser(UserRegistrationRequestDto requestDto) {

        User user = userMapper.toModel(requestDto);
        userRepository.save(user);
        return userMapper.toRegistrationResponseDto(user);
    }

    @Override
    public List<UserResponseDto> getAllUsers() {

        return userRepository.findAll().stream()
                .map(userMapper::toUserResponseDto)
                .toList();
    }

    @Override
    public UserResponseDto getUserByUserName(String userName) {

        User user = userRepository.findByUserName(userName).orElseThrow(
                () -> new UserException("User does not exist"));
        return userMapper.toUserResponseDto(user);
    }

    @Override
    public UserResponseDto getUserById(String userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new UserException("User does not exist"));
        return userMapper.toUserResponseDto(user);
    }
}

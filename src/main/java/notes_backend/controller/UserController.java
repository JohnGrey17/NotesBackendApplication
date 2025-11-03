package notes_backend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import notes_backend.dto.user.UserRegistrationRequestDto;
import notes_backend.dto.user.UserRegistrationResponseDto;
import notes_backend.dto.user.UserResponseDto;
import notes_backend.service.user.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Tag(name = "User Controller",description = "This controller handles request "
        + "and response all user simple service")
public class UserController {

    private final UserService userService;

    @PostMapping("/add")
    @Operation(summary = "add new user",description = "add new user to repository")
    public ResponseEntity<UserRegistrationResponseDto> addNewUser(@RequestBody @Valid UserRegistrationRequestDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.addNewUser(dto));
    }

    @GetMapping("/getAll")
    @Operation(summary = "Get all users", description = "Get all users from repository")
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/id/{id}")
    @Operation(summary = "Get user by ID", description = "Get a specific user by user ID")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable String id) {
        UserResponseDto user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/name/{userName}")
    @Operation(summary = "Get user by userName", description = "Get user by userName")
    public ResponseEntity<UserResponseDto> getUserByUserName(@PathVariable String userName) {
        UserResponseDto user = userService.getUserByUserName(userName);
        return ResponseEntity.ok(user);
    }

}

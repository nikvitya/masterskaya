package ru.yandex.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.yandex.model.dto.UserCreateDTO;
import ru.yandex.model.dto.UserPasswordDTO;
import ru.yandex.model.dto.UserResponseDTO;
import ru.yandex.model.dto.UserUpdateDTO;
import ru.yandex.service.UserService;

import java.util.List;
import java.util.Map;

import static ru.yandex.constants.Constatnts.X_USER_ID;


@RestController
@AllArgsConstructor
@Validated
public class UserController {
    private final UserService userService;

    @PostMapping("/users")
    public UserResponseDTO createUser(@Valid @RequestBody UserCreateDTO userCreateDTO) {
        return userService.save(userCreateDTO);
    }

    @PatchMapping("/users")
    public UserResponseDTO updateUser(
            @RequestHeader(value = X_USER_ID, required = false) Long headerUserId,
            @Valid @RequestBody UserUpdateDTO userUpdateDTO) {

        return userService.update(headerUserId, userUpdateDTO);
    }

    @GetMapping("/users/{id}")
    public UserResponseDTO getUserById(@PathVariable @Min(value = 1, message = "Id не должно быть меньше 1") Long id,
                                       @RequestHeader(value = X_USER_ID, required = false) Long headerUserId) {
        return userService.getUserById(id, headerUserId);
    }


    @GetMapping("/users")
    public List<UserResponseDTO> getAllUsers(@RequestParam(defaultValue = "0") int page,
                                             @RequestParam(defaultValue = "10") int size) {

        return userService.getAllUsers(page,size);
    }


    @DeleteMapping("/users")
    public ResponseEntity<Map<String, String>> deleteUser(@RequestHeader(value = X_USER_ID, required = false) Long headerUserId,
                                                          @RequestBody @Valid UserPasswordDTO userPasswordDTO) {
       return userService.delete(headerUserId, userPasswordDTO);
    }
}


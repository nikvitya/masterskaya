package ru.yandex.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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
@Tag(name = "User Controller", description = "Контроллер для создания,получения,обновления,удаления пользователя.")
public class UserController {
    private final UserService userService;

    @Operation(
            summary = "Добавление пользователя",
            description = "Позволяет cоздать пользователя"
    )
    @PostMapping("/users")
    public UserResponseDTO createUser(@Valid @RequestBody UserCreateDTO userCreateDTO) {
        return userService.save(userCreateDTO);
    }

    @Operation(
            summary = "Обновление пользователя",
            description = "Обновление пользователя (userId (кто делает запрос) берем из header" +
                    " и проверяем верность введенного пароля, нельзя обновить email)"
    )
    @PatchMapping("/users")
    public UserResponseDTO updateUser(
            @RequestHeader(value = X_USER_ID, required = false)
            @Parameter(description = "id автора", example = "1") Long headerUserId,
            @Valid @RequestBody UserUpdateDTO userUpdateDTO) {

        return userService.update(headerUserId, userUpdateDTO);
    }

    @Operation(
            summary = "Получение пользователя по id",
            description = "без пароля, НО если запрашивает сам себя (проверяем по header), то с паролем"
    )
    @GetMapping("/users/{id}")
    public UserResponseDTO getUserById(@PathVariable @Min(value = 1, message = "Id не должно быть меньше 1")
                                       @Parameter(description = "id пользователя", example = "1") Long id,
                                       @RequestHeader(value = X_USER_ID, required = false)
                                       @Parameter(description = "id пользователя в заголовке", example = "1") Long headerUserId) {
        return userService.getUserById(id, headerUserId);
    }

    @Operation(
            summary = "Получение всех пользователей с пагинацией"
    )
    @GetMapping("/users")
    public List<UserResponseDTO> getAllUsers(@RequestParam(defaultValue = "0")
                                             @Parameter(description = "номер страницы", example = "0") int page,
                                             @RequestParam(defaultValue = "10")
                                             @Parameter(description = "количество отзывов", example = "10") int size) {

        return userService.getAllUsers(page, size);
    }

    @Operation(
            summary = "Удаление пользователя",
            description = " userId (кто делает запрос) берем из header и проверяем верность введенного пароля"
    )
    @DeleteMapping("/users")
    public ResponseEntity<Map<String, String>> deleteUser(@RequestHeader(value = X_USER_ID, required = false)
                                                          @Parameter(description = "id пользователя", example = "1") Long headerUserId,
                                                          @RequestBody @Valid UserPasswordDTO userPasswordDTO) {
        return userService.delete(headerUserId, userPasswordDTO);
    }
}


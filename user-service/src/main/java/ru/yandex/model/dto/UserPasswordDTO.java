package ru.yandex.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserPasswordDTO {
    @NotBlank(message = "Password is required")
    @Schema(description = "Пароль пользователя", example = "qwerty")
    private String password;
}

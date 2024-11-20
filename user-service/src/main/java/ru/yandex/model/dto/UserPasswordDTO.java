package ru.yandex.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserPasswordDTO {
    @NotBlank(message = "Password is required")
    private String password;
}

package ru.yandex.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
public class UserUpdateDTO {
    @Schema(description = "Имя пользователя", example = "Виталий")
    private String name;

    @NotBlank
    @Schema(description = "Пароль пользователя", example = "qwerty")
    private String password;

    @Schema(description = "Краткое описание", example = "Обновленный замечательный человек! :)")
    @JsonProperty("about_me")
    private String aboutMe;
}

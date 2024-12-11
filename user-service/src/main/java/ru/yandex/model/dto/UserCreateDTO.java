package ru.yandex.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
public class UserCreateDTO {

    @NotBlank(message = "Имя обязательно")
    @Schema(description = "Имя пользователя", example = "Виталий")
    private String name;

    @JsonProperty("e_mail")
    @Email
    @NotBlank
    @Schema(description = "Email пользователя", example = "vitaly@ya.ru")
    private String email;

    @NotBlank
    @Schema(description = "Пароль пользователя", example = "qwerty")
    private String password;

    @JsonProperty("about_me")
    @Schema(description = "Краткое описание", example = "Замечательный человек! :)")
    private String aboutMe;
}

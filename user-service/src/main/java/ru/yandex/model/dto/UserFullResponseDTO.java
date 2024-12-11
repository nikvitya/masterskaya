package ru.yandex.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder(builderMethodName = "userFullBuilder")
public class UserFullResponseDTO extends UserResponseDTO {
    @Schema(description = "Пароль пользователя", example = "qwerty")
    private String password;
    @Schema(description = "id пользователя", example = "1")
    private Long id;
    @Schema(description = "Имя пользователя", example = "Виталий")
    private String name;
    @Schema(description = "Email пользователя", example = "vitaly@ya.ru")
    private String email;
    @Schema(description = "Краткое описание", example = "Замечательный человек! :)")
    private String aboutMe;

}


package ru.yandex.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
public class UserResponseDTO {
    @Schema(description = "id пользователя", example = "1")
    private Long id;
    @Schema(description = "Имя пользователя", example = "Виталий")
    private String name;
    @Schema(description = "Email пользователя", example = "vitaly@ya.ru")
    @JsonProperty("e_mail")
    private String email;
    @Schema(description = "Краткое описание", example = "Замечательный человек! :)")
    @JsonProperty("about_me")
    private String aboutMe;
}

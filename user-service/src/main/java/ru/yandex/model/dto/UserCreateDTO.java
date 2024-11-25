package ru.yandex.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
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
    private String name;

    @JsonProperty("e_mail")
    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String password;

    @JsonProperty("about_me")
    private String aboutMe;
}

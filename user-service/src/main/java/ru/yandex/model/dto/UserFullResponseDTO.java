package ru.yandex.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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

    private String password;
    private Long id;
    private String name;
    private String email;
    private String aboutMe;

}


package ru.yandex.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
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

    private Long id;
    private String name;

    @JsonProperty("e_mail")
    private String email;

    @JsonProperty("about_me")
    private String aboutMe;
}

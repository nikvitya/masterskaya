package ru.yandex.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import ru.yandex.model.dto.UserCreateDTO;
import ru.yandex.model.dto.UserPasswordDTO;
import ru.yandex.model.dto.UserResponseDTO;
import ru.yandex.model.dto.UserUpdateDTO;
import ru.yandex.service.UserService;

import java.nio.charset.StandardCharsets;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class UserControllerTest {
    private final MockMvc mockMvc;
    private final ObjectMapper objectMapper;
    @MockBean
    private UserService userService;


    @Test
    @SneakyThrows
    void createUser() {
        UserCreateDTO userCreateDTO = UserCreateDTO.builder()
                .name("user1")
                .email("user@ya.ru")
                .password("userpassword")
                .aboutMe("iamuser")
                .build();

        UserResponseDTO userResponseDTO = UserResponseDTO.builder()
                .id(1L)
                .name("user1")
                .email("user@ya.ru")
                .aboutMe("iamuser")
                .build();

        when(userService.save(any(UserCreateDTO.class))).thenReturn(userResponseDTO);

        mockMvc.perform(post("/users")
                        .content(objectMapper.writeValueAsString(userCreateDTO))
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding(StandardCharsets.UTF_8)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(userResponseDTO)));
    }

    @Test
    @SneakyThrows
    void updateUser() {
        Long headerUserId = 1L;
        UserUpdateDTO userUpdateDTO = UserUpdateDTO.builder()
                .name("user1")
                .password("userpassword")
                .aboutMe("iamuser")
                .build();

        UserResponseDTO userResponseDTO = UserResponseDTO.builder()
                .id(1L)
                .name("user1")
                .email("user@ya.ru")
                .aboutMe("iamuser")
                .build();

        when(userService.update(eq(headerUserId), any(UserUpdateDTO.class))).thenReturn(userResponseDTO);

        mockMvc.perform(patch("/users")
                        .content(objectMapper.writeValueAsString(userUpdateDTO))
                        .header("X-User-ID", headerUserId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding(StandardCharsets.UTF_8)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(userResponseDTO)));
    }


    @Test
    @SneakyThrows
    void getUserById() {
        Long id = 1L;
        Long headerUserId = 1L;

        UserResponseDTO userResponseDTO = UserResponseDTO.builder()
                .id(1L)
                .name("user1")
                .email("user@ya.ru")
                .aboutMe("iamuser")
                .build();

        when(userService.getUserById(eq(id), eq(headerUserId))).thenReturn(userResponseDTO);

        mockMvc.perform(get("/users/{id}", id)
                        .header("X-User-ID", headerUserId))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(userResponseDTO)));
    }

    @Test
    @SneakyThrows
    void deleteUser() {
        Long headerUserId = 1L;

        UserPasswordDTO userPasswordDTO = UserPasswordDTO.builder()
                .password("userpassword")
                .build();

        when(userService.delete(eq(headerUserId), any(UserPasswordDTO.class)))
                .thenReturn(ResponseEntity.ok(Map.of("message", "Пользователь успешно удален")));

        mockMvc.perform(delete("/users")
                        .header("X-User-ID", headerUserId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userPasswordDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Пользователь успешно удален"));
    }


}

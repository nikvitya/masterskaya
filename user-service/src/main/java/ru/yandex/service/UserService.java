package ru.yandex.service;

import org.springframework.http.ResponseEntity;
import ru.yandex.model.dto.UserCreateDTO;
import ru.yandex.model.dto.UserPasswordDTO;
import ru.yandex.model.dto.UserResponseDTO;
import ru.yandex.model.dto.UserUpdateDTO;

import java.util.List;
import java.util.Map;

public interface UserService {
    UserResponseDTO save(UserCreateDTO userCreateDTO);

    UserResponseDTO update(Long headerUserId, UserUpdateDTO userUpdateDTO);

    UserResponseDTO getUserById(Long id, Long headerUserId);

    ResponseEntity<Map<String, String>> delete(Long headerUserId, UserPasswordDTO userPasswordDTO);

    List<UserResponseDTO> getAllUsers(int page, int size);
}

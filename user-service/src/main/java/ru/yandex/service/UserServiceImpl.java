package ru.yandex.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.yandex.exception.IncorrectParameterException;
import ru.yandex.exception.UserNotFoundException;
import ru.yandex.mapper.UserMapper;
import ru.yandex.model.User;
import ru.yandex.model.dto.UserCreateDTO;
import ru.yandex.model.dto.UserPasswordDTO;
import ru.yandex.model.dto.UserResponseDTO;
import ru.yandex.model.dto.UserUpdateDTO;
import ru.yandex.repository.UserRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserResponseDTO save(UserCreateDTO userCreateDTO) {
        User user = userRepository.save(userMapper.toEntity(userCreateDTO));
        return userMapper.toResponseDTO(user);
    }

    @Override
    public UserResponseDTO update(Long headerUserId, UserUpdateDTO userUpdateDTO) {
        checkHeaderUserId(headerUserId);

        User user = userRepository.findById(headerUserId)
                .orElseThrow(() -> new UserNotFoundException("Пользователь не найден"));

        if (!userUpdateDTO.getPassword().equals(user.getPassword())) {
            throw new IncorrectParameterException("Неправильный пароль");
        }

        user.setName(userUpdateDTO.getName());
        user.setAboutMe(userUpdateDTO.getAboutMe());

        userRepository.save(user);

        return userMapper.toResponseDTO(user);
    }

    @Override
    public UserResponseDTO getUserById(Long id, Long headerUserId) {
        checkHeaderUserId(headerUserId);

        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Пользователь не найден"));

        if (Objects.equals(id, headerUserId)) {
            return userMapper.toFullResponseDTO(user);
        }

        return userMapper.toResponseDTO(user);
    }

    @Override
    public List<UserResponseDTO> getAllUsers(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id"));
        return userRepository.findAll(pageable).map(userMapper::toResponseDTO).stream().toList();

    }

    @Override
    public ResponseEntity<Map<String, String>> delete(Long headerUserId, UserPasswordDTO userPasswordDTO) {
        checkHeaderUserId(headerUserId);

        User user = userRepository.findById(headerUserId)
                .orElseThrow(() -> new UserNotFoundException("Пользователь не найден"));

        if (!user.getPassword().equals(userPasswordDTO.getPassword())) {
            throw new IncorrectParameterException("Неправильный пароль");
        }

        userRepository.delete(user);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Пользователь успешно удален");
        return ResponseEntity.ok(response);
    }


    private void checkHeaderUserId(Long headerUserId) {
        if (headerUserId == null || headerUserId <= 0) {
            throw new IncorrectParameterException("X-User-Id не может быть null или отрицатльным");
        }
    }
}

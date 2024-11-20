package ru.yandex.service;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;
import ru.yandex.exception.IncorrectParameterException;
import ru.yandex.exception.UserNotFoundException;
import ru.yandex.model.User;
import ru.yandex.model.dto.UserCreateDTO;
import ru.yandex.model.dto.UserPasswordDTO;
import ru.yandex.model.dto.UserResponseDTO;
import ru.yandex.model.dto.UserUpdateDTO;
import ru.yandex.repository.UserRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@ActiveProfiles("test")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Transactional
@Sql(scripts = "/schema.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class UserServiceImplTest {
    private final UserServiceImpl userService;
    private final UserRepository userRepository;

    UserCreateDTO userCreateDTO = UserCreateDTO.builder()
            .name("user1")
            .email("user@ya.ru")
            .password("userpassword")
            .aboutMe("iamuser")
            .build();

    @AfterEach
    void clean() {
        userRepository.deleteAll();
    }


    @Test
    void save_validUser_success() {

        UserResponseDTO savedUser = userService.save(userCreateDTO);

        assertEquals(1L, savedUser.getId());
        assertEquals(savedUser.getName(), userCreateDTO.getName());
        assertEquals(savedUser.getEmail(), userCreateDTO.getEmail());
        assertEquals(savedUser.getAboutMe(), userCreateDTO.getAboutMe());
    }

    @Test
    void update_validData_success() {
        UserResponseDTO savedUser = userService.save(userCreateDTO);

        UserUpdateDTO userUpdateDTO = new UserUpdateDTO();
        userUpdateDTO.setName("New Name");
        userUpdateDTO.setAboutMe("Updated about me");
        userUpdateDTO.setPassword("userpassword");

        UserResponseDTO updatedUser = userService.update(savedUser.getId(), userUpdateDTO);

        assertEquals(updatedUser.getName(), userUpdateDTO.getName());
        assertEquals(updatedUser.getAboutMe(), userUpdateDTO.getAboutMe());
    }

    @Test
    void update_wrongPassword_throwsException() {
        UserResponseDTO savedUser = userService.save(userCreateDTO);

        UserUpdateDTO userUpdateDTO = new UserUpdateDTO();
        userUpdateDTO.setName("New Name");
        userUpdateDTO.setPassword("wrongPassword");
        assertThrows(IncorrectParameterException.class, () -> userService.update(savedUser.getId(), userUpdateDTO));
    }


    @Test
    void getUserById_existingUser_success() {
        UserResponseDTO savedUser = userService.save(userCreateDTO);

        UserResponseDTO retrievedUser = userService.getUserById(1L, 1L);

        assertEquals(savedUser.getId(),retrievedUser.getId());
        assertEquals(savedUser.getName(),retrievedUser.getName()); ;
    }

    @Test
    void getUserById_nonExistingUser_throwsException() {
        assertThrows(UserNotFoundException.class, () -> userService.getUserById(100L, 100L));
    }


    @Test
    void delete_validData_success() {
        UserResponseDTO savedUser = userService.save(userCreateDTO);

        UserPasswordDTO userPasswordDTO = new UserPasswordDTO();
        userPasswordDTO.setPassword("userpassword");

        userService.delete(savedUser.getId(), userPasswordDTO);
        Optional<User> user = userRepository.findById(savedUser.getId());

        assertEquals(Optional.empty(), user);
    }

    @Test
    void delete_wrongPassword_throwsException() {
        UserResponseDTO savedUser = userService.save(userCreateDTO);

        UserPasswordDTO userPasswordDTO = new UserPasswordDTO();
        userPasswordDTO.setPassword("wrongPassword");

        assertThrows(IncorrectParameterException.class, () -> userService.delete(savedUser.getId(), userPasswordDTO));
    }

    @Test
    void getAllUsers_success() {
        UserResponseDTO savedUser = userService.save(userCreateDTO);
        UserResponseDTO savedUser2 = userService.save(userCreateDTO);

        List<UserResponseDTO> users = userService.getAllUsers(0, 10);

        assertEquals(2, users.size());
    }
}

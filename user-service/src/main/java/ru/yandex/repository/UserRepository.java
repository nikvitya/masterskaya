package ru.yandex.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.yandex.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}

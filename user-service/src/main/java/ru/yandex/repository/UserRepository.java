package ru.yandex.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.yandex.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
}

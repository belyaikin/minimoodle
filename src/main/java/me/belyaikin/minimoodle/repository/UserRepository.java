package me.belyaikin.minimoodle.repository;

import me.belyaikin.minimoodle.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByTelegramId(long id);
}

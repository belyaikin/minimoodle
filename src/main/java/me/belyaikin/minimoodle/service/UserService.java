package me.belyaikin.minimoodle.service;

import me.belyaikin.minimoodle.model.User;
import me.belyaikin.minimoodle.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public User create(User user) {
        return repository.save(user);
    }

    public User getByTelegramId(long id) {
        return repository.findById(id).orElse(null);
    }

    public boolean isUserRegistered(long id) {
        return getByTelegramId(id) != null;
    }

    public List<User> getAll() {
        return repository.findAll();
    }
}

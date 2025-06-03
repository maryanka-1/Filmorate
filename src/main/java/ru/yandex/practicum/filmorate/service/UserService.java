package ru.yandex.practicum.filmorate.service;

import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.User;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {
    private final Map<Integer, User> users = new HashMap<>();
    private static int userIdCounter = 0;

    public User addUser(User newUser) {
        if (!users.containsValue(newUser)) {
            newUser.setId(++userIdCounter);
            users.put(newUser.getId(), newUser);
            return newUser;
        } else throw new ValidationException("Этот пользователь уже был добавлен");
    }

    public User updateUser(User newUser) {
        if (users.containsKey(newUser.getId())) {
            users.put(newUser.getId(), newUser);
            return newUser;
        } else throw new ValidationException("Пользователь не найден");

    }

    public Collection<User> getUsers() {
        return users.values();
    }
}

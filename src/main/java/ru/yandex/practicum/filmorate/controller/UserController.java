package ru.yandex.practicum.filmorate.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.service.UserService;

import java.time.LocalDate;
import java.util.Collection;

@RestController
@RequestMapping("/users")
@Slf4j
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    public User addUser(@RequestBody User newUser) {
        if (checkValidation(newUser)) {
            userService.addUser(newUser);
            log.info("пользователь добавлен: {}", newUser);
        }
        return newUser;
    }

    @PutMapping
    public User update(@RequestBody User newUser) {
        if (checkValidation(newUser)) {
            userService.updateUser(newUser);
            log.info("Пользователь обновлен: {}", newUser);
        } else throw new ValidationException("пользователь не найден");
        return newUser;
    }

    @GetMapping
    public Collection<User> getUsers() {
        return userService.getUsers();
    }

    public boolean checkValidation(User newUser) {
        if (newUser == null) {
            log.error("Пользователь некорректный");
            throw new ValidationException("Анкета пользователя не заполнена");
        }
        if (newUser.getEmail() == null || newUser.getEmail().isEmpty() || !newUser.getEmail().contains("@")) {
            log.error("Не корректная почта");
            throw new ValidationException("Адрес электронной почты не может быть пустым либо введен не корректный формат");
        }
        if (newUser.getLogin() == null || newUser.getLogin().isEmpty() || newUser.getLogin().contains(" ")) {
            log.error("Не корректный логин");
            throw new ValidationException("Логин не может быть пустым или содержать пробелы");
        }

        if (newUser.getBirthday().isAfter(LocalDate.now()) || newUser.getBirthday().isEqual(LocalDate.now())) {
            log.error("Некорректная дата рождения");
            throw new ValidationException("Дата рождения должна быть меньше текущей");
        }
        if (!userService.getUsers().isEmpty()) {
            userService.getUsers().forEach(user -> {
                if (user.getId() != newUser.getId()) {
                    if (user.getEmail().equals(newUser.getEmail())) {
                        log.error("Введенная почта уже занята");
                        throw new ValidationException("Пользователь с электронной почтой " + newUser.getEmail() + " уже зарегистрирован.");
                    }
                    if (user.getLogin().equals(newUser.getLogin())) {
                        log.error("Введенный логин уже занят");
                        throw new ValidationException("Пользователь с логином " + newUser.getLogin() + " уже зарегистрирован.");
                    }
                }
            });
        }
        if (newUser.getName() == null) {
            log.info("Именем пользователя будет логин, т.к. не было введено");
            newUser.setName(newUser.getLogin());
        }
        return true;
    }
}

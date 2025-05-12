package ru.yandex.practicum.filmorate.controller;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.User;
import java.time.LocalDate;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


class UserControllerTest {
    UserController controller = new UserController();
    User user = new User("mail@ya.ru", "Peter", "PeterPan", LocalDate.of(2000, 10, 10));
    User user1 = new User("bbb@ya.ru", "Vasya", "vasya", LocalDate.of(2000, 10, 10));

    @Test
    void shouldAddUser() {
        controller.addUser(user);
        int expected = 1;
        int actual = controller.getUsers().size();
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void shouldUpdate() {
        controller.addUser(user);
        controller.update(user);
        Map<Integer, User> temp = new HashMap<>();
        Collection<User> actual = controller.getUsers();
        temp.put(user.getId(), user);
        Collection<User> expected = temp.values();
        assertThat(actual).containsExactlyInAnyOrderElementsOf(expected);
    }

    @Test
    void shouldGetUsers() {
        controller.addUser(user);
        controller.addUser(user1);
        Map<Integer, User> temp = new HashMap<>();
        temp.put(user.getId(), user);
        temp.put(user1.getId(), user1);
        Collection<User> actual = controller.getUsers();
        Collection<User> expected = temp.values();
        assertThat(actual).containsExactlyInAnyOrderElementsOf(expected);
    }

    @Test
    void shouldValidNullEmail() {
        ValidationException exception = assertThrows(ValidationException.class, () -> controller.checkValidation(new User(
                null, "Peta", "hjn", LocalDate.of(2000, 10, 10))));
        String expected = "Адрес электронной почты не может быть пустым либо введен не корректный формат";
        String actual = exception.getMessage();
        int expected1 = 0;
        int actual1 = controller.getUsers().size();
        assertThat(actual).isEqualTo(expected);
        assertThat(actual1).isEqualTo(expected1);
    }

    @Test
    void shouldAddUserEmptyEmail() {
        ValidationException exception = assertThrows(ValidationException.class, () -> controller.checkValidation(new User(
                "", "Peta", "hjn", LocalDate.of(2000, 10, 10))));
        String expected = "Адрес электронной почты не может быть пустым либо введен не корректный формат";
        String actual = exception.getMessage();
        int expected1 = 0;
        int actual1 = controller.getUsers().size();
        assertThat(actual).isEqualTo(expected);
        assertThat(actual1).isEqualTo(expected1);
    }

    @Test
    void shouldAddUserIncorrectEmail() {
        ValidationException exception = assertThrows(ValidationException.class, () -> controller.checkValidation(new User(
                "hahahha", "Peta", "hjn", LocalDate.of(2000, 10, 10))));
        String expected = "Адрес электронной почты не может быть пустым либо введен не корректный формат";
        String actual = exception.getMessage();
        int expected1 = 0;
        int actual1 = controller.getUsers().size();
        assertThat(actual).isEqualTo(expected);
        assertThat(actual1).isEqualTo(expected1);
    }

    @Test
    void shouldAddUserNullLogin() {
        ValidationException exception = assertThrows(ValidationException.class, () -> controller.checkValidation(new User(
                "bbb@ya.ru", "Peta", null, LocalDate.of(2000, 10, 10))));
        String expected = "Логин не может быть пустым или содержать пробелы";
        String actual = exception.getMessage();
        int expected1 = 0;
        int actual1 = controller.getUsers().size();
        assertThat(actual).isEqualTo(expected);
        assertThat(actual1).isEqualTo(expected1);
    }

    @Test
    void shouldAddUserEmptyLogin() {
        ValidationException exception = assertThrows(ValidationException.class, () -> controller.checkValidation(new User(
                "bbb@ya.ru", "Peta", "", LocalDate.of(2000, 10, 10))));
        String expected = "Логин не может быть пустым или содержать пробелы";
        String actual = exception.getMessage();
        int expected1 = 0;
        int actual1 = controller.getUsers().size();
        assertThat(actual).isEqualTo(expected);
        assertThat(actual1).isEqualTo(expected1);
    }

    @Test
    void shouldAddUserIncorrectLogin() {
        ValidationException exception = assertThrows(ValidationException.class, () -> controller.checkValidation(new User(
                "bbb@ya,ru", "Peter", "Peter Pan", LocalDate.of(2000, 10, 10))));
        String expected = "Логин не может быть пустым или содержать пробелы";
        String actual = exception.getMessage();
        int expected1 = 0;
        int actual1 = controller.getUsers().size();
        assertThat(actual).isEqualTo(expected);
        assertThat(actual1).isEqualTo(expected1);
    }

    @Test
    void shouldAddUserNameNull() {
        User user2 = new User("bbb@ya.ru", null, "PeterPan", LocalDate.of(2000, 10, 10));
        controller.addUser(user2);
        String expected = "PeterPan";
        String actual = controller.listUsers.get(user2.getId()).getName();
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void shouldAddUserBirthdayInFuture() {
        ValidationException exception = assertThrows(ValidationException.class, () -> controller.checkValidation(new User(
                "bbb@ya.ru", "Peta", "Peta", LocalDate.of(2050, 10, 10))));
        String expected = "Дата рождения должна быть меньше текущей";
        String actual = exception.getMessage();
        int expected1 = 0;
        int actual1 = controller.getUsers().size();
        assertThat(actual).isEqualTo(expected);
        assertThat(actual1).isEqualTo(expected1);
    }

    @Test
    void shouldAddUserBirthdayToday() {
        ValidationException exception = assertThrows(ValidationException.class, () -> controller.checkValidation(new User(
                "bbb@ya,ru", "Peter", "PeterPan", LocalDate.now())));
        String expected = "Дата рождения должна быть меньше текущей";
        String actual = exception.getMessage();
        int expected1 = 0;
        int actual1 = controller.getUsers().size();
        assertThat(actual).isEqualTo(expected);
        assertThat(actual1).isEqualTo(expected1);
    }

    @Test
    void shouldAddUserEmailBusy() {
        controller.addUser(user);
        ValidationException exception = assertThrows(ValidationException.class, () -> controller.checkValidation(new User(
                "mail@ya.ru", "Peter", "PeterPan", LocalDate.of(2000, 10, 10))));
        String expected = "Пользователь с электронной почтой mail@ya.ru уже зарегистрирован.";
        String actual = exception.getMessage();
        int expected1 = 1;
        int actual1 = controller.getUsers().size();
        assertThat(actual).isEqualTo(expected);
        assertThat(actual1).isEqualTo(expected1);
    }

    @Test
    void shouldAddUserLoginBusy() {
        controller.addUser(user);
        ValidationException exception = assertThrows(ValidationException.class, () -> controller.checkValidation(new User(
                "mahjl@ya.ru", "Peter", "PeterPan", LocalDate.of(2000, 10, 10))));
        String expected = "Пользователь с логином PeterPan уже зарегистрирован.";
        String actual = exception.getMessage();
        int expected1 = 1;
        int actual1 = controller.getUsers().size();
        assertThat(actual).isEqualTo(expected);
        assertThat(actual1).isEqualTo(expected1);
    }

    @Test
    void shouldAddNull() {
        ValidationException exception = assertThrows(ValidationException.class, () -> controller.checkValidation(null));
        String expected = "Анкета пользователя не заполнена";
        String actual = exception.getMessage();
        assertThat(actual).isEqualTo(expected);
    }
}
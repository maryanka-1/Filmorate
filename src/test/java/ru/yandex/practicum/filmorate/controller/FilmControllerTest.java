package ru.yandex.practicum.filmorate.controller;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


class FilmControllerTest {
    FilmController controller = new FilmController();
    Film film1 = new Film("Limitless", "science fiction film", LocalDate.of(2011, 01, 01), 105);
    Film film2 = new Film("Shutter Island", "detective film", LocalDate.of(2009, 01, 01), 138);

    @Test
    void shouldAddFilm() {
        controller.addFilm(film1);
        int expected = 1;
        int actual = controller.getFilms().size();
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void shouldUpdateFilm() {
        controller.addFilm(film1);
        int id = film1.getId();
        film1.setName("No name");
        String expected = "No name";
        controller.updateFilm(film1);
        String actual = controller.films.get(id).getName();
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void shouldGetFilms() {
        controller.addFilm(film1);
        controller.addFilm(film2);
        Map<Integer, Film> temp = new HashMap<>();
        temp.put(film1.getId(), film1);
        temp.put(film2.getId(), film2);
        Collection<Film> actual = controller.getFilms();
        Collection<Film> expected = temp.values();
        assertThat(actual).containsExactlyInAnyOrderElementsOf(expected);
    }

    @Test
    void shouldValidFilmNull() {
        ValidationException exception = assertThrows(ValidationException.class, () -> controller.checkValidation(null));
        String expected = "Фильм не может быть добавлен";
        String actual = exception.getMessage();
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void shouldValidNameFilmNull() {
        ValidationException exception = assertThrows(ValidationException.class, () -> controller.checkValidation(
                new Film(null, "description", LocalDate.now(), 100)));
        String expected = "Название фильма не может быть пустым";
        String actual = exception.getMessage();
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void shouldValidNameFilmEmpty() {
        ValidationException exception = assertThrows(ValidationException.class, () -> controller.checkValidation(
                new Film("", "description", LocalDate.now(), 100)));
        String expected = "Название фильма не может быть пустым";
        String actual = exception.getMessage();
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void shouldValidDescription() {
        ValidationException exception = assertThrows(ValidationException.class, () -> controller.checkValidation(new Film(
                "Name",
                "description/description/descrihjhjhjhjhjhjhjhjhjhjhjhjhjhhhpjkjkjkjkjkjkjkjkjjjjjjjhhkjhjhnbtion/description/description/description/description/description/description/description/description/description/",
                LocalDate.now(),
                100)));
        String expected = "Описание на может превышать 200 символов";
        String actual = exception.getMessage();
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void shouldValidDateFilm() {
        ValidationException exception = assertThrows(ValidationException.class, () -> controller.checkValidation(new Film(
                "Name",
                "description",
                LocalDate.of(1800, 1, 1),
                100)));
        String expected = "Дата выхода фильма не может быть ранее 28.12.1895";
        String actual = exception.getMessage();
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void shouldValidDuration() {
        ValidationException exception = assertThrows(ValidationException.class, () -> controller.checkValidation(new Film(
                "Name",
                "description",
                LocalDate.now(), -10)));
        String expected = "Некорректная длительность фильма";
        String actual = exception.getMessage();
        assertThat(actual).isEqualTo(expected);
    }
}
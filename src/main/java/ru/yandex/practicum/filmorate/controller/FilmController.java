package ru.yandex.practicum.filmorate.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.service.FilmService;

import java.time.LocalDate;
import java.util.Collection;

@RestController
@RequestMapping("/films")
@Slf4j
@RequiredArgsConstructor
public class FilmController {
    private final FilmService filmService;

    @PostMapping
    public Film addFilm(@RequestBody Film newFilm) {
        if (checkValidation(newFilm)) {
            filmService.addFilm(newFilm);
            log.info("Фильм добавлен: {}", newFilm);
        }
        return newFilm;
    }


    @PutMapping
    public Film updateFilm(@RequestBody Film newFilm) {
        if (checkValidation(newFilm)) {
            filmService.updateFilm(newFilm);
            log.info("фильм обновлен: {}", newFilm);
        }
        return newFilm;
    }

    @GetMapping
    public Collection<Film> getFilms() {
        return filmService.getFilms();
    }

    public boolean checkValidation(Film newFilm) {
        if (newFilm == null) {
            log.error("Ошибка при добавлении или обновлении фильма");
            throw new ValidationException("Фильм не может быть добавлен");
        }
        if (newFilm.getName() == null || newFilm.getName().isEmpty()) {
            log.error("Ошибка при добавлении или обновлении фильма");
            throw new ValidationException("Название фильма не может быть пустым");
        }
        if (newFilm.getDescription().length() > 200) {
            log.error("Ошибка при добавлении или обновлении фильма");
            throw new ValidationException("Описание на может превышать 200 символов");
        }
        if (newFilm.getReleaseDate().isBefore(LocalDate.of(1895, 12, 28))) {
            log.error("Ошибка при добавлении или обновлении фильма");
            throw new ValidationException("Дата выхода фильма не может быть ранее 28.12.1895");
        }
        if (newFilm.getDuration() < 1) {
            log.error("Ошибка при добавлении или обновлении фильма");
            throw new ValidationException("Некорректная длительность фильма");
        }
        return true;
    }
}

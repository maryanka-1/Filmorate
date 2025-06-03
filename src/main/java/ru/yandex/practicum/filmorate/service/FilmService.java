package ru.yandex.practicum.filmorate.service;

import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class FilmService {
    private final Map<Integer, Film> films = new HashMap<>();
    private static int filmIdCounter = 0;

    public Film addFilm(Film newFilm) {
        if (!films.containsValue(newFilm)) {
            newFilm.setId(++filmIdCounter);
            films.put(newFilm.getId(), newFilm);
            return newFilm;
        } else throw new ValidationException("Этот фильм уже был добавлен");
    }

    public Film updateFilm(Film newFilm) {
        if (films.containsKey(newFilm.getId())) {
            films.put(newFilm.getId(), newFilm);
            return newFilm;
        } else throw new ValidationException("фильм не найден");

    }

    public Collection<Film> getFilms() {
        return films.values();
    }
}

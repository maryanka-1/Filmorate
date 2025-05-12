package ru.yandex.practicum.filmorate.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Film.
 */
@Getter
@Setter
public class Film {
    private static int idCounter = 0;
    private int id;
    private String name;
    private String description;
    private LocalDate releaseDate;
    private double duration;

    public Film(String name, String description, LocalDate releaseDate, double duration) {
        this.id = ++idCounter;
        this.name = name;
        this.description = description;
        this.releaseDate = releaseDate;
        this.duration = duration;
    }
}

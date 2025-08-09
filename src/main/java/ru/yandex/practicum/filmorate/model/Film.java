package ru.yandex.practicum.filmorate.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

/**
 * Film.
 */
@Getter
@Setter
public class Film {
    private int id;
    private String name;
    private String description;
    private LocalDate releaseDate;
    private double duration;
    private Set<Genre> genre;
    private Rating rating;

    public Film(String name, String description, LocalDate releaseDate, double duration, Set<Genre> genre, Rating rating) {
        this.name = name;
        this.description = description;
        this.releaseDate = releaseDate;
        this.duration = duration;
        this.genre = genre;
        this.rating = rating;
    }

}

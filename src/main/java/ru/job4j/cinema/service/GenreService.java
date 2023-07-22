package ru.job4j.cinema.service;

import ru.job4j.cinema.model.Genre;

import java.util.Collection;

public interface GenreService {

    Genre findById(int id);

    Collection<Genre> findAll();
}

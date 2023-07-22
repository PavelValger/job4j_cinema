package ru.job4j.cinema.service;

import ru.job4j.cinema.dto.SessionCell;
import ru.job4j.cinema.dto.SessionPreview;
import ru.job4j.cinema.model.Session;

import java.util.Collection;

public interface SessionService {

    SessionCell findById(int id);

    Collection<SessionPreview> findAll();

    Collection<Session> findByFilm(int filmId);
}

package ru.job4j.cinema.service;

import ru.job4j.cinema.dto.SessionCell;
import ru.job4j.cinema.dto.SessionPreview;

import java.util.Collection;

public interface SessionService {

    SessionCell findById(int id);

    Collection<SessionPreview> findAll();
}

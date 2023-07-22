package ru.job4j.cinema.service;

import org.springframework.stereotype.Service;
import ru.job4j.cinema.dto.SessionCell;
import ru.job4j.cinema.dto.SessionPreview;
import ru.job4j.cinema.model.Session;
import ru.job4j.cinema.repository.SessionRepository;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class SimpleSessionService implements SessionService {
    private final SessionRepository sessionRepository;
    private final HallService hallService;
    private final FilmService filmService;
    private final ConcurrentHashMap<Integer, SessionPreview> sessionCells = new ConcurrentHashMap<>();
    private final AtomicInteger count = new AtomicInteger();
    private final Collection<Integer> keeperPlaces = new ConcurrentLinkedQueue<>();

    public SimpleSessionService(SessionRepository sessionRepository,
                                HallService hallService, FilmService filmService) {
        this.sessionRepository = sessionRepository;
        this.hallService = hallService;
        this.filmService = filmService;
    }

    private void zeroing() {
        count.set(0);
        keeperPlaces.clear();
    }

    @Override
    public SessionCell findById(int id) {
        var session = sessionRepository.findById(id);
        var film = filmService.findById(session.getFilmId());
        var hall = hallService.findById(session.getHallsId());
        SessionCell sessionCell = new SessionCell(film, session.getStartTime(), session.getEndTime(),
                session.getPrice(), null, null);
        while (count.incrementAndGet() <= hall.getRowCount()) {
            keeperPlaces.add(count.get());
        }
        sessionCell.setRowCount(keeperPlaces);
        zeroing();
        int placeInRowCount = hall.getPlaceCount() / hall.getRowCount();
        while (count.incrementAndGet() <= placeInRowCount) {
            keeperPlaces.add(count.get());
        }
        sessionCell.setPlaceInRowCount(keeperPlaces);
        zeroing();
        return sessionCell;
    }

    @Override
    public Collection<SessionPreview> findAll() {
        var sessions = sessionRepository.findAll();
        for (Session session : sessions) {
            var sessionPreview = new SessionPreview(
                    session.getId(),
                    filmService.findById(session.getFilmId()),
                    hallService.findById(session.getHallsId()),
                    session.getStartTime(), session.getEndTime(), session.getPrice());
            sessionCells.putIfAbsent(session.getId(), sessionPreview);
        }
        return sessionCells.values();
    }

    @Override
    public Collection<Session> findByFilm(int filmId) {
        return sessionRepository.findByFilm(filmId);
    }
}

package ru.job4j.cinema.service;

import org.springframework.stereotype.Service;
import ru.job4j.cinema.dto.SessionCell;
import ru.job4j.cinema.dto.SessionPreview;
import ru.job4j.cinema.model.Session;
import ru.job4j.cinema.repository.SessionRepository;

import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class SimpleSessionService implements SessionService {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("EEEE dd MMMM HH:mm");
    private final SessionRepository sessionRepository;
    private final HallService hallService;
    private final FilmService filmService;
    private final ConcurrentHashMap<Integer, SessionPreview> sessionCells = new ConcurrentHashMap<>();
    private final AtomicInteger countRows = new AtomicInteger();
    private final AtomicInteger countPlaces = new AtomicInteger();
    private final Collection<Integer> keeperPlaces = new ConcurrentLinkedQueue<>();
    private final Collection<Integer> keeperRows = new ConcurrentLinkedQueue<>();

    public SimpleSessionService(SessionRepository sessionRepository,
                                HallService hallService, FilmService filmService) {
        this.sessionRepository = sessionRepository;
        this.hallService = hallService;
        this.filmService = filmService;
    }

    @Override
    public SessionCell findById(int id) {
        var session = sessionRepository.findById(id);
        var film = filmService.findById(session.getFilmId());
        var hall = hallService.findById(session.getHallsId());
        while (countRows.incrementAndGet() <= hall.getRowCount()) {
            keeperRows.add(countRows.get());
        }
        int placeInRowCount = hall.getPlaceCount() / hall.getRowCount();
        while (countPlaces.incrementAndGet() <= placeInRowCount) {
            keeperPlaces.add(countPlaces.get());
        }
        return new SessionCell(
                session.getId(), film, FORMATTER.format(session.getStartTime()),
                FORMATTER.format(session.getEndTime()),
                session.getPrice(), keeperRows, keeperPlaces);
    }

    @Override
    public Collection<SessionPreview> findAll() {
        var sessions = sessionRepository.findAll();
        for (Session session : sessions) {
            var sessionPreview = new SessionPreview(
                    session.getId(),
                    filmService.findById(session.getFilmId()),
                    hallService.findById(session.getHallsId()),
                    FORMATTER.format(session.getStartTime()), FORMATTER.format(session.getEndTime()),
                    session.getPrice());
            sessionCells.putIfAbsent(session.getId(), sessionPreview);
        }
        return sessionCells.values();
    }
}

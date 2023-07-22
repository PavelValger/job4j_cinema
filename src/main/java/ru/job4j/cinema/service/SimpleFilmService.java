package ru.job4j.cinema.service;

import org.springframework.stereotype.Service;
import ru.job4j.cinema.dto.FilmPreview;
import ru.job4j.cinema.model.Film;
import ru.job4j.cinema.repository.FilmRepository;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class SimpleFilmService implements FilmService {
    private final ConcurrentHashMap<Integer, FilmPreview> filmsPreview = new ConcurrentHashMap<>();
    private final FilmRepository filmRepository;
    private final GenreService genreService;

    public SimpleFilmService(FilmRepository filmRepository, GenreService genreService) {
        this.filmRepository = filmRepository;
        this.genreService = genreService;
    }

    @Override
    public Film findById(int id) {
        return filmRepository.findById(id);
    }

    @Override
    public Collection<Film> findAllFilm() {
        return filmRepository.findAll();
    }

    @Override
    public Collection<FilmPreview> findAll() {
        var films = filmRepository.findAll();
        for (Film film : films) {
            FilmPreview filmPreview = new FilmPreview(
                    film.getName(), film.getDescription(),
                    film.getYear(), film.getMinimalAge(),
                    film.getDurationInMinutes(), genreService.findById(film.getGenreId()).getName());
            filmsPreview.putIfAbsent(film.getId(), filmPreview);
        }
        return filmsPreview.values();
    }
}
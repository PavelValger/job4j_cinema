package ru.job4j.cinema.dto;

import ru.job4j.cinema.model.Film;
import ru.job4j.cinema.model.Session;

import java.util.Objects;

public class Movie {
    private Film film;
    private String genre;
    private Session session;

    public Movie(Film film, String genre, Session session) {
        this.film = film;
        this.genre = genre;
        this.session = session;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Movie movie = (Movie) o;
        return Objects.equals(session, movie.session);
    }

    @Override
    public int hashCode() {
        return Objects.hash(session);
    }
}

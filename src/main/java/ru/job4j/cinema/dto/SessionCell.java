package ru.job4j.cinema.dto;

import ru.job4j.cinema.model.Film;
import ru.job4j.cinema.model.Hall;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Objects;

public class SessionCell {
    private Film film;
    private Hall hall;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private int price;
    private Collection<Integer> rowCount;
    private Collection<Integer> placeInRowCount;

    public SessionCell(Film film, Hall hall, LocalDateTime startTime, LocalDateTime endTime, int price,
                       Collection<Integer> rowCount, Collection<Integer> placeInRowCount) {
        this.film = film;
        this.hall = hall;
        this.startTime = startTime;
        this.endTime = endTime;
        this.price = price;
        this.rowCount = rowCount;
        this.placeInRowCount = placeInRowCount;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public Hall getHall() {
        return hall;
    }

    public void setHall(Hall hall) {
        this.hall = hall;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Collection<Integer> getRowCount() {
        return rowCount;
    }

    public void setRowCount(Collection<Integer> rowCount) {
        this.rowCount = rowCount;
    }

    public Collection<Integer> getPlaceInRowCount() {
        return placeInRowCount;
    }

    public void setPlaceInRowCount(Collection<Integer> placeInRowCount) {
        this.placeInRowCount = placeInRowCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SessionCell that = (SessionCell) o;
        return Objects.equals(film, that.film) && Objects.equals(hall, that.hall)
                && Objects.equals(startTime, that.startTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(film, hall, startTime);
    }
}

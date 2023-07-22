package ru.job4j.cinema.service;

import ru.job4j.cinema.dto.FileTransfer;

import java.util.Optional;

public interface FileService {

    Optional<FileTransfer> getFileById(int id);
}

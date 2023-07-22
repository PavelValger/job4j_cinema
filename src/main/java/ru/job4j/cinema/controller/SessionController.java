package ru.job4j.cinema.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.cinema.service.SessionService;

@Controller
@RequestMapping("/sessions")
public class SessionController {
    private final SessionService sessionService;

    public SessionController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @GetMapping("/{id}")
    public String getById(Model model, @PathVariable int id) {
        var sessionCell = sessionService.findById(id);
        model.addAttribute("rowCount", sessionCell.getRowCount());
        model.addAttribute("placeInRowCount", sessionCell.getPlaceInRowCount());
        model.addAttribute("sessionCell", sessionCell);
        return "sessions/one";
    }

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("sessions", sessionService.findAll());
        return "sessions/list";
    }
}

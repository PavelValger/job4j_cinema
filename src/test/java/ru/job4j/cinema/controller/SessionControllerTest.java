package ru.job4j.cinema.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.ui.ConcurrentModel;
import ru.job4j.cinema.dto.SessionCell;
import ru.job4j.cinema.dto.SessionPreview;
import ru.job4j.cinema.service.SessionService;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

class SessionControllerTest {
    private SessionService sessionService;
    private SessionController sessionController;

    @BeforeEach
    public void initServices() {
        sessionService = mock(SessionService.class);
        sessionController = new SessionController(sessionService);
    }

    @Test
    public void whenRequestSessionsListPageThenGetPageWithSessionsPreview() {
        var session1 = new SessionPreview(1, null, null, "", "", 300);
        var session2 = new SessionPreview(2, null, null, "", "", 500);
        var expectedSessions = List.of(session1, session2);
        when(sessionService.findAll()).thenReturn(expectedSessions);

        var model = new ConcurrentModel();
        var view = sessionController.getAll(model);
        var actualSessions = model.getAttribute("sessions");

        assertThat(view).isEqualTo("sessions/list");
        assertThat(actualSessions).isEqualTo(expectedSessions);
    }

    @Test
    public void whenRequestSessionOnePageThenGetPageWithSessionsOne() {
        var session = new SessionCell(
                1, null, "", "", 400, null, null);
        var model = new ConcurrentModel();
        var idArgumentCaptor = ArgumentCaptor.forClass(Integer.class);
        when(sessionService.findById(idArgumentCaptor.capture())).thenReturn(session);

        var view = sessionController.getById(model, session.getId(), new MockHttpServletRequest());
        var actualId = idArgumentCaptor.getValue();

        assertThat(view).isEqualTo("sessions/one");
        assertThat(actualId).isEqualTo(session.getId());
    }
}
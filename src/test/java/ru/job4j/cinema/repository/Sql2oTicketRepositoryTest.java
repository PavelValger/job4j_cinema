package ru.job4j.cinema.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

import org.sql2o.Sql2o;
import ru.job4j.cinema.configuration.DatasourceConfiguration;
import ru.job4j.cinema.model.Ticket;
import ru.job4j.cinema.model.User;

import java.util.Properties;

class Sql2oTicketRepositoryTest {
    private static Sql2oTicketRepository sql2oTicketRepository;
    private static Sql2oUserRepository sql2oUserRepository;
    private static Sql2o sql2o;

    @BeforeAll
    public static void initRepositories() throws Exception {
        var properties = new Properties();
        try (var inputStream = Sql2oFileRepository.class.getClassLoader()
                .getResourceAsStream("connection.properties")) {
            properties.load(inputStream);
        }
        var url = properties.getProperty("datasource.url");
        var username = properties.getProperty("datasource.username");
        var password = properties.getProperty("datasource.password");
        var configuration = new DatasourceConfiguration();
        var datasource = configuration.connectionPool(url, username, password);
        sql2o = configuration.databaseClient(datasource);
        sql2oUserRepository = new Sql2oUserRepository(sql2o);
        sql2oTicketRepository = new Sql2oTicketRepository(sql2o);
    }

    @AfterEach
    public void clearTable() {
        try (var connection = sql2o.open()) {
            var query = connection.createQuery("TRUNCATE TABLE users RESTART IDENTITY");
            query.executeUpdate();
            query = connection.createQuery("TRUNCATE TABLE tickets RESTART IDENTITY");
            query.executeUpdate();
        }
    }

    @Test
    public void whenSaveTicketThenGetSame() {
        String fullName = "test";
        String email = "pavelwalker@mail.ru";
        String pass = "qwerty";
        var user = sql2oUserRepository.save(new User(0, fullName, email, pass)).get();
        int userId = user.getId();
        var ticket = sql2oTicketRepository
                .save(new Ticket(0, 3, 2, 3, userId)).get();
        Ticket findTicket;
        try (var connection = sql2o.open()) {
            var query = connection.createQuery(
                    "SELECT * FROM tickets WHERE id = :id");
            query.addParameter("id", ticket.getId());
            findTicket = query.setColumnMappings(Ticket.COLUMN_MAPPING).executeAndFetchFirst(Ticket.class);
        }
        assertThat(findTicket).usingRecursiveComparison().isEqualTo(ticket);
    }
}
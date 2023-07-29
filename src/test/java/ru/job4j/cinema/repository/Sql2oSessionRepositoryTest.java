package ru.job4j.cinema.repository;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

import ru.job4j.cinema.configuration.DatasourceConfiguration;
import ru.job4j.cinema.model.Session;

import java.time.LocalDateTime;
import java.util.Properties;

class Sql2oSessionRepositoryTest {
    private static Sql2oSessionRepository sql2oSessionRepository;

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
        var sql2o = configuration.databaseClient(datasource);
        sql2oSessionRepository = new Sql2oSessionRepository(sql2o);
    }

    @Test
    public void whenFindAllSessionsThenGetSame() {
        var dateFirst = LocalDateTime.of(2023, 7, 16, 17, 30);
        var dateSecond = LocalDateTime.of(2023, 7, 16, 19, 0);
        Session session = new Session(6, 3, 2, dateFirst, dateSecond, 300);
        assertThat(sql2oSessionRepository.findAll())
                .hasSize(9)
                .element(5)
                .usingRecursiveComparison().isEqualTo(session);
    }

    @Test
    public void whenFindByIdSessionThenGetSame() {
        var dateFirst = LocalDateTime.of(2023, 7, 16, 17, 30);
        var dateSecond = LocalDateTime.of(2023, 7, 16, 19, 0);
        Session session = new Session(6, 3, 2, dateFirst, dateSecond, 300);
        assertThat(sql2oSessionRepository.findById(6))
                .usingRecursiveComparison().isEqualTo(session);
    }
}
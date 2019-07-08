package com.justpz.tcontainers.training;

import com.justpz.tcontainers.training.db.LiquibaseInitializer;
import org.junit.jupiter.api.*;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

@Testcontainers
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class BookRepositoryTest {
    @Container
    private static final PostgreSQLContainer postgresqlContainer = new PostgreSQLContainer()
            .withDatabaseName("foo")
            .withUsername("foo")
            .withPassword("secret");
    private BookRepository bookRepository;

    @BeforeAll
    void init() throws SQLException {
        try (Connection connection = postgresqlContainer.createConnection("")) {
            LiquibaseInitializer.init(connection);
        }
        TestContainerDataSource dataSource = new TestContainerDataSource(postgresqlContainer);
        this.bookRepository = new BookRepository(dataSource);
    }

    @Order(1)
    @Test
    void shouldGetInitValuesFromDatabase() {

        List<String> strings = bookRepository.getAll();
        assertEquals(2, strings.size());
    }

    @Order(2)
    @Test
    void shouldInsertNewValueToDatabase() {
        int insert = bookRepository.insert("Dupa");
        assertEquals(1, insert);
    }


    @Order(3)
    @Test
    void shouldGetInitValuesWithInserted() {
        List<String> strings = bookRepository.getAll();
        assertEquals(3, strings.size());
    }

}
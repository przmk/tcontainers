package com.justpz.tcontainers.training;

import com.justpz.tcontainers.training.db.LiquibaseInitializer;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
    }

    @BeforeEach
    void setUp() throws SQLException {
        TestContainerDataSource dataSource = new TestContainerDataSource(postgresqlContainer);
        this.bookRepository = new BookRepository(dataSource);
    }

    @Test
    void test() {

        List<String> strings = bookRepository.getAll();
        assertEquals(2, strings.size());
    }

    @Test
    void test2() {
        bookRepository.insert("Dupa");
        List<String> strings = bookRepository.getAll();
        assertEquals(3, strings.size());
    }

    @Test
    void test3() {
        List<String> strings = bookRepository.getAll();
        assertEquals(3, strings.size());
    }

}
package com.justpz.tcontainers.training;

import com.justpz.tcontainers.training.db.LiquibaseInitializer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Testcontainers
class BookRepositoryGlobalTest extends AbstractContainerBaseTest {

    private BookRepository bookRepository;

    @BeforeEach
    void setUp() throws SQLException {
        try (Connection connection = postgresqlContainer.createConnection("")) {
            LiquibaseInitializer.init(connection);
        }
        TestContainerDataSource dataSource = new TestContainerDataSource(postgresqlContainer);
        this.bookRepository = new BookRepository(dataSource);
    }

    @Test
    void shouldGetInitValuesFromDatabase() {
        List<String> strings = bookRepository.getAll();
        assertEquals(2, strings.size());
    }

    @Test
    void shouldInsertNewValueToDatabase() {
        int insert = bookRepository.insert("Dupa");
        assertEquals(1, insert);
        List<String> strings = bookRepository.getAll();
        assertEquals(3, strings.size());
    }

    @Test
    void shouldGetInitWithoutValuesFromPreviousTest() {
        List<String> strings = bookRepository.getAll();
        assertEquals(2, strings.size());
    }

}
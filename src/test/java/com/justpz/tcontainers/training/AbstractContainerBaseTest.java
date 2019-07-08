package com.justpz.tcontainers.training;

import org.testcontainers.containers.JdbcDatabaseContainer;
import org.testcontainers.containers.PostgreSQLContainer;

abstract class AbstractContainerBaseTest {

    static final JdbcDatabaseContainer DATABASE_CONTAINER;

    static {
        DATABASE_CONTAINER = new PostgreSQLContainer()
                .withDatabaseName("foo")
                .withUsername("foo")
                .withPassword("secret");
        DATABASE_CONTAINER.start();
    }
}

package com.justpz.tcontainers.training;

import org.testcontainers.containers.PostgreSQLContainer;

abstract class AbstractContainerBaseTest {

    static final PostgreSQLContainer postgresqlContainer;

    static {
        postgresqlContainer = new PostgreSQLContainer()
                .withDatabaseName("foo")
                .withUsername("foo")
                .withPassword("secret");
        postgresqlContainer.start();

    }
}

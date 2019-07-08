package com.justpz.tcontainers.training;

import com.justpz.tcontainers.training.db.DataSourceFactory;
import org.postgresql.ds.PGSimpleDataSource;
import org.testcontainers.containers.PostgreSQLContainer;

import javax.sql.DataSource;
import java.sql.SQLException;

public class TestContainerDataSource extends DataSourceFactory {
    private final PostgreSQLContainer postgreSQLContainer;

    public TestContainerDataSource(PostgreSQLContainer postgreSQLContainer) {
        this.postgreSQLContainer = postgreSQLContainer;
    }

    @Override
    public DataSource getDataSource() throws SQLException {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setURL(postgreSQLContainer.getJdbcUrl());
        dataSource.setDatabaseName(postgreSQLContainer.getDatabaseName());
        dataSource.setUser(postgreSQLContainer.getUsername());
        dataSource.setPassword(postgreSQLContainer.getPassword());
        return dataSource;
    }
}

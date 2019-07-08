package com.justpz.tcontainers.training;

import com.justpz.tcontainers.training.db.DataSourceFactory;
import org.postgresql.ds.PGSimpleDataSource;
import org.testcontainers.containers.JdbcDatabaseContainer;

import javax.sql.DataSource;

public class TestContainerDataSourceFactory extends DataSourceFactory {
    private final JdbcDatabaseContainer jdbcDatabaseContainer;

    public TestContainerDataSourceFactory(JdbcDatabaseContainer jdbcDatabaseContainer) {
        this.jdbcDatabaseContainer = jdbcDatabaseContainer;
    }

    @Override
    public DataSource getDataSource() {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setURL(jdbcDatabaseContainer.getJdbcUrl());
        dataSource.setDatabaseName(jdbcDatabaseContainer.getDatabaseName());
        dataSource.setUser(jdbcDatabaseContainer.getUsername());
        dataSource.setPassword(jdbcDatabaseContainer.getPassword());
        return dataSource;
    }
}

package com.justpz.tcontainers.basic.db;

import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.sql.SQLException;

public class DataSourceFactory {
    public DataSource getDataSource() throws SQLException {
        return new PGSimpleDataSource();
    }


}

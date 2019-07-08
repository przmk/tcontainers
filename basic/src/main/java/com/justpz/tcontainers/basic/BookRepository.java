package com.justpz.tcontainers.basic;


import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookRepository {
    private DataSource dataSource;

    BookRepository(DataSourceFactory dataSourceFactory) throws SQLException {
        this.dataSource = dataSourceFactory.getDataSource();
    }

    List<String> getAll() {
        try (Connection conn = dataSource.getConnection()) {
            final PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM Book");
            final ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<String> list = new ArrayList<>();
            while (resultSet.next()) {
                list.add(resultSet.getString("title"));
            }
            return list;

        } catch (SQLException sqle) {
            throw new RuntimeException("Error reading message", sqle);
        }
    }

    int insert(String title) {
        try (Connection conn = dataSource.getConnection()) {
            final PreparedStatement preparedStatement = conn.prepareStatement("insert into book (title) values (?)");
            preparedStatement.setString(1, title);
            return preparedStatement.executeUpdate();

        } catch (SQLException sqle) {
            throw new RuntimeException("Error reading message", sqle);
        }
    }
}

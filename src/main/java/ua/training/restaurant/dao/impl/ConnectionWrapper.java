package ua.training.restaurant.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConnectionWrapper implements AutoCloseable {
    private Connection connection;

    public ConnectionWrapper(Connection connection) {
        this.connection = connection;
    }

    public void setAutoCommit(boolean autoCommit) throws SQLException {
        connection.setAutoCommit(autoCommit);
    }

    public boolean isTransaction() throws SQLException {
        return !connection.getAutoCommit();
    }

    public void commit() throws SQLException {
        connection.commit();
    }

    public void rollback() throws SQLException {
        connection.rollback();
    }

    public PreparedStatement prepareStatement(String sql) throws SQLException {
        return connection.prepareStatement(sql);
    }

    @Override
    public void close() throws SQLException {
        if (!isTransaction()) {
            connection.close();
        }
    }
}
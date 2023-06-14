package ua.training.restaurant.dao.impl;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;

public class TransactionManager {
    private static final Logger log = Logger.getLogger(TransactionManager.class);

    private static ThreadLocal<Connection> connectionThreadLocal = new ThreadLocal<>();

    public static Connection getConnection() throws SQLException {
        Connection connection = connectionThreadLocal.get();

        if (connection != null) {
            return connection;
        }
        return ConnectionPoolHolder.getDataSource().getConnection();
//        return DataSourceConnectionPool.getConnection();
    }

    public static void startTransaction() throws SQLException {
        Connection connection = connectionThreadLocal.get();

        if (connection != null) {
            throw new SQLException("Transaction already started");
        }

        connection = ConnectionPoolHolder.getDataSource().getConnection();
        connection.setAutoCommit(false);
        connectionThreadLocal.set(connection);
    }

    public static void commit() throws SQLException {
        Connection connection = connectionThreadLocal.get();

        if (connection == null) {
            throw new SQLException("Transaction not started to be commit.");
        }

        connection.commit();
    }

    public static void rollback() throws SQLException {
        Connection connection = connectionThreadLocal.get();

        if (connection == null) {
            throw new SQLException("Transaction not started to be rollback.");
        }

        connection.rollback();
    }

    public static void close() throws SQLException {
        Connection connection = connectionThreadLocal.get();

        if (connection == null) {
            throw new SQLException("Transaction not started to be rollback.");
        }

        connection.close();
    }
}
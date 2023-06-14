package ua.training.restaurant.dao.impl;

import org.apache.log4j.Logger;

import java.sql.SQLException;

public class TransactionManagerWrapper {
    private static final Logger LOG = Logger.getLogger(TransactionManager.class);

    private static ThreadLocal<ConnectionWrapper> th = new ThreadLocal<>();

    public static ConnectionWrapper getConnection() throws SQLException {
        ConnectionWrapper connectionWrapper = th.get();

        if (connectionWrapper != null) {
            return connectionWrapper;
        }

        connectionWrapper = new ConnectionWrapper(ConnectionPoolHolder.getDataSource().getConnection());
        return connectionWrapper;
    }

    public static void startTransaction() throws SQLException {
        ConnectionWrapper connectionWrapper = th.get();

        if (connectionWrapper != null) {
            throw new SQLException("Transaction already started");
        }

        connectionWrapper = new ConnectionWrapper(ConnectionPoolHolder.getDataSource().getConnection());
        connectionWrapper.setAutoCommit(false);
        th.set(connectionWrapper);
    }

    public static void commit() {
        ConnectionWrapper connectionWrapper = th.get();

        if (connectionWrapper == null) {
            throw new RuntimeException("Transaction not started to be commit.");
        }

        try {
            connectionWrapper.commit();
            connectionWrapper.setAutoCommit(true);
            connectionWrapper.close();
        } catch (SQLException e) {

        }
    }

    public static void rollback() {
        ConnectionWrapper connectionWrapper = th.get();

        if (connectionWrapper == null) {
            throw new RuntimeException("Transaction not started to be rollback.");
        }

        try {

            connectionWrapper.rollback();
            connectionWrapper.setAutoCommit(true);
            connectionWrapper.close();
        } catch (SQLException ex) {
            LOG.error("Something happens", ex);
        }
    }
}
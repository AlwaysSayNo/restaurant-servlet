package ua.training.restaurant.dao.impl;

import ua.training.restaurant.dao.DaoFactory;
import ua.training.restaurant.dao.DishDao;
import ua.training.restaurant.dao.OrderDao;
import ua.training.restaurant.dao.UserDao;
import ua.training.restaurant.exceptions.DatabaseConnectionException;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class JDBCDaoFactory extends DaoFactory {
    private DataSource dataSource = ConnectionPoolHolder.getDataSource();

    @Override
    public DishDao createDishDao() {
        return new JDBCDishDao(getConnection());
    }

    @Override
    public OrderDao createOrderDao() {
        return new JDBCOrderDao(getConnection());
    }

    @Override
    public UserDao createUserDao() {
        return new JDBCUserDao(getConnection());
    }

    private Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new DatabaseConnectionException(e.getMessage());
        }
    }
}

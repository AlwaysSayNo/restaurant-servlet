package ua.training.restaurant.dao.impl;

import org.apache.log4j.Logger;
import ua.training.restaurant.dao.DaoFactory;
import ua.training.restaurant.dao.OrderDao;
import ua.training.restaurant.dao.UserDao;
import ua.training.restaurant.dao.UserOrderDao;
import ua.training.restaurant.entity.order.Order;
import ua.training.restaurant.entity.user.User;
import ua.training.restaurant.exceptions.TransactionException;

import java.sql.SQLException;

public class JDBCUserOrderDao implements UserOrderDao {
    private final static Logger log = Logger.getLogger(JDBCUserDao.class);

    @Override
    public boolean saveUserAndOrder(User user, Order order) {
        try (UserDao userDao = DaoFactory.getInstance().createUserDao();
             OrderDao orderDao = DaoFactory.getInstance().createOrderDao()) {

            TransactionManagerWrapper.startTransaction();
            userDao.update(user);
            orderDao.update(order);
            TransactionManagerWrapper.commit();
            return true;
        } catch (SQLException e) {
            TransactionManagerWrapper.rollback();
            log.error(e);
            throw new TransactionException(e.getMessage());
        } catch (Exception e) {
            log.error(e);
        }
        return false;
    }
}

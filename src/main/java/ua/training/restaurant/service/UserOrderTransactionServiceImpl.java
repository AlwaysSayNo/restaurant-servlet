package ua.training.restaurant.service;

import ua.training.restaurant.dao.UserOrderDao;
import ua.training.restaurant.dao.impl.JDBCUserOrderDao;
import ua.training.restaurant.entity.order.Order;
import ua.training.restaurant.entity.user.User;

public class UserOrderTransactionServiceImpl implements UserOrderTransactionService {
    @Override
    public boolean saveUserAndOrder(User user, Order order) {
        UserOrderDao userOrderDao = new JDBCUserOrderDao();
        userOrderDao.saveUserAndOrder(user, order);
        return true;
    }
}

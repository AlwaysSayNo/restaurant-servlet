package ua.training.restaurant.dao;

import ua.training.restaurant.entity.order.Order;
import ua.training.restaurant.entity.user.User;

public interface UserOrderDao {
    boolean saveUserAndOrder(User user, Order order);
}

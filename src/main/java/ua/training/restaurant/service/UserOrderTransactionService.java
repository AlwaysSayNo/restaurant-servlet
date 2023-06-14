package ua.training.restaurant.service;

import ua.training.restaurant.entity.order.Order;
import ua.training.restaurant.entity.user.User;

public interface UserOrderTransactionService {
    boolean saveUserAndOrder(User user, Order order);
}

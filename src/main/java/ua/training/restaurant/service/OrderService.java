package ua.training.restaurant.service;

import ua.training.restaurant.entity.Dish;
import ua.training.restaurant.entity.order.Order;
import ua.training.restaurant.entity.order.Order_Status;
import ua.training.restaurant.entity.user.User;

import java.util.List;

/**
 * Created by Student
 */

public interface OrderService {
    List<Order> findByUser(User user) throws Exception;

    Order findById(Long id) throws Exception;

    Order save(Order order, User user) throws Exception;

    List<Order> findByStatus(Order_Status order_status) throws Exception;

    Order update(Order order) throws Exception;

    Order addDish(Order order, Dish dish, int quantity);

    Order updateDish(Order order, Long id, int quantity);

    Order removeDish(Order order, Dish dish);

    Order updateQuantity(Order order1, Order order2);

    List<Order> findByUserId(Long id) throws Exception;

    void confirmOrder(Long id) throws Exception;

    void setPaid(Order order);
}

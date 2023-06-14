package ua.training.restaurant.dao;

import ua.training.restaurant.entity.order.Order;
import ua.training.restaurant.entity.order.Order_Status;
import ua.training.restaurant.entity.user.User;

import java.util.List;

public interface OrderDao extends GenericDao<Order> {
    List<Order> findByUser(User user);

    List<Order> findByStatus(Order_Status order_status);

    List<Order> findByUserId(Long id);

    Order save(Order order);

    Order update(Order order);
}

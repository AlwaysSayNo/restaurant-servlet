package ua.training.restaurant.entity;

import ua.training.restaurant.entity.order.Order;
import ua.training.restaurant.entity.user.User;

import java.util.List;

public class Bill {
    private Long id;
    private User user;
    private List<OrderUnit> orderUnits;
    private Long totalCost;

    public Bill(Order order) {
        this.id = order.getId();
        this.user = order.getUser();
        this.orderUnits = order.getOrderUnits();
        this.totalCost = order.getAmountTotal();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<OrderUnit> getOrderUnits() {
        return orderUnits;
    }

    public void setOrderUnits(List<OrderUnit> orderUnits) {
        this.orderUnits = orderUnits;
    }

    public Long getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Long totalCost) {
        this.totalCost = totalCost;
    }
}

package ua.training.restaurant.entity.order;

import ua.training.restaurant.entity.OrderUnit;
import ua.training.restaurant.entity.user.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private Long id;
    private User user;
    private List<OrderUnit> orderUnits;
    private LocalDateTime created, accepted, ready, paid;
    private Order_Status status;

    public Order(Long id, User user, List<OrderUnit> orderUnits, LocalDateTime created, LocalDateTime accepted,
                 LocalDateTime ready, LocalDateTime paid, Order_Status status) {
        this.id = id;
        this.user = user;
        this.orderUnits = orderUnits;
        this.created = created;
        this.accepted = accepted;
        this.ready = ready;
        this.paid = paid;
        this.status = status;
    }

    public Order() {
        this.orderUnits = new ArrayList<>();
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

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getAccepted() {
        return accepted;
    }

    public void setAccepted(LocalDateTime accepted) {
        this.accepted = accepted;
    }

    public LocalDateTime getReady() {
        return ready;
    }

    public void setReady(LocalDateTime ready) {
        this.ready = ready;
    }

    public LocalDateTime getPaid() {
        return paid;
    }

    public void setPaid(LocalDateTime paid) {
        this.paid = paid;
    }

    public Order_Status getStatus() {
        return status;
    }

    public void setStatus(Order_Status status) {
        this.status = status;
    }

    public boolean isEmpty() {
        return this.getOrderUnits().isEmpty();
    }

    public int getQuantityTotal() {
        return orderUnits.stream().mapToInt(OrderUnit::getQuantity).sum();
    }

    public Long getAmountTotal() {
        return orderUnits.stream().mapToLong(OrderUnit::getAmount).sum();
    }
}
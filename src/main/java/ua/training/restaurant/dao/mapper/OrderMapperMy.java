package ua.training.restaurant.dao.mapper;

import ua.training.restaurant.entity.Dish;
import ua.training.restaurant.entity.OrderUnit;
import ua.training.restaurant.entity.order.Order;
import ua.training.restaurant.entity.order.Order_Status;
import ua.training.restaurant.entity.user.Role;
import ua.training.restaurant.entity.user.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class OrderMapperMy implements MyObjectMapper<Order> {
    @Override
    public Order extractFromResultSet(ResultSet rs) throws SQLException {
        Order order = new Order();

        order.setId(rs.getLong("order_id"));
        order.setAccepted(parseIfNotNull(rs.getTimestamp("accepted")));
        order.setCreated(parseIfNotNull(rs.getTimestamp("created")));
        order.setPaid(parseIfNotNull(rs.getTimestamp("paid")));
        order.setReady(parseIfNotNull(rs.getTimestamp("ready")));
        order.setStatus(Order_Status.values()[rs.getInt("status")]);

        order.setOrderUnits(extractOrderUnitsFromResultSet(rs));

        order.setUser(extractUserFromResultSet(rs));
        return order;
    }

    private List<OrderUnit> extractOrderUnitsFromResultSet(ResultSet rs) throws SQLException {
        Dish dish = new Dish();
        dish.setId(rs.getLong("dish_id"));
        dish.setNameEn(rs.getString("dish_name_en"));
        dish.setNameUa(rs.getString("dish_name_ua"));
        dish.setPortion(rs.getLong("portion"));
        dish.setPrice(rs.getLong("price"));

        OrderUnit orderUnit = new OrderUnit();
        orderUnit.setDish(dish);
        orderUnit.setId(rs.getLong("order_unit_id"));
        orderUnit.setQuantity(rs.getInt("quantity"));

        List<OrderUnit> orderUnits = new ArrayList<>();
        orderUnits.add(orderUnit);
        return orderUnits;
    }

    private User extractUserFromResultSet(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getLong("user_id"));
        user.setFunds(rs.getLong("funds"));
        user.setNameEN(rs.getString("user_name_en"));
        user.setNameUA(rs.getString("user_name_ua"));
        user.setOrdersNumber(rs.getInt("orders_number"));
        user.setOrdersTotalCost(rs.getLong("orders_total_cost"));
        user.setPassword(rs.getString("password"));
        var instance = Calendar.getInstance();
        instance.setTimeInMillis(rs.getDate("registration_date").getTime());
        user.setRegistrationDate(instance);
        user.setUsername(rs.getString("username"));

        ArrayList<Role> authorities = new ArrayList<>();
        authorities.add(Role.values()[rs.getInt("authorities")]);
        user.setAuthorities(authorities);
        return user;
    }

    private LocalDateTime parseIfNotNull(Timestamp timestamp) {
        LocalDateTime time = null;
        if (timestamp != null) {
            time = timestamp.toLocalDateTime();
        }
        return time;
    }

    public List<Order> combine(List<Order> orders) {
        if (orders.size() <= 1) {
            return orders;
        }
        for (int i = orders.size() - 1; i >= 1; i--) {
            if (orders.get(i).getId().equals(orders.get(i - 1).getId())) {
                orders.get(i - 1).getOrderUnits().addAll(orders.get(i).getOrderUnits());
                orders.remove(i);
            }
        }
        return orders;
    }
}
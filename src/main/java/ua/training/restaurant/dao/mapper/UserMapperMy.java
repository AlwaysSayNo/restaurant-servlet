package ua.training.restaurant.dao.mapper;

import ua.training.restaurant.entity.user.Role;
import ua.training.restaurant.entity.user.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

public class UserMapperMy implements MyObjectMapper<User> {

    @Override
    public User extractFromResultSet(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getLong("id"));
        user.setFunds(rs.getLong("funds"));
        user.setNameEN(rs.getString("name_en"));
        user.setNameUA(rs.getString("name_ua"));
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
}

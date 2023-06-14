package ua.training.restaurant.service;

import ua.training.restaurant.dao.DaoFactory;
import ua.training.restaurant.dao.UserDao;
import ua.training.restaurant.entity.order.Order;
import ua.training.restaurant.entity.user.Role;
import ua.training.restaurant.entity.user.User;
import ua.training.restaurant.exceptions.UserNotFoundException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Student
 */
public class UserServiceImpl implements UserService {
    private DaoFactory daoFactory = DaoFactory.getInstance();

    @Override
    public User save(User user) throws Exception {
        try (UserDao dao = daoFactory.createUserDao()) {
            return dao.save(user);
        }
    }

    @Override
    public User update(User user) throws Exception {
        try (UserDao dao = daoFactory.createUserDao()) {
            return dao.update(user);
        }
    }

    @Override
    public User findByUsernameAndPassword(String username, String password) throws Exception {
        try (UserDao dao = daoFactory.createUserDao()) {
            return dao.findByUsernameAndPassword(username, password).orElseThrow(UserNotFoundException::new);
        }
    }

    public List<User> findAllUsers() throws Exception { //means role.user
        try (UserDao dao = daoFactory.createUserDao()) {
            return dao.findByAuthoritiesContaining(Role.USER);
        }
    }

    @Override
    public User setDefaultParams(User user) {
        List<Role> authorities = new ArrayList<>();
        authorities.add(Role.USER);
        user.setAuthorities(authorities);
        user.setFunds(0L);
        user.setOrdersNumber(0);
        user.setOrdersTotalCost(0L);
        var instance = Calendar.getInstance();
        user.setRegistrationDate(instance);
        return user;
    }

    @Override
    public User addOrderToStatistic(User user, Order order) {
        user.setFunds(user.getFunds() - order.getAmountTotal());
        user.setOrdersTotalCost(user.getOrdersTotalCost() + order.getAmountTotal());
        user.setOrdersNumber(user.getOrdersNumber() + 1);
        return user;
    }

    @Override
    public User addFunds(User user, Long funds) throws Exception {
        user.setFunds(user.getFunds() + funds);
        update(user);
        return user;
    }
}

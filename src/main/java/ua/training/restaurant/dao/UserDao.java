package ua.training.restaurant.dao;

import ua.training.restaurant.entity.user.Role;
import ua.training.restaurant.entity.user.User;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface UserDao extends GenericDao<User> {
    List<User> findAll();

    Optional<User> findByUsernameAndPassword(String username, String password);

    List<User> findByAuthoritiesContaining(Role role);

    User save(User user) throws SQLException;

    User update(User user) throws SQLException;
}

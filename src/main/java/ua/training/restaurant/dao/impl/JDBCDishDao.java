package ua.training.restaurant.dao.impl;

import org.apache.log4j.Logger;
import ua.training.restaurant.dao.DishDao;
import ua.training.restaurant.dao.GenericDao;
import ua.training.restaurant.dao.mapper.DishMapperMy;
import ua.training.restaurant.entity.Dish;
import ua.training.restaurant.exceptions.DatabaseClosingException;
import ua.training.restaurant.exceptions.PropertyFileNotFoundIOExceprion;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

public class JDBCDishDao implements DishDao {
    private final static Logger log = Logger.getLogger(JDBCDishDao.class);
    private Connection connection;
    private Properties prop;

    public JDBCDishDao(Connection connection) {
        try {
            this.prop = new Properties();
            this.prop.load(JDBCDishDao.class.getClassLoader().getResourceAsStream(GenericDao.PROPERTY_FILE_PATH));
        } catch (IOException e) {
            log.error(e);
            throw new PropertyFileNotFoundIOExceprion(e.getMessage());
        }
        this.connection = connection;
    }

    @Override
    public Optional<Dish> findById(Long id) {
        Optional<Dish> dish;
        String query = MessageFormat.format(prop.getProperty("dishes.findById"), id);
        try (Statement st = connection.createStatement()) {
            ResultSet rs = st.executeQuery(query);
            rs.next();
            DishMapperMy dishMapper = new DishMapperMy();
            dish = Optional.of(dishMapper.extractFromResultSet(rs));
        } catch (SQLException e) {
            dish = Optional.empty();
        }
        return dish;
    }

    @Override
    public List<Dish> findAll(int firstIndex, int elementsNumber) {
        List<Dish> dishes = new ArrayList<>();
        String query = MessageFormat.format(prop.getProperty("dishes.findAll"), elementsNumber, firstIndex);
        try (Statement st = connection.createStatement()) {
            ResultSet rs = st.executeQuery(query);
            DishMapperMy dishMapper = new DishMapperMy();
            while (rs.next()) {
                Dish dish = dishMapper.extractFromResultSet(rs);
                dishes.add(dish);
            }
        } catch (SQLException e) {
            log.error(e);
        }
        return dishes;
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new DatabaseClosingException(e.getMessage());
        }
    }
}

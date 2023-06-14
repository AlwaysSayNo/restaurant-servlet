package ua.training.restaurant.dao.impl;

import org.apache.log4j.Logger;
import ua.training.restaurant.dao.GenericDao;
import ua.training.restaurant.dao.OrderDao;
import ua.training.restaurant.dao.mapper.OrderMapperMy;
import ua.training.restaurant.entity.OrderUnit;
import ua.training.restaurant.entity.order.Order;
import ua.training.restaurant.entity.order.Order_Status;
import ua.training.restaurant.entity.user.User;
import ua.training.restaurant.exceptions.DatabaseClosingException;
import ua.training.restaurant.exceptions.PropertyFileNotFoundIOExceprion;

import java.io.IOException;
import java.sql.*;
import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;


public class JDBCOrderDao implements OrderDao {
    private final static Logger log = Logger.getLogger(JDBCOrderDao.class);
    private static final String SEARCH_COLUMN_ORDER_ID = "order_id";
    private static final String SEARCH_COLUMN_STATUS = "status";
    private static final String SEARCH_COLUMN_USER_ID = "orders.user_id";
    private Connection connection;
    private Properties prop;

    public JDBCOrderDao(Connection connection) {
        try {
            this.prop = new Properties();
            this.prop.load(JDBCOrderDao.class.getClassLoader().getResourceAsStream(GenericDao.PROPERTY_FILE_PATH));
        } catch (IOException e) {
            log.error(e);
            throw new PropertyFileNotFoundIOExceprion(e.getMessage());
        }
        this.connection = connection;
    }

    @Override
    public Optional<Order> findById(Long id) {
        String query = MessageFormat.format(prop.getProperty("orders.genericQuery"), SEARCH_COLUMN_ORDER_ID, id.toString());
        return Optional.of(findOrderList(query).get(0));
    }

    @Override
    public List<Order> findByUser(User user) {
        return findByUserId(user.getId());
    }

    @Override
    public List<Order> findByStatus(Order_Status order_status) {
        String query = MessageFormat.format(prop.getProperty("orders.genericQuery"), SEARCH_COLUMN_STATUS, order_status.ordinal());
        return findOrderList(query);
    }

    @Override
    public List<Order> findByUserId(Long id) {
        String query = MessageFormat.format(prop.getProperty("orders.genericQuery"), SEARCH_COLUMN_USER_ID, id.toString());
        return findOrderList(query);
    }

    private List<Order> findOrderList(String query) {
        List<Order> orders = new ArrayList<>();
        try (Statement st = connection.createStatement()) {
            ResultSet rs = st.executeQuery(query);
            OrderMapperMy orderMapper = new OrderMapperMy();
            while (rs.next()) {
                Order order = orderMapper.extractFromResultSet(rs);
                orders.add(order);
            }
            orders = orderMapper.combine(orders);
        } catch (SQLException e) {
            log.error(e);
        }
        return orders;
    }

    @Override
    public Order save(Order order) {
        String query = MessageFormat.format(prop.getProperty("orders.save"), castDateOrNull(order.getAccepted()), castDateOrNull(order.getCreated()),
                castDateOrNull(order.getPaid()), castDateOrNull(order.getReady()), order.getStatus().ordinal(), order.getUser().getId().toString());
        saveOrUpdate(order, query);
        saveOrderUnits(order);
        return order;
    }

    private Order saveOrderUnits(Order order) {
        String query;
        for (OrderUnit orderUnit : order.getOrderUnits()) {
            query = MessageFormat.format(prop.getProperty("order_units.save"), orderUnit.getQuantity().toString(), orderUnit.getDish().getId().toString());
            try (Statement st = connection.createStatement()) {
                st.executeUpdate(query);
            } catch (SQLException e) {
                log.error(e);
            }
            query = MessageFormat.format(prop.getProperty("orders_order_units.save"), "");
            saveOrUpdate(order, query);
        }
        return order;
    }

    @Override
    public Order update(Order order) {
        String query = MessageFormat.format(prop.getProperty("orders.update"), castDateOrNull(order.getAccepted()), castDateOrNull(order.getPaid()),
                castDateOrNull(order.getReady()), order.getStatus().ordinal(), order.getId().toString());
        return saveOrUpdate(order, query);
    }

    private Order saveOrUpdate(Order order, String query) {
        try (Statement st = connection.createStatement()) {
            st.executeUpdate(query);
        } catch (SQLException e) {
            log.error(e);
        }
        return order;
    }

    private String castDateOrNull(LocalDateTime time) {
        if (time != null) {
            return "'" + Timestamp.valueOf(time).toString() + "'";
        }
        return "null";
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

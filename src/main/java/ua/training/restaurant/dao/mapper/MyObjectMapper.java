package ua.training.restaurant.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface MyObjectMapper<T> {
    T extractFromResultSet(ResultSet rs) throws SQLException;
}

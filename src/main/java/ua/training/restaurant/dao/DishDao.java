package ua.training.restaurant.dao;

import ua.training.restaurant.entity.Dish;

import java.util.List;

public interface DishDao extends GenericDao<Dish> {
    List<Dish> findAll(int firstIndex, int elementsNumber);
}

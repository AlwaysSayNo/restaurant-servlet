package ua.training.restaurant.service;

import ua.training.restaurant.dao.DaoFactory;
import ua.training.restaurant.dao.DishDao;
import ua.training.restaurant.entity.Dish;
import ua.training.restaurant.exceptions.DishNotFoundException;
import ua.training.restaurant.exceptions.EmptyDishesListException;
import ua.training.restaurant.exceptions.EmptyOrderException;

import java.util.List;

public class DishServiceImpl implements DishService {
    private DaoFactory daoFactory = DaoFactory.getInstance();

    @Override
    public List<Dish> findAll(int page, int recordsPerPage) throws Exception {
        int firstIndex = page * recordsPerPage;
        try (DishDao dao = daoFactory.createDishDao()) {
            List<Dish> dishes = dao.findAll(firstIndex, recordsPerPage);
            if (dishes.size() == 0) {
                throw new EmptyDishesListException();
            }
            return dishes;
        }
    }

    @Override
    public Dish findByID(Long id) throws Exception {
        try (DishDao dao = daoFactory.createDishDao()) {
            return dao.findById(id).orElseThrow(DishNotFoundException::new);
        }
    }
}

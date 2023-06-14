package ua.training.restaurant.servlet.command;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import ua.training.restaurant.entity.Dish;
import ua.training.restaurant.entity.Simple;
import ua.training.restaurant.entity.order.Order;
import ua.training.restaurant.exceptions.DishNotFoundException;
import ua.training.restaurant.service.DishService;
import ua.training.restaurant.service.DishServiceImpl;
import ua.training.restaurant.service.OrderService;
import ua.training.restaurant.service.OrderServiceImpl;
import ua.training.restaurant.utils.Utils;

import javax.servlet.http.HttpServletRequest;

public class ShoppingCartRemoveDish implements Command {
    private final static Logger log = Logger.getLogger(ShoppingCartRemoveDish.class);
    private final ObjectMapper objectMapper = new ObjectMapper();
    private OrderService orderService;
    private DishService dishService;

    public ShoppingCartRemoveDish() {
        orderService = new OrderServiceImpl();
        dishService = new DishServiceImpl();
    }

    //TODO session
    @Override
    public String execute(HttpServletRequest request) {
        Long id = Long.parseLong(request.getParameter("id"));

        try {
            log.info("trying to remove dish with id " + id);

            Dish dish = dishService.findByID(id);
            Order order = Utils.getOrderInSession(request);
            orderService.removeDish(order, dish);

            return objectMapper.writeValueAsString(new Simple<>("Ok"));
        } catch (DishNotFoundException e) {
            log.error("Cannot find dish by id " + id);
            throw new RuntimeException("Cannot find dish by id " + id);
        } catch (Exception e) {
            log.error(e);
            throw new RuntimeException(e.getMessage());
        }
    }
}

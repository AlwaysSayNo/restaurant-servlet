package ua.training.restaurant.servlet.command;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import ua.training.restaurant.entity.Dish;
import ua.training.restaurant.entity.order.Order;
import ua.training.restaurant.exceptions.DishNotFoundException;
import ua.training.restaurant.service.DishService;
import ua.training.restaurant.service.DishServiceImpl;
import ua.training.restaurant.service.OrderService;
import ua.training.restaurant.service.OrderServiceImpl;
import ua.training.restaurant.utils.Utils;

import javax.servlet.http.HttpServletRequest;

public class BuyProduct implements Command {
    private final static Logger log = Logger.getLogger(BuyProduct.class);
    private final ObjectMapper objectMapper = new ObjectMapper();
    private DishService dishService;
    private OrderService orderService;

    public BuyProduct() {
        dishService = new DishServiceImpl();
        orderService = new OrderServiceImpl();
    }

    //TODO session
    @Override
    public String execute(HttpServletRequest request) {
        Long id = Long.parseLong(request.getParameter("id"));
        log.info("trying to add dish with id " + id + " to shopping cart");
        try {
            Dish dish = dishService.findByID(id);
            Order order = Utils.getOrderInSession(request);
            return objectMapper.writeValueAsString(orderService.addDish(order, dish, 1));
        } catch (DishNotFoundException e) {
            log.error("cannot find dish by id " + id);
            throw new IllegalArgumentException("cannot find dish by id " + id);
        } catch (Exception e) {
            log.error(e);
            throw new IllegalArgumentException();
        }
    }
}

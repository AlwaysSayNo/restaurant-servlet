package ua.training.restaurant.servlet.command;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import ua.training.restaurant.entity.Simple;
import ua.training.restaurant.entity.order.Order;
import ua.training.restaurant.entity.user.User;
import ua.training.restaurant.exceptions.EmptyOrderException;
import ua.training.restaurant.service.OrderService;
import ua.training.restaurant.service.OrderServiceImpl;
import ua.training.restaurant.utils.Utils;

import javax.servlet.http.HttpServletRequest;

public class SaveOrder implements Command {
    private final static Logger log = Logger.getLogger(SaveOrder.class);
    private final ObjectMapper objectMapper = new ObjectMapper();
    private OrderService orderService;

    public SaveOrder() {
        orderService = new OrderServiceImpl();
    }

    //TODO session
    @Override
    public String execute(HttpServletRequest request) {
        try {
            User user = (User) request.getSession().getAttribute("loginedUser");
            log.info("trying to insert created order into db");
            Order order = Utils.getOrderInSession(request);

            order = orderService.save(order, user);
            Utils.removeOrderInSession(request);

            return objectMapper.writeValueAsString(order);
        } catch (EmptyOrderException e) {
            log.error("order is empty");
            throw new RuntimeException("Order is empty");
        } catch (Exception e) {
            log.error(e);
            throw new RuntimeException(e);
        }
    }
}

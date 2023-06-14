package ua.training.restaurant.servlet.command;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import ua.training.restaurant.entity.Simple;
import ua.training.restaurant.entity.order.Order_Status;
import ua.training.restaurant.service.OrderService;
import ua.training.restaurant.service.OrderServiceImpl;

import javax.servlet.http.HttpServletRequest;

public class OrderConfirmation implements Command {
    private final static Logger log = Logger.getLogger(OrderConfirmation.class);
    private final ObjectMapper objectMapper = new ObjectMapper();
    private OrderService orderService;

    public OrderConfirmation() {
        orderService = new OrderServiceImpl();
    }

    @Override
    public String execute(HttpServletRequest request) {
        log.info("getting orderconfirmation page");

        try {
            return objectMapper.writeValueAsString(orderService.findByStatus(Order_Status.CREATED));
        } catch (Exception e) {
            log.error(e);
            throw new RuntimeException(e);
        }
    }
}

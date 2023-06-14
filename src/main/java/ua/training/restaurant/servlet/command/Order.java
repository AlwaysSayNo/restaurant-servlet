package ua.training.restaurant.servlet.command;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import ua.training.restaurant.service.OrderService;
import ua.training.restaurant.service.OrderServiceImpl;

import javax.servlet.http.HttpServletRequest;

public class Order implements Command {
    private final static Logger log = Logger.getLogger(Order.class);
    private final ObjectMapper objectMapper = new ObjectMapper();
    private OrderService orderService;

    public Order() {
        orderService = new OrderServiceImpl();
    }

    @Override
    public String execute(HttpServletRequest request) {
        Long id = Long.parseLong(request.getParameter("id"));
        log.info("getting order info page");
        try {
            return objectMapper.writeValueAsString(orderService.findById(id));
        } catch (Exception e) {
            log.error("cannot find order by id " + id);
            throw new RuntimeException("Cannot find order by id " + id);
        }
    }
}

package ua.training.restaurant.servlet.command;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import ua.training.restaurant.entity.order.Order_Status;
import ua.training.restaurant.service.OrderService;
import ua.training.restaurant.service.OrderServiceImpl;

import javax.servlet.http.HttpServletRequest;

public class BillMaking implements Command {
    private final static Logger log = Logger.getLogger(BillMaking.class);
    private final ObjectMapper objectMapper = new ObjectMapper();
    private OrderService orderService;

    public BillMaking() {
        orderService = new OrderServiceImpl();
    }

    @Override
    public String execute(HttpServletRequest request) {
        log.info("getting bill making page");
        try {
            return objectMapper.writeValueAsString(orderService.findByStatus(Order_Status.READY));
        } catch (Exception e) {
            log.error(e);
            throw new RuntimeException(e);
        }
    }
}

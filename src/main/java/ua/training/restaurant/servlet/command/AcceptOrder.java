package ua.training.restaurant.servlet.command;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import ua.training.restaurant.entity.Simple;
import ua.training.restaurant.exceptions.OrderNotFoundException;
import ua.training.restaurant.service.OrderService;
import ua.training.restaurant.service.OrderServiceImpl;

import javax.servlet.http.HttpServletRequest;

public class AcceptOrder implements Command {
    private final static Logger log = Logger.getLogger(AcceptOrder.class);
    private final ObjectMapper objectMapper = new ObjectMapper();
    private OrderService orderService;

    public AcceptOrder() {
        this.orderService = new OrderServiceImpl();
    }

    @Override
    public String execute(HttpServletRequest request) {
        Long id = Long.parseLong(request.getParameter("id"));
        log.info("trying to confirm order");
        try {
            orderService.confirmOrder(id);
            return objectMapper.writeValueAsString(new Simple<>("Ok"));
        } catch (OrderNotFoundException e) {
            log.error("cannot find order by id " + id);
            throw new RuntimeException("Cannot find order by id " + id);
        } catch (Exception e) {
            log.error(e);
            throw new RuntimeException(e);
        }
    }
}

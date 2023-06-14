package ua.training.restaurant.servlet.command;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import ua.training.restaurant.entity.order.Order;
import ua.training.restaurant.entity.order.Order_Status;
import ua.training.restaurant.exceptions.OrderNotFoundException;
import ua.training.restaurant.service.OrderService;
import ua.training.restaurant.service.OrderServiceImpl;

import javax.servlet.http.HttpServletRequest;

public class MakeBill implements Command {
    private final static Logger log = Logger.getLogger(MakeBill.class);
    private final ObjectMapper objectMapper = new ObjectMapper();
    private OrderService orderService;

    public MakeBill() {
        orderService = new OrderServiceImpl();
    }

    @Override
    public String execute(HttpServletRequest request) {
        Long id = Long.parseLong(request.getParameter("id"));
        log.info("trying to make bill");
        Order order;
        try {
            order = orderService.findById(id);
            order.setStatus(Order_Status.UNPAID);
            return objectMapper.writeValueAsString(orderService.update(order));
        } catch (OrderNotFoundException e) {
            log.error("cannot find order by id " + id);
            throw new RuntimeException("cannot find order by id " + id);
        } catch (Exception e) {
            log.error(e);
            throw new RuntimeException(e);
        }
    }
}

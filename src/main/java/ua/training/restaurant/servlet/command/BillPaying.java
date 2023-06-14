package ua.training.restaurant.servlet.command;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import ua.training.restaurant.entity.Bill;
import ua.training.restaurant.entity.order.Order;
import ua.training.restaurant.entity.order.Order_Status;
import ua.training.restaurant.entity.user.User;
import ua.training.restaurant.service.OrderService;
import ua.training.restaurant.service.OrderServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class BillPaying implements Command {
    private final static Logger log = Logger.getLogger(BillPaying.class);
    private final ObjectMapper objectMapper = new ObjectMapper();
    private OrderService orderService;

    public BillPaying() {
        orderService = new OrderServiceImpl();
    }

    @Override
    public String execute(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("loginedUser");
        log.info("getting billpaying page");
        try {
            List<Order> orders = orderService.findByUser(user);
            List<Bill> bills = new ArrayList<>();
            orders.stream().filter(x -> x.getStatus().equals(Order_Status.UNPAID)).forEach(x -> bills.add(new Bill(x)));
            return objectMapper.writeValueAsString(bills);
        } catch (Exception e) {
            log.error(e);
        }
        return "WEB-INF/view/billpaying.jsp";
    }
}

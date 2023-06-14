package ua.training.restaurant.servlet.command;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import ua.training.restaurant.entity.order.Order;
import ua.training.restaurant.entity.user.User;
import ua.training.restaurant.exceptions.OrderNotFoundException;
import ua.training.restaurant.service.*;

import javax.servlet.http.HttpServletRequest;

public class PayBill implements Command {
    private final static Logger log = Logger.getLogger(PayBill.class);
    private final ObjectMapper objectMapper = new ObjectMapper();
    private OrderService orderService;
    private UserService userService;
    private UserOrderTransactionService service;

    public PayBill() {
        orderService = new OrderServiceImpl();
        userService = new UserServiceImpl();
        service = new UserOrderTransactionServiceImpl();
    }

    //TODO session
    @Override
    public String execute(HttpServletRequest request) {
        Long id = Long.parseLong(request.getParameter("id"));

        try {
            User user = (User) request.getSession().getAttribute("loginedUser");
            log.info("trying to pay bill");
            Order order;

            order = orderService.findById(id);
            userService.addOrderToStatistic(user, order);
            orderService.setPaid(order);
            service.saveUserAndOrder(user, order);
        } catch (OrderNotFoundException e) {
            log.error("cannot find order by id " + id);
            request.setAttribute("failureMessage", "failureMessage");
        } catch (Exception e) {
            log.error("user dont have enough funds");
            request.setAttribute("notEnoughFundsMessage", "notEnoughFundsMessage");
        }
        return "redirect:/billpaying";
    }
}

package ua.training.restaurant.servlet.command;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import ua.training.restaurant.entity.user.Role;
import ua.training.restaurant.entity.user.User;
import ua.training.restaurant.service.OrderService;
import ua.training.restaurant.service.OrderServiceImpl;

import javax.servlet.http.HttpServletRequest;

public class ConcreteUserStatistic implements Command {
    private final static Logger log = Logger.getLogger(ConcreteUserStatistic.class);
    private final ObjectMapper objectMapper = new ObjectMapper();
    private OrderService orderService;

    public ConcreteUserStatistic() {
        orderService = new OrderServiceImpl();
    }

    //TODO session
    @Override
    public String execute(HttpServletRequest request) {
        log.info("getting concrete user statistic page");
        User user = (User) request.getSession().getAttribute("loginedUser");
        if (user.getAuthorities().get(0).equals(Role.USER)) {
            try {
                return objectMapper.writeValueAsString(orderService.findByUser(user));
            } catch (Exception e) {
                log.error(e);
                throw new RuntimeException(e);
            }
        } else {
            Long id = Long.parseLong(request.getParameter("id"));
            try {
                return objectMapper.writeValueAsString(orderService.findByUserId(id));
            } catch (Exception e) {
                log.error(e);
                throw new RuntimeException(e);
            }
        }
    }
}

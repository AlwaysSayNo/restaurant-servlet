package ua.training.restaurant.servlet.command;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import ua.training.restaurant.entity.order.Order;
import ua.training.restaurant.utils.Utils;

import javax.servlet.http.HttpServletRequest;

public class ShoppingCart implements Command {
    private final static Logger log = Logger.getLogger(ShoppingCart.class);
    private final ObjectMapper objectMapper = new ObjectMapper();

    public String execute(HttpServletRequest request) {
        String res = null;
        if (request.getMethod().equals("GET"))
            res = doGet(request);
        else if (request.getMethod().equals("POST"))
            res = doPost(request);
        return res;
    }

    //TODO session
    public String doGet(HttpServletRequest request) {
        try {
            log.info("getting shoppingcart page");
            Order order = Utils.getOrderInSession(request);
            return objectMapper.writeValueAsString(order);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public String doPost(HttpServletRequest request) {
        throw new RuntimeException("No such request");
    }
}

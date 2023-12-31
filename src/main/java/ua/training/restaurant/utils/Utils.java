package ua.training.restaurant.utils;

import ua.training.restaurant.entity.order.Order;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class Utils {
    //Products in the order, stored in Session.
    public static Order getOrderInSession(HttpServletRequest request) {

        Order order = (Order) request.getSession().getAttribute("myOrder");

        if (!Optional.ofNullable(order).isPresent()) {
            order = new Order();
            request.getSession().setAttribute("myOrder", order);
        }
        return order;
    }

    public static void removeOrderInSession(HttpServletRequest request) {
        request.getSession().removeAttribute("myOrder");
    }

    public static void storeLastOrderedOrderInSession(HttpServletRequest request, Order order) {
        request.getSession().setAttribute("lastOrderedOrder", order);
    }

    public static Order getLastOrderedOrderInSession(HttpServletRequest request) {
        return (Order) request.getSession().getAttribute("lastOrderedOrder");
    }
}

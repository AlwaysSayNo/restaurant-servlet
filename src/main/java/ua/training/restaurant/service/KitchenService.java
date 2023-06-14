package ua.training.restaurant.service;

import org.apache.log4j.Logger;
import ua.training.restaurant.entity.order.Order;
import ua.training.restaurant.entity.order.Order_Status;

import static java.time.LocalDateTime.now;

public class KitchenService {
    private final static Logger log = Logger.getLogger(KitchenService.class);

    public static Order cookDishes(Order order) {
        log.info("Order is cooking");
        order.setStatus(Order_Status.READY);
        order.setReady(now());
        return order;
    }
}

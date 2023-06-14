package ua.training.restaurant.servlet.command;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import ua.training.restaurant.service.DishService;
import ua.training.restaurant.service.DishServiceImpl;

import javax.servlet.http.HttpServletRequest;

public class FoodMenu implements Command {
    private static final int RECORDS_PER_PAGE = 100;
    private final static Logger log = Logger.getLogger(FoodMenu.class);
    private final ObjectMapper objectMapper = new ObjectMapper();
    private DishService dishService;

    public FoodMenu() {
        dishService = new DishServiceImpl();
    }

    @Override
    public String execute(HttpServletRequest request) {
        var pageParam = request.getParameter("page");
        int page = pageParam != null ? Integer.parseInt(pageParam) - 1 : 0;
        log.info("getting food menu page");
        try {
            return objectMapper.writeValueAsString(dishService.findAll(page, RECORDS_PER_PAGE));
        } catch (Exception e) {
            log.error(e);
            throw new RuntimeException(e);
        }
    }
}

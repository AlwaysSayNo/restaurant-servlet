package ua.training.restaurant.servlet.command;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import ua.training.restaurant.service.UserService;
import ua.training.restaurant.service.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;

// Перелік усіх користувачів
public class UserStatistics implements Command {
    private final static Logger log = Logger.getLogger(UserStatistics.class);
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final UserService userService;

    public UserStatistics() {
        userService = new UserServiceImpl();
    }

    @Override
    public String execute(HttpServletRequest request) {
        log.info("getting userstatistics page");
        try {
            return objectMapper.writeValueAsString(userService.findAllUsers());
        } catch (Exception e) {
            log.error(e);
            throw new RuntimeException(e.getMessage());
        }
    }
}

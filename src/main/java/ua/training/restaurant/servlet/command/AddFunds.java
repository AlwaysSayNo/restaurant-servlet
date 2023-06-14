package ua.training.restaurant.servlet.command;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import ua.training.restaurant.entity.user.User;
import ua.training.restaurant.service.UserService;
import ua.training.restaurant.service.UserServiceImpl;
import ua.training.restaurant.utils.Validator;

import javax.servlet.http.HttpServletRequest;

public class AddFunds implements Command {
    private final static Logger log = Logger.getLogger(AddFunds.class);
    private final ObjectMapper objectMapper = new ObjectMapper();
    private UserService userService;

    public AddFunds() {
        userService = new UserServiceImpl();
    }

    public String execute(HttpServletRequest request) {
        String res = null;
        if (request.getMethod().equals("GET"))
            res = doGet(request);
        else if (request.getMethod().equals("POST"))
            res = doPost(request);
        return res;
    }

    public String doGet(HttpServletRequest request) {
        log.info("getting addfunds page");
        throw new RuntimeException("No such request");
    }

    //TODO session
    public String doPost(HttpServletRequest request) {
        log.info("trying to add funds to user account");
        try {
            Long funds = Long.parseLong(request.getParameter("funds"));
            User user = (User) request.getSession().getAttribute("loginedUser");
            Validator.throwExIfFundsNotValid(funds);
            return objectMapper.writeValueAsString(userService.addFunds(user, funds));
        } catch (Exception e) {
            log.error("invalid funds");
            throw new RuntimeException("invalid funds");
        }
    }
}

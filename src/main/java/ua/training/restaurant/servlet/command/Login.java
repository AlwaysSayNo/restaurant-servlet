package ua.training.restaurant.servlet.command;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import ua.training.restaurant.entity.Simple;
import ua.training.restaurant.entity.user.User;
import ua.training.restaurant.service.UserService;
import ua.training.restaurant.service.UserServiceImpl;
import ua.training.restaurant.utils.AppUtils;

import javax.servlet.http.HttpServletRequest;

public class Login implements Command {
    private final static Logger log = Logger.getLogger(Login.class);
    private final ObjectMapper objectMapper = new ObjectMapper();
    private UserService userService;

    public Login() {
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

    //TODO session
    private String doGet(HttpServletRequest request) {
        log.info("getting login page");
        try {
            request.getSession().invalidate();
            return objectMapper.writeValueAsString(new Simple<>("Ok"));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    //TODO session
    private String doPost(HttpServletRequest request) {
        String url;
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user;
        try {
            user = userService.findByUsernameAndPassword(username, password);

            AppUtils.storeLoginedUser(request.getSession(), user);
            return objectMapper.writeValueAsString(user);
        } catch (Exception e) {
            log.error(e);
            String errorMessage = "errorMessage";
            try {
                return objectMapper.writeValueAsString(new Simple<>(errorMessage));
            } catch (JsonProcessingException ex) {
                throw new RuntimeException(e);
            }
        }
    }
}


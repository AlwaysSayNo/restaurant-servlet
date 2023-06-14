package ua.training.restaurant.servlet.command;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import ua.training.restaurant.bcrypt.BCrypt;
import ua.training.restaurant.entity.Simple;
import ua.training.restaurant.entity.user.User;
import ua.training.restaurant.exceptions.UserDataNotValidException;
import ua.training.restaurant.service.UserService;
import ua.training.restaurant.service.UserServiceImpl;
import ua.training.restaurant.utils.Validator;

import javax.servlet.http.HttpServletRequest;

public class SignUp implements Command {
    private final static Logger log = Logger.getLogger(SignUp.class);
    private final ObjectMapper objectMapper = new ObjectMapper();
    private UserService userService;

    public SignUp() {
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

    private String doGet(HttpServletRequest request) {
        throw new RuntimeException("No such request");
    }

    //TODO session
    private String doPost(HttpServletRequest request) {
        try {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String nameEn = request.getParameter("nameEn");
            String nameUa = request.getParameter("nameUa");
            Validator.throwExIfUserNotValid(username, password, nameEn, nameUa);
            User user = new User();
            user.setUsername(username);
            user.setPassword(BCrypt.hashpw(password,BCrypt.gensalt()));
            user.setNameEN(nameEn);
            user.setNameUA(nameUa);
            userService.setDefaultParams(user);
            userService.save(user);
            return objectMapper.writeValueAsString(new Simple<>("Ok"));
        } catch (UserDataNotValidException e) {
            log.error("Invalid data");
            throw new RuntimeException("Invalid data");
        } catch (Exception e) {
            log.error("username already exists");
            throw new RuntimeException("Username already exists");
        }
    }
}

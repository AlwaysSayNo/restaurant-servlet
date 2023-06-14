package ua.training.restaurant.servlet.command;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import ua.training.restaurant.entity.Simple;

import javax.servlet.http.HttpServletRequest;

public class LogOut implements Command {
    private final static Logger log = Logger.getLogger(LogOut.class);
    private final ObjectMapper objectMapper = new ObjectMapper();

    //TODO session
    public String execute(HttpServletRequest request) {
        log.info("user logged out");
        try {
            request.getSession().invalidate();
            return objectMapper.writeValueAsString(new Simple<>("Ok"));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}

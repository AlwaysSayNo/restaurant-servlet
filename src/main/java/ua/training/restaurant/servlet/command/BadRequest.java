package ua.training.restaurant.servlet.command;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import ua.training.restaurant.entity.Simple;

import javax.servlet.http.HttpServletRequest;

public class BadRequest implements Command {

    private final static Logger log = Logger.getLogger(BadRequest.class);
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String execute(HttpServletRequest request) {
        try {
            return objectMapper.writeValueAsString(new Simple<>("Bad request"));
        } catch (Exception e) {
            log.error(e);
            throw new RuntimeException(e);
        }
    }

}

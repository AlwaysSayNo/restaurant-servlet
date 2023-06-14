package ua.training.restaurant.servlet.command;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

// Сторінка із меню
public class UserPage implements Command {
    private final static Logger log = Logger.getLogger(UserPage.class);

    @Override
    public String execute(HttpServletRequest request) {
        log.info("getting user's page");
        throw new RuntimeException("No such request");
    }
}

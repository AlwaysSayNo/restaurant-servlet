package ua.training.restaurant.servlet.command;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class AdminPage implements Command {
    private final static Logger log = Logger.getLogger(AdminPage.class);

    @Override
    public String execute(HttpServletRequest request) {
        log.info("getting admin's page");
        throw new RuntimeException("No such request");
    }
}

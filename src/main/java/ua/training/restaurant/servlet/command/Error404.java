package ua.training.restaurant.servlet.command;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class Error404 implements Command {
    private final static Logger log = Logger.getLogger(Error404.class);

    @Override
    public String execute(HttpServletRequest request) {
        log.info("getting error page");
        throw new RuntimeException("No such request");
    }
}

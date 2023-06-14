package ua.training.restaurant.exceptions;

public class DatabaseClosingException extends RuntimeException {
    public DatabaseClosingException() {
    }

    public DatabaseClosingException(String message) {
        super(message);
    }
}

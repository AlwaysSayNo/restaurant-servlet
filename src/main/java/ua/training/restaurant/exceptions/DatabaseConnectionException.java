package ua.training.restaurant.exceptions;

public class DatabaseConnectionException extends RuntimeException {
    public DatabaseConnectionException() {
    }

    public DatabaseConnectionException(String message) {
        super(message);
    }
}

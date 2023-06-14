package ua.training.restaurant.exceptions;

public class UserDataNotValidException extends Exception {
    public UserDataNotValidException() {
    }

    public UserDataNotValidException(String message) {
        super(message);
    }
}

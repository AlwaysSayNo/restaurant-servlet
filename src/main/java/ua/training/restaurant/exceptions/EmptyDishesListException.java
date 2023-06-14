package ua.training.restaurant.exceptions;

public class EmptyDishesListException extends RuntimeException {
    public EmptyDishesListException() {
    }

    public EmptyDishesListException(String message) {
        super(message);
    }
}

package ua.training.restaurant.exceptions;

public class PropertyFileNotFoundIOExceprion extends RuntimeException {
    public PropertyFileNotFoundIOExceprion() {
    }

    public PropertyFileNotFoundIOExceprion(String message) {
        super(message);
    }
}

package ua.training.restaurant.entity;

public class Simple<T> {

    private T value;

    public Simple() {
    }

    public Simple(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}

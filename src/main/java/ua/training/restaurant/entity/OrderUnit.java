package ua.training.restaurant.entity;

public class OrderUnit {
    private Long id;
    private Dish dish;
    private Integer quantity;

    public OrderUnit() {
        this.quantity = 0;
    }

    public OrderUnit(Long id, Dish dish, Integer quantity) {
        this.id = id;
        this.dish = dish;
        this.quantity = quantity;
    }

    public Long getAmount() {
        return this.dish.getPrice() * this.quantity;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}

package ua.training.restaurant.entity;

public class Dish {
    private Long id;
    private String nameEn;
    private String nameUa;
    private Long portion; //in grams
    private Long price;

    public Dish() {

    }

    public Dish(Long id, String nameEn, String nameUa, Long portion, Long price) {
        this.id = id;
        this.nameEn = nameEn;
        this.nameUa = nameUa;
        this.portion = portion;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public String getNameUa() {
        return nameUa;
    }

    public void setNameUa(String nameUa) {
        this.nameUa = nameUa;
    }

    public Long getPortion() {
        return portion;
    }

    public void setPortion(Long portion) {
        this.portion = portion;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }
}

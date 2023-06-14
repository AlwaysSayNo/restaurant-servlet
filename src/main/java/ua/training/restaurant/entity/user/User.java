package ua.training.restaurant.entity.user;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class User {
    private Long id;
    private String nameEN;
    private String nameUA;
    private String username;
    private String password;
    private Long funds; //in US pennies
    private int ordersNumber;
    private Long ordersTotalCost;
    private Calendar registrationDate;
    private List<Role> authorities;

    public User(Long id, String nameEN, String nameUA, String username, String password, Long funds, int ordersNumber,
                Long ordersTotalCost, Calendar registrationDate, Role... authorities) {
        this.id = id;
        this.nameEN = nameEN;
        this.nameUA = nameUA;
        this.username = username;
        this.password = password;
        this.funds = funds;
        this.ordersNumber = ordersNumber;
        this.ordersTotalCost = ordersTotalCost;
        this.registrationDate = registrationDate;
        this.authorities = new ArrayList<>();
        if (authorities != null) {
            this.authorities.addAll(Arrays.asList(authorities));
        }
    }

    public User() {
    }

    public List<Role> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<Role> authorities) {
        this.authorities = authorities;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameEN() {
        return nameEN;
    }

    public void setNameEN(String nameEN) {
        this.nameEN = nameEN;
    }

    public String getNameUA() {
        return nameUA;
    }

    public void setNameUA(String nameUA) {
        this.nameUA = nameUA;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getFunds() {
        return funds;
    }

    public void setFunds(Long funds) {
        this.funds = funds;
    }

    public int getOrdersNumber() {
        return ordersNumber;
    }

    public void setOrdersNumber(int ordersNumber) {
        this.ordersNumber = ordersNumber;
    }

    public Long getOrdersTotalCost() {
        return ordersTotalCost;
    }

    public void setOrdersTotalCost(Long ordersTotalCost) {
        this.ordersTotalCost = ordersTotalCost;
    }

    public Calendar getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Calendar registrationDate) {
        this.registrationDate = registrationDate;
    }
}


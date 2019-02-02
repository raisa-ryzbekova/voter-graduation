package ru.raisaryzbekova.voter.to;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class DishTo extends BaseTo {

    @NotBlank
    @Size(min = 2, max = 100)
    @Column(name = "name", nullable = false)
    protected String name;

    @Column(name = "price", nullable = false)
    private int price;

    @NotNull
    private Integer restaurantId;

    public DishTo() {
    }

    public DishTo(String name, int price, Integer restaurantId) {
        this.name = name;
        this.price = price;
        this.restaurantId = restaurantId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Integer getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Integer restaurantId) {
        this.restaurantId = restaurantId;
    }

    @Override
    public String toString() {
        return "DishTo{" +
                "name='" + name +
                ", price=" + price +
                ", restaurantId=" + restaurantId +
                '}';
    }
}
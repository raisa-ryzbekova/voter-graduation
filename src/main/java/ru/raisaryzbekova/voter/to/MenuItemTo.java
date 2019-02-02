package ru.raisaryzbekova.voter.to;

import javax.validation.constraints.NotNull;

public class MenuItemTo extends BaseTo {

    @NotNull
    private Integer dishId;

    public MenuItemTo() {
    }

    public MenuItemTo(Integer restaurantId, Integer dishId) {
        this.dishId = dishId;
    }

    public Integer getDishId() {
        return dishId;
    }

    public void setDishId(Integer dishId) {
        this.dishId = dishId;
    }

    @Override
    public String toString() {
        return "MenuItemTo{" +
                "dishId=" + dishId +
                '}';
    }
}
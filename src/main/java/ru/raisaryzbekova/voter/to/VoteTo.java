package ru.raisaryzbekova.voter.to;

import javax.validation.constraints.NotNull;

public class VoteTo extends BaseTo {

    @NotNull
    private Integer restaurantId;

    public VoteTo() {
    }

    public VoteTo(Integer id, @NotNull Integer restaurantId) {
        super(id);
        this.restaurantId = restaurantId;
    }

    public Integer getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Integer restaurantId) {
        this.restaurantId = restaurantId;
    }

    @Override
    public String toString() {
        return "VoteTo{" +
                "id=" + id +
                ", restaurantId=" + restaurantId +
                '}';
    }
}
package ru.raisaryzbekova.voter.to;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class VoteTo extends BaseTo {

    @Column(name = "date", nullable = false)
    @NotNull
    private LocalDate date;

    @NotNull
    private Integer restaurantId;

    public VoteTo() {
    }

    public VoteTo(Integer restaurantId) {
        this.restaurantId = restaurantId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
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
                "date=" + date +
                "restaurantId=" + restaurantId +
                '}';
    }
}
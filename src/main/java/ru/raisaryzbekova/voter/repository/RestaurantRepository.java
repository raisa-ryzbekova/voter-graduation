package ru.raisaryzbekova.voter.repository;

import ru.raisaryzbekova.voter.model.Restaurant;

import java.util.List;

public interface RestaurantRepository {

    Restaurant save(Restaurant restaurant);

    Restaurant get(int id);

    List<Restaurant> getAll();
}
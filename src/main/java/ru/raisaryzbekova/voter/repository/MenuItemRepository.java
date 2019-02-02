package ru.raisaryzbekova.voter.repository;

import ru.raisaryzbekova.voter.model.Dish;
import ru.raisaryzbekova.voter.model.MenuItem;
import ru.raisaryzbekova.voter.model.Restaurant;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface MenuItemRepository {

    MenuItem save(MenuItem menuItem, int dishId);

    Map<Restaurant, List<Dish>> getAllByDate(LocalDate date);

    MenuItem get(int id);

    List<MenuItem> getAll();
}

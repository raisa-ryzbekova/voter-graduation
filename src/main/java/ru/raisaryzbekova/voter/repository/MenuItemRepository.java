package ru.raisaryzbekova.voter.repository;

import ru.raisaryzbekova.voter.model.MenuItem;

import java.time.LocalDate;
import java.util.List;

public interface MenuItemRepository {

    MenuItem save(MenuItem menuItem, int dishId, int restaurantId);

    List<MenuItem> getByDate(LocalDate date);

    MenuItem get(int id);

    List<MenuItem> getAll();
}

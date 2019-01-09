package ru.raisaryzbekova.voter.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import ru.raisaryzbekova.voter.model.MenuItem;
import ru.raisaryzbekova.voter.repository.MenuItemRepository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class DataJpaMenuItemRepository implements MenuItemRepository {

    private static final Sort SORT_NAME = new Sort(Sort.Direction.DESC, "date");

    @Autowired
    CrudMenuItemRepository crudMenuItemRepository;

    @Autowired
    CrudDishRepository crudDishRepository;

    @Autowired
    CrudRestaurantRepository crudRestaurantRepository;

    @Override
    public MenuItem save(MenuItem menuItem, int dishId, int restaurantId) {
        if (!menuItem.isNew() && get(menuItem.getId()) == null) {
            return null;
        }
        menuItem.setDish(crudDishRepository.getOne(dishId));
        menuItem.setRestaurant(crudRestaurantRepository.getOne(restaurantId));
        return crudMenuItemRepository.save(menuItem);
    }

    @Override
    public List<MenuItem> getByDate(LocalDate date) {
        return crudMenuItemRepository.getByDate(date);
    }

    @Override
    public MenuItem get(int id) {
        return crudMenuItemRepository.findById(id).orElse(null);
    }

    @Override
    public List<MenuItem> getAll() {
        return crudMenuItemRepository.findAll(SORT_NAME);
    }
}

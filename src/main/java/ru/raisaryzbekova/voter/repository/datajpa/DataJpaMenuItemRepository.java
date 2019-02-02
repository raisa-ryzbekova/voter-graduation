package ru.raisaryzbekova.voter.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import ru.raisaryzbekova.voter.model.Dish;
import ru.raisaryzbekova.voter.model.MenuItem;
import ru.raisaryzbekova.voter.model.Restaurant;
import ru.raisaryzbekova.voter.repository.MenuItemRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;

@Repository
public class DataJpaMenuItemRepository implements MenuItemRepository {

    private static final Sort SORT_DATE = new Sort(Sort.Direction.DESC, "date");

    @Autowired
    CrudMenuItemRepository crudMenuItemRepository;

    @Autowired
    CrudDishRepository crudDishRepository;

    @Autowired
    CrudRestaurantRepository crudRestaurantRepository;

    @Override
    public MenuItem save(MenuItem menuItem, int dishId) {
        menuItem.setDish(crudDishRepository.getOne(dishId));
        return crudMenuItemRepository.save(menuItem);
    }

    @Override
    public MenuItem get(int id) {
        return crudMenuItemRepository.findById(id).orElse(null);
    }

    @Override
    public Map<Restaurant, List<Dish>> getAllByDate(LocalDate date) {
        return crudMenuItemRepository.getAllByDate(date).stream()
                .map(MenuItem::getDish)
                .collect(groupingBy(Dish::getRestaurant));
    }

    @Override
    public List<MenuItem> getAll() {
        return crudMenuItemRepository.findAll(SORT_DATE);
    }
}
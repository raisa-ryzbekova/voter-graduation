package ru.raisaryzbekova.voter.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.raisaryzbekova.voter.model.Dish;
import ru.raisaryzbekova.voter.repository.DishRepository;

import java.util.List;

@Repository
public class DataJpaDishRepositoryImpl implements DishRepository {

    @Autowired
    CrudDishRepository crudDishRepository;

    @Autowired
    CrudRestaurantRepository crudRestaurantRepository;

    @Override
    @Transactional
    public Dish save(Dish dish, int restaurantId) {
        dish.setRestaurant(crudRestaurantRepository.getOne(restaurantId));
        return crudDishRepository.save(dish);
    }

    @Override
    public Dish get(int id, int restaurantId) {
        return crudDishRepository.findById(id).filter(dish -> dish.getRestaurant().getId() == restaurantId).orElse(null);
    }

    @Override
    public List<Dish> getAllByRestaurant(int restaurantId) {
        return crudDishRepository.getAllByRestaurant(restaurantId);
    }
}
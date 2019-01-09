package ru.raisaryzbekova.voter.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import ru.raisaryzbekova.voter.model.Dish;
import ru.raisaryzbekova.voter.util.exception.NotFoundException;

import javax.validation.ConstraintViolationException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.raisaryzbekova.voter.testdata.DishTestData.*;
import static ru.raisaryzbekova.voter.testdata.RestaurantTestData.RESTAURANT1_ID;

public class DishRepositoryTest extends AbstractRepositoryTest {

    @Autowired
    protected DishRepository dishRepository;

    @Test
    void create() throws Exception {
        Dish created = getCreated();
        dishRepository.save(created, RESTAURANT1_ID);
        assertMatch(dishRepository.getAll(RESTAURANT1_ID), DISH1, DISH2, created);
    }

    @Test
    void duplicateDescriptionCreate() throws Exception {
        assertThrows(DataAccessException.class, () ->
                dishRepository.save(new Dish(null, "Блюдо 1", 500), RESTAURANT1_ID));
    }

    @Test
    void validation() throws Exception {
        validateRootCause(() -> dishRepository.save(new Dish(null, "  ", 500), RESTAURANT1_ID), ConstraintViolationException.class);
        validateRootCause(() -> dishRepository.save(new Dish(null, "Б", 500), RESTAURANT1_ID), ConstraintViolationException.class);
    }

    @Test
    void get() throws Exception {
        Dish actual = dishRepository.get(DISH1_ID, RESTAURANT1_ID);
        assertMatch(actual, DISH1);
    }

    @Test
    void getAll() throws Exception {
        assertMatch(dishRepository.getAll(RESTAURANT1_ID), DISHES);
    }
}
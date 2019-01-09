package ru.raisaryzbekova.voter.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import ru.raisaryzbekova.voter.model.Restaurant;
import ru.raisaryzbekova.voter.testdata.DishTestData;
import ru.raisaryzbekova.voter.util.exception.NotFoundException;

import javax.validation.ConstraintViolationException;
import java.time.Month;
import java.util.List;

import static java.time.LocalDate.of;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.raisaryzbekova.voter.testdata.RestaurantTestData.*;

class RestaurantServiceTest extends AbstractRepositoryTest {

    @Autowired
    protected RestaurantRepository restaurantRepository;

    @Test
    void create() throws Exception {
        Restaurant newRestaurant = new Restaurant(null, "NewRestaurant", "222225", "Улица, д.4");
        Restaurant created = restaurantRepository.save(new Restaurant(newRestaurant));
        newRestaurant.setId(created.getId());
        assertMatch(restaurantRepository.getAll(), newRestaurant, RESTAURANT_1, RESTAURANT_2, RESTAURANT_3);
    }

    @Test
    void get() throws Exception {
        Restaurant restaurant = restaurantRepository.get(RESTAURANT1_ID);
        assertMatch(restaurant, RESTAURANT_1);
    }


    @Test
    void getAll() throws Exception {
        List<Restaurant> all = restaurantRepository.getAll();
        assertMatch(all, RESTAURANT_1, RESTAURANT_2, RESTAURANT_3);
    }
}

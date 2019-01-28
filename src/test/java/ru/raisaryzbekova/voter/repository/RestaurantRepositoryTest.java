package ru.raisaryzbekova.voter.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.raisaryzbekova.voter.model.Restaurant;

import java.util.List;

import static ru.raisaryzbekova.voter.testdata.RestaurantTestData.*;

class RestaurantRepositoryTest extends AbstractRepositoryTest {

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

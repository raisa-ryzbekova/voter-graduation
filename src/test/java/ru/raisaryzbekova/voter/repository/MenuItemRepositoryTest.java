package ru.raisaryzbekova.voter.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.raisaryzbekova.voter.model.Dish;
import ru.raisaryzbekova.voter.model.MenuItem;
import ru.raisaryzbekova.voter.model.Restaurant;

import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.time.LocalDate.of;

import static ru.raisaryzbekova.voter.testdata.DishTestData.*;
import static ru.raisaryzbekova.voter.testdata.MenuItemTestData.*;
import static ru.raisaryzbekova.voter.testdata.RestaurantTestData.*;

public class MenuItemRepositoryTest extends AbstractRepositoryTest {

    @Autowired
    protected MenuItemRepository menuItemRepository;

    @Test
    void create() throws Exception {
        MenuItem created = getCreatedMenuItem();
        menuItemRepository.save(created, DISH1_ID);
        assertMatch(menuItemRepository.getAll(), created, MENU_ITEM5, MENU_ITEM4, MENU_ITEM3, MENU_ITEM2, MENU_ITEM1, MENU_ITEM6);
    }

    @Test
    void getByDate() throws Exception {
        Map<Restaurant, List<Dish>> data_for_voting = menuItemRepository.getAllByDate(of(2018, Month.NOVEMBER, 7));
        List<Restaurant> restaurantList = new ArrayList<>(data_for_voting.keySet());
        assertMatch(restaurantList, RESTAURANT_2, RESTAURANT_1, RESTAURANT_3);
        assertMatch(data_for_voting.get(RESTAURANT_1), DISH_1, DISH_2);
        assertMatch(data_for_voting.get(RESTAURANT_2), DISH_3, DISH_4);
        assertMatch(data_for_voting.get(RESTAURANT_3), DISH_5);
    }

    @Test
    void get() throws Exception {
        MenuItem actual = menuItemRepository.get(MENU_ITEM_ID);
        assertMatch(actual, MENU_ITEM1);
    }

    @Test
    void getAll() throws Exception {
        assertMatch(menuItemRepository.getAll(), MENU_ITEMS);
    }
}
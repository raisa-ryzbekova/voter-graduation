package ru.raisaryzbekova.voter.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.raisaryzbekova.voter.model.MenuItem;

import java.time.Month;

import static java.time.LocalDate.of;
import static ru.raisaryzbekova.voter.testdata.DishTestData.DISH1_ID;
import static ru.raisaryzbekova.voter.testdata.MenuItemTestData.*;
import static ru.raisaryzbekova.voter.testdata.RestaurantTestData.RESTAURANT1_ID;

public class MenuItemRepositoryTest extends AbstractRepositoryTest {

    @Autowired
    protected MenuItemRepository menuItemRepository;

    @Test
    void create() throws Exception {
        MenuItem created = getCreated();
        menuItemRepository.save(created, DISH1_ID, RESTAURANT1_ID);
        assertMatch(menuItemRepository.getAll(), MENU_ITEM1, MENU_ITEM2, MENU_ITEM3, MENU_ITEM4, MENU_ITEM5, created, MENU_ITEM6);
    }

    @Test
    void getByDate() throws Exception {
        assertMatch(menuItemRepository.getByDate(of(2018, Month.NOVEMBER, 7)), MENU_ITEM1, MENU_ITEM2, MENU_ITEM3, MENU_ITEM4, MENU_ITEM5);
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

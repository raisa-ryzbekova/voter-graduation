package ru.raisaryzbekova.voter.testdata;

import ru.raisaryzbekova.voter.model.MenuItem;

import java.time.Month;
import java.util.List;

import static java.time.LocalDate.of;
import static java.time.LocalTime.of;
import static org.assertj.core.api.Assertions.assertThat;
import static ru.raisaryzbekova.voter.model.AbstractBaseEntity.START_SEQ;

public class MenuItemTestData {
    public static final int MENU_ITEM_ID = START_SEQ + 11;

    public static final MenuItem MENU_ITEM1 = new MenuItem(MENU_ITEM_ID, of(2018, Month.NOVEMBER, 7), of(7, 0));
    public static final MenuItem MENU_ITEM2 = new MenuItem(MENU_ITEM_ID + 1, of(2018, Month.NOVEMBER, 7), of(7, 0));
    public static final MenuItem MENU_ITEM3 = new MenuItem(MENU_ITEM_ID + 2, of(2018, Month.NOVEMBER, 7), of(7, 0));
    public static final MenuItem MENU_ITEM4 = new MenuItem(MENU_ITEM_ID + 3, of(2018, Month.NOVEMBER, 7), of(7, 0));
    public static final MenuItem MENU_ITEM5 = new MenuItem(MENU_ITEM_ID + 4, of(2018, Month.NOVEMBER, 7), of(7, 0));
    public static final MenuItem MENU_ITEM6 = new MenuItem(MENU_ITEM_ID + 5, of(2018, Month.NOVEMBER, 6), of(7, 0));

    public static final List<MenuItem> MENU_ITEMS = List.of(MENU_ITEM1, MENU_ITEM2, MENU_ITEM3, MENU_ITEM4, MENU_ITEM5, MENU_ITEM6);

    public static MenuItem getCreated() {
        return new MenuItem(null, of(2018, Month.NOVEMBER, 7), of(7, 0));
    }

    public static void assertMatch(MenuItem actual, MenuItem expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "restaurant", "dish");
    }

    public static void assertMatch(Iterable<MenuItem> actual, MenuItem... expected) {
        assertMatch(actual, List.of(expected));
    }

    public static void assertMatch(Iterable<MenuItem> actual, Iterable<MenuItem> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("restaurant", "dish").isEqualTo(expected);
    }
}

package ru.raisaryzbekova.voter.testdata;

import ru.raisaryzbekova.voter.model.Restaurant;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.raisaryzbekova.voter.model.AbstractBaseEntity.START_SEQ;

public class RestaurantTestData {
    public static final int RESTAURANT1_ID = START_SEQ + 2;

    public static final Restaurant RESTAURANT_1 = new Restaurant(RESTAURANT1_ID, "Ресторан 1", "222222", "Улица, д.1");
    public static final Restaurant RESTAURANT_2 = new Restaurant(RESTAURANT1_ID + 1, "Ресторан 2", "222223", "Улица, д.2");
    public static final Restaurant RESTAURANT_3 = new Restaurant(RESTAURANT1_ID + 2, "Ресторан 3", "222224", "Улица, д.3");

    public static void assertMatch(Restaurant actual, Restaurant expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "dishes", "votes", "menuItems");
    }

    public static void assertMatch(Iterable<Restaurant> actual, Restaurant... expected) {
        assertMatch(actual, List.of(expected));
    }

    public static void assertMatch(Iterable<Restaurant> actual, Iterable<Restaurant> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("dishes", "votes", "menuItems").isEqualTo(expected);
    }
}
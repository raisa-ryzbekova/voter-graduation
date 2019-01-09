package ru.raisaryzbekova.voter.testdata;

import ru.raisaryzbekova.voter.model.Dish;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.raisaryzbekova.voter.model.AbstractBaseEntity.START_SEQ;

public class DishTestData {

    public static final int DISH1_ID = START_SEQ + 5;

    public static final Dish DISH1 = new Dish(DISH1_ID, "Блюдо 1", 500);
    public static final Dish DISH2 = new Dish(DISH1_ID + 1, "Блюдо 2", 500);
    public static final Dish DISH3 = new Dish(DISH1_ID + 2, "Блюдо 3", 500);
    public static final Dish DISH4 = new Dish(DISH1_ID + 3, "Блюдо 4", 500);
    public static final Dish DISH5 = new Dish(DISH1_ID + 4, "Блюдо 5", 500);
    public static final Dish DISH6 = new Dish(DISH1_ID + 5, "Блюдо 6", 500);

    public static final List<Dish> DISHES = List.of(DISH1, DISH2);

    public static Dish getCreated() {
        return new Dish(null, "Созданное блюдо", 500);
    }

    public static void assertMatch(Dish actual, Dish expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "restaurant", "menuItems");
    }

    public static void assertMatch(Iterable<Dish> actual, Dish... expected) {
        assertMatch(actual, List.of(expected));
    }

    public static void assertMatch(Iterable<Dish> actual, Iterable<Dish> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("restaurant", "menuItems").isEqualTo(expected);
    }
}
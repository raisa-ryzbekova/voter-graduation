package ru.raisaryzbekova.voter.testdata;

import ru.raisaryzbekova.voter.model.User;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.raisaryzbekova.voter.model.AbstractBaseEntity.START_SEQ;

public class UserTestData {
    public static final int ADMIN_ID = START_SEQ;
    public static final int USER1_ID = START_SEQ + 1;

    public static final User ADMIN = new User(ADMIN_ID, "Admin", "admin@gmail.com", "admin", "ROLE_ADMIN");
    public static final User USER1 = new User(USER1_ID, "User1", "user1@yandex.ru", "password1", "ROLE_USER");

    public static void assertMatch(User actual, User expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "votes");
    }

    public static void assertMatch(Iterable<User> actual, User... expected) {
        assertMatch(actual, List.of(expected));
    }

    public static void assertMatch(Iterable<User> actual, Iterable<User> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("votes").isEqualTo(expected);
    }
}
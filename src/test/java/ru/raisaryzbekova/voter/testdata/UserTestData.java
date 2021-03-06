package ru.raisaryzbekova.voter.testdata;

import ru.raisaryzbekova.voter.model.Role;
import ru.raisaryzbekova.voter.model.User;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.raisaryzbekova.voter.model.AbstractBaseEntity.START_SEQ;

public class UserTestData {
    public static final int USER_ID = START_SEQ;
    public static final int ADMIN_ID = START_SEQ + 1;

    public static final User USER = new User(USER_ID, "User", "user@yandex.ru", "password", Role.ROLE_USER);
    public static final User ADMIN = new User(ADMIN_ID, "Admin", "admin@gmail.com", "admin", Role.ROLE_USER, Role.ROLE_ADMIN);

    public static void assertMatch(User actual, User expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "votes", "registered", "password");
    }

    public static void assertMatch(Iterable<User> actual, User... expected) {
        assertMatch(actual, List.of(expected));
    }

    public static void assertMatch(Iterable<User> actual, Iterable<User> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("votes", "registered", "password").isEqualTo(expected);
    }
}
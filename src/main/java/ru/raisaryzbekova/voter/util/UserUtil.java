package ru.raisaryzbekova.voter.util;

import ru.raisaryzbekova.voter.model.User;
import ru.raisaryzbekova.voter.to.UserTo;

public class UserUtil {

    public static UserTo asTo(User user) {
        return new UserTo(user.getId(), user.getName(), user.getEmail(), user.getPassword());
    }
}
package ru.raisaryzbekova.voter.repository;

import ru.raisaryzbekova.voter.model.User;

import java.util.List;

public interface UserRepository {

    User save(User user);

    User get(int id);

    List<User> getAll();
}
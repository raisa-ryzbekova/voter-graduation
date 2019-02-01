package ru.raisaryzbekova.voter.service;

import ru.raisaryzbekova.voter.model.User;
import ru.raisaryzbekova.voter.util.exception.NotFoundException;

import java.util.List;

public interface UserService {

    User create(User user);

    User get(int id) throws NotFoundException;

    List<User> getAll();

    void enable(int id, boolean enable);

    void delete(int id) throws NotFoundException;
}
package ru.raisaryzbekova.voter.repository;

import ru.raisaryzbekova.voter.model.Vote;

import java.time.LocalDate;
import java.util.List;

public interface VoteRepository {

    Vote save(Vote vote, int userId, int restaurantId);

    Vote get(LocalDate date, int userId);

    List<Vote> getAll(int userId);
}
package ru.raisaryzbekova.voter.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.raisaryzbekova.voter.model.Vote;

import java.time.LocalDate;

import static ru.raisaryzbekova.voter.testdata.RestaurantTestData.*;
import static ru.raisaryzbekova.voter.testdata.UserTestData.USER_ID;
import static ru.raisaryzbekova.voter.testdata.VoteTestData.*;

public class VoteRepositoryTest extends AbstractRepositoryTest {

    @Autowired
    protected VoteRepository voteRepository;

    @Test
    void create() throws Exception {
        Vote created = getCreated();
        voteRepository.save(created, USER_ID, RESTAURANT1_ID);
        assertMatch(voteRepository.getAll(USER_ID), created, VOTE1);
    }

    @Test
    void update() throws Exception {
        Vote updated = getUpdated();
        voteRepository.save(updated, USER_ID, RESTAURANT1_ID);
        assertMatch(voteRepository.get(LocalDate.of(2018, 11, 7), USER_ID), updated);
    }
}
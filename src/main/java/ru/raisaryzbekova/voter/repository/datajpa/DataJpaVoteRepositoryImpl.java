package ru.raisaryzbekova.voter.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.raisaryzbekova.voter.model.Vote;
import ru.raisaryzbekova.voter.repository.VoteRepository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class DataJpaVoteRepositoryImpl implements VoteRepository {

    @Autowired
    CrudVoteRepository crudVoteRepository;

    @Autowired
    CrudUserRepository crudUserRepository;

    @Autowired
    CrudRestaurantRepository crudRestaurantRepository;

    @Override
    @Transactional
    public Vote save(Vote vote, int userId, int restaurantId) {
        if (!vote.isNew() && get(vote.getDate(), userId) == null) {
            return null;
        }
        vote.setUser(crudUserRepository.getOne(userId));
        vote.setRestaurant(crudRestaurantRepository.getOne(restaurantId));
        return crudVoteRepository.save(vote);
    }

    @Override
    public Vote get(LocalDate date, int userId) {
        return crudVoteRepository.get(date, userId);
    }

    @Override
    public List<Vote> getAll(int userId) {
        return crudVoteRepository.getAll(userId);
    }
}

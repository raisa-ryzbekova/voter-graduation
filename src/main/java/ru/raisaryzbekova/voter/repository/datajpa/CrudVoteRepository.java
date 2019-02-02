package ru.raisaryzbekova.voter.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.raisaryzbekova.voter.model.Vote;

import java.time.LocalDate;
import java.util.List;

@Transactional(readOnly = true)
public interface CrudVoteRepository extends JpaRepository<Vote, Integer> {

    @Override
    @Transactional
    Vote save(Vote vote);

    @Query("SELECT v FROM Vote v WHERE v.date=:date AND v.user.id=:userId")
    Vote get(@Param("date") LocalDate date, @Param("userId") int userId);

    @Query("SELECT v FROM Vote v WHERE v.user.id=:userId ORDER BY v.date DESC")
    List<Vote> getAllByUser(@Param("userId") int userId);
}
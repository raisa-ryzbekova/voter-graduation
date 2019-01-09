package ru.raisaryzbekova.voter.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.raisaryzbekova.voter.model.MenuItem;

import java.time.LocalDate;
import java.util.List;

@Transactional(readOnly = true)
public interface CrudMenuItemRepository extends JpaRepository<MenuItem, Integer> {

    @Override
    @Transactional
    MenuItem save(MenuItem menuItem);

    @Query("SELECT m FROM MenuItem m JOIN FETCH m.dish JOIN FETCH m.restaurant WHERE m.date=:date")
    List<MenuItem> getByDate(@Param("date") LocalDate date);
}

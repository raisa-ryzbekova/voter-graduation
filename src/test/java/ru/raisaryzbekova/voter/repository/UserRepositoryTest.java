package ru.raisaryzbekova.voter.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import ru.raisaryzbekova.voter.model.Role;
import ru.raisaryzbekova.voter.model.User;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import static ru.raisaryzbekova.voter.testdata.UserTestData.*;

class UserRepositoryTest extends AbstractRepositoryTest {

    @Autowired
    protected UserRepository userRepository;

    @Autowired
    private CacheManager cacheManager;

    @Autowired
    private JpaUtil jpaUtil;

    @BeforeEach
    void setUp() throws Exception {
        cacheManager.getCache("users").clear();
        jpaUtil.clear2ndLevelHibernateCache();
    }

    @Test
    void create() throws Exception {
        User newUser = new User(null, "New", "new@gmail.com", "newPass", false, new Date(), Collections.singleton(Role.ROLE_USER));
        User created = userRepository.save(new User(newUser));
        newUser.setId(created.getId());
        assertMatch(userRepository.getAll(), ADMIN, newUser, USER);
    }

    @Test
    void get() throws Exception {
        User user = userRepository.get(USER_ID);
        assertMatch(user, USER);
    }

    @Test
    void getAll() throws Exception {
        List<User> all = userRepository.getAll();
        assertMatch(all, ADMIN, USER);
    }
}
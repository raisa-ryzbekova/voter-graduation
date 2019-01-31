package ru.raisaryzbekova.voter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import ru.raisaryzbekova.voter.AuthorizedUser;
import ru.raisaryzbekova.voter.model.User;
import ru.raisaryzbekova.voter.repository.UserRepository;
import ru.raisaryzbekova.voter.util.exception.NotFoundException;

import java.util.List;

import static ru.raisaryzbekova.voter.util.UserUtil.prepareToSave;
import static ru.raisaryzbekova.voter.util.ValidationUtil.checkNotFoundWithId;

@Service("userService")
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.userRepository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @CachePut(value = "users", key = "#user.id")
    public User create(User user) {
        Assert.notNull(user, "User must not be null");
        return userRepository.save(prepareToSave(user, passwordEncoder));
    }

    @Cacheable(value = "users", key = "#id")
    public User get(int id) throws NotFoundException {
        return checkNotFoundWithId(userRepository.get(id), id);
    }

    public List<User> getAll() {
        return userRepository.getAll();
    }

    @CachePut(value = "users", key = "#id")
    @Transactional
    public void enable(int id, boolean enabled) {
        User user = userRepository.get(id);
        user.setEnabled(enabled);
        userRepository.save(user);
    }

    @CacheEvict(value = "users", allEntries = true)
    public void delete(int id) throws NotFoundException {
        checkNotFoundWithId(userRepository.delete(id), id);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.getByEmail(email.toLowerCase());
        if (user == null) {
            throw new UsernameNotFoundException("User " + email + " is not found");
        }
        return new AuthorizedUser(user);
    }
}
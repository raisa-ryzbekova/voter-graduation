package ru.raisaryzbekova.voter.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.raisaryzbekova.voter.model.User;
import ru.raisaryzbekova.voter.repository.UserRepository;

import java.net.URI;
import java.util.List;

import static ru.raisaryzbekova.voter.util.ValidationUtil.checkNew;
import static ru.raisaryzbekova.voter.util.ValidationUtil.checkNotFoundWithId;
import static ru.raisaryzbekova.voter.web.SecurityUtil.authUserId;

@RestController
@RequestMapping
public class UserRestController {

    protected final Logger log = LoggerFactory.getLogger(getClass());

    private final UserRepository userRepository;

    @Autowired
    public UserRestController(UserRepository repository) {
        this.userRepository = repository;
    }

    @PostMapping(value = "/rest/admin/users", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @CacheEvict(value = "users", allEntries = true)
    public ResponseEntity<User> create(@RequestBody User user) {
        log.info("create {}", user);
        checkNew(user);
        User created = userRepository.save(user);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/rest/admin/users/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @GetMapping(value = "/rest/admin/users/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public User get(@PathVariable("id") int id) {
        log.info("get {}", id);
        return checkNotFoundWithId(userRepository.get(id), id);
    }

    @GetMapping(value = "/rest/admin/users", produces = MediaType.APPLICATION_JSON_VALUE)
    @Cacheable("users")
    public List<User> getAll() {
        log.info("getAll");
        return userRepository.getAll();
    }

    @GetMapping(value = "/rest/profile", produces = MediaType.APPLICATION_JSON_VALUE)
    public User get() {
        return userRepository.get(authUserId());
    }
}
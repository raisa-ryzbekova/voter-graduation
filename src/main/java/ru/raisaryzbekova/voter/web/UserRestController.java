package ru.raisaryzbekova.voter.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.raisaryzbekova.voter.model.User;
import ru.raisaryzbekova.voter.service.UserService;

import java.net.URI;
import java.util.List;

import static ru.raisaryzbekova.voter.util.ValidationUtil.checkNew;
import static ru.raisaryzbekova.voter.util.SecurityUtil.authUserId;

@RestController
@RequestMapping
public class UserRestController {

    @Autowired
    private UserService userService;

    protected final Logger log = LoggerFactory.getLogger(getClass());

    @PostMapping(value = "/rest/admin/users", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> create(@RequestBody User user) {
        log.info("create {}", user);
        checkNew(user);
        User created = userService.create(user);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/rest/admin/users/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @GetMapping(value = "/rest/admin/users/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public User get(@PathVariable("id") int id) {
        log.info("get {}", id);
        return userService.get(id);
    }

    @GetMapping(value = "/rest/admin/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> getAll() {
        log.info("getAll");
        return userService.getAll();
    }

    @GetMapping(value = "/rest/profile", produces = MediaType.APPLICATION_JSON_VALUE)
    public User get() {
        return userService.get(authUserId());
    }

    @PutMapping(value = "/rest/admin/users/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void enable(@PathVariable("id") int id, @RequestBody User user) {
        userService.enable(id, user.isEnabled());
    }

    @DeleteMapping(value = "/rest/profile")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete() {
        userService.delete(authUserId());
    }
}
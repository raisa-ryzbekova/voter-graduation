package ru.raisaryzbekova.voter.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.raisaryzbekova.voter.model.Dish;
import ru.raisaryzbekova.voter.repository.DishRepository;
import ru.raisaryzbekova.voter.to.DishTo;
import ru.raisaryzbekova.voter.util.exception.NotFoundException;

import java.net.URI;
import java.util.List;

import static ru.raisaryzbekova.voter.util.ValidationUtil.checkNotFoundWithId;

@RestController
@RequestMapping(DishRestController.REST_URL)
public class DishRestController {

    protected final Logger log = LoggerFactory.getLogger(getClass());

    private final DishRepository dishRepository;

    @Autowired
    public DishRestController(DishRepository repository) {
        this.dishRepository = repository;
    }

    static final String REST_URL = "/rest/admin";

    @PostMapping(value = "/dishes", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Dish> create(@RequestBody DishTo dishTo) {
        log.info("create {} in restaurant id={}", dishTo, dishTo.getRestaurantId());
        Dish created = dishRepository.save(new Dish(dishTo.getName(), dishTo.getPrice()), dishTo.getRestaurantId());
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @GetMapping(value = "/dishes/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Dish get(@PathVariable("id") int id, @RequestParam(value = "restaurantId", required = false) int restaurantId) throws NotFoundException {
        log.info("get dish with id={}", id);
        return checkNotFoundWithId(dishRepository.get(id, restaurantId), id);
    }

    @GetMapping(value = "/dishes", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Dish> getAll(@RequestParam(value = "restaurantId", required = false) int restaurantId) {
        log.info("get all dishes");
        return dishRepository.getAll(restaurantId);
    }
}
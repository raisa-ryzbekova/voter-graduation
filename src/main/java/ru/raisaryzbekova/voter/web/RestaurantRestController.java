package ru.raisaryzbekova.voter.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.raisaryzbekova.voter.model.Restaurant;
import ru.raisaryzbekova.voter.repository.RestaurantRepository;
import ru.raisaryzbekova.voter.util.exception.NotFoundException;

import java.net.URI;
import java.util.List;

import static ru.raisaryzbekova.voter.util.ValidationUtil.checkNew;
import static ru.raisaryzbekova.voter.util.ValidationUtil.checkNotFoundWithId;

@RestController
@RequestMapping(RestaurantRestController.REST_URL)
public class RestaurantRestController {

    protected final Logger log = LoggerFactory.getLogger(getClass());

    private final RestaurantRepository restaurantRepository;

    @Autowired
    public RestaurantRestController(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    static final String REST_URL = "/rest/admin/restaurants";

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Restaurant> create(@RequestBody Restaurant restaurant) throws IllegalArgumentException {
        log.info("create {}", restaurant);
        checkNew(restaurant);
        Restaurant created = restaurantRepository.save(restaurant);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @GetMapping(value = "/{restaurantId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Restaurant get(@PathVariable("restaurantId") int id) throws NotFoundException {
        log.info("get {}", id);
        return checkNotFoundWithId(restaurantRepository.get(id), id);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Restaurant> getAll() {
        log.info("getAll");
        return restaurantRepository.getAll();
    }
}
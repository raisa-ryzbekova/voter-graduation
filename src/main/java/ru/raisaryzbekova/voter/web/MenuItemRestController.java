package ru.raisaryzbekova.voter.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.raisaryzbekova.voter.model.Dish;
import ru.raisaryzbekova.voter.model.MenuItem;
import ru.raisaryzbekova.voter.model.Restaurant;
import ru.raisaryzbekova.voter.repository.MenuItemRepository;
import ru.raisaryzbekova.voter.to.MenuItemTo;

import java.net.URI;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping
public class MenuItemRestController {

    protected final Logger log = LoggerFactory.getLogger(getClass());

    private final MenuItemRepository menuItemRepository;

    @Autowired
    public MenuItemRestController(MenuItemRepository repository) {
        this.menuItemRepository = repository;
    }

    @PostMapping(value = "/rest/admin/menu-items", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MenuItem> create(@RequestBody MenuItemTo menuItemTo) {
        log.info("create menu item {}", menuItemTo);
        MenuItem created = menuItemRepository.save(new MenuItem(LocalDate.now(), LocalTime.now()), menuItemTo.getDishId());
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/rest/admin/menu-items/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @GetMapping(value = "/rest/profile/menu-items-by-date", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<Restaurant, List<Dish>> getByDate(@RequestParam(value = "date", required = false) LocalDate date) {
        log.info("get by date menu items");
        return menuItemRepository.getAllByDate(date);
    }
}
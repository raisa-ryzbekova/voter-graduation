package ru.raisaryzbekova.voter.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.raisaryzbekova.voter.model.MenuItem;
import ru.raisaryzbekova.voter.repository.MenuItemRepository;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

import static ru.raisaryzbekova.voter.util.ValidationUtil.checkNew;

@RestController
@RequestMapping
public class MenuItemRestController {

    protected final Logger log = LoggerFactory.getLogger(getClass());

    private final MenuItemRepository menuItemRepository;

    @Autowired
    public MenuItemRestController(MenuItemRepository repository) {
        this.menuItemRepository = repository;
    }

    @PostMapping(value = "/rest/admin/{restaurantId}/menu-items", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MenuItem> create(@RequestBody MenuItem menuItem,
                                           @PathVariable("restaurantId") int restaurantId,
                                           @RequestParam(value = "dishId", required = false) int dishId) {
        log.info("create menu item {}", menuItem);
        checkNew(menuItem);
        MenuItem created = menuItemRepository.save(menuItem, dishId, restaurantId);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/rest/admin/menu-items/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @GetMapping("/rest/profile/menu-items-by-date")
    public List<MenuItem> getByDate(@RequestParam(value = "date", required = false) LocalDate date) {
        log.info("get by date menu items");
        return menuItemRepository.getByDate(date);
    }
}
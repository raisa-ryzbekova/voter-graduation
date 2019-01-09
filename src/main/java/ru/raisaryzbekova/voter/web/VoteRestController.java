package ru.raisaryzbekova.voter.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.raisaryzbekova.voter.model.Vote;
import ru.raisaryzbekova.voter.repository.VoteRepository;
import ru.raisaryzbekova.voter.util.exception.LateForVoteException;
import ru.raisaryzbekova.voter.util.exception.NotFoundException;

import java.net.URI;
import java.time.LocalTime;
import java.util.List;

import static ru.raisaryzbekova.voter.util.ValidationUtil.*;
import static ru.raisaryzbekova.voter.web.SecurityUtil.authUserId;

@RestController
@RequestMapping(VoteRestController.REST_URL)
public class VoteRestController {

    protected final Logger log = LoggerFactory.getLogger(getClass());

    private final VoteRepository voteRepository;

    @Autowired
    public VoteRestController(VoteRepository repository) {
        this.voteRepository = repository;
    }

    LocalTime localTime = LocalTime.now();

    static final String REST_URL = "/rest/profile/votes";

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Vote> create(@RequestBody Vote vote, @RequestParam(value = "restaurantId", required = false) int restaurantId) throws LateForVoteException, IllegalArgumentException {
        log.info("create {}", vote);
        checkNew(vote);
        if (localTime.isBefore(LocalTime.of(11, 0, 0))) {
            Vote created = voteRepository.save(vote, authUserId(), restaurantId);
            URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path(REST_URL + "/{id}")
                    .buildAndExpand(created.getId()).toUri();
            return ResponseEntity.created(uriOfNewResource).body(created);
        } else {
            throw new LateForVoteException(vote + " is too late, can't be changed");
        }
    }

    @GetMapping("/{id}")
    public Vote get(@PathVariable("id") int id) throws NotFoundException {
        log.info("get {} userId={}", id, authUserId());
        return checkNotFoundWithId(voteRepository.get(id, authUserId()), id);
    }

    @GetMapping
    public List<Vote> getAll() {
        log.info("getAll userId={}");
        return voteRepository.getAll(authUserId());
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void update(@RequestBody Vote vote, @PathVariable("id") int id, @RequestParam(value = "restaurantId", required = false) int restaurantId) throws NotFoundException, LateForVoteException {
        log.info("update {} userId={}", vote, authUserId());
        Assert.notNull(vote, "vote must not be null");
        assureIdConsistent(vote, id);
        if (localTime.isBefore(LocalTime.of(11, 0, 0))) {
            checkNotFoundWithId(voteRepository.save(vote, authUserId(), restaurantId), vote.getId());
        } else {
            throw new LateForVoteException(vote + " is too late, can't be changed");
        }
    }
}

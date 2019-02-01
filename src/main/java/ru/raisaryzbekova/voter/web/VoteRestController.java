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
import ru.raisaryzbekova.voter.to.VoteTo;
import ru.raisaryzbekova.voter.util.exception.LateForVoteException;
import ru.raisaryzbekova.voter.util.exception.NotFoundException;

import java.net.URI;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static ru.raisaryzbekova.voter.util.ValidationUtil.*;
import static ru.raisaryzbekova.voter.util.SecurityUtil.authUserId;

@RestController
@RequestMapping(VoteRestController.REST_URL)
public class VoteRestController {

    protected final Logger log = LoggerFactory.getLogger(getClass());

    private static final LocalTime TIME_LIMIT_FOR_CHANGE_VOTE = LocalTime.of(11, 0, 0);

    static final String REST_URL = "/rest/profile/votes";

    private final VoteRepository voteRepository;

    @Autowired
    public VoteRestController(VoteRepository repository) {
        this.voteRepository = repository;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Vote> create(@RequestBody VoteTo voteTo) throws LateForVoteException, IllegalArgumentException {
        log.info("create {}", voteTo);
        Vote created = voteRepository.save(new Vote(LocalDate.now(), LocalTime.now()), authUserId(), voteTo.getRestaurantId());
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
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
    public void update(@RequestBody VoteTo voteTo, @PathVariable("id") int id) throws NotFoundException, LateForVoteException {
        log.info("update {} userId={}", voteTo, authUserId());
        Assert.notNull(voteTo, "voteTo must not be null");
        LocalTime localTime = LocalTime.now();
        if (localTime.isBefore(TIME_LIMIT_FOR_CHANGE_VOTE)) {
            Vote vote = voteRepository.get(id, authUserId());
            vote.setLocalTime(localTime);
            vote.setDate(LocalDate.now());
            voteRepository.save(vote, authUserId(), voteTo.getRestaurantId());
        } else {
            throw new LateForVoteException(voteTo + " is too late, can't be changed");
        }
    }
}
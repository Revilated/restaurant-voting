package com.github.revilated.restaurantvoting.web.vote;

import com.github.revilated.restaurantvoting.model.*;
import com.github.revilated.restaurantvoting.service.*;
import com.github.revilated.restaurantvoting.web.*;
import lombok.*;
import lombok.extern.slf4j.*;
import org.springframework.http.*;
import org.springframework.security.core.annotation.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = VoteController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@AllArgsConstructor
public class VoteController {

    static final String REST_URL = "/api/profile/votes";

    private final VotingService votingService;

    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void vote(@AuthenticationPrincipal AuthUser user, @RequestParam int restaurantId) {
        log.info("vote for restaurantId={}", restaurantId);
        votingService.vote(user.id(), restaurantId);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void changeVote(@AuthenticationPrincipal AuthUser user, @RequestParam int restaurantId) {
        log.info("change vote to restaurantId={}", restaurantId);
        votingService.changeVote(user.id(), restaurantId);
    }

    @GetMapping
    public ResponseEntity<Vote> getPerToday(@AuthenticationPrincipal AuthUser user) {
        log.info("get today's vote");
        return ResponseEntity.of(votingService.get(user.id()));
    }
}
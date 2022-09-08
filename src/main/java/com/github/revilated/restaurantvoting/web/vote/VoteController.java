package com.github.revilated.restaurantvoting.web.vote;

import com.github.revilated.restaurantvoting.service.*;
import com.github.revilated.restaurantvoting.web.*;
import lombok.*;
import lombok.extern.slf4j.*;
import org.springframework.cache.annotation.*;
import org.springframework.http.*;
import org.springframework.security.core.annotation.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = VoteController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@AllArgsConstructor
@CacheConfig(cacheNames = "restaurants")
public class VoteController {

    static final String REST_URL = "/api/profile/restaurants/{id}/vote";

    private final VotingService votingService;

    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @CacheEvict(allEntries = true)
    public void vote(@PathVariable int id, @AuthenticationPrincipal AuthUser user) {
        log.info("vote for restaurantId={}", id);
        votingService.vote(user.id(), id);
    }
}
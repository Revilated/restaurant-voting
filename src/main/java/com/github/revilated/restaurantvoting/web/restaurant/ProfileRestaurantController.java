package com.github.revilated.restaurantvoting.web.restaurant;

import com.github.revilated.restaurantvoting.repository.*;
import com.github.revilated.restaurantvoting.to.*;
import lombok.*;
import lombok.extern.slf4j.*;
import org.springframework.cache.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(value = ProfileRestaurantController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@AllArgsConstructor
@CacheConfig(cacheNames = "restaurants")
public class ProfileRestaurantController {

    static final String REST_URL = "/api/profile/restaurants";

    private final RestaurantRepository repository;

    @GetMapping
    @Cacheable
    public List<RestaurantTo> getAllWithVotes() {
        log.info("getAllWithVotes");
        return repository.findAllWithDailyVotes().stream()
                .map(r -> new RestaurantTo(r.getId(), r.getName(), r.getVotes().size()))
                .toList();
    }
}
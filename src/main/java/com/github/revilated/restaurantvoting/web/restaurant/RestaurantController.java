package com.github.revilated.restaurantvoting.web.restaurant;

import com.github.revilated.restaurantvoting.repository.*;
import com.github.revilated.restaurantvoting.to.*;
import com.github.revilated.restaurantvoting.util.*;
import lombok.*;
import lombok.extern.slf4j.*;
import org.springframework.cache.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(value = RestaurantController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@AllArgsConstructor
@CacheConfig(cacheNames = "restaurants")
public class RestaurantController {

    static final String REST_URL = "/api/restaurants";

    private final RestaurantRepository repository;

    @GetMapping
    @Cacheable
    public List<RestaurantTo> getAllWithVotesAndMenu() {
        log.info("getAllWithVotesAndMenu");
        return RestaurantUtil.toTos(repository.findAllWithDailyVotesAndMenu());
    }
}
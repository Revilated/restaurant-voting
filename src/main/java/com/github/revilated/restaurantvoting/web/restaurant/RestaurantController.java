package com.github.revilated.restaurantvoting.web.restaurant;

import com.github.revilated.restaurantvoting.repository.*;
import com.github.revilated.restaurantvoting.to.*;
import com.github.revilated.restaurantvoting.util.*;
import lombok.*;
import lombok.extern.slf4j.*;
import org.springframework.cache.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.time.*;
import java.util.*;

@RestController
@RequestMapping(value = RestaurantController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@AllArgsConstructor
@CacheConfig(cacheNames = "restaurants")
public class RestaurantController {

    static final String REST_URL = "/api/restaurants";

    private final RestaurantRepository restaurantRepository;
    private final VoteRepository voteRepository;

    @GetMapping
    @Cacheable
    public List<RestaurantTo> getAllWithDailyVotesAndMenu() {
        log.info("getAllWithDailyVotesAndMenu");
        var date = LocalDate.now();
        return restaurantRepository.findAllWithMenuByDate(date).stream()
                .map(r -> {
                    int id = Objects.requireNonNull(r.getId(), "Restaurant id must not be null");
                    var votes = voteRepository.countAllByRestaurantIdAndCreatedDate(id, date);
                    return RestaurantUtil.toTo(r, votes);
                })
                .toList();
    }
}
package com.github.revilated.restaurantvoting.web.dish;

import com.github.revilated.restaurantvoting.model.*;
import com.github.revilated.restaurantvoting.repository.*;
import lombok.*;
import lombok.extern.slf4j.*;
import org.springframework.cache.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.time.*;
import java.util.*;

@RestController
@RequestMapping(value = MenuController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@AllArgsConstructor
@CacheConfig(cacheNames = "menu")
public class MenuController {

    static final String REST_URL = "/api/restaurants/{restaurantId}/menu";

    private final DishRepository repository;

    @GetMapping
    @Cacheable
    public List<Dish> getPerToday(@PathVariable int restaurantId) {
        log.info("getAllPerDay");
        return repository.findMenu(restaurantId, LocalDate.now());
    }
}
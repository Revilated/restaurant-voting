package com.github.revilated.restaurantvoting.web.menu;

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
@RequestMapping(value = ProfileMenuController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@AllArgsConstructor
@CacheConfig(cacheNames = "menu")
public class ProfileMenuController {

    static final String REST_URL = "/api/profile/restaurants/{restaurantId}/menu";

    private final MenuRepository repository;

    @GetMapping
    @Cacheable
    public List<Dish> getAllPerDay(@PathVariable int restaurantId) {
        log.info("getAllPerDay");
        return repository.findAllPerDate(restaurantId, LocalDate.now());
    }
}
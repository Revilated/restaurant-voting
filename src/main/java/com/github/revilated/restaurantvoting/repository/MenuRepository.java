/*
 * 2022 https://github.com/revilated
 */
package com.github.revilated.restaurantvoting.repository;

import com.github.revilated.restaurantvoting.error.*;
import com.github.revilated.restaurantvoting.model.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.transaction.annotation.*;

import java.time.*;
import java.util.*;

/**
 * @author revilated
 */
@Transactional(readOnly = true)
public interface MenuRepository extends BaseRepository<Dish> {

    Optional<Dish> findByRestaurantIdAndId(int restaurantId, int id);

    default void checkBelongs(int restaurantId, int id) {
        findByRestaurantIdAndId(restaurantId, id).orElseThrow(
                () -> new DataConflictException(Dish.class.getSimpleName() + " id=" + id +
                        " doesn't belong to " + Restaurant.class.getSimpleName() + " id=" + restaurantId));
    }

    @Query("SELECT d FROM Dish d WHERE d.restaurantId = :restaurantId AND d.createdDate = :date ORDER BY d.name")
    List<Dish> findAllPerDate(int restaurantId, LocalDate date);
}
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

import static com.github.revilated.restaurantvoting.util.validation.ValidationUtil.*;

/**
 * @author revilated
 */
@Transactional(readOnly = true)
public interface DishRepository extends BaseRepository<Dish> {

    Optional<Dish> findByRestaurantIdAndId(int restaurantId, int id);

    @Transactional
    int deleteByRestaurantIdAndId(int restaurantId, int id);

    default void deleteExisted(int restaurantId, int id) {
        checkModification(deleteByRestaurantIdAndId(restaurantId, id), id);
    }

    default void checkBelongs(int restaurantId, int id) {
        findByRestaurantIdAndId(restaurantId, id).orElseThrow(
                () -> new DataConflictException(Dish.class.getSimpleName() + " id=" + id +
                        " doesn't belong to " + Restaurant.class.getSimpleName() + " id=" + restaurantId));
    }

    @Query("SELECT d FROM Dish d WHERE d.restaurantId = :restaurantId AND d.createdDate = :date ORDER BY d.name")
    List<Dish> findMenu(int restaurantId, LocalDate date);
}

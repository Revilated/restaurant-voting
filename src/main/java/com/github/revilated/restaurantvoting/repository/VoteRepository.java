/*
 * 2022 https://github.com/revilated
 */
package com.github.revilated.restaurantvoting.repository;

import com.github.revilated.restaurantvoting.error.*;
import com.github.revilated.restaurantvoting.model.*;
import org.springframework.transaction.annotation.*;

import java.time.*;
import java.util.*;

/**
 * @author revilated
 */
@Transactional(readOnly = true)
public interface VoteRepository extends BaseRepository<Vote> {

    Optional<Vote> findByUserIdAndCreatedDate(int userId, LocalDate createdDate);

    int countAllByRestaurantIdAndCreatedDate(int restaurantId, LocalDate createdDate);

    default Vote getExistedByUserPerDate(int userId, LocalDate date) {
        return findByUserIdAndCreatedDate(userId, date)
                .orElseThrow(() -> new IllegalRequestDataException("Vote not found for userId=" + userId));
    }
}

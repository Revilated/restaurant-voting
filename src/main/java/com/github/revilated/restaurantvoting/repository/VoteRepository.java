/*
 * 2022 https://github.com/revilated
 */
package com.github.revilated.restaurantvoting.repository;

import com.github.revilated.restaurantvoting.model.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.transaction.annotation.*;

import java.time.*;
import java.util.*;

/**
 * @author revilated
 */
@Transactional(readOnly = true)
public interface VoteRepository extends BaseRepository<Vote> {

    @Query("SELECT v FROM Vote v WHERE v.userId = :userId AND v.createdDate >= :startDate AND v.createdDate < :endDate")
    Optional<Vote> findBetweenDateHalfOpen(int userId, LocalDate startDate, LocalDate endDate);

    default Optional<Vote> findByUserPerToday(int userId) {
        var now = LocalDate.now();
        return findBetweenDateHalfOpen(userId, now, now.plusDays(1));
    }
}

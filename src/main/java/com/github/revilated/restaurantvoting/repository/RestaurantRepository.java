/*
 * 2022 https://github.com/revilated
 */
package com.github.revilated.restaurantvoting.repository;

import com.github.revilated.restaurantvoting.model.*;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.*;
import org.springframework.transaction.annotation.*;

import javax.persistence.*;
import java.time.*;
import java.util.*;

/**
 * @author revilated
 */
@Transactional(readOnly = true)
public interface RestaurantRepository extends BaseRepository<Restaurant> {

    @Query("SELECT r FROM Restaurant r LEFT JOIN FETCH r.menu as d WHERE r.id = :id AND d.createdDate = :date")
    Optional<Restaurant> findWithMenuByDate(int id, LocalDate date);

    @Query("SELECT DISTINCT r FROM Restaurant r LEFT JOIN FETCH r.menu as d WHERE d.createdDate = :date ORDER BY r.name ASC")
    @QueryHints(value = { @QueryHint(name = org.hibernate.jpa.QueryHints.HINT_PASS_DISTINCT_THROUGH, value = "false" )}, forCounting = false)
    List<Restaurant> findAllWithMenuByDate(LocalDate date);
}

/*
 * 2022 https://github.com/revilated
 */
package com.github.revilated.restaurantvoting.repository;

import com.github.revilated.restaurantvoting.model.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.transaction.annotation.*;

import java.util.*;

/**
 * @author revilated
 */
@Transactional(readOnly = true)
public interface RestaurantRepository extends BaseRepository<Restaurant> {

    @EntityGraph(attributePaths = {"menu"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT r FROM Restaurant r WHERE r.id=?1")
    Optional<Restaurant> findWithDailyMenu(int id);

    @EntityGraph(attributePaths = {"menu"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT r FROM Restaurant r ORDER BY r.name ASC")
    List<Restaurant> findAllWithDailyMenu();

    // https://stackoverflow.com/questions/65757777/how-to-fetch-multiple-lists-with-entitygraph
    @EntityGraph(attributePaths = {"votes"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT r FROM Restaurant r WHERE r in (:restaurants) ORDER BY r.name ASC")
    List<Restaurant> reloadWithDailyVotes(List<Restaurant> restaurants);

    default List<Restaurant> findAllWithDailyVotesAndMenu() {
        return reloadWithDailyVotes(findAllWithDailyMenu());
    }
}

/*
 * 2022 https://github.com/revilated
 */
package com.github.revilated.restaurantvoting.service;

import com.github.revilated.restaurantvoting.error.*;
import com.github.revilated.restaurantvoting.model.*;
import com.github.revilated.restaurantvoting.repository.*;
import lombok.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

import java.time.*;

/**
 * @author revilated
 */
@Service
@AllArgsConstructor
public class VotingService {

    public static final LocalTime MAX_VOTE_TIME = LocalTime.of(10, 59);

    private final VoteRepository voteRepository;
    private final Clock clock;

    @Transactional
    public void vote(int userId, int restaurantId) {
        if (LocalTime.now(clock).isAfter(MAX_VOTE_TIME)) {
            throw new DataConflictException("Too late to vote");
        }
        voteRepository.findByUserPerToday(userId).ifPresentOrElse(
                v -> v.setRestaurantId(restaurantId),
                () -> {
                    var newVote = new Vote(userId, restaurantId);
                    voteRepository.save(newVote);
                });
    }
}

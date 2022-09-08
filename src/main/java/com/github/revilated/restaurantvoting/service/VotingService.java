/*
 * 2022 https://github.com/revilated
 */
package com.github.revilated.restaurantvoting.service;

import com.github.revilated.restaurantvoting.error.*;
import com.github.revilated.restaurantvoting.model.*;
import com.github.revilated.restaurantvoting.repository.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

import java.time.*;

/**
 * @author revilated
 */
@Service
public class VotingService {

    private final VoteRepository voteRepository;
    private final Clock clock;
    private final LocalTime maxVoteTime;

    public VotingService(VoteRepository voteRepository, Clock clock, @Value("${app.maxVoteTime}") LocalTime maxVoteTime) {
        this.voteRepository = voteRepository;
        this.clock = clock;
        this.maxVoteTime = maxVoteTime;
    }

    @Transactional
    public void vote(int userId, int restaurantId) {
        if (LocalTime.now(clock).isAfter(maxVoteTime)) {
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

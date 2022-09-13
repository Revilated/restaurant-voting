/*
 * 2022 https://github.com/revilated
 */
package com.github.revilated.restaurantvoting.web.vote;

import com.github.revilated.restaurantvoting.repository.*;
import com.github.revilated.restaurantvoting.service.*;
import org.springframework.context.annotation.*;

import java.time.*;

/**
 * @author revilated
 */
public abstract class AbstractTestVotingServiceConfig {

    private static final LocalTime MAX_VOTE_TIME = LocalTime.parse("11:00");

    protected abstract Duration maxVoteTimeDiff();

    @Bean
    @Primary
    VotingService testVotingService(VoteRepository voteRepository) {
        var instant = LocalDate.now()
                .atTime(MAX_VOTE_TIME.plus(maxVoteTimeDiff()))
                .atZone(ZoneId.systemDefault())
                .toInstant();
        var clock = Clock.fixed(instant, ZoneId.systemDefault());
        return new VotingService(voteRepository, clock, MAX_VOTE_TIME);
    }
}

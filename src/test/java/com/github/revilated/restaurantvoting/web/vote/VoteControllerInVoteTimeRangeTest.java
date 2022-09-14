package com.github.revilated.restaurantvoting.web.vote;

import com.github.revilated.restaurantvoting.repository.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.http.*;
import org.springframework.security.test.context.support.*;
import org.springframework.test.web.servlet.request.*;

import java.time.*;

import static com.github.revilated.restaurantvoting.web.UserTestData.*;
import static com.github.revilated.restaurantvoting.web.restaurant.RestaurantTestData.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class VoteControllerInVoteTimeRangeTest extends AbstractVoteControllerTest {

    @Autowired
    private VoteRepository voteRepository;

    @Test
    @WithUserDetails(NO_VOTES_USER_MAIL)
    void vote() throws Exception {
        perform(MockMvcRequestBuilders.post(REST_URL + "?restaurantId=" + RESTAURANT1_ID)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
        var vote = voteRepository.findByUserIdAndCreatedDate(NO_VOTES_USER_ID, LocalDate.now());
        assertTrue(vote.isPresent());
        assertEquals(RESTAURANT1_ID, vote.get().getRestaurantId());
    }

    @Test
    @WithUserDetails(USER1_MAIL)
    void changeVote() throws Exception {
        perform(MockMvcRequestBuilders.put(REST_URL + "?restaurantId=" + RESTAURANT2_ID)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
        var vote = voteRepository.findByUserIdAndCreatedDate(USER1_ID, LocalDate.now());
        assertTrue(vote.isPresent());
        assertEquals(RESTAURANT2_ID, vote.get().getRestaurantId());
    }

    @Test
    @WithUserDetails(USER1_MAIL)
    void sameVote() {
        assertThrows(Exception.class, () ->
                perform(MockMvcRequestBuilders.post(REST_URL + "?restaurantId=" + RESTAURANT1_ID)
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isNoContent())
        );
    }

    @TestConfiguration
    public static class Config extends AbstractTestVotingServiceConfig {

        @Override
        protected Duration maxVoteTimeDiff() {
            return Duration.ofMinutes(-30);
        }
    }
}
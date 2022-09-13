package com.github.revilated.restaurantvoting.web.vote;

import com.github.revilated.restaurantvoting.web.*;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.*;
import org.springframework.http.*;
import org.springframework.security.test.context.support.*;
import org.springframework.test.web.servlet.request.*;

import java.time.*;

import static com.github.revilated.restaurantvoting.web.UserTestData.*;
import static com.github.revilated.restaurantvoting.web.restaurant.RestaurantTestData.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class VoteControllerOutOfVoteTimeRangeTest extends AbstractControllerTest {

    private static final String REST_URL = VoteController.REST_URL;

    @Test
    @WithUserDetails(NO_VOTES_USER_MAIL)
    void vote() {
        assertDoesNotThrow(() ->
                perform(MockMvcRequestBuilders.post(REST_URL, RESTAURANT1_ID)
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isNoContent())
        );
    }

    @Test
    @WithUserDetails(USER1_MAIL)
    void changeVote() {
        assertThrows(Exception.class, () ->
                perform(MockMvcRequestBuilders.put(REST_URL, RESTAURANT2_ID)
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isNoContent())
        );
    }

    @TestConfiguration
    public static class Config extends AbstractTestVotingServiceConfig {

        @Override
        protected Duration maxVoteTimeDiff() {
            return Duration.ofMinutes(30);
        }
    }
}
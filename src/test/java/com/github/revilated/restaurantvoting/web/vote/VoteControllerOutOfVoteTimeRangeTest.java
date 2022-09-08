package com.github.revilated.restaurantvoting.web.vote;

import com.github.revilated.restaurantvoting.service.*;
import com.github.revilated.restaurantvoting.web.*;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.*;
import org.springframework.context.annotation.*;
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
    @WithUserDetails(USER1_MAIL)
    void voteOutOfTime() {
        assertThrows(Exception.class, () ->
                perform(MockMvcRequestBuilders.post(REST_URL, RESTAURANT2_ID)
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isNoContent())
        );
    }

    @TestConfiguration
    public static class Config {

        @Bean
        @Primary
        Clock testClock() {
            var instant = LocalDate.now()
                    .atTime(VotingService.MAX_VOTE_TIME)
                    .plusMinutes(30)
                    .atZone(ZoneId.systemDefault())
                    .toInstant();
            return Clock.fixed(instant, ZoneId.systemDefault());
        }
    }
}
package com.github.revilated.restaurantvoting.web.vote;

import com.github.revilated.restaurantvoting.model.*;
import com.github.revilated.restaurantvoting.web.*;

import java.time.*;
import java.util.*;

public class VoteTestData {
    public static final MatcherFactory.Matcher<Vote> VOTE_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Vote.class);

    public static final int VOTE1_ID = 1;
    public static final Vote vote1 = new Vote(VOTE1_ID, LocalDate.parse("2022-08-31"), 1, 1);
    public static final Vote vote2 = new Vote(VOTE1_ID + 1, LocalDate.parse("2022-08-31"), 2, 2);
    public static final Vote vote3 = new Vote(VOTE1_ID + 2, LocalDate.now(), 1, 1);
    public static final Vote vote4 = new Vote(VOTE1_ID + 3, LocalDate.now(), 2, 1);
    public static final Vote vote5 = new Vote(VOTE1_ID + 4, LocalDate.now(), 3, 3);
    public static final List<Vote> restaurant1DailyVotes = List.of(vote3, vote4);
    public static final List<Vote> restaurant2DailyVotes = List.of();
    public static final List<Vote> restaurant3DailyVotes = List.of(vote5);

    /*public static Dish getNew() {
        return new Dish(null, "Мороженое", 7000L, LocalDate.now(), 1);
    }

    public static Dish getUpdated() {
        return new Dish(VOTE1_ID, "Картофель по-деревенски", 6000L, dish5.getCreatedDate(), 1);
    }*/
}

package com.github.revilated.restaurantvoting.web.restaurant;

import com.github.revilated.restaurantvoting.model.*;
import com.github.revilated.restaurantvoting.to.*;
import com.github.revilated.restaurantvoting.web.*;
import com.github.revilated.restaurantvoting.web.menu.*;
import com.github.revilated.restaurantvoting.web.vote.*;

import java.util.*;

import static org.assertj.core.api.Assertions.*;

public class RestaurantTestData {
    public static final MatcherFactory.Matcher<Restaurant> RESTAURANT_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Restaurant.class, "votes", "menu");
    public static MatcherFactory.Matcher<RestaurantTo> RESTAURANT_TO_MATCHER = MatcherFactory.usingEqualsComparator(RestaurantTo.class);
    public static MatcherFactory.Matcher<Restaurant> RESTAURANT_WITH_MENU_MATCHER =
            MatcherFactory.usingAssertions(Restaurant.class,
                    (a, e) -> assertThat(a).usingRecursiveComparison()
                            .ignoringFields("votes", "menu.restaurantId").isEqualTo(e),
                    (a, e) -> {
                        throw new UnsupportedOperationException();
                    });

    public static final int RESTAURANT1_ID = 1;
    public static final int RESTAURANT2_ID = 2;
    public static final Restaurant restaurant1 = new Restaurant(RESTAURANT1_ID, "Макдональдс");
    public static final Restaurant restaurant2 = new Restaurant(RESTAURANT2_ID, "Бургер Кинг");
    public static final Restaurant restaurant3 = new Restaurant(RESTAURANT1_ID + 2, "KFC");

    public static final List<Restaurant> restaurants = List.of(restaurant3, restaurant2, restaurant1);

    static {
        restaurant1.setMenu(MenuTestData.restaurant1DailyMenu);
        restaurant1.setVotes(VoteTestData.restaurant1DailyVotes);
        restaurant2.setVotes(VoteTestData.restaurant2DailyVotes);
        restaurant3.setVotes(VoteTestData.restaurant3DailyVotes);
    }

    public static Restaurant getNew() {
        return new Restaurant(null, "Subway");
    }

    public static Restaurant getUpdated() {
        return new Restaurant(RESTAURANT1_ID, "Вкусно и точка");
    }
}

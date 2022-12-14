/*
 * 2022 https://github.com/revilated
 */
package com.github.revilated.restaurantvoting.util;

import com.github.revilated.restaurantvoting.model.*;
import com.github.revilated.restaurantvoting.to.*;
import lombok.experimental.*;

import java.util.*;

/**
 * @author revilated
 */
@UtilityClass
public class RestaurantUtil {

    public static RestaurantTo toTo(Restaurant restaurant, int votes) {
        return new RestaurantTo(restaurant.getId(), restaurant.getName(), votes, restaurant.getMenu());
    }

    public static List<RestaurantTo> toTos(List<Restaurant> restaurants) {
        return restaurants.stream()
                .map(r -> new RestaurantTo(r.getId(), r.getName(), r.getVotes().size(), r.getMenu()))
                .toList();
    }
}

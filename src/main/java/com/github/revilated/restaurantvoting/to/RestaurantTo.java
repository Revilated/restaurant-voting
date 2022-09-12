/*
 * 2022 https://github.com/revilated
 */
package com.github.revilated.restaurantvoting.to;

import com.github.revilated.restaurantvoting.model.*;
import lombok.*;

import java.util.*;

/**
 * @author revilated
 */
@Value
@EqualsAndHashCode(callSuper = true)
public class RestaurantTo extends NamedTo {

    int votes;

    List<Dish> menu;

    public RestaurantTo(Integer id, String name, int votes, List<Dish> menu) {
        super(id, name);
        this.votes = votes;
        this.menu = menu;
    }
}

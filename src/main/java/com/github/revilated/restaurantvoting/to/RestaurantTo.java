/*
 * 2022 https://github.com/revilated
 */
package com.github.revilated.restaurantvoting.to;

import lombok.*;

/**
 * @author revilated
 */
@Value
@EqualsAndHashCode(callSuper = true)
public class RestaurantTo extends NamedTo {

    int votes;

    public RestaurantTo(Integer id, String name, int votes) {
        super(id, name);
        this.votes = votes;
    }
}

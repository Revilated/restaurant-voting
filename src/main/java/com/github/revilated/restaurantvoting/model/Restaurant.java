/*
 * 2022 https://github.com/revilated
 */
package com.github.revilated.restaurantvoting.model;

import io.swagger.v3.oas.annotations.media.*;
import lombok.*;
import org.hibernate.annotations.*;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.*;
import java.util.*;

/**
 * @author revilated
 */
@Entity
@Table(
        name = "restaurant",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"name"}, name = "restaurant_unique_name_idx")}
)
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Restaurant extends NamedEntity {

    @OneToMany(fetch = FetchType.LAZY)//, cascade = CascadeType.REMOVE, orphanRemoval = true)
    @JoinColumn(name = "restaurant_id", updatable = false)
    @OnDelete(action = OnDeleteAction.CASCADE) //https://stackoverflow.com/a/44988100/548473
    @Schema(hidden = true)
    private Set<Vote> votes;

    @OneToMany(fetch = FetchType.LAZY)//, mappedBy = "restaurant")//, cascade = CascadeType.REMOVE, orphanRemoval = true)
    @JoinColumn(name = "restaurant_id", updatable = false)
    @OnDelete(action = OnDeleteAction.CASCADE) //https://stackoverflow.com/a/44988100/548473
    @Schema(hidden = true)
    private List<Dish> menu;

    public Restaurant(Integer id, String name) {
        super(id, name);
    }
}

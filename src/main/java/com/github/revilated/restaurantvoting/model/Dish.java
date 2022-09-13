/*
 * 2022 https://github.com/revilated
 */
package com.github.revilated.restaurantvoting.model;

import com.fasterxml.jackson.annotation.*;
import lombok.*;
import org.hibernate.validator.constraints.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.*;

/**
 * @author revilated
 */
@Entity
@Table(
        name = "dish",
        uniqueConstraints = {@UniqueConstraint(
                columnNames = {"restaurant_id", "name", "created_date"},
                name = "dish_unique_restaurant_name_created_idx")
        }
)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class Dish extends NamedEntity {

    @Column(name = "price", nullable = false)
    @Range(min = 0)
    private Long price;

    @Column(name = "created_date", nullable = false, columnDefinition = "date default now()", updatable = false)
    @NotNull
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDate createdDate = LocalDate.now();

    @Column(name = "restaurant_id", nullable = false, updatable = false)
    @JsonIgnore
    private int restaurantId;

    public Dish(Integer id, String name, Long price, LocalDate createdDate, int restaurantId) {
        super(id, name);
        this.price = price;
        this.createdDate = createdDate;
        this.restaurantId = restaurantId;
    }

    public Dish(Integer id, String name, Long price, int restaurantId) {
        super(id, name);
        this.price = price;
        this.restaurantId = restaurantId;
    }
}

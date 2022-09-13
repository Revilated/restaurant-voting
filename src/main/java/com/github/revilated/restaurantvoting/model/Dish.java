/*
 * 2022 https://github.com/revilated
 */
package com.github.revilated.restaurantvoting.model;

import com.fasterxml.jackson.annotation.*;
import com.github.revilated.restaurantvoting.util.validation.*;
import lombok.*;
import org.hibernate.validator.constraints.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
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
                name = "dish_unique_restaurant_date_name_idx")
        }
)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class Dish extends BaseEntity {

    @NotBlank
    @Size(min = 2, max = 255)
    @Column(name = "name", nullable = false)
    @NoHtml
    private String name;

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
        super(id);
        this.name = name;
        this.price = price;
        this.createdDate = createdDate;
        this.restaurantId = restaurantId;
    }

    public Dish(Integer id, String name, Long price, int restaurantId) {
        super(id);
        this.name = name;
        this.price = price;
        this.restaurantId = restaurantId;
    }

    @Override
    public String toString() {
        return super.toString() + '[' + name + ']';
    }
}

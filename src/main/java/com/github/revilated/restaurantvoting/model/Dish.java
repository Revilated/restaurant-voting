/*
 * 2022 https://github.com/revilated
 */
package com.github.revilated.restaurantvoting.model;

import com.fasterxml.jackson.annotation.*;
import lombok.*;
import org.hibernate.validator.constraints.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.*;

/**
 * @author revilated
 */
@Entity
@Table(name = "dish",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"created", "restaurant_id", "name"},
                name = "dish_unique_created_restaurant_name_idx")
        })
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class Dish extends NamedEntity {

    @Column(name = "price", nullable = false)
    @Range(min = 0)
    private Long price;

    @Column(name = "created", nullable = false, columnDefinition = "date default now()", updatable = false)
    @NotNull
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Temporal(TemporalType.DATE)
    private Date createdDate = new Date();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @JsonIgnore
    private Restaurant restaurant;
}

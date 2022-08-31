/*
 * 2022 https://github.com/revilated
 */
package com.github.revilated.restaurantvoting.model;

import com.fasterxml.jackson.annotation.*;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.*;

/**
 * @author revilated
 */
@Entity
@Table(name = "vote", uniqueConstraints = {@UniqueConstraint(columnNames = {"created", "user_id", "restaurant_id"}, name = "vote_unique_created_user_restaurant_idx")})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Setter
@Getter
public class Vote extends BaseEntity {

    @Column(name = "created", nullable = false, columnDefinition = "date default now()", updatable = false)
    @NotNull
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Temporal(TemporalType.DATE)
    private Date createdDate = new Date();

    @Column(name = "user_id", nullable = false, updatable = false, insertable = false)
    private Integer userId;

    @Column(name = "restaurant_id", nullable = false, updatable = false, insertable = false)
    private Integer restaurantId;
}

/*
 * 2022 https://github.com/revilated
 */
package com.github.revilated.restaurantvoting.model;

import com.fasterxml.jackson.annotation.*;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.*;

/**
 * @author revilated
 */
@Entity
@Table(name = "vote", uniqueConstraints = {
        @UniqueConstraint(
                columnNames = {"user_id", "created_date"},
                name = "vote_unique_user_date_idx"
        )
})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Setter
@Getter
public class Vote extends BaseEntity {

    @Column(name = "created_date", nullable = false, columnDefinition = "date default now()", updatable = false)
    @NotNull
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDate createdDate = LocalDate.now();

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    @JsonIgnore
    private User user;

    @Column(name = "user_id", nullable = false, updatable = false)
    @JsonIgnore
    private Integer userId;

    @Column(name = "restaurant_id", nullable = false)
    private Integer restaurantId;

    public Vote(Integer userId, Integer restaurantId) {
        this.userId = userId;
        this.restaurantId = restaurantId;
    }

    public Vote(Integer id, LocalDate createdDate, Integer userId, Integer restaurantId) {
        super(id);
        this.createdDate = createdDate;
        this.userId = userId;
        this.restaurantId = restaurantId;
    }
}

package com.github.revilated.restaurantvoting;

import com.fasterxml.jackson.annotation.*;
import org.springframework.util.*;

public interface HasId {
    Integer getId();

    void setId(Integer id);

    @JsonIgnore
    default boolean isNew() {
        return getId() == null;
    }

    // doesn't work for hibernate lazy proxy
    default int id() {
        Assert.notNull(getId(), "Entity must has id");
        return getId();
    }
}

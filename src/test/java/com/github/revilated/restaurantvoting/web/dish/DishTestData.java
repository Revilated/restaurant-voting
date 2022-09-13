package com.github.revilated.restaurantvoting.web.dish;

import com.github.revilated.restaurantvoting.model.*;
import com.github.revilated.restaurantvoting.web.*;

import java.time.*;
import java.util.*;

public class DishTestData {
    public static final MatcherFactory.Matcher<Dish> DISH_MATCHER = MatcherFactory
            .usingIgnoringFieldsComparator(Dish.class, "restaurantId", "createdDate");

    public static final int DISH1_ID = 1;
    public static final int NOT_FOUND = 100;
    public static final int TODAY_RESTAURANT1_DISH1_ID = DISH1_ID + 3;
    public static final Dish dish1 = new Dish(DISH1_ID, "Двойной чизбургер", 15000L, LocalDate.parse("2022-08-31"), 1);
    public static final Dish dish2 = new Dish(DISH1_ID + 1, "Нагетсы", 13000L, LocalDate.parse("2022-08-31"), 1);
    public static final Dish dish3 = new Dish(DISH1_ID + 2, "Чай", 4000L, LocalDate.parse("2022-08-31"), 1);
    public static final Dish dish4 = new Dish(DISH1_ID + 3, "Биг тейсти", 20000L, LocalDate.now(), 1);
    public static final Dish dish5 = new Dish(DISH1_ID + 4, "Картофель фри", 5000L, LocalDate.now(), 1);
    public static final Dish dish6 = new Dish(DISH1_ID + 5, "Кола", 6000L, LocalDate.now(), 1);
    public static final Dish dish7 = new Dish(DISH1_ID + 6, "Двойной воппер", 30000L, LocalDate.now(), 2);
    public static final Dish dish8 = new Dish(DISH1_ID + 7, "Пепси", 7000L, LocalDate.now(), 2);
    public static final Dish dish9 = new Dish(DISH1_ID + 8, "Твистер", 10000L, LocalDate.now(), 3);
    public static final Dish dish10 = new Dish(DISH1_ID + 9, "Куриные крылья", 15000L, LocalDate.now(), 3);
    public static final Dish dish11 = new Dish(DISH1_ID + 10, "Картофель по-деревенски", 7000L, LocalDate.now(), 3);
    public static final Dish dish12 = new Dish(DISH1_ID + 11, "Спрайт", 6000L, LocalDate.now(), 3);
    public static final Dish todayRestaurant1Dish1 = dish4;
    public static final Dish todayRestaurant1Dish2 = dish5;

    public static final List<Dish> restaurant1DailyMenu = List.of(dish4, dish5, dish6);
    public static final List<Dish> restaurant2DailyMenu = List.of(dish7, dish8);
    public static final List<Dish> restaurant3DailyMenu = List.of(dish9, dish10, dish11, dish12);

    public static Dish getNew() {
        return new Dish(null, "Мороженое", 7000L, LocalDate.now(), 1);
    }

    public static Dish getUpdated() {
        return new Dish(DISH1_ID, "Картофель по-деревенски", 6000L, dish5.getCreatedDate(), 1);
    }
}

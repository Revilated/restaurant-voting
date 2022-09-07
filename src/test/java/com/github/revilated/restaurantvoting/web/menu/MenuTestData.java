package com.github.revilated.restaurantvoting.web.menu;

import com.github.revilated.restaurantvoting.model.*;
import com.github.revilated.restaurantvoting.web.*;

import java.time.*;
import java.util.*;

public class MenuTestData {
    public static final MatcherFactory.Matcher<Dish> MENU_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Dish.class);

    public static final int DISH1_ID = 1;
    public static final Dish dish1 = new Dish(DISH1_ID, "Двойной чизбургер", 15000L, LocalDate.parse("2022-08-31"), 1);
    public static final Dish dish2 = new Dish(DISH1_ID + 1, "Нагетсы", 13000L, LocalDate.parse("2022-08-31"), 1);
    public static final Dish dish3 = new Dish(DISH1_ID + 2, "Чай", 4000L, LocalDate.parse("2022-08-31"), 1);
    public static final Dish dish4 = new Dish(DISH1_ID + 3, "Биг тейсти", 20000L, LocalDate.now(), 1);
    public static final Dish dish5 = new Dish(DISH1_ID + 4, "Картофель фри", 5000L, LocalDate.now(), 1);
    public static final Dish dish6 = new Dish(DISH1_ID + 5, "Кола", 6000L, LocalDate.now(), 1);

    public static final List<Dish> restaurant1DailyMenu = List.of(dish4, dish5, dish6);

    public static Dish getNew() {
        return new Dish(null, "Мороженое", 7000L, LocalDate.now(), 1);
    }

    public static Dish getUpdated() {
        return new Dish(DISH1_ID, "Картофель по-деревенски", 6000L, dish5.getCreatedDate(), 1);
    }
}
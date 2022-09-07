package com.github.revilated.restaurantvoting.web;

import com.github.revilated.restaurantvoting.model.*;

public class UserTestData {
    public static final MatcherFactory.Matcher<User> USER_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(User.class, "registered", "password");

    public static final int USER1_ID = 1;
    public static final int USER2_ID = 2;
    public static final int ADMIN_ID = 3;
    public static final int NOT_FOUND = 100;
    public static final String USER1_MAIL = "user1@yandex.ru";
    public static final String USER2_MAIL = "user2@yandex.ru";
    public static final String ADMIN_MAIL = "admin@gmail.com";

    public static final User user1 = new User(USER1_ID, "User1", USER1_MAIL, "user1", Role.USER);
    public static final User user2 = new User(USER2_ID, "User2", USER2_MAIL, "user2");
    public static final User admin = new User(ADMIN_ID, "Admin", ADMIN_MAIL, "admin", Role.ADMIN, Role.USER);

    /*public static User getNew() {
        return new User(null, "New", "new@gmail.com", "newPass", 1555, false, new Date(), Collections.singleton(Role.USER));
    }

    public static User getUpdated() {
        return new User(USER1_ID, "UpdatedName", USER1_MAIL, "newPass", 330, false, new Date(), List.of(Role.ADMIN));
    }

    public static String jsonWithPassword(User user, String passw) {
        return JsonUtil.writeAdditionProps(user, "password", passw);
    }*/
}

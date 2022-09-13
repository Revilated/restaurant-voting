package com.github.revilated.restaurantvoting.web.dish;

import com.github.revilated.restaurantvoting.model.*;
import com.github.revilated.restaurantvoting.repository.*;
import com.github.revilated.restaurantvoting.web.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.security.test.context.support.*;
import org.springframework.test.web.servlet.*;
import org.springframework.test.web.servlet.request.*;
import org.springframework.transaction.annotation.*;

import static com.github.revilated.restaurantvoting.util.JsonUtil.*;
import static com.github.revilated.restaurantvoting.web.UserTestData.*;
import static com.github.revilated.restaurantvoting.web.dish.DishTestData.NOT_FOUND;
import static com.github.revilated.restaurantvoting.web.dish.DishTestData.getNew;
import static com.github.revilated.restaurantvoting.web.dish.DishTestData.getUpdated;
import static com.github.revilated.restaurantvoting.web.dish.DishTestData.*;
import static com.github.revilated.restaurantvoting.web.restaurant.RestaurantTestData.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class AdminDishControllerTest extends AbstractControllerTest {

    private static final String REST_URL = AdminDishController.REST_URL + '/';

    @Autowired
    private DishRepository dishRepository;

    @Test
    @WithUserDetails(ADMIN_MAIL)
    void get() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + DISH1_ID, RESTAURANT1_ID))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(DISH_MATCHER.contentJson(dish1));
    }

    @Test
    @WithUserDetails(ADMIN_MAIL)
    void delete() throws Exception {
        perform(MockMvcRequestBuilders.delete(REST_URL + DISH1_ID, RESTAURANT1_ID))
                .andDo(print())
                .andExpect(status().isNoContent());
        assertFalse(dishRepository.findByRestaurantIdAndId(RESTAURANT1_ID, DISH1_ID).isPresent());
    }

    @Test
    @WithUserDetails(ADMIN_MAIL)
    void createWithLocation() throws Exception {
        Dish newDish = getNew();
        ResultActions action = perform(MockMvcRequestBuilders.post(REST_URL, RESTAURANT1_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(writeValue(newDish)))
                .andExpect(status().isCreated());
        Dish created = DISH_MATCHER.readFromJson(action);
        int newId = created.id();
        newDish.setId(newId);
        DISH_MATCHER.assertMatch(created, newDish);
        DISH_MATCHER.assertMatch(dishRepository.getExisted(newId), newDish);
    }

    @Test
    @WithUserDetails(ADMIN_MAIL)
    void update() throws Exception {
        Dish updated = getUpdated();
        updated.setId(null);
        perform(MockMvcRequestBuilders.put(REST_URL + DISH1_ID, RESTAURANT1_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(writeValue(updated)))
                .andDo(print())
                .andExpect(status().isNoContent());
        DISH_MATCHER.assertMatch(dishRepository.getExisted(DISH1_ID), getUpdated());
    }

    @Test
    @WithUserDetails(ADMIN_MAIL)
    void getNotFound() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + NOT_FOUND, RESTAURANT1_ID))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void deleteNotFound() {
        assertThrows(Exception.class, () ->
                perform(MockMvcRequestBuilders.delete(REST_URL + NOT_FOUND, RESTAURANT1_ID))
                        .andDo(print())
        );

    }

    @Test
    void getUnAuth() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + DISH1_ID, RESTAURANT1_ID))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithUserDetails(USER1_MAIL)
    void getForbidden() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + DISH1_ID, RESTAURANT1_ID))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails(ADMIN_MAIL)
    void createInvalid() throws Exception {
        Dish invalid = new Dish(null, "", 0L, RESTAURANT1_ID);
        perform(MockMvcRequestBuilders.post(REST_URL, RESTAURANT1_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(writeValue(invalid)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @WithUserDetails(ADMIN_MAIL)
    void updateInvalid() throws Exception {
        Dish invalid = new Dish(DISH1_ID, "", 0L, RESTAURANT1_ID);
        perform(MockMvcRequestBuilders.put(REST_URL + DISH1_ID, RESTAURANT1_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(writeValue(invalid)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @WithUserDetails(ADMIN_MAIL)
    void updateHtmlUnsafe() throws Exception {
        Dish updated = new Dish(DISH1_ID, "<script>alert(123)</script>", dish1.getPrice(), dish1.getRestaurantId());
        perform(MockMvcRequestBuilders.put(REST_URL + DISH1_ID, RESTAURANT1_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(writeValue(updated)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @Transactional(propagation = Propagation.NEVER)
    @WithUserDetails(ADMIN_MAIL)
    void updateDuplicate() {
        Dish updated = new Dish(TODAY_RESTAURANT1_DISH1_ID, todayRestaurant1Dish2.getName(),
                todayRestaurant1Dish1.getPrice(), RESTAURANT1_ID);
        assertThrows(Exception.class, () ->
                perform(MockMvcRequestBuilders.put(REST_URL + TODAY_RESTAURANT1_DISH1_ID, RESTAURANT1_ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(writeValue(updated)))
                        .andDo(print()));
    }

    @Test
    @Transactional(propagation = Propagation.NEVER)
    @WithUserDetails(ADMIN_MAIL)
    void createDuplicate() {
        Dish invalid = new Dish(null, todayRestaurant1Dish1.getName(), 1000L, RESTAURANT1_ID);
        assertThrows(Exception.class, () ->
                perform(MockMvcRequestBuilders.post(REST_URL, RESTAURANT1_ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(writeValue(invalid)))
                        .andDo(print())
        );
    }
}
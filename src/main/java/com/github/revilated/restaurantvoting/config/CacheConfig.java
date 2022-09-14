/*
 * 2022 https://github.com/revilated
 */
package com.github.revilated.restaurantvoting.config;

import com.github.benmanes.caffeine.cache.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.cache.annotation.*;
import org.springframework.cache.caffeine.*;
import org.springframework.context.annotation.*;

import java.time.*;

/**
 * @author revilated
 */
@Configuration
@EnableCaching
public class CacheConfig {

    @Autowired(required = false)
    public void configureCacheManager(CaffeineCacheManager cacheManager) {
        cacheManager.registerCustomCache("restaurants", Caffeine.newBuilder()
                .expireAfterWrite(Duration.ofSeconds(20))
                .build());
    }
}

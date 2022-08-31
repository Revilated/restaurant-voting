package com.github.revilated.restaurantvoting.config;

import lombok.extern.slf4j.*;
import org.h2.tools.*;
import org.springframework.cache.annotation.*;
import org.springframework.context.annotation.*;

import java.sql.*;

@Configuration
@Slf4j
@EnableCaching
// TODO: cache only most requested data!
public class AppConfig {

    @Profile("!test")
    @Bean(initMethod = "start", destroyMethod = "stop")
    Server h2Server() throws SQLException {
        log.info("Start H2 TCP server");
        return Server.createTcpServer("-tcp", "-tcpAllowOthers", "-tcpPort", "9092");
    }

    /*@Autowired
    void configureAndStoreObjectMapper(ObjectMapper objectMapper) {
        objectMapper.registerModule(new Hibernate5Module());
        JsonUtil.setMapper(objectMapper);
    }*/
}

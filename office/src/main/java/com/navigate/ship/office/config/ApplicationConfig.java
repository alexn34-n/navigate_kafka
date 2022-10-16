package com.navigate.ship.office.config;


import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.navigate.ship.common.processor.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.http.WebSocket;
import java.util.concurrent.TimeUnit;

@Configuration
public class ApplicationConfig {

    @Bean
    public MessageConverter converter(){
        return new MessageConverter();
    }

    @Bean
    public Cache<String, WebSocket> sessionCache(){
        return Caffeine.newBuilder()
                .expireAfterWrite(20, TimeUnit.MINUTES)
                .build();
    }
}

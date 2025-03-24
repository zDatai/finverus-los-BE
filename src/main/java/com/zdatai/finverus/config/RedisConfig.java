package com.zdatai.finverus.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedisConfig {

    @Bean
    public CacheConfig buiCacheConfig() {
        return CacheConfig.builder()
                .build();
    }

}

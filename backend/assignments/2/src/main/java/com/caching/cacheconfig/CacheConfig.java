package com.caching.cacheconfig;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

/**
 * Configuration class for setting up caching using Caffeine in a Spring application.
 */
@Configuration
@EnableCaching
@ComponentScan("com.caching")
public class CacheConfig {
    /**
     * Configures and provides a Caffeine object for caching.
     * @return Caffeine Object
     */
    @Bean
    public Caffeine<Object,Object> caffeineConfig() {
        return Caffeine.newBuilder()
                .maximumSize(10)
                .expireAfterWrite(10, TimeUnit.MINUTES);
    }

    /**
     *  Configures and provides a CacheManager using Caffeine for managing caches in the application.
     * @param caffeine Caffeine Object
     * @return CacheManager Object
     */
    @Bean
    public CacheManager cacheManager(Caffeine<Object,Object> caffeine) {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager();
        cacheManager.setCaffeine(caffeine);
        return cacheManager;
    }
}

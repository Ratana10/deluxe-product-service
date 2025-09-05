package com.deluxe.product_service.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;

@Configuration
@EnableCaching
public class RedisConfig {
    @Value("${spring.application.name}")
    private String appName;

    @Bean
    public CacheManager cacheManager(RedisConnectionFactory cf) {
        var keySer = new StringRedisSerializer();
        var valSer = new GenericJackson2JsonRedisSerializer();

        var base = RedisCacheConfiguration.defaultCacheConfig()
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(keySer))
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(valSer))
                .disableCachingNullValues()
                .prefixCacheNameWith(appName+"::")
                .entryTtl(Duration.ofMinutes(10));

        return RedisCacheManager.builder(cf).cacheDefaults(base).build();
    }
}
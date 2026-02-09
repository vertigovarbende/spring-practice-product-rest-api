package com.deveyk.northwind.common.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;

/**
 * Configuration class for setting up Redis-related beans.
 * This class defines configurations for RedisTemplate and CacheManager
 * to enable Redis integration in the application.
 */
@Configuration
@Profile("dev")
public class RedisConfig {

    /**
     * Configures and provides a RedisTemplate bean for interacting with Redis.
     * The RedisTemplate is configured to use JSON serialization for values
     * and String serialization for keys, making it suitable for handling complex objects.
     *
     * @param redisConnectionFactory the factory used to create connections to the Redis instance
     * @return a configured instance of RedisTemplate for Redis interactions
     */
    @Bean("redisTemplate")
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);

        // JSON Serialization
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
        ObjectMapper objectMapper = createObjectMapper();
        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);

        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();

        // set key serialization
        redisTemplate.setKeySerializer(stringRedisSerializer);
        redisTemplate.setHashKeySerializer(stringRedisSerializer);

        // set value serialization
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
        redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);

        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

    /**
     * Configures and provides a RedisCacheManager bean for managing Redis caches.
     * This method sets up cache defaults such as TTL, value serialization, key serialization,
     * and disables caching of null values.
     *
     * @param redisConnectionFactory the connection factory used to create connections to the Redis instance
     * @return a configured instance of RedisCacheManager for managing caches in Redis
     */
    @Bean("redisCacheManager")
    public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
        RedisCacheConfiguration defaultConfig = RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofMinutes(10))
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(jackson2JsonRedisSerializer()))
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))
                .disableCachingNullValues();

        return RedisCacheManager.builder(redisConnectionFactory)
                .cacheDefaults(defaultConfig)
                .build();
    }

    /**
     * Creates and configures a Jackson2JsonRedisSerializer for serializing and deserializing objects to and from JSON.
     * This serializer is designed to work with Redis by converting objects into JSON representation using a customized ObjectMapper.
     * The configured ObjectMapper enables handling of complex Java types and provides enhanced serialization capabilities.
     *
     * @return a configured instance of Jackson2JsonRedisSerializer for handling object serialization and deserialization
     */
    private Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer() {
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
        jackson2JsonRedisSerializer.setObjectMapper(createObjectMapper());
        return jackson2JsonRedisSerializer;
    }

    /**
     * Creates and configures an instance of ObjectMapper for JSON serialization and deserialization.
     * This method customizes the ObjectMapper to enhance its capabilities for handling complex types,
     * including default typing for non-final classes, visibility rules, and Java 8 time module support.
     *
     * @return a customized ObjectMapper instance
     */
    private ObjectMapper createObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        objectMapper.activateDefaultTyping(LaissezFaireSubTypeValidator.instance,
                ObjectMapper.DefaultTyping.NON_FINAL);
        objectMapper.registerModule(new JavaTimeModule());
        return objectMapper;
    }
}
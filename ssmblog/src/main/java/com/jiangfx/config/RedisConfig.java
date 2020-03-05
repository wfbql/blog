package com.jiangfx.config;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: 姜飞祥
 * @Description:
 * @Date: Create in 2018/10/6 9:35
 * @param: $params$
 * @return: $returns$
 */
@Configuration
@EnableCaching
public class RedisConfig {

    @Bean(name = "redisTemplate")
    public RedisTemplate initRedisTemplate(){
        //Jedis连接池
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        //最大空闲数
        poolConfig.setMaxIdle(50);
        //最大连接数
        poolConfig.setMaxTotal(100);
        //最大等待毫秒数
        poolConfig.setMaxWaitMillis(20000);
        //创建Jedis连接工厂
        JedisConnectionFactory connectionFactory = new JedisConnectionFactory(poolConfig);
        connectionFactory.setHostName("");
        connectionFactory.setPort(6379);
        //调用后初始化方法，没有它将抛出异常
        connectionFactory.afterPropertiesSet();
        //自定义序列化器
        RedisSerializer jdkSerializationRedisSerializer = new JdkSerializationRedisSerializer();
        RedisSerializer stringRedisSerializer = new StringRedisSerializer();
        //定义RedisTemplate,并连接工程
        RedisTemplate redisTemplate = new RedisTemplate();
        redisTemplate.setConnectionFactory(connectionFactory);
        //设置序列化器
        redisTemplate.setDefaultSerializer(stringRedisSerializer);
        redisTemplate.setKeySerializer(stringRedisSerializer);
        redisTemplate.setValueSerializer(jdkSerializationRedisSerializer);
        redisTemplate.setHashKeySerializer(stringRedisSerializer);
        redisTemplate.setHashValueSerializer(jdkSerializationRedisSerializer);
        return redisTemplate;
    }

    @Bean(name = "redisCacheManager")
    public Cacheable initRedisCacheManager(RedisTemplate redisTemplate){
        RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);
        //设置超时时间10分钟，单位秒
        cacheManager.setDefaultExpiration(600);
                //设置缓存名称
        List<String> cacheNames = new ArrayList();
        cacheNames.add("redisCacheManager");
        cacheManager.setCacheNames(cacheNames);
        return (Cacheable) cacheManager;
    }
}

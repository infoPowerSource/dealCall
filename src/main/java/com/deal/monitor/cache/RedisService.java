package com.deal.monitor.cache;

import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.core.RedisTemplate;

import com.deal.util.SpringBeanUtil;

public class RedisService{
	// @Autowired
	// @Qualifier("redisTemplate")
	private static RedisTemplate<String, Object> redisTemplate = (RedisTemplate) SpringBeanUtil.getBeanByName("redisTemplate");
	private static final String SYSCACHE_NAME = "cacheSession";

	public static void putCache(String key, Object value){
		redisTemplate.opsForHash().put(key, key, value);
		redisTemplate.expire(key, 300, TimeUnit.MINUTES);
	}

	public static Object getCache(String key){
		return redisTemplate.opsForHash().get(key, key);
	}

	public static void removeCache(String key){
		redisTemplate.opsForHash().delete(key, key);
	}

	public static boolean isExist(String key){
		return redisTemplate.opsForHash().hasKey(key, key);
	}

	public static void putOperToCache(String key, Object value){
		redisTemplate.opsForSet().add(key, value);
		redisTemplate.expire(key, 3, TimeUnit.MINUTES);
	}

	public static boolean IsExistOper(String key, Object value){
		return redisTemplate.opsForSet().isMember(key, value);
	}

	public static void removeOperToCatch(String key, Object value){
		redisTemplate.opsForSet().remove(key, value);
	}
	
	public static void putOperToCheWithTime(String key, Object value,int times){
		redisTemplate.opsForSet().add(key, value);
		redisTemplate.expire(key, times, TimeUnit.SECONDS);
	}
	
	public static void putSessionToCache(String key, Object value){
		redisTemplate.opsForHash().put(SYSCACHE_NAME, key, value);
	}
	public static void removeSessionFromCache(String key){
		redisTemplate.opsForHash().delete(SYSCACHE_NAME, key);
	}
	public static Object getSessionFromCache(String key){
		return redisTemplate.opsForHash().get(SYSCACHE_NAME, key);
	}
	public static boolean isExistSession(String key){
		return redisTemplate.opsForHash().hasKey(SYSCACHE_NAME, key);
	}
}

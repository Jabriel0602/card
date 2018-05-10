package com.card.common.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author yangzhanbang
 * @date 2018/5/9 17:34
 * @desc
 */
@Service
public class RedisUtil {

	@Autowired
	private RedisTemplate redisTemplate;

	public RedisUtil(RedisTemplate redisTemplate) {
		this.redisTemplate = redisTemplate;
	}


	public void set(String key, Object value,Long second) {
		ValueOperations valueOperations = redisTemplate.opsForValue();
		valueOperations.set(key, value,second,TimeUnit.SECONDS);
		//BoundValueOperations的理解对保存的值做一些细微的操作
//        BoundValueOperations boundValueOperations = redisTemplate.boundValueOps(key);
	}

	public void set(String key, Object value) {
		ValueOperations valueOperations = redisTemplate.opsForValue();
		valueOperations.set(key, value);
	}

		public Object get(String key) {
		return redisTemplate.opsForValue().get(key);
	}

	public void setList(String key, List<?> value) {
		ListOperations listOperations = redisTemplate.opsForList();
		listOperations.leftPush(key, value);
	}

	public Object getList(String key) {
		return redisTemplate.opsForList().leftPop(key);
	}

	public void setSet(String key, Set<?> value) {
		SetOperations setOperations = redisTemplate.opsForSet();
		setOperations.add(key, value);
	}

	public Object getSet(String key) {
		return redisTemplate.opsForSet().members(key);
	}


	public void setHash(String key, Map<String, ?> value) {
		HashOperations hashOperations = redisTemplate.opsForHash();
		hashOperations.putAll(key, value);
	}

	public Object getHash(String key) {
		return redisTemplate.opsForHash().entries(key);
	}


	public void delete(String key) {
		redisTemplate.delete(key);
	}
}

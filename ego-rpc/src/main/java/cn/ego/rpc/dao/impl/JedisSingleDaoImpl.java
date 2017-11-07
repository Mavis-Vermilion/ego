package cn.ego.rpc.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;

import cn.ego.rpc.dao.JedisDao;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class JedisSingleDaoImpl implements JedisDao{

	@Autowired
	private JedisPool jedisPool;
	
	@Override
	public String set(String key, String value) {
		Jedis jedis = jedisPool.getResource();
		String val = jedis.set(key, value);
		jedis.close();
		return val;
	}

	@Override
	public String get(String key) {
		Jedis jedis = jedisPool.getResource();
		String val = jedis.get(key);
		jedis.close();
		return val;
	}

	@Override
	public Long hset(String key, String field, String value) {
		Jedis jedis = jedisPool.getResource();
		Long val = jedis.hset(key, field, value);
		jedis.close();
		return val;
	}

	@Override
	public String hget(String key, String field) {
		Jedis jedis = jedisPool.getResource();
		String val = jedis.hget(key, field);
		jedis.close();
		return val;
	}

	@Override
	public Long del(String key) {
		Jedis jedis = jedisPool.getResource();
		Long val = jedis.del(key);
		jedis.close();
		return val;
	}

	@Override
	public Long hdel(String key, String field) {
		Jedis jedis = jedisPool.getResource();
		Long val = jedis.hdel(key, field);
		jedis.close();
		return val;
	}

	@Override
	public Long expire(String key, int seconds) {
		Jedis jedis = jedisPool.getResource();
		Long val = jedis.expire(key, seconds);
		jedis.close();
		return val;
	}

	@Override
	public Long ttl(String key) {
		Jedis jedis = jedisPool.getResource();
		Long val = jedis.ttl(key);
		jedis.close();
		return val;
	}

}

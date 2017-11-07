package cn.ego.order.dao;

public interface JedisDao {

	public String set(String key, String value);

	public String get(String key);

	public Long hset(String key, String field, String value);

	public String hget(String key, String field);

	public Long del(String key);

	public Long hdel(String key, String field);

	public Long expire(String key, int seconds);

	public Long ttl(String key);

	public Long incr(String key);
}

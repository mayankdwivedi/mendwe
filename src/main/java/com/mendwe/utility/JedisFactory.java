package com.mendwe.utility;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisFactory {
	private static JedisPool jedisPool;
	private static JedisFactory instance;

	/*
	 * public JedisFactory() { JedisPoolConfig poolConfig = new
	 * JedisPoolConfig(); jedisPool = new JedisPool(poolConfig, "localhost");
	 * jedisPool = new JedisPool(poolConfig, RedisDBConfig.HOST,
	 * RedisDBConfig.PORT, RedisDBConfig.TIMEOUT, RedisDBConfig.PASSWORD); }
	 */

	public JedisPool getJedisPool() {
		JedisPoolConfig poolConfig = new JedisPoolConfig();
		jedisPool = new JedisPool(poolConfig, "localhost");
		/*
		 * jedisPool = new JedisPool(poolConfig, RedisDBConfig.HOST,
		 * RedisDBConfig.PORT, RedisDBConfig.TIMEOUT, RedisDBConfig.PASSWORD);
		 */
		return jedisPool;
	}

	public void returnJedisResource(Jedis j) {
		jedisPool.returnResource(j);
		jedisPool.destroy();

	}

	public static JedisFactory getInstance() {
		if (instance == null) {
			instance = new JedisFactory();
		}
		return instance;
	}

}
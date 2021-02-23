package com.example.demo.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public final class RedisUtil {

    //Redis服务器IP
    private static String ADDR = "39.107.43.71";
    //Redis的端口号
    private static int PORT = 6379;
    //访问密码
    private static String AUTH = "xyzt2019";
    //等待可用连接的最大时间，单位毫秒，默认值为-1，表示永不超时。如果超过等待时间，则直接抛出JedisConnectionException；
    private static int TIMEOUT = 30000;
    private static JedisPool jedisPool = null;
    private static Jedis jedis=getJedis();

    /**
     * 初始化Redis连接池
     */
    static {
        try {
            JedisPoolConfig config = new JedisPoolConfig();
            //最大空闲连接数, 默认8个
            config.setMaxIdle(10);
            //最大连接数, 默认8个
            config.setMaxTotal(20);
            //在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
            config.setTestOnBorrow(true);
            config.setMaxWaitMillis(-1);
            //是否启用pool的jmx管理功能, 默认true
            config.setJmxEnabled(true);
            jedisPool = new JedisPool(config, ADDR, PORT, TIMEOUT, AUTH);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取Jedis实例
     * @return
     */
    public synchronized static Jedis getJedis() {
        try {
            if (jedisPool != null) {
                Jedis resource = jedisPool.getResource();
                return resource;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 释放jedis资源
     * @param jedis
     */
    public static void returnResource(final Jedis jedis) {
        if (jedis != null) {
            jedisPool.returnResource(jedis);
        }
    }

    /**
     * 获取元素
     */
    public static String get(String key) {
        String string = jedis.get(key);
        jedis.close();
        return string;
    }

    /**
     *  加入元素
     */
    public static String set(String key, String value) {
        String string = jedis.set(key, value);
        jedis.close();
        return string;
    }

    /**
     * 获取元素
     */
    public static String hget(String hkey, String key) {
        String string = jedis.hget(hkey, key);
        jedis.close();
        return string;
    }

    /**
     * 加入元素
     */
    public static long hset(String hkey, String key, String value) {
        Long result = jedis.hset(hkey, key, value);
        jedis.close();
        return result;
    }

    /**
     * 增一
     */
    public static long incr(String key) {
        Long result = jedis.incr(key);
        jedis.close();
        return result;
    }

    /**
     * 设置 key 的过期时间，key 过期后将不再可用。单位以秒计。
     */
    public static long expire(String key, int second) {
        Long result = jedis.expire(key, second);
        jedis.close();
        return result;
    }

    /**
     * 以秒为单位返回 key 的剩余过期时间。
     */
    public static long ttl(String key) {
        Long result = jedis.ttl(key);
        jedis.close();
        return result;
    }

    /**
     * 删除
     */
    public static long del(String key) {
        Long result = jedis.del(key);
        jedis.close();
        return result;
    }

    /**
     * 删除
     */
    public static long hdel(String hkey, String key) {
        Long result = jedis.hdel(hkey, key);
        jedis.close();
        return result;
    }

    /**
     * 是否存在
     */
    public static Boolean hexists(String hkey, String key) {
        Boolean result = jedis.hexists(hkey, key);
        jedis.close();
        return result;
    }
}
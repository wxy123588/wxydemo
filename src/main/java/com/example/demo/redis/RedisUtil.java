package com.example.demo.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.List;
import java.util.Map;
import java.util.Set;

public final class RedisUtil {

    //用户存入redis map.put(id,json.toJSONString(user))
    public static  String user_redis="user_redis";
    //最近话单存入redis map.put(id,json.toJSONString(statusevent))
    public static  String statusevent_redis="statusevent_redis";
    //线路存入redis map.put(id,json.toJSONString(numberpool))
    public static  String numberpool_redis="numberpool_redis";
    //线路组存入redis map.put(id,json.toJSONString(numberpoolgroup))
    public static  String numberpoolgroup_redis="numberpoolgroup_redis";
    //线路组和线路关联
    // jedis.sadd("ccpaas"+numberPoolGroupRela.getNumbergroupid(), numberPoolGroupRela.getNumberpoolid());
    //话单表
    // jedis.sadd("ccpaas" + statusEvent.getCalled(), statusEvent.getDiscalled());

    //Redis服务器IP
//    private static String ADDR = "39.107.43.71";
    private static String ADDR = "172.16.1.219";
    //Redis的端口号
    private static int PORT = 6379;
    //访问密码
//    private static String AUTH = "xyzt2019";
    private static String AUTH = "xyzt123";
    //等待可用连接的最大时间，单位毫秒，默认值为-1，表示永不超时。如果超过等待时间，则直接抛出JedisConnectionException；
    private static int TIMEOUT = 30000;
    private static JedisPool jedisPool = null;

    /**
     * 初始化Redis连接池
     */
    static {
        try {
            JedisPoolConfig config = new JedisPoolConfig();
            //最大空闲连接数, 默认8个
            config.setMaxIdle(100);
            //最大连接数, 默认8个
            config.setMaxTotal(200);
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
        Jedis jedis=getJedis();
        String string = jedis.get(key);
        returnResource(jedis);
        return string;
    }

    /**
     *  加入元素
     */
    public static String set(String key, String value) {
        Jedis jedis=getJedis();
        String string = jedis.set(key, value);
        returnResource(jedis);
        return string;
    }

    /**
     * 获取元素
     */
    public static String hget(String hkey, String key) {
        Jedis jedis=getJedis();
        String string = jedis.hget(hkey, key);
        returnResource(jedis);
        return string;
    }

    /**
     * 加入元素
     */
    public static long hset(String hkey, String key, String value) {
        Jedis jedis=getJedis();
        Long result = jedis.hset(hkey, key, value);
        returnResource(jedis);
        return result;
    }

    /**
     * 增一
     */
    public static long incr(String key) {
        Jedis jedis=getJedis();
        Long result = jedis.incr(key);
        returnResource(jedis);
        return result;
    }

    /**
     * 设置 key 的过期时间，key 过期后将不再可用。单位以秒计。
     */
    public static long expire(String key, int second) {
        Jedis jedis=getJedis();
        Long result = jedis.expire(key, second);
        returnResource(jedis);
        return result;
    }

    /**
     * 以秒为单位返回 key 的剩余过期时间。
     */
    public static long ttl(String key) {
        Jedis jedis=getJedis();
        Long result = jedis.ttl(key);
        returnResource(jedis);
        return result;
    }

    /**
     * 删除
     */
    public static long del(String key) {
        Jedis jedis=getJedis();
        Long result = jedis.del(key);
        returnResource(jedis);
        return result;
    }

    /**
     * 删除
     */
    public static long hdel(String hkey, String key) {
        Jedis jedis=getJedis();
        Long result = jedis.hdel(hkey, key);
        returnResource(jedis);
        return result;
    }

    /**
     * 是否存在
     */
    public static Boolean hexists(String hkey, String key) {
        Jedis jedis=getJedis();
        Boolean result = jedis.hexists(hkey, key);
        returnResource(jedis);
        return result;
    }
    /**
     * set
     */
    public static String hmset(String hkey, Map key) {
        Jedis jedis=getJedis();
        String result = jedis.hmset(hkey, key);
        returnResource(jedis);
        return result;
    }
    /**
     * get
     */
    public static List<String> hmget(String hkey, String key[]) {
        Jedis jedis=getJedis();
        List<String> result = jedis.hmget(hkey, key);
        returnResource(jedis);
        return result;
    }
    /**
     * get
     */
    public static Set<String> smembers(String key) {
        Jedis jedis=getJedis();
        Set<String> result = jedis.smembers(key);
        returnResource(jedis);
        return result;
    }
    /**
     * get
     */
    public static Long sadd(String key,String value) {
        Jedis jedis=getJedis();
        Long result = jedis.sadd(key,value);
        returnResource(jedis);
        return result;
    }
}
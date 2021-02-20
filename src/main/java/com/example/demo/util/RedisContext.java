package com.example.demo.util;

import redis.clients.jedis.Jedis;

public class RedisContext {
    //jedis.hmset(statusevent_redis,map);
    public static  String statusevent_redis="statusevent_redis";//最近话单存入redis标识 map.put(id,json.toJSONString(statusevent))
    public static  String user_redis="user_redis";//用户存入redis标识 map.put(id,json.toJSONString(user))
    public static  String numberpool_redis="numberpool_redis";//线路存入redis标识 map.put(id,json.toJSONString(numberpool))
    public static  String numberpool_groupid_redis="numberpool_groupid_redis";//线路组和线路关联  jedis.sadd(groupid, poolid);
    public static  String numberpoolgroup_redis="numberpoolgroup_redis";//线路组存入redis标识 map.put(id,json.toJSONString(numberpoolgroup))
    public static Jedis getjedis(){
        Jedis jedis = new Jedis("39.107.43.71", 6379);
        jedis.auth("xyzt2019");
        return jedis;
    }
}

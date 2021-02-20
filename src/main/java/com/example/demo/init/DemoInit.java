package com.example.demo.init;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.util.RedisContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class DemoInit {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private StatusEventRepository statusEventRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private NumberPoolRepository numberPoolRepository;
    @Autowired
    private NumberPoolGroupRepository numberPoolGroupRepository;
    @Autowired
    private NumberPoolGroupRelaRepository numberPoolGroupRelaRepository;

    private JSONObject json = new JSONObject();
    private Map<String, String> map = new HashMap<String, String>();
    private Jedis jedis= RedisContext.getjedis();


    public  void init(){
        List<User> userlist=userRepository.findAll();
        if(userlist.size()>0){
            Map<String, String> usermap = new HashMap<String, String>();
            for(User user:userlist){
                map.put(user.getId(),json.toJSONString(user));
            }
            jedis.hmset(RedisContext.user_redis,map);
        }
        jedis.del(RedisContext.user_redis);
        List<NumberPool> numberpoollist=numberPoolRepository.findAll();
        if(userlist.size()>0){
            Map<String, String> usermap = new HashMap<String, String>();
            for(User user:userlist){
                map.put(user.getId(),json.toJSONString(user));
            }
            jedis.hmset(RedisContext.user_redis,map);
        }
        List<NumberPoolGroup> numberpoolgrouplist=numberPoolGroupRepository.findAll();
        if(userlist.size()>0){
            Map<String, String> usermap = new HashMap<String, String>();
            for(User user:userlist){
                map.put(user.getId(),json.toJSONString(user));
            }
            jedis.hmset(RedisContext.user_redis,map);
        }
        List<NumberPoolGroupRela> numberpoolgrouprelalist=numberPoolGroupRelaRepository.findAll();
        if(userlist.size()>0){
            Map<String, String> usermap = new HashMap<String, String>();
            for(User user:userlist){
                map.put(user.getId(),json.toJSONString(user));
            }
            jedis.hmset(RedisContext.user_redis,map);
        }
        List<StatusEvent> a=statusEventRepository.findbycreatetime();
        if(userlist.size()>0){
            Map<String, String> usermap = new HashMap<String, String>();
            for(User user:userlist){
                map.put(user.getId(),json.toJSONString(user));
            }
            jedis.hmset(RedisContext.user_redis,map);
        }
    }
}

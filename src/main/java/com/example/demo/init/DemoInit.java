package com.example.demo.init;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.entity.*;
import com.example.demo.mobile.MobileAddress;
import com.example.demo.mobile.MobileNumberUtils;
import com.example.demo.redis.RedisUtil;
import com.example.demo.repository.*;
import com.example.demo.util.RedisContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

import java.util.*;

@RestController
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

    private static JSONObject json = new JSONObject();
    private Map<String, String> numpoolmap = new HashMap<String, String>();
    private Map<String, String> numpoolgroupmap = new HashMap<String, String>();
    private Map<String, String> usermap = new HashMap<String, String>();
    private Map<String, String> statuteventmap = new HashMap<String, String>();
    private Jedis jedis= RedisUtil.getJedis();

    public void init () {
        //存user
        List<com.example.demo.entity.User> userlist = userRepository.findAll();
        if (userlist.size() > 0) {
            for (User user : userlist) {
                usermap.put(user.getId(), json.toJSONString(user));
            }
            jedis.hmset(RedisContext.user_redis, usermap);
        }
        //存线路
        List<NumberPool> numberpoollist = numberPoolRepository.findAll();
        if (numberpoollist.size() > 0) {
            for (NumberPool numberPool : numberpoollist) {
                numpoolmap.put(numberPool.getId(), json.toJSONString(numberPool));
            }
            jedis.hmset(RedisContext.numberpool_redis, numpoolmap);
        }
        //存线路组
        List<NumberPoolGroup> numberpoolgrouplist = numberPoolGroupRepository.findAll();
        if (numberpoolgrouplist.size() > 0) {
            for (NumberPoolGroup numberPoolGroup : numberpoolgrouplist) {
                numpoolgroupmap.put(numberPoolGroup.getId(), json.toJSONString(numberPoolGroup));
            }
            jedis.hmset(RedisContext.numberpoolgroup_redis, numpoolgroupmap);
        }
        //存线路组和线路对照
        List<NumberPoolGroupRela> numberpoolgrouprelalist = numberPoolGroupRelaRepository.findAll();
        if (numberpoolgrouprelalist.size() > 0) {
            for (NumberPoolGroupRela numberPoolGroupRela : numberpoolgrouprelalist) {
                jedis.sadd("ccpaas"+numberPoolGroupRela.getNumbergroupid(), numberPoolGroupRela.getNumberpoolid());//放入缓存
            }
        }
        //存通话记录
        List<StatusEvent> statusEventlist = statusEventRepository.findbycreatetime();
        if (statusEventlist.size() > 0) {
            for (StatusEvent statusEvent : statusEventlist) {
               // statuteventmap.put(statusEvent.getId(), json.toJSONString(statusEvent));
                jedis.sadd("ccpaas" + statusEvent.getCalled(), statusEvent.getDiscalled());//已接通固定外显（被叫、外显）
            }
            jedis.hmset(RedisContext.statusevent_redis, statuteventmap);
        }
        jedis.close();
    }

}

package com.example.test;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.DemoApplication;

import com.example.demo.entity.*;
import com.example.demo.entity.User;
import com.example.demo.redis.RedisUtil;
import com.example.demo.repository.*;
import com.example.demo.util.RedisContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class test4 {
    private JSONObject json = new JSONObject();

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

    private Map<String, String> numpoolmap = new HashMap<String, String>();
    private Map<String, String> numpoolgroupmap = new HashMap<String, String>();
    private Map<String, String> usermap = new HashMap<String, String>();
    private Map<String, String> statuteventmap = new HashMap<String, String>();
    private Jedis jedis= RedisUtil.getJedis();


    @Test
    public void testJedis(){
        // 链接redis
        Jedis jedis = new Jedis("39.107.43.71", 6379);
        jedis.auth("xyzt2019");
        jedis.del(RedisContext.user_redis);
        jedis.del(RedisContext.statusevent_redis);
        jedis.del(RedisContext.numberpool_redis);
        jedis.del(RedisContext.numberpoolgroup_redis);
//        List<NumberPoolGroupRela> list=numberPoolGroupRelaRepository.findAll();
//        for(NumberPoolGroupRela numberPoolGroupRela:list){
//            jedis.del("ccpaas"+numberPoolGroupRela.getNumbergroupid());
//        }
//        List<StatusEvent> stlist=statusEventRepository.findbycreatetime();
//        for(StatusEvent st:stlist){
//            jedis.del("ccpaas"+st.getCalled());
//        }
        init();
    }

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
                statuteventmap.put(statusEvent.getId(), json.toJSONString(statusEvent));
                jedis.sadd("ccpaas" + statusEvent.getCalled(), statusEvent.getDiscalled());//已接通固定外显（被叫、外显）
                jedis.expire("ccpaas" + statusEvent.getCalled(),600);
            }
            jedis.hmset(RedisContext.statusevent_redis, statuteventmap);
        }
        jedis.close();
    }
}


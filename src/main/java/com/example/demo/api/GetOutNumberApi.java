package com.example.demo.api;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.entity.*;
import com.example.demo.mobile.MobileAddress;
import com.example.demo.mobile.MobileNumberUtils;
import com.example.demo.repository.*;
import com.example.demo.util.JsonUtil;
import com.example.demo.util.RedisContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

import java.util.*;

@RestController
@RequestMapping("/getOutNumberApi")
public class GetOutNumberApi {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
int numcount=0;
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
    private Map<String, String> numpoolmap = new HashMap<String, String>();
    private Map<String, String> numpoolgroupmap = new HashMap<String, String>();
    private Map<String, String> usermap = new HashMap<String, String>();
    private Map<String, String> statuteventmap = new HashMap<String, String>();
    private Jedis jedis=RedisContext.getjedis();

    //传递话单id
    @RequestMapping(value = "/getoutnumber")
    public  String getoutnumber(String eventid){
        logger.info("第a"+(numcount)+"次starttime:"+ System.currentTimeMillis());
        StatusEvent statusEvent=getstatudevent(eventid);//对应话单
        logger.info("第b"+(numcount)+"次starttime:"+ System.currentTimeMillis());
        if(statusEvent!=null&&statusEvent.getUserid()!=null){
            User user=getUserbyid(statusEvent.getUserid());//查询当前坐席
            logger.info("第c"+(numcount)+"次starttime:"+ System.currentTimeMillis());
            if(user!=null){
                if(user.getNumgroupenable()==false){ //固定线路
                   NumberPool num= getnumberpoolbyid(user.getNumberpoolid());//单线路直接查询线路号码
                    logger.info("第d"+(numcount)+"次starttime:"+ System.currentTimeMillis());
                    return num.getNumber();
                }else{
                    NumberPoolGroup numberPoolGroup=getnumberpoolgroupbyid(user.getNumbergroupid());//查询当前坐席所在的线路组
                    logger.info("第e"+(numcount)+"次starttime:"+ System.currentTimeMillis());
                    List<NumberPool>  numlist=null;
                    if(numberPoolGroup!=null&&numberPoolGroup.getStrategy()!=null){
                        String strategy=numberPoolGroup.getStrategy();//策略
                        if(strategy.equals("poll")){//轮询
                            return getnumberpoolbygroupid(user.getNumbergroupid()).getNumber();//查询线路组里面的线路
                        }else if(strategy.equals("attribution")){//归属地
                            String called=statusEvent.getCalled();
                            MobileAddress mobileAddress= MobileNumberUtils.getAddress(called);
                            //statusEvent.getUserid();
                            numlist= numberPoolRepository.findBygroupidandprovinceAndcity(numberPoolGroup.getId(),mobileAddress.getProvince(),mobileAddress.getCity());//查询线路组里面的线路
                            if(numlist.size()>0){
                                //NumberPool returnnumberpool= getattributionmobilenum(numlist);

                            }else{
                                numlist= numberPoolRepository.findBygroupidandprovince(numberPoolGroup.getId(),mobileAddress.getProvince());//查询线路组里面的线路
                                if(numlist.size()>0){
                                    //NumberPool returnnumberpool= getattributionmobilenum(numlist);
                                }
                            }
                        }else{//接通后固定外显
                            List<StatusEvent> list=statusEventRepository.findByCalledAndAnswer(statusEvent.getCalled(),"answered");
                            if(list.size()>0){
                                return list.get(0).getDiscalled();
                            }
                        }
                    }
                }
            }
        }
        return null;
    }

    //获取话单
    public  StatusEvent getstatudevent(String id){
        logger.info("StatusEvent1:"+ System.currentTimeMillis());
        StatusEvent statusEvent =null;
        if(jedis.hexists(RedisContext.statusevent_redis,id)){
            logger.info("StatusEvent2:"+ System.currentTimeMillis());
            String event =jedis.hget(RedisContext.statusevent_redis,id);
            logger.info("StatusEvent3:"+ System.currentTimeMillis());
            statusEvent= json.parseObject(event, StatusEvent.class);
            logger.info("StatusEvent4:"+ System.currentTimeMillis());
        }else{
            statusEvent=statusEventRepository.findById(id);
            logger.info("StatusEvent5:"+ System.currentTimeMillis());
            statuteventmap.put(id,json.toJSONString(statusEvent));//数据库查询放入缓存
            logger.info("StatusEvent6:"+ System.currentTimeMillis());
            jedis.hmset(RedisContext.statusevent_redis,statuteventmap);
            logger.info("StatusEvent7:"+ System.currentTimeMillis());
        }
        logger.info("StatusEvent8:"+ System.currentTimeMillis());
        jedis.close();
        return statusEvent;
    }
    //获取用户
    public  User getUserbyid(String id){
        User user =null;
        logger.info("user1:"+ System.currentTimeMillis());
        if(jedis.hexists(RedisContext.user_redis,id)){
            logger.info("user2:"+ System.currentTimeMillis());
            String temp =jedis.hget(RedisContext.user_redis,id);
            logger.info("user3:"+ System.currentTimeMillis());
            user= json.parseObject(temp, User.class);
            logger.info("user4:"+ System.currentTimeMillis());
        }else{
            logger.info("user5:"+ System.currentTimeMillis());
            user=userRepository.findById(id);//查询当前坐席
            usermap.put(id,json.toJSONString(user));//数据库查询放入缓存
            jedis.hmset(RedisContext.user_redis,usermap);
        }
        logger.info("user6:"+ System.currentTimeMillis());
        jedis.close();
        logger.info("user7:"+ System.currentTimeMillis());
        return user;
    }
    //获取线路号码
    public  NumberPool getnumberpoolbyid(String id){
        NumberPool numberpool =null;
        if(jedis.hexists(RedisContext.numberpool_redis,id)){
            String temp =jedis.hget(RedisContext.numberpool_redis,id);
            numberpool= json.parseObject(temp, NumberPool.class);
        }else{
            numberpool= numberPoolRepository.findById(id);//单线路直接查询线路号码
            numpoolmap.put(id,json.toJSONString(numberpool));//数据库查询放入缓存
            jedis.hmset(RedisContext.numberpool_redis,numpoolmap);
        }
        jedis.close();
        return numberpool;
    }

    //获取线路组
    public  NumberPoolGroup getnumberpoolgroupbyid(String id){
        NumberPoolGroup numberpoolgroup =null;
        if(jedis.hexists(RedisContext.numberpoolgroup_redis,id)){
            String temp =jedis.hget(RedisContext.numberpoolgroup_redis,id);
            numberpoolgroup= json.parseObject(temp, NumberPoolGroup.class);
        }else{
            numberpoolgroup= numberPoolGroupRepository.findById(id);
            numpoolgroupmap.put(id,json.toJSONString(numberpoolgroup));//数据库查询放入缓存
            jedis.hmset(RedisContext.numberpoolgroup_redis,numpoolgroupmap);
        }
        jedis.close();
        return numberpoolgroup;
    }

    //获取最终线路号码
    public  NumberPool getnumberpoolbygroupid(String groupid){
        NumberPool numberpool =null;
        Set<String> poolStr = jedis.smembers(groupid);//获取所有线路id字符串
        if(poolStr.isEmpty()){//缓存没有则数据库查询，并放入缓存
            List<NumberPoolGroupRela> list=numberPoolGroupRelaRepository.findByNumbergroupid(groupid);
            if(list.size()==0){
                return null;
            }
            for(NumberPoolGroupRela rela:list){
                jedis.sadd(groupid, rela.getNumberpoolid());//放入缓存
                poolStr.add(rela.getNumberpoolid());//拼接所有线路id字符串
            }
        }
        //通过线路id字符串获取线路，没有则数据库查询，并放入缓存
        List<String>  templist = jedis.hmget(RedisContext.numberpool_redis,  (String[])poolStr.toArray(new String[poolStr.size()]));
        if(templist.size()>0){
            numberpool =json.parseObject(templist.get(0), NumberPool.class);
            int temp=numberpool.getCount();//获取线路count最小的线路
            for (int j=1;j<templist.size();j++) {
                NumberPool numpool =  json.parseObject(templist.get(j), NumberPool.class);
                if(numpool.getCount()<temp){
                    temp=numpool.getCount();
                    numberpool=numpool;
                }
            }
            numberpool.setCount(++temp);
            numpoolmap.put(numberpool.getId(),json.toJSONString(numberpool));//放入缓存
            jedis.hmset(RedisContext.numberpool_redis,numpoolmap);
        }else{
            List<NumberPool> list=numberPoolRepository.findBygroupid(groupid);
            if(list.size()==0){
                return null;
            }
            for(NumberPool numberPool:list){
                numpoolmap.put(numberPool.getId(),json.toJSONString(numberpool));//数据库查询放入缓存
            }
            jedis.hmset(RedisContext.numberpool_redis,numpoolmap);
            numberpool=getnumPoolbycount(list);
        }
        jedis.close();
        logger.info("第f"+(numcount++)+"次starttime:"+ System.currentTimeMillis());
        return numberpool;
    }

    //获取count最小的线路
    public NumberPool getnumPoolbycount(List<NumberPool>  list){
        NumberPool numberpool =list.get(0);
        int temp=numberpool.getCount();
        for (int j=1;j<list.size();j++) {
            NumberPool numpool =  list.get(j);
            if(numpool.getCount()<temp){
                temp=numpool.getCount();
                numberpool=numpool;
            }
        }
        numberpool.setCount(temp++);
        numpoolmap.put(numberpool.getId(),json.toJSONString(numberpool));//放入缓存
        jedis.hmset(RedisContext.numberpool_redis,numpoolmap);
        jedis.close();
        return numberpool;
    }

}

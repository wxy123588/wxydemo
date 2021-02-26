package com.example.demo.api;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.entity.*;
import com.example.demo.mobile.MobileAddress;
import com.example.demo.mobile.MobileNumberUtils;
import com.example.demo.redis.RedisUtil;
import com.example.demo.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/getOutNumberApi")
public class GetOutNumberApi {
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
   // private Map<String, String> statuteventmap = new HashMap<String, String>();
    private int numcount=1;

    @RequestMapping(value = "/getoutnumber1")
    public  String getoutnumber1(String called,String userid){
        userid="3aa98c20d8ff4059b985087fbe868eb7";//单线路
        String val= getoutnumber("13477808291",userid);
        logger.info(++numcount+"*************"+val);
        return val;
    }
    @RequestMapping(value = "/getoutnumber2")
    public  String getoutnumber2(String called,String userid){
        userid="69dd9ba7e9f843d0b0ec11fa4bb4848c";//获取外显号码线路组fixedaftercall
        String val= getoutnumber("13477808291",userid);
        logger.info(++numcount+"*************"+val);
        return val;
    }
    @RequestMapping(value = "/getoutnumber3")
    public  String getoutnumber3(String called,String userid){
        userid="edfcf835fb1a488bbd46431cd0d83f60";//获取外显号码线路组pull
        String val= getoutnumber("13477808291",userid);
        logger.info(++numcount+"*************"+val);
        return val;
    }
    @RequestMapping(value = "/getoutnumber4")
    public  String getoutnumber4(String called,String userid){
        userid="2c90810d77704983017770becd21015d";//获取外显号码线路组attribution
        String val= getoutnumber("13477808291",userid);
        logger.info(++numcount+"*************"+val);
        return val;
    }
    //传递话单id
    @RequestMapping(value = "/getoutnumber")
    public  String getoutnumber(String called,String userid){
        called="13477808291";
        //StatusEvent statusEvent=getstatudevent(eventid);//对应话单
            User user=getUserbyid(userid);//查询当前坐席
            if(user!=null){
                if(user.getNumgroupenable()==false){ //固定线路
                    NumberPool num= getnumberpoolbyid(user.getNumberpoolid());//单线路直接查询线路号码
                    return num.getNumber();
                }else{
                    NumberPoolGroup numberPoolGroup=getnumberpoolgroupbyid(user.getNumbergroupid());//查询当前坐席所在的线路组
                    List<NumberPool>  numlist=null;
                    if(numberPoolGroup!=null&&numberPoolGroup.getStrategy()!=null){
                        String strategy=numberPoolGroup.getStrategy();//策略
                        if(strategy.equals("poll")){//轮询
                            NumberPool nub=getnumberpoolbygroupid(user.getNumbergroupid());
                            return nub.getNumber();//查询线路组里面的线路
                        }else if(strategy.equals("attribution")){//归属地
                            NumberPool nub= getnumberpoolbygroupidandAtt(user.getNumbergroupid(),called);
                            return nub.getNumber();
                        }else{//接通后固定外显
                            NumberPool nub=getnumberpoolbyhistory(called,numberPoolGroup.getId());
                            return nub.getNumber();

                        }
                    }
                }
            }
        return null;
    }

    //获取话单
//    public  StatusEvent getstatudevent(String id){
//        StatusEvent statusEvent =null;
//        String event =RedisUtil.hget(RedisUtil.statusevent_redis,id);
//        if(!StringUtils.isEmpty(event)){
//            statusEvent= json.parseObject(event, StatusEvent.class);
//        }else{
//            statusEvent=statusEventRepository.findById(id);
//            //statuteventmap.put(id,json.toJSONString(statusEvent));//数据库查询放入缓存
//            //RedisUtil.hmset(RedisUtil.statusevent_redis,statuteventmap);
//        }
//        return statusEvent;
//    }
    //获取用户
    public  User getUserbyid(String id){
        User user =null;
        String temp =RedisUtil.hget(RedisUtil.user_redis,id);
        if(!StringUtils.isEmpty(temp)){
            user= json.parseObject(temp, User.class);
        }else{
            user=userRepository.findById(id);//查询当前坐席
            usermap.put(id,json.toJSONString(user));//数据库查询放入缓存
            RedisUtil.hmset(RedisUtil.user_redis,usermap);
        }
        return user;
    }
    //获取线路号码
    public  NumberPool getnumberpoolbyid(String id){
        NumberPool numberpool =null;
        String temp =RedisUtil.hget(RedisUtil.numberpool_redis,id);
        if(!StringUtils.isEmpty(temp)){
            numberpool= json.parseObject(temp, NumberPool.class);
        }else{
            numberpool= numberPoolRepository.findById(id);//单线路直接查询线路号码
            numpoolmap.put(id,json.toJSONString(numberpool));//数据库查询放入缓存
            RedisUtil.hmset(RedisUtil.numberpool_redis,numpoolmap);
        }
        numberpool.setCount(numberpool.getCount()+1);
        numpoolmap.put(numberpool.getId(),json.toJSONString(numberpool));//放入缓存
        RedisUtil.hmset(RedisUtil.numberpool_redis,numpoolmap);
        return numberpool;
    }

    //获取线路组
    public  NumberPoolGroup getnumberpoolgroupbyid(String id){
        NumberPoolGroup numberpoolgroup =null;
        String temp =RedisUtil.hget(RedisUtil.numberpoolgroup_redis,id);
        if(!StringUtils.isEmpty(temp)){
            numberpoolgroup= json.parseObject(temp, NumberPoolGroup.class);
        }else{
            numberpoolgroup= numberPoolGroupRepository.findById(id);
            numpoolgroupmap.put(id,json.toJSONString(numberpoolgroup));//数据库查询放入缓存
            RedisUtil.hmset(RedisUtil.numberpoolgroup_redis,numpoolgroupmap);
        }
        return numberpoolgroup;
    }

    //获取最终线路号码
    public  NumberPool getnumberpoolbygroupid(String groupid){
        NumberPool numberpool =null;
        Set<String> poolStr = RedisUtil.smembers("ccpaas"+groupid);//获取所有线路id字符串

        //缓存没有则数据库查询，并放入缓存
        if(poolStr.isEmpty()){
            List<NumberPoolGroupRela> list=numberPoolGroupRelaRepository.findByNumbergroupid(groupid);
            if(list.size()==0){
                return null;
            }
            for(NumberPoolGroupRela rela:list){
                RedisUtil.sadd("ccpaas"+groupid, rela.getNumberpoolid());//放入缓存
                poolStr.add(rela.getNumberpoolid());//拼接所有线路id字符串
            }
        }
        //通过线路id字符串获取线路，没有则数据库查询，并放入缓存
        List<String>  templist = RedisUtil.hmget(RedisUtil.numberpool_redis,  (String[])poolStr.toArray(new String[poolStr.size()]));
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
            RedisUtil.hmset(RedisUtil.numberpool_redis,numpoolmap);
        }else{
            List<NumberPool> list=numberPoolRepository.findBygroupid(groupid);
            if(list.size()==0){
                return null;
            }
            for(NumberPool numberPool:list){
                numpoolmap.put(numberPool.getId(),json.toJSONString(numberpool));//数据库查询放入缓存
            }
            RedisUtil.hmset(RedisUtil.numberpool_redis,numpoolmap);
            numberpool=getnumPoolbycount(list);
        }
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
        RedisUtil.hmset(RedisUtil.numberpool_redis,numpoolmap);
        return numberpool;
    }

    //根据归属地匹配外显号码
    public  NumberPool getnumberpoolbygroupidandAtt(String groupid,String called){
        NumberPool numPool=null;
        MobileAddress mobileAddress= MobileNumberUtils.getAddress(called);
        Set<String> poolStr = RedisUtil.smembers("ccpaas"+groupid);//获取所有线路id字符串
        //获取所有线路
        List<String>  templist = RedisUtil.hmget(RedisUtil.numberpool_redis,  (String[])poolStr.toArray(new String[poolStr.size()]));
        List<NumberPool> numberpooplist=json.parseArray(templist.toString(),NumberPool.class);
        //获取所有线路中匹配省的
        List<NumberPool> prolist=  numberpooplist.stream().filter(numberpoop -> numberpoop.getProvince().equals(mobileAddress.getProvince())).collect(Collectors.toList());
        if(prolist.size()>0){
            //获取所有线路中匹配市的
            List<NumberPool> citylist=  prolist.stream().filter(numberpoop -> numberpoop.getCity().equals(mobileAddress.getCity())).collect(Collectors.toList());
            if(citylist.size()>0){
                numPool=getnumPoolbycount(citylist);
            }else{
                numPool=getnumPoolbycount(prolist);
            }
        }else{
            numPool=getnumPoolbycount(numberpooplist);
        }
        return numPool;
    }

    //接通后固定外显
    public  NumberPool getnumberpoolbyhistory(String called,String numbergroupid) {
        //通过被叫查询所有有过记录的主叫号码
        Set<String> discallStr = RedisUtil.smembers("ccpaas"+called);
        if (discallStr.isEmpty()) {
            //走轮询
            NumberPool numberpool=getnumberpoolbygroupid(numbergroupid);
            return numberpool;
        }else{
            //线路组下可用线路id
            Set<String> poolStr = RedisUtil.smembers("ccpaas"+numbergroupid);
            if(!poolStr.isEmpty()){
                //线路组下可用线路list
                List<String>  numpoollist = RedisUtil.hmget(RedisUtil.numberpool_redis,  (String[])poolStr.toArray(new String[poolStr.size()]));
                if(!numpoollist.isEmpty()){
                    NumberPool nub =null;
                    for(String str:discallStr){//历史线路组 主叫
                        for(int i=0;i<numpoollist.size();i++){//遍历可用线路组
                            nub =json.parseObject(numpoollist.get(i), NumberPool.class);
                            if(str.equals(nub.getNumber())){
                                return nub;
                            }
                        }
                    }
                    List<NumberPool> numberpooplist=json.parseArray(numpoollist.toString(),NumberPool.class);
                    return getnumPoolbycount(numberpooplist);
                }
            }
        }
        return null;
    }

}

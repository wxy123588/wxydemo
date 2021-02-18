package com.example.demo.api;

import com.example.demo.entity.*;
import com.example.demo.mobile.MobileAddress;
import com.example.demo.mobile.MobileNumberUtils;
import com.example.demo.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;
import java.util.List;

@RestController
@RequestMapping("/getOutNumberApi")
public class GetOutNumberApi {
    int i=0;
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

    //传递话单id
    @RequestMapping(value = "/getoutnumber")
    public  String getoutnumber(String eventid){
        logger.info("第"+(++i)+"次starttime:"+ System.currentTimeMillis());
        String returnnumer="";
        NumberPool returnnumberpool= null;
        StatusEvent statusEvent=statusEventRepository.findById(eventid);//查询到对应话单
        if(statusEvent!=null&&statusEvent.getUserid()!=null){
            User user=userRepository.findById(statusEvent.getUserid());//查询当前坐席
            if(user!=null){
                if(user.getNumgroupenable()==false){ //固定线路
                   NumberPool num= numberPoolRepository.findById(user.getNumberpoolid());//单线路直接查询线路号码
                    returnnumer= num.getNumber();
                }else{
                    NumberPoolGroup numberPoolGroup=numberPoolGroupRepository.findById(user.getNumbergroupid());//查询当前坐席所在的线路组
                    List<NumberPool>  numlist=null;
                    if(numberPoolGroup!=null&&numberPoolGroup.getStrategy()!=null){
                        String strategy=numberPoolGroup.getStrategy();//策略
                        if(strategy.equals("poll")){//轮询
                            numlist= numberPoolRepository.findBygroupidmaxcount(numberPoolGroup.getId());//查询线路组里面的线路
                            if(numlist.size()>0){
                                returnnumberpool= numlist.get(0);
                                returnnumberpool.setCount(returnnumberpool.getCount()+1);
                                numberPoolRepository.save(returnnumberpool);
                                logger.info("第"+i+"次endtime:"+ System.currentTimeMillis());
                                return returnnumberpool.getNumber();
                            }
                        }else if(strategy.equals("attribution")){//归属地
                            String called=statusEvent.getCalled();
                            MobileAddress mobileAddress= MobileNumberUtils.getAddress(called);
                            //statusEvent.getUserid();
                            numlist= numberPoolRepository.findBygroupidandprovinceAndcity(numberPoolGroup.getId(),mobileAddress.getProvince(),mobileAddress.getCity());//查询线路组里面的线路
                            if(numlist.size()>0){
                                returnnumberpool= getattributionmobilenum(numlist);

                            }else{
                                numlist= numberPoolRepository.findBygroupidandprovince(numberPoolGroup.getId(),mobileAddress.getProvince());//查询线路组里面的线路
                                if(numlist.size()>0){
                                    returnnumberpool= getattributionmobilenum(numlist);
                                }
                            }
                        }else{//接通后固定外显
                            List<StatusEvent> list=statusEventRepository.findByCalledAndAnswer(statusEvent.getCalled(),"answered");
                            if(list.size()>0){
                                returnnumer=list.get(0).getDiscalled();
                            }
                        }
                    }
                }
            }
        }
        return null;
    }

    //轮询
    public NumberPool getattributionmobilenum(List<NumberPool>  list){


        return null;
    }
}

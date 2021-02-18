
package com.example.demo.entity;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import org.hibernate.annotations.Proxy;


@Entity
@Table(name = "uk_callcenter_event")
@Proxy(lazy = false)
public class StatusEvent implements Serializable, Comparable<StatusEvent>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2796276564445713776L;

	private String id ;
	private Date createtime = new Date();
	private Date updatetime = new Date() ;
	
	private boolean inside ;		//是否内线
	private String code ;
	
	private boolean transcon ;		//条件转人工
	private String transconid ;		//转人工条件

	private String creater ;		//变更用处，标识是否已接通
	
	private String source ;	//来源
	private String answer ;//应答时间

	private boolean callback ;	//是否是回呼
	private String ccquene ;	//队列
	
	private String calltype ;	//呼叫方向类型 | 计费类型
	
	private String voicecalled ;//
	
	private String servicestatus ;	//通话状态
	
	private String channelstatus ;	//通道状态
	
	private Date inqueuetime ;		//进队列时间
	private Date outqueuetime ;		//进队列时间
	private int queuetime ;			//排队时长
	
	private String gateway ;		//网关名称
	private boolean gatewaycount;	//网关已经计数
	
	private boolean ivr ;			//是否有IVR
	private Date ivrentertime ;		//IVR进入时间
	private Date ivrleavetime ;	//IVR离开时间
	private int ivrtime ;		//IVR停留时间
	
	@Transient
	private boolean autoanswer ;	//是否需要自动接听
	
	private Date answertime ;//应答时间
	private int ringduration ;//振铃时长
	
	private int waittime ;		//坐席等待时长
	private boolean current ;//
	private boolean forecast ;
	private String skill ;
	private String forecastid;
	
	private boolean ai ;		//是否机器人呼叫
	private String aieventid ;	//机器人呼叫ID
	private int asrtimes ;		//asr次数
	private int ttstimes ;		//tts次数
	private int timeouttimes ;		//当前节点超时次数
	private int errortimes ;		//当前节点错误次数
	private int nmlinetimes ;		//非主线节点次数
	
	private boolean aitrans ;	//是否AI转接
	private String aiext ;		//机器人号码
	
	private String timehour ;		//拨打时间段
	private String timehourmin ;	//拨打时间段
	
	private String priphone ;	//隐私号码
	private String hidetype ;	//隐藏号码的呼叫方向
	
	private boolean transfaild ;	//转接人工失败 （无人工坐席）
	
	private String aitransqus;	//转接问题ID
	private Date aitranstime ;	//转接时间
	private long aitransduration ;	//转接时通话时长
	
	private String aitransans ;	//答案ID
	
	private boolean init ;//
	
	private String coreuuid ;	//FS标识
	
	private String caller ;//呼叫发起号码
	
	private String calling ;//呼叫对象
	private String called ;//被叫号码
	
	private String discaller;//
	private String discalled;//
	
	private String agentype ;	//callback
	private String quene ;		//呼入队列
	
	private String ani ;//主叫号码
	
	private String touser ;//目标用户
	private String direction ;//呼叫方向
	
	private String calldir ;	//呼叫方向
	private String otherdir;	//对边呼叫方向
	
	private boolean waste ;		//是否作废名单
	private boolean apstatus ;	//是否预约名单
	
	private String otherlegdest ;	//对边呼叫号码
	
	private long time ;///
	private String localdatetime ;//时间发起时间
	
	private Date starttime ;//通话开始时间
	private Date endtime ;//通话结束时间
	
	private int duration ;//通话时长

	private int durationcost ;//计费时长 不满60秒，按60秒计算。

	private String membersessionid ;	//转接前通话ID
	
	private String status ;//
	private String state ;//
	private String agent ;//坐席工号
	private String action ;//
	private String name;//
	private String host ;//时间主机
	private String ipaddr ;//主机IP	
	private String sipaddr ;	//SIP 客户端IP
	
	private String extention ;//联系人ID
	private String hostid ;//服务器ID
	
	private String metaname ;		//呼叫名单表名称
	private String taskid ;			//呼叫ID，电销业务产生的
	private String actid ;			//呼叫ID，电销业务产生的
	private String batid ;			//呼叫ID，电销业务产生的
	private String dataid ;			//呼叫ID，电销业务产生的
	private String nameid ;			//名单ID，电销业务产生的
	private String disphonenum ;	//是否隐藏号码
	private String distype ;		//号码隐藏方式
	
	private String busstype ;		//业务类型
	
	private String siptrunk ;		//自定义 SIP Trunk
	private boolean prefix ;		//是否启用加0前缀
	
	private boolean callstatus ;	//拨打状态  ， 成功或失败
	
	private String con_surnames  ;	//联系人姓名-业务字段
	private String con_intention ;	//意向度 -- 业务字段
	private String con_quality 	;	//质检--业务字段
	private String con_qualitysubmit;//质检--可提交
	private String con_qualitypass ;  //质检--通过
	
	
	private String qualitystatus ;//质检状态  ， 已分配/未分配
	private String qualitydisorgan ;	//分配的质检部门
	private String qualitydisuser;		//分配的质检人
	private Date qualitydistime;		//分配的时间
	private String assuser ;			//分配执行人
	private String templateid ;			//分配的质检模板
	private String qualitydistype;				//分配状态  ，未分配not/分配到部门disorgan/分配到坐席disagent
	
	private String qualityorgan ;		//实际的质检部门
	private String qualityuser;			//实际的质检人
	private int qualityscore ;			//质检评分
	private Date qualitytime ;			//质检时间
	private String qualitytype ;			//质检类型
	
	private String qualityactid;	//质检活动id
	private String qualityfilterid;	//质检筛选表单id
	
	private String qualityresult; //质检结果（通过/未通过）
	
	
	private boolean record ;//是否录音
	
	private Date startrecord ;//开始录音时间
	private Date endrecord ;//结束录音时间
	private int recordtime ;//录音时长
	private String recordfile ;//开始录音时间
	private String recordfilename ;//结束录音时间
	
	@Transient
	private boolean rest = false ;	//当前是否示忙状态
	
	private String contactsid ;//
	
	private String bridgeid ;			//桥接对方ID
	private boolean bridge ;			//是否桥接
	
	private boolean misscall = true;	//是否漏话
	
	private boolean servicesummary ;	//是否记录服务小结
	private String serviceid 	;		//服务小结ID
	
	private int calls ;//
	
	private String orgi ;//
	
	private String country ;	//国家
	private String province ;	//省份
	private String city ;		//城市
	private String isp ;		//运营商
	private boolean satisf ;	//是否记录满意度调查
	private String satisfaction 	;		//满意度评价
	private Date satisfdate ;				//满意度调查提交时间

	private String userid;
	private String username;
	private String organ;
	
	private boolean dtmf ;		//是否记录了DTMF信息
	private String dtmfrec ;	//DTMF记录
	
	private int trans;		//是否语音转写（0未转写1已转写）
	private Date transbegin;//语音转写开始时间
	private Date transend;	//语音转写结束时间
	private String transtime;//语音转写用时
	private String transtatus;//语音转写状态
	private int transcost;	  //语音转写费用
	private String tranid;//语音转写任务ID
	
	private int qualitypass=2;//质检是否合格(默认2为未质检)
	
	private String workstatus ;
	
	private String hangupcase ;		//挂断原因	
	private String hangupinitiator ;//挂断发起方

	private String ossstatus = "0";//录音文件是否上传到oss 0否 1是 2文件不存在
	
	private String itemid ;			//项目ID
	
	private String accountdes;//客户账号信息
	
	private boolean conference ;	//是否在会议中
	private boolean conferenceinitiator ;	//是否会议发起人
	private Date inconferenecetime ;//进入会议时间
	private long conferenceduration ;//会议时长
	
	private String conferencenum;	//会议号码
	private String conferenceid ;	//会议ID

	private Integer levelscore;
	private String level;
	private Integer focustimes;

	private String processid;//话术or问卷id
	
	private boolean igr;		//是否进行性别年龄识别
	private String igrstatus ;	//igr状态
	private String igrvoice;	//igr语音文件
	private int igrvoicetime ;	//igr语音文件时长
	private String igrage;		//识别引擎返回 - 表示识别的年龄[0：middle(12~40岁) 1：child（0~12岁）2：old（40岁以上）]
	private String igrchild;	//识别引擎返回 - 表示识别为儿童的概率值，儿童、中年、老年概率值最大的为最终结果	
	private String igrmiddle;	//识别引擎返回 - 表示识别为中年的概率值，儿童、中年、老年概率值最大的为最终结果
	private String igrold;		//识别引擎返回 - 表示识别为老年的概率值，儿童、中年、老年概率值最大的为最终结果
	
	private String igrgender;	//识别引擎返回 - 表示识别的性别 [0：女性 1：男性]
	private String igrfemale;	//识别引擎返回 - 表示识别为女声的概率值，女声、男声概率值较大的为最终结果
	private String igrmale;		//识别引擎返回 - 表示识别为男声的概率值，女声、男声概率值较大的为最终结
	
	private String extdata1;	//名单扩展字段1
	private String extdata2;	//名单扩展字段2
	private String extdata3;	//名单扩展字段3
	private String extdata4;	//名单扩展字段4
	private String extdata5;	//名单扩展字段5
	private String extdata6;	//名单扩展字段6
	
	private Date igrbegin;		//开始识别
	private Date igrend;		//结束识别
	
	private boolean aicollect;//是否是全流程机器人采集数据
	
	private boolean autoquality;//是否自动质检
	private boolean spotqc;//是否抽检
	private Date spotqctime;//抽检时间
	private boolean spotqcsuccess;//抽检是否成功
	private boolean appealqc;//是否申诉质检
	private boolean arbitrateqc;//是否仲裁质检
	
	private String transresult; //转接结果（阿里云返回的结果）
	
	private int templatever = 0 ;//分配的质检模板版本号

	private String numberpoolid ;	//线路ID
	private String numberpoolname ;	//线路名称
	
	public String getWorkstatus() {
		return workstatus;
	}
	public void setWorkstatus(String workstatus) {
		this.workstatus = workstatus;
	}
	private String extno;
	@Transient
	public String getExtno() {
		return extno;
	}
	public void setExtno(String extno) {
		this.extno = extno;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	private String callresult;
	@Id
	@Column(length = 32)
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "assigned")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getAgent() {
		return agent;
	}
	public void setAgent(String agent) {
		this.agent = agent;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getIpaddr() {
		return ipaddr;
	}
	public void setIpaddr(String ipaddr) {
		this.ipaddr = ipaddr;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public String getCaller() {
		return caller;
	}
	public void setCaller(String caller) {
		this.caller = caller;
	}
	public String getTouser() {
		return touser;
	}
	public void setTouser(String touser) {
		this.touser = touser;
	}
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}
	public long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
	}
	public String getLocaldatetime() {
		return localdatetime;
	}
	public void setLocaldatetime(String localdatetime) {
		this.localdatetime = localdatetime;
	}
	public String getCalling() {
		return calling;
	}
	public void setCalling(String calling) {
		this.calling = calling;
	}
	public String getCalled() {
		return called;
	}
	public void setCalled(String called) {
		this.called = called;
	}
	public String getAni() {
		return ani;
	}
	public void setAni(String ani) {
		this.ani = ani;
	}
	public String getAgentype() {
		return agentype;
	}
	public void setAgentype(String agentype) {
		this.agentype = agentype;
	}
	public String getQuene() {
		return quene;
	}
	public void setQuene(String quene) {
		this.quene = quene;
	}
	@Override
	public int compareTo(StatusEvent o) {
		return (int) (o.getTime() - this.getTime());
	}
	@Column(name="scurrent")
	public boolean isCurrent() {
		return current;
	}
	public void setCurrent(boolean current) {
		this.current = current;
	}
	public boolean isInit() {
		return init;
	}
	public void setInit(boolean init) {
		this.init = init;
	}
	public String getOrgi() {
		return orgi;
	}
	public void setOrgi(String orgi) {
		this.orgi = orgi;
	}
	@Transient
	public int getCalls() {
		return calls;
	}
	public void setCalls(int calls) {
		this.calls = calls;
	}
	public Date getStarttime() {
		return starttime;
	}
	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}
	public Date getEndtime() {
		return endtime;
	}
	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public Date getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}
	@Column(name="srecord")
	public boolean isRecord() {
		return record;
	}
	public void setRecord(boolean record) {
		this.record = record;
	}
	public int getRecordtime() {
		return recordtime;
	}
	public void setRecordtime(int recordtime) {
		this.recordtime = recordtime;
	}
	public boolean isMisscall() {
		return misscall;
	}
	public void setMisscall(boolean misscall) {
		this.misscall = misscall;
	}
	public boolean isInside() {
		return inside;
	}
	public void setInside(boolean inside) {
		this.inside = inside;
	}
	public Date getStartrecord() {
		return startrecord;
	}
	public void setStartrecord(Date startrecord) {
		this.startrecord = startrecord;
	}
	public Date getEndrecord() {
		return endrecord;
	}
	public void setEndrecord(Date endrecord) {
		this.endrecord = endrecord;
	}
	public Date getAnswertime() {
		return answertime;
	}
	public void setAnswertime(Date answertime) {
		this.answertime = answertime;
	}
	public int getRingduration() {
		return ringduration;
	}
	public void setRingduration(int ringduration) {
		this.ringduration = ringduration;
	}
	public boolean isServicesummary() {
		return servicesummary;
	}
	public void setServicesummary(boolean servicesummary) {
		this.servicesummary = servicesummary;
	}
	public String getServiceid() {
		return serviceid;
	}
	public void setServiceid(String serviceid) {
		this.serviceid = serviceid;
	}
	public String getRecordfile() {
		return recordfile;
	}
	public void setRecordfile(String recordfile) {
		this.recordfile = recordfile;
	}
	public boolean isCallback() {
		return callback;
	}
	public void setCallback(boolean callback) {
		this.callback = callback;
	}
	public String getCcquene() {
		return ccquene;
	}
	public void setCcquene(String ccquene) {
		this.ccquene = ccquene;
	}
	public String getServicestatus() {
		return servicestatus;
	}
	public void setServicestatus(String servicestatus) {
		this.servicestatus = servicestatus;
	}
	public String getChannelstatus() {
		return channelstatus;
	}
	public void setChannelstatus(String channelstatus) {
		this.channelstatus = channelstatus;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getIsp() {
		return isp;
	}
	public void setIsp(String isp) {
		this.isp = isp;
	}
	public String getVoicecalled() {
		return voicecalled;
	}
	public void setVoicecalled(String voicecalled) {
		this.voicecalled = voicecalled;
	}
	public String getContactsid() {
		return contactsid;
	}
	public void setContactsid(String contactsid) {
		this.contactsid = contactsid;
	}
	public String getExtention() {
		return extention;
	}
	public void setExtention(String extention) {
		this.extention = extention;
	}
	public String getHostid() {
		return hostid;
	}
	public void setHostid(String hostid) {
		this.hostid = hostid;
	}
	public String getCalltype() {
		return calltype;
	}
	public void setCalltype(String calltype) {
		this.calltype = calltype;
	}
	public String getCalldir() {
		return calldir;
	}
	public void setCalldir(String calldir) {
		this.calldir = calldir;
	}
	public String getOtherdir() {
		return otherdir;
	}
	public void setOtherdir(String otherdir) {
		this.otherdir = otherdir;
	}
	public String getOtherlegdest() {
		return otherlegdest;
	}
	public void setOtherlegdest(String otherlegdest) {
		this.otherlegdest = otherlegdest;
	}
	public String getBridgeid() {
		return bridgeid;
	}
	public void setBridgeid(String bridgeid) {
		this.bridgeid = bridgeid;
	}
	public boolean isBridge() {
		return bridge;
	}
	public void setBridge(boolean bridge) {
		this.bridge = bridge;
	}
	public String getRecordfilename() {
		return recordfilename;
	}
	public void setRecordfilename(String recordfilename) {
		this.recordfilename = recordfilename;
	}
	public String getDiscaller() {
		return discaller;
	}
	public void setDiscaller(String discaller) {
		this.discaller = discaller;
	}
	public String getDiscalled() {
		return discalled;
	}
	public void setDiscalled(String discalled) {
		this.discalled = discalled;
	}
	public boolean isSatisf() {
		return satisf;
	}
	public void setSatisf(boolean satisf) {
		this.satisf = satisf;
	}
	public String getSatisfaction() {
		return satisfaction;
	}
	public void setSatisfaction(String satisfaction) {
		this.satisfaction = satisfaction;
	}
	public Date getSatisfdate() {
		return satisfdate;
	}
	public void setSatisfdate(Date satisfdate) {
		this.satisfdate = satisfdate;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}

	public String getTaskid() {
		return taskid;
	}
	public void setTaskid(String taskid) {
		this.taskid = taskid;
	}
	public String getActid() {
		return actid;
	}
	public void setActid(String actid) {
		this.actid = actid;
	}
	public String getBatid() {
		return batid;
	}
	public void setBatid(String batid) {
		this.batid = batid;
	}
	public String getDataid() {
		return dataid;
	}
	public void setDataid(String dataid) {
		this.dataid = dataid;
	}
	public String getDisphonenum() {
		return disphonenum;
	}
	public void setDisphonenum(String disphonenum) {
		this.disphonenum = disphonenum;
	}
	public String getDistype() {
		return distype;
	}
	public void setDistype(String distype) {
		this.distype = distype;
	}
	public String getNameid() {
		return nameid;
	}
	public void setNameid(String nameid) {
		this.nameid = nameid;
	}
	public String getSiptrunk() {
		return siptrunk;
	}
	public void setSiptrunk(String siptrunk) {
		this.siptrunk = siptrunk;
	}
	public boolean isPrefix() {
		return prefix;
	}
	public void setPrefix(boolean prefix) {
		this.prefix = prefix;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getOrgan() {
		return organ;
	}
	public void setOrgan(String organ) {
		this.organ = organ;
	}
	public String getBusstype() {
		return busstype;
	}
	public void setBusstype(String busstype) {
		this.busstype = busstype;
	}
	public String getMetaname() {
		return metaname;
	}
	public void setMetaname(String metaname) {
		this.metaname = metaname;
	}
	public boolean isWaste() {
		return waste;
	}
	public void setWaste(boolean waste) {
		this.waste = waste;
	}
	public String getCreater() {
		return creater;
	}
	public void setCreater(String creater) {
		this.creater = creater;
	}
	public boolean isCallstatus() {
		return callstatus;
	}
	public void setCallstatus(boolean callstatus) {
		this.callstatus = callstatus;
	}
	public boolean isApstatus() {
		return apstatus;
	}
	public void setApstatus(boolean apstatus) {
		this.apstatus = apstatus;
	}
	public String getQualitystatus() {
		return qualitystatus;
	}
	public void setQualitystatus(String qualitystatus) {
		this.qualitystatus = qualitystatus;
	}
	public String getQualitydisorgan() {
		return qualitydisorgan;
	}
	public void setQualitydisorgan(String qualitydisorgan) {
		this.qualitydisorgan = qualitydisorgan;
	}
	public String getQualitydisuser() {
		return qualitydisuser;
	}
	public void setQualitydisuser(String qualitydisuser) {
		this.qualitydisuser = qualitydisuser;
	}
	public String getQualityorgan() {
		return qualityorgan;
	}
	public void setQualityorgan(String qualityorgan) {
		this.qualityorgan = qualityorgan;
	}
	public String getQualityuser() {
		return qualityuser;
	}
	public void setQualityuser(String qualityuser) {
		this.qualityuser = qualityuser;
	}
	public int getQualityscore() {
		return qualityscore;
	}
	public void setQualityscore(int qualityscore) {
		this.qualityscore = qualityscore;
	}
	public Date getQualitytime() {
		return qualitytime;
	}
	public void setQualitytime(Date qualitytime) {
		this.qualitytime = qualitytime;
	}
	public String getQualitytype() {
		return qualitytype;
	}
	public void setQualitytype(String qualitytype) {
		this.qualitytype = qualitytype;
	}
	public Date getQualitydistime() {
		return qualitydistime;
	}
	public void setQualitydistime(Date qualitydistime) {
		this.qualitydistime = qualitydistime;
	}
	public String getAssuser() {
		return assuser;
	}
	public void setAssuser(String assuser) {
		this.assuser = assuser;
	}
	public String getTemplateid() {
		return templateid;
	}
	public void setTemplateid(String templateid) {
		this.templateid = templateid;
	}
	public String getQualitydistype() {
		return qualitydistype;
	}
	public void setQualitydistype(String qualitydistype) {
		this.qualitydistype = qualitydistype;
	}
	public String getQualityactid() {
		return qualityactid;
	}
	public void setQualityactid(String qualityactid) {
		this.qualityactid = qualityactid;
	}
	public String getQualityfilterid() {
		return qualityfilterid;
	}
	public void setQualityfilterid(String qualityfilterid) {
		this.qualityfilterid = qualityfilterid;
	}
	public int getTrans() {
		return trans;
	}
	public void setTrans(int trans) {
		this.trans = trans;
	}
	public Date getTransbegin() {
		return transbegin;
	}
	public void setTransbegin(Date transbegin) {
		this.transbegin = transbegin;
	}
	public Date getTransend() {
		return transend;
	}
	public void setTransend(Date transend) {
		this.transend = transend;
	}
	public String getTranstime() {
		return transtime;
	}
	public void setTranstime(String transtime) {
		this.transtime = transtime;
	}
	public String getTranstatus() {
		return transtatus;
	}
	public void setTranstatus(String transtatus) {
		this.transtatus = transtatus;
	}
	public int getTranscost() {
		return transcost;
	}
	public void setTranscost(int transcost) {
		this.transcost = transcost;
	}
	public int getQualitypass() {
		return qualitypass;
	}
	public void setQualitypass(int qualitypass) {
		this.qualitypass = qualitypass;
	}
	public String getTranid() {
		return tranid;
	}
	public void setTranid(String tranid) {
		this.tranid = tranid;
	}
	public boolean isForecast() {
		return forecast;
	}
	public void setForecast(boolean forecast) {
		this.forecast = forecast;
	}
	public String getSkill() {
		return skill;
	}
	public void setSkill(String skill) {
		this.skill = skill;
	}
	public String getForecastid() {
		return forecastid;
	}
	public void setForecastid(String forecastid) {
		this.forecastid = forecastid;
	}
	public String getCallresult() {
		return callresult;
	}
	public void setCallresult(String callresult) {
		this.callresult = callresult;
	}
	public boolean isDtmf() {
		return dtmf;
	}
	public void setDtmf(boolean dtmf) {
		this.dtmf = dtmf;
	}
	public String getDtmfrec() {
		return dtmfrec;
	}
	public void setDtmfrec(String dtmfrec) {
		this.dtmfrec = dtmfrec;
	}
	public String getHangupcase() {
		return hangupcase;
	}
	public void setHangupcase(String hangupcase) {
		this.hangupcase = hangupcase;
	}
	public String getHangupinitiator() {
		return hangupinitiator;
	}
	public void setHangupinitiator(String hangupinitiator) {
		this.hangupinitiator = hangupinitiator;
	}
	public String getSipaddr() {
		return sipaddr;
	}
	public void setSipaddr(String sipaddr) {
		this.sipaddr = sipaddr;
	}
	@Transient
	public boolean isAutoanswer() {
		return autoanswer;
	}
	public void setAutoanswer(boolean autoanswer) {
		this.autoanswer = autoanswer;
	}

	public String getOssstatus() {
		return ossstatus;
	}

	public void setOssstatus(String ossstatus) {
		this.ossstatus = ossstatus;
	}
	public String getCoreuuid() {
		return coreuuid;
	}
	public void setCoreuuid(String coreuuid) {
		this.coreuuid = coreuuid;
	}
	public Date getInqueuetime() {
		return inqueuetime;
	}
	public void setInqueuetime(Date inqueuetime) {
		this.inqueuetime = inqueuetime;
	}
	public Date getOutqueuetime() {
		return outqueuetime;
	}
	public void setOutqueuetime(Date outqueuetime) {
		this.outqueuetime = outqueuetime;
	}
	public int getQueuetime() {
		return queuetime;
	}
	public void setQueuetime(int queuetime) {
		this.queuetime = queuetime;
	}
	public String getMembersessionid() {
		return membersessionid;
	}
	public void setMembersessionid(String membersessionid) {
		this.membersessionid = membersessionid;
	}
	public String getQualityresult() {
		return qualityresult;
	}
	public void setQualityresult(String qualityresult) {
		this.qualityresult = qualityresult;
	}
	public String getItemid() {
		return itemid;
	}
	public void setItemid(String itemid) {
		this.itemid = itemid;
	}

	public String getAccountdes() {
		return accountdes;
	}
	public void setAccountdes(String accountdes) {
		this.accountdes = accountdes;
	}
	public boolean isConference() {
		return conference;
	}
	public void setConference(boolean conference) {
		this.conference = conference;
	}
	public Date getInconferenecetime() {
		return inconferenecetime;
	}
	public void setInconferenecetime(Date inconferenecetime) {
		this.inconferenecetime = inconferenecetime;
	}
	public long getConferenceduration() {
		return conferenceduration;
	}
	public void setConferenceduration(long conferenceduration) {
		this.conferenceduration = conferenceduration;
	}
	public String getConferencenum() {
		return conferencenum;
	}
	public void setConferencenum(String conferencenum) {
		this.conferencenum = conferencenum;
	}
	public String getConferenceid() {
		return conferenceid;
	}
	public void setConferenceid(String conferenceid) {
		this.conferenceid = conferenceid;
	}
	public boolean isConferenceinitiator() {
		return conferenceinitiator;
	}
	public void setConferenceinitiator(boolean conferenceinitiator) {
		this.conferenceinitiator = conferenceinitiator;
	}


	public Integer getLevelscore() {
		return levelscore;
	}

	public void setLevelscore(Integer levelscore) {
		this.levelscore = levelscore;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public Integer getFocustimes() {
		return focustimes;
	}

	public void setFocustimes(Integer focustimes) {
		this.focustimes = focustimes;
	}

	public String getProcessid() {
		return processid;
	}

	public void setProcessid(String processid) {
		this.processid = processid;
	}

	public String getGateway() {
		return gateway;
	}
	public void setGateway(String gateway) {
		this.gateway = gateway;
	}
	public boolean isGatewaycount() {
		return gatewaycount;
	}
	public void setGatewaycount(boolean gatewaycount) {
		this.gatewaycount = gatewaycount;
	}
	
	public boolean isAitrans() {
		return aitrans;
	}
	public void setAitrans(boolean aitrans) {
		this.aitrans = aitrans;
	}
	public String getAiext() {
		return aiext;
	}
	public void setAiext(String aiext) {
		this.aiext = aiext;
	}
	public String getAitransqus() {
		return aitransqus;
	}
	public void setAitransqus(String aitransqus) {
		this.aitransqus = aitransqus;
	}
	public Date getAitranstime() {
		return aitranstime;
	}
	public void setAitranstime(Date aitranstime) {
		this.aitranstime = aitranstime;
	}
	public long getAitransduration() {
		return aitransduration;
	}
	public void setAitransduration(long aitransduration) {
		this.aitransduration = aitransduration;
	}
	
	public boolean isAi() {
		return ai;
	}
	public void setAi(boolean ai) {
		this.ai = ai;
	}
	public String getAieventid() {
		return aieventid;
	}
	public void setAieventid(String aieventid) {
		this.aieventid = aieventid;
	}
	public int getAsrtimes() {
		return asrtimes;
	}
	public void setAsrtimes(int asrtimes) {
		this.asrtimes = asrtimes;
	}
	public int getTtstimes() {
		return ttstimes;
	}
	public void setTtstimes(int ttstimes) {
		this.ttstimes = ttstimes;
	}
	public int getTimeouttimes() {
		return timeouttimes;
	}
	public void setTimeouttimes(int timeouttimes) {
		this.timeouttimes = timeouttimes;
	}
	public int getErrortimes() {
		return errortimes;
	}
	public void setErrortimes(int errortimes) {
		this.errortimes = errortimes;
	}
	public int getNmlinetimes() {
		return nmlinetimes;
	}
	public void setNmlinetimes(int nmlinetimes) {
		this.nmlinetimes = nmlinetimes;
	}
	public int getWaittime() {
		return waittime;
	}
	public void setWaittime(int waittime) {
		this.waittime = waittime;
	}
	public boolean isTransfaild() {
		return transfaild;
	}
	public void setTransfaild(boolean transfaild) {
		this.transfaild = transfaild;
	}
	public String getPriphone() {
		return priphone;
	}
	public void setPriphone(String priphone) {
		this.priphone = priphone;
	}
	public boolean isTranscon() {
		return transcon;
	}
	public void setTranscon(boolean transcon) {
		this.transcon = transcon;
	}
	public String getTransconid() {
		return transconid;
	}
	public void setTransconid(String transconid) {
		this.transconid = transconid;
	}
	@Override
	public String toString() {
		return "StatusEvent{" +
				"id='" + id + '\'' +
				", createtime=" + createtime +
				", updatetime=" + updatetime +
				", inside=" + inside +
				", code='" + code + '\'' +
				", creater='" + creater + '\'' +
				", source='" + source + '\'' +
				", answer='" + answer + '\'' +
				", callback=" + callback +
				", ccquene='" + ccquene + '\'' +
				", calltype='" + calltype + '\'' +
				", voicecalled='" + voicecalled + '\'' +
				", servicestatus='" + servicestatus + '\'' +
				", channelstatus='" + channelstatus + '\'' +
				", inqueuetime=" + inqueuetime +
				", outqueuetime=" + outqueuetime +
				", queuetime=" + queuetime +
				", autoanswer=" + autoanswer +
				", answertime=" + answertime +
				", ringduration=" + ringduration +
				", current=" + current +
				", forecast=" + forecast +
				", skill='" + skill + '\'' +
				", forecastid='" + forecastid + '\'' +
				", init=" + init +
				", coreuuid='" + coreuuid + '\'' +
				", caller='" + caller + '\'' +
				", calling='" + calling + '\'' +
				", called='" + called + '\'' +
				", discaller='" + discaller + '\'' +
				", discalled='" + discalled + '\'' +
				", agentype='" + agentype + '\'' +
				", quene='" + quene + '\'' +
				", ani='" + ani + '\'' +
				", touser='" + touser + '\'' +
				", direction='" + direction + '\'' +
				", calldir='" + calldir + '\'' +
				", otherdir='" + otherdir + '\'' +
				", waste=" + waste +
				", apstatus=" + apstatus +
				", otherlegdest='" + otherlegdest + '\'' +
				", time=" + time +
				", localdatetime='" + localdatetime + '\'' +
				", starttime=" + starttime +
				", endtime=" + endtime +
				", duration=" + duration +
				", membersessionid='" + membersessionid + '\'' +
				", status='" + status + '\'' +
				", state='" + state + '\'' +
				", agent='" + agent + '\'' +
				", action='" + action + '\'' +
				", name='" + name + '\'' +
				", host='" + host + '\'' +
				", ipaddr='" + ipaddr + '\'' +
				", sipaddr='" + sipaddr + '\'' +
				", extention='" + extention + '\'' +
				", hostid='" + hostid + '\'' +
				", metaname='" + metaname + '\'' +
				", taskid='" + taskid + '\'' +
				", actid='" + actid + '\'' +
				", batid='" + batid + '\'' +
				", dataid='" + dataid + '\'' +
				", nameid='" + nameid + '\'' +
				", disphonenum='" + disphonenum + '\'' +
				", distype='" + distype + '\'' +
				", busstype='" + busstype + '\'' +
				", siptrunk='" + siptrunk + '\'' +
				", prefix=" + prefix +
				", callstatus=" + callstatus +
				", qualitystatus='" + qualitystatus + '\'' +
				", qualitydisorgan='" + qualitydisorgan + '\'' +
				", qualitydisuser='" + qualitydisuser + '\'' +
				", qualitydistime=" + qualitydistime +
				", assuser='" + assuser + '\'' +
				", templateid='" + templateid + '\'' +
				", qualitydistype='" + qualitydistype + '\'' +
				", qualityorgan='" + qualityorgan + '\'' +
				", qualityuser='" + qualityuser + '\'' +
				", qualityscore=" + qualityscore +
				", qualitytime=" + qualitytime +
				", qualitytype='" + qualitytype + '\'' +
				", qualityactid='" + qualityactid + '\'' +
				", qualityfilterid='" + qualityfilterid + '\'' +
				", qualityresult='" + qualityresult + '\'' +
				", record=" + record +
				", startrecord=" + startrecord +
				", endrecord=" + endrecord +
				", recordtime=" + recordtime +
				", recordfile='" + recordfile + '\'' +
				", recordfilename='" + recordfilename + '\'' +
				", contactsid='" + contactsid + '\'' +
				", bridgeid='" + bridgeid + '\'' +
				", bridge=" + bridge +
				", misscall=" + misscall +
				", servicesummary=" + servicesummary +
				", serviceid='" + serviceid + '\'' +
				", calls=" + calls +
				", orgi='" + orgi + '\'' +
				", country='" + country + '\'' +
				", province='" + province + '\'' +
				", city='" + city + '\'' +
				", isp='" + isp + '\'' +
				", satisf=" + satisf +
				", satisfaction='" + satisfaction + '\'' +
				", satisfdate=" + satisfdate +
				", userid='" + userid + '\'' +
				", username='" + username + '\'' +
				", organ='" + organ + '\'' +
				", dtmf=" + dtmf +
				", dtmfrec='" + dtmfrec + '\'' +
				", trans=" + trans +
				", transbegin=" + transbegin +
				", transend=" + transend +
				", transtime='" + transtime + '\'' +
				", transtatus='" + transtatus + '\'' +
				", transcost=" + transcost +
				", tranid='" + tranid + '\'' +
				", qualitypass=" + qualitypass +
				", workstatus='" + workstatus + '\'' +
				", hangupcase='" + hangupcase + '\'' +
				", hangupinitiator='" + hangupinitiator + '\'' +
				", ossstatus='" + ossstatus + '\'' +
				", itemid='" + itemid + '\'' +
				", extno='" + extno + '\'' +
				", callresult='" + callresult + '\'' +
				", numberpoolid='" + numberpoolid + '\'' +
				", numberpoolname='" + numberpoolname + '\'' +
				", durationcost=" + durationcost +
				'}';
	}
	public boolean isIgr() {
		return igr;
	}
	public void setIgr(boolean igr) {
		this.igr = igr;
	}
	public String getIgrage() {
		return igrage;
	}
	public void setIgrage(String igrage) {
		this.igrage = igrage;
	}
	public String getIgrchild() {
		return igrchild;
	}
	public void setIgrchild(String igrchild) {
		this.igrchild = igrchild;
	}
	public String getIgrmiddle() {
		return igrmiddle;
	}
	public void setIgrmiddle(String igrmiddle) {
		this.igrmiddle = igrmiddle;
	}
	public String getIgrold() {
		return igrold;
	}
	public void setIgrold(String igrold) {
		this.igrold = igrold;
	}
	public String getIgrgender() {
		return igrgender;
	}
	public void setIgrgender(String igrgender) {
		this.igrgender = igrgender;
	}
	public String getIgrfemale() {
		return igrfemale;
	}
	public void setIgrfemale(String igrfemale) {
		this.igrfemale = igrfemale;
	}
	public String getIgrmale() {
		return igrmale;
	}
	public void setIgrmale(String igrmale) {
		this.igrmale = igrmale;
	}
	public Date getIgrbegin() {
		return igrbegin;
	}
	public void setIgrbegin(Date igrbegin) {
		this.igrbegin = igrbegin;
	}
	public Date getIgrend() {
		return igrend;
	}
	public void setIgrend(Date igrend) {
		this.igrend = igrend;
	}
	public boolean isAicollect() {
		return aicollect;
	}
	public void setAicollect(boolean aicollect) {
		this.aicollect = aicollect;
	}
	public String getIgrstatus() {
		return igrstatus;
	}
	public void setIgrstatus(String igrstatus) {
		this.igrstatus = igrstatus;
	}
	public String getIgrvoice() {
		return igrvoice;
	}
	public void setIgrvoice(String igrvoice) {
		this.igrvoice = igrvoice;
	}
	public int getIgrvoicetime() {
		return igrvoicetime;
	}
	public void setIgrvoicetime(int igrvoicetime) {
		this.igrvoicetime = igrvoicetime;
	}
	public String getCon_surnames() {
		return con_surnames;
	}
	public void setCon_surnames(String con_surnames) {
		this.con_surnames = con_surnames;
	}
	public String getCon_intention() {
		return con_intention;
	}
	public void setCon_intention(String con_intention) {
		this.con_intention = con_intention;
	}
	public String getCon_quality() {
		return con_quality;
	}
	public void setCon_quality(String con_quality) {
		this.con_quality = con_quality;
	}
	public String getCon_qualitysubmit() {
		return con_qualitysubmit;
	}
	public void setCon_qualitysubmit(String con_qualitysubmit) {
		this.con_qualitysubmit = con_qualitysubmit;
	}
	public String getCon_qualitypass() {
		return con_qualitypass;
	}
	public void setCon_qualitypass(String con_qualitypass) {
		this.con_qualitypass = con_qualitypass;
	}
	public String getAitransans() {
		return aitransans;
	}
	public void setAitransans(String aitransans) {
		this.aitransans = aitransans;
	}
	public boolean isAutoquality() {
		return autoquality;
	}
	public void setAutoquality(boolean autoquality) {
		this.autoquality = autoquality;
	}
	public boolean isSpotqc() {
		return spotqc;
	}
	public void setSpotqc(boolean spotqc) {
		this.spotqc = spotqc;
	}
	public Date getSpotqctime() {
		return spotqctime;
	}
	public void setSpotqctime(Date spotqctime) {
		this.spotqctime = spotqctime;
	}
	public boolean isSpotqcsuccess() {
		return spotqcsuccess;
	}
	public void setSpotqcsuccess(boolean spotqcsuccess) {
		this.spotqcsuccess = spotqcsuccess;
	}
	public boolean isAppealqc() {
		return appealqc;
	}
	public void setAppealqc(boolean appealqc) {
		this.appealqc = appealqc;
	}
	public boolean isArbitrateqc() {
		return arbitrateqc;
	}
	public void setArbitrateqc(boolean arbitrateqc) {
		this.arbitrateqc = arbitrateqc;
	}
	public String getExtdata1() {
		return extdata1;
	}
	public void setExtdata1(String extdata1) {
		this.extdata1 = extdata1;
	}
	public String getExtdata2() {
		return extdata2;
	}
	public void setExtdata2(String extdata2) {
		this.extdata2 = extdata2;
	}
	public String getExtdata3() {
		return extdata3;
	}
	public void setExtdata3(String extdata3) {
		this.extdata3 = extdata3;
	}
	public String getExtdata4() {
		return extdata4;
	}
	public void setExtdata4(String extdata4) {
		this.extdata4 = extdata4;
	}
	public String getExtdata5() {
		return extdata5;
	}
	public void setExtdata5(String extdata5) {
		this.extdata5 = extdata5;
	}
	public String getExtdata6() {
		return extdata6;
	}
	public void setExtdata6(String extdata6) {
		this.extdata6 = extdata6;
	}
	@Transient
	public boolean isRest() {
		return rest;
	}
	public void setRest(boolean rest) {
		this.rest = rest;
	}
	public String getHidetype() {
		return hidetype;
	}
	public void setHidetype(String hidetype) {
		this.hidetype = hidetype;
	}
	public boolean isIvr() {
		return ivr;
	}
	public void setIvr(boolean ivr) {
		this.ivr = ivr;
	}
	public Date getIvrentertime() {
		return ivrentertime;
	}
	public void setIvrentertime(Date ivrentertime) {
		this.ivrentertime = ivrentertime;
	}
	public Date getIvrleavetime() {
		return ivrleavetime;
	}
	public void setIvrleavetime(Date ivrleavetime) {
		this.ivrleavetime = ivrleavetime;
	}
	public int getIvrtime() {
		return ivrtime;
	}
	public void setIvrtime(int ivrtime) {
		this.ivrtime = ivrtime;
	}
	public String getTimehour() {
		return timehour;
	}
	public void setTimehour(String timehour) {
		this.timehour = timehour;
	}
	public String getTimehourmin() {
		return timehourmin;
	}
	public void setTimehourmin(String timehourmin) {
		this.timehourmin = timehourmin;
	}
	public String getTransresult() {
		return transresult;
	}
	public void setTransresult(String transresult) {
		this.transresult = transresult;
	}
	public int getTemplatever() {
		return templatever;
	}
	public void setTemplatever(int templatever) {
		this.templatever = templatever;
	}

	public String getNumberpoolid() {
		return numberpoolid;
	}

	public void setNumberpoolid(String numberpoolid) {
		this.numberpoolid = numberpoolid;
	}

	public String getNumberpoolname() {
		return numberpoolname;
	}

	public void setNumberpoolname(String numberpoolname) {
		this.numberpoolname = numberpoolname;
	}

	public int getDurationcost() {
		return durationcost;
	}

	public void setDurationcost(int durationcost) {
		this.durationcost = durationcost;
	}
}

package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.util.Objects;


/**
 * 号码池
 *
 */
@Entity
@Table(name="uk_number_pool")
@org.hibernate.annotations.Proxy(lazy = false)
public class NumberPool implements java.io.Serializable{

	private static final long serialVersionUID = -5540389613813168608L;

	@Id
	private String id;

	private String name;//号码名称

	private String hostid;

	private String creater;
	private String updater;
	private Date createtime = new Date();
	private Date updatetime = new Date();
	
	private boolean datastatus ;//数据状态，是否已删除 0否 1是

	private String number;//号码

	private String orgi;
	
	private String siptrunkid;//所属SIP网关
	
	private String province ;		//号码省份
	private String city ;			//号码城市

	//@Transient
	private int count ;			//号码呼出数量、存入redis

	/**
	 * @return the id
	 */
	@Id
	@Column(length = 32)
//	@GeneratedValue(generator = "system-uuid")
//	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHostid() {
		return hostid;
	}

	public void setHostid(String hostid) {
		this.hostid = hostid;
	}

	public String getCreater() {
		return creater;
	}

	public void setCreater(String creater) {
		this.creater = creater;
	}

	public String getUpdater() {
		return updater;
	}

	public void setUpdater(String updater) {
		this.updater = updater;
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

	public boolean isDatastatus() {
		return datastatus;
	}

	public void setDatastatus(boolean datastatus) {
		this.datastatus = datastatus;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getOrgi() {
		return orgi;
	}

	public void setOrgi(String orgi) {
		this.orgi = orgi;
	}

	public String getSiptrunkid() {
		return siptrunkid;
	}

	public void setSiptrunkid(String siptrunkid) {
		this.siptrunkid = siptrunkid;
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


	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		NumberPool that = (NumberPool) o;
		return Objects.equals(id, that.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
}

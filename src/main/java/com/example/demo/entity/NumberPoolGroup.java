package com.example.demo.entity;


import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.util.Date;


/**
 * 号码组管理表
 *
 */
@Entity
@Table(name = "uk_number_pool_group")
@org.hibernate.annotations.Proxy(lazy = false)
public class NumberPoolGroup implements java.io.Serializable{

	private static final long serialVersionUID = -554038916133168228L;

	@Id
	private String id;

	private String hostid;
	private String creater;
	private String updater;
	private Date createtime = new Date();
	private Date updatetime = new Date();
	private boolean datastatus ;//数据状态，是否已删除 0否 1是
	private String memo;//号码的id
	private String orgi;
	private String name;//线路组名称
	private Integer count;//线路组名称
	private String strategy;//线路组策略  poll:轮询 attribution:归属地外显    fixedaftercall:接通后固定外显

	/**
	 * @return the id
	 */
	@Id
	@Column(length = 32)
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getOrgi() {
		return orgi;
	}

	public void setOrgi(String orgi) {
		this.orgi = orgi;
	}


	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public String getStrategy() {
		return strategy;
	}

	public void setStrategy(String strategy) {
		this.strategy = strategy;
	}
}

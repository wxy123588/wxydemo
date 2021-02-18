package com.example.demo.entity;


import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.util.Date;
import java.util.Objects;


/**
 * 号码组和号码关系表
 *
 */
@Entity
@Table(name = "uk_number_pool_group_rela")
@org.hibernate.annotations.Proxy(lazy = false)
public class NumberPoolGroupRela implements java.io.Serializable{

	private static final long serialVersionUID = -554038916133168228L;

	@Id
	private String id;

	private String hostid;
	private String creater;
	private String updater;
	private Date createtime = new Date();
	private Date updatetime = new Date();
	private boolean datastatus ;//数据状态，是否已删除 0否 1是
	private String numbergroupid;//分组id
	private String numberpoolid;//号码的id
	private String orgi;
	private String outnum;
	private String siptrunkname;

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

	public String getNumbergroupid() {
		return numbergroupid;
	}

	public void setNumbergroupid(String numbergroupid) {
		this.numbergroupid = numbergroupid;
	}

	public String getNumberpoolid() {
		return numberpoolid;
	}

	public void setNumberpoolid(String numberpoolid) {
		this.numberpoolid = numberpoolid;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		NumberPoolGroupRela that = (NumberPoolGroupRela) o;
		return numbergroupid.equals(that.numbergroupid) &&
				numberpoolid.equals(that.numberpoolid) &&
				orgi.equals(that.orgi);
	}

	@Override
	public int hashCode() {
		return Objects.hash(numbergroupid, numberpoolid, orgi);
	}

	public String getOutnum() {
		return outnum;
	}

	public void setOutnum(String outnum) {
		this.outnum = outnum;
	}

	public String getSiptrunkname() {
		return siptrunkname;
	}

	public void setSiptrunkname(String siptrunkname) {
		this.siptrunkname = siptrunkname;
	}
}

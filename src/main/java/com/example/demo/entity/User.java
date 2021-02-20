package com.example.demo.entity;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.*;


/**
 * @author jaddy0302 Rivulet User.java 2010-3-17
 * 
 */
@Entity
@Table(name = "uk_user")
@org.hibernate.annotations.Proxy(lazy = false)
public class User implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    @Id
	private String id ;

	private String orgi ;

	private String status ;		//
	private boolean datastatus ;//数据状态，是否已删除	


	private Boolean numgroupenable=true; //true表示使用线路组， 呼叫的时候取numbergroupid；false表示用线路，取numberpoolid
	private String numberpoolid;   //线路id
	private String numbergroupid;  //线路组id

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOrgi() {
		return orgi;
	}

	public void setOrgi(String orgi) {
		this.orgi = orgi;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public boolean isDatastatus() {
		return datastatus;
	}

	public void setDatastatus(boolean datastatus) {
		this.datastatus = datastatus;
	}

	public Boolean getNumgroupenable() {
		return numgroupenable;
	}

	public void setNumgroupenable(Boolean numgroupenable) {
		this.numgroupenable = numgroupenable;
	}

	public String getNumberpoolid() {
		return numberpoolid;
	}

	public void setNumberpoolid(String numberpoolid) {
		this.numberpoolid = numberpoolid;
	}

	public String getNumbergroupid() {
		return numbergroupid;
	}

	public void setNumbergroupid(String numbergroupid) {
		this.numbergroupid = numbergroupid;
	}
}

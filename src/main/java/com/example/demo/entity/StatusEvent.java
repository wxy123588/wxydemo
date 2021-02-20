
package com.example.demo.entity;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import org.hibernate.annotations.Proxy;


@Entity
@Table(name = "uk_callcenter_event")
@Proxy(lazy = false)
public class StatusEvent implements java.io.Serializable{

	private static final long serialVersionUID = 2796276564445713776L;

	private String id ;

	private Date createtime = new Date();


	private String answer ;//应答时间

	private String calling ;//呼叫对象
	private String called ;//被叫号码

	private String discaller;//
	private String discalled;//

	private String userid;

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

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
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

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}
}

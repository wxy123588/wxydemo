package com.example.demo.util;

import com.example.demo.util.RestResultType;

public class RestResult implements java.io.Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 783408637220961119L;
	private RestResultType status ;
	
	public RestResult(RestResultType status , Object data){
		this.status = status ;
		this.data = data ;
	}
	public RestResult(RestResultType status , Object data, Object msg){
		this.status = status ;
		this.data = data ;
		this.msg=msg;
	}
	
	public RestResult(RestResultType status){
		this.status = status ;
	}
	
	public Object data ;
	public Object msg;

	public RestResultType getStatus() {
		return status;
	}

	public void setStatus(RestResultType status) {
		this.status = status;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public Object getMsg() {
		return msg;
	}

	public void setMsg(Object msg) {
		this.msg = msg;
	}
	
	
}

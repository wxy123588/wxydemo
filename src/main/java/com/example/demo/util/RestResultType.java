package com.example.demo.util;

public enum RestResultType {
	OK(200, "OK") ,
	AUTH_ERROR(300, "AUTHORIZATION FAILD") ,
	USER_DELETE(400, "ADMIN USER"),
	ORGAN_DELETE(500, "NOT EXIST"), 
	LACKDATA(501, "LACK OF NECESSARY PARAMETERS") ,
	WORKORDERS_DELETE(600 , "WORKORDERS NOT EXIST"),
	WORKORDERS_NOTEXIST(601 , "WORKORDERS NOT EXIST"),
	XIAOE_TOPIC_DELETE(700, "TOPIC NOT EXIST") , 
	XIAOE_TOPIC_NOT_EMPTY(701, "TYPE NOT EXIST") , 
	XIAOE_TYPE_DELETE(702, "TYPE NOT EXIST"),
	DATA_DOES_NOT_EXIST(480,"DATA DOES NOT EXIST"),//数据不存在(统一使用该项反馈);
	NO_PASS_MUST_ENTER(604,"NO PASS MUST ENTER"),//没有传必输项
	PRODUCT_TITLE_NOTEXIST(800, "PRODUCT TITLE NOTEXIST"),
	SYSTEM_ERROR(900, "SYSTEM ERROR");

	public int status ;
	private String message ;
	
	RestResultType(int status, String message) {
		this.status = status;
		this.message = message;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}

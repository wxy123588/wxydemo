package com.example.demo.mobile;


import org.springframework.util.StringUtils;

public class MobileAddress {
	private String id ;			//ID
	private String code ;		//号段编码
	private String country ;	//国家
	private String province ;	//省份
	private String city ;		//城市
	private String isp ;		//运营商
	
	private String areacode ;	//区号
	private String zipcode ;	//邮编
	
	private String admincode ;	//管理代码
	
	public MobileAddress(String code , String areacode , String province , String city , String isp, String admincode){
		this.code = code ;
		if(!StringUtils.isEmpty(areacode)){
			this.areacode = areacode ;
		}
		this.province = province ;
		this.city = city ;
		this.isp = isp ;
		this.admincode = admincode ;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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

	public String getAreacode() {
		return areacode;
	}

	public void setAreacode(String areacode) {
		this.areacode = areacode;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getAdmincode() {
		return admincode;
	}

	public void setAdmincode(String admincode) {
		this.admincode = admincode;
	}
}

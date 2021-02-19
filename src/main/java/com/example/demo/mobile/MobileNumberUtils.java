package com.example.demo.mobile;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

@Component
public class MobileNumberUtils {

	private static Map<String , MobileAddress> mobileAddressMap  = new HashMap<String ,MobileAddress>();

	public static int init() throws IOException{
		File file = new File( MobileNumberUtils.class.getResource("/config/mobile.data").getFile());
		if(file.exists()){
			FileInputStream reader = new FileInputStream(file);
			InputStreamReader isr = new InputStreamReader(reader , "UTF-8");
			BufferedReader bf = new BufferedReader(isr);
			try{
				String data = null ;
				while((data = bf.readLine()) != null){
					String[] group = data.split("[\t ]") ;
					MobileAddress address = null ;
					if(group.length == 5){
						address = new MobileAddress(group[0], group[1], group[2], group[3],group[4] ,null) ;
					}else if(group.length == 4){
						address = new MobileAddress(group[0], group[1], group[2], group[2],group[3], null) ;
					}else if(group.length == 8){
						address = new MobileAddress(group[1], group[6], group[2], group[3],group[4], group[7]) ;
					}else if(group.length == 3){
						address = new MobileAddress(group[0], group[1], group[2], group[2],null, null) ;
					}
					if(address!=null){
						if(mobileAddressMap.get(address.getCode()) == null){
							mobileAddressMap.put(address.getCode(), address) ;
						}
						if(mobileAddressMap.get(address.getAreacode()) == null){
							mobileAddressMap.put(address.getAreacode(), address) ;
						}
						if(mobileAddressMap.get(address.getCity()) == null){
							mobileAddressMap.put(address.getCity(), address) ;
						}
					}
				}
			}catch(Exception ex){
				ex.printStackTrace();
			}finally{
				bf.close();
				isr.close();
				reader.close();
			}
		}
		return mobileAddressMap.size() ;
	}
	/**
	 * 根据呼入号码 找到对应 城市 , 需要传入的号码是 手机号 或者 固话号码，位数为 11位
	 * @param phonenumber
	 * @return
	 *
	 *
	 */
	public static MobileAddress getAddress(String phonenumber){
		String code = "";
		if(!StringUtils.isEmpty(phonenumber) && phonenumber.length() > 10 && phonenumber.length() <= 12){

			if(phonenumber.startsWith("0")){
				code = phonenumber.substring(0 ,  4) ;
				if(mobileAddressMap.get(code) == null) {
					code = phonenumber.substring(0 ,  3) ;
				}
			}else if(phonenumber.startsWith("1")){
				code = phonenumber.substring(0 , 7) ;
			}
		}else if(!StringUtils.isEmpty(phonenumber) && phonenumber.length() >= 13){
			//国际号码
			if(phonenumber.startsWith("0")){
				code = phonenumber.substring(0 ,  6) ;
				if(mobileAddressMap.get(code) == null) {
					code = phonenumber.substring(0 ,  5) ;
				}
				if(mobileAddressMap.get(code) == null) {
					code = phonenumber.substring(0 ,  4) ;
				}
				if(mobileAddressMap.get(code) == null) {
					code = phonenumber.substring(0 ,  3) ;
				}
			}
		}else if(!StringUtils.isEmpty(phonenumber) && phonenumber.length() <= 9 && phonenumber.length() >= 6 ){

			if(phonenumber.startsWith("0")){
				code = phonenumber.substring(0 ,  4) ;
				if(mobileAddressMap.get(code) == null) {
					code = phonenumber.substring(0 ,  3) ;
				}
			}
		}
		return mobileAddressMap.get(code) ;
	}

	/**
	 * 根据呼入号码 找到对应 城市 , 需要传入的号码是 手机号 或者 固话号码，位数为 11位
	 * @param phonenumber
	 * @return
	 */
	public static boolean isMobileAddress(String phonenumber){
		return !StringUtils.isEmpty(phonenumber) && phonenumber.length() > 10 && phonenumber.startsWith("1") && getAddress(phonenumber)!=null;
	}

	/**
	 * 根据呼入号码 找到对应 城市 , 需要传入的号码是 手机号 或者 固话号码，位数为 11位
	 * @return
	 */
	public static boolean isSameAreacode(String city,MobileAddress address){
		if(!StringUtils.isEmpty(city) && city.endsWith("市")) {
			city = city.substring(0 , city.length() - 1) ;
		}
		MobileAddress temp = mobileAddressMap.get(city);
		return  temp != null && address != null && temp.getAreacode().equals(address.getAreacode());
	}
}

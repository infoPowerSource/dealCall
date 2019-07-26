package com.deal.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {
	
	public final static String EMPTY_STRING = "";

	
	public static String filterNULL(Object obj) {
		return (obj == null) ? EMPTY_STRING : String.valueOf(obj);
	}
	
	public static Integer filterNumberNULL(Object obj) {
		return (obj == null) ? 0 : Integer.valueOf(String.valueOf(obj));
	}
	
	public static boolean isEmptyString(String str) {
		if(str != null && str.length() > 0) {
			return false;
		} else {
			return true;
		}
	}
	
	public static boolean isNotEmptyString(String str) {
		if(str != null && str.length() > 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean isMobileNO(String mobiles){  
		  
		Pattern p = Pattern.compile("^[1][3,4,5,6,7,8,9][0-9]{9}$");
		Matcher m = p.matcher(mobiles);
		return m.matches();    
	}  
	
	//电话号码转换为统一的acm格式
	public static String convertAcmPhone(String countryCode,String areaCode,String phone) {
		String newPhone ="",newCountryCode="",newAreaCode=areaCode;
		//将国家码去掉+号，换成（）
		newCountryCode = countryCode.replace("+", "");
		newCountryCode = "("+newCountryCode+")";
		//首先判断电话是否手机号码
		if(isMobileNO(phone)){
			newPhone = newCountryCode + phone;
		}else{
			//不是手机号码，加区号，如果是国内区号，去掉首位0			
			if(areaCode.startsWith("0")){
				//国内区号
				newAreaCode = newAreaCode.replaceFirst("0", "");
			}
			newPhone = newCountryCode + newAreaCode + phone;
		}
		return newPhone;
	}
}

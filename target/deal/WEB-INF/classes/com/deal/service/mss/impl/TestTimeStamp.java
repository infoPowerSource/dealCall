package com.deal.service.mss.impl;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

import org.springframework.util.StringUtils;

import com.deal.util.DateFormatUtil;
import com.google.common.collect.Maps;

import edu.emory.mathcs.backport.java.util.Arrays;

public class TestTimeStamp {
	
//	public final static Map<String,String> testMap=new ConcurrentHashMap<String,String>();
//	static{
//		testMap.put("0", "0--");
//		testMap.put("1", "1--");
//		testMap.put("2", "2--");
//		testMap.put("3", "3--");
//		testMap.put("4", "4--");
//		testMap.put("5","5--");
//		testMap.put("6","6---");
//		testMap.put("7", "7--");
//		testMap.put("8", "8---");
//	}

	public static void main(String[] args) {
		System.out.println("--------------");
//		Set<String> keySet=testMap.keySet();
//		for(String key:keySet){
//			System.out.println(key);
//			System.out.println(testMap.get(key));
//		}
		String str="10023000";
		String con="+81";
		String newStr = str.replaceFirst("^0*", "");
		System.out.println(con+newStr);
//		long handleTime = System.currentTimeMillis()/1000;
//		System.out.println(handleTime);
//    	System.out.println(DateFormatUtil.getTimeAsDate());
//		Timestamp stamp=new Timestamp(System.currentTimeMillis());
//		System.out.println(stamp);
//		Timestamp stamp1=DateFormatUtil.addMinutesInTimestamp(stamp,20);
//		Timestamp stamp3=DateFormatUtil.today();
//	    System.out.println(stamp3);
//		String countryCode="+86";
//		System.out.println(countryCode.substring(1));
//	    System.out.println(DateFormatUtil.compareMinute(stamp,stamp1));
//		String xdata="2017/06/14   20:  31";
//		String temp=xdata.replace("/","-");
//		temp=temp.replace("  ","");
//		stamp = Timestamp.valueOf(temp+":00");
//		String time="04:30";
//		System.out.println(time.split(":")[0]);
//		int du=Integer.valueOf(time.split(":")[0])*60+Integer.valueOf(time.split(":")[1]);		
//		System.out.println(du);
//		System.out.println(stamp);
//		System.out.println("--------------------");
//		String str="61053873";
//		Integer[] onStateIndex= new Integer[]{103,4,5};
//		System.out.println(Arrays.asList(onStateIndex).contains(4));
//		System.out.println("--------------");
//		System.out.println(Integer.valueOf(str));
//		int redenr=Integer.valueOf(str)%9;
//		System.out.println(redenr);
//		System.out.println(stamp);
//		Calendar cc = Calendar.getInstance();
//		 cc.setTime(stamp);
//		 cc.add(Calendar.MINUTE,150);
//		
//		 System.out.println(stamp.getDate());
//		 System.out.println(cc.getTime());
//		 Timestamp afterStamp=new Timestamp(cc.getTimeInMillis());
//		 System.out.println(afterStamp);
//         System.out.println(stamp.getYear()+1900);
//         System.out.println(stamp.getMonth()+1);
//         System.out.println(stamp.getHours()+":"+stamp.getMinutes());
//         System.out.println(stamp.getMinutes());
//         System.out.println(stamp.getMinutes());
//	String content="span>${supportName}，您好</span><br><br><span>已为您开通主动服务系统帐号:</span><br><span>使用公司内网帐号与密码即可登录</span><br><span>主动服务系统网址：meetme.quanshi.com/active</span><br>";
//	content = StringUtils.replace(content, "${supportName}", "zhengdong");
//	System.out.println(content);
//		Map<String, Integer> configMap = Maps.newHashMap();
//		configMap.put("会议开始，自动外呼专家", 1);
//		configMap.put("有咨询客户入会后，自动外呼专家", 2);
//		configMap.put("所有咨询客户入会后，自动外呼专家", 3);
//		configMap.put("与咨询客户确认后，由客服外呼专家", 4);
//		System.out.println(configMap.get("会议开始，自动外呼专家"));
//		List<String> strList;
//		System.out.println();
		
	}
	
	

}

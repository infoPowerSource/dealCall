package com.deal.util;


import java.io.UnsupportedEncodingException;

import org.apache.log4j.Logger;

import net.sourceforge.pinyin4j.*;

public class PingYin4j {
	final static Logger log = Logger.getLogger(PingYin4j.class);
	
	/**
	 * 判断一个字符是不是中文
	 * @param cn
	 * @return 中文返回true，英文返回false
	 */
	private static boolean getCnAscii(char cn) {       
		try {
			//取得UTF-8编码的字节
			byte[] bytes = (String.valueOf(cn)).getBytes("UTF-8");
			log.debug("bytes.length=" + bytes.length);
			
			//大于4个Byte的是错误
	        if(bytes==null || bytes.length>4 || bytes.length<=0) { 
	            return false;
	        }
	        //UTF-8中，英文字符是1个字节
	        if (bytes.length == 1) {
	        	return false;
	        }else if(bytes.length > 1) { 
	        	//大于1个字节的中文字符
	            return true;
	        }
		} catch (UnsupportedEncodingException e) {
			log.error("Your OS doesn't support UTF-8");
			log.error(e.getMessage(), e);
		}
		
		//出现Exception时默认不是中文
        return false;
    }
	
	/**
	 * 字符串转化为拼音
	 * @param cnStr 中英文字符串
	 * @return 把中英文字符串转化为拼音后的字符串
	 */	
	public static String getFullSpell(String cnStr) {
		//空串直接返回
		if(null == cnStr || "".equals(cnStr.trim())) {
            return cnStr;
        }
        
        //拆分
        char[] chars = cnStr.toCharArray();
        StringBuffer retuBuf = new StringBuffer();
        //循环每一个字符
        for (int i=0;i<chars.length;i++) {
        	//判断是不是中文
        	boolean flag = getCnAscii(chars[i]);
        	//只有中文才能转拼音，英文转会出错
        	if(flag==true){
        		String[] pyt = PinyinHelper.toHanyuPinyinStringArray(chars[i]);
        		//防空的判断，防止中文特殊字符（全角）。如果为空，跳过该字符
        		if(pyt!=null){
        			String py = pyt[0].substring(0,pyt[0].length()-1);
        			retuBuf.append(py);
        		}
        	}
        	else{
        		//英文不转换，直接使用
        		retuBuf.append(chars[i]);
        	}
        }
        
        return retuBuf.toString();
    }
}

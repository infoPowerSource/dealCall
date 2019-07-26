package com.deal.util;

import java.io.UnsupportedEncodingException;  
  
import org.apache.commons.codec.binary.Base64;  
  
/** 
 * 封装Base64的工具类 
 *  
 */  
public class UrlBase64Util {  
    public final static String ENCODING = "UTF-8";  
  
    // 加密  
    public static String encoded(String data) throws UnsupportedEncodingException {  
        byte[] b = Base64.encodeBase64URLSafe(data.getBytes(ENCODING));  
        return new String(b, ENCODING);  
    }  
  
    // 解密  
    public static String decode(String data) throws UnsupportedEncodingException {  
        byte[] b = Base64.decodeBase64(data.getBytes(ENCODING));  
        return new String(b, ENCODING);  
    }  

    public static void main(String[] args) throws UnsupportedEncodingException {  
    	String str = "所以余数任何情况下都只可能是0，1，2这三个数中的一个。如果余数是0的话，就表示原文字节数正好是3的倍数（最理想的情况）。如果是1的话，转成2个Base64编码字符，为了让Base64编码是4的倍数，就要补2个等号；同理，如果是2的话，就要补1个w.mp3";  
        // 加密该字符串  
        String encodedString = encoded(str);  
        System.out.println(encodedString);  
        // 解密该字符串  
        String decodedString = decode(encodedString);  
        System.out.println(decodedString);  
    }  
}  
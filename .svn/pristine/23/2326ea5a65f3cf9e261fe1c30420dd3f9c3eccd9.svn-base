package com.deal.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * MD5加密工具(是基于hash算法实现,不可逆)
 * 
 * @Md5Utils
 */
public class Md5Utils{
	private static Logger logger = LoggerFactory.getLogger(Md5Utils.class);

	/** 16进制的字符数组 */
	private final static String[] hexDigits = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

	/**
	 * @param source
	 *            需要加密的原字符串
	 * @return 32位
	 */
	public static String MD5Encode(String source){
		return MD5Encode(source, "utf-8", false);
	}

	/**
	 * @param source
	 *            需要加密的原字符串
	 * @param encoding
	 *            指定编码类型
	 * @param uppercase
	 *            是否转为大写字符串
	 * @return 32位
	 */
	public static String MD5Encode(String source, String encoding, boolean uppercase){
		String result = null;
		try{
			result = source;
			// 获得MD5摘要对象
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			// 使用指定的字节数组更新摘要信息
			messageDigest.update(result.getBytes(encoding));
			// messageDigest.digest()获得16位长度
			result = byteArrayToHexString(messageDigest.digest());

		}catch (Exception e){
			logger.error("", e);
		}
		return uppercase ? result.toUpperCase() : result;
	}

	/**
	 * 转换字节数组为16进制字符串
	 * 
	 * @param bytes
	 *            字节数组
	 * @return
	 */
	private static String byteArrayToHexString(byte[] bytes){
		StringBuilder stringBuilder = new StringBuilder();
		for(byte tem : bytes){
			stringBuilder.append(byteToHexString(tem));
		}
		return stringBuilder.toString();
	}

	/**
	 * 转换byte到16进制
	 * 
	 * @param b
	 *            要转换的byte
	 * @return 16进制对应的字符
	 */
	private static String byteToHexString(byte b){
		int n = b;
		if(n < 0){
			n = 256 + n;
		}
		int d1 = n / 16;
		int d2 = n % 16;
		return hexDigits[d1] + hexDigits[d2];
	}

	// public static String MD5Encode2(String pwd){
	// try{
	// // 创建加密对象
	// MessageDigest digest = MessageDigest.getInstance("md5");
	// // 调用加密对象的方法，加密的动作已经完成
	// byte[] bs = digest.digest(pwd.getBytes());
	// // 接下来，我们要对加密后的结果，进行优化，按照mysql的优化思路走
	// // mysql的优化思路：
	// // 第一步，将数据全部转换成正数：
	// String hexString = "";
	// for(byte b : bs){
	// // 第一步，将数据全部转换成正数：
	// // 解释：为什么采用b&255
	// /*
	// * b:它本来是一个byte类型的数据(1个字节) 255：是一个int类型的数据(4个字节)
	// * byte类型的数据与int类型的数据进行运算，会自动类型提升为int类型 eg: b: 1001 1100(原始数据)
	// * 运算时： b: 0000 0000 0000 0000 0000 0000 1001 1100 255: 0000
	// * 0000 0000 0000 0000 0000 1111 1111 结果：0000 0000 0000 0000
	// * 0000 0000 1001 1100 此时的temp是一个int类型的整数
	// */
	// int temp = b & 255;
	// // 第二步，将所有的数据转换成16进制的形式
	// // 注意：转换的时候注意if正数>=0&&<16，那么如果使用Integer.toHexString()，可能会造成缺少位数
	// // 因此，需要对temp进行判断
	// if(temp < 16 && temp >= 0){
	// // 手动补上一个“0”
	// hexString = hexString + "0" + Integer.toHexString(temp);
	// }else{
	// hexString = hexString + Integer.toHexString(temp);
	// }
	// }
	// return hexString;
	// }catch (NoSuchAlgorithmException e){
	// logger.error("", e);
	// }
	// return "";
	// }

	public static void main(String[] args) throws Exception, UnsupportedEncodingException{
		System.out.println(MD5Encode("123") + ">" + MD5Encode("123").length());
//		System.out.println(MD5Encode2("123") + ">" + MD5Encode2("123").length());
	}
}

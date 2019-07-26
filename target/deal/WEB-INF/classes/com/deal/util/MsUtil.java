package com.deal.util;

import java.util.Map;
import com.gnet.acmw.client.AcmClient;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONUtils;


/**
 * @brief 工具类，包括：封装JSON、解析JSON等
 * 
 * @author zhipeng.xu
 * 
 * @date 2017.05.16
 * 
 * @version 1.0.0
 * 
 *          Revision History
 ****************************************************************************************/
public class MsUtil {
	
	/**
	 * @brief 将JSON字符串解析成Map<String, String>类型
	 * @param[in] json 需要解析的JSON字符串
	 * @return Map<String, Object> 解析后的Map<String, Object>类型数据
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, Object> jsonSTM(String json) {
		return (Map<String, Object>) JSONObject.fromObject(json);
	}

	/**
	 * @brief 转换JSON字符串成JSONObject
	 * @param[in] json JSON字符串
	 * @return JSONObject
	 */
	public static JSONObject jsonSTO(String json) {
		return JSONObject.fromObject(json);
	}

	/**
	 * @brief 取得JSON字符串中key对应的JSONArray
	 * @param[in] json JSON字符串
	 * @param[in] key JSONArray字符串对应的key
	 * @return key对应的JSONArray
	 */
	public static JSONArray jsonGetArray(String json, String key)
			throws Exception {
		return jsonSTO(json).getJSONArray(key);
	}

	/**
	 * @brief 将Map<String, String>类型数据转换成JSON字符串
	 * @param[in] map 要封装成JSON字符串的Map<String, String>类型数据
	 * @return String 组装后的JSON串
	 */
	public static String jsonMTS(Map<String, Object> map) {
		return JSONObject.fromObject(map).toString();
	}

	/**
	 * @brief 将Object类型数据转换成JSON字符串
	 * @param object
	 *            要封装成JSON的Object类型数据对象
	 * @return String 组装后的JSON串
	 */
	public static String jsonOTS(Object object) {
		// MsJSONWriter writer = new MsJSONWriter(true);
		// if (object instanceof Party) {
		// String[] properties = new String[]{"conf", "conference", "class"};
		// writer.writeLimited(Party.class, object, properties);
		// return writer.getBuf();
		// }
		// else if (object instanceof Conference) {
		// String[] properties = new String[]{"partyList", "class"};
		// writer.writeLimited(Party.class, object, properties);
		// return writer.getBuf();
		// }
		// return writer.write(object);
		if (JSONUtils.isArray(object)) {
			return JSONArray.fromObject(object).toString();
		}
		return JSONObject.fromObject(object).toString();
	}

	/**
	 * @brief 将Object类型数据组装成Map<String, String>类型数据
	 * @details -# 首先调用jsonOTS转换对象成JSON String类型 -# 再调用jsonSTM转成JSON
	 *          String类型为Map<String, String>
	 * 
	 * @param[in] object 要封装成Map<String, String>类型数据的对象
	 * @return Map<String, String> 解析后的JSON数据
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, Object> jsonOTM(Object object) {
		return (Map<String, Object>) JSONObject.fromObject(object);
	}

	/**
	 * @brief 验证billingCode格式
	 * @param[in] billingCode 会议唯一标识
	 * @return true:格式正确;false:格式不正确
	 */
	public static boolean checkBillingCode(String billingCode) {
		if (billingCode == null || billingCode.equals("")) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * @brief 验证队列名称格式
	 * @param[in] queueName 队列名称
	 * @return true:格式正确;false:格式不正确
	 */
	public static boolean checkRoutingKey(String routingKey) {
		if (routingKey == null || routingKey.equals("")) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * @brief 验证Acm返回消息
	 * @param[in] result Acm返回值
	 * @return true:操作成功;false操作失败
	 */
	public static boolean checkAcmResult(int result) {
		// TODO AcmClient define 1
		return result == AcmClient.Return_Success || result == 1;
	}

	/**
	 * @brief 验证PartyId
	 * @param[in] partyId
	 * @return true:partyId格式正确; false:partyId格式不正确
	 */
	public static boolean checkPartyId(String partyId) {
		if (partyId == null || partyId.equals("")) {
			return false;
		} else {
			return true;
		}
	}
}

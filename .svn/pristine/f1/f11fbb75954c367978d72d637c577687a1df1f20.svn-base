package com.deal.util;

import java.io.IOException;

import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author zhipeng.xu
 *
 */
public class JSONUtil
{
	public static <T> Object JSONToObj(String jsonStr,Class<T> obj)
	{
		T t = null;
		try
		{
			ObjectMapper objectMapper = new ObjectMapper();
			t = objectMapper.readValue(jsonStr,obj);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return t;
	}

	public static <T> JSONObject objectToJson(T obj)
			throws JSONException,IOException
	{
		ObjectMapper mapper = new ObjectMapper();
		// Convert object to JSON string
		String jsonStr = "";
		try
		{
			jsonStr = mapper.writeValueAsString(obj);
		}catch(IOException e)
		{
			throw e;
		}
		return new JSONObject(jsonStr);
	}
}

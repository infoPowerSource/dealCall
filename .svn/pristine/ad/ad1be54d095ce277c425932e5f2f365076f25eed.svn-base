package com.deal.service.report.impl;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.deal.entity.report.ConfDetailInput;
import com.deal.entity.report.ConfInput;
import com.deal.service.report.IConfCDRManagerService;
import com.deal.util.Consts;
import com.deal.util.JSONUtil;

@Service
public class ConfCDRManagerServiceImpl implements IConfCDRManagerService
{
	private static Logger logger = LoggerFactory
			.getLogger(ConfCdrServiceImpl.class);
	
	@Value("${confCDR.request.url}")
	private String confCDRRestUrl;

	/**
	 * 从计费接口获取会议数据
	 * 
	 * @param ConfInput
	 * @return JSONObject getConfList
	 * @throws JSONException
	 */
	public JSONObject getConfList(ConfInput confInput)
	{
		if(null == confInput)
		{
			logger.error("getConfList--confInput is null;");
			return null;
		}
		String url = confCDRRestUrl + "/uniinterface/billing/getconferencelist";
		DefaultHttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost(url);
		JSONObject response = null;
		try
		{
			String confInputString = JSONUtil.objectToJson(confInput)
					.toString();
			StringEntity s = new StringEntity(confInputString);
			s.setContentEncoding("UTF-8");
			s.setContentType("application/json");
			post.setEntity(s);
			HttpResponse res = client.execute(post);
			if(res.getStatusLine().getStatusCode() == HttpStatus.SC_OK)
			{
				HttpEntity entity = res.getEntity();
				String result = EntityUtils.toString(entity);
				// response = JSONObject.toJSONObject(result);
				response = new JSONObject(result);
				logger.info("getConfList-计费接口调用成功url=" + url + "参数："
						+ confInputString);
			}
			else
			{
				logger.info("getConfList-计费接口失败url" + url + "，状态为"
						+ res.getStatusLine().getStatusCode());
				logger.info("getConfList-计费接口失败，参数" + confInputString);
			}
		}catch(Exception e)
		{
			logger.error("getConfList-计费接口url=" + url + "失败",e);
		}
		// return response;
		return response;
	}

	/**
	 * 从计费接口获取会议明细数据
	 * 
	 * @param ConfDetailInput
	 * @return JSONObject getConfDetail
	 * @throws JSONException
	 */
	public JSONObject getConfDetail(ConfDetailInput confDetailInput)
	{
		if(null == confDetailInput)
		{
			return null;
		}
		String url = confCDRRestUrl + "/uniinterface/billing/getconfdetail";
		DefaultHttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost(url);
		JSONObject response = null;
		try
		{
			String confDetailInputstString = JSONUtil
					.objectToJson(confDetailInput).toString();
			StringEntity s = new StringEntity(confDetailInputstString);
			s.setContentEncoding("UTF-8");
			s.setContentType("application/json");
			post.setEntity(s);
			HttpResponse res = client.execute(post);
			if(res.getStatusLine().getStatusCode() == HttpStatus.SC_OK)
			{
				HttpEntity entity = res.getEntity();
				String result = EntityUtils.toString(entity);
				response = new JSONObject(result);
				logger.info("getConfDetail-计费接口调用成功url=" + url + "参数："
						+ confDetailInputstString);
			}
			else
			{
				logger.info(
						"getConfDetail-计费接口调用失败,参数：" + confDetailInputstString);
				logger.info("getConfDetail-计费接口调用失败，url:" + url + "状态："
						+ res.getStatusLine().getStatusCode());
			}
		}catch(Exception e)
		{
			logger.error("getConfDetail-计费接口调用失败url:" + url,e);
		}
		return response;
	}
}
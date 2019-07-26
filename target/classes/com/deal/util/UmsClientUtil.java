package com.deal.util;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSONObject;
import com.deal.entity.login.UmsResult;
import com.deal.entity.ums.UmsResponseDTO;
import com.deal.entity.ums.UmsUserDTO;

@Component
public class UmsClientUtil{
	private static Logger logger = LoggerFactory.getLogger(UmsClientUtil.class);
	@Resource(name = "csgRestTemplate")
	private RestTemplate csgRestTemplate;
	@Value("${url.ums}")
	private String urlPrefix;//url.ums=http://192.168.28.173:8081/umsapi/rs

	public JSONObject sendHttp(String url, HttpEntity<String> requestEntity){
		JSONObject result = null;
		try{
			result = csgRestTemplate.postForObject(url, requestEntity, JSONObject.class);
			if(logger.isInfoEnabled()){
				logger.info("urlPrefix:" + url + "," + "sync response:" + result);
			}
			// String username = result.getString("lastName");
			// logger.info("lastName:" + username);
		}catch (Exception e1){
			logger.error("Http req error,", e1);
		}
		return result;
	}

	public JSONObject sendHttpNoEntity(String url){
		JSONObject result = null;
		try{
			result = csgRestTemplate.getForObject(url, JSONObject.class);
			if(logger.isDebugEnabled()){
				logger.debug("sync response:" + result);
			}
			// String username = result.getString("lastName");
			// logger.info("lastName:" + username);
		}catch (Exception e1){
			logger.error("Http req error,", e1);
		}
		return result;
	}

	/* 登录 */
	public UmsResponseDTO checkLoginName(UmsUserDTO umsUserDto){
		// 拼装url地址http://192.168.28.173:8081/umsapi/rs/users/generalAuthentication
		String url = urlPrefix + "/users/generalAuthentication";
		// 设置请求头
		MultiValueMap<String, String> heads = new LinkedMultiValueMap<String, String>();
		heads.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);
		// 将请求信息加到请求体中
		String json = JsonMapper.nonDefaultMapper().toJson(umsUserDto);
		logger.info("call ums interface login json:" + json + ".");
		HttpEntity<String> requestEntity = new HttpEntity<String>(json, heads);
		try{
			ResponseEntity<UmsResponseDTO> responseEntity = csgRestTemplate.exchange(url, HttpMethod.POST, requestEntity, UmsResponseDTO.class);
			// 返回值
			UmsResponseDTO umsResponseDTO = responseEntity.getBody();
			// return umsResponseDTO.getRetCode().intValue();
			return umsResponseDTO;
		}catch (Exception e){
			logger.error("call ums interface:" + (url) + " exception," + e.getMessage());
			return null;
		}
	}

	/* 修改密码 */
	public UmsResult updatePasswordWithOld(String umsid, String oldPass, String newPass){
		UmsResult ur = new UmsResult();
		MultiValueMap<String, String> heads = new LinkedMultiValueMap<String, String>();
		heads.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE);
		String body = "oldPassword=" + oldPass + "&newPassword=" + newPass;
		HttpEntity<String> requestEntity = new HttpEntity<String>(body, heads);
		// 拼装url地址
		// String url = "http://192.168.28.173:8081/umsapi/rs/users/id/" + umsid
		// + "/change_password";
		String url = urlPrefix + "/users/id/" + umsid + "/change_password";
		try{
			ResponseEntity<String> responseEntity = csgRestTemplate.exchange(url, HttpMethod.PUT, requestEntity, String.class);
			// 返回值
			ur.setResponseBody(responseEntity.getBody());
			return ur;
		}catch (Exception e){
			logger.error("call ums interface:" + (url) + " exception," + e.getMessage());
	        HttpClientErrorException e1 = (HttpClientErrorException) e;
	        ur.setResult(false);
	        ur.setErrorInfo(e1.getResponseBodyAsString());
			return ur;
		}
	}

	/* 重置密码 */
	public String updatePasswordNoWithOld(String umsid, String newPass){
		HttpHeaders requestHeaders = new HttpHeaders();
		// requestHeaders.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> requestEntity = new HttpEntity<String>(newPass, requestHeaders);
		// 拼装url地址
		// String url = "http://192.168.28.173:8081/umsapi/rs/users/id/" + umsid
		// + "/password";
		String url = urlPrefix + "/users/id/" + umsid + "/password";
		try{
			ResponseEntity<String> responseEntity = csgRestTemplate.exchange(url, HttpMethod.PUT, requestEntity, String.class);
			// 返回值
			return responseEntity.getBody();
		}catch (Exception e){
			logger.error("call ums interface:" + (url) + " exception," + e.getMessage());
			return "";
		}
	}

	/* 通过手机号查找umsid */
	public Object getUmsIdByTel(String tel){
		HttpHeaders requestHeaders = new HttpHeaders();
		// requestHeaders.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> requestEntity = new HttpEntity<String>("", requestHeaders);
		// 拼装url地址
		// String url =
		// "http://192.168.28.173:8081/umsapi/rs/users/mobile/13252877620";
		String url = urlPrefix + "/users/mobile/" + tel;
		try{
			ResponseEntity<Object> responseEntity = csgRestTemplate.exchange(url, HttpMethod.GET, requestEntity, Object.class);
			// 返回值
			return responseEntity.getBody();
		}catch (Exception e){
			logger.error("call ums interface:" + (url) + " exception," + e.getMessage());
			return "";
		}
	}

	/* 通过手机号查找umsid */
	public Object getUmsIdByMail(String mail){
		HttpHeaders requestHeaders = new HttpHeaders();
		// requestHeaders.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> requestEntity = new HttpEntity<String>("", requestHeaders);
		// 拼装url地址
		// String url =
		// "http://192.168.28.173:8081/umsapi/rs/users/email?email=wu_923_b_08@qs.com";
		String url = urlPrefix + "/users/email?email=" + mail;
		try{
			ResponseEntity<Object> responseEntity = csgRestTemplate.exchange(url, HttpMethod.GET, requestEntity, Object.class);
			// 返回值
			return responseEntity.getBody();
		}catch (Exception e){
			logger.error("call ums interface:" + (url) + " exception," + e.getMessage());
			return "";
		}
	}
	
	/* 通过siteId查找site信息 */
	public Object getSiteById(String siteId){
		HttpHeaders requestHeaders = new HttpHeaders();
		// requestHeaders.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> requestEntity = new HttpEntity<String>("", requestHeaders);
		// 拼装url地址
		// String url =
		// "http://192.168.28.173:8081/umsapi/rs/sites/76681";
		String url = urlPrefix + "/sites/" + siteId;
		try{
			ResponseEntity<Object> responseEntity = csgRestTemplate.exchange(url, HttpMethod.GET, requestEntity, Object.class);
			// 返回值
			return responseEntity.getBody();
		}catch (Exception e){
			logger.error("call ums interface:" + (url) + " exception," + e.getMessage());
			return "";
		}
	}

	/* BOSS同步接口回调方法 */
	public Object sendBossCallback(String umsUrl, String str){
		String url = umsUrl;
		// 设置请求头
		MultiValueMap<String, String> heads = new LinkedMultiValueMap<String, String>();
		heads.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);
		// 将请求信息加到请求体中
		HttpEntity<String> requestEntity = new HttpEntity<String>(str, heads);
		try{
			ResponseEntity<Object> responseEntity = csgRestTemplate.exchange(url, HttpMethod.POST, requestEntity, Object.class);
			// 返回值
			Object umsResponseDTO = responseEntity.getBody();
			// return umsResponseDTO.getRetCode().intValue();
			return umsResponseDTO;
		}catch (Exception e){
			logger.error("call ums interface:" + (url) + " exception," + e.getMessage());
			return null;
		}
	}

	public HttpEntity<String> defaultNoJsonSyncUms(String entity){
		HttpHeaders requestHeaders = new HttpHeaders();
		return new HttpEntity<String>(entity, requestHeaders);
	}

	public HttpEntity<String> defaultJsonSyncUms(String entity){
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setContentType(MediaType.APPLICATION_JSON);
		return new HttpEntity<String>(entity, requestHeaders);
	}

	public void testSyncUms(){
		csgRestTemplate = new RestTemplate();
		urlPrefix = "http://192.168.28.173:8081/umsapi/rs/users/325301";
		JSONObject result = sendHttpNoEntity(urlPrefix);
		System.out.println("userId:    " + result + "," + result.getString("result"));
	}

	public void testSyncUms2(){
		csgRestTemplate = new RestTemplate();
		urlPrefix = "http://newboss2pc3.quanshi.com:8080/activation-engine/responseApi/contract";
//		String k = "{\"result\":1,\"componentName\":\"deal\",\"requestId\":\"868\",\"successed\":[],\"finishedTime\":\"1497000391592\",\"failed\":[],\"contractId\":41189}";
		String k = "{\"result\":\"1\",\"componentName\":\"deal\",\"requestId\":\"868\",\"successed\":[],\"finishedTime\":\"1497000391592\",\"failed\":[],\"contractId\":41189}";
		System.out.println(k);
		HttpEntity<String> requestEntity = defaultJsonSyncUms(k);
		Object result = sendBossCallback(urlPrefix,k);
//		JSONObject result = sendHttp(urlPrefix, requestEntity);
		System.out.println("userId:    " + result + "," + result);
	}

	public static void main(String[] args){
		UmsClientUtil k = new UmsClientUtil();
		// k.testSyncUms();
		 k.testSyncUms2();
//		k.testSyncUms3();
		// RestTemplate restTemplate = new RestTemplate();
		// String k =
		// "{\"input\":{\"billingcode\":\"5575651\",\"yearmonth\":\"201509\",\"confid\":\"5575651-201509-0001\"},\"filter\":[]}";
		//
		// HttpHeaders requestHeaders = new HttpHeaders();
		// requestHeaders.setContentType(MediaType.APPLICATION_JSON);
		// HttpEntity<String> requestEntity = new HttpEntity<String>(k,
		// requestHeaders);
		// JSONObject result = restTemplate.postForObject(
		// "http://192.168.11.196:8090/billingquery/uniinterface/billing/getfeeconfdetail",
		// requestEntity,
		// JSONObject.class);
		// System.out.println("userId: " + result + "," +
		// result.getString("result"));
	}

	private void testSyncUms3(){
		urlPrefix = "http://192.168.28.173:8081/umsapi/rs/users";
		csgRestTemplate = new RestTemplate();
		// String result = getUmsIdByTel("13252877620");
		Object result = getUmsIdByMail("wu_923_b_08@qs.com");
		Object x = ((java.util.LinkedHashMap) result).get("id");
		// x = ((java.util.LinkedHashMap)x).get("user");
		// x = ((java.util.LinkedHashMap)x).get("mobileNumber");
		System.out.println(x);
		// String result = updatePasswordNoWithOld("63675754","33333333");
		// String result =
		// updatePasswordWithOld("63675754","33333333","11111111");
		System.out.println("userId:    " + result);
		// UmsUserDTO u=new UmsUserDTO();
		// u.setUserName("wu_923_b_08@qs.com");
		// u.setPassword("11111111");
		//
		// UmsResponseDTO result = checkLoginName(u);
		// System.out.println(result.getRetCode().intValue());;//+","+result.
		//
		// Object x =
		// ((java.util.LinkedHashMap)result.getRetObj()).get("userInfo");
		// x = ((java.util.LinkedHashMap)x).get("user");
		// x = ((java.util.LinkedHashMap)x).get("mobileNumber");
		// System.out.println(x);
		// "id""displayName""mobileNumber""email""countryCode""registertime"
		// "userstatus""cityCode"
	}
}

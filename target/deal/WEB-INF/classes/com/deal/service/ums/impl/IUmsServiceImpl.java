package com.deal.service.ums.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.deal.entity.ums.UmsResponseDTO;
import com.deal.entity.ums.UmsUserDTO;
import com.deal.service.ums.IUmsService;
import com.deal.util.JsonMapper;

@Service
public class IUmsServiceImpl implements IUmsService
{
	public static final Logger logger = LoggerFactory.getLogger(IUmsServiceImpl.class);
	
	@Autowired
	RestTemplate restTemplate;
	
	@Value("${url.ums}")
	private String umsRestUrl;
	
	@Override
	public Integer checkLoginName(UmsUserDTO umsUserDto){
		// 拼装url地址
		String url = umsRestUrl + "/users/generalAuthentication";
		// 设置请求头
		MultiValueMap<String, String> heads = new LinkedMultiValueMap<String, String>();
		heads.add("Content-Type", MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8");
		// 将请求信息加到请求体中
		String json = JsonMapper.nonDefaultMapper().toJson(umsUserDto);
		logger.info("call ums interface login json:" + json + ".");
		HttpEntity<String> requestEntity = new HttpEntity<String>(json, heads);		
		try {
			ResponseEntity<UmsResponseDTO> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, UmsResponseDTO.class);
			// 返回值
			UmsResponseDTO umsResponseDTO = responseEntity.getBody();
			return umsResponseDTO.getRetCode().intValue();
		} catch (Exception e) {
			logger.error("call ums interface:" + (url) + " exception," + e.getMessage());
			return -1;
		}
	}
}

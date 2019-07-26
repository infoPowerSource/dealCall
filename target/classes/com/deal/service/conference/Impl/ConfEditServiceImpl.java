package com.deal.service.conference.Impl;

import org.codehaus.plexus.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.deal.dao.create.ConferenceDao;
import com.deal.entity.create.ConferenceInfo;
import com.deal.entity.party.CahcheConference;
import com.deal.monitor.cache.CacheConferenceManage;
import com.deal.service.acm.IACMService;
import com.deal.service.cache.ICacheService;
import com.deal.service.conference.IConfEditService;

@Service
public class ConfEditServiceImpl implements IConfEditService{
	public static final Logger logger = LoggerFactory.getLogger(ConfEditServiceImpl.class);
	@Autowired
	private ConferenceDao confDao;
	@Autowired
	private IACMService acmService;
	@Autowired
	private ICacheService cacheService;

	@Override
	public void setRecordStatus(String confId, String recordStatus){
		if(StringUtils.isEmpty(confId)){
			throw new RuntimeException("传入的会议confId为空");
		}
		if(StringUtils.isEmpty(recordStatus)){
			throw new RuntimeException("传入的会议录音设置值recordStatus为空");
		}
		ConferenceInfo confInfo=confDao.getConfInfoById(confId);
		if(cacheService.getCustomerNumber(confInfo.getConfBillingcode())==0){
			throw new RuntimeException("会议中没有咨询客户，不能设置会议录音");
		}
		if(cacheService.getExpertNumber(confInfo.getConfBillingcode())<1){
			throw new RuntimeException("会议中没有顾问，正在播放提示音，不能设置会议录音");
		}
		CahcheConference cacheConf=CacheConferenceManage.getLocalCacheConference(confInfo.getConfBillingcode());
		//1：开始录音 2：暂停录音
		if(!ObjectUtils.isEmpty(cacheConf)){
			if(recordStatus.equals("1")){
				if(acmService.startRecord(confInfo)==0){
					logger.info("开始录音设置ACM成功，会议BC"+confInfo.getConfBillingcode()+"录音设置状态为"+recordStatus);
				}else{
					logger.info("开始录音设置ACM失败，会议BC"+confInfo.getConfBillingcode()+"录音设置状态为"+recordStatus);
					throw new RuntimeException("开始录音设置ACM失败，会议BC"+confInfo.getConfBillingcode()+"录音设置状态为"+recordStatus);
				}
			}else if(recordStatus.equals("2")){
				if(acmService.stopRecord(confInfo)==0){
					logger.info("停止录音设置ACM成功，会议BC"+confInfo.getConfBillingcode()+"录音设置状态为"+recordStatus);
				}else{
					logger.info("停止录音设置ACM失败，会议BC"+confInfo.getConfBillingcode()+"录音设置状态为"+recordStatus);
					throw new RuntimeException("停止录音设置ACM失败，会议BC"+confInfo.getConfBillingcode()+"录音设置状态为"+recordStatus);
				}
			}
			cacheConf.setRecordStatus(recordStatus);
			CacheConferenceManage.updateRecordStatus(confInfo.getConfBillingcode(),cacheConf);
			logger.info("会议录音设置保存缓存成功，会议BC"+confInfo.getConfBillingcode()+"录音设置状态为"+recordStatus);
		}else{
			throw new RuntimeException("没查询到该会议ID的会议信息");
		}
	}

	@Override
	public int getRecordStatus(String confId){
		ConferenceInfo confInfo=confDao.getConfInfoById(confId);
		CahcheConference cacheConf=CacheConferenceManage.getLocalCacheConference(confInfo.getConfBillingcode());
		if(ObjectUtils.isEmpty(cacheConf)){
			return -1;
		}else{
			//logger.info("会议BC:"+confInfo.getConfBillingcode()+"录音状态为"+cacheConf.getRecordStatus());
			return Integer.valueOf(cacheConf.getRecordStatus());
		}
	}

}

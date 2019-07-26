package com.deal.service.cache.Impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.deal.entity.party.CacheParty;
import com.deal.entity.party.CahcheConference;
import com.deal.monitor.cache.CacheConferenceManage;
import com.deal.service.cache.ICacheService;
import com.deal.util.Consts;

@Service
public class CacheServiceImpl implements ICacheService{
	public static final Logger logger = LoggerFactory.getLogger(CacheServiceImpl.class);

	@Override
	public int getCustomerNumber(String billingCode){
		CahcheConference localConference = CacheConferenceManage.getLocalCacheConference(billingCode);
		if(ObjectUtils.isEmpty(localConference)){
			logger.error("缓存中查询会议信息为空，BC:" + billingCode);
			return -1;
		}
		int guestNum = 0;
		List<CacheParty> partyList = localConference.getPartyList();
		for(CacheParty cacheParty : partyList){
			if(!ObjectUtils.isEmpty(cacheParty)){
				if(cacheParty.getPartyRole() == Consts.IS_HOST && cacheParty.getConnectState() > 2){
					++guestNum;
				}
			}
		}
		logger.info("会议BC:"+billingCode+"中咨询客户的数量为:"+guestNum);
		return guestNum;
	}

	@Override
	public int getExpertNumber(String confBillingcode) {
		CahcheConference localConference = CacheConferenceManage.getLocalCacheConference(confBillingcode);
		if(ObjectUtils.isEmpty(localConference)){
			logger.error("缓存中查询会议信息为空，BC:" + confBillingcode);
			return -1;
		}
		int expertNum = 0;
		List<CacheParty> partyList = localConference.getPartyList();
		for(CacheParty cacheParty : partyList){
			if(!ObjectUtils.isEmpty(cacheParty)){
				if(cacheParty.getPartyRole() == Consts.IS_GUEST && cacheParty.getConnectState() > 2){
					++expertNum;
				}
			}
		}
		logger.info("会议BC:"+confBillingcode+"中专家的数量为:"+expertNum);
		return expertNum;
	}
}

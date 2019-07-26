package com.deal.service.create.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.deal.dao.create.ConferenceDao;
import com.deal.entity.create.ConferenceInfo;
import com.deal.util.Consts;

@Service
public class ConferenceHandleServiceImpl implements IConferenceHandleService{
	private static Logger logger = LoggerFactory.getLogger(ConferenceHandleServiceImpl.class);
	
	@Autowired
	private ConferenceDao confDao;

	@Override
	public boolean updateConfInfo(ConferenceInfo confInfo){
		try{
			confInfo.setConfStatus(Consts.CONFERENCE_STATUS_BEGINING);
			confDao.update(confInfo);
		}catch(Exception e){
			logger.error("送客服返回已经结束的会议时,更新会议信息时出错，confId="+confInfo.getConfId()+"错误信息"+e.getMessage());
			return false;
		}
		return true;
	}

	@Override
	public boolean updateConfStatusbyBc(String billingCode){
		try{
			confDao.updateConfStatusByBc(billingCode);
			logger.info("更新拉入走廊的会议为正在召开，bc="+billingCode);
		}catch(Exception e){
			logger.error("更新拉入走廊的会议为正在召开出错，bc="+billingCode+"错误信息"+e.getMessage());
			return false;
		}
		return true;
	}

	@Override
	public ConferenceInfo getConfInfoByBc(String billingCode){
		ConferenceInfo confInfo =new ConferenceInfo();
		try{
			confInfo=confDao.getConfInfoByBc(billingCode);
			logger.info("根据bc获取confInfo的信息，bc="+billingCode);
			if(ObjectUtils.isEmpty(confInfo)){
				return null;
			}
		}catch(Exception e){
			logger.error("根据bc获取confInfo的信息出错，bc="+billingCode+"错误信息"+e.getMessage());
		}
		
		return confInfo;
	}
}

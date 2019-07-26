package com.deal.service.support.impl;

import java.sql.Timestamp;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deal.dao.support.GalleryConfDao;
import com.deal.entity.create.ConferenceInfo;
import com.deal.entity.create.CustomerInfo;
import com.deal.entity.support.GalleryConfInfo;
import com.deal.service.conference.IConfInfoDetail;
import com.deal.service.support.ISupportHandleService;
import com.google.common.collect.Lists;

@Service
public class SupportHandleServiceImpl implements ISupportHandleService{

	public static final Logger logger = LoggerFactory.getLogger(SupportHandleServiceImpl.class);
	@Autowired
	private GalleryConfDao galDao;
	@Autowired
	private IConfInfoDetail confInfoDetailServie;

	@Override
	public void saveGalleryConfInf(ConferenceInfo conference, CustomerInfo customer){
		GalleryConfInfo galInfo = new GalleryConfInfo();
		galInfo.setConfId(conference.getConfId());
		galInfo.setConfBc(conference.getConfBillingcode());
		galInfo.setCreateTime(new Timestamp(System.currentTimeMillis()));
		galInfo.setConfEndTime(null);
		String phone = confInfoDetailServie.getAcmPhone(customer);

		galInfo.setCustPhone(phone);
		galInfo.setCustName(customer.getCustName());
		try{
			galDao.save(galInfo);
			logger.info("存储走廊会议信息成功confid="+conference.getConfId());
		}catch (Exception e){
			logger.error("存储走廊会议信息出错会议id="+conference.getConfId()+"错误信息e="+e.getMessage());
		}
	}
	

	@Override
	public void updateGalleryConfEndTime(ConferenceInfo conference, CustomerInfo customer){

		String phone = confInfoDetailServie.getAcmPhone(customer);
		Timestamp endTime=new Timestamp(System.currentTimeMillis());
		try{
			galDao.updateGalleryEndTime(conference.getConfBillingcode(),phone,endTime);
			logger.info("存储送回走廊时间成功confid="+conference.getConfId());
		}catch (Exception e){
			logger.error("存储送回走廊时间出错会议id="+conference.getConfId()+"错误信息e="+e.getMessage());
		}
	}

	@Override
	public boolean getDataBybcAndPhone(String bc, String phone){
		List<Object> gallerConfList=Lists.newArrayList();
		gallerConfList=galDao.getDataByBcAndPhone(bc,phone);
		if(gallerConfList.size()>0){
			return true;
		}
		return false;
	}
}

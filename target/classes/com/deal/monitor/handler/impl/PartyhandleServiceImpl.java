package com.deal.monitor.handler.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deal.dao.create.PartyDao;
import com.deal.entity.party.PartyInfo;
import com.deal.monitor.handler.IPartyHandleService;
import com.deal.util.DateFormatUtil;

@Service
public class PartyhandleServiceImpl implements IPartyHandleService{
	public static final Logger logger = LoggerFactory.getLogger(PartyhandleServiceImpl.class);
	@Autowired
	private PartyDao partyDao;
	
	@Override
	public boolean savePartyStatus(PartyInfo partyInfo){
		logger.info("专家状态为 ：" + partyInfo.getPartyStatus()+"partyId:"+partyInfo.getCreateTime());
		// 判断 如果专家状态为 0或1 则为离线状态，其余都是 在线状态
		if(partyInfo.getPartyStatus() == 0 || partyInfo.getPartyStatus() == 1) {
			partyInfo.setPartyStatus(0);
		}else{
			partyInfo.setPartyStatus(1);
		}
		// 根据partyId 查询数据库中 是否存在该专家
		try{
			PartyInfo party = partyDao.getPartyById(partyInfo);
			if(party != null) {
				//如果当前party的状态和数据库中的状态相同，则不更新
				if(party.getPartyStatus() ==partyInfo.getPartyStatus()){
					return false;
				}
				// 数据库中存在该专家
				// 更新专家信息 更新专家信息时，ismake要更新成未生成任务 因为有可能一些专家 已经生成任务后，再次入会，又掉线的情况
				// 1、如果掉线任务已经生成，isMake变1，但是此时 专家 又掉线，需要重新记录秒数
				// 2、专家掉线不到20s后 重新入会，则秒数 也需要重新记录
				party.setPartyStatus(partyInfo.getPartyStatus());
				party.setPartyName(partyInfo.getPartyName());
				party.setPartyPhone(partyInfo.getPartyPhone());
				party.setIsMake(0);
				party.setPartyTime(0);
				party.setUpdateTime(DateFormatUtil.getTimeAsDate());
				partyDao.updateParty(party);
			}else{
				// 执行插入
				partyInfo.setCreateTime(DateFormatUtil.getTimeAsDate());
				partyInfo.setUpdateTime(DateFormatUtil.getTimeAsDate());
				partyDao.save(partyInfo);
			}
		}catch (Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}

}

package com.deal.monitor.handler.impl;

import java.sql.Timestamp;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deal.entity.create.ConferenceInfo;
import com.deal.entity.create.ConferenceSupportTaskInfo;
import com.deal.entity.party.CacheParty;
import com.deal.entity.party.CahcheConference;
import com.deal.entity.party.PartyInfo;
import com.deal.monitor.cache.CacheConferenceManage;
import com.deal.monitor.cache.RedisService;
import com.deal.monitor.handler.IPartyThreadService;
import com.deal.monitor.notify.PartyThreadHandler;
import com.deal.util.Consts;
import com.deal.util.DateFormatUtil;
import com.deal.util.SpringBeanUtil;
import com.gnet.acmw.client.IParty;
import com.google.common.collect.Lists;

/**
 * 专家掉线处理任务
 * 
 * @author zhipeng.xu
 *
 */
@Service("partyThreadImpl")
public class PartyThreadImpl implements IPartyThreadService{
	public static final Logger logger = LoggerFactory.getLogger(PartyThreadImpl.class);

	@Autowired
	private PartyThreadHandler partyThreadHandler;

	@Override
	public synchronized void executePartyStatus(){
		try{
			partyThreadHandler = (PartyThreadHandler) SpringBeanUtil.getBeanByName("partyThreadHandler");
			List<PartyInfo> mapParty = partyThreadHandler.getPartyInfo();
			List<PartyInfo> partyList = Lists.newArrayList();
			if(null == mapParty || mapParty.size() == 0){
				return;
			}
			for(PartyInfo partyInfo : mapParty){
				// 判断会议是否已经结束或者会中是否有人存在
				ConferenceInfo conferenceInfo = partyThreadHandler.getConfInfo(partyInfo.getConfId());
				String billingCode = conferenceInfo.getConfBillingcode();
//				 if(partyThreadHandler.execute(conferenceInfo.getConfBillingcode()))
//				 {
					// 判断专家信息表中是否有离线20s
					if(partyInfo.getPartyTime() == 20){
						// 存入任务池
						ConferenceSupportTaskInfo conferenceTaskInfo = new ConferenceSupportTaskInfo();
						CahcheConference localConference = CacheConferenceManage.getLocalCacheConference(conferenceInfo.getConfBillingcode());
						// 如果缓存不为空，并且会中有人，才会生成掉线任务
						if(localConference != null){
							// logger.info("缓存中会议信息：BC=" + localConference.getBillingcode());
							// 如果会中无人，也不会生成掉线任务
							// if(havePartyInMeeting(localConference.getCacheConference()))
							// {
							if(havePartyInMeeting(localConference)){
								// 系统到达预定时间，主动外呼专家，专家未接通，后来咨询客户入会，要将专家生成掉线型任务
								// 生成掉线型任务
								Timestamp now = new Timestamp(System.currentTimeMillis());
								conferenceTaskInfo.setConfID(partyInfo.getConfId());
								conferenceTaskInfo.setConfType(Consts.CONF_TYPE_OUT);
								conferenceTaskInfo.setCreateTime(now);
								conferenceTaskInfo.setStatus(Consts.SUPPORT_TASK_NEW);
								// 先判断任务池中 是否存在此掉线任务
									int num = partyThreadHandler.getSupportOut(partyInfo.getConfId(), Consts.CONF_TYPE_OUT).size();
									logger.info("掉线任务数量：" + num);
									if(num == 0){
										// 集群随机休眠
										avoidClusterError();
										partyThreadHandler.addTask(conferenceTaskInfo);
										partyInfo.setIsMake(1);
										logger.info("生成专家掉线任务成功");
									}
							}else{
								// 会议中无人
								// 会议结束，将isMake置为2
								if(partyInfo.getIsMake() == 2){
									continue;
								}else{
									partyInfo.setIsMake(2);
								}
							}
						}else{
							// 会议结束，将isMake置为2
							if(partyInfo.getIsMake() == 2){
								continue;
							}else{
								partyInfo.setIsMake(2);
							}
						}
					}else{
						// 掉线秒数加1
						partyInfo.setPartyTime(partyInfo.getPartyTime() + 1);
					}
					// 更新时间
					partyInfo.setUpdateTime(DateFormatUtil.getTimeDate());
					// logger.info("参会人信息:"+partyInfo.getPartyId()+"会议信息"+partyInfo.getIsMake());
					partyList.add(partyInfo);
				}
				partyThreadHandler.updateAll(partyList);
//			}
		}catch (Exception e){
			logger.error("专家掉线处理任务失败 " + e.toString());
			e.printStackTrace();
		}

	}

	/**
	 * @brief 判断会中是否有人,且必须是参与人
	 * @param cacheConference
	 * @return true：会中有人；false：会中无人
	 */
	private boolean havePartyInMeeting(CahcheConference cacheConference){
		boolean havePartyInMeeting = false;
		List<CacheParty> partyList = cacheConference.getPartyList();
		if(partyList != null){
			for(CacheParty party : partyList){
				if(party.getConnectState() != IParty.ConnectState.DISCONNECTED && party.getPartyRole() == 1){
					havePartyInMeeting = true;
				}
			}
		}
		return havePartyInMeeting;
	}

	/**
	 * 随机休眠，避免集群中的服务器同时进行Action操作
	 */
	protected void avoidClusterError(){
		double random = Math.random();
		try{
			long time = (long) (random * 2000);
			logger.info("sleep " + time + " milliseconds");
			Thread.sleep(time);
		}catch (InterruptedException e){
			e.printStackTrace();
		}
	}

}

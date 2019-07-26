package com.deal.service.syncboss;

import com.deal.entity.create.ConferenceInfo;
import com.deal.entity.syncboss.SyncBossConfInfoParams;
import com.deal.entity.syncboss.SyncBossConfInfoResult;

public interface IBossInformService{

	/**
	 * 调用boss接口同步预约会议
	 * @param resvConfParams 调用参数
	 * @return 
	 */
	public SyncBossConfInfoResult syncResvConfInBoss(ConferenceInfo confInfo);
	
	public void syncAccessFromBoss(String bridgeName);
}

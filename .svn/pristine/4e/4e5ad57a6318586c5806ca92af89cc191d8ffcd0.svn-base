package com.deal.service.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.deal.dao.create.ConferenceDao;
import com.deal.service.syncboss.IBossInformService;

@Component("accessNumberSyncFromBossJob")
public class AccessNumberSyncFromBossJob{
	private static Logger logger = LoggerFactory.getLogger(AccessNumberSyncFromBossJob.class);
	@Autowired
	private ConferenceDao confDao;
	@Autowired
	private IBossInformService bossService;

	public synchronized void execute(){
		try{
			logger.info("从Boss同步平台接入号，开始------");
			bossService.syncAccessFromBoss("summit2");

			bossService.syncAccessFromBoss("summit7");

			logger.info("从Boss同步平台接入号，结束------");
		}catch(Exception e){
			e.printStackTrace();
			logger.error("从Boss同步平台接入号,出错",e.getMessage());
		}
	}
}

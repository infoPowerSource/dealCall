package com.deal.controller.syncboss;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.deal.entity.syncboss.RequestDTO;
import com.deal.service.syncboss.ISyncBossService;

@Component
@Scope("prototype")
public class SyncBossRunnable implements Runnable{
	private static Logger logger = LoggerFactory.getLogger(SyncBossRunnable.class);
	@Autowired
	public ISyncBossService iSyncBossService;
	private RequestDTO input;

	public void setRequestDTO(RequestDTO input){
		this.input = input;
	}

	@Override
	public void run(){
		try{
			iSyncBossService.saveSyncDataResult(input);
		}catch (Throwable e){
			logger.error("同步BOSS账号信息失败，", e);
		}
	}

}

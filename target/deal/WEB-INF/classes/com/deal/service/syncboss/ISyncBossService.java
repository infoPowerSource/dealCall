package com.deal.service.syncboss;

import java.text.ParseException;

import com.deal.entity.syncboss.RequestDTO;

public interface ISyncBossService{

	public void saveSyncDataResult(RequestDTO input) throws ParseException;

}

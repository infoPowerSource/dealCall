package com.deal.monitor.notify;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deal.dao.create.ConferenceDao;
import com.deal.dao.create.PartyDao;
import com.deal.dao.support.SupportConfInfoDao;
import com.deal.dao.support.SupportDao;
import com.deal.entity.create.ConferenceInfo;
import com.deal.entity.create.ConferenceSupportTaskInfo;
import com.deal.entity.party.PartyInfo;

@Service("partyThreadHandler")
public class PartyThreadHandler{
	@Autowired
	private PartyDao partyDao;
	@Autowired
	private SupportDao supportDao;
	@Autowired
	private ConferenceDao confDao;
	@Autowired
	private SupportConfInfoDao supportConfDao;
	
	public List<PartyInfo> getPartyInfo(){
		return partyDao.getPartyList();
	}

	public void addTask(ConferenceSupportTaskInfo supportInfo){
		supportDao.save(supportInfo);
	}

	public List<ConferenceSupportTaskInfo> getSupportOut(Long confId, Integer type){
		return supportConfDao.getSupportOut(confId, type);
	}

	public void updateAll(List<PartyInfo> mapParty){
		partyDao.updateAll(mapParty);
	}

	public ConferenceInfo getConfInfo(Long confId){
		return confDao.getConfInfoForMonitById(String.valueOf(confId));
	}
	
}

package com.deal.monitor.cache;

import com.gnet.acmw.client.IConference;
import com.gnet.acmw.client.IParty;
import java.util.*;
/**
 * 本地会议缓存
 * @author zhipeng.xu
 *
 */
public class LocalCacheConference {
	private IConference cacheConference;
	private Map<String,IParty> parties = new HashMap<String, IParty>();
	
	public IConference getCacheConference() {
		return cacheConference;
	}
	public void setCacheConference(IConference cacheConference) {
		this.cacheConference = cacheConference;
	}
	
	public void addPartyById(String partyId,IParty party){
		parties.put(partyId, party);
	}
	
	public IParty getPartyById(String partyId){
		return parties.get(partyId);
	}
	
	public void clearPartyById(String partyId){
		parties.remove(partyId);
	}
	
	public Map<String, IParty> getParties() {
		return parties;
	}
	public void setParties(Map<String, IParty> parties) {
		this.parties = parties;
	}
}

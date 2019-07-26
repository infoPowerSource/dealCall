package com.deal.entity.party;

import java.io.Serializable;
import java.util.List;

public class CahcheConference implements Serializable{

	private static final long serialVersionUID = 1L;

	private String billingcode;
	
	// 0：未开启录音 1：正在录音 2：暂停录音
	private String recordStatus;

	private List<CacheParty> partyList;

	public String getBillingcode(){
		return billingcode;
	}

	public void setBillingcode(String billingcode){
		this.billingcode = billingcode;
	}

	public List<CacheParty> getPartyList(){
		return partyList;
	}

	public void setPartyList(List<CacheParty> partyList){
		this.partyList = partyList;
	}
	
	public String getRecordStatus(){
		return recordStatus;
	}

	public void setRecordStatus(String recordStatus){
		this.recordStatus = recordStatus;
	}

}

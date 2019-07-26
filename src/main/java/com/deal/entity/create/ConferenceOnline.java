package com.deal.entity.create;

import java.io.Serializable;
import java.util.List;

import com.deal.entity.party.PartyInfo;

public class ConferenceOnline implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// 咨询客户在线
	private List<PartyInfo> hostonline;
	// 专家在线
	private List<PartyInfo> guestonline;
	// 咨询客户离线
	private List<PartyInfo> hostoffline;
	// 专家离线
	private List<PartyInfo> guestoffline;
	// 预约时间
	private String orderTime;
	
	private String confTime;

	private ConferenceInfoForm conferenceInfo;
	
	public String getConfTime(){
		return confTime;
	}

	public void setConfTime(String confTime){
		this.confTime = confTime;
	}

	public String getOrderTime(){
		return orderTime;
	}

	public void setOrderTime(String orderTime){
		this.orderTime = orderTime;
	}

	public ConferenceInfoForm getConferenceInfo(){
		return conferenceInfo;
	}

	public void setConferenceInfo(ConferenceInfoForm conferenceInfo){
		this.conferenceInfo = conferenceInfo;
	}

	public List<PartyInfo> getHostonline(){
		return hostonline;
	}

	public void setHostonline(List<PartyInfo> hostonline){
		this.hostonline = hostonline;
	}

	public List<PartyInfo> getGuestonline(){
		return guestonline;
	}

	public void setGuestonline(List<PartyInfo> guestonline){
		this.guestonline = guestonline;
	}

	public List<PartyInfo> getHostoffline(){
		return hostoffline;
	}

	public void setHostoffline(List<PartyInfo> hostoffline){
		this.hostoffline = hostoffline;
	}

	public List<PartyInfo> getGuestoffline(){
		return guestoffline;
	}

	public void setGuestoffline(List<PartyInfo> guestoffline){
		this.guestoffline = guestoffline;
	}
}

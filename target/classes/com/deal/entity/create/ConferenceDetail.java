package com.deal.entity.create;

import java.io.Serializable;
import java.util.List;

public class ConferenceDetail implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer online;
	private Integer offline;
	private List<ConferenceEndInfo> confOver;
	private List<ConferenceOnline> confLive;

	public Integer getOnline(){
		return online;
	}

	public void setOnline(Integer online){
		this.online = online;
	}

	public Integer getOffline(){
		return offline;
	}

	public void setOffline(Integer offline){
		this.offline = offline;
	}

	public List<ConferenceOnline> getConfLive(){
		return confLive;
	}

	public void setConfLive(List<ConferenceOnline> confLive){
		this.confLive = confLive;
	}

	public List<ConferenceEndInfo> getConfOver(){
		return confOver;
	}

	public void setConfOver(List<ConferenceEndInfo> confOver){
		this.confOver = confOver;
	}
}

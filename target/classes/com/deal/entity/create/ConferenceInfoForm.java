package com.deal.entity.create;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

public class ConferenceInfoForm implements Serializable{

	private static final long serialVersionUID = 1L;
	private String confId;
	private String confName;
	private String beginTime;
	private String confDuration;
	private String confConfig;
	private String tapedMark;
	private String config;
	private String duration;
	private int confStatus;
	private String confBillingcode;
	private String chairmanPassword;
	private String partyPassword;
	private List<CustomerInfo> custInfo;
	private String notifyemail;
	private String notifysms;
	private String join;
	
	public String getNotifyemail(){
		return notifyemail;
	}

	public void setNotifyemail(String notifyemail){
		this.notifyemail = notifyemail;
	}

	public String getNotifysms(){
		return notifysms;
	}

	public void setNotifysms(String notifysms){
		this.notifysms = notifysms;
	}

	public String getJoin(){
		return join;
	}

	public void setJoin(String join){
		this.join = join;
	}

	public String getConfBillingcode(){
		return confBillingcode;
	}

	public void setConfBillingcode(String confBillingcode){
		this.confBillingcode = confBillingcode;
	}

	public String getChairmanPassword(){
		return chairmanPassword;
	}

	public void setChairmanPassword(String chairmanPassword){
		this.chairmanPassword = chairmanPassword;
	}

	public String getPartyPassword(){
		return partyPassword;
	}

	public void setPartyPassword(String partyPassword){
		this.partyPassword = partyPassword;
	}
	
	public int getConfStatus(){
		return confStatus;
	}

	public void setConfStatus(int confStatus){
		this.confStatus = confStatus;
	}

	public String getConfId(){
		return confId;
	}

	public void setConfId(String confId){
		this.confId = confId;
	}

	public String getConfig(){
		return config;
	}

	public void setConfig(String config){
		this.config = config;
	}

	public String getDuration(){
		return duration;
	}

	public void setDuration(String duration){
		this.duration = duration;
	}

	public List<CustomerInfo> getCustInfo(){
		return custInfo;
	}

	public void setCustInfo(List<CustomerInfo> custInfo){
		this.custInfo = custInfo;
	}

	public String getConfName(){
		return confName;
	}

	public void setConfName(String confName){
		this.confName = confName;
	}

	public String getBeginTime(){
		return beginTime;
	}

	public void setBeginTime(String beginTime){
		this.beginTime = beginTime;
	}

	public String getConfDuration(){
		return confDuration;
	}

	public void setConfDuration(String confDuration){
		this.confDuration = confDuration;
	}

	public String getConfConfig(){
		return confConfig;
	}

	public void setConfConfig(String confConfig){
		this.confConfig = confConfig;
	}

	public String getTapedMark(){
		return tapedMark;
	}

	public void setTapedMark(String tapedMark){
		this.tapedMark = tapedMark;
	}

}

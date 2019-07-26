package com.deal.entity.create;

import java.sql.Timestamp;
import java.util.List;

public class ConferenceSupportForm{

	private Long confID;
	private String confName;
	private int confType;
	private Timestamp beginTime;
	private int confDuration;
	private Timestamp endTime;
	private int confConfig;
	private int HandleStatus;
	private int ConfStatus;
	private int OnlineMan;
	private int OfflineMan;
	private Long supportID;
	private String confBillingcode;
	private String bridgeName;
	
	public String getConfName() {
		return confName;
	}
	public void setConfName(String confName) {
		this.confName = confName;
	}
	public Timestamp getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(Timestamp beginTime) {
		this.beginTime = beginTime;
	}
	public int getConfDuration() {
		return confDuration;
	}
	public void setConfDuration(int confDuration) {
		this.confDuration = confDuration;
	}
	public Long getConfID()
	{
		return confID;
	}
	public void setConfID(Long confID)
	{
		this.confID = confID;
	}
	public Timestamp getEndTime()
	{
		return endTime;
	}
	public void setEndTime(Timestamp endTime)
	{
		this.endTime = endTime;
	}
	public int getConfConfig()
	{
		return confConfig;
	}
	public void setConfConfig(int confConfig)
	{
		this.confConfig = confConfig;
	}
	public int getHandleStatus()
	{
		return HandleStatus;
	}
	public void setHandleStatus(int handleStatus)
	{
		HandleStatus = handleStatus;
	}
	public int getConfStatus()
	{
		return ConfStatus;
	}
	public void setConfStatus(int confStatus)
	{
		ConfStatus = confStatus;
	}
	public int getOnlineMan()
	{
		return OnlineMan;
	}
	public void setOnlineMan(int onlineMan)
	{
		OnlineMan = onlineMan;
	}
	public int getOfflineMan(){
		return OfflineMan;
	}
	public void setOfflineMan(int offlineMan){
		OfflineMan = offlineMan;
	}
	public int getConfType()
	{
		return confType;
	}
	public void setConfType(int confType)
	{
		this.confType = confType;
	}
	public String getConfBillingcode(){
		return confBillingcode;
	}
	public void setConfBillingcode(String confBillingcode){
		this.confBillingcode = confBillingcode;
	}
	public Long getSupportID(){
		return supportID;
	}
	public void setSupportID(Long supportID){
		this.supportID = supportID;
	}
	public String getBridgeName(){
		return bridgeName;
	}
	public void setBridgeName(String bridgeName){
		this.bridgeName = bridgeName;
	}
}

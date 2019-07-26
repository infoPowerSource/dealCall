package com.deal.entity.create;

import java.io.Serializable;
import java.util.List;

public class AppConferenceDetail implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Long confId;
	private String confName;
	private String confStatus;
	private String confDate;
	private String beginTime;
	private String endTime;
	private String orderTime;
	private String hostPassCode;
	private String guestPassCode;
	private String radioUrl;
	private String radioName;
	private String reportUrl;
	private String reportName;
	private List<CustomerSupportForm> customerList;
	public Long getConfId(){
		return confId;
	}
	public String getOrderTime(){
		return orderTime;
	}
	public void setOrderTime(String orderTime){
		this.orderTime = orderTime;
	}
	public void setConfId(Long confId){
		this.confId = confId;
	}
	public String getConfName(){
		return confName;
	}
	public void setConfName(String confName){
		this.confName = confName;
	}
	public String getConfStatus(){
		return confStatus;
	}
	public void setConfStatus(String confStatus){
		this.confStatus = confStatus;
	}
	public String getConfDate(){
		return confDate;
	}
	public void setConfDate(String confDate){
		this.confDate = confDate;
	}
	public String getBeginTime(){
		return beginTime;
	}
	public void setBeginTime(String beginTime){
		this.beginTime = beginTime;
	}
	public String getEndTime(){
		return endTime;
	}
	public void setEndTime(String endTime){
		this.endTime = endTime;
	}
	public String getHostPassCode(){
		return hostPassCode;
	}
	public void setHostPassCode(String hostPassCode){
		this.hostPassCode = hostPassCode;
	}
	public String getGuestPassCode(){
		return guestPassCode;
	}
	public void setGuestPassCode(String guestPassCode){
		this.guestPassCode = guestPassCode;
	}
	public List<CustomerSupportForm> getCustomerList(){
		return customerList;
	}
	public void setCustomerList(List<CustomerSupportForm> customerList){
		this.customerList = customerList;
	}
	public String getRadioUrl(){
		return radioUrl;
	}
	public void setRadioUrl(String radioUrl){
		this.radioUrl = radioUrl;
	}
	public String getRadioName(){
		return radioName;
	}
	public void setRadioName(String radioName){
		this.radioName = radioName;
	}
	public String getReportUrl(){
		return reportUrl;
	}
	public void setReportUrl(String reportUrl){
		this.reportUrl = reportUrl;
	}
	public String getReportName(){
		return reportName;
	}
	public void setReportName(String reportName){
		this.reportName = reportName;
	}

}

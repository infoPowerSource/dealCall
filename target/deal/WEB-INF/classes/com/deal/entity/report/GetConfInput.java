package com.deal.entity.report;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * CDR会议接口参数 对象
 * @author zhipeng.xu
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetConfInput {
	private String billingcode;
	private String startTime; //开始时间参数
	private String endTime;   //结束时间参数
 
	public GetConfInput(){
		
	}
	public  GetConfInput(String billingcode,String startTime,String endTime) {
		this.billingcode=billingcode;
		this.startTime=startTime;
		this.endTime=endTime;
	}
		
	
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getBillingcode() {
		return billingcode;
	}
	public void setBillingcode(String billingcode) {
		this.billingcode = billingcode;
	}

}

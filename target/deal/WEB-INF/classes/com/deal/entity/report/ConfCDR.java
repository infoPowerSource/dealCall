package com.deal.entity.report;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ConfCDR {
	private String recordId; 
	private String platFormId;
	private String billingCode;
	private String msgStartTime;
	private String msgEndtime;
	private String status;  
    private String confid;
    private String info;
	
	public String getPlatFormId() {
		return platFormId;
	}
	public void setPlatFormId(String platFormId) {
		this.platFormId = platFormId;
	}
	public String getBillingCode() {
		return billingCode;
	}
	public void setBillingCode(String billingCode) {
		this.billingCode = billingCode;
	}
	public String getMsgStartTime() {
		return msgStartTime;
	}
	public void setMsgStartTime(String msgStartTime) {
		this.msgStartTime = msgStartTime;
	}
	public String getMsgEndtime() {
		return msgEndtime;
	}
	public void setMsgEndtime(String msgEndtime) {
		this.msgEndtime = msgEndtime;
	}
	public String getRecordId() {
		return recordId;
	}
	public void setRecordId(String recordId) {
		this.recordId = recordId;
	}
	
	
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public String getConfid() {
		return confid;
	}
	public void setConfid(String confid) {
		this.confid = confid;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}

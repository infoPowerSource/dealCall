package com.deal.entity.report;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
/**
 * 计费CDR明细接口参数 对象
 * @author zhipeng.xu
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetConfDetailInput {
	private String platFormId; //CDR平台ID
	private String msgRecordId;//CDR会议ID
	private String startTime;//CDR中会议开始时间
	private String endTime;//CDR会议结束时间
	public String getPlatFormId() {
		return platFormId;
	}
	public void setPlatFormId(String platFormId) {
		this.platFormId = platFormId;
	}
	public String getMsgRecordId() {
		return msgRecordId;
	}
	public void setMsgRecordId(String msgRecordId) {
		this.msgRecordId = msgRecordId;
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
}

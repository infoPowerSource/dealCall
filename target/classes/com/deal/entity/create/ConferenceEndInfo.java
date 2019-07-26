package com.deal.entity.create;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.*;
import javax.persistence.Table;

public class ConferenceEndInfo implements Serializable{
	private static final long serialVersionUID = 1L;
	private ConferenceInfoForm confInfo;
	private String radioUrl;
	private String radioName;
	private String reportUrl;
	private String reportName;
	/**
	 * 预约时间
	 */
	private String orderTime;
	private String confTime;
	
	public String getConfTime() {
		return confTime;
	}
	public void setConfTime(String confTime) {
		this.confTime = confTime;
	}
	public ConferenceInfoForm getConfInfo()
	{
		return confInfo;
	}
	public void setConfInfo(ConferenceInfoForm confInfo)
	{
		this.confInfo = confInfo;
	}
	public String getRadioUrl()
	{
		return radioUrl;
	}
	public void setRadioUrl(String radioUrl)
	{
		this.radioUrl = radioUrl;
	}
	public String getRadioName()
	{
		return radioName;
	}
	public void setRadioName(String radioName)
	{
		this.radioName = radioName;
	}
	public String getReportUrl()
	{
		return reportUrl;
	}
	public void setReportUrl(String reportUrl)
	{
		this.reportUrl = reportUrl;
	}
	public String getReportName()
	{
		return reportName;
	}
	public void setReportName(String reportName)
	{
		this.reportName = reportName;
	}
	
	public String getOrderTime()
	{
		return orderTime;
	}
	public void setOrderTime(String orderTime)
	{
		this.orderTime = orderTime;
	}

	
}

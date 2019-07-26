package com.deal.entity.sms;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name="tb_sms_send_task")
public class SMSSendTask implements Serializable
{
	private static final long serialVersionUID = 1L;
	private long smsId;
	private int smsType;
	private String receive;
	private String smsContent;
	private Timestamp sendTime;
	private int isHandle;
	private Timestamp finishTime;
	
	@Id @GeneratedValue
	@Column(name="sms_id",unique = true,nullable = false,length = 20)
	public long getSmsId()
	{
		return smsId;
	}
	public void setSmsId(long smsId)
	{
		this.smsId = smsId;
	}
	
	@Column(name="sms_type",nullable=false)
	public int getSmsType()
	{
		return smsType;
	}
	public void setSmsType(int smsType)
	{
		this.smsType = smsType;
	}
	
	@Column(name="receive",length=20,nullable=false)
	public String getReceive()
	{
		return receive;
	}
	public void setReceive(String receive)
	{
		this.receive = receive;
	}
	
	@Column(name="sms_content",length=2500,nullable=false)
	@Type(type="text")
	public String getSmsContent()
	{
		return smsContent;
	}
	public void setSmsContent(String smsContent)
	{
		this.smsContent = smsContent;
	}
	
	@Column(name="send_time")
	public Timestamp getSendTime()
	{
		return sendTime;
	}
	public void setSendTime(Timestamp sendTime)
	{
		this.sendTime = sendTime;
	}
	
	@Column(name="is_handle",nullable=false)
	public int getIsHandle()
	{
		return isHandle;
	}
	public void setIsHandle(int isHandle)
	{
		this.isHandle = isHandle;
	}
	
	@Column(name="finish_time")
	public Timestamp getFinishTime() {
		return finishTime;
	}
	public void setFinishTime(Timestamp finishTime) {
		this.finishTime = finishTime;
	}
}

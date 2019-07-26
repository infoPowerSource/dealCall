package com.deal.entity.mss;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name="tb_email_send_task_history")
public class MSSSendTaskHistory implements Serializable
{
	private static final long serialVersionUID = 1L;
	private long recordId;
	private String emailTitle;
	private int emailType;
	private String emailSender;
	private String emilReceiver;
	private String emailContent;
	private String emailContentExt;
	private Timestamp sendTime;
	private Timestamp finishTime;
	private long emailId;
	
	@Id @GeneratedValue
	@Column(name="record_id",unique=true,nullable=false,length = 20)
	public long getRecordId()
	{
		return recordId;
	}
	public void setRecordId(long recordId)
	{
		this.recordId = recordId;
	}
	
	@Column(name="email_title",length=400,nullable=false)
	public String getEmailTitle()
	{
		return emailTitle;
	}
	public void setEmailTitle(String emailTitle)
	{
		this.emailTitle = emailTitle;
	}
	
	@Column(name="email_type",nullable=false)
	public int getEmailType()
	{
		return emailType;
	}
	public void setEmailType(int emailType)
	{
		this.emailType = emailType;
	}
	
	@Column(name="email_sender",length=100,nullable=false)
	public String getEmailSender()
	{
		return emailSender;
	}
	public void setEmailSender(String emailSender)
	{
		this.emailSender = emailSender;
	}
	@Column(name="email_receiver",length=100,nullable=false)
	public String getEmilReceiver()
	{
		return emilReceiver;
	}
	public void setEmilReceiver(String emilReceiver)
	{
		this.emilReceiver = emilReceiver;
	}
	
	@Column(name="email_content_ext",length=2500)
	@Type(type="text")
	public String getEmailContentExt(){
		return emailContentExt;
	}
	public void setEmailContentExt(String emailContentExt){
		this.emailContentExt = emailContentExt;
	}
	
	@Column(name="email_content",length=2500,nullable=false)
	@Type(type="text")
	public String getEmailContent()
	{
		return emailContent;
	}
	public void setEmailContent(String emailContent)
	{
		this.emailContent = emailContent;
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
	
	@Column(name="finish_time",nullable=false)
	public Timestamp getFinishTime()
	{
		return finishTime;
	}
	public void setFinishTime(Timestamp finishTime)
	{
		this.finishTime = finishTime;
	}
	
	@Column(name="email_id",length = 20)
	public long getEmailId()
	{
		return emailId;
	}
	public void setEmailId(long emailId)
	{
		this.emailId = emailId;
	}
}

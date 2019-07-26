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
@Table(name="tb_email_send_history_record")
public class MSSSendHistoryRecord implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long id;
	private long emailId;
	private int	emailType;
	private String	emailProject;
	private String	emailSender;
	private String	emailReceiver;
	private String	emailContent;
	private Timestamp sendTime;
	private Short	ifSuccess;
	private Timestamp	sentTime;
	private long confId;
	
	@Id @GeneratedValue
	@Column(name="id",unique = true,nullable = false,length = 10)
	public long getId(){
		return id;
	}
	public void setId(long id){
		this.id = id;
	}
	@Column(name="email_id",unique = true,nullable = false,length = 20)
	public Long getEmailId(){
		return emailId;
	}
	public void setEmailId(Long emailId){
		this.emailId = emailId;
	}
	@Column(name="email_type",nullable = false)
	public int getEmailType(){
		return emailType;
	}
	public void setEmailType(int emailType){
		this.emailType = emailType;
	}
	@Column(name="email_project",nullable = false,length = 300)
	public String getEmailProject(){
		return emailProject;
	}
	public void setEmailProject(String emailProject){
		this.emailProject = emailProject;
	}
	@Column(name="email_sender",nullable=false,length=30)
	public String getEmailSender(){
		return emailSender;
	}
	public void setEmailSender(String emailSender){
		this.emailSender = emailSender;
	}
	@Column(name="email_receiver",nullable = false,length=30)
	public String getEmailReceiver(){
		return emailReceiver;
	}
	public void setEmailReceiver(String emailReceiver){
		this.emailReceiver = emailReceiver;
	}
	@Column(name="email_content",length = 2500)
	@Type(type="text")
	public String getEmailContent(){
		return emailContent;
	}
	public void setEmailContent(String emailContent){
		this.emailContent = emailContent;
	}
	@Column(name="send_time")
	public Timestamp getSendTime(){
		return sendTime;
	}
	public void setSendTime(Timestamp sendTime){
		this.sendTime = sendTime;
	}
	@Column(name="if_success",nullable = false)
	public Short getIfSuccess(){
		return ifSuccess;
	}
	public void setIfSuccess(Short ifSuccess){
		this.ifSuccess = ifSuccess;
	}
	@Column(name="sent_time",nullable = false, length = 0)
	public Timestamp getSentTime(){
		return sentTime;
	}
	public void setSentTime(Timestamp sentTime){
		this.sentTime = sentTime;
	}
	@Column(name = "conf_id", nullable = false, length = 20)
	public long getConfId(){
		return confId;
	}
	public void setConfId(long confId){
		this.confId = confId;
	}
}

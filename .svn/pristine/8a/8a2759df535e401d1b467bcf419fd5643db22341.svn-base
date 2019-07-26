package com.deal.entity.mss;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.deal.entity.create.ConferenceInfo;

@Entity
@Table(name = "tb_email_send_task")
public class MSSSendTask implements Serializable{
	private static final long serialVersionUID = 1L;
	private long emailId;
	private int emailType;
	private String emailTitle;
	private String emailSender;
	private String emailReceiver;
	private String ReceiveName;
	private String emailContent;
	private String emailContentExt;
	private Timestamp sendTime;
	private int isHandle;
	private ConferenceInfo confInfo;

	@Id
	@GeneratedValue
	@Column(name = "email_id", unique = true, nullable = false, length = 20)
	public long getEmailId(){
		return emailId;
	}

	public void setEmailId(long emailId){
		this.emailId = emailId;
	}

	@Column(name = "email_type", nullable = false)
	public int getEmailType(){
		return emailType;
	}

	public void setEmailType(int emailType){
		this.emailType = emailType;
	}

	@Column(name = "email_title", length = 400, nullable = false)
	public String getEmailTitle(){
		return emailTitle;
	}

	public void setEmailTitle(String emailTitle){
		this.emailTitle = emailTitle;
	}

	@Column(name = "receive_name", length = 20, nullable = false)
	public String getReceiveName(){
		return ReceiveName;
	}

	public void setReceiveName(String receiveName){
		ReceiveName = receiveName;
	}

	@Column(name = "email_sender", length = 100, nullable = false)
	public String getEmailSender(){
		return emailSender;
	}

	public void setEmailSender(String emailSender){
		this.emailSender = emailSender;
	}

	@Column(name = "email_receiver", length = 100, nullable = false)
	public String getEmailReceiver(){
		return emailReceiver;
	}

	public void setEmailReceiver(String emailReceiver){
		this.emailReceiver = emailReceiver;
	}

	@Column(name = "email_content_ext", length = 2500)
	@Type(type = "text")
	public String getEmailContentExt(){
		return emailContentExt;
	}

	public void setEmailContentExt(String emailContentExt){
		this.emailContentExt = emailContentExt;
	}

	@Column(name = "email_content", length = 2500, nullable = false)
	@Type(type = "text")
	public String getEmailContent(){
		return emailContent;
	}

	public void setEmailContent(String emailContent){
		this.emailContent = emailContent;
	}

	@Column(name = "send_time")
	public Timestamp getSendTime(){
		return sendTime;
	}

	public void setSendTime(Timestamp sendTime){
		this.sendTime = sendTime;
	}

	@Column(name = "is_handle", nullable = false)
	public int getIsHandle(){
		return isHandle;
	}

	public void setIsHandle(int isHandle){
		this.isHandle = isHandle;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "conf_id")
	public ConferenceInfo getConfInfo(){
		return confInfo;
	}

	public void setConfInfo(ConferenceInfo confInfo){
		this.confInfo = confInfo;
	}

}

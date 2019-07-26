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
@Table(name="tb_sms_send_history_record")
public class SMSSendHistoryRecord implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long id;
	private Long smsId;
	private int	smsType;
	private String	receiver;
	private String	content;
	private Timestamp	sentTime;
	private int		ifSuccess;
	private long confId;
	
	@Id @GeneratedValue
	@Column(name="id",unique = true,nullable = false,length = 10)
	public long getId(){
		return id;
	}
	public void setId(long id){
		this.id = id;
	}
	@Column(name="sms_id",unique = true,nullable = false,length = 20)
	public Long getSmsId(){
		return smsId;
	}
	public void setSmsId(Long smsId){
		this.smsId = smsId;
	}
	@Column(name="sms_type", nullable = false)
	public int getSmsType(){
		return smsType;
	}
	public void setSmsType(int smsType){
		this.smsType = smsType;
	}
	@Column(name="receiver", nullable = false, length = 30)
	public String getReceiver(){
		return receiver;
	}
	public void setReceiver(String receiver){
		this.receiver = receiver;
	}
	@Column(name="content", nullable = false, length = 2500)
	@Type(type="text")
	public String getContent(){
		return content;
	}
	public void setContent(String content){
		this.content = content;
	}
	@Column(name="sent_time", nullable = false)
	public Timestamp getSentTime(){
		return sentTime;
	}
	public void setSentTime(Timestamp sentTime){
		this.sentTime = sentTime;
	}
	@Column(name="if_success", nullable = false)
	public int getIfSuccess(){
		return ifSuccess;
	}
	public void setIfSuccess(int ifSuccess){
		this.ifSuccess = ifSuccess;
	}
	@Column(name = "conf_id", nullable = false, length = 20)
	public long getConfId(){
		return confId;
	}
	public void setConfId(long confId){
		this.confId = confId;
	}

}

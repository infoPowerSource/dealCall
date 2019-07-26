package com.deal.entity.support;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.deal.entity.create.ConferenceInfo;

@Entity
@Table(name="tb_support_handle_history_record")
public class SuppportHandleHistoryRecord implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long id;
	private long recordId;
	private int confType;
	private int handlerType;
	private String outboundConfig;
	private Timestamp createTime;
	private long supportId;
	private long confInfo;
	
	@Id @GeneratedValue
	@Column(name="id",unique = true,nullable = false,length = 10)
	public long getId(){
		return id;
	}
	public void setId(long id){
		this.id = id;
	}
	@Column(name="record_id",unique = true,nullable = false,length = 10)
	public long getRecordId(){
		return recordId;
	}
	public void setRecordId(long recordId){
		this.recordId = recordId;
	}
	
	@Column(name="conf_type",nullable=false)
	public int getConfType(){
		return confType;
	}
	public void setConfType(int confType){
		this.confType = confType;
	}
	
	@Column(name="handler_type",nullable=false)
	public int getHandlerType(){
		return handlerType;
	}
	public void setHandlerType(int handlerType){
		this.handlerType = handlerType;
	}
	
	@Column(name="outbound_config",length=20)
	public String getOutboundConfig(){
		return outboundConfig;
	}
	public void setOutboundConfig(String outboundConfig){
		this.outboundConfig = outboundConfig;
	}
	
	@Column(name="create_time",nullable=false)
	public Timestamp getCreateTime(){
		return createTime;
	}
	public void setCreateTime(Timestamp createTime){
		this.createTime = createTime;
	}
	@Column(name = "support_id", nullable = false, length = 20)
	public long getSupportId(){
		return supportId;
	}
	public void setSupportId(long supportId){
		this.supportId = supportId;
	}
	@Column(name = "conf_id", nullable = false, length = 20)
	public long getConfInfo(){
		return confInfo;
	}
	public void setConfInfo(long confInfo){
		this.confInfo = confInfo;
	}
}

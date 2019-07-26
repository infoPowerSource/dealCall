package com.deal.entity.create;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tb_conference_radio_history_info")
public class ConferenceRadioHistoryInfo implements Serializable{

	private static final long serialVersionUID = 1L;
	private long recordId;
	private long radioId;
	private String fileName;
	private String fileUrl;
	private Timestamp createTime;
	private int isValid;
	private long confInfo;
	
	@Id @GeneratedValue
	@Column(name="record_id",unique=true,nullable=false,length=20)
	public long getRecordId(){
		return recordId;
	}
	public void setRecordId(long recordId){
		this.recordId = recordId;
	}
	
	@Column(name="radio_id",nullable=false,length=20)
	public long getRadioId(){
		return radioId;
	}
	public void setRadioId(long radioId){
		this.radioId = radioId;
	}
	@Column(name="file_name",unique = true,length=50,nullable=false)
	public String getFileName(){
		return fileName;
	}
	public void setFileName(String fileName){
		this.fileName = fileName;
	}
	
	@Column(name="file_url",length=100,nullable=false)
	public String getFileUrl(){
		return fileUrl;
	}
	public void setFileUrl(String fileUrl){
		this.fileUrl = fileUrl;
	}
	
	@Column(name="create_time",nullable=false)
	public Timestamp getCreateTime(){
		return createTime;
	}
	public void setCreateTime(Timestamp createTime){
		this.createTime = createTime;
	}
	
	@Column(name="is_valid",nullable=false)
	public int getIsValid(){
		return isValid;
	}
	public void setIsValid(int isValid){
		this.isValid = isValid;
	}
	@Column(name = "conf_id", nullable = false, length = 20)
	public long getConfInfo(){
		return confInfo;
	}
	public void setConfInfo(long confInfo){
		this.confInfo = confInfo;
	}
}

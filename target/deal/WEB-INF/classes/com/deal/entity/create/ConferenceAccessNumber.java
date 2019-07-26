package com.deal.entity.create;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tb_access_number")
public class ConferenceAccessNumber implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long accessId;
	private String bridgeName;
	private String language;
	private String telNum;
	//1：有效 。0：无效
	private int isvalid;
	
	@Id @GeneratedValue
	@Column(name="access_id",unique=true,nullable=false,length=20)
	public long getAccessId(){
		return accessId;
	}
	public void setAccessId(long accessId){
		this.accessId = accessId;
	}
	@Column(name="bridge_name",nullable=false,length=10)
	public String getBridgeName(){
		return bridgeName;
	}
	public void setBridgeName(String bridgeName){
		this.bridgeName = bridgeName;
	}
	@Column(name="language",nullable=false,length=10)
	public String getLanguage(){
		return language;
	}
	public void setLanguage(String language){
		this.language = language;
	}
	@Column(name="tel_num",nullable=false,length=20)
	public String getTelNum(){
		return telNum;
	}
	public void setTelNum(String telNum){
		this.telNum = telNum;
	}
	@Column(name="is_valid",nullable=false,length=10)
	public int getIsvalid(){
		return isvalid;
	}
	public void setIsvalid(int isvalid){
		this.isvalid = isvalid;
	}
}

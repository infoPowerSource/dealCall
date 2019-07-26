package com.deal.entity.support;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tb_support_info")
public class SupportInfo implements Serializable
{
	private static final long serialVersionUID = 1L;
	private long supportId;
	private String supportName;
	private String supportEmail;
	private String supportTel;
	private String meetmeBillingcode;
	private String chairmanPasscode;
	private String partyPassword;
	private Timestamp createTime;
	private int isValid;
	private String bridgeName;
	
	@Id @GeneratedValue
	@Column(name="support_id",unique = true,nullable = false,length = 10)
	public long getSupportId()
	{
		return supportId;
	}
	public void setSupportId(long supportId)
	{
		this.supportId = supportId;
	}
	
	@Column(name="support_name",length=10,nullable=false)
	public String getSupportName()
	{
		return supportName;
	}
	public void setSupportName(String supportName)
	{
		this.supportName = supportName;
	}
	
	@Column(name="support_email",length=50,nullable=false)
	public String getSupportEmail()
	{
		return supportEmail;
	}
	public void setSupportEmail(String supportEmail)
	{
		this.supportEmail = supportEmail;
	}
	
	@Column(name="support_tel",length=20,nullable=false)
	public String getSupportTel()
	{
		return supportTel;
	}
	public void setSupportTel(String supportTel)
	{
		this.supportTel = supportTel;
	}
	@Column(name="meetme_billingcode",length=30,nullable=false)
	public String getMeetmeBillingcode()
	{
		return meetmeBillingcode;
	}
	public void setMeetmeBillingcode(String meetmeBillingcode)
	{
		this.meetmeBillingcode = meetmeBillingcode;
	}
	
	@Column(name="chairman_password",length=20,nullable=false)
	public String getChairmanPasscode()
	{
		return chairmanPasscode;
	}
	public void setChairmanPasscode(String chairmanPasscode)
	{
		this.chairmanPasscode = chairmanPasscode;
	}
	
	@Column(name="party_password",length=20,nullable=false)
	public String getPartyPassword()
	{
		return partyPassword;
	}
	public void setPartyPassword(String partyPassword)
	{
		this.partyPassword = partyPassword;
	}
	
	@Column(name="create_time",nullable=false)
	public Timestamp getCreateTime()
	{
		return createTime;
	}
	public void setCreateTime(Timestamp createTime)
	{
		this.createTime = createTime;
	}
	
	@Column(name="is_valid",nullable=false)
	public int getIsValid()
	{
		return isValid;
	}
	public void setIsValid(int isValid)
	{
		this.isValid = isValid;
	}
	
	@Column(name="bridge_name",length=20,nullable=false)
	public String getBridgeName(){
		return bridgeName;
	}
	public void setBridgeName(String bridgeName){
		this.bridgeName = bridgeName;
	}
}

package com.deal.entity.create;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tb_conference_billingcode_info")
public class ConferenceBillingcodeInfo implements Serializable
{
	private static final long serialVersionUID = 1L;
	private long billingcodeId;
	private String billingcode;
	private int isUsed;
	private Timestamp useTime;

	@Id @GeneratedValue
	@Column(name="billingcode_id",unique = true,nullable = false,length = 20)
	public long getBillingcodeId()
	{
		return billingcodeId;
	}
	public void setBillingcodeId(long billingcodeId)
	{
		this.billingcodeId = billingcodeId;
	}
	
	@Column(name="biilingcode",length=20,nullable=false)
	public String getBillingcode()
	{
		return billingcode;
	}
	public void setBillingcode(String billingcode)
	{
		this.billingcode = billingcode;
	}
	@Column(name="is_used",nullable=false)
	public int getIsUsed()
	{
		return isUsed;
	}
	public void setIsUsed(int isUsed)
	{
		this.isUsed = isUsed;
	}
	
	@Column(name="use_time")
	public Timestamp getUseTime()
	{
		return useTime;
	}
	public void setUseTime(Timestamp useTime)
	{
		this.useTime = useTime;
	}
	
}

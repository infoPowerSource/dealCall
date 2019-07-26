package com.deal.entity.create;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tb_conference_password_info")
public class ConferencePasswordInfo implements Serializable
{
	private static final long serialVersionUID = 1L;
	private long passwordId;
	private String password;
	private int passwordLength;
	private Timestamp useTime;
	private int isUsed;
	
	@Id @GeneratedValue
	@Column(name="password_id",unique = true,nullable = false,length = 20)
	public long getPasswordId()
	{
		return passwordId;
	}
	public void setPasswordId(long passwordId)
	{
		this.passwordId = passwordId;
	}
	
	@Column(name="password",nullable=false)
	public String getPassword()
	{
		return password;
	}
	public void setPassword(String password)
	{
		this.password = password;
	}
	
	@Column(name="password_length",nullable=false)
	public int getPasswordLength()
	{
		return passwordLength;
	}
	public void setPasswordLength(int passwordLength)
	{
		this.passwordLength = passwordLength;
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
	
	@Column(name="is_use",nullable=false)
	public int getIsUsed()
	{
		return isUsed;
	}
	public void setIsUsed(int isUsed)
	{
		this.isUsed = isUsed;
	}
}

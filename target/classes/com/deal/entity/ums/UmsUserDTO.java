package com.deal.entity.ums;

public class UmsUserDTO
{
	private String userName;	
	private String password;
	private String siteID;	
	private String productID;
	public String getUserName()
	{
		return userName;
	}
	public void setUserName(String userName)
	{
		this.userName = userName;
	}
	public String getPassword()
	{
		return password;
	}
	public void setPassword(String password)
	{
		this.password = password;
	}
	public String getSiteID()
	{
		return siteID;
	}
	public void setSiteID(String siteID)
	{
		this.siteID = siteID;
	}
	public String getProductID()
	{
		return productID;
	}
	public void setProductID(String productID)
	{
		this.productID = productID;
	}
	
}

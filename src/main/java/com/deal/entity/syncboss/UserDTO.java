package com.deal.entity.syncboss;

import java.util.Date;
import java.util.List;

public class UserDTO {
	private Long id;
	private String billingCode;
	private ResourceDTO resource;
	private Integer accountId;
	private Integer siteId;
	private List<ComponentDTO> components;
	private Date expiryDate;
	private Byte gender;
	private String vip;
	private String conferenceName;
	private String userid;
	private Byte billLanguage;
	
	public UserDTO(){}

	/**
	 * @param id
	 * @param billingCode
	 * @param resource
	 * @param accountId
	 * @param siteId
	 * @param components
	 * @param expiryDate
	 * @param gender
	 * @param vip
	 */
	public UserDTO(Long id, String billingCode, ResourceDTO resource,
			Integer accountId, Integer siteId, List<ComponentDTO> components,
			Date expiryDate, Byte gender, String vip, String conferenceName, String userid, Byte billLanguage) {
		this.id = id;
		this.billingCode = billingCode;
		this.resource = resource;
		this.accountId = accountId;
		this.siteId = siteId;
		this.components = components;
		this.expiryDate = expiryDate;
		this.gender = gender;
		this.vip = vip;
		this.conferenceName = conferenceName;
		this.userid = userid;
		this.setBillLanguage(billLanguage);
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * @return the billingCode
	 */
	public String getBillingCode() {
		return billingCode;
	}
	
	/**
	 * @param billingCode the billingCode to set
	 */
	public void setBillingCode(String billingCode) {
		this.billingCode = billingCode;
	}
	
	/**
	 * @return the resource
	 */
	public ResourceDTO getResource() {
		return resource;
	}
	
	/**
	 * @param resource the resource to set
	 */
	public void setResource(ResourceDTO resource) {
		this.resource = resource;
	}
	
	/**
	 * @return the accountId
	 */
	public Integer getAccountId() {
		return accountId;
	}
	
	/**
	 * @param accountId the accountId to set
	 */
	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}
	
	/**
	 * @return the siteId
	 */
	public Integer getSiteId() {
		return siteId;
	}
	
	/**
	 * @param siteId the siteId to set
	 */
	public void setSiteId(Integer siteId) {
		this.siteId = siteId;
	}
	
	/**
	 * @return the components
	 */
	public List<ComponentDTO> getComponents() {
		return components;
	}
	
	/**
	 * @param components the components to set
	 */
	public void setComponents(List<ComponentDTO> components) {
		this.components = components;
	}

	/**
	 * @return the expiryDate
	 */
	public Date getExpiryDate() {
		return expiryDate;
	}

	/**
	 * @param expiryDate the expiryDate to set
	 */
	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public Byte getGender() {
		return gender;
	}

	public void setGender(Byte gender) {
		this.gender = gender;
	}

	public String getVip() {
		return vip;
	}

	public void setVip(String vip) {
		this.vip = vip;
	}

	public String getConferenceName() {
		return conferenceName;
	}

	public void setConferenceName(String conferenceName) {
		this.conferenceName = conferenceName;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public Byte getBillLanguage() {
		return billLanguage;
	}

	public void setBillLanguage(Byte billLanguage) {
		this.billLanguage = billLanguage;
	}
	
}


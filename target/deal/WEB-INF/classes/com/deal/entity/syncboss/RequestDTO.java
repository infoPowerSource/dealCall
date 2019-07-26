package com.deal.entity.syncboss;

import java.util.Date;


public class RequestDTO {
	private String requestId;
	private String callback;
	private Date createdTime;
	private CustomerDTO customer;
	private String creator;
	private String type;
	
	public RequestDTO(){}
	
	
	/**
	 * @param requestId
	 * @param callback
	 * @param createdTime
	 * @param customer
	 * @param creator
	 * @param type
	 */
	public RequestDTO(String requestId, String callback, Date createdTime,
			CustomerDTO customer, String creator, String type) {
		this.requestId = requestId;
		this.callback = callback;
		this.createdTime = createdTime;
		this.customer = customer;
		this.creator = creator;
		this.type = type;
	}


	/**
	 * @return the requestId
	 */
	public String getRequestId() {
		return requestId;
	}

	/**
	 * @param requestId the requestId to set
	 */
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	/**
	 * @return the callback
	 */
	public String getCallback() {
		return callback;
	}
	
	/**
	 * @param callback the callback to set
	 */
	public void setCallback(String callback) {
		this.callback = callback;
	}
	
	/**
	 * @return the createdTime
	 */
	public Date getCreatedTime() {
		return createdTime;
	}
	
	/**
	 * @param createdTime the createdTime to set
	 */
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	
	/**
	 * @return the customer
	 */
	public CustomerDTO getCustomer() {
		return customer;
	}
	
	/**
	 * @param customer the customer to set
	 */
	public void setCustomer(CustomerDTO customer) {
		this.customer = customer;
	}
	
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}


	/**
	 * @return the creator
	 */
	public String getCreator() {
		return creator;
	}

	/**
	 * @param creator the creator to set
	 */
	public void setCreator(String creator) {
		this.creator = creator;
	}
	
	
}


package com.deal.entity.syncboss;


import java.util.Map;


public class ComponentDTO {
	private Long id;
	private String name;
	private String activationUrl;
	private Integer order;
	private Map<String, String> property;
	
	// 鏍囩ず姝ゆ璇锋眰鏃剁粍浠剁殑鐘舵��
	private Integer status;
	
	public ComponentDTO(){}
	
	/**
	 * @param id
	 * @param name
	 * @param activationUrl
	 * @param order
	 * @param property
	 * @param status
	 */
	public ComponentDTO(Long id, String name, String activationUrl,
			Integer order, Map<String, String> property, Integer status) {
		this.id = id;
		this.name = name;
		this.activationUrl = activationUrl;
		this.order = order;
		this.property = property;
		this.status = status;
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @return the activationUrl
	 */
	public String getActivationUrl() {
		return activationUrl;
	}
	
	/**
	 * @param activationUrl the activationUrl to set
	 */
	public void setActivationUrl(String activationUrl) {
		this.activationUrl = activationUrl;
	}
	
	/**
	 * @return the property
	 */
	public Map<String, String> getProperty() {
		return property;
	}
	
	/**
	 * @param property the property to set
	 */
	public void setProperty(Map<String, String> property) {
		this.property = property;
	}

	/**
	 * @return the order
	 */
	public Integer getOrder() {
		return order;
	}

	/**
	 * @param order the order to set
	 */
	public void setOrder(Integer order) {
		this.order = order;
	}

	/**
	 * @return the status
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
}

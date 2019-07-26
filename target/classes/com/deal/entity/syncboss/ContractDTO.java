package com.deal.entity.syncboss;

import java.util.List;


public class ContractDTO {
	
	// 合同id
	private Integer id;
	
	// 产品id
	private Integer productId;
	
	private ResourceDTO resource;
	
	private List<ComponentDTO> components;
	
	public ContractDTO(){}
	
	public ContractDTO(Integer id, Integer productId, ResourceDTO resource, List<ComponentDTO> components) {
		super();
		this.id = id;
		this.productId = productId;
		this.resource = resource;
		this.components = components;
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
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

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	
	
}


package com.deal.entity.syncboss;

import java.util.List;

public class CustomerDTO {
    private Long id;
	private String customerCode;
	private String customerName;
	private ContractDTO contract;
	private List<UserDTO> users;
	// UMS中校验失败的账号列表
	private List<UserActiveResult> errorUsers;
	
	public CustomerDTO(){}
	
	/**
	 * @param id
	 * @param customerCode
	 * @param contract
	 * @param users
	 */
	public CustomerDTO(Long id, String customerCode, ContractDTO contract, List<UserDTO> users, List<UserActiveResult> errorUsers) {
		this.id = id;
		this.customerCode = customerCode;
		this.contract = contract;
		this.users = users;
		this.errorUsers = errorUsers;
		
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
	 * @return the customerCode
	 */
	public String getCustomerCode() {
		return customerCode;
	}
	
	/**
	 * @param customerCode the customerCode to set
	 */
	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}
	
	public String getCustomerName(){
		return customerName;
	}

	public void setCustomerName(String customerName){
		this.customerName = customerName;
	}

	/**
	 * @return the contract
	 */
	public ContractDTO getContract() {
		return contract;
	}
	
	/**
	 * @param contract the contract to set
	 */
	public void setContract(ContractDTO contract) {
		this.contract = contract;
	}
	
	/**
	 * @return the users
	 */
	public List<UserDTO> getUsers() {
		return users;
	}
	
	/**
	 * @param users the users to set
	 */
	public void setUsers(List<UserDTO> users) {
		this.users = users;
	}

	/**
	 * @return the errorUsers
	 */
	public List<UserActiveResult> getErrorUsers() {
		return errorUsers;
	}

	/**
	 * @param errorUsers the errorUsers to set
	 */
	public void setErrorUsers(List<UserActiveResult> errorUsers) {
		this.errorUsers = errorUsers;
	}

	
}


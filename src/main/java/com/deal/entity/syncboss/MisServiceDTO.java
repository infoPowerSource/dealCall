package com.deal.entity.syncboss;

public class MisServiceDTO {
	private String createOper;
	private String customerCode;
	private String billingCode;
	private String accountId;
	private String contractId;
	private String type;
	
	
	public MisServiceDTO() {
		super();
	}

	/**
	 * @param createOper
	 * @param customerCode
	 * @param billingCode
	 * @param accountId
	 * @param contractId
	 * @param type
	 */
	public MisServiceDTO(String createOper, String customerCode,
			String billingCode, String accountId, String contractId, String type) {
		this.createOper = createOper;
		this.customerCode = customerCode;
		this.billingCode = billingCode;
		this.accountId = accountId;
		this.contractId = contractId;
		this.type = type;
	}

	/**
	 * @return the createOper
	 */
	public String getCreateOper() {
		return createOper;
	}

	/**
	 * @param createOper the createOper to set
	 */
	public void setCreateOper(String createOper) {
		this.createOper = createOper;
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
	 * @return the accountId
	 */
	public String getAccountId() {
		return accountId;
	}

	/**
	 * @param accountId the accountId to set
	 */
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	/**
	 * @return the contractId
	 */
	public String getContractId() {
		return contractId;
	}

	/**
	 * @param contractId the contractId to set
	 */
	public void setContractId(String contractId) {
		this.contractId = contractId;
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

	
}

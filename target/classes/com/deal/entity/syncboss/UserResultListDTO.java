package com.deal.entity.syncboss;

public class UserResultListDTO {
	private Long id;

	private String billingCode;

	private Integer accountId;

	private String errorCode;

	private String error;

	public UserResultListDTO() {
	}

	public UserResultListDTO(Long id, String billingCode, Integer accountId) {
		this.id = id;
		this.billingCode = billingCode;
		this.accountId = accountId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBillingCode() {
		return billingCode;
	}

	public void setBillingCode(String billingCode) {
		this.billingCode = billingCode;
	}

	public Integer getAccountId() {
		return accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

}

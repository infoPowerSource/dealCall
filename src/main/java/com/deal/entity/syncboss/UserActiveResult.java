package com.deal.entity.syncboss;


public class UserActiveResult {
	// ums userId
	private Long id;
	// 计费代码
	private String billingCode;
	// 错误代码
	private String errorCode;
	// 错误消息
	private String error;
	// qsboss中的账户ID
	private Integer accountId;
	
	/**
	 * 
	 */
	public UserActiveResult() {
		super();
	}

	public UserActiveResult(Long id, String billingCode,Integer accountId, String errorCode, String error) {
		super();
		this.id = id;
		this.billingCode = billingCode;
		this.accountId = accountId;
		this.errorCode = errorCode;
		this.error = error;
	}

	public static UserActiveResult newSuccessedResult(Long id, Integer accountId, String billingCode) {
		return new UserActiveResult(id, billingCode, accountId, null, null);
	}
	
	public static UserActiveResult newFailedResult(Long id, String billingCode, Integer accountId, String errorCode, String error) {
		return new UserActiveResult(id, billingCode, accountId, errorCode, error);
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
	 * @return the errorCode
	 */
	public String getErrorCode() {
		return errorCode;
	}
	
	/**
	 * @param errorCode the errorCode to set
	 */
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	
	/**
	 * @return the error
	 */
	public String getError() {
		return error;
	}
	
	/**
	 * @param error the error to set
	 */
	public void setError(String error) {
		this.error = error;
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
	
}


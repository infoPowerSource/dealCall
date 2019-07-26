package com.deal.entity.syncboss;

import java.util.Date;
import java.util.List;


public class ResponseDTO {
	// 璇锋眰ID
	private String requestId;
	// 璇锋眰澶勭悊瀹屾垚鏃堕棿
	private Date finishedTime;
	// 鎴愬姛鐨勮处鍙峰垪琛�
	private List<UserActiveResult> successed;
	// 澶辫触鐨勮处鍙峰垪琛�
	private List<UserActiveResult> failed;
	// 鏄惁鎴愬姛
	private Integer result;
	// 鍚堝悓Id
	private Integer contractId;
	// 缁勪欢Name
	private String componentName;
	// 璇锋眰绫诲瀷
	private String type;
	
	/**
	 * 
	 */
	public ResponseDTO() {
		super();
	}

	/**
	 * @param requestId
	 * @param finishedTime
	 * @param successed
	 * @param failed
	 * @param result
	 * @param contractId
	 * @param componentName
	 * @param type
	 */
	public ResponseDTO(String requestId, Date finishedTime,
			List<UserActiveResult> successed, List<UserActiveResult> failed,
			Integer result, Integer contractId, String componentName,
			String type) {
		this.requestId = requestId;
		this.finishedTime = finishedTime;
		this.successed = successed;
		this.failed = failed;
		this.result = result;
		this.contractId = contractId;
		this.componentName = componentName;
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
	 * @return the finishedTime
	 */
	public Date getFinishedTime() {
		return finishedTime;
	}

	/**
	 * @param finishedTime the finishedTime to set
	 */
	public void setFinishedTime(Date finishedTime) {
		this.finishedTime = finishedTime;
	}

	/**
	 * @return the successed
	 */
	public List<UserActiveResult> getSuccessed() {
		return successed;
	}

	/**
	 * @param successed the successed to set
	 */
	public void setSuccessed(List<UserActiveResult> successed) {
		this.successed = successed;
	}

	/**
	 * @return the failed
	 */
	public List<UserActiveResult> getFailed() {
		return failed;
	}

	/**
	 * @param failed the failed to set
	 */
	public void setFailed(List<UserActiveResult> failed) {
		this.failed = failed;
	}

	/**
	 * @return the result
	 */
	public Integer getResult() {
		return result;
	}

	/**
	 * @param result the result to set
	 */
	public void setResult(Integer result) {
		this.result = result;
	}

	/**
	 * @return the contractId
	 */
	public Integer getContractId() {
		return contractId;
	}

	/**
	 * @param contractId the contractId to set
	 */
	public void setContractId(Integer contractId) {
		this.contractId = contractId;
	}

	/**
	 * @return the componentName
	 */
	public String getComponentName() {
		return componentName;
	}

	/**
	 * @param componentName the componentName to set
	 */
	public void setComponentName(String componentName) {
		this.componentName = componentName;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param requestType the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

}


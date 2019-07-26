package com.deal.entity.syncboss;

import java.util.ArrayList;
import java.util.List;

public class UserResultDTO{
	private String requestId;

	private String finishedTime;

	private Integer contractId;

	private String componentName;

	private int result = 1;

	private List<UserResultListDTO> successedList = new ArrayList<UserResultListDTO>();

	private List<UserResultListDTO> failedList = new ArrayList<UserResultListDTO>();

	public UserResultDTO() {
	}

	public UserResultDTO(String requestId, String finishedTime, Integer contractId, String componentName, List<UserResultListDTO> successedList,
			List<UserResultListDTO> failedList) {
		this.requestId = requestId;
		this.finishedTime = finishedTime;
		this.contractId = contractId;
		this.componentName = componentName;
		this.successedList = successedList;
		this.failedList = failedList;
	}

	public int getResult(){
		return result;
	}

	public void setResult(int result){
		this.result = result;
	}

	public String getRequestId(){
		return requestId;
	}

	public void setRequestId(String requestId){
		this.requestId = requestId;
	}

	public String getFinishedTime(){
		return finishedTime;
	}

	public void setFinishedTime(String finishedTime){
		this.finishedTime = finishedTime;
	}

	public Integer getContractId(){
		return contractId;
	}

	public void setContractId(Integer contractId){
		this.contractId = contractId;
	}

	public String getComponentName(){
		return componentName;
	}

	public void setComponentName(String componentName){
		this.componentName = componentName;
	}

	public List<UserResultListDTO> getSuccessedList(){
		return successedList;
	}

	public void setSuccessedList(List<UserResultListDTO> successedList){
		this.successedList = successedList;
	}

	public List<UserResultListDTO> getFailedList(){
		return failedList;
	}

	public void setFailedList(List<UserResultListDTO> failedList){
		this.failedList = failedList;
	}

}

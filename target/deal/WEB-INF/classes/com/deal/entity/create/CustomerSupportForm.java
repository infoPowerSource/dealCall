package com.deal.entity.create;

public class CustomerSupportForm {
	private Long confId;
	private Long custId;
	private String custName;
	private String custEmail;
	private String custContryCode;
	private String custAreacode;
	private String custTel;
	//参与人类型，1:咨询客户 0：专家
	private int custType;
	//参与人状态，0不在会；1在会中
	private int custStatus;
	private String custSpeakStatus;
	
	public Long getCustId(){
		return custId;
	}
	public void setCustId(Long custId){
		this.custId = custId;
	}
	public String getCustName(){
		return custName;
	}
	public void setCustName(String custName){
		this.custName = custName;
	}
	public String getCustEmail(){
		return custEmail;
	}
	public void setCustEmail(String custEmail){
		this.custEmail = custEmail;
	}
	public String getCustContryCode(){
		return custContryCode;
	}
	public void setCustContryCode(String custContryCode){
		this.custContryCode = custContryCode;
	}
	public String getCustAreacode(){
		return custAreacode;
	}
	public void setCustAreacode(String custAreacode){
		this.custAreacode = custAreacode;
	}
	public String getCustTel(){
		return custTel;
	}
	public void setCustTel(String custTel){
		this.custTel = custTel;
	}
	public int getCustType(){
		return custType;
	}
	public void setCustType(int custType){
		this.custType = custType;
	}
	public Long getConfId(){
		return confId;
	}
	public void setConfId(Long confId){
		this.confId = confId;
	}
	public int getCustStatus(){
		return custStatus;
	}
	public void setCustStatus(int custStatus){
		this.custStatus = custStatus;
	}
	public String getCustSpeakStatus(){
		return custSpeakStatus;
	}
	public void setCustSpeakStatus(String custSpeakStatus){
		this.custSpeakStatus = custSpeakStatus;
	}
	

}

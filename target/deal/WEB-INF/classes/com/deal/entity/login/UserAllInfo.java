package com.deal.entity.login;

import java.io.Serializable;
import java.sql.Timestamp;

import org.springframework.util.StringUtils;

public class UserAllInfo implements Serializable{
	private static final long serialVersionUID = 1L;
	private long userId;
	private String userBillingcode = "";
	private String userDisplayName = "";
	private String userMobileNumber = "";
	private String userEmail = "";
	private String countryCode = "";
	private String areaCode = "";
	// private String userTel;
	// private String extensionTel;
	private int userStatus;
	private Timestamp createTime;
	// private Timestamp invalidTime;
	private long siteId;
	private UserSiteInfo siteInfo;
	private String userCustomerCode = "";
	private String companyName = "";
	// plateformName
	private String bridgeName = "";
	private String pcIp;

	//@propType = 2
	private int pcode1InTone;
	private int pcode1OutTone;
	private String pcode2Mode;
	private int pcode2InTone;
	private int pcode2OutTone;

	private Timestamp outDate;

	public UserAllInfo() {
	}

	public Timestamp getOutDate(){
		return outDate;
	}

	public void setOutDate(int timeOut){
		outDate = new Timestamp(System.currentTimeMillis() + timeOut * 1000);//传残单位：秒。
	}
	
	public void setOutDateTimestamp(Timestamp timeOut){
		outDate = timeOut;
	}

	public String getUserCustomerCode(){
		return userCustomerCode;
	}

	public void setUserCustomerCode(String userCustomerCode){
		if(!StringUtils.isEmpty(userCustomerCode)){
			this.userCustomerCode = userCustomerCode;
		}
	}

	public long getUserId(){
		return userId;
	}

	public void setUserId(long userId){
		this.userId = userId;
	}

	public String getUserBillingcode(){
		return userBillingcode;
	}

	public void setUserBillingcode(String userBillingcode){
		if(!StringUtils.isEmpty(userBillingcode)){
			this.userBillingcode = userBillingcode;
		}
	}

	public String getUserDisplayName(){
		return userDisplayName;
	}

	public void setUserDisplayName(String userDisplayName){
		if(!StringUtils.isEmpty(userDisplayName)){
			this.userDisplayName = userDisplayName;
		}
	}

	public String getUserMobileNumber(){
		return userMobileNumber;
	}

	public void setUserMobileNumber(String userMobileNumber){
		if(!StringUtils.isEmpty(userMobileNumber)){
			this.userMobileNumber = userMobileNumber;
		}
	}

	public String getUserEmail(){
		return userEmail;
	}

	public void setUserEmail(String userEmail){
		if(!StringUtils.isEmpty(userEmail)){
			this.userEmail = userEmail;
		}
	}

	public String getCountryCode(){
		return countryCode;
	}

	public void setCountryCode(String countryCode){
		if(!StringUtils.isEmpty(countryCode)){
			this.countryCode = countryCode;
		}
	}

	public String getAreaCode(){
		return areaCode;
	}

	public void setAreaCode(String areaCode){
		if(!StringUtils.isEmpty(areaCode)){
			this.areaCode = areaCode;
		}
	}

	public int getUserStatus(){
		return userStatus;
	}

	public void setUserStatus(int userStatus){
		this.userStatus = userStatus;
	}

	public Timestamp getCreateTime(){
		return createTime;
	}

	public void setCreateTime(Timestamp createTime){
		this.createTime = createTime;
	}

	public long getSiteId(){
		return siteId;
	}

	public void setSiteId(long siteId){
		this.siteId = siteId;
	}

	public UserSiteInfo getSiteInfo(){
		return siteInfo;
	}

	public void setSiteInfo(UserSiteInfo siteInfo){
		this.siteInfo = siteInfo;
	}

	public String getCompanyName(){
		return companyName;
	}

	public void setCompanyName(String companyName){
		if(!StringUtils.isEmpty(companyName)){
			this.companyName = companyName;
		}
	}

	public String getBridgeName(){
		return bridgeName;
	}

	public void setBridgeName(String bridgeName){
		this.bridgeName = bridgeName;
	}

	public String getPcIp(){
		return pcIp;
	}

	public void setPcIp(String pcIp){
		this.pcIp = pcIp;
	}

	public int getPcode1InTone(){
		return pcode1InTone;
	}

	public void setPcode1InTone(int pcode1InTone){
		this.pcode1InTone = pcode1InTone;
	}

	public int getPcode1OutTone(){
		return pcode1OutTone;
	}

	public void setPcode1OutTone(int pcode1OutTone){
		this.pcode1OutTone = pcode1OutTone;
	}

	public String getPcode2Mode(){
		return pcode2Mode;
	}

	public void setPcode2Mode(String pcode2Mode){
		this.pcode2Mode = pcode2Mode;
	}

	public int getPcode2InTone(){
		return pcode2InTone;
	}

	public void setPcode2InTone(int pcode2InTone){
		this.pcode2InTone = pcode2InTone;
	}

	public int getPcode2OutTone(){
		return pcode2OutTone;
	}

	public void setPcode2OutTone(int pcode2OutTone){
		this.pcode2OutTone = pcode2OutTone;
	}

}

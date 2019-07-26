package com.deal.entity.syncboss;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class SyncBossConfInfoParams{
	/**
	 * 会议计费代码
	 */
	private String confBillingCode;
	/**
	 * 关联主计费代码
	 */
	private String parentBillingCode;
	/**
	 * 主持人密码
	 */
	private String hostPassword;
	/**
	 * 参与人密码
	 */
	private String guestPassword;
	/**
	 * 会议名称
	 */
	private String confName;
	/**
	 * 会议状态 1：正常;2:删除，不传默认为1
	 */
	private String confStatus = "1";

	/**
	 * 会议开始时间
	 */
	private Long confBeginTime;
	/**
	 * 会议结束时间
	 */
	private Long confEndTime;

	public String getConfBillingCode(){
		return confBillingCode;
	}

	public void setConfBillingCode(String confBillingCode){
		this.confBillingCode = confBillingCode;
	}

	public String getParentBillingCode(){
		return parentBillingCode;
	}

	public void setParentBillingCode(String parentBillingCode){
		this.parentBillingCode = parentBillingCode;
	}

	public String getHostPassword(){
		return hostPassword;
	}

	public void setHostPassword(String hostPassword){
		this.hostPassword = hostPassword;
	}

	public String getGuestPassword(){
		return guestPassword;
	}

	public void setGuestPassword(String guestPassword){
		this.guestPassword = guestPassword;
	}

	public String getConfName(){
		return confName;
	}

	public void setConfName(String confName){
		this.confName = confName;
	}

	public String getConfStatus(){
		return confStatus;
	}

	public void setConfStatus(String confStatus){
		this.confStatus = confStatus;
	}

	public Long getConfBeginTime(){
		return confBeginTime;
	}

	public void setConfBeginTime(Long confBeginTime){
		this.confBeginTime = confBeginTime;
	}

	public Long getConfEndTime(){
		return confEndTime;
	}

	public void setConfEndTime(Long confEndTime){
		this.confEndTime = confEndTime;
	}
}

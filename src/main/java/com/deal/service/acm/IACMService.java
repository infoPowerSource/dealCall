package com.deal.service.acm;

import com.deal.entity.create.ConferenceInfo;
import com.deal.entity.login.UserInfo;

public interface IACMService{
	
    /**
     * 创建会议
     * @param 会议的所有信息
     * @param 
     * @return int数字，返回开通会议结果
     */
	public int createConference(ConferenceInfo confInfo);
	 /**
     * 更新会议
     * @param 会议的所有信息
     * @param 
     * @return int数字，返回开通会议结果
     */
	public int updateConference(ConferenceInfo confInfo);
	
	/**
     * 更新会议
     * @param 会议的所有信息
     * @param 
     * @return int数字，返回开通会议结果
     */
	public int deleteConferenceById(ConferenceInfo confInfo);
	public int deleteConfByBillingcode(String confBillingcode);
	public int stopRecord(ConferenceInfo confInfo);
	public int startRecord(ConferenceInfo confInfo);
}

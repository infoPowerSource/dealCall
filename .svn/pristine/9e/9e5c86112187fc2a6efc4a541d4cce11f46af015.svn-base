package com.deal.service.sms;

import java.util.Map;

import com.deal.entity.create.ConferenceInfo;
import com.deal.entity.sms.Note;
import com.deal.entity.sms.SMSSendTask;

public interface ISMSService {
	
	/**
	 * 调用短信发送接口发送短信
	 * @param note 短信实体
	 * @param noteType 短信类型
	 * @param id *0记录id
	 * @return 0:失败 ;1:成功
	 */
	public int sendSMS(Note note,ConferenceInfo confInfo);
	
	//Map include(mailType,customerInfo,conferenceInfo)
	public void sendSMSByTask(Map<String, Object> params);
	//短信校验码接口
	public int sendVerifySMS(String phone,String content,String billingcode);
	//立即发送接口
	public int sendSMS(SMSSendTask sendTask);

}

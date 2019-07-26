package com.deal.service.mss;

import java.util.Map;

import com.deal.entity.mss.MSSSendTask;

import net.fortuna.ical4j.model.ValidationException;

public interface IMSSService {

	//Map包括 邮件类型（mailType）,会议信息(conferenceInfo),和客户信息——新增的有值，其他场景这个为空（customerInfoList）
//	public void sendMail(Map<String,Object> params) throws ValidationException;
	
	//Map include(mailType,conferenceInfo,customerInfo)
	public void sendMailByTask(Map<String, Object> params) throws ValidationException;
	
	//Map包括收件人名称，收件人的姓名，邮件内容
	public void sendMailForChangePwd(Map<String,String> params);

	/**
	 * 发送会后录音文件
	 * @author zhipeng.xu
	 * @param radioMap
	 * @param email 
	 */
	public void saveMailForRadio(Map radioMap, String email);
}

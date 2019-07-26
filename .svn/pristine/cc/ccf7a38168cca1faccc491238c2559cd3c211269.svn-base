package com.deal.service.task;

import java.sql.Timestamp;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.deal.dao.mss.MailSendDao;
import com.deal.dao.mss.MailSendTaskDao;
import com.deal.entity.gnet.GnetSsMailSendingTask;
import com.deal.entity.mss.MSSSendTask;
import com.deal.monitor.cache.RedisService;
import com.deal.util.Consts;
import com.google.common.collect.Lists;

@Component("mailSendTaskJob")
public class MailSendTaskJob{
	private static Logger logger = LoggerFactory.getLogger(MailSendTaskJob.class);

	@Autowired
	private MailSendDao sendDao;
	@Autowired
	private MailSendTaskDao taskDao;

	@Value("${email.server.address}")
	private String emailServerAddress;
	@Value("${email.defaultEmailSenderAddress}")
	private String emailSenderAddress;

	public synchronized void execute(){
		logger.info("开始执行发送邮件任务-------");
		List<MSSSendTask> taskList = Lists.newArrayList();
		taskList = taskDao.getTaskListByDate();
		for(MSSSendTask task : taskList){
			if(!RedisService.IsExistOper(String.valueOf(task.getEmailId()), "mailSendTaskJob")){
				RedisService.putOperToCache(String.valueOf(task.getEmailId()), "mailSendTaskJob");
				GnetSsMailSendingTask send = new GnetSsMailSendingTask();
				send.setIsHandled((short) Consts.IS_NOT_HANDLE_FLAG);
				send.setResultCode((short) Consts.EMAIL_MSS_RESULT_CODE);
				send.setCreateTime(new Timestamp(System.currentTimeMillis()));
				send.setPostfixAddress(emailServerAddress);
				send.setMailTitle(task.getEmailTitle());
				send.setReceiverAddress(task.getEmailReceiver());
				send.setSenderAddress(emailSenderAddress);
				send.setSenderName(Consts.EMAIL_MSS_SEND_NAME);
				send.setReceiverName(task.getReceiveName());
				send.setSsMailId((long) Consts.EMAIL_MSS_NEGATIVE_FLAG);
				send.setMailContent(task.getEmailContent());
				send.setSsMailRecipientId((long)Consts.EMAIL_MSS_NEGATIVE_FLAG);
				send.setUpdateTime(new Timestamp(System.currentTimeMillis()));
				if(task.getEmailType() == Consts.EMAIL_TYPE_FOR_SUPPORT_ACCOUNT_ACTIVE){
					send.setType((short) Consts.EMAIL_GNET_CONTENT_TYPE);
				}else{
					send.setMailContentExt(task.getEmailContentExt());
					send.setType((short) Consts.EMAIL_GNET_EXT_CONTENT_TYPE);
				}
				try{
					sendDao.save(send);
					task.setIsHandle(Consts.IS_HANDLE_FLAG);
					logger.info("存入MSS服务器数据库成功，邮件收件人地址:" + task.getEmailReceiver() + "收件人姓名:" + task.getReceiveName());
				}catch (Exception e){
					logger.error("存入MSS服务器数据库出错，邮件收件人地址:" + task.getEmailReceiver() + "收件人姓名:" + task.getReceiveName() + "错误信息:" + e.toString());
				}
			}
		}
		logger.info("结束执行发送邮件任务-------");
	}
}

package com.deal.service.task;

import java.sql.Timestamp;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.deal.dao.mss.MailSendTaskHistoryDao;
import com.deal.dao.mss.MailSendTaskDao;
import com.deal.entity.mss.MSSSendTask;
import com.deal.entity.mss.MSSSendTaskHistory;
import com.deal.entity.sms.SMSSendTask;
import com.deal.monitor.cache.RedisService;
import com.google.common.collect.Lists;

@Component("mailSendTaskToHistoryJob")
public class MailSendTaskToHistoryJob{
	private static Logger logger = LoggerFactory.getLogger(MailSendTaskToHistoryJob.class);

	@Autowired
	private MailSendTaskDao taskDao;

	@Autowired
	private MailSendTaskHistoryDao historyDao;

	public synchronized void execute(){
		logger.info("开始执行mail任务到历史库任务-------");
		List<MSSSendTask> processedList = taskDao.getProcessedTaskList();
		List<MSSSendTask> successList = Lists.newArrayList();
		for(MSSSendTask task : processedList){
			if(!RedisService.IsExistOper(String.valueOf(task.getEmailId()) + "history","mailSendTaskToHistoryJob")){
				RedisService.putOperToCache(String.valueOf(task.getEmailId())+ "history","mailSendTaskToHistoryJob");
				MSSSendTaskHistory historyTask = new MSSSendTaskHistory();
				historyTask.setEmailContent(task.getEmailContent());
				historyTask.setEmailContentExt(task.getEmailContentExt());
				historyTask.setEmailId(task.getEmailId());
				historyTask.setEmailSender(task.getEmailSender());
				historyTask.setEmailTitle(task.getEmailTitle());
				historyTask.setEmailType(task.getEmailType());
				historyTask.setEmilReceiver(task.getEmailReceiver());
				historyTask.setSendTime(task.getSendTime());
				historyTask.setFinishTime(new Timestamp(System.currentTimeMillis()));
//				BeanUtils.copyProperties(task, historyTask);
				try{
					historyDao.save(historyTask);
					successList.add(task);
				}catch (Exception e){
					logger.error("存入邮件历史任务表出错，邮件ID:" + task.getEmailId() + "异常信息:" + e);
				}
			}
		}
		for(MSSSendTask task : successList){
			try{
				taskDao.deleteById(task.getEmailId());
			}catch (Exception e){
				logger.error("删除邮件任务表出错，短信ID:" + task.getEmailId() + "异常信息:" + e);
			}
		}
		logger.info("结束执行mail任务到历史库任务-------");
	}
}

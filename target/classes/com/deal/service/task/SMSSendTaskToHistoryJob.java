package com.deal.service.task;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.deal.entity.sms.SMSSendTask;
import com.deal.entity.sms.SMSSendTaskHistory;
import com.deal.monitor.cache.RedisService;
import com.google.common.collect.Lists;
import com.deal.dao.sms.SMSSendTaskDao;
import com.deal.dao.sms.SMSSendTaskHistoryDao;

@Component("smsSendTaskToHistoryJob")
public class SMSSendTaskToHistoryJob{
	private static Logger logger = LoggerFactory.getLogger(SMSSendTaskToHistoryJob.class);
	@Autowired
	private SMSSendTaskDao taskDao;

	@Autowired
	private SMSSendTaskHistoryDao historyDao;

	public synchronized void execute(){
		List<SMSSendTask> processedList = taskDao.getProcessedTaskList();
		List<SMSSendTask> successList = Lists.newArrayList();
		logger.info("开始执行短信任务到历史库任务-------");
		for(SMSSendTask task : processedList){
			if(!RedisService.IsExistOper(String.valueOf(task.getSmsId())+"history","smsSendTaskToHistoryJob")){
				RedisService.putOperToCache(String.valueOf(task.getSmsId())+"history","smsSendTaskToHistoryJob");
				SMSSendTaskHistory history = new SMSSendTaskHistory();
				BeanUtils.copyProperties(task, history);
				try{
					historyDao.save(history);
					successList.add(task);
				}catch (Exception e){
					logger.error("存入短信历史任务表出错，短信ID:" + task.getSmsId() + "异常信息:" + e);
				}
			}
		}
		for(SMSSendTask task : successList){
			try{
				taskDao.deleteById(task.getSmsId());
			}catch (Exception e){
				logger.error("删除短信任务表出错，短信ID:" + task.getSmsId() + "异常信息:" + e);
			}
		}
		logger.info("结束执行短信任务到历史库任务-------");
	}
}

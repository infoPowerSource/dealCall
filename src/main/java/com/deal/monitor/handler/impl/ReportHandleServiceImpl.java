package com.deal.monitor.handler.impl;

import java.sql.Timestamp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deal.dao.create.ConferenceDao;
import com.deal.dao.report.ConferenceTaskManagerDao;
import com.deal.entity.create.ConferenceInfo;
import com.deal.entity.create.ConferenceWaitMake;
import com.deal.entity.create.ConferenceWaitMakeId;
import com.deal.monitor.handler.IReportHandleService;
import com.deal.util.Consts;

@Service
public class ReportHandleServiceImpl implements IReportHandleService{
	
	@Autowired
	private ConferenceTaskManagerDao taskManagerDao;
	@Autowired
	private ConferenceDao confDao;
	public static final Logger logger = LoggerFactory.getLogger(ReportHandleServiceImpl.class);

	@Override
	public void createConferenceTask(String billingCode){
		try{

			// 根据会议billingCode查询会议信息
			ConferenceInfo conference = confDao.getConfByBillingCode(billingCode);
			if(null != conference) {
				createConferenceRecordingTask(conference.getConfId());
				Timestamp now = new Timestamp(System.currentTimeMillis());
				/**
				 * 创建会议会后报告信息调度
				 */
				ConferenceWaitMakeId taskId = new ConferenceWaitMakeId();
				taskId.setConferenceId(conference.getConfId());
				taskId.setTimerType(Consts.CONFERENCE_TIMER_TYPE_REPORT);
				/**
				 * 根据会议信息更新会议任务
				 */
				ConferenceWaitMake confMake = taskManagerDao.getWaitMakeForMonit(taskId);
				if(confMake == null) {
					ConferenceWaitMake makeAdd = new ConferenceWaitMake();
					makeAdd.setId(taskId);
					makeAdd.setCreateTime(now);
					makeAdd.setGenerationTime(now);
					makeAdd.setTaskResult("0");
					makeAdd.setIfMake((short) 0);
					/**
					 * 保存报表调度任务
					 */
					taskManagerDao.saveForMonit(makeAdd);
				}else{
					/**
					 * 更新报表调度任务
					 */
					confMake.setCreateTime(now);
					confMake.setGenerationTime(now);
					confMake.setIfMake((short)0);
					confMake.setTaskResult("0");
					taskManagerDao.updateForMonit(confMake);
				}
			}
		}catch (Exception e){
			logger.error("会议结束，会后任务处理失败 billingCode " + billingCode);
			e.printStackTrace();
		}

		logger.info("会议结束，会后任务处理结束 billingCode " + billingCode);
	}
	
	/**
	 * 创建会议录音任务
	 * 
	 * @param conferenceId
	 */
	protected void createConferenceRecordingTask(Long confId){
		Timestamp now = new Timestamp(System.currentTimeMillis());
		/**
		 * 创建会议录音调度
		 */
		ConferenceWaitMakeId taskId = new ConferenceWaitMakeId();
		taskId.setConferenceId(confId);
		taskId.setTimerType(Consts.CONFERENCE_TIMER_TYPE_RECORD);
		/**
		 * 根据会议录音更新会议任务
		 */
		ConferenceWaitMake confMake = taskManagerDao.getWaitMakeForMonit(taskId);
		if(confMake == null) {
			ConferenceWaitMake makeAdd = new ConferenceWaitMake();
			makeAdd.setId(taskId);
			makeAdd.setCreateTime(now);
			makeAdd.setGenerationTime(now);
			makeAdd.setTaskResult("0");
			makeAdd.setIfMake((short) 0);
			/**
			 * 保存报表调度任务
			 */
			taskManagerDao.saveForMonit(makeAdd);
		}else{
			/**
			 * 更新报表调度任务
			 */
			confMake.setCreateTime(now);
			confMake.setGenerationTime(now);
			confMake.setIfMake((short)0);
			confMake.setTaskResult("0");
			taskManagerDao.updateForMonit(confMake);
		}
	}


}

package com.deal.service.report.impl;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.deal.dao.report.ConferenceRadioDao;
import com.deal.dao.report.ConferenceReportDao;
import com.deal.dao.report.ConferenceTaskManagerDao;
import com.deal.entity.create.ConferenceInfo;
import com.deal.entity.create.ConferenceReportInfo;
import com.deal.entity.create.ConferenceWaitMake;
import com.deal.entity.create.ConferenceWaitMakeId;
import com.deal.service.create.IConferenceService;
import com.deal.service.report.IMeetingReportService;
import com.deal.util.Consts;
import com.deal.util.ExcelUtils;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * 会后报告处理
 * 
 * @author zhipeng.xu
 *
 */
@Service
public class MeetingReportServiceImpl implements IMeetingReportService{
	public static final Logger logger = LoggerFactory.getLogger(MeetingReportServiceImpl.class);
	@Autowired
	private IConferenceService confService;
	@Autowired
	private ConferenceRadioDao radioDao;
	@Autowired
	private ConferenceReportDao reportDao;
	@Autowired
	private ConferenceTaskManagerDao taskManagerDao;
	/**
	 * 会后报告
	 * 
	 * @param billingCode
	 */
	public void work(String billingCode){
		Assert.hasText(billingCode, "billingcode is null");
		logger.info("create report billingCode : {}", billingCode);
		createConferenceTask(billingCode);
	}

	/**
	 * 创建报表任务
	 * 
	 * @param billingCode
	 *            会议billingcode
	 */
	protected void createConferenceTask(String billingCode){

		try{

			// 根据会议billingCode查询会议信息
			ConferenceInfo conference = confService.getConfByBillingCode(billingCode);
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
				ConferenceWaitMake confMake = taskManagerDao.getWaitMake(taskId);
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
					taskManagerDao.save(makeAdd);
				}else{
					/**
					 * 更新报表调度任务
					 */
					confMake.setCreateTime(now);
					confMake.setGenerationTime(now);
					confMake.setIfMake((short)0);
					confMake.setTaskResult("0");
					taskManagerDao.update(confMake);
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
		ConferenceWaitMake confMake = taskManagerDao.getWaitMake(taskId);
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
			taskManagerDao.save(makeAdd);
		}else{
			/**
			 * 更新报表调度任务
			 */
			confMake.setCreateTime(now);
			confMake.setGenerationTime(now);
			confMake.setIfMake((short)0);
			confMake.setTaskResult("0");
			taskManagerDao.update(confMake);
		}
	}

	/**
	 * 根据会议Id 查询会后报告
	 * 
	 * @param confId
	 * @return
	 */
	public List<ConferenceReportInfo> getReportByConfId(String confId){
		return reportDao.getReportByConfId(confId);
	}

	public Map<String, Object> getReportDataByConfId(String confId){
		Map<String,Object> reportDataMap=Maps.newHashMap();
		List<ConferenceReportInfo> reportList=Lists.newArrayList();
		reportList=reportDao.getReportByConfId(confId);
		String filePath = "";
		String fileName = "";
		if(reportList.size()>0){
			for(ConferenceReportInfo reportInfo:reportList){
				filePath = reportInfo.getReportUrl();
				fileName = reportInfo.getReportName();
			}
			reportDataMap=ExcelUtils.readExcelData(fileName,filePath);
		}else{
			logger.info("改会议的会议报告没查到,confId:"+confId);
			throw new RuntimeException("改会议的会议报告没查到,confId:"+confId);
		}
		return reportDataMap;
	}

}

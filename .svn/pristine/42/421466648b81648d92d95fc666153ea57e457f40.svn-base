package com.deal.service.report.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deal.dao.report.ConferenceRadioDao;
import com.deal.dao.report.RecordDao;
import com.deal.entity.create.ConferenceInfo;
import com.deal.entity.create.ConferenceRadioInfo;
import com.deal.entity.create.ConferenceWaitMake;
import com.deal.entity.report.BoRdRecordFile;
import com.deal.service.report.IConferenceRecordService;
import com.deal.util.Consts;
import com.deal.util.DateFormatUtil;

@Service
public class ConferenceRecordServiceImpl implements IConferenceRecordService{

	@Autowired
	private ConferenceRadioDao radioDao;
	@Autowired
	private RecordDao recordDao;
	private static Logger logger = LoggerFactory.getLogger(ConferenceRecordServiceImpl.class);

	/**
	 * 为未监控的会议查找录音文件
	 * 
	 * @param conferenceWaitMake
	 *            会议定时任务对象
	 * @return
	 */
	public boolean addRecordFileForNoActiveConf(ConferenceWaitMake conferenceWaitMake, ConferenceInfo conf){

		if(null == conferenceWaitMake) {
			logger.error("getRecordFileForNoActiveConf error   -- byConferenceWaitMake is null");
			return false;
		}
		// 获取会议信息
		List<BoRdRecordFile> recordFileList = null;
		Long confId = conferenceWaitMake.getId().getConferenceId();
		// 根据会议confId
		String billingcode = conf.getConfBillingcode();
		Timestamp startDate = DateFormatUtil.str2TimeStamp(DateFormatUtil.DateToStr(conf.getBeginTime(), "yyyy-MM-dd HH:mm:ss"));
		Timestamp endDate = DateFormatUtil.str2TimeStamp(DateFormatUtil.DateToStr(conf.getEndTime(), "yyyy-MM-dd HH:mm:ss"));

		if(null == billingcode || "".equals(billingcode.trim())) {
			return false;
		}
		boolean flag = false;
		try{
			// 获取录音
			recordFileList = selectRecordFileFromMis(startDate, endDate, billingcode);
			// 没有录音，直接返回
			if(null == recordFileList || recordFileList.size() == 0) {
				return false;
			}
			// 插入录音
			flag = addRecord(conferenceWaitMake, recordFileList, confId, billingcode, startDate.toString(), endDate.toString());
		}catch (Exception e){
			logger.error("getRecordFileForNoActiveConf error billingcode=" + billingcode, e);
		}
		return flag;
	}

	/**
	 * 抽取公共方法，从本地（summit同步到本地）获取录音文件名称及地址
	 * 
	 * @param startDate
	 *            录音开始时间
	 * @param endDate
	 *            录音结束时间
	 * @param billingcode
	 *            会议billingcode
	 * @return
	 */
	public List<BoRdRecordFile> selectRecordFileFromMis(Timestamp startDate, Timestamp endDate, String billingcode){
		if(null == billingcode || "".equals(billingcode.trim())) {
			logger.error("getRecordFileFromMis billingcode is null");
			return null;
		}
		if("".equals(startDate.toString()) || "".equals(endDate.toString())) {
			// 开始时间和结束时间都为null，无法查询，则直接返回空
			logger.error("getRecordFileFromMis startDate or endDate is null");
			return null;
		}
		List<BoRdRecordFile> recordFileList = new ArrayList<>();
		Timestamp addSecodeDate1 = DateFormatUtil.addMinutesInTimestamp(startDate, Consts.CONFSTARTTIME_ADD_SECOND / 60);
		// 录音文件搜索范围改成会议开始时间到会议结束时间加90秒
		Timestamp addSecodeDate = DateFormatUtil.addMinutesInTimestamp(endDate, Consts.CONFENDTIME_ADD_SECOND / 60);
		if(addSecodeDate1 != null) {
			startDate = addSecodeDate1;
		}
		if(addSecodeDate != null) {
			endDate = addSecodeDate;
		}
		BoRdRecordFile recordFile = new BoRdRecordFile();
		recordFile.setBgntime(startDate);
		recordFile.setEndtime(endDate);
		recordFile.setBillingcode(billingcode);
		try{
			recordFileList = recordDao.selectRecord(recordFile);
			if(recordFileList == null || recordFileList.size() == 0) {
				logger.error("未找到从summit平台同步过来的录音文件，会议billingCode" + billingcode);
			}
		}catch (Exception e){
			logger.error("getRecordFileFromMis 从mis获取录音失败 会议billingcode=" + billingcode, e);
		}
		return recordFileList;
	}

	/*
	 * @param taskResult
	 * 
	 * @param recordFileList
	 * 
	 * @param confId
	 * 
	 * @param billingcode
	 * 
	 * @return
	 * 
	 * @throws Exception
	 */
	public boolean addRecord(ConferenceWaitMake conferenceWaitMake, List<BoRdRecordFile> recordFileList, Long confId, String billingcode, String meetmeStartTime,
			String meetmeEndTime) throws Exception{
		// 如果没有找到录音文件，返回false
		if(null == recordFileList || recordFileList.size() == 0) {
			return false;
		}
		if(null == conferenceWaitMake) {
			logger.error("insertRecordFileToDeal -- conferenceWaitMake is null , billingcode=" + billingcode);
			return false;
		}
		//String taskResult = conferenceWaitMake.getTaskResult();
		Integer timerType = conferenceWaitMake.getId().getTimerType();
		if(null == timerType) {
			logger.error("insertRecordFileToDeal -- timerType is null , billingcode=" + billingcode);
			return false;
		}
		// 找到录音文件，查询出信息，插入本地录音库
		for(BoRdRecordFile boRdRecordFile : recordFileList){
			String fileName = boRdRecordFile.getFilename();
			if(null == fileName || "".equals(fileName.trim())) {
				logger.error("filename is null");
				continue;
			}
			// 先查找主动服务库中是否已有该条录音信息
			List<ConferenceRadioInfo> byRecordFiles = null;
			ConferenceRadioInfo confRadio = new ConferenceRadioInfo();
			confRadio.setFileName(fileName);
			byRecordFiles = radioDao.getLocalRecord(confRadio);
			// 没有该条录音信息就插入，有就不做处理
			if(null == byRecordFiles || byRecordFiles.size() == 0) {// test pass
				ConferenceRadioInfo dealRecordFile = new ConferenceRadioInfo();
				ConferenceInfo confInfo = new ConferenceInfo();
				confInfo.setConfId(confId);
				dealRecordFile.setConfInfo(confInfo);
				Timestamp now = new Timestamp(System.currentTimeMillis());
				dealRecordFile.setCreateTime(now);
				dealRecordFile.setFileName(boRdRecordFile.getFilename());
				dealRecordFile.setFileUrl(boRdRecordFile.getFilepath());
				dealRecordFile.setIsValid(0);
				radioDao.save(dealRecordFile);
				logger.info("录音文件插入 ,会议id=" + confId + ",Filename=" + boRdRecordFile.getFilename() + ",billingcode=" + billingcode);
			}else{
				logger.info("录音文件已存在 ,会议id=" + confId + ",Filename=" + boRdRecordFile.getFilename() + ",billingcode=" + billingcode);
			}
		}
		return true;
	}

	/**
	 * 根据会议ID 查询录音文件
	 */
	public List<ConferenceRadioInfo> getRadioInfoByConfId(String confId){
		return radioDao.getRadioByConfId(confId);
	}

}

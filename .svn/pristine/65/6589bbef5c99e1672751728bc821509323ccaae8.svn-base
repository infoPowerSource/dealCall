package com.deal.service.task;

import java.sql.Timestamp;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.deal.dao.create.ConferenceDao;
import com.deal.dao.create.ConferenceHistoryDao;
import com.deal.dao.create.CustomerDao;
import com.deal.dao.create.CustomerHistoryDao;
import com.deal.dao.mss.MailSendHistoryRecordDao;
import com.deal.dao.mss.MailSendRecordDao;
import com.deal.dao.report.ConferenceRadioDao;
import com.deal.dao.report.ConferenceRadioHistoryDao;
import com.deal.dao.report.ConferenceReportDao;
import com.deal.dao.report.ConferenceReportHistoryDao;
import com.deal.dao.sms.SMSSendHistoryRecordDao;
import com.deal.dao.sms.SMSSendRecordDao;
import com.deal.dao.support.SupportHandleHistoryRecodeDao;
import com.deal.dao.support.SupportHandleRecodeDao;
import com.deal.entity.create.ConferenceInfo;
import com.deal.entity.create.ConferenceInfoHistory;
import com.deal.entity.create.ConferenceRadioHistoryInfo;
import com.deal.entity.create.ConferenceRadioInfo;
import com.deal.entity.create.ConferenceReportHistoryInfo;
import com.deal.entity.create.ConferenceReportInfo;
import com.deal.entity.create.CustomerInfo;
import com.deal.entity.create.CustomerInfoHistory;
import com.deal.entity.mss.MSSSendHistoryRecord;
import com.deal.entity.mss.MSSSendRecord;
import com.deal.entity.sms.SMSSendHistoryRecord;
import com.deal.entity.sms.SMSSendRecord;
import com.deal.entity.support.SupportHandlerRecord;
import com.deal.entity.support.SuppportHandleHistoryRecord;
import com.deal.monitor.cache.RedisService;
import com.deal.util.DateFormatUtil;
import com.google.common.collect.Lists;

@Component("conferenceInfoToHistoryJob")
public class ConferenceInfoToHistoryJob{
	private static Logger logger = LoggerFactory.getLogger(ConferenceInfoToHistoryJob.class);
	@Autowired
	private ConferenceDao confDao;
	@Autowired
	private CustomerDao custDao;
	@Autowired
	private ConferenceHistoryDao confHisDao;
	@Autowired
	private CustomerHistoryDao custHisDao;
	@Autowired
	private ConferenceRadioDao radioDao;
	@Autowired
	private ConferenceReportDao reportDao;
	@Autowired
	private ConferenceRadioHistoryDao  radioHistoryDao;
	@Autowired
	private ConferenceReportHistoryDao reportHistoryDao;
	@Autowired
	private SupportHandleRecodeDao   supportHandleDao;
	@Autowired
	private SupportHandleHistoryRecodeDao  supportHandleHisDao;
	@Autowired
	private MailSendRecordDao mailSendRecordDao;
	@Autowired
	private SMSSendRecordDao smsSendRecordDao;
	@Autowired
	private MailSendHistoryRecordDao  mailSendHisDao;
	@Autowired
	private SMSSendHistoryRecordDao  smsSendHisDao;

	public synchronized void execute(){
		logger.info("开始执行回收会议到历史库的任务--------------");
		Timestamp currTime = new Timestamp(System.currentTimeMillis());
		Timestamp beforeThreeMonthTime = DateFormatUtil.addMonthInTimestamp(currTime, -3);
		List<ConferenceInfo> successConfList = Lists.newArrayList();
		List<CustomerInfo> successCustList = Lists.newArrayList();
		List<Long> successRadioIdList = Lists.newArrayList();
		List<Long> successReportIdList =Lists.newArrayList();
		List<Long> successHanlerRecordList=Lists.newArrayList();
		List<Long> successEmailSendRecordList=Lists.newArrayList();
		List<Long> successSmsSendRecordList=Lists.newArrayList();
		List<ConferenceInfo> confList = confDao.getConfListBeforeThrMon(beforeThreeMonthTime);
		logger.info("查询出三月前的会议条数"+confList.size());
		for(ConferenceInfo conf : confList){
			if(!RedisService.IsExistOper(String.valueOf(conf.getConfId()), "conferenceInfoToHistoryJob")){
				RedisService.putOperToCache(String.valueOf(conf.getConfId()), "conferenceInfoToHistoryJob");
				ConferenceInfoHistory confHistory = new ConferenceInfoHistory();
				BeanUtils.copyProperties(conf, confHistory);
				try{
					confHisDao.save(confHistory);
					successConfList.add(conf);
				}catch (Exception e){
					logger.error("保存三月会议信息到历史表出错", e.getMessage());
				}
				List<CustomerInfo> custList = custDao.getThrMonConfCustList(conf.getConfId());
				for(CustomerInfo cust : custList){
					CustomerInfoHistory custHistory = new CustomerInfoHistory();
					BeanUtils.copyProperties(cust, custHistory);
					custHistory.setConfInfo(cust.getConfInfo().getConfId());
					try{
						custHisDao.save(custHistory);
						successCustList.add(cust);
					}catch (Exception e){
						logger.error("保存三月前会议参会人信息到历史表出错", e.getMessage());
					}
				}
				List<ConferenceRadioInfo> radioInfoList=radioDao.getThrMonRadioInfoList(conf.getConfId());
                for(ConferenceRadioInfo redioInfo:radioInfoList){
                	ConferenceRadioHistoryInfo radioHitory=new ConferenceRadioHistoryInfo();
                	BeanUtils.copyProperties(redioInfo, radioHitory);
                	radioHitory.setConfInfo(redioInfo.getConfInfo().getConfId());
                	try{
                		radioHistoryDao.save(radioHitory);
                		successRadioIdList.add(redioInfo.getRadioId());
					}catch (Exception e){
						logger.error("保存三月前会议radio信息到历史表出错", e.getMessage());
					}
				}
				List<ConferenceReportInfo> reportInfoList=reportDao.getThrMonReportInfoList(conf.getConfId());
				for(ConferenceReportInfo reportInfo:reportInfoList){
					ConferenceReportHistoryInfo reportHistory=new ConferenceReportHistoryInfo();
					BeanUtils.copyProperties(reportInfo, reportHistory);
					reportHistory.setConfInfo(reportInfo.getConfInfo().getConfId());
					try{
						reportHistoryDao.save(reportHistory);
						successReportIdList.add(reportInfo.getReportId());
					}catch (Exception e){
						logger.error("保存三月前会议report信息到历史表出错", e.getMessage());
					}
				}
				List<SupportHandlerRecord> supportHandlerList=supportHandleDao.getThrMonSupportHandlerList(conf.getConfId());
				for(SupportHandlerRecord supportHanler:supportHandlerList){
					SuppportHandleHistoryRecord historyRecode=new SuppportHandleHistoryRecord();
					BeanUtils.copyProperties(supportHanler, historyRecode);
					historyRecode.setConfInfo(supportHanler.getConfInfo().getConfId());
					try{
						supportHandleHisDao.save(historyRecode);
						successHanlerRecordList.add(supportHanler.getRecordId());
					}catch (Exception e){
						logger.error("保存三月前会议report信息到历史表出错", e.getMessage());
					}
				}
				List<MSSSendRecord> mssRecordList=mailSendRecordDao.getThrMonEmailSendRecordList(conf.getConfId());
				for(MSSSendRecord record:mssRecordList){
					MSSSendHistoryRecord historyRecord=new MSSSendHistoryRecord();
					BeanUtils.copyProperties(record, historyRecord);
					historyRecord.setConfId(record.getConfInfo().getConfId());
					try{
						mailSendHisDao.save(historyRecord);
						successEmailSendRecordList.add(record.getEmailId());
					}catch (Exception e){
						logger.error("保存三月前发送邮件记录到历史表出错", e.getMessage());
					}
				}
				List<SMSSendRecord> smsRecordList=smsSendRecordDao.getThrMonSmsSendRecordList(conf.getConfId());
                for(SMSSendRecord record:smsRecordList){
                	SMSSendHistoryRecord smsHistory=new SMSSendHistoryRecord();
                	BeanUtils.copyProperties(record, smsHistory);
                	smsHistory.setConfId(record.getConfInfo().getConfId());
                	try{
                		smsSendHisDao.save(smsHistory);
                		successSmsSendRecordList.add(record.getSmsId());
					}catch (Exception e){
						logger.error("保存三月前发送短信记录到历史表出错", e.getMessage());
					}
				}
			}
		}
		for(CustomerInfo cust : successCustList){
			try{
				custDao.deleteById(cust.getCustId());
			}catch (Exception e){
				logger.error("删除三月前会议参会人信息出错", e.getMessage());
			}
		}
		for(Long id:successRadioIdList){
			try{
				radioDao.deleteById(id);
			}catch (Exception e){
				logger.error("删除三月前会议参会人信息出错", e.getMessage());
			}
		}
		for(Long id:successReportIdList){
			try{
				reportDao.deleteById(id);
			}catch (Exception e){
				logger.error("删除三月前会议参会人信息出错", e.getMessage());
			}
		}
		for(Long id:successHanlerRecordList){
			try{
				supportHandleDao.deleteById(id);
			}catch (Exception e){
				logger.error("删除三月前会议参会人信息出错", e.getMessage());
			}
		}
		for(Long id:successEmailSendRecordList){
			try{
				mailSendRecordDao.deleteById(id);
			}catch (Exception e){
				logger.error("删除三月前邮件发送记录信息出错", e.getMessage());
			}
		}
		for(Long id:successSmsSendRecordList){
			try{
				smsSendRecordDao.deleteById(id);
			}catch (Exception e){
				logger.error("删除三月前短信发送记录信息出错", e.getMessage());
			}
		}
		for(ConferenceInfo conf : successConfList){
			try{
				confDao.deleteById(conf.getConfId());
			}catch (Exception e){
				e.printStackTrace();
				logger.error("删除三月会议信息出错", e.getMessage());
			}
		}
		logger.info("结束执行回收会议到历史库的任务--------------");
	}
}

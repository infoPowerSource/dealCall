package com.deal.service.report;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.deal.dao.report.ConferenceRadioDao;
import com.deal.entity.create.ConferenceInfo;
import com.deal.entity.create.ConferenceRadioInfo;
import com.deal.entity.create.ConferenceWaitMake;
import com.deal.entity.report.BoRdRecordFile;

public interface IConferenceRecordService{
	public boolean addRecordFileForNoActiveConf(ConferenceWaitMake conferenceWaitMake, ConferenceInfo conf);

	public List<BoRdRecordFile> selectRecordFileFromMis(Timestamp startDate, Timestamp endDate, String billingcode);

	/**
	 * 根据会议Id 查询本地录音文件
	 * 
	 * @param confId
	 * @return
	 */
	public List<ConferenceRadioInfo> getRadioInfoByConfId(String confId);
}

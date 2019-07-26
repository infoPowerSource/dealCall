package com.deal.service.report.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deal.dao.report.ConferenceTaskManagerDao;
import com.deal.entity.create.ConferenceWaitMake;
import com.deal.entity.create.ConferenceWaitMakeId;

/**
 * 会后报告生成类
 * 
 * @author zhipeng.xu
 *
 */
@Service
public class ConferenceTaskManagerServiceImpl{
	@Autowired
	private ConferenceTaskManagerDao confTaskManagerDao;

	/**
	 * 查询会后报告任务列表
	 * 
	 * @return
	 */
	public List<ConferenceWaitMake> getConferenceWaitMake(){
		return confTaskManagerDao.getConferenceWaitMake();
	}

	/**
	 * 保存会后报告任务列表
	 * 
	 * @param gnetRsConferenceWaitMake
	 */
	public void saveConferenceWaitMake(ConferenceWaitMake conferenceWaitMake){
		confTaskManagerDao.save(conferenceWaitMake);
	}

	/**
	 * 跟新会后报告
	 * 
	 * @param conferenceWaitMake
	 */
	public void update(ConferenceWaitMake conferenceWaitMake){
		confTaskManagerDao.update(conferenceWaitMake);
	}

}

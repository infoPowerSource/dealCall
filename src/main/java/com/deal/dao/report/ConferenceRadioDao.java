package com.deal.dao.report;

import java.util.List;

import org.springframework.stereotype.Component;

import com.deal.dao.common.BaseDao;
import com.deal.entity.create.ConferenceRadioInfo;
import com.deal.entity.create.ConferenceReportInfo;
import com.deal.entity.create.CustomerInfo;

@Component
public class ConferenceRadioDao extends BaseDao{

	public List<ConferenceRadioInfo> getRadioByConfId(String confId){
		String sqlStr = "from ConferenceRadioInfo t where t.confInfo.confId='" + confId + "'";
		List<ConferenceRadioInfo> list = this.getSession().createQuery(sqlStr).list();
		return list;
	}

	/**
	 * 查询本地录音文件
	 * 
	 * @param confRadio
	 * @return
	 */
	public List<ConferenceRadioInfo> getLocalRecord(ConferenceRadioInfo confRadio){
		String sqlStr = "from ConferenceRadioInfo t where t.fileName = '" + confRadio.getFileName() + "'";
		List<ConferenceRadioInfo> list = this.getSession().createQuery(sqlStr).list();
		return list;
	}

	/**
	 * 保存本地录音文件
	 * 
	 * @param dealRecordFile
	 */
	public void save(ConferenceRadioInfo dealRecordFile){
		this.getSession().save(dealRecordFile);
	}

	public List<ConferenceRadioInfo> getThrMonRadioInfoList(long confId){
		String sqlStr = "from ConferenceRadioInfo t where t.confInfo.confId =:confId";
		List<ConferenceRadioInfo> list=this.getSession().createQuery(sqlStr).setParameter("confId", confId).list();
		return list;
	}

	public void deleteById(Long id){
		this.getSession().createQuery("delete from ConferenceRadioInfo where radioId=:radioId").setParameter("radioId", id).executeUpdate();
	}
}

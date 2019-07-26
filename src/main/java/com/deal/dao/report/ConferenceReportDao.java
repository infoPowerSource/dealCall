package com.deal.dao.report;

import java.util.List;

import org.springframework.stereotype.Component;

import com.deal.dao.common.BaseDao;
import com.deal.entity.create.ConferenceInfo;
import com.deal.entity.create.ConferenceRadioInfo;
import com.deal.entity.create.ConferenceReportInfo;

@Component
public class ConferenceReportDao extends BaseDao{
	public List<ConferenceReportInfo> getReportByConfId(String confId){
		String sqlStr = "from ConferenceReportInfo t where t.confInfo.confId='" + confId + "'";
		List<ConferenceReportInfo> list = this.getSession().createQuery(sqlStr).list();
		return list;
	}

	public void save(ConferenceReportInfo conferenceReport){
		this.getSession().save(conferenceReport);
	}

	public List<ConferenceReportInfo> getThrMonReportInfoList(long confId){
		String sqlStr = "from ConferenceReportInfo t where t.confInfo.confId =:confId";
		List<ConferenceReportInfo> list=this.getSession().createQuery(sqlStr).setParameter("confId", confId).list();
		return list;
	}

	public void deleteById(Long id){
		this.getSession().createQuery("delete from ConferenceReportInfo where reportId=:reportId").setParameter("reportId", id).executeUpdate();
	}
}

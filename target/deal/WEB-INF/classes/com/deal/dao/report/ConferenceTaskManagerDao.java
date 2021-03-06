package com.deal.dao.report;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import com.deal.dao.common.BaseDao;
import com.deal.entity.create.ConferenceRadioInfo;
import com.deal.entity.create.ConferenceReportInfo;
import com.deal.entity.create.ConferenceWaitMake;
import com.deal.entity.create.ConferenceWaitMakeId;
import com.deal.entity.party.PartyInfo;

@Component
public class ConferenceTaskManagerDao extends BaseDao{
	/**
	 * 查询会后报告 任务列表
	 * 
	 * @return
	 */
	public List<ConferenceWaitMake> getConferenceWaitMake(){
		String sqlStr = "from ConferenceWaitMake where ifMake =0";
		List<ConferenceWaitMake> list = this.getSession().createQuery(sqlStr).list();
		return list;
	}

	/**
	 * 保存会后报告调度任务
	 * 
	 * @param gnetRsConferenceWaitMake
	 */
	public void save(ConferenceWaitMake conferenceWaitMake){
		this.getSession().save(conferenceWaitMake);
	}

	public ConferenceRadioInfo selectRadioByConfId(String confId){
		return (ConferenceRadioInfo) this.getSession().createQuery("from ConferenceRadioInfo t where t.confInfo.confId=?").setParameter(0, Long.valueOf(confId)).uniqueResult();
	}

	public ConferenceReportInfo selectReportByConfId(String confId){
		return (ConferenceReportInfo) this.getSession().createQuery("from ConferenceReportInfo t where t.confInfo.confId=?").setParameter(0, Long.valueOf(confId)).uniqueResult();
	}

	public ConferenceWaitMake getWaitMake(ConferenceWaitMakeId taskId){
		return (ConferenceWaitMake) this.getSession()
				.createQuery("from ConferenceWaitMake where id.conferenceId='" + taskId.getConferenceId() + "' and id.timerType=" + taskId.getTimerType() + "").uniqueResult();

	}

	public void update(ConferenceWaitMake confMake){
		this.getSession().update(confMake);
	}

	public void saveForMonit(ConferenceWaitMake makeAdd){
		Session session=this.getOpenSession();
		session.saveOrUpdate(makeAdd);
		this.closeSession(session);
	}

	public void updateForMonit(ConferenceWaitMake confMake){
		Session session=this.getOpenSession();
		String hql = "update ConferenceWaitMake set ifMake=:ifMake,taskResult=:taskResult where id.conferenceId=:conferenceId and id.timerType=:timerType";
		Query query = session.createQuery(hql);
		query.setParameter("ifMake", confMake.getIfMake());
		query.setParameter("taskResult", confMake.getTaskResult());
		query.setParameter("conferenceId", confMake.getId().getConferenceId());
		query.setParameter("timerType", confMake.getId().getTimerType());
		query.executeUpdate();
		session.close();
	}

	public ConferenceWaitMake getWaitMakeForMonit(ConferenceWaitMakeId taskId){
		ConferenceWaitMake confMake=new ConferenceWaitMake();
		Session session=this.getOpenSession();
		confMake=(ConferenceWaitMake)session.createQuery("from ConferenceWaitMake where id.conferenceId='" + taskId.getConferenceId() + "' and id.timerType=" + taskId.getTimerType() + "").uniqueResult();
		session.close();
		return confMake;
	}
}

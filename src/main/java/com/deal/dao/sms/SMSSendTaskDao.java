package com.deal.dao.sms;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.stereotype.Component;

import com.deal.dao.common.BaseDao;
import com.deal.entity.sms.SMSSendTask;

@Component
public class SMSSendTaskDao extends BaseDao {

	public void save(SMSSendTask task) {
		this.getSession().save(task);
	}

	public List<SMSSendTask> getTaskListByDate() {
		Timestamp nowData = new Timestamp(System.currentTimeMillis());
		String sql = "from SMSSendTask where isHandle=0 and (sendTime=null or sendTime <'" + nowData + "')";
		return (List<SMSSendTask>) this.getSession().createQuery(sql).list();
	}

	public void update(SMSSendTask sendTask) {
		this.getSession().update(sendTask);
	}

	public List<SMSSendTask> getProcessedTaskList() {
		String sql = "from SMSSendTask where isHandle=1";
		return (List<SMSSendTask>) this.getSession().createQuery(sql).list();
	}

	public void deleteById(long smsId) {
		this.getSession().createQuery("delete from SMSSendTask where smsId=?").setParameter(0, smsId).executeUpdate();
	}

}

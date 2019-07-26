package com.deal.dao.mss;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.stereotype.Component;

import com.deal.dao.common.BaseDao;
import com.deal.entity.mss.MSSSendTask;

@Component
public class MailSendTaskDao extends BaseDao {

	public void save(MSSSendTask sendTask) {
		this.getSession().save(sendTask);
	}

	@SuppressWarnings("unchecked")
	public List<MSSSendTask> getProcessedTaskList() {
		String sql = "from MSSSendTask where isHandle=1";
		return (List<MSSSendTask>) this.getSession().createQuery(sql).list();
	}

	public void deleteById(long emailId) {
		this.getSession().createQuery("delete from MSSSendTask where emailId=:emailId").setParameter("emailId", emailId)
				.executeUpdate();
	}

	public List<MSSSendTask> getTaskListByDate() {
		Timestamp nowData = new Timestamp(System.currentTimeMillis());
		String sql = "from MSSSendTask where isHandle=0 and (sendTime=null or sendTime <'" + nowData + "')";
		return (List<MSSSendTask>) this.getSession().createQuery(sql).list();
	}

	public void update(MSSSendTask task) {
		this.getSession().update(task);
	}
}

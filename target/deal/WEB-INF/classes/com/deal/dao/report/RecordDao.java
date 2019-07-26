package com.deal.dao.report;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.deal.dao.common.BaseDao;
import com.deal.entity.create.ConferenceRadioInfo;
import com.deal.entity.create.ConferenceReportInfo;
import com.deal.entity.report.BoRdRecordFile;

@Component
public class RecordDao extends BaseDao
{
	public List<BoRdRecordFile> selectRecord(BoRdRecordFile recordFile)
	{
		String sqlStr = "from BoRdRecordFile t where t.bgntime>='"
				+ recordFile.getBgntime() + "' and t.endtime<'"
				+ recordFile.getEndtime() + "' and t.billingcode ='"
				+ recordFile.getBillingcode() + "' order by t.bgntime ";
		List<BoRdRecordFile> list = this.sessionFactory.getCurrentSession()
				.createQuery(sqlStr).list();
		return list;
	}
}

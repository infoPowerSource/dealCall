package com.deal.dao.mss;

import com.deal.dao.common.BaseDao;
import com.deal.entity.mss.MSSSendRecord;
import com.deal.entity.support.SupportHandlerRecord;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * @version V0.1
 * @Description: 邮件发送记录
 * @author: yanqing.zhang
 * @date: 2017/12/4 15:54
 */
@Component
public class MailSendRecordDao extends BaseDao{
    public void save(MSSSendRecord recode) {
        this.getSession().saveOrUpdate(recode);
    }

	public List<MSSSendRecord> getThrMonEmailSendRecordList(long confId){
		String sqlStr = "from MSSSendRecord t where t.confInfo.confId =:confId";
		List<MSSSendRecord> list=this.getSession().createQuery(sqlStr).setParameter("confId", confId).list();
		return list;
	}

	public void deleteById(Long id){
		this.getSession().createQuery("delete from MSSSendRecord where emailId=:emailId").setParameter("emailId", id).executeUpdate();
	}
}

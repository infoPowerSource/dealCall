package com.deal.dao.task;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import com.deal.dao.common.BaseDao;
import com.deal.entity.colony.ColonyHandle;

/**
 * 集群处理器
 * 
 * @author zhipeng.xu
 *
 */
@Component
public class ColonyDAO extends BaseDao{

	public ColonyHandle findById(String billingCode){
		Session session =this.getOpenSession();
		ColonyHandle colonyHandle=new ColonyHandle();
		colonyHandle=(ColonyHandle)session.createQuery("from ColonyHandle where billingCode='" + billingCode + "'").uniqueResult();
		session.close();
		return colonyHandle;
	}

	public void save(ColonyHandle colonyHandle){
		Session session =this.getOpenSession();
		session.saveOrUpdate(colonyHandle);
		this.closeSession(session);
	}

	public void update(ColonyHandle colonyHandle){
		Session session =this.getOpenSession();
		String sql="update ColonyHandle set colonyTime=:colonyTime where colonyIp=:colonyIp";
		Query query = session.createQuery(sql);
		query.setParameter("colonyTime", colonyHandle.getColonyTime());
		query.setParameter("colonyIp", colonyHandle.getColonyIp());
		query.executeUpdate();
		session.close();
	}
}

package com.deal.dao.login.syncboss;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.deal.entity.create.ConferenceAccessNumber;

@Component
public class AccessNumDao{

	@Autowired
	private SessionFactory sessionFactory;

	private Session getSession(){
		return sessionFactory.getCurrentSession();
	}

	public void saveAccessNum(ConferenceAccessNumber accessNumInfo){
		this.getSession().save(accessNumInfo);
	}

	public void delAccessNum(String bridgeName){
		this.getSession().createQuery("delete from ConferenceAccessNumber where bridgeName=:bridgeName").setParameter("bridgeName", bridgeName).executeUpdate();
	}
	
	// 根据bridgeName获取接入号列表
	@SuppressWarnings("unchecked")
	public List<ConferenceAccessNumber> getAccessByBridge(String bridgeName){
		String hql = "from ConferenceAccessNumber where bridgeName=:bridgeName and isvalid=0";
		Query query = this.getSession().createQuery(hql);
		query.setParameter("bridgeName", bridgeName);
		List<ConferenceAccessNumber> accessNumList = query.list();
		return accessNumList ;
	}
}

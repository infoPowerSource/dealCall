package com.deal.dao.mss;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.deal.entity.gnet.GnetSsMailSendingTask;

public class MailSendDao {
	
	@Autowired
	protected SessionFactory sessionFactory;
	
    public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Session getSession(){
	        return sessionFactory.getCurrentSession();
	    }
	
    public void save(GnetSsMailSendingTask task){
    	this.sessionFactory.getCurrentSession().save(task);
    }
}

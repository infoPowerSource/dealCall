package com.deal.dao.task;

import org.springframework.stereotype.Component;

import com.deal.dao.common.BaseDao;
import com.deal.entity.task.AttemperTask;
/**
 * 调度处理器
 * @author zhipeng.xu
 *
 */
@Component
public class AttemperTaskDAO extends BaseDao{

	public AttemperTask findById(Integer attemperId){
		return (AttemperTask) this.getSession().createQuery("from AttemperTask where attemperId=?").setParameter(0, attemperId).uniqueResult();

	}

	public void save(AttemperTask attemperTask){
		this.getSession().save(attemperTask);
	}

	public void attachDirty(AttemperTask attemperTask){
		this.getSession().update(attemperTask);
	}

}

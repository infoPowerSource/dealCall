package com.deal.dao.support;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import com.deal.dao.common.BaseDao;
import com.deal.entity.create.ConferenceSupportTaskInfo;
import com.google.common.collect.Lists;

@Component
public class SupportConfInfoDao extends BaseDao{

	public List<ConferenceSupportTaskInfo> getConferenceSupportList(){
		List<ConferenceSupportTaskInfo> conferenceSupportTaskList = Lists.newArrayList();
		String hql = "from ConferenceSupportTaskInfo where status=0";
		// 限制每次查询10条
		conferenceSupportTaskList = this.getSession().createQuery(hql).setMaxResults(10).list();
		return conferenceSupportTaskList;
	}

	// public List<ConferenceSupportTaskInfo> getSupportNotOpenListWithBridge(){
	// String hql = "from ConferenceSupportTaskInfo where status=0 and
	// TIMESTAMPDIFF(SECOND,createTime,now())>30 ";
	// List<ConferenceSupportTaskInfo> conferenceSupportTaskList =
	// this.getSession().createQuery(hql).list();
	// return conferenceSupportTaskList;
	// }

	// 查询任务池中，是否有超出30秒没有领取的任务，不区分平台
	public List<ConferenceSupportTaskInfo> getConferenceSupportNotOpenList(){
		String hql = "from ConferenceSupportTaskInfo where status=0 and TIMESTAMPDIFF(SECOND,createTime,now())>30 ";
		List<ConferenceSupportTaskInfo> conferenceSupportTaskList = this.getSession().createQuery(hql).list();
		return conferenceSupportTaskList;
	}

	// 任务没结束，但是消耗时间大于90秒，将会议的任务状态改为0，重新推入任务池
	public Integer reNewSupportStatus(){
		String hql = "update ConferenceSupportTaskInfo set status=0 where status=1 and TIMESTAMPDIFF(SECOND,updateTime,now())>90";
		Query query = this.getSession().createQuery(hql);
		return query.executeUpdate();
	}

	// 根据confid查询会议列表，查看状态是否没有领取，如果没有领取返回会议信息，可以领取
	// 领取任务后，将会议的任务状态改为1；
	public Integer updateSupportStatusOpen(long supportID){
		String hql = "update ConferenceSupportTaskInfo set status=1,updateTime=now() where status=0 and supportID=?";
		Query query = this.getSession().createQuery(hql);
		query.setParameter(0, supportID);
		return query.executeUpdate();
	}

	// 领取任务后，时间超过一分钟后，仍然要处理，更新时间；
	public Integer updateSupportStatusHold(long supportID){
		String hql = "update ConferenceSupportTaskInfo set status=1,updateTime=now() where supportID=?";
		Query query = this.getSession().createQuery(hql);
		query.setParameter(0, supportID);
		return query.executeUpdate();
	}

	// 任务没结束，将会议的任务状态改为0，重新推入任务池；
	public Integer updateSupportStatusNew(long supportID){
		String hql = "update ConferenceSupportTaskInfo set status=0 where supportID=?";
		Query query = this.getSession().createQuery(hql);
		query.setParameter(0, supportID);
		return query.executeUpdate();
	}

	// 不用事务，任务没结束，将会议的任务状态改为0，重新推入任务池；
	public Integer updateSupportStatusToNew(long supportID){
		Session session = this.getOpenSession();
		String hql = "update ConferenceSupportTaskInfo set status=0 where supportID=?";
		Query query = session.createQuery(hql);
		query.setParameter(0, supportID);
		int result = query.executeUpdate();
		session.close();
		return result;
	}

	// 任务结束，将任务的任务状态改为2；
	public Integer updateSupportStatusClose(Long supportID){
		String hql = "update ConferenceSupportTaskInfo set status=2,updateTime=now() where supportID=?";
		Query query = this.getSession().createQuery(hql);
		query.setParameter(0, supportID);
		return query.executeUpdate();
	}

	// 后台外呼结束后，将任务的任务状态改为2；
	public Integer updateSupportStatusForCallOut(Long supportID){
		Session session = this.getOpenSession();
		String hql = "update ConferenceSupportTaskInfo set status=2,updateTime=now() where supportID=?";
		Query query = session.createQuery(hql);
		query.setParameter(0, supportID);
		int result = query.executeUpdate();
		session.close();
		return result;
	}

	// 任务结束，但是还得等待5分钟或10分钟外呼专家成功后，才能将任务的任务状态改为2，目前置为3；
	public Integer updateSupportStatusToWaitClose(Long supportID){
		String hql = "update ConferenceSupportTaskInfo set status=3,updateTime=now() where supportID=?";
		Query query = this.getSession().createQuery(hql);
		query.setParameter(0, supportID);
		return query.executeUpdate();
	}
	
	/**
	 * 会议结束 将此会议的 没有被领取的任务 置为-1
	 * 
	 * @param confId
	 * @return
	 */
	public int updateSupportStatusDelete(long confId){
		Session session =this.getOpenSession();
		String hql = "update ConferenceSupportTaskInfo set status='-1' where status=0 and confID=?";
		Query query = session.createQuery(hql);
		query.setParameter(0, confId);
		int result = query.executeUpdate();
		session.close();
		return result;
	}
	
	/**
	 * 根据会议id 和离线型type 判断任务池是否有离线型任务
	 * 
	 * @param confId
	 * @return
	 */
	public List<ConferenceSupportTaskInfo> getSupportOut(Long confId, Integer type){
		Session session = this.getOpenSession();
		String hql = "from ConferenceSupportTaskInfo where status in (0,1,3) and confId='" + confId + "'";
		Query query = session.createQuery(hql);
		List<ConferenceSupportTaskInfo> conferenceSupportTaskList = Lists.newArrayList();
		conferenceSupportTaskList = query.list();
		session.close();
		return conferenceSupportTaskList;
	}

}

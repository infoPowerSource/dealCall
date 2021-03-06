package com.deal.dao.support;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import com.deal.dao.common.BaseDao;
import com.deal.entity.conference.ConfQueryDTO;
import com.deal.entity.create.ConferenceInfo;
import com.deal.entity.create.ConferenceSupportForm;
import com.deal.entity.create.ConferenceSupportTaskInfo;
import com.deal.entity.create.CustomerInfo;
import com.deal.entity.support.SupportHandlerRecord;
import com.deal.entity.support.SupportInfo;
import com.google.common.collect.Lists;

/**
 * Created by kun.bai on 2017/5/19.
 */
@Component
public class SupportDao extends BaseDao{

	// 根据email获取客服人员信息列表
	@SuppressWarnings("unchecked")
	public List<SupportInfo> getSupportListByEmail(String email){
		String hql = "from SupportInfo where supportEmail=:email";
		Query query = this.getSession().createQuery(hql);
		query.setParameter("email", email);
		List<SupportInfo> supportList = query.list();
		return supportList ;
	}
	
	// 根据email获取客服人员信息
	public SupportInfo getSupportByEmail(String email,String bridgeName){
		return (SupportInfo) this.getSession().createQuery("from SupportInfo where supportEmail='"+email+"' and bridgeName='"+bridgeName+"'").uniqueResult();
	}

//	// 提取所有任务列表，与会议表关联，查询相关信息
//	@SuppressWarnings("unchecked")
//	public List<ConferenceSupportTaskInfo> getConferenceSupportList(){
//		List<ConferenceSupportTaskInfo> conferenceSupportTaskList=Lists.newArrayList();
//		List<ConferenceSupportForm> taskList = new ArrayList<ConferenceSupportForm>();
//		String hql = "from ConferenceSupportTaskInfo where status=0";
//		Query query = this.getSession().createQuery(hql);
//		// 限制每次查询10条
//		query.setMaxResults(10);
//		conferenceSupportTaskList = query.list();
//		return conferenceSupportTaskList;
//	}

	// 查询任务池中，是否有超出30秒没有领取的任务,根据平台查询
//	@SuppressWarnings("unchecked")
//	public List<ConferenceSupportTaskInfo> getSupportNotOpenListWithBridge(String bridgeName){
//		String hql = "from ConferenceSupportTaskInfo where status=0 and TIMESTAMPDIFF(SECOND,createTime,now())>30 ";
//		Query query = this.getSession().createQuery(hql);
//		List<ConferenceSupportTaskInfo> conferenceSupportTaskList = query.list();
//		return conferenceSupportTaskList;
//	}

	// 查询任务池中，是否有超出30秒没有领取的任务，不区分平台
//	@SuppressWarnings("unchecked")
//	public List<ConferenceSupportTaskInfo> getConferenceSupportNotOpenList(){
//		String hql = "from ConferenceSupportTaskInfo where status=0 and TIMESTAMPDIFF(SECOND,createTime,now())>30 ";
//		Query query = this.getSession().createQuery(hql);
//		List<ConferenceSupportTaskInfo> conferenceSupportTaskList = query.list();
//		
//		return conferenceSupportTaskList;
//	}

	// 任务没结束，但是消耗时间大于90秒，将会议的任务状态改为0，重新推入任务池；
//	public Integer reNewSupportStatus(){
//		String hql = "update ConferenceSupportTaskInfo set status=0 where status=1 and TIMESTAMPDIFF(SECOND,updateTime,now())>90";
//		Query query = this.getSession().createQuery(hql);
//		return query.executeUpdate();
//	}

//	// 提取所有会议中参与者信息
//	@SuppressWarnings("unchecked")
//	public List<CustomerInfo> getConferenceCustomerList(Long confId){
//		String sql = "from CustomerInfo where confInfo.confId=:confId";
//		return (List<CustomerInfo>) this.getSession().createQuery(sql).setParameter("confId", confId).list();
//	}

//	// 根据confId 提取会议信息
//	public ConferenceInfo getConferenceInfo(Long confId){
//		String sql = "from ConferenceInfo where confId=?";
//		return (ConferenceInfo) this.getSession().createQuery(sql).setParameter(0, confId).uniqueResult();
//	}

//	// 根据custId ,查询参与人信息
//	public CustomerInfo getCustomerInfo(Long custId){
//		String sql = "from CustomerInfo where custId=?";
//		return (CustomerInfo) this.getSession().createQuery(sql).setParameter(0, custId).uniqueResult();
//	}

//	// 根据confid查询会议列表，查看状态是否没有领取，如果没有领取返回会议信息，可以领取
//	// 领取任务后，将会议的任务状态改为1；
//	public Integer updateSupportStatusOpen(long supportID){
//		String hql = "update ConferenceSupportTaskInfo set status=1,updateTime=now() where status=0 and supportID=?";
//		Query query = this.getSession().createQuery(hql);
//		query.setParameter(0, supportID);
//		return query.executeUpdate();
//	}
	
//	// 领取任务后，时间超过一分钟后，仍然要处理，更新时间；
//	public Integer updateSupportStatusHold(long supportID){
//		String hql = "update ConferenceSupportTaskInfo set status=1,updateTime=now() where supportID=?";
//		Query query = this.getSession().createQuery(hql);
//		query.setParameter(0, supportID);
//		return query.executeUpdate();
//	}

//	// 任务没结束，将会议的任务状态改为0，重新推入任务池；
//	public Integer updateSupportStatusNew(long supportID){
//		String hql = "update ConferenceSupportTaskInfo set status=0 where supportID=?";
//		Query query = this.getSession().createQuery(hql);
//		query.setParameter(0, supportID);
//		return query.executeUpdate();
//	}
	

//	// 不用事务，任务没结束，将会议的任务状态改为0，重新推入任务池；
//	public Integer updateSupportStatusToNew(long supportID){
//		Session session =this.getOpenSession();
//		String hql = "update ConferenceSupportTaskInfo set status=0 where supportID=?";
//		Query query = session.createQuery(hql);
//		query.setParameter(0, supportID);
//		int result = query.executeUpdate();
//		session.close();
//		return result;
//	}

//	// 任务结束，将任务的任务状态改为2；
//	public Integer updateSupportStatusClose(Long supportID){
//		String hql = "update ConferenceSupportTaskInfo set status=2,updateTime=now() where supportID=?";
//		Query query = this.getSession().createQuery(hql);
//		query.setParameter(0, supportID);
//		return query.executeUpdate();
//	}

//	// 后台外呼结束后，将任务的任务状态改为2；
//	public Integer updateSupportStatusForCallOut(Long supportID){
//		Session session =this.getOpenSession();
//		String hql = "update ConferenceSupportTaskInfo set status=2,updateTime=now() where supportID=?";
//		Query query = session.createQuery(hql);
//		query.setParameter(0, supportID);
//		int result = query.executeUpdate();
//		session.close();
//		return result;
//	}

//	// 任务结束，但是还得等待5分钟或10分钟外呼专家成功后，才能将任务的任务状态改为2，目前置为3；
//	public Integer updateSupportStatusToWaitClose(Long supportID){
//		String hql = "update ConferenceSupportTaskInfo set status=3,updateTime=now() where supportID=?";
//		Query query = this.getSession().createQuery(hql);
//		query.setParameter(0, supportID);
//		return query.executeUpdate();
//	}

	// 客服人员所有操作，登记到记录表中
//	public void saveHandlerRecord(SupportHandlerRecord recode){
//		this.getSession().save(recode);
//	}

	public void save(ConferenceSupportTaskInfo supportInfo){
		Session session = this.getOpenSession();
		session.save(supportInfo);
		this.closeSession(session);
	}

//	/**
//	 * 会议结束 将此会议的 没有被领取的任务 置为-1
//	 * 
//	 * @param confId
//	 * @return
//	 */
//	public int updateSupportStatusDelete(long confId){
//		Session session =this.getOpenSession();
//		String hql = "update ConferenceSupportTaskInfo set status='-1' where status=0 and confID=?";
//		Query query = session.createQuery(hql);
//		query.setParameter(0, confId);
//		int result = query.executeUpdate();
//		session.close();
//		return result;
//	}

//	/**
//	 * 根据会议id 和离线型type 判断任务池是否有离线型任务
//	 * 
//	 * @param confId
//	 * @return
//	 */
//	public List<ConferenceSupportTaskInfo> getSupportOut(Long confId, Integer type){
//		Session session = this.getOpenSession();
//		String hql = "from ConferenceSupportTaskInfo where status in (0,1,3) and confId='" + confId + "'";
//		Query query = session.createQuery(hql);
//		List<ConferenceSupportTaskInfo> conferenceSupportTaskList = Lists.newArrayList();
//		conferenceSupportTaskList = query.list();
//		session.close();
//		return conferenceSupportTaskList;
//	}

	/**
	 * 根据bc 查询是否是客服会议
	 * 
	 * @param billingCode
	 * @return
	 */
	public SupportInfo getSupportConf(String billingCode){
		Session session = this.getOpenSession();
		String hql = "from SupportInfo where meetmeBillingcode ='" + billingCode + "'";
		Query query = session.createQuery(hql);
		SupportInfo supportInfo = new SupportInfo();
		supportInfo = (SupportInfo) query.uniqueResult();
		session.close();
		return supportInfo;
	}

	// 修改客服人员的电话号码；
	public Integer updateSupportTelNum(String email,String bridgeName, String telNum){
		String hql = "update SupportInfo set supportTel=:supportTel where supportEmail=:supportEmail and bridgeName =:bridgeName";
		Query query = this.getSession().createQuery(hql);
		query.setParameter("supportTel", telNum);
		query.setParameter("supportEmail", email);
		query.setParameter("bridgeName", bridgeName);
		return query.executeUpdate();
	}

    //客服查询会议总量，根据时间统计当前时间至明天24点的会议量
//    public List<ConferenceInfo> getConfNumByTime(Timestamp beginTime, Timestamp endTime){
//    	String sqlStr = "from ConferenceInfo t where t.beginTime < :endTime and t.beginTime >=:beginTime order by t.beginTime";
//        List<ConferenceInfo> list = this.getSession().createQuery(sqlStr)
//        		.setParameter("beginTime", beginTime)
//                .setParameter("endTime",endTime)
//                .list();
//        return list;
//    }
}

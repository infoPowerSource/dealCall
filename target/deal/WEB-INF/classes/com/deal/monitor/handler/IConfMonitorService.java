package com.deal.monitor.handler;

import java.util.List;

import com.deal.entity.create.CustomerInfo;
import com.deal.entity.support.SupportInfo;
import com.gnet.acmw.client.DialoutParty;
import com.gnet.acmw.client.IConference;
import com.gnet.acmw.client.IParty;

/**
 * 监控接口类
 * 
 * @date 2017.05.22
 * @author zhipeng.xu
 * 
 */
public interface IConfMonitorService{
	/**
	 * 负责多线程获取从消息队列中的BC，将会议信息存放到缓存中
	 * 
	 * @author zhipeng.xu
	 * @param billingCode
	 */
	public void confMonitorPushCache(String billingCode);

	/**
	 * 会议外呼 对外的统一接口 2、有咨询客户入会，外呼专家 3、所有咨询客户入会，外呼专家
	 * 
	 * @param billingCode
	 * @return 返回匹配的真实姓名
	 */
	public String confMonitorHandle(String billingCode);

	/**
	 * 获取专家姓名
	 * 
	 * @param confId ， phone
	 * @return 返回匹配的真实姓名
	 */
	public String getGuestName(Long confId,String acmPhone);
	/**
	 * 会议时间达到预约开始时间，系统自动激活会议
	 * 
	 * @param billingCode
	 */
	public Integer confCallOutAuto(String billingCode);

	/**
	 * 会议时间达到预约开始时间，系统自动外呼专家
	 * 
	 * @param billingCode
	 * @return
	 */
	public boolean callOutGuestAuto(String billingCode);

	/**
	 * 外呼
	 * 
	 * @author zhipeng.xu
	 * @param dp
	 *            acm 外呼单个参会人
	 * @param conference
	 *            会议handle
	 * @return
	 */
	public int callOut(String bridgeName, DialoutParty dp, IConference conference);

	/**
	 * 外呼多个参会人
	 * 
	 * @param listDp
	 * @param conference
	 * @return
	 */
	public int callOutGroup(List<DialoutParty> listDp, IConference conference);

	/**
	 * 根据bc 和 partyid 查询参会人信息
	 * 
	 * @param billingCode
	 * @param partyId
	 * @return
	 */
	public IParty findPartyById(String billingCode, String partyId);

	/**
	 * 客服op主动外呼自己
	 * 
	 * @param billingCode
	 * @return
	 */
	public int callOutForOp(String billingCode);

	/**
	 * 客服op挂断自己
	 */
	public int hangUpForOp(String billingCode);

	/**
	 * 外呼入口 平台暂时写死
	 * 
	 * @param billingCode
	 *            会议bc
	 * @param partyList
	 *            参会人 集合
	 * @return
	 */
	public boolean control_callout(String summit, String billingCode, List<CustomerInfo> partyList);

	/**
	 * 转移咨询客户
	 * 
	 * @param targetBillingCode
	 * @param billingCode
	 * @param party
	 * @return
	 */
	public boolean transferPartyToAnotherConf(String targetBillingCode, String billingCode, CustomerInfo party);

	public boolean isOnlineOp(String billingCode, String phone);

	/**
	 * 判断通讯录人员是否在线 0在线 1不在线 2不存在此会议
	 * 
	 * @param billingCode
	 * @param phone
	 * @return
	 */
	public Integer isOnline(String billingCode, String phone);

	public int callOutOpAuto(SupportInfo supportConf);

//	public int enterMoniter(String billingCode);
	public boolean isSubscribeAndActiveConf(String billingCode);
	public void callPersenal(String billingCode);
}

package com.deal.util;

import java.util.Map;

import com.google.common.collect.Maps;

public class Consts{
	public static final int CONFERENCE_SMS_TYPE_VERIFY = 4;
	public static final int CONFERENCE_SMS_TYPE_INFORM_CUSTOMER = 0;
	public static final int CONFERENCE_SMS_TYPE_INFORM_EXPERT = 2;
	public static final int CONFERENCE_SMS_TYPE_CANCEL_CUSTOMER = 1;
	public static final int CONFERENCE_SMS_TYPE_CANCEL_EXPERT = 3;
	public static final int SMS_HANDLE_RESULT_PROCESSED = 1;
	public static final int SMS_HANDLE_RESULT_NO_PROCESSING = 0;
	public static final int SMS_SEND_SUCCESS_FLAG = 1;
	public static final int SMS_SEND_FALSE_FLAG = 0;
	public static final int IS_NOT_HANDLE_FLAG = 0;
	public static final int IS_HANDLE_FLAG = 1;
	public static final int CONFERENCE_PARTY_TYPE_FOR_CUSTOMER = 1;
	public static final int CONFERENCE_PARTY_TYPE_FOR_EXPERT = 0;
	// mail type
	public static final int EMAIL_TYPE_FOR_INFORM_CUSTOMER = 0;
	public static final int EMAIL_TYPE_FOR_INFORM_CANCEL_CUSTOMER = 1;
	public static final int EMAIL_TYPE_FOR_INFORM_EXPERT = 2;
	public static final int EMAIL_TYPE_FOR_INFORM_CANCEL_EXPERT = 3;
	public static final int EMAIL_TYPE_FOR_SUPPORT_ACCOUNT_ACTIVE = 4;
	public static final int EMAIL_TYPE_FOR_INFORM_CHANGE_CUSTOMER=7;
	public static final int EMAIL_TYPE_FOR_INFORM_CHANGE_EXPERT=8;
	public static final int EMAIL_TYPE_FOR_INFORM_RADIO=9;
	public static final int EMAIL_TYPE_FOR_INFORM_BC_WARNING=10;
	public static final int EMAIL_TYPE_FOR_INFORM_PC_WARNING=11;
	public static final int EMAIL_MSS_RESULT_CODE = 9;
	public static final String EMAIL_MSS_SEND_NAME = "全时电话会议";
	public static final String EMAIL_MSS_RESET_PASSWORD_TITLE = "主动服务-验证邮件";
	public static final String EMAIL_FOR_WARNING="运维同事";
	public static final int EMAIL_MSS_NEGATIVE_FLAG = -1;
	// 日历邮件和普通邮件
	public static final int EMAIL_GNET_EXT_CONTENT_TYPE = 1;
	public static final int EMAIL_GNET_CONTENT_TYPE = 0;
	// 会议提醒时间
	public static final int CONFERENCE_ALARM_TIME = 15;
	// 客服登录产品id
	public static final String OP_LOGIN_PRODUCTID = "20";
	// 站点id
	public static final String OP_LOGIN_SITEID = "0";
	/**
	 * 是否是主动服务会议 0非 1是
	 */
	public static final int IS_DEAL_MEETING = 0;
	/**
	 * 咨询客户
	 */
	public static final int IS_HOST = 1;
	/**
	 * 专家
	 */
	public static final int IS_GUEST = 0;
	/**
	 * 会议开始自动外呼专家
	 */
	public static final Integer MEETING_CONFIG_AUTO = 1;
	/**
	 * 有咨询客户入会 自动外呼专家
	 */
	public static final Integer MEETING_CONFIG_SINGLE_HOST = 2;
	/**
	 * 所有咨询客户入会 自动外呼专家
	 */
	public static final Integer MEETING_CONFIG_ALL_HOST = 3;
	/**
	 * 自咨询客户沟通，由客服外呼专家
	 */
	public static final Integer MEETING_CONFIG_DEAL = 4;
	/**
	 * 会议状态类型0：没有召开
	 */
	public static final Integer CONFERENCE_STATUS_NOTBEGIN = 0;
	/**
	 * 会议状态类型1：召开中
	 */
	public static final Integer CONFERENCE_STATUS_BEGINING = 1;
	/**
	 * 会议状态类型 2：已经结束
	 */
	public static final Integer CONFERENCE_STATUS_END = 2;
	/**
	 * 会议状态类型 3：已经被取消
	 */
	public static final Integer CONFERENCE_STATUS_CANCEL = 3;
	/**
	 * 会中休息 4
	 */
	public static final Integer CONFERENCE_STATUS_REST = 4;
	/**
	 * 会前10分钟可入会状态 5
	 */
	public static final Integer CONFERENCE_STATUS_PRE10 = 5;
	/**
	 * 会议调度任务类型 1:会议录音生成
	 */
	public static final Integer CONFERENCE_TIMER_TYPE_RECORD = 1;
	/**
	 * 会议调度任务类型 2:会议报表生成
	 */
	public static final Integer CONFERENCE_TIMER_TYPE_REPORT = 2;

	/**
	 * 会后报告调度任务 0 未完成 1 已完成
	 */
	public static final short TASK_UNFINISH = 0;
	public static final short TASK_FINISH = 1;
	public static final short TASK_ERROR_TIMES = 10;
	/**
	 * 任务十次失败
	 */
	public static final short TASK_ERROR_TEN = 2;
	// 1：result非空 会议已结束并有记录 ;
	public static final String CONFCDR_STATUS_END_RECORDS = "1";
	// 0：result为{} 会议已结束无会议记录;
	public static final String CONFCDR_STATUS_END_NORECORD = "0";
	// -1：result为{} 会议未结束
	public static final String CONFCDR_STATUS_NOEND = "-1";
	// -2：result为{} 无任何会议
	public static final String CONFCDR_STATUS_NOCONF = "-2";
	// -3：result为{} 会议结束是采集有问题
	public static final String CONFCDR_STATUS_END_GETERR = "-3";
	// -4：result为{} 会议结束采集中
	public static final String CONFCDR_STATUS_END_GETING = "-4";
	// 0:confCDR.info 会议明细未匹配城市；
	public static final String CONFCDR_INFO_CITY_NO = "0";
	// 0:confCDR.info 会议明细已匹配城市
	public static final String CONFCDR_INFO_CITY_OK = "1";
	// qsboss url
	public static String ConfCDR_REQUEST_URL = "";
	/**
	 * 任务池状态 -1、已取消；0、新任务；1、已完成
	 */
	public static final short SUPPORT_TASK_CONCEL = -1;
	public static final short SUPPORT_TASK_NEW = 0;
	public static final short SUPPORT_TASK_FINISH = 1;

	/**
	 * 会议类型 （0:交易 1：掉线 2：大方 3：重要）
	 */
	public static final int CONF_TYPE_BUSINESS = 0;
	public static final int CONF_TYPE_OUT = 1;
	public static final int CONF_TYPE_BIG = 2;
	public static final int CONF_TYPE_IMPORTENT = 3;

	public static final int IS_USED = 1;
	public static final int USABLE = 0;

	/**
	 * 会议结束时间加90秒-会议设置结束时间延后10小时再90秒
	 */
	public static final int CONFENDTIME_ADD_SECOND = 36090;

	/**
	 * 会议开始时间向前减90秒-会议设置开始时间前10分钟再90秒
	 */
	public static final int CONFSTARTTIME_ADD_SECOND = -690;

	// 会议调度任务类型1：未监控的会议录音生成
	public static final Integer NOACTIVECONF_TIMER_TYPE_RECORD = 1;

	// 会议配置
	// 1: 会议开始，立刻外呼专家（默认）
	public static final int CALL_EXPERT_IMMEDIATELY_WHEN_CONF_BEGIN = 1;
	// 2: 有咨询客户入会，外呼专家
	public static final int CALL_EXPERT_WHEN_CLIENT_ENROLLMENT = 2;
	// 3: 所有咨询客户入会，外呼专家
	public static final int CALL_EXPERT_WHEN_ALL_CLIENT_ENROLLMENT = 3;
	// 4: 与咨询客户确认后，外呼专家
	public static final int CALL_EXPERT_WHEN_WITH_CLIENT_CONFIRM = 4;
	// 会议录音
	public static final int CONFERENCE_RECORDING = 2;
	// 会议不录音
	public static final int CONFERENCE_NOT_RECORDING = 0;
	//会议发送通知邮件
	public static final int CONFERENCE_SEND_MAIL=1;
	//会议不发送通知邮件
	public static final int CONFERNCE_NOT_SEND_MAIL=0;
	//会议发送通知短信
	public static final int CONFERENCE_SEND_SMS=1;
	//会议不发送通知短信
	public static final int CONFERENCE_NOT_SEND_SMS=0;
	//限制主动入会电话
	public static final int CONFERENCE_LIMIT_PARTY=1;
	//不限制主动入会电话
	public static final int CONFERENCE_NOT_LIMIT_PARTY=0;

	public static int getConferenceConfigByName(String confConfig){
		Map<String, Integer> configMap = Maps.newHashMap();
		configMap.put("会议开始，自动外呼专家", CALL_EXPERT_IMMEDIATELY_WHEN_CONF_BEGIN);
		configMap.put("有咨询客户入会后，自动外呼专家", CALL_EXPERT_WHEN_CLIENT_ENROLLMENT);
		configMap.put("所有咨询客户入会后，自动外呼专家", CALL_EXPERT_WHEN_ALL_CLIENT_ENROLLMENT);
		configMap.put("与咨询客户确认后，由客服外呼专家", CALL_EXPERT_WHEN_WITH_CLIENT_CONFIRM);
		return configMap.get(confConfig);
	}

	// session_登录_用户信息
	public static final String SESSION_USERALLINFO = "userAllInfo";

	// session_登录_userName
	public static final String SESSION_USERNAME = "userName";
	
	// session_重新登录
	public static final String SESSION_RELOGIN = "relogin";
	
	// session_重置密码_umsId
	public static final String RESETPWD_MOBILE_UMSID = "setPwdFromMobile_umsId";

	// user status
	public static final int USER_STATUS_FOR_DISABLE = 0;
	public static final int USER_STATUS_FOR_ENABLE = 1;
	public static final int USER_STATUS_FOR_DELETE = 2;
    // 会议开通邮件标题
	public static final String CONFERENCE_INFO_PROFIX="会议通知---";
	public static final String CONFERENCE_CANCEL_PROFIX="会议取消---";
	public static final String CONFERENCE_CHANGE_PROXIF="会议变更---";
	//每页显示行数
	public static final int CONF_LIST_COUNT_FOR_EACHPAGE=30;
	public static final int APP_CONF_LIST_COUNT_FOR_EACHPAGE=30;
	//平台名称
	public static final String PLATEFORM_FOR_TWO="summit2";
	public static final String PLATEFORM_FOR_SEVEN="summit7";
	//回收密码已经处理过的会议
	public static final int IS_HANDLED=1;
	public static final int NOT_HANDLED=0;
}

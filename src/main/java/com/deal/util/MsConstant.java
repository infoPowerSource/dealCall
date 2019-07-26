/****************************************************************************************
 * Copyright (c) 2010~2012 All Rights Reserved by
 * G-Net Integrated Service Co.,  Ltd. 
 ****************************************************************************************/
package com.deal.util;

/**
 * @brief 公用常量
 * 
 * @author zhipeng.xu
 * 
 * @date 2017.05.16
 * 
 * @version 1.0.0
 * 
 *          Revision History
 ****************************************************************************************/
public class MsConstant
{
	/** Log Tag **/
	public static final String Log_Tag = "MS";

	/**
	 * 录音状态
	 * 
	 * @author zhipeng.xu
	 * 
	 */
	public static class RecordState
	{
		/** 停止录音 **/
		public static final int STATE_STOP = 0;
		/** 开始录音 **/
		public static final int STATE_START = 1;
		/** 暂停录音 **/
		public static final int STATE_PAUSE = 2;
		/** 录音连接中 **/
		public static final int STATE_PROGRESS = 100;
	}

	public static final class QAState
	{
		/** 开始QA状态 **/
		public static final int STATE_START = 1;
		/** 结束QA状态 **/
		public static final int STATE_END = 0;
	}

	/**
	 * 会场语音状态
	 * 
	 * @author zhipeng.xu
	 * 
	 */
	public static final class WaitingLine
	{
		/** 开始会场语音 **/
		public static final int STATE_START = 1;
		/** 结束会场语音 **/
		public static final int STATE_STOP = 0;
	}

	public static class MapKey
	{
		/** 消息ID key **/
		public static final String ACTION_KEY = "signId";
		/** 错误消息 key **/
		public static final String ERROR_CODE = "errorCode";
		/** 会议唯一标识 **/
		public static final String BILLING_CODE = "billingCode";
		/** 参会人唯一标识 **/
		public static final String PARTY_ID = "partyId";
		/** 消息通道名 **/
		public static final String ROUTING_KEY = "routingKey";
		/** 操作序号 **/
		public static final String ACTION = "action";
		/** Notify消息的key **/
		public static final String NOTIFY_KEY = "rMsgId";
		/** Notify消息的key **/
		public static final String NOTIFY_KEY_DESC = "rMsgIdDesc";
		/** 返回消息的key **/
		public static final String REMSG_KEY = "rMsgId";
		/** 返回消息ID key **/
		public static final String NOTIFY_OLD_VALUE = "oldValue";
		/** 返回消息ID key **/
		public static final String NOTIFY_NEW_VALUE = "newValue";
		/** 返回消息ID key **/
		public static final String NOTIFY_OBJECT = "obj";
		/** 请求监控会议的方法 0，表示不需要调用开会的方法;1，表示需要调用开会的方法 **/
		public static final String MONITOR_TYPE_KEY = "monitorType";
		/** partyid列表标识 **/
		public static final String PARTY_ID_LIST_KEY = "partyIdList";// /<partyid列表标识
		/** dpe列表标识 **/
		public static final String DPE_KEY = "dpeList";
		/** 投票问题 **/
		public static final String VOTE_QUESTION = "voteQuestion";// /<投票问题
		/** 投票选项 **/
		public static final String VOTE_CHOICES = "voteChoices";// /<投票选项
		/** 使用指定的语音文件 **/
		public static final String WAITINGLINE_FILE_NAME = "waitinglineFileName";// /<使用指定的语音文件
		/** 主持人入会提示音 **/
		public static final String CONF_HOSTENTRY = "confHostEntry";// /<主持人入会提示音
		/** 主持人退会提示音 **/
		public static final String CONF_HOSTEXIT = "confHostExit";// /<主持人退会提示音
		/** 参与人入会提示音 **/
		public static final String CONF_GUESTENTRY = "confGuestEntry";// /<参与人入会提示音
		/** 参与人退会提示音 **/
		public static final String CONF_GUESTEXIT = "confGuestExit";// /<参与人退会提示音
		/** 新会议时长 **/
		public static final String CONF_DURATION = "confDuration";// /<新会议时长
		/** Party电话号码 **/
		public static final String PARTY_PHONE = "partyPhone";// /<Party电话号码
		/** PartyRole **/
		public static final String PARTY_ROLE = "partyRole";// /<PartyRole
		/** partyName **/
		public static final String PARTY_NAME = "partyName";// /<partyName
		/** partyUserDefined **/
		public static final String PARTY_USER_DEFINED = "partyUserDefined";// /<partyUserDefined
		/** 参与人静音操作类型0:自我静音/解除自我静音操作;1:主持人静音/解除静音与会方操作 **/
		public static final String PARTY_MUTE_TYPE = "partyMuteType";// /<参与人静音操作类型0:自我静音/解除自我静音操作;1:主持人静音/解除静音与会方操作
		/** 是否播放提示音 **/
		public static final String PLAY_MESSAGE = "playMessage";// /<是否播放提示音
		public static final String CONFERENCE = "conference";
		public static final String PARTY = "party";
		/** 外呼结果,有失败时才返回 **/
		public static final String DAIL_RESULT = "dailResult";// /<外呼结果,有失败时才返回
		public static final String MONITOR_BRIDGENAME = "monitor_bridgeName";
	}

	/**
	 * 静音状态
	 * 
	 * @author zhipeng.xu
	 * 
	 */
	public static class MuteState
	{
		/** 自我静音状态 **/
		public static final int STATE_MUTE = 1;
		/** 解除自我静音状态 **/
		public static final int STATE_UNMUTE = 0;
	}

	/**
	 * Action消息类型
	 * 
	 * @author zhipeng.xu
	 * 
	 */
	public static class ActionIds
	{
		/** 获取会中所有信息 **/
		public static final String ALL_CONF_INFO = "ALLINFO";// /<获取会中所有信息
		/** 监控会议请求 **/
		public static final String START_MONITOR = "1";// /<监控会议请求
		/** 外呼单个参会人请求 **/
		public static final String DIAL_PARTY = "2";// /<外呼单个参会人请求
		/** 参会人请求退出会议请求 **/
		public static final String PARTY_EXIT = "3";// /<参会人请求退出会议
		/** 参与人联系客服请求 **/
		public static final String PARTY_CALL_OPERATOR = "4";// /< 参与人联系客服请求
		/** 录音请求 **/
		public static final String CONF_RECORD = "5";// /<录音请求
		/** 全体静音请求 **/
		public static final String CONF_MUTE = "6";// /<全体静音请求
		/** 投票请求 **/
		public static final String CONF_VOTE = "7";// /<投票请求
		/** 问答请求 **/
		public static final String CONF_QA = "8";// /<问答请求
		/** 踢出参会人请求 **/
		public static final String KICKOUT_PARTY = "9";// /<踢出参会人请求
		/** 会议状态询问请求 **/
		public static final String ENQUIRE_CONF = "11";// /<会议状态询问请求
		/** 会议锁定请求 **/
		public static final String CONF_LOCK = "12";// /<会议锁定请求
		/** Talker请求 **/
		public static final String CONF_TALKER = "13";// /<Talker请求
		/** WaitingLine请求 **/
		public static final String CONF_WAITING_LINE = "14";// /<waitingline请求
		/** 修改会议进出提示音请求 **/
		public static final String CONF_ENTRY_EXIT_CONFIG = "15";// /<修改会议进出提示音请求
		/** 会议延时请求 **/
		public static final String CONF_NEW_DURATION = "16";// /<会议延时请求
		/** 会议联系客服请求 **/
		public static final String CONF_CALL_OPERATOR = "17";// /<会议联系客服请求
		/** 修改用户的电话号码请求 **/
		public static final String MODIFY_PARTY_PHONE = "18";// /<修改用户的电话号码请求
		/** 修改用户的角色请求 **/
		public static final String MODIFY_PARTY_ROLE = "19";// /<修改用户的角色请求
		/** 开启子会请求 **/
		public static final String CREATE_SUB_CONF = "20";// /<开启子会请求
		/** 转移参会者到其他的子会请求 **/
		public static final String TRANSFER_PARTY = "21";// /<转移参会者到其他的子会请求
		/** 结束会议请求 **/
		public static final String CLOSE_CONF = "22";// /<结束会议请求
		/** 疑似噪音请求 **/
		public static final String NOISE_PARTY = "23";// /<疑似噪音请求
		/** 外呼多个参会人请求 **/
		public static final String DIAL_MULTI_PARTY = "24";// /<外呼多个参会人请求
		/** 单个外呼已经存在的会议方请求 **/
		public static final String DIAL_EXIST_PARTY = "25";// /<单个外呼已经存在的会议方请求
		/** 使用新的角色单个外呼已经存在的会议方请求 **/
		public static final String DIAL_EXIST_PARTY_ROLE = "26";// /<使用新的角色单个外呼已经存在的会议方请求
		/** 多个外呼已经存在的会议方请求 **/
		public static final String DIAL_MULTI_EXIST_PARTY = "27";// /<多个外呼已经存在的会议方请求
		/** 获取所有订阅的会议请求 **/
		public static final String GET_SUBSCRIBED_CONFS = "28";// /<多个外呼已经存在的会议方请求
		/** 参与人静音请求 **/
		public static final String PARTY_MUTE = "29";// /<参与人静音请求
		/** 文字聊天请求 **/
		public static final String CHAT = "100";// /<文字聊天请求
		/** 聊天历史请求 **/
		public static final String CHAT_HISTORY = "101";// /<聊天历史请求
		/** QA状态时会议方位置移动以及删除 **/
		public static final String CONF_QA_MOVE = "32";// /<QA状态时会议方位置移动以及删除
		/** 修改参会人姓名 **/
		public static final String MODIFY_PARTY_NAME = "33";// /<修改参会人姓名
		/** 修改参会人扩展字段 **/
		public static final String MODIFY_PARTY_USER_DEFINED = "34";// /<修改参会人扩展字段
		/** 修改参会人扩展字段2 **/
		public static final String MODIFY_PARTY_USER_DEFINED2 = "35";// /<修改参会人扩展字段
		/** 修改参会人扩展字段3 **/
		public static final String MODIFY_PARTY_USER_DEFINED3 = "36";// /<修改参会人扩展字段
	}

	/**
	 * Notify消息类型
	 * 
	 * @author zhipeng.xu
	 * 
	 */
	public static class NotifyKey
	{
		// 会议
		public static final String Conf_Start = "Conf_Start"; // <会议开始
		public static final String Conf_End = "Conf_End"; // <会议结束
		public static final String Conf_Living = "Conf_Living"; // <会议Living
		// 会议属性 ;
		public static final String Conf_State = "Conf_State"; // <会议状态
		public static final String Conf_LockState = "Conf_LockState"; // /<会议加锁
		public static final String Conf_MuteState = "Conf_MuteState"; // /<会议静音
		public static final String Conf_RecordState = "Conf_RecordState"; // /<会议录音
		public static final String Conf_TalkerState = "Conf_TalkerState"; // /<会议Talker状态
		public static final String Conf_WaitingLineState = "Conf_WaitingLineState"; // <会议插播录音
		public static final String Conf_QAState = "Conf_QAState"; // <会议QA
		public static final String Conf_VotingState = "Conf_VotingState"; // /<会议投票
		public static final String Conf_EntryExitTone = "Conf_EntryExitTone"; // /<会议进出提示音
		public static final String Conf_GuestAudioMode = "Conf_GuestAudioMode"; // /<参与人进入会议的状态
		public static final String Conf_GuestMuteOverride = "Conf_GuestMuteOverrid"; // /<参与人*6解除*4设置
		public static final String Conf_NewDuration = "Conf_NewDuration"; // /<会议新时长
		public static final String Conf_MaxParties = "Conf_MaxParties"; // /<会议最大方数
		public static final String Conf_ChairDisc = "Conf_ChairDisc"; // /<主持人挂断，会议是否结束
		public static final String Conf_QuickStart = "Conf_QuickStart"; // /<主持人未到会，会议是否开始
		public static final String Conf_Pnr = "Conf_Pnr"; // 入会时是否录制姓名
		public static final String Conf_TalkingParty = "Conf_TalkingParty"; // /<talking会议方
		public static final String Conf_NoiseParty = "Conf_NoiseParty"; // /<疑似噪音会议方
		public static final String Conf_RecordEnabled = "Conf_RecordEnabled"; // /<会议是否能录音
		public static final String Conf_RecordStateAcm = "Conf_RecordStateAcm"; // /<ACM自定义的录音状态
		// QA ;
		public static final String QA_Moderators = "QA_Moderators"; // <QA的Moderators列表
		public static final String QA_FloorPartyID = "QA_FloorPartyID"; // /<QA的FloorPartyID
		public static final String QA_Queue = "QA_Queue"; // <QA的举手列表
		// Voting ;
		public static final String Voting_Result = "Voting_Result"; // <投票结果
		public static final String Voting_PartyResult = "Voting_PartyResult"; // /<记名投票结果
		// 会议方属性 " ;
		public static final String Party_Add = "Party_Add"; // <增加会议方
		public static final String Party_Del = "Party_Del"; // <删除会议方
		public static final String Party_Name = "Party_Name"; // <会议方名称
		public static final String Party_Phone = "Party_Phone"; // <电话
		public static final String Party_Role = "Party_Role"; // <角色
		public static final String Party_ConnectState = "Party_ConnectState"; // /<连接状态
		public static final String Party_DataConfID = "Party_DataConfID"; // /<DataConfID
		public static final String Party_UserDefined = "Party_UserDefined"; // /<UserDefined
		public static final String Party_UserDefined2 = "Party_UserDefined2"; // /<UserDefined2
		public static final String Party_UserDefined3 = "Party_UserDefined3"; // /<UserDefined3
		public static final String Party_AGC = "Party_AGC"; // <AGC
		public static final String Party_InputGain = "Party_InputGain"; // /<InputGain
		public static final String Party_OutputGain = "Party_OutputGain"; // /<OutputGain
		public static final String Party_NetWorkHold = "Party_NetWorkHold"; // /<NetworkHold
		public static final String Party_IsRequestOpHelp = "Party_IsRequestOpHelp"; // /<是否请求了Operator																// Help
		public static final String Party_OperatorTime = "Party_OperatorTime"; // /<和Operator通话的开始时间
	}

	public static class ActionResultKey
	{
		/** 操作返回会议唯一标识 key **/
		public static final String BILLINGCODE_KEY = "billingCode";
	}

	public static class ErrorCodes
	{
		/** 会议没有召开 **/
		public static final String CONF_NOT_OPEN = "8001";// /<会议没有召开
		/** 解析JSON失败 **/
		public static final String INVALID_JSON_STRING = "8009";// /<解析JSON失败
		/** 无效的请求格式 **/
		public static final String INVALID_ACTION_KEY = "8012";// /<无效的请求格式
		/** 外呼失败 **/
		public static final String DIAL_OUT_FAILURE = "8015";// /<外呼失败
		/** Billingcode格式不正确 **/
		public static final String INVALID_BILLINGCODE = "8016";// /<Billingcode格式不正确
		/** 预付费卡余额不足 **/
		public static final String INVALID_PREPAID_CARD = "8059";// /<预付费卡余额不足
		/** Partyid格式错误 **/
		public static final String INVALID_PARTYID = "8018";// /<Partyid格式错误
		/** 参会人联系客服失败 **/
		public static final String PARTY_CALL_OP_FAILURE = "8023";// /<参会人联系客服失败
		/** 录音失败 **/
		public static final String CONF_RECORD_FAILURE = "8025";// /<录音失败
		/** 外呼参会人为空 **/
		public static final String DIAL_NULL_PARTY = "8060";// /<外呼参会人为空
		/** ACM系统异常 **/
		public static final String ACM_FAILS = "8061";// /<ACM系统异常
		/** 会议订阅失败 **/
		public static final String CONF_SUBSCRIBE_FAILURE = "8062";// /<会议订阅失败
		/** hangupType错误 **/
		public static final String INVALID_HANGUP_TYPE = "8063";// /<hangupType错误
		/** opType错误 **/
		public static final String INVALID_OP_TYPE = "8064";// /<opType错误
		/** 挂断参会人失败 **/
		public static final String HANGUP_PARTY_FAILURE = "8065";// /<挂断参会人失败
		/** 会场静音失败 **/
		public static final String CONF_MUTE_FAILURE = "8066";// /<会场静音失败
		/** 关闭录音失败 **/
		public static final String CONF_STOP_RECORD_FAILURE = "8067";// /<关闭录音失败
		/** 暂停录音失败 **/
		public static final String CONF_PAUSE_RECORD_FAILURE = "8068";// /<暂停录音失败
		/** 解除会场静音失败 **/
		public static final String CONF_UNMUTE_FAILURE = "8069";// /<解除会场静音失败
		/** 结束投票失败 **/
		public static final String CONF_UNVOTE_FAILURE = "8070";// /<结束投票失败
		/** 开启投票失败 **/
		public static final String CONF_VOTE_FAILURE = "8071";// /<开启投票失败
		/** 投票questions为空 **/
		public static final String CONF_VOTE_NO_QUESTIONS_FAILURE = "8072";// /<投票questions为空
		/** choice格式错误 **/
		public static final String CONF_VOTE_INVALID_CHOICE = "8073";// /<choice格式错误
		/** 结束问答失败 **/
		public static final String CONF_STOP_QA_FAILURE = "8074";// /<结束问答失败
		/** 开启问答失败 **/
		public static final String CONF_START_QA_FAILURE = "8075";// /<开启问答失败
		/** 踢出与会者失败 **/
		public static final String CONF_KICKOUT_PARTY_FAILURE = "8076";// /<踢出与会者失败
		/** 调用【当前会议是否Living】方法失败 **/
		public static final String CONF_IS_LIVING_FAILURE = "8077";// /<调用【当前会议是否Living】方法失败
		/** 会议锁定失败 **/
		public static final String CONF_LOCK_FAILURE = "8078";// /<会议锁定失败
		/** 解除会议锁定失败 **/
		public static final String CONF_UNLOCK_FAILURE = "8079";// /<解除会议锁定失败
		/** lockType格式不正确 **/
		public static final String CONF_INVALID_LOCK_TYPE = "8080";// /<lockType格式不正确
		/** talkerType格式错误 **/
		public static final String CONF_INVALID_TALKER_TYPE = "8081";// /<talkerType格式错误
		/** 开启talker失败 **/
		public static final String CONF_START_TALKER_FAILURE = "8082";// /<开启talker失败
		/** 关闭talker失败 **/
		public static final String CONF_STOP_TALKER_FAILURE = "8083";// /<关闭talker失败
		/** wlType（waitingline操作标识）格式错误 **/
		public static final String CONF_INVALID_WAITINGLINE = "8084";// /<wlType（waitingline操作标识）格式错误
		/** 开启waitingline失败 **/
		public static final String CONF_START_WAITINGLINE_FAILURE = "8085";// /<开启waitingline失败
		/** 关闭waitingline失败 **/
		public static final String CONF_STOP_WAITINGLINE_FAILURE = "8086";// /<关闭waitingline失败
		/** Filename格式错误 **/
		public static final String INVALID_FILE_NAME = "8087";// /<Filename格式错误
		/** hostEntry主持人入会提示音编号格式错误 **/
		public static final String INVALID_HOSTENTRY = "8088";// /<hostEntry主持人入会提示音编号格式错误
		/** hostExit主持退会议提示音编号格式错误 **/
		public static final String INVALID_HOSTEXIT = "8089";// /<hostExit主持退会议提示音编号格式错误
		/** guestEntry参与人入会提示音编号格式错误 **/
		public static final String INVALID_GUESTENTRY = "8090";// /<guestEntry参与人入会提示音编号格式错误
		/** guestExit参与人退会提入会退会提示音错误 **/
		public static final String INVALID_GUESTEXIT = "8091";// /<guestExit参与人退会提入会退会提示音错误
		/** 修改入会退会提示音失败 **/
		public static final String CONF_ENTRY_EXIT_CONFIG_FAILURE = "8092";// /<修改入会退会提示音失败
		/** newDuration格式错误 **/
		public static final String CONF_INVALID_NEW_DURATION = "8093";// /<newDuration格式错误
		/** 【会议延时】失败 **/
		public static final String CONF_NEW_DURATION_FAILURE = "8094";// /<【会议延时】失败
		/** Coptype（会议联系客服操作类型）格式错误 **/
		public static final String INVALID_CONF_OP_TYPE = "8095";// /<Coptype（会议联系客服操作类型）格式错误
		/** 会议联系客服失败 **/
		public static final String CONF_OP_FAILURE = "8096";// /<会议联系客服失败
		/** 取消会议联系客服失败 **/
		public static final String CONF_CANCEL_OP_FAILURE = "8097";// /<取消会议联系客服失败
		/** 【修改用户的电话号码】失败 **/
		public static final String PARTY_PHONE_MODIFY_FAILURE = "8098";// /<【修改用户的电话号码】失败
		/** 权限格式错误 **/
		public static final String INVALID_ROLE_TYPE = "8099";// /<权限格式错误
		/** 权限格式错误 **/
		public static final String INVALID_NAME_TYPE = "8199";// /<权限格式错误
		/** 【修改用户的角色】失败 **/
		public static final String PARTY_ROLE_MODIFY_FAILURE = "8100";// /<【修改用户的角色】失败
		/** 【修改用户的姓名】失败 **/
		public static final String PARTY_NAME_MODIFY_FAILURE = "8200";// /<【修改用户的姓名】失败
		/** 【修改用户的扩展字段】失败 **/
		public static final String PARTY_USER_DEFINED_MODIFY_FAILURE = "8201";// /<【修改用户的扩展字段】失败
		/** 【结束会议】失败 **/
		public static final String CONF_CLOSE_FAILURE = "8101";// /<【结束会议】失败
		/** 外呼列表为空 **/
		public static final String DIAL_EMPTY_PARTY_LIST = "8102";// /<外呼列表为空
		/** 【外呼多个参会人】失败 **/
		public static final String DIAL_MULTI_PARTY_FAILURE = "8103";// /<【外呼多个参会人】失败
		/** 【单个外呼已经存在的会议方】失败 **/
		public static final String DIAL_EXIST_PARTY_FAILURE = "8104";// /<【单个外呼已经存在的会议方】失败
		/** 【使用新的角色单个外呼已经存在的会议方】失败 **/
		public static final String DIAL_EXIST_PARTY_WITH_NEW_ROLE_FAILURE = "8105";// /<【使用新的角色单个外呼已经存在的会议方】失败
		/** partyIDList为空 **/
		public static final String EMPTY_PARTYIDLIST = "8106";// /<partyIDList为空
		/** 【多个外呼已经存在的会议方】失败 **/
		public static final String DIAL_MULTI_EXIST_PARTY_FAILURE = "8107";// /<【多个外呼已经存在的会议方】失败
		/** 【获取所有订阅的会议】失败 **/
		public static final String GET_ALL_CONFERENCE_FAILURE = "8108";// /<【获取所有订阅的会议】失败
		/** pmuteType格式错误 **/
		public static final String PARTY_MUTE_INVALID_TYPE = "8109";// /<pmuteType格式错误
		/** mutestate格式错误 **/
		public static final String INVALID_MUTESTATE = "8110";// /<mutestate格式错误
		/** playMessage格式错误 **/
		public static final String INVALID_PLAYMESSAGE = "8111";// /<playMessage格式错误
		/** 【自我静音/解除自我静音】失败 **/
		public static final String PARTY_MUTE_UNMUTE_FAILURE = "8112";// /<【自我静音/解除自我静音】失败
		/** 【主持人静音/解除静音与会方】失败 **/
		public static final String CONF_MUTE_UNMUTE_PARTY_FAILURE = "8113";// /<【主持人静音/解除静音与会方】失败
		/** 【主持人静音或解除多个人静音】失败 **/
		public static final String CONF_MUTE_UNMUTE_MULTI_PARTY_FAILURE = "8114";// /<【主持人静音或解除多个人静音】失败
		/** 【自我静音】失败 **/
		public static final String PARTY_MUTE_FAILURE = "8030";// /<【自我静音】失败
		/** 【取消自我静音】失败 **/
		public static final String PARTY_UNMUTE_FAILURE = "8031";// /<【取消自我静音】失败
		/** 【主持人静音与会方】失败 **/
		public static final String HOST_MUTE_PARTY_FAILURE = "8045";// /<【主持人静音与会方】失败
		/** 【主持人取消静音与会方】失败 **/
		public static final String HOST_UNMUTE_PARTY_FAILURE = "8046";// /<【主持人取消静音与会方】失败
		/** 【主持人静音多个与会方】失败 **/
		public static final String HOST_MUTE_MULTI_PARTY_FAILURE = "8112";// /<【主持人静音多个与会方】失败
		/** 【主持人解除静音多个与会方】失败 **/
		public static final String HOST_UNMUTE_MULTI_PARTY_FAILURE = "8113";// /<【主持人解除静音多个与会方】失败
		/** 问答移动会议方失败 **/
		public static final String CONF_QA_MOVE_FAILURE = "1010";// /<问答移动会议方失败
		/** 问答移动会议方操作参数错误 **/
		public static final String INVALID_CONF_QA_MOVE_TYPE = "1011";// /<问答移动会议方操作参数错误
		/** 外呼Action中存在外呼失败 **/
		public static final String DAIL_EXSIT_FAUILT = "1020";// /<外呼Action中存在外呼失败
	}
}

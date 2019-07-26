/****************************************************************************************
 * Copyright (c) 2010~2012 All Rights Resverved by
 *  G-Net Integrated Service co. Ltd. 
 ****************************************************************************************/
package com.deal.util;

import java.util.HashMap;
import java.util.Map;

import com.gnet.acmw.client.event.EventIndex;

/**
 * @brief 
 *		
 * @author zhipeng.xu
 *
 * @date 2017.05.16
 *
 * @version 1.0.0
 *        
 * Revision History 
 ****************************************************************************************/
public class MsNotifyIndex {
	private static final Map<Integer, String> indexPropertyNameMap = new HashMap<Integer, String>();
	
	private static final Map<Integer, String> confIndexMap = new HashMap<Integer, String>();
	
	private static final Map<Integer, String> confPropertyIndexMap = new HashMap<Integer, String>();
	
	private static final Map<Integer, String> partyIndexMap = new HashMap<Integer, String>();
	
	private static final Map<Integer, String> partyPropertyIndexMap = new HashMap<Integer, String>();
	static {
		
		confIndexMap.put(EventIndex.Conf_Start, 				"Conf_Start");
		confIndexMap.put(EventIndex.Conf_End, 				"Conf_End");
		confIndexMap.put(EventIndex.Conf_State, 			"Conf_State");
		confPropertyIndexMap.put(EventIndex.Conf_Living, 			"Conf_Living");
		confPropertyIndexMap.put(EventIndex.Conf_LockState, 		"Conf_LockState");
		confPropertyIndexMap.put(EventIndex.Conf_MuteState, 		"Conf_MuteState");
		confPropertyIndexMap.put(EventIndex.Conf_RecordState, 		"Conf_RecordState");
		confPropertyIndexMap.put(EventIndex.Conf_TalkerState, 		"Conf_TalkerState");
		confPropertyIndexMap.put(EventIndex.Conf_WaitingLineState, "Conf_WaitingLine");
		confPropertyIndexMap.put(EventIndex.Conf_QAState, 			"Conf_QAState");
		confPropertyIndexMap.put(EventIndex.Conf_VotingState, 		"Conf_VotingState");
		confPropertyIndexMap.put(EventIndex.Conf_EntryExitTone,		"Conf_EntryExitTone");
		confPropertyIndexMap.put(EventIndex.Conf_Pnr, 		"Conf_Pnr");
		confPropertyIndexMap.put(EventIndex.Conf_GuestAudioMode, 		"Conf_GuestAudioMode");
		confPropertyIndexMap.put(EventIndex.Conf_GuestMuteOverride, 		"Conf_GuestMuteOverride");
		confPropertyIndexMap.put(EventIndex.Conf_NewDuration, 		"Conf_NewDuration");
		confPropertyIndexMap.put(EventIndex.Conf_MaxParties, 		"Conf_MaxParties");
		confPropertyIndexMap.put(EventIndex.Conf_ChairDisc, 		"Conf_ChairDisc");
		confPropertyIndexMap.put(EventIndex.Conf_QuickStart, 		"Conf_QuickStart");
		confPropertyIndexMap.put(EventIndex.Conf_TalkingParty, 		"Conf_TalkingParty");
		confPropertyIndexMap.put(EventIndex.Conf_NoiseParty, 		"Conf_NoiseParty");
		confPropertyIndexMap.put(EventIndex.Conf_RecordEnabled,		"Conf_RecordEnabled");	
		confPropertyIndexMap.put(EventIndex.Conf_RecordStateAcm,	"Conf_RecordStateAcm");
		
		confPropertyIndexMap.put(EventIndex.QA_Moderators, 			"QA_Moderators");
		confPropertyIndexMap.put(EventIndex.QA_FloorPartyID, 		"QA_FloorPartyID");
		confPropertyIndexMap.put(EventIndex.QA_Queue, 				"QA_Queue");
		confPropertyIndexMap.put(EventIndex.Voting_Result, 			"Voting_Result");
		//confPropertyIndexMap.put(EventIndex.Voting_PartyResult, 			"Voting_PartyResult");//<记名投票结果
		partyIndexMap.put(EventIndex.Party_Add, 			"Party_Add");
		partyIndexMap.put(EventIndex.Party_Del, 			"Party_Del");
		
		partyPropertyIndexMap.put(EventIndex.Party_Name, 		"Party_Name");
		partyPropertyIndexMap.put(EventIndex.Party_Phone, 			"Party_Phone");
		partyPropertyIndexMap.put(EventIndex.Party_Role, 			"Party_Role");
		partyPropertyIndexMap.put(EventIndex.Party_ConnectState,	"Party_ConnectState");
		partyPropertyIndexMap.put(EventIndex.Party_DataConfID, 		"Party_DataConfID");
		//partyPropertyIndexMap.put(EventIndex.Party_InputGain,	"Party_InputGain");
		//partyPropertyIndexMap.put(EventIndex.Party_OutputGain,	"Party_OutputGain");		
		partyPropertyIndexMap.put(EventIndex.Party_UserDefined, 	"Party_UserDefined");
		partyPropertyIndexMap.put(EventIndex.Party_UserDefined2, 	"Party_UserDefined2");
		partyPropertyIndexMap.put(EventIndex.Party_UserDefined3, 	"Party_UserDefined3");
		
		//partyPropertyIndexMap.put(EventIndex.Party_AGC, 		"Party_AGC");
		partyPropertyIndexMap.put(EventIndex.Party_IsRequestOpHelp, "Party_IsRequestOpHelp");
		partyPropertyIndexMap.put(EventIndex.Party_OperatorTime, 	"Party_OperatorTime");
		//partyPropertyIndexMap.put(EventIndex.Party_NetWorkHold, 	"Party_NetWorkHold");
		partyPropertyIndexMap.put(EventIndex.Party_OperatorTime, 	"Party_OperatorTime");
		indexPropertyNameMap.putAll(confIndexMap);
		indexPropertyNameMap.putAll(confPropertyIndexMap);
		indexPropertyNameMap.putAll(partyIndexMap);
		indexPropertyNameMap.putAll(partyPropertyIndexMap);
	}
	
	public static boolean isConfNotify(int indexKey) {
		return confIndexMap.containsKey(indexKey);
	}
	public static boolean isConfPropertyNotify(int indexKey) {
		return confPropertyIndexMap.containsKey(indexKey);
	}
	
	public static boolean isPartyNotify(int indexKey) {
		return partyIndexMap.containsKey(indexKey);
	}
	
	public static boolean isPartyPropertyNotify(int indexKey) {
		return partyPropertyIndexMap.containsKey(indexKey);
	}
	
	/**
	 * @brief 根据index获取属性名
	 * @param[in] index Event的Index
	 */
	public static String getPropertyName(int index){
		return indexPropertyNameMap.get(index);
	}
}

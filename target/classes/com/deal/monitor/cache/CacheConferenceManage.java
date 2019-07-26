package com.deal.monitor.cache;

import java.util.List;

import org.springframework.util.ObjectUtils;

import com.deal.entity.party.CacheParty;
import com.deal.entity.party.CahcheConference;
import com.gnet.acmw.client.IConference;
import com.gnet.acmw.client.IParty;
import com.google.common.collect.Lists;

/**
 * 缓存管理类
 * 
 * @author zhipeng.xu
 * 
 */
public class CacheConferenceManage{
	/**
	 * 保存会议缓存对象
	 */

	/**
	 * @brief 获取会议缓存对象
	 * @param billingCode
	 *            会议的billingCode
	 * @return 会议对象；如果没有获取到则为null
	 */
	public static IConference getCacheConferenceInstace(String billingCode){
		return null;
	}

	/**
	 * 保存ACM端会议对象引用，不存在则保存，存在则设置会议缓存对象
	 * 
	 * @param billingCode
	 * @param conference
	 */
	public static void pushCacheConference(String billingCode, IConference cacheConference){
		if(!RedisService.isExist(billingCode)){
			CahcheConference cacheConf = new CahcheConference();
			List<CacheParty> partyList = Lists.newArrayList();
			cacheConf.setBillingcode(billingCode);
			cacheConf.setRecordStatus("0");
			for(IParty party : cacheConference.getPartyList()){
				CacheParty cacheparty = new CacheParty();
				cacheparty.setPhoneNumber(party.getPhoneNumber().getPhoneNumber());
				cacheparty.setConnectState(party.getConnectState());
				cacheparty.setPartyId(party.getPartyID());
				cacheparty.setPartyRole(party.getRole());
				partyList.add(cacheparty);
			}
			cacheConf.setPartyList(partyList);
			RedisService.putCache(billingCode, cacheConf);
		}else{
			CahcheConference cacheConf = (CahcheConference) RedisService.getCache(billingCode);
			List<CacheParty> partyList = Lists.newArrayList();
			cacheConf.setBillingcode(billingCode);
			cacheConf.setRecordStatus("0");
			for(IParty party : cacheConference.getPartyList()){
				CacheParty cacheparty = new CacheParty();
				cacheparty.setPhoneNumber(party.getPhoneNumber().getPhoneNumber());
				cacheparty.setConnectState(party.getConnectState());
				cacheparty.setPartyId(party.getPartyID());
				cacheparty.setPartyRole(party.getRole());
				partyList.add(cacheparty);
			}
			cacheConf.setPartyList(partyList);
			RedisService.putCache(billingCode, cacheConf);
		}
	}

	/**
	 * 清除ACM端会议对象引用
	 * 
	 * @param billingCode
	 */
	public static void clearCacheConference(String billingCode){
		if(RedisService.isExist(billingCode)){
			RedisService.removeCache(billingCode);
		}
	}

	/**
	 * 获取保存ACM端会议和参会人引用的对象,保证获取到对象不为空
	 * 
	 * @param billingCode
	 * @return
	 */
	public static CahcheConference getLocalCacheConference(String billingCode){
		CahcheConference cahcheConference = null;
		if(RedisService.isExist(billingCode)){
			cahcheConference = (CahcheConference)RedisService.getCache(billingCode);
		}
		return cahcheConference;
	}

	/**
	 * 保存参会人引用
	 * 
	 * @param billingCode
	 * @param cacheConference
	 * @param partyId
	 * @param party
	 */
	public static void addPartyByToCache(String billingCode, String partyId, IParty party){
		if(RedisService.isExist(billingCode)){
			CahcheConference cacheConf = (CahcheConference) RedisService.getCache(billingCode);
			List<CacheParty> partyList = cacheConf.getPartyList();
			CacheParty cacheParty = new CacheParty();
			cacheParty.setConnectState(party.getConnectState());
			cacheParty.setPhoneNumber(party.getPhoneNumber().getPhoneNumber());
			cacheParty.setPartyId(partyId);
			cacheParty.setPartyRole(party.getRole());
			partyList.add(cacheParty);
			cacheConf.setPartyList(partyList);
			RedisService.putCache(billingCode, cacheConf);
		}
	}

	/**
	 * 获取缓存中的参会人引用
	 * 
	 * @param billingCode
	 * @param partyId
	 * @return
	 */
	public static CacheParty getCacheParty(String billingCode, String partyId){
		CacheParty party = null;
		if(RedisService.isExist(billingCode)){
			CahcheConference cacheConf = (CahcheConference) RedisService.getCache(billingCode);
			List<CacheParty> partyList = cacheConf.getPartyList();
			for(CacheParty Iparty : partyList){
				if(Iparty.getPartyId().equalsIgnoreCase(partyId)){
					party = Iparty;
				}
			}
		}
		return party;
	}

	/**
	 * 清除缓存的参会人
	 * 
	 * @param billingCode
	 * @param partyId
	 */
	public static void clearCacheParty(String billingCode, String partyId){
		CahcheConference cacheConf = (CahcheConference) RedisService.getCache(billingCode);
		if(!ObjectUtils.isEmpty(cacheConf)){
			List<CacheParty> partyList = cacheConf.getPartyList();
			if(partyList.size() > 0){
				for(CacheParty party : partyList){
					if(party.getPartyId().equalsIgnoreCase(partyId)){
						partyList.remove(party);
						break;
					}
				}
			}
			cacheConf.setPartyList(partyList);
			RedisService.putCache(billingCode, cacheConf);
		}
	}

	/**
	 * 更新缓存中参会人信息
	 * 
	 * @param billingCode会议bc
	 * @param partyId参会人id
	 * @param party参会人信息
	 */
	public static void updateCacheParty(String billingCode, String partyId, IParty party){
		if(RedisService.isExist(billingCode)){
			CahcheConference cacheConf = (CahcheConference) RedisService.getCache(billingCode);
			List<CacheParty> partyList = cacheConf.getPartyList();
			for(CacheParty cParty : partyList){
				if(cParty.getPartyId().equalsIgnoreCase(partyId)){
					cParty.setConnectState(party.getConnectState());
					cParty.setConnectTime(party.getConnectTime());
					cParty.setDisconnectReason(party.getDisconnectReason());
					cParty.setInConfTime(party.getInConfTime());
				}
			}
			RedisService.putCache(billingCode, cacheConf);
		}
	}

	/**
	 * 更新本地缓存
	 * 
	 * @param billingCode
	 */
	public static void updateCache(String billingCode, IConference cacheConference){
		if(RedisService.isExist(billingCode)){
			CahcheConference cacheConf = (CahcheConference) RedisService.getCache(billingCode);
			List<CacheParty> partyList = Lists.newArrayList();
			cacheConf.setBillingcode(billingCode);
			for(IParty party : cacheConference.getPartyList()){
				CacheParty cacheparty = new CacheParty();
				cacheparty.setPhoneNumber(party.getPhoneNumber().getPhoneNumber());
				cacheparty.setConnectState(party.getConnectState());
				cacheparty.setPartyId(party.getPartyID());
				cacheparty.setPartyRole(party.getRole());
			}
			cacheConf.setPartyList(partyList);
			RedisService.putCache(billingCode, cacheConf);
		}else{
			CahcheConference cacheConf = new CahcheConference();
			List<CacheParty> partyList = Lists.newArrayList();
			cacheConf.setBillingcode(billingCode);
			for(IParty party : cacheConference.getPartyList()){
				CacheParty cacheparty = new CacheParty();
				cacheparty.setPhoneNumber(party.getPhoneNumber().getPhoneNumber());
				cacheparty.setConnectState(party.getConnectState());
				cacheparty.setPartyId(party.getPartyID());
				cacheparty.setPartyRole(party.getRole());
			}
			cacheConf.setPartyList(partyList);
			RedisService.putCache(billingCode, cacheConf);
		}
	}

	public static void updateRecordStatus(String billingcode,CahcheConference cacheConf){
		RedisService.putCache(billingcode, cacheConf);
	}
}

package com.deal.controller.conference;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.deal.dao.login.UserInfoDao;
import com.deal.entity.create.ConferenceInfo;
import com.deal.entity.create.ConferenceInfoForm;
import com.deal.entity.create.ConferenceMonitor;
import com.deal.entity.create.CustomerInfo;
import com.deal.entity.login.UserAllInfo;
import com.deal.entity.login.UserInfo;
import com.deal.entity.party.PartyInfo;
import com.deal.monitor.handler.IConfMonitorService;
import com.deal.service.conference.IConfCustomerService;
import com.deal.service.conference.IConfInfoDetail;
import com.deal.service.create.IConferenceService;
import com.deal.util.Consts;
import com.google.common.collect.Lists;

@Controller
public class BossConfereceMonitorController{
	private static Logger logger = LoggerFactory.getLogger(BossConfereceMonitorController.class);
	@Autowired
	private IConferenceService conferenceService;
	@Autowired
	private IConfCustomerService confPartyService;
	@Autowired
	private IConfMonitorService confMonitorService;
	@Autowired
	private IConfInfoDetail confInfoDetailServie;
	@Autowired
	private UserInfoDao userInfoDao;
    @Value("${deal.refresh.timeout}")
    private String refreshTimeout;
    @Value("${deal.boss.userid}")
    private String boss_userid;

	/**
	 * 查看会议监控
	 */
	@RequestMapping(value = "boss/getConfDetail", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView confMon(@RequestParam("billingcode") String billingcode,@RequestParam("empid") String empid, HttpServletRequest request){
		if(billingcode == null) {
			logger.error("billingcode is null");
			return null;
		}
		// 根据会议Id查询会议实体
		ConferenceInfo confInfo = conferenceService.getConfByBillingCode(billingcode);
		if(confInfo == null) {
			logger.error("confInfo is null billingcode {}", billingcode);
			return null;
		}
		//客服通过boss访问主动服务会议监控
		logger.info("客服通过boss访问主动服务会议的监控页面，操作人empid="+empid + ",操作账号billingcode="+billingcode);
		//将客服登录账号存入session
		UserAllInfo userAllInfo = new UserAllInfo();
		UserInfo userInfo = userInfoDao.getValidUserByUmsId(boss_userid);
		if(userInfo !=null){
			userAllInfo.setUserId(userInfo.getUserId());
			userAllInfo.setSiteId(userInfo.getSiteInfo().getSiteId());
			userAllInfo.setUserCustomerCode(userInfo.getUserCustomerCode());
			userAllInfo.setCompanyName(userInfo.getSiteInfo().getCompanyName());
			userAllInfo.setUserBillingcode(userInfo.getUserBillingCode());
			userAllInfo.setUserStatus(userInfo.getUserStatus());
			userAllInfo.setCreateTime(userInfo.getCreateTime());
			userAllInfo.setBridgeName(userInfo.getBridgeName());
			userAllInfo.setPcIp(userInfo.getPcIp());
			userAllInfo.setPcode1InTone(userInfo.getPcode1InTone());
			userAllInfo.setPcode1OutTone(userInfo.getPcode1OutTone());
			userAllInfo.setPcode2Mode(userInfo.getPcode2Mode());
			userAllInfo.setPcode2InTone(userInfo.getPcode2InTone());
			userAllInfo.setPcode2OutTone(userInfo.getPcode2OutTone());
			userAllInfo.setSiteInfo(userInfo.getSiteInfo());
			userAllInfo.setUserDisplayName("客服");
			userAllInfo.setUserEmail("service@quanshi.com");
			userAllInfo.setUserMobileNumber("");
			userAllInfo.setCountryCode("");
			userAllInfo.setAreaCode("");
			userAllInfo.setOutDate(Integer.parseInt(refreshTimeout));
		}
        request.getSession().setAttribute(Consts.SESSION_USERALLINFO, userAllInfo);
        
		
		String confId = String.valueOf(confInfo.getConfId()); 
		ConferenceInfoForm confInfoForm = new ConferenceInfoForm();
		BeanUtils.copyProperties(confInfo, confInfoForm);
		confInfoForm.setConfId(String.valueOf(confInfo.getConfId()));
		List<PartyInfo> finPartyList = Lists.newArrayList();
		// 根据会议Id 查询通讯录中的参会人
		List<CustomerInfo> custList = confPartyService.getPartyInfo(confId);
		for(CustomerInfo custInfo : custList){
			PartyInfo partyForm = new PartyInfo();
			String phone = confInfoDetailServie.getAcmPhone(custInfo);
			Integer status = confMonitorService.isOnline(confInfo.getConfBillingcode(), phone);
			partyForm.setPartyStatus(status);
			partyForm.setPartyName(custInfo.getCustName());
			partyForm.setPartyPhone(custInfo.getCustTel());
			partyForm.setPartyRole(custInfo.getCustType());
			finPartyList.add(partyForm);
		}
		// 同步缓存中的参会人和实际添加的参会人 是否相同
		ConferenceMonitor confMoniter = new ConferenceMonitor();
		confMoniter.setPartyInfo(finPartyList);
		confMoniter.setConference(confInfoForm);
		// 如果会议正在进行
		return new ModelAndView("conference/confDetailEdit", "confMon", confMoniter);
	}
}

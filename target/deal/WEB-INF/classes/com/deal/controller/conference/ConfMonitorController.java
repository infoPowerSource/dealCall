package com.deal.controller.conference;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.deal.entity.conference.ConferenceDTO;
import com.deal.entity.create.ConferenceInfo;
import com.deal.entity.create.ConferenceInfoForm;
import com.deal.entity.create.ConferenceMonitor;
import com.deal.entity.create.CustomerInfo;
import com.deal.entity.login.UserAllInfo;
import com.deal.entity.party.PartyInfo;
import com.deal.monitor.handler.IConfMonitorService;
import com.deal.service.conference.IConfCustomerService;
import com.deal.service.conference.IConfInfoDetail;
import com.deal.service.create.IConferenceService;
import com.deal.util.Consts;
import com.deal.util.DateFormatUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

@Controller
public class ConfMonitorController{
	private static Logger logger = LoggerFactory.getLogger(ConfMonitorController.class);
	@Autowired
	private IConferenceService conferenceService;
	@Autowired
	private IConfCustomerService confPartyService;
	@Autowired
	private IConfMonitorService confMonitorService;
	@Autowired
	private IConfInfoDetail confInfoDetailServie;

	/**
	 * 获取session用户信息
	 * 
	 * @return
	 */
	public UserAllInfo getSession(){
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();
		return (UserAllInfo) request.getSession().getAttribute(Consts.SESSION_USERALLINFO);

	}

	/**
	 * 查看会议监控
	 */
	@RequestMapping(value = "/app/conf/confDetail/{confId}", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView confAppDeail(@PathVariable String confId, ModelMap model){
		if(confId == null) {
			logger.error("confId is null");
			return null;
		}
		// 根据会议Id查询会议实体
		ConferenceInfo confInfo = conferenceService.getConfInfoById(confId);
		if(confInfo == null) {
			logger.error("confInfo is null confId {}", confId);
			return null;
		}

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
		
		String confTime = DateFormatUtil.DateToStr(confInfo.getBeginTime(), "yyyy-MM-dd");
		String timeBegin = DateFormatUtil.DateToStr(confInfo.getBeginTime(), "HH:mm");
		String timeEnd = DateFormatUtil.DateToStr(confInfo.getEndTime(), "HH:mm");
		String orderTime = confTime +" "+ timeBegin + "-" + timeEnd;
		confMoniter.setConfTime(orderTime);
		// 如果会议正在进行
		return new ModelAndView("conference/confDetail", "confMon", confMoniter);
	}
	

	/**
	 * 查看会议监控
	 */
	@RequestMapping("/app/conf/confRefresh")
	@ResponseBody
	public ConferenceMonitor appConfRefresh(String confId){
		if(confId == null) {
			logger.error("confId is null");
			return null;
		}

		// 根据会议Id查询会议实体
		ConferenceInfo confInfo = conferenceService.getConfInfoById(confId);
		if(confInfo == null) {
			logger.error("confInfo is null confId {}", confId);
			return null;
		}
		
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
		ConferenceMonitor confMoniter = new ConferenceMonitor();
		confMoniter.setPartyInfo(finPartyList);
		confMoniter.setConference(confInfoForm);

		String confTime = DateFormatUtil.DateToStr(confInfo.getBeginTime(), "yyyy-MM-dd");
		String timeBegin = DateFormatUtil.DateToStr(confInfo.getBeginTime(), "HH:mm");
		String timeEnd = DateFormatUtil.DateToStr(confInfo.getEndTime(), "HH:mm");
		String orderTime = timeBegin + "-" + timeEnd;
		confMoniter.setConfTime(orderTime);
		
		return confMoniter;
	}
	
	/**
	 * 查看会议监控
	 */
	@RequestMapping(value = "conf/confMon/{confId}", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView confMon(@PathVariable String confId, ModelMap model){
		if(confId == null) {
			logger.error("confId is null");
			return null;
		}
		// 根据会议Id查询会议实体
		ConferenceInfo confInfo = conferenceService.getConfInfoById(confId);
		if(confInfo == null) {
			logger.error("confInfo is null confId {}", confId);
			return null;
		}
		/**
		 * 进入监控时激活会中休息的会
		if(confInfo.getConfStatus() == 4) {
//			if(confMonitorService.enterMoniter(confInfo.getConfBillingcode()) != 0) {
//				logger.error("会中休息会议激活失败 ，会议ID{}", confId);
//				return null;
//			}
			if(!confMonitorService.isSubscribeAndActiveConf(confInfo.getConfBillingcode())) {
				logger.error("会中休息会议激活失败 ，会议ID{}", confId);
				return null;			
			}
		}
		
		 */
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

	/**
	 * 查看会议监控
	 */
	@RequestMapping("/conf/confMonRefresh")
	@ResponseBody
	public ConferenceMonitor confMonRefresh(String confId){
		if(confId == null) {
			logger.error("confId is null");
			return null;
		}

		// 根据会议Id查询会议实体
		ConferenceInfo confInfo = conferenceService.getConfInfoById(confId);
		if(confInfo == null) {
			logger.error("confInfo is null confId {}", confId);
			return null;
		}
		/**
		 * 进入监控时激活会中休息的会
		 */
		/*if(confInfo.getConfStatus() == 4) {
			if(confMonitorService.enterMoniter(confInfo.getConfBillingcode()) != 0) {
				logger.error("会中休息会议激活失败 ，会议ID{}", confId);
				return null;
			}
		}*/
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
		ConferenceMonitor confMoniter = new ConferenceMonitor();
		confMoniter.setPartyInfo(finPartyList);
		confMoniter.setConference(confInfoForm);
		return confMoniter;
	}
	

	/**
	 * 查看会议中是否有人
	 */
	@RequestMapping("/conf/isAllOffLine")
	@ResponseBody
	public ConferenceMonitor getIsAllOffLine(String confId){
		if(confId == null) {
			logger.error("confId is null");
			return null;
		}
		// 根据会议Id查询会议实体
		ConferenceInfo confInfo = conferenceService.getConfInfoById(confId);
		if(confInfo == null) {
			logger.error("confInfo is null confId {}", confId);
			return null;
		}
		Integer isAllOffLine = 1;
		// 根据会议Id 查询通讯录中的参会人
		List<CustomerInfo> custList = confPartyService.getPartyInfo(confId);
		for(CustomerInfo custInfo : custList){
			String phone = confInfoDetailServie.getAcmPhone(custInfo);
			Integer status = confMonitorService.isOnline(confInfo.getConfBillingcode(), phone);
			if(status == 0) {
				isAllOffLine = 0;
			}
		}
		ConferenceMonitor confMoniter = new ConferenceMonitor();
		return confMoniter;
	}

	/**
	 * 监控端外呼其他号码
	 * 
	 * @param phone
	 * @param name
	 * @param role
	 * @param billingCode
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "conf/callout/", method = RequestMethod.POST)
	@ResponseBody
	public Integer callout(String phone, String name, String role, String billingCode, String confId){
		try{
			logger.info("监控端外呼开始, phone is " + phone + " role is " + role + " billingCode is " + billingCode);
			if(phone == null || role == null || billingCode == null) {
				// 信息校验失败
				logger.error("监控端外呼失败");
				return 1;
			}
			List<CustomerInfo> custList = confPartyService.getCustList(phone, Integer.valueOf(role), Long.valueOf(confId));
			if(custList == null || custList.size() == 0) {
				logger.error("外呼失败，此参会人不存在通讯录");
				return 1;
			}
			// 获取该用户所在平台
			String summit = getSession().getBridgeName();
			if(!confMonitorService.control_callout(summit, billingCode, custList)) {
				logger.error("监控端外呼失败");
				return 1;
			}
			Integer status = confMonitorService.isOnline(billingCode, phone);
			if(status != 0) {
				logger.info("正在外呼，请稍候");
				return 2;
			}
		}catch (Exception e){
			logger.error("监控端外呼失败");
			e.printStackTrace();
			return 1;
		}
		return 0;
	}

	/**
	 * 监控端外呼其他号码
	 * 
	 * @param phone
	 * @param name
	 * @param role
	 * @param billingCode
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "conf/calloutOther/", method = RequestMethod.POST)
	@ResponseBody
	public Integer calloutOther(String phone, String name, String role, String billingCode, String confId, String country, String area){
		try{
			logger.info("监控端外呼其他号码开始, phone is" + phone + " name is " + name + " role is " + role + " billingCode is " + billingCode);
			if(phone == null || name == null || role == null || billingCode == null) {
				// 信息校验失败
				logger.error("监控端外呼失败，phone " + phone);
				return 1;
			}
			// 获取该用户所在平台
			String summit = getSession().getBridgeName();
			ConferenceInfo conferenceInfo = new ConferenceInfo();
			List<CustomerInfo> partyList = Lists.newArrayList();
			conferenceInfo.setConfId(Long.valueOf(confId));
			String username = name;
			CustomerInfo customer = new CustomerInfo();
			customer.setCustTel(phone);
			customer.setCustContryCode(country);
			customer.setCustAreacode(area);
			customer.setCustEmail("");
			customer.setCustName(username);
			customer.setCustType(Integer.valueOf(role));
			customer.setConfInfo(conferenceInfo);
			partyList.add(customer);
			// 根据手机号去通讯录查询，判断是否是 通讯录中的 参会人，如果没有则 根据角色 进行相应添加
			if(!confPartyService.addParty(customer)) {
				logger.error("外呼号码重复，phone " + phone);
				return 3;
			}
			if(!confMonitorService.control_callout(summit, billingCode, partyList)) {
				logger.error("监控端外呼失败，phone " + phone);
				return 1;
			}
			Integer status = confMonitorService.isOnline(billingCode, phone);
			if(status != 0) {
				logger.info("正在外呼，请稍候，phone " + phone);
				return 2;
			}
		}catch (Exception e){
			logger.error("监控端外呼失败，phone " + phone);
			e.printStackTrace();
			return 1;
		}
		return 0;
	}

	/**
	 * 监控端外呼其他号码
	 * 
	 * @param phone
	 * @param name
	 * @param role
	 * @param billingCode
	 * @param model
	 * @return
	 */
	@RequestMapping("/app/conf/callout")
	@ResponseBody
	public Integer appCallout(HttpServletRequest request){
		try{
			String phone = request.getParameter("phone");
			String role = request.getParameter("role");
			String billingCode = request.getParameter("billingCode");
			String name = request.getParameter("name");
			String confId = request.getParameter("confId");
			logger.info("监控端外呼开始, phone is " + phone + " role is " + role + " billingCode is " + billingCode);
			if(phone == null || role == null || billingCode == null) {
				// 信息校验失败
				logger.error("监控端外呼失败");
				return 1;
			}
			List<CustomerInfo> custList = confPartyService.getCustList(phone, Integer.valueOf(role), Long.valueOf(confId));
			if(custList == null || custList.size() == 0) {
				logger.error("外呼失败，此参会人不存在通讯录");
				return 1;
			}
			// 获取该用户所在平台
			String summit = getSession().getBridgeName();
			if(!confMonitorService.control_callout(summit, billingCode, custList)) {
				logger.error("监控端外呼失败");
				return 1;
			}
			Integer status = confMonitorService.isOnline(billingCode, phone);
			if(status != 0) {
				logger.info("正在外呼，请稍候");
				return 2;
			}
		}catch (Exception e){
			logger.error("监控端外呼失败");
			e.printStackTrace();
			return 1;
		}
		return 0;
	}

	/**
	 * 监控端外呼其他号码
	 * 
	 * @param phone
	 * @param name
	 * @param role
	 * @param billingCode
	 * @param model
	 * @return
	 */
	@RequestMapping("/app/conf/calloutOther")
	@ResponseBody
	public Integer appCalloutOther(HttpServletRequest request){

		String phone= request.getParameter("phone");
		String name= request.getParameter("name");
		String role= request.getParameter("role");
		String billingCode= request.getParameter("billingCode");
		String confId= request.getParameter("confId");
		String country= request.getParameter("country");
		String area=request.getParameter("area");
		
		try{
			logger.info("监控端外呼其他号码开始, phone is" + phone + " name is " + name + " role is " + role + " billingCode is " + billingCode);
			if(phone == null || name == null || role == null || billingCode == null) {
				// 信息校验失败
				logger.error("监控端外呼失败，phone " + phone);
				return 1;
			}
			// 获取该用户所在平台
			String summit = getSession().getBridgeName();
			ConferenceInfo conferenceInfo = new ConferenceInfo();
			List<CustomerInfo> partyList = Lists.newArrayList();
			conferenceInfo.setConfId(Long.valueOf(confId));
			String username = name;
			CustomerInfo customer = new CustomerInfo();
			customer.setCustTel(phone);
			customer.setCustContryCode(country);
			customer.setCustAreacode(area);
			customer.setCustEmail("");
			customer.setCustName(username);
			customer.setCustType(Integer.valueOf(role));
			customer.setConfInfo(conferenceInfo);
			partyList.add(customer);

			// 根据姓名去通讯录查询，判断是否是 通讯录中的 参会人
			if(!confPartyService.checkParty(customer)) {
				logger.error("外呼姓名重复，name " + username);
				return 4;
			}
			// 根据手机号去通讯录查询，判断是否是 通讯录中的 参会人，如果没有则 根据角色 进行相应添加
			if(!confPartyService.addParty(customer)) {
				logger.error("外呼号码重复，phone " + phone);
				return 3;
			}
			if(!confMonitorService.control_callout(summit, billingCode, partyList)) {
				logger.error("监控端外呼失败，phone " + phone);
				return 1;
			}
			Integer status = confMonitorService.isOnline(billingCode, phone);
			if(status != 0) {
				logger.info("正在外呼，请稍候，phone " + phone);
				return 2;
			}
		}catch (Exception e){
			logger.error("监控端外呼失败，phone " + phone);
			e.printStackTrace();
			return 1;
		}
		return 0;
	}

    @RequestMapping(value = "app/conf/confRecord/{confId}", method = RequestMethod.GET)
	@ResponseBody
    public ModelAndView getConfRecord(@PathVariable String confId){
		ConferenceInfo confInfo = conferenceService.getConfInfoById(confId);
        return new ModelAndView("conference/sendConfRecord", "confInfo", confInfo);

    }

    @RequestMapping(value = "/app/conf/confReport", method = RequestMethod.GET)
	@ResponseBody
    public ModelAndView getConfReport(){
        
        return new ModelAndView("conference/confReport");

    }
    
    @RequestMapping(value = "/app/conf/callOtherPage/{confId}/{billingcode}", method = RequestMethod.GET)
	@ResponseBody
    public ModelAndView getCallOtherPage(@PathVariable String confId,@PathVariable String billingcode){
    
    	ConferenceDTO confDto = new ConferenceDTO();
    	confDto.setBillingcode(billingcode);
    	confDto.setConfId(confId);
        return new ModelAndView("/conference/callOtherNum", "confDto", confDto);
    }
    
}

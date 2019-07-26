package com.deal.controller.create;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.deal.entity.conference.ConferenceCountryCode;
import com.deal.entity.create.ConferenceInfo;
import com.deal.entity.create.ConferenceInfoForm;
import com.deal.entity.create.CustomerInfo;
import com.deal.entity.login.UserAllInfo;
import com.deal.service.create.IConferenceService;
import com.deal.util.Consts;
import com.deal.util.DateFormatUtil;
import com.google.common.collect.Maps;

@Controller
public class ConferenceController{
	private static Logger logger = LoggerFactory.getLogger(ConferenceController.class);

	@Autowired
	public IConferenceService confService;

	@RequestMapping("/create/addConference")
	@ResponseBody
	public Map<String, Object> createConference(ConferenceInfoForm confForm, HttpServletRequest request){
		Map<String, Object> result = Maps.newHashMap();
		if(StringUtils.isEmpty(confForm.getNotifyemail())){
			confForm.setNotifyemail("0");
		}
		if(StringUtils.isEmpty(confForm.getNotifysms())){
			confForm.setNotifysms("0");
		}
		try{
			UserAllInfo userInfo = (UserAllInfo) request.getSession().getAttribute("userAllInfo");
			confService.addConferenceInfo(confForm, userInfo);
			result.put("success", "true");
			result.put("msg", "开通会议成功");
		}catch (Exception e){
			e.printStackTrace();
			result.put("success", "false");
			result.put("msg", "开通会议失败,错误信息：" + e.getMessage());
			logger.error("开通会议失败,失败原因:" + e.getMessage());
		}
		return result;
	}

	@RequestMapping("app/create/addConference")
	@ResponseBody
	public Map<String, Object> createAppConference(HttpServletRequest request){
		Map<String, Object> result = Maps.newHashMap();
		try{
			UserAllInfo userInfo = (UserAllInfo) request.getSession().getAttribute("userAllInfo");
			String data = request.getParameter("data");
			String confId = request.getParameter("confId");
			confService.addAppConferenceInfo(data, userInfo, confId);
			result.put("success", "true");
			result.put("msg", "开通会议成功");
		}catch (Exception e){
			e.printStackTrace();
			result.put("success", "false");
			result.put("msg", "开通会议失败,错误信息：" + e.getMessage());
			logger.error("开通会议失败,失败原因:" + e.getMessage());
		}
		return result;
	}

	@RequestMapping("/create/createConference")
	public String addConference(){
		return "/create/createConf";
	}

	@RequestMapping("/app/create/createConference")
	public String createApp(ModelMap model){
		return "/create/createConf";
	}

	@RequestMapping(value = "/create/modifyConference/{confId}", method = RequestMethod.GET)
	public String modifyConference(@PathVariable String confId, ModelMap model){
		ConferenceInfo confinfo = confService.getConfInfoById(confId);
		Map<Integer, List<CustomerInfo>> custMap = confService.getAllCustTypeList(confId);
		model.addAttribute("confInfo", confinfo);
		model.addAttribute("experList", custMap.get(Consts.IS_GUEST));
		model.addAttribute("partyList", custMap.get(Consts.IS_HOST));
		// model.addAttribute("confBegintime",DateFormatUtil.timestamps2StringWithoutSecond(confinfo.getBeginTime()));
		model.addAttribute("confBegintime", confinfo.getBeginTime());
		model.addAttribute("confduration", DateFormatUtil.minutes2Data(confinfo.getConfDuration()));
		return "/create/modifyConf";
	}

	@RequestMapping(value = "/create/deleteConference/{confId}", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> deleteConference(@PathVariable String confId){
		Map<String, Object> result = Maps.newHashMap();
		try{
			confService.delConferenceInfo(confId);
			result.put("success", "true");
			result.put("msg", "删除会议成功");
		}catch (Exception e){
			e.printStackTrace();
			result.put("success", "false");
			result.put("msg", "开通会议失败");
			logger.error("删除会议失败,失败原因:" + e);
		}
		return result;
	}

	@RequestMapping("/create/delBillingCode")
	public String deleteBilingCode(){
		try{
			confService.deleteBillingCode();
		}catch (Exception e){
			logger.error("删除billingCode和passcode失败");
		}
		return "SUCCESS";
	}

	@GetMapping({"/create/getConferenceData/{confId}","${app.mobile.path}/create/getConferenceData/{confId}"})
	@ResponseBody
	public ConferenceInfo getCondenceData(@PathVariable String confId){
		return confService.getConfInfoById(confId);
	}

	@RequestMapping("/create/updateConference")
	@ResponseBody
	public Map<String, Object> updateConference(ConferenceInfoForm confForm, HttpServletRequest request){
		Map<String, Object> result = Maps.newHashMap();
        if(StringUtils.isEmpty(confForm.getNotifyemail())){
            confForm.setNotifyemail("0");
        }
        if(StringUtils.isEmpty(confForm.getNotifysms())){
            confForm.setNotifysms("0");
        }
		try{
			UserAllInfo userInfo = (UserAllInfo) request.getSession().getAttribute("userAllInfo");
			confService.updateConference(confForm, userInfo);
			result.put("success", "true");
			result.put("msg", "更新会议成功");
		}catch (Exception e){
			e.printStackTrace();
			result.put("success", "false");
			result.put("msg", "更新会议信息失败,错误信息：" + e.getMessage());
			logger.error("更新会议失败");
		}
		return result;
	}

	@RequestMapping(value = "/app/create/deleteConference/{confId}", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> appDeleteConference(@PathVariable String confId){
		Map<String, Object> result = Maps.newHashMap();
		try{
			confService.delConferenceInfo(confId);
			result.put("success", "true");
			result.put("msg", "取消会议成功");
		}catch (Exception e){
			e.printStackTrace();
			result.put("success", "false");
			result.put("msg", "取消会议失败");
			logger.error("删除会议失败,失败原因:" + e);
		}
		return result;
	}

	/**
	 * 查询国家码列表
	 *
	 * @return
	 */
	@RequestMapping("/create/countrycode")
	@ResponseBody
	public List<ConferenceCountryCode> getCountryList(){

		return confService.getCountryCode();
	}
	/**
	 * 查询国家码列表
	 *
	 * @return
	 */
	@RequestMapping("/app/create/countrycode")
	@ResponseBody
	public List<ConferenceCountryCode> getAppCountryList(){

		return confService.getCountryCode();
	}
}

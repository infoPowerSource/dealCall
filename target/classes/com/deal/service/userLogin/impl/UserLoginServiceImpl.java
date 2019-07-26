package com.deal.service.userLogin.impl;

import java.net.URL;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.plexus.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deal.dao.login.UserInfoDao;
import com.deal.dao.login.syncboss.UserSiteDao;
import com.deal.entity.login.CheckFindBackNameResult;
import com.deal.entity.login.UmsResult;
import com.deal.entity.login.UserAllInfo;
import com.deal.entity.login.UserInfo;
import com.deal.entity.login.UserLoginInfo;
import com.deal.entity.ums.UmsResponseDTO;
import com.deal.monitor.cache.RedisService;
import com.deal.service.mss.IMSSService;
import com.deal.service.sms.ISMSService;
import com.deal.service.userLogin.IUserLoginEmailVerifyService;
import com.deal.service.userLogin.IUserLoginService;
import com.deal.util.Consts;
import com.deal.util.RandomUtil;
import com.deal.util.UmsClientUtil;

@Service
public class UserLoginServiceImpl implements IUserLoginService{
	private static Logger logger = LoggerFactory.getLogger(UserLoginServiceImpl.class);

	@Autowired
	UmsClientUtil httpClient;

	@Resource
	private HttpServletRequest request;

	@Resource
	private HttpServletResponse response;

	@Autowired
	private ISMSService iSMSService;

	@Autowired
	IMSSService iMSSService;

	@Autowired
	IUserLoginEmailVerifyService IEmailVerifyService;

	@Autowired
	private UserInfoDao userInfoDao;

	@Autowired
	private UserSiteDao UserSiteDao;

	/* 构造完整UserAllInfo，放入session中 */
	@Override
	@SuppressWarnings("rawtypes")
	public UserAllInfo getUserAllInfo(UmsResponseDTO res){
		Object x = ((java.util.LinkedHashMap) res.getRetObj()).get("userInfo");
		x = ((java.util.LinkedHashMap) x).get("user");
		if(null == x){
			logger.info("UMS登录结果没有user");
			return null;
		}
		String umsiIdStr = String.valueOf(((java.util.LinkedHashMap) x).get("id"));
		if(null == umsiIdStr || "".equals(umsiIdStr)){
			logger.info("UMS登录结果没有umsiId");
			return null;
		}
		UserInfo userInfo = userInfoDao.getValidUserByUmsId(umsiIdStr);
		if(null == userInfo){
			logger.info("未匹配到UserInfo,umsiId:" + umsiIdStr);
			return null;
		}
		UserAllInfo r = new UserAllInfo();
		r.setUserId(userInfo.getUserId());
		r.setSiteId(userInfo.getSiteInfo().getSiteId());
		r.setUserCustomerCode(userInfo.getUserCustomerCode());
		r.setCompanyName(userInfo.getSiteInfo().getCompanyName());
		r.setUserBillingcode(userInfo.getUserBillingCode());
		r.setUserStatus(userInfo.getUserStatus());
		r.setCreateTime(userInfo.getCreateTime());
		r.setBridgeName(userInfo.getBridgeName());
		r.setPcIp(userInfo.getPcIp());
		//@propType = 2
		r.setPcode1InTone(userInfo.getPcode1InTone());
		r.setPcode1OutTone(userInfo.getPcode1OutTone());
		r.setPcode2Mode(userInfo.getPcode2Mode());
		r.setPcode2InTone(userInfo.getPcode2InTone());
		r.setPcode2OutTone(userInfo.getPcode2OutTone());

		String displayName = (String) ((java.util.LinkedHashMap) x).get("displayName");
		String userEmail = (String) ((java.util.LinkedHashMap) x).get("email");
		String mobileNumber = (String) ((java.util.LinkedHashMap) x).get("mobileNumber");
		String countryCode = (String) ((java.util.LinkedHashMap) x).get("countryCode");
		String areaCode = (String) ((java.util.LinkedHashMap) x).get("cityCode");
		int userStatus = (int) ((java.util.LinkedHashMap) x).get("userstatus");
		if(1 != userStatus){
			logger.info("Ums状态不可用,userStatus:" + userStatus);
			return null;
		}
		r.setUserDisplayName(displayName);
		r.setUserEmail(userEmail);
		r.setUserMobileNumber(mobileNumber);
		r.setCountryCode(countryCode);
		r.setAreaCode(areaCode);

		r.setSiteInfo(userInfo.getSiteInfo());
		return r;
	}

	@Override
	public void updateSession(HttpServletRequest request, String userIdStr){
		if(RedisService.IsExistOper(userIdStr+"Login", true)){
			//存在userid的登录信息，表明重复登录，踢出原来的登录
			RedisService.putOperToCache(userIdStr + Consts.SESSION_RELOGIN,true);
			logger.info(userIdStr + Consts.SESSION_RELOGIN + " sessionId:" + request.getSession().getId());
		}else{
			//第一个登录者，将userid登录信息保存到redis中，并且保存sessionid
			RedisService.putOperToCache(userIdStr+"Login", true);
			logger.info(userIdStr+"Login" + " sessionId:" + request.getSession().getId());
		}
		/*
		ServletContext application = request.getSession().getServletContext();
		Map<String, HttpSession> service = (Map<String, HttpSession>) application.getAttribute("userSessions");
		if(null == service){
			service = new HashMap<String,HttpSession>();
			application.setAttribute("userSessions", service);
		}
		//登录成功之后将用户插入到session中 
		String userIdStr = String.valueOf(userAllInfo.getUserId());
		HttpSession userHttpSession = service.get(userIdStr);
		if(null != userHttpSession){ 
		   //如果该session在此之前已经存在，则将该用户进行退出操作 
			 userSessionClear(service , userIdStr); 
		} 
		//将新的session存放到map<username,session>中 
		service.put(userIdStr, request.getSession());*/
	}

	private void userSessionClear(Map<String, HttpSession> service, String userIdStr){
		    //得到需要退出的用户的session 
		    HttpSession session = service.get(userIdStr); 
		    //在map<username,session>中移除该用户，记住想要退出该用户，必须将该session废除或是remove掉user 
		    service.remove(userIdStr); 
		    //得到session的所属性合集 
		    Enumeration e = session.getAttributeNames(); 
		    //删除所有属性 
		    while(e.hasMoreElements()){ 
		      String sessionName = (String) e.nextElement(); 
		      session.removeAttribute(sessionName); 
		    } 
		    logger.info("relogin"+",id:"+request.getSession().getId());
		    session.setAttribute(Consts.SESSION_RELOGIN, "true");
	}

	/**
	 * 获取客户端IP地址
	 * 
	 * @param request
	 * @return
	 */
	public String getIpAddr(HttpServletRequest request) {

		String ip = request.getHeader("x-forwarded-for");

		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {

			ip = request.getHeader("Proxy-Client-IP");

		}

		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {

			ip = request.getHeader("WL-Proxy-Client-IP");

		}

		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {

			ip = request.getRemoteAddr();

		}

		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {

			ip = request.getHeader("http_client_ip");

		}

		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {

			ip = request.getHeader("HTTP_X_FORWARDED_FOR");

		}

		// 如果是多级代理，那么取第一个ip为客户ip

		if (ip != null && ip.indexOf(",") != -1) {

			ip = ip.substring(ip.lastIndexOf(",") + 1, ip.length()).trim();

		}

		return ip;

	}
	
	@Override
	public UmsResult updatePass(UserLoginInfo user, HttpSession session){
		UmsResult umsResult = new UmsResult();
		try{
			// session 取umsid
			String umsid = "";
			UserAllInfo userAllInfo = (UserAllInfo) session.getAttribute(Consts.SESSION_USERALLINFO);
			if(null == userAllInfo){
				logger.warn("Session_userAllInfo is null");
				umsResult.setResult(false);
				return umsResult;
			}else{
				umsid = String.valueOf(userAllInfo.getUserId());
			}
			if(null == umsid || "".equals(umsid)){
				logger.warn("Session_userAllInfo_umsid is null");
				umsResult.setResult(false);
				return umsResult;
			}
			String oldPassword = user.getOldPassword();
			String password = user.getPassword();
			logger.info("umsid:" + umsid + ",Password_old:" + oldPassword + ",Password_new:" + password);
			
			umsResult = httpClient.updatePasswordWithOld(umsid, oldPassword, password);
		}catch (Exception e){
			logger.error("updatePass error:", e);
			umsResult.setResult(false);
			return umsResult;
		}
		return umsResult;
	}

	//-1:用户不存在；0：验证失败；1：成功；2：请求超时，请重试
	@Override
	public int checkEmailVer(UserLoginInfo user){
		String umsId = user.getId();
		String inputVerifycode = user.getCheckcode();
		UserInfo userInfo = userInfoDao.getValidUserByUmsId(umsId);
		if(null == userInfo){
			logger.info("未匹配到UserInfo,umsiId:" + umsId);
			return -1;
		}
		String dbValue = userInfo.getEmailVerify();
		logger.info("check验证码 ,inputVerifycode:" + inputVerifycode + ",dbValue:" + dbValue);
		if(StringUtils.isEmpty(inputVerifycode) || StringUtils.isEmpty(dbValue)){
			return 0;
		}else{
			if(inputVerifycode.equals(dbValue)){
				if(IEmailVerifyService.isvalidTime(userInfo.getEmailCheckTime())){
					logger.info("邮箱验证码验证成功");
					return 1;
				}else{
					logger.info("邮箱验证码验证超时");
					return 2; //超时
				}
			}
		}
		return 0;
	}

	@Override
	public boolean resetPass(UserLoginInfo user, HttpSession session){ // session
																		// 取umsid
		String umsid = user.getId();
		String password = user.getPassword();
		logger.info("umsid:" + umsid + ",Password:" + password);
		String result = httpClient.updatePasswordNoWithOld(umsid, password);
		if(result.equals("")){
			return false;
		}
		logger.info("userId:" + result);
		if("".equals(result) || "false".equals(result)){
			return false;
		}else{
			logger.info("updatePass is success ,result:" + result);
		}
		return true;
	}

	// type 0:邮箱；1：手机号；2：未匹配到UMSID 3:匹配失败
	@Override
	public CheckFindBackNameResult checkFindBackName(String name, HttpSession session){
		CheckFindBackNameResult result = new CheckFindBackNameResult();
		try{
			Object r = null;
			if(name.indexOf("@") != -1){
				r = httpClient.getUmsIdByMail(name);
				if(null == r || "".equals(r)){
					result.setType("2");
					return result;
				}
				UserLoginInfo uli = getUmsId(r);
				result.setType("0");
				result.setUmsId(uli.getId());
				result.setDisplayName(uli.getUsername());
				return result;
			}else{
				r = httpClient.getUmsIdByTel(name);
				if(null == r || "".equals(r)){
					result.setType("2");
					return result;
				}
				UserLoginInfo uli = getUmsId(r);
				result.setType("1");
				result.setUmsId(uli.getId());
				return result;
			}
		}catch (Exception e){
			logger.error("通过手机/邮箱匹配UMSID异常", e);
			result.setType("3");
			return result;
		}
	}

	@SuppressWarnings("rawtypes")
	public UserLoginInfo getUmsId(Object str){
		Object id = ((java.util.LinkedHashMap) str).get("id");
		Object displayName = ((java.util.LinkedHashMap) str).get("displayName");
		UserLoginInfo uli = new UserLoginInfo();
		uli.setId(String.valueOf(id));
		uli.setUsername(String.valueOf(displayName));
		logger.info("通过手机/邮箱匹配UMSID:" + uli.getId() + ",displayName:" + uli.getUsername() + ",str:" + str);
		return uli;
	}

	public static void main(String[] args){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String createdTimeStr = sdf.format(new Date());
		System.out.println(Timestamp.valueOf(createdTimeStr));;
	}

	@Override
	// -1:邮箱未注册；0：发送短信失败；1：短信发送成功
	public int saveVerifySMS(String name, String umsiId, HttpSession session){
		// 根据umsiId查找billingcode
		UserInfo userInfo = userInfoDao.getValidUserByUmsId(umsiId);
		if(null == userInfo){
			logger.info("未匹配到UserInfo,umsiId:" + umsiId);
			return -1;
		}
		String billingcode = userInfo.getUserBillingCode();
		String content = RandomUtil.generateString(4);
		userInfo.setTelVerify(content);
		userInfoDao.updateUser(userInfo);
		logger.info("sendVerifySMS name:" + name + ",content:" + content + ",billingcode:" + billingcode);
		int result = 0;
		try{
			result = iSMSService.sendVerifySMS(name, getSMSContent(content), billingcode);
		}catch (Exception e){
			logger.error("发送短信异常", e);
			return 0;
		}
		return result;
	}

	@Override
	public boolean checkFindBackTelPassVC(String inputVerifycode, HttpSession session, String umsId){
		/* check验证码 */
		// String inputValue = (String) session.getAttribute(type);
		UserInfo userInfo = userInfoDao.getValidUserByUmsId(umsId);
		if(null == userInfo){
			logger.info("未匹配到UserInfo,umsiId:" + umsId);
			return false;
		}
		String dbValue = userInfo.getTelVerify();
		logger.info("check验证码 ,inputVerifycode:" + inputVerifycode + ",dbValue:" + dbValue);
		if(inputVerifycode == null || "".equals(inputVerifycode)){
			return false;
		}else if(dbValue == null || "".equals(dbValue)){
			return false;
		}else{
			if(inputVerifycode.equalsIgnoreCase(dbValue)){
				return true;
			}
		}
		return false;
	}

	@Override
	// -1:邮箱未注册；0：发送邮件失败；1：邮件发送成功
	public int saveSendMail(String userEmail, CheckFindBackNameResult cfbnResult, HttpSession session){
		String umsId = cfbnResult.getUmsiId();
		String userDisplayName = cfbnResult.getDisplayName();

		if(StringUtils.isEmpty(userDisplayName) || "null".equals(userDisplayName)){
			userDisplayName = "";
		}
		Map<String, String> params = new HashMap<String, String>();
		// 根据umsiId查找billingcode
		UserInfo userInfo = userInfoDao.getValidUserByUmsId(umsId);
		if(null == userInfo){
			logger.info("未匹配到UserInfo,umsiId:" + umsId);
			return -1;
		}
        if(!StringUtils.isEmpty(userEmail)){
        	if(StringUtils.isEmpty(userInfo.getUserEmail())){
            	userInfo.setUserEmail(userEmail);
        	}
        } else {
        	userEmail = userInfo.getUserEmail();
        }
		String userEamilDisplayName = userEmail;
		logger.info("邮箱找回密码,发送邮件接口 ,userEmail:" + userEmail + ",umsiId:" + umsId + ",UserDisplayName:" + userDisplayName);
		try{
			params.put("userDisplayName", userEamilDisplayName);
			params.put("userEmail", userEmail);
			userInfo.setEmailDisplayName(userDisplayName);
			String emailVerify = saveGenerateEmailVerify(userInfo);
			String emailContent = "http://" + userInfo.getSiteInfo().getSiteUrl() + "/deal/login/resetPassword?id=" + umsId + "&verify=" + emailVerify;
			emailContent = getMailContent(userDisplayName,emailContent);
			logger.info("emailContent:" + emailContent);
			params.put("emailContent", emailContent);
			iMSSService.sendMailForChangePwd(params);
		}catch (Exception e){
			logger.error("发送邮件错误", e);
			return 0;
		}
		return 1;
	}

	@Override
	public UserInfo getUserInfo(String umsId){
		UserInfo userInfo = userInfoDao.getValidUserByUmsId(umsId);
		if(null == userInfo){
			logger.info("未匹配到UserInfo,umsiId:" + umsId);
			return null;
		}
		return userInfo;
	}

	@Override
	public String saveGenerateEmailVerify(UserInfo userInfo){
		String emailVerify = "";
		try{
			emailVerify = IEmailVerifyService.saveGenerateMd5(userInfo);
		}catch (Exception e1){
			logger.error("saveGenerateEmailVerify error:",e1);
		}
		return emailVerify;
	}

	/**
	 * 转换访问路径
	 * 
	 * @param request
	 * @param path
	 * @author tiezheng.zhang
	 * @return
	 */
	public static String convertUrlPath(HttpServletRequest request, String path){
		if(request == null){
			return null;
		}
		String header = request.getScheme();
		if(request.getHeader("X-Forwarded-Proto") != null){
			header = request.getHeader("X-Forwarded-Proto");// X-Forwarded-For
		}

		String portStr = String.valueOf(request.getServerPort());
		if(request.getHeader("X-Forwarded-Port") != null){
			portStr = request.getHeader("X-Forwarded-Port");
		}

		String basePath = header + "://" + request.getServerName() + ":" + portStr + path + "/";

		return basePath;
	}

	@Override
	public boolean executeCheckSiteResult(HttpServletRequest request, String url, UserAllInfo userAllInfo){
		try{
			String siteUrlDB = userAllInfo.getSiteInfo().getSiteUrl();
			long siteId = userAllInfo.getSiteInfo().getSiteId();
			String reqSiteUrl = convertUrlPath(request, ""); // +"deal"
			// 提取有效SiteUrl
			reqSiteUrl = getValidSiteUrl(request, reqSiteUrl);
			// 比对siteUrlDB与siteUrl
			if(!reqSiteUrl.startsWith(siteUrlDB)){
				logger.info("checkSite站点校验失败,reqSiteUrl:" + reqSiteUrl + ",siteUrlDB:" + siteUrlDB + ",UmsId:" + userAllInfo.getUserId());
				return false;
			}
			logger.info("checkSite站点校验成功,reqSiteUrl:" + reqSiteUrl + ",siteUrlDB:" + siteUrlDB + ",UmsId:" + userAllInfo.getUserId());
			// 通过siteId去UMS验证站点状态
			Object siteInfo = httpClient.getSiteById(String.valueOf(siteId));
			if(siteInfo.equals("")){
				return false;
			}
			logger.info("站点状态同步成功,siteId:" + siteId);
			int siteStatus = getSiteStatus(siteInfo);
			if(1 != siteStatus){// 1 可用 0 不可用
				logger.info("站点状态不可用,siteId:" + siteId);
				return false;
			}
		}catch (Exception e){
			logger.info("checkSite站点校验错误,", e);
			return false;
		}
		// return false;
		logger.info("checkSite站点校验成功");
		return true;
	}

	public int getSiteStatus(Object str){
		Object x = ((java.util.LinkedHashMap) str).get("siteStatus");
		logger.info("通过获取站点状态:" + x + ",str:" + str);
		return Integer.parseInt(String.valueOf(x));
	}

	//构造短信模板
	private String getSMSContent(String content){
		return "【全时会议】验证码" + content + "，请用于找回密码，如非本人操作，请忽略。 ";
	}
	
	//构造邮件模板
	private String getMailContent(String userDisplayName, String emailContent){

		return "<br/><br/><div class=\'text\' style=\' text-align:left;\'><B><font size=\"3\">" + userDisplayName + ",你好！</B></font></div>"
	            +"<br/>&nbsp;&nbsp;<div class=\'text\' style=\' text-align:left;\'><B><font size=\"4\">您正在使用邮箱找回密码，请点击以下链接重新设置登录密码</font></B></div>" 
	            +"<br/><div class=\'text\' style=\' text-align:left;\'><a href=" + emailContent + ">" + emailContent + "</a></div>"
	            +"<br/>"
	            +"<br/>"
				+"<hr><br/>&nbsp;&nbsp;为保障账号安全，请勿转寄此邮件"
				+"<br/>&nbsp;&nbsp;如果你遇到任何问题请拨打全时24小时服务热线400-810-1919";
	}
	
	private String getValidSiteUrl(HttpServletRequest request, String url){
		return deletePortToSiteUrl(request, url);
	}

	/**
	 * 将站点url去掉实际访问的端口
	 * 
	 * @param request
	 * @param site
	 * @author tao.li
	 * @return
	 */
	private String deletePortToSiteUrl(HttpServletRequest request, String site){
		if(request == null || site == null)
			return site;
		String siteUrl;
		if(site.indexOf("http://") == 0){
			site = site.substring(4 + 3);
		}

		if(site.indexOf("https://") == 0){
			site = site.substring(5 + 3);
		}

		URL r = null;
		try{
			r = new URL(new String(request.getRequestURL()));
		}catch (Exception e){
			e.printStackTrace();

		}
		if(site.indexOf(r.getAuthority()) > -1){
			siteUrl = site.replace(r.getAuthority(), r.getHost());
		}

		siteUrl = site.replace(":80/", "/");
		siteUrl = siteUrl.replace(":8080/", "/");
		siteUrl = siteUrl.replace(":443/", "/");
		return siteUrl;
	}
}

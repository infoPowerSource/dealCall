package com.deal.controller.userLogin;

import com.deal.entity.login.*;
import com.deal.entity.ums.UmsResponseDTO;
import com.deal.entity.ums.UmsUserDTO;
import com.deal.monitor.cache.RedisService;
import com.deal.service.userLogin.IUserLoginService;
import com.deal.util.Consts;
import com.deal.util.UmsClientUtil;
import com.google.common.collect.Maps;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.mobile.device.Device;
import org.springframework.mobile.device.DeviceType;
import org.springframework.mobile.device.DeviceUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

@Controller
@RequestMapping({"","${app.mobile.path}"})
public class UserLoginController{
    private static Logger logger = LoggerFactory.getLogger(UserLoginController.class);
    
    @Autowired
    private UmsClientUtil umsHttp;
    @Autowired
    private IUserLoginService iUserLoginService;

    @Value("${deal.refresh.timeout}")
    private String refreshTimeout;

    @RequestMapping("/")
    public String dealPage(UserLoginInfo user, ModelMap model){
        logger.info("first page0");
        return "login/login";
    }

    @RequestMapping("/login")
    public ModelAndView registerPage(UserLoginInfo user, HttpSession session, HttpServletRequest request){

        session.removeAttribute(Consts.SESSION_USERALLINFO);
        session.removeAttribute(Consts.SESSION_USERNAME);
        session.removeAttribute(Consts.SESSION_RELOGIN);
        session.invalidate();
        String showContent = request.getParameter("type");
        if(StringUtils.isEmpty(showContent)){
            return new ModelAndView("login/login");
        }
        return new ModelAndView("login/login", "showContent", showContent);
    }

    @RequestMapping("/login/sessionStatus")
    @ResponseBody
    public String sessionStatus(HttpServletRequest request, HttpServletResponse response, Device device){
        return "";
    }


    @RequestMapping("/login/checkUser")
    @ResponseBody
    public Map<String, Object> checkUser(UserLoginInfo user, HttpServletRequest request,Device device){
        Map<String, Object> result = Maps.newHashMap();
        try{
            String ipAddr = iUserLoginService.getIpAddr(request);
            logger.info("RemoteAddr:" + ipAddr + ",URI:" + request.getRequestURL());

            String name = user.getUsername();
            String rempwd = user.getRempwd();
            String password = user.getPassword();
            logger.info("checkUser-LoginName:" + name + ",Password:" + password + ",rempwd:" + rempwd);
            UmsUserDTO u = new UmsUserDTO();
            u.setUserName(name);
            u.setPassword(password);
            UmsResponseDTO res = umsHttp.checkLoginName(u);
            if(null != res){
                int returnCode = res.getRetCode().intValue();
                logger.info("checkUser-umsHttp result code:" + res.getRetCode().intValue());
                if(returnCode == 0){
                    // 登录成功
                    UserAllInfo userAllInfo = iUserLoginService.getUserAllInfo(res);
                    if(null == userAllInfo){
                        logger.info("userAllInfo 用户状态不可用，登录失败");
                        result.put("false", "false");
                        result.put("msg", "未匹配到有效用户");
                        return result;
                    }
                    boolean checkResult = iUserLoginService.executeCheckSiteResult(request, String.valueOf(request.getRequestURL()), userAllInfo);
                    if(!checkResult){
                        logger.info("checkSite 失败，登录失败");
                        result.put("false", "false");
                        result.put("msg", "站点校验失败");
                        return result;
                    }
                    result.put("success", "true");
                    result.put("msg", "登录成功");
                    // 把用户数据保存在session域对象中
                    userAllInfo.setOutDate(Integer.parseInt(refreshTimeout));
                    request.getSession().setAttribute(Consts.SESSION_USERALLINFO, userAllInfo);
                    request.getSession().setAttribute(Consts.SESSION_USERNAME, userAllInfo.getUserDisplayName());
                    //将当前会议的登陆者sessionid 更新到redis
                    String userId = String.valueOf(userAllInfo.getUserId());
                    //添加用户设备标识 by yanqing.zhang 2017.11.7
                    if(device.isMobile() || device.isTablet()){
                        userId += DeviceType.MOBILE;
                    }
                    if(RedisService.isExistSession(userId + "SessionId")){
                        RedisService.removeSessionFromCache(userId + "SessionId");
                    }
                    RedisService.putSessionToCache(userId + "SessionId", request.getSession().getId());
                    iUserLoginService.updateSession(request, userId);
                    logger.info("登录成功 [username:" + name + ", userId:" + userAllInfo.getUserId() + "], session设置:" + refreshTimeout + "秒,超时时间:" + userAllInfo.getOutDate()
                            + ",RemoteAddr:" + ipAddr);
                }else {
                    if(returnCode == -1){
                        result.put("false", "false");
                        result.put("msg", "登录名错误");
                    }else if(returnCode == -2){
                        result.put("false", "false");
                        result.put("msg", "密码错误");
                    }else if(returnCode == -3){
                        result.put("false", "false");
                        result.put("msg", "登录名或密码错误");
                    }else if(returnCode == -4){
                        result.put("false", "false");
                        result.put("msg", "用户不存在");
                    }else {
                        result.put("false", "false");
                        result.put("msg", "登录失败");
                    }
                }
            }else {
                result.put("false", "false");
                result.put("msg", "登录失败");
            }
        }catch (Exception e){
            result.put("false", "false");
            result.put("msg", "登录失败");
            logger.error("checkUser", e);
        }
        return result;
    }

    @RequestMapping("/login/logout")
    public ModelAndView logout(HttpSession session){
        UserAllInfo userAllInfo = (UserAllInfo) session.getAttribute(Consts.SESSION_USERALLINFO);
        String userid = "";
        if(userAllInfo != null){
            userid = String.valueOf(userAllInfo.getUserId());
            RedisService.removeSessionFromCache(userid + "Login");
        }

        session.removeAttribute(Consts.SESSION_USERALLINFO);
        session.removeAttribute(Consts.SESSION_USERNAME);
        return new ModelAndView("redirect:/login");
    }

    @RequestMapping("/login/forgetpassword")
    public ModelAndView forgetPass(ModelMap model, HttpServletRequest request){
        logger.info("forgetpassword page");

        String showContent = request.getParameter("showContent");
        if(StringUtils.isEmpty(showContent)){
            return new ModelAndView("login/forgetPassword");
        }

        return new ModelAndView("login/forgetPassword", "showContent", showContent);

    }

    @RequestMapping("/login/findBackPass")
    @ResponseBody
    public Map<String, Object> findBackPass(UserLoginInfo user, HttpSession session, HttpServletRequest request){
        Map<String, Object> result = Maps.newHashMap();
        try{
            String name = user.getUsername();
            String password = user.getCheckcode();
            logger.info("findName:" + name + ",Password:" + password);

            CheckFindBackNameResult cfbnResult = iUserLoginService.checkFindBackName(name, session);
            String type = cfbnResult.getType();
            logger.info("findBackPass type:" + type);
            if("0".equals(type)){// type 0:邮箱；1：手机号；2：未匹配到UMSID 3:匹配失败
                int svsmsResult = iUserLoginService.saveSendMail(name, cfbnResult, session);
                if(1 != svsmsResult){
                    logger.info("findBackPass-发邮件失败，svsmsResult:" + svsmsResult);
                    if(0 == svsmsResult){
                        result.put("success", false);
                        result.put("msg", "邮件发送失败，请重试");
                    }else if(-1 == svsmsResult){
                        result.put("success", false);
                        result.put("msg", "用户不存在");
                    }else {
                        result.put("success", false);
                        result.put("msg", "请重试");
                    }
                }else {
                    logger.info("findBackPass-发邮件成功");
                    result.put("success", true);
                    result.put("name", name);
                    result.put("id", cfbnResult.getUmsiId());
                    result.put("msg", "0");
                }
            }else if("1".equals(type)){
                int svsmsResult = iUserLoginService.saveVerifySMS(name, cfbnResult.getUmsiId(), session);
                if(1 != svsmsResult){
                    logger.info("findBackPass-发短信失败，svsmsResult:" + svsmsResult);
                    if(0 == svsmsResult){
                        result.put("success", false);
                        result.put("msg", "短信发送失败，请重试");
                    }else if(-1 == svsmsResult){
                        result.put("success", false);
                        result.put("msg", "用户不存在");
                    }else {
                        result.put("success", false);
                        result.put("msg", "请重试");
                    }
                }else {
                    logger.info("findBackPass-发短信成功，");
                    result.put("success", true);
                    result.put("name", name);
                    result.put("id", cfbnResult.getUmsiId());
                    result.put("msg", "1");
                }
            }else if("2".equals(type)){ //ums中未注册
                result.put("success", false);
                result.put("msg", "用户不存在");
            }else {
                result.put("success", false);
                result.put("msg", "处理失败，请重试");
            }
        }catch (Exception e){
            result.put("success", false);
            result.put("msg", "忘记密码处理失败");
            logger.error("findBackPass", e);
        }
        return result;
    }

    @RequestMapping("/login/setPwdFromEmail")
    public ModelAndView findBackPassByMail(HttpServletRequest req){
        logger.info("setPwdFromEmail page");
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        if(StringUtils.isEmpty(id) || StringUtils.isEmpty(name)){
            return new ModelAndView("login/login");
        }
        UserLoginInfo uli = new UserLoginInfo();
        uli.setId(id);
        uli.setUsername(name);
        return new ModelAndView("login/setPwdFromEmail", "uli", uli);
    }

    @RequestMapping("/login/setPwdFromMobile")
    public ModelAndView findBackPassByTel(HttpServletRequest req){
        logger.info("setPwdFromMobile page");
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        if(StringUtils.isEmpty(id) || StringUtils.isEmpty(name)){
            return new ModelAndView("login/login");
        }
        UserLoginInfo uli = new UserLoginInfo();
        uli.setId(id);
        uli.setUsername(name);
        return new ModelAndView("login/setPwdFromMobile", "uli", uli);
    }

    @RequestMapping("/login/findBackPassByTel_h")
    @ResponseBody
    public Map<String, Object> findBackPassByTel_h(HttpServletRequest req, UserLoginInfo user, HttpSession session){
        Map<String, Object> result = Maps.newHashMap();
        try{
            String umsId = req.getParameter("id");
            String name = req.getParameter("name");
            String inputVerify = user.getCheckcode();
            logger.info("findBackPassByTel_h-findName:" + name + ",inputVerify:" + inputVerify);

            boolean r = iUserLoginService.checkFindBackTelPassVC(inputVerify, session, umsId);
            if(!r){
                result.put("success", false);
                result.put("msg", "验证码错误，请重新输入");
                return result;
            }else {
                result.put("success", true);
                result.put("msg", "验证成功");
                session.setAttribute(Consts.RESETPWD_MOBILE_UMSID, umsId);
                session.setMaxInactiveInterval(Integer.parseInt(refreshTimeout));
            }
        }catch (Exception e){
            result.put("success", false);
            result.put("msg", "手机号找回密码处理失败");
            logger.error("", e);
        }
        return result;
    }

    @RequestMapping("/login/resetPassword")
    public ModelAndView resetPass(HttpServletRequest req, HttpSession session){
        logger.info("resetPassword page");
        String id = req.getParameter("id");
        Object Object_id = (Object) session.getAttribute(Consts.RESETPWD_MOBILE_UMSID);
        // mobile处理
        if(null != Object_id){
            id = (String) Object_id;
            UserLoginInfo uli = new UserLoginInfo();
            uli.setId(id);
            return new ModelAndView("login/resetPassword", "uli", uli);
        }
        String verify = req.getParameter("verify");
        if(StringUtils.isEmpty(id) || StringUtils.isEmpty(verify)){
            return new ModelAndView("redirect:/login/forgetpassword");
        }
        UserLoginInfo uli = new UserLoginInfo();
        uli.setId(id);
        uli.setCheckcode(verify);

        int checkResult = iUserLoginService.checkEmailVer(uli);
        // -1:用户不存在；0：验证失败；1：成功；2：请求超时，请重试
        if(1 != checkResult){
            if(0 == checkResult || 2 == checkResult){
                logger.info("验证失败，原因：" + checkResult);
                return new ModelAndView("redirect:/login/setPwdTimeOutFromEmail?id=" + id);
            }

            uli.setShowContent(String.valueOf(checkResult));
            logger.info("验证失败，原因：" + checkResult);
            return new ModelAndView("redirect:/login/forgetpassword", "showContent", checkResult);
        }
        return new ModelAndView("login/resetPassword", "uli", uli);

    }

    @RequestMapping("/login/modifyPassword")
    public String updatePass(HttpServletRequest req, HttpSession session){
        logger.info("modifyPassword page");
        return "login/modifyPassword";
    }

    @RequestMapping("/login/resetPass_h")
    @ResponseBody
    public Map<String, Object> resetPass_h(HttpServletRequest req, UserLoginInfo user, HttpSession session){
        Map<String, Object> result = Maps.newHashMap();
        try{
            String umsid = req.getParameter("id");
            user.setId(umsid);
            String password = user.getPassword();
            logger.info("resetPass_h-Password:" + password + ",umsid:" + umsid);
            boolean r = iUserLoginService.resetPass(user, session);
            if(r){
                result.put("success", "true");
                result.put("msg", "重置密码成功");
            }else {
                result.put("false", "false");
                result.put("msg", "重置密码失败");
            }
        }catch (Exception e){
            result.put("false", "false");
            result.put("msg", "重置密码失败");
            logger.error("重置密码失败,", e);
        }
        return result;
    }

    @RequestMapping("/login/updatePass_h")
    @ResponseBody
    public Map<String, Object> updatePass_h(UserLoginInfo user, HttpSession session){
        Map<String, Object> result = Maps.newHashMap();
        try{
            String oldPassword = user.getOldPassword();
            String password = user.getPassword();
            logger.info("updatePass_h-Password_old:" + oldPassword + ",Password:" + password);
            UmsResult umsResult = iUserLoginService.updatePass(user, session);
            if(umsResult.isResult()){
                result.put("success", "true");
                result.put("msg", "更改密码成功");
                logger.info("updatePass is success ,result:" + umsResult.getResponseBody());
            }else if("wrong old password".equals(umsResult.getErrorInfo())){
                result.put("false", "false");
                result.put("msg", "旧密码错误");
                logger.info("updatePass is false ,result:旧密码错误");
            }else {
                result.put("false", "false");
                result.put("msg", "更改密码失败");
                logger.info("updatePass is false");
            }
        }catch (Exception e){
            result.put("false", "false");
            result.put("msg", "更改密码失败");
            logger.error("更改密码失败", e);
        }
        return result;
    }

    @RequestMapping("/login/findBackPassByTel_resendSMS")
    @ResponseBody
    public Map<String, Object> findBackPassByTel_resendSMS(HttpServletRequest req, HttpSession session){
        Map<String, Object> result = Maps.newHashMap();
        try{
            String umsId = req.getParameter("id");
            String name = req.getParameter("name");
            logger.info("findBackPassByTel_resendSMS-name:" + name + ",umsId:" + umsId);
            int svsmsResult = iUserLoginService.saveVerifySMS(name, umsId, session);
            if(1 != svsmsResult){
                logger.info("发短信失败，svsmsResult:" + svsmsResult);
                if(0 == svsmsResult){
                    result.put("success", false);
                    result.put("msg", "短信发送失败，请重试");
                }else if(-1 == svsmsResult){
                    result.put("success", false);
                    result.put("msg", "用户不存在");
                }else {
                    result.put("success", false);
                    result.put("msg", "请重试");
                }
            }else {
                logger.info("发短信成功，");
                result.put("success", true);
                result.put("msg", "重新发短信成功");
            }
        }catch (Exception e){
            result.put("false", "false");
            result.put("msg", "重新发短信失败");
            logger.error("重新发短信失败", e);
        }
        return result;
    }

    @RequestMapping("/login/findBackPassByMail_resendMail")
    @ResponseBody
    public Map<String, Object> findBackPassByMail_resendMail(HttpServletRequest req, HttpSession session){
        Map<String, Object> result = Maps.newHashMap();
        try{
            String umsId = req.getParameter("id");
            String name = req.getParameter("name");
            String userDisplayName = "";
            UserInfo userInfo = iUserLoginService.getUserInfo(umsId);
            if(null != userInfo){
                userDisplayName = userInfo.getEmailDisplayName();
            }
            CheckFindBackNameResult cbnr = new CheckFindBackNameResult();
            cbnr.setUmsId(umsId);
            cbnr.setDisplayName(userDisplayName);
            logger.info("findBackPassByMail_resendMail-name:" + name + ",umsId:" + umsId + ",userDisplayName:" + userDisplayName);
            int svsmsResult = iUserLoginService.saveSendMail(name, cbnr, session);
            if(1 != svsmsResult){
                logger.info("findBackPass-发邮件失败，svsmsResult:" + svsmsResult);
                if(0 == svsmsResult){
                    result.put("success", false);
                    result.put("msg", "邮件发送失败，请重试");
                }else if(-1 == svsmsResult){
                    result.put("success", false);
                    result.put("msg", "用户不存在");
                }else {
                    result.put("success", false);
                    result.put("msg", "请重试");
                }
            }else {
                result.put("success", true);
                result.put("msg", "重新发送邮件成功");
            }
        }catch (Exception e){
            result.put("false", "false");
            result.put("msg", "重新发送邮件失败");
            logger.error("重新发送短信邮件", e);
        }
        return result;
    }

    @RequestMapping("/login/setPwdTimeOutFromEmail")
    public ModelAndView setPwdTimeOutFromEmail(HttpServletRequest req, HttpSession session){
        logger.info("setPwdTimeOutFromEmail page");
        String id = req.getParameter("id");
        if(StringUtils.isEmpty(id)){
            return new ModelAndView("login/forgetPassword");
        }
        UserLoginInfo uli = new UserLoginInfo();
        uli.setId(id);
        return new ModelAndView("login/setPwdTimeOutFromEmail", "uli", uli);
    }
}

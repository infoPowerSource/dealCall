package com.deal.controller.userLogin;

import com.deal.dao.login.UserInfoDao;
import com.deal.entity.login.UserAllInfo;
import com.deal.entity.login.UserInfo;
import com.deal.monitor.cache.RedisService;
import com.deal.util.Consts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.mobile.device.Device;
import org.springframework.mobile.device.DeviceType;
import org.springframework.mobile.device.DeviceUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * 登陆拦截器 场景：用户点击查看的时候，我们进行登陆拦截器操作，判断用户是否登陆？ 登陆，则不拦截，没登陆，则转到登陆界面；
 */
public class LoginHandlerIntercepter implements HandlerInterceptor{
    private static Logger logger = LoggerFactory.getLogger(LoginHandlerIntercepter.class);
    private List<String> excludedUrls;

    @Value("${deal.refresh.timeout}")
    private String refreshTimeout;

    @Autowired
    private UserInfoDao userInfoDao;

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object arg2, Exception arg3) throws Exception{
    }

    @Override
    public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3) throws Exception{

    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
        String requestURI = request.getRequestURI();
        HttpSession session = request.getSession();
        UserAllInfo username = (UserAllInfo) session.getAttribute(Consts.SESSION_USERALLINFO);
        //获取session中用户id
        Long userid = 0L;
        if(username != null){
            userid = username.getUserId();
        }

        //sessionstatus handle first
        if(requestURI.contains("/login/sessionStatus")){
            //添加用户设备标识 by yanqing.zhang 2017.11.7
            Device currentDevice = DeviceUtils.getCurrentDevice(request);
            String userId = String.valueOf(userid);
            if(currentDevice.isMobile() || currentDevice.isTablet()){
                userId += DeviceType.MOBILE;
            }
            Boolean isReLogin = RedisService.IsExistOper(userId + Consts.SESSION_RELOGIN, true);
            String last_sessionid = (String) RedisService.getSessionFromCache(userId + "SessionId");

            //判断是否有重复登录，并且是否是当前最新的登陆者，不是的话，退出
            if(isReLogin && !last_sessionid.equals(session.getId())){
                logger.info("ReLogin sessionId:" + request.getSession().getId());
                if("XMLHttpRequest".equals(request.getHeader("x-requested-with"))){
                    response.setStatus(HttpStatus.CONFLICT.value());
                    //async request(ajax) set response Header
                    response.setHeader("sessionstatus", "relogin");
                    return true;
                }else{
                    //sync request Redirect to /login?type=1
                    response.sendRedirect(request.getContextPath() + "/login?type=1");
                    //清除重复登录信息,并将第一个登陆者信息更新为当前登陆者
                    RedisService.removeOperToCatch(userId + Consts.SESSION_RELOGIN, true);
                    //interrupt handle
                    return false;
                }
            }
        }

        if(!requestURI.startsWith("/deal/login/modifyPassword")){
            for(String url : excludedUrls){
                if(requestURI.startsWith(url)){
                    return true;
                }
            }
        }

        if(username != null){
            UserInfo userInfo = userInfoDao.getValidUserByUmsId(String.valueOf(username.getUserId()));
            if(null == userInfo){
                logger.info("未匹配到UserInfo,umsiId:" + username.getUserId());
                return true;
            }

            if(!isvalidTime(username.getUserId(), username.getOutDate())){
                // 登录超时
                //如果判断是 AJAX 请求,直接设置为session超时
                if("XMLHttpRequest".equals(request.getHeader("x-requested-with"))){
                    logger.info("login timeOut-login-ajax,Pedirect to login page , URI:" + request.getRequestURI());
                    incrTime(username.getUserId(), username);
                    response.setHeader("sessionstatus", "timeout");
                    return true;
                }
            }
            // 登陆成功的用户
            return true;
        }else {
            // 没有登陆，转向登陆界面
            //如果判断是 AJAX 请求,直接设置为session超时
            if("XMLHttpRequest".equals(request.getHeader("x-requested-with"))){
                logger.info("login timeOut-unlogin-ajax,Pedirect to login page , URI:" + request.getRequestURI());
                response.setHeader("sessionstatus", "unlogin");
                return true;
            }else {
                logger.info("login timeOut-unlogin-no ajax,Pedirect to login page , URI:" + request.getRequestURI());
                response.sendRedirect(request.getContextPath() + "/login");
            }
            return false;
        }
    }

    /**
     * 获取客户端IP地址
     *
     * @param request
     * @return
     */
    public String getIpAddr(HttpServletRequest request){

        String ip = request.getHeader("x-forwarded-for");

        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){

            ip = request.getHeader("Proxy-Client-IP");

        }

        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){

            ip = request.getHeader("WL-Proxy-Client-IP");

        }

        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){

            ip = request.getRemoteAddr();

        }

        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){

            ip = request.getHeader("http_client_ip");

        }

        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){

            ip = request.getHeader("HTTP_X_FORWARDED_FOR");

        }

        // 如果是多级代理，那么取第一个ip为客户ip

        if(ip != null && ip.indexOf(",") != -1){

            ip = ip.substring(ip.lastIndexOf(",") + 1, ip.length()).trim();

        }

        return ip;

    }

    public void setExcludedUrls(List<String> excludedUrls){
        this.excludedUrls = excludedUrls;
    }

    private boolean isvalidTime(Long userId, Timestamp outDateT){
        long outTime = outDateT.getTime();

        Date now = new Date();
        long nowTime = now.getTime();

        boolean isvalid = nowTime <= outTime ? true : false;
        if(!isvalid){
            logger.info(
                    "userId:" + userId + ",isvalid:" + isvalid + ",outTime:" + outTime + ",nowTime:" + nowTime + ",outDate:" + new Timestamp(outTime) + ",nowDate" + new Timestamp(
                            nowTime));
        }
        return isvalid;
    }

    private void incrTime(Long userId, UserAllInfo userAllInfo){
        long currentTimeMillis = System.currentTimeMillis();
        Timestamp outDate = new java.sql.Timestamp(currentTimeMillis + Integer.parseInt(refreshTimeout) * 1000);//传残单位：秒。
        userAllInfo.setOutDateTimestamp(outDate);
        logger.info("incrTime-userId:" + userId + ",outTime:" + outDate.getTime() + ",nowTime:" + currentTimeMillis + ",outDate:" + outDate + ",nowDate" + new Timestamp(
                currentTimeMillis));
    }

    public static void main(String[] args){
        System.out.println(new Timestamp(1499147537136L));
    }
}

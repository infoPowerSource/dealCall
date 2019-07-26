package com.deal.controller.conference;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.deal.entity.create.AppConferenceDetail;
import com.deal.entity.create.ConferenceRadioInfo;
import com.deal.entity.login.UserAllInfo;
import com.deal.service.conference.IConfEditService;
import com.deal.service.conference.IConfInfoDetail;
import com.deal.service.create.IConferenceService;
import com.deal.service.mss.IMSSService;
import com.deal.service.report.IConferenceRecordService;
import com.deal.service.report.IMeetingReportService;
import com.deal.util.Consts;
import com.deal.util.DateFormatUtil;
import com.deal.util.Page;
import com.deal.util.UrlBase64Util;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;


@Controller
public class AppConferenceController{
	private static Logger logger = LoggerFactory.getLogger(AppConferenceController.class);
    @Autowired
    private IConfInfoDetail confInfoDetail;
    @Autowired
    private IMeetingReportService reportService;
    @Autowired
    private IMSSService mssService;
    @Value("${record.recordPath}")
    private String recordUrl;
    @Autowired
    private IConferenceRecordService radioService;
    @Autowired
    private IConferenceService confService;
    @Autowired
    private IConfEditService editService;

    @Value("${platform2moreAccessNumber}")
    private String AccessNum2;

    @Value("${platform7moreAccessNumber}")
    private String AccessNum7;
	
	/*
     * 	手机端根据日期，获取会议未开数量和已结束数量
     */
    @ResponseBody
    @RequestMapping("/app/conf/getConfNum")
    public Map<String, Object> appGetConfNum(String date, HttpServletRequest request){
    	Map<String, Object> result = Maps.newHashMap();
        try{
            // 获取session中的用户信息
            // 从session中拿到 客服登录的实体信息
            UserAllInfo userInfo = (UserAllInfo) request.getSession().getAttribute("userAllInfo");
            if(ObjectUtils.isEmpty(userInfo)){
                return null;
            }
            String billingCode = userInfo.getUserBillingcode();
            
            Map<String, Integer> numMap = confInfoDetail.getOnlineAndEndConfNum(billingCode);
            int onlineConfNum = 0;
            int endConfNum = 0;
            if(numMap.size() > 0){
                onlineConfNum = numMap.get("onlineConf");
                endConfNum = numMap.get("endConf");
            }
            result.put("onlineNum",onlineConfNum);
    		result.put("offlineNum",endConfNum);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    /*
     * 	手机端根据日期，获取会议列表
     */
    @ResponseBody
    @RequestMapping("/app/conf/getOnlineConfList")
    public List<AppConferenceDetail> appOnlieConfList(String currPage, String date, HttpServletRequest request){
        List<AppConferenceDetail> confDetail = Lists.newArrayList();
        try{
            // 获取session中的用户信息
            // 从session中拿到 客服登录的实体信息
            UserAllInfo userInfo = (UserAllInfo) request.getSession().getAttribute("userAllInfo");
            if(ObjectUtils.isEmpty(userInfo)){
                return null;
            }
            String billingCode = userInfo.getUserBillingcode();
            Timestamp beginTime = DateFormatUtil.today();
            confDetail = confInfoDetail.getAppOnlineConfList(billingCode, currPage, beginTime);
        }catch (Exception e){
            e.printStackTrace();
        }
        return confDetail;
    }

    /*
     * 	手机端根据日期，获取会议列表
     */
    @ResponseBody
    @RequestMapping("/app/conf/getEndConfList")
    public List<AppConferenceDetail> appEndConfList(String currPage, String date, HttpServletRequest request){
        List<AppConferenceDetail> confDetail = Lists.newArrayList();
        try{
            // 获取session中的用户信息
            // 从session中拿到 客服登录的实体信息
            UserAllInfo userInfo = (UserAllInfo) request.getSession().getAttribute("userAllInfo");
            if(ObjectUtils.isEmpty(userInfo)){
                return null;
            }
            String billingCode = userInfo.getUserBillingcode();
            Timestamp beginTime = new Timestamp(System.currentTimeMillis());
            confDetail = confInfoDetail.getAppEndConfList(billingCode, currPage, beginTime);
        }catch (Exception e){
            e.printStackTrace();
        }
        return confDetail;
    }

    @RequestMapping("/app/conf/confInfo")
    @ResponseBody
    public ModelAndView conferenceList(HttpServletRequest request){
        //		ConferenceDetail confDetail = new ConferenceDetail();
        List<AppConferenceDetail> confDetail = Lists.newArrayList();
        try{
            // 获取session中的用户信息
            // 从session中拿到 客服登录的实体信息
            UserAllInfo userInfo = (UserAllInfo) request.getSession().getAttribute("userAllInfo");
            String billingCode = userInfo.getUserBillingcode();
            /**
             * 取得今天凌晨时间和明天凌晨时间beginTime、endTime
             */
            Timestamp beginTime = DateFormatUtil.today();
            Timestamp endTime = DateFormatUtil.dateReductionDay(beginTime, -1);
            //confDetail = confInfoDetail.getAppOnlineConfList(billingCode, "1", beginTime, endTime);
        }catch (Exception e){
            e.printStackTrace();
        }

        return new ModelAndView("conference/confList", "confInfo", confDetail);
    }

    @RequestMapping("/app/conf/reportData/")
    @ResponseBody
    public String getReportData(HttpServletRequest request){
        String confId = request.getParameter("confId");
        String result = "";
        if(StringUtils.isEmpty(confId)){
            logger.info("查询会议报告的会议报告ID为空,confId:" + confId);
        }
        try{
            Map<String, Object> reportDataMap = reportService.getReportDataByConfId(confId);
            result = JSON.toJSONString(reportDataMap, true);
        }catch (Exception e){
            e.printStackTrace();
            logger.error("读取会议报告出错，错误信息", e.getMessage());
        }
        return result;
    }

    /**
     * 下载录音文件
     *
     * @param recordName
     * @return
     */
    @RequestMapping("/app/record/{recordName}")
    @ResponseBody
    public String appDownloadRecord(@PathVariable String recordName){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletResponse response = attributes.getResponse();
        /**
         * 详细描述：
         */
        try{
            /**
             * 输出文件下载输出流
             */
            logger.info("解密前文件名：" + recordName);
            String radioNamePrefix = recordName.substring(0, recordName.indexOf("."));
            String radioNameSuffix = recordName.substring(recordName.indexOf("."));
            String deRecordName = UrlBase64Util.decode(radioNamePrefix) + radioNameSuffix + ".zip";
            String filePath = recordUrl;
            if(filePath.endsWith("/") || filePath.endsWith("\\")){
                filePath = filePath + deRecordName;
            }else {
                filePath = filePath + File.separator + deRecordName;
            }
            // 解密
            // radioName 加密
			/*
			 * des.setKey("DEAL"); String desName =
			 * des.getDesString(recordName);
			 */

            logger.info("record download path:" + filePath);
            // 输入流
            FileInputStream fileInputStream = new FileInputStream(filePath);
            // 输出流
            ServletOutputStream servletOutputStream = response.getOutputStream();
            // 设置文件头信息
            response.setContentType("APPLICATION/OCTET-STREAM;charset=GBK");
            response.setHeader("Content-Disposition", "attachment; filename=\"" + new String(recordName.concat(".zip").getBytes("GBK"), "iso-8859-1") + "\"");
            // Private指示对于单个用户的整个或部分响应消息，不能被共享缓存处理。这允许服务器仅仅描述当用户的部分响应消息，此响应消息对于其他用户的请求无效。
            response.setHeader("Cache-Control", "Private");
            // Causes the proxy cache to see the page as "stale"
            response.setDateHeader("Expires", 0);
            // HTTP 1.0 backwardcompatibility
            response.setHeader("Pragma", "public");
            byte[] bytes = new byte[20480];
            int size = 0;
            while((size = fileInputStream.read(bytes)) != -1){
                servletOutputStream.write(bytes, 0, size);
            }
            // 关闭输入流
            fileInputStream.close();
            // 关闭输出流
            servletOutputStream.close();
        }catch (Exception e){
            logger.error("下载会议录音异常！", e);
            e.printStackTrace();
            return "error";
        }
        return "success";
    }


    /**
     * 发送录音
     *
     * @param confId 会议Id
     * @param email  邮件
     * @return
     */
    @RequestMapping("/app/conf/sendRecord")
    @ResponseBody
    public String sendRecordByApp(@RequestParam("confId") String confId, @RequestParam("email") String email){
        try{
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = attributes.getRequest();

            if(confId == null || email == null){
                logger.error("发送录音参数校验失败");
                return "error";
            }
            String url = "http://" + request.getServerName(); // 服务器地址
            // + ":" + request.getServerPort(); // 端口号
            String fileUrl = url + "/deal/record/";
            logger.info("下载地址为" + fileUrl);
            // 根据会议Id 查询本地录音文件名
            List<ConferenceRadioInfo> listRadio = radioService.getRadioInfoByConfId(confId);
            List<String> listdown = new ArrayList<>();
            for(ConferenceRadioInfo radio : listRadio){
                // 拼接录音下载录音地址
                String radioName = radio.getFileName();
                // radioName 加密
				/*
				 * des.setKey("DEAL"); String encName =
				 * des.getEncString(radioName);
				 */

                String radioNamePrefix = radioName.substring(0, radioName.indexOf("."));
                String radioNameSuffix = radioName.substring(radioName.indexOf("."));
                String download = fileUrl + UrlBase64Util.encoded(radioNamePrefix) + radioNameSuffix;
                logger.info("录音下载地址：" + download + ",加密前文件名：" + radioName);
                listdown.add(download);
            }
            /**
             * 发送邮件
             */
            Map<String, Object> radioMap = new HashMap<String, Object>();
            radioMap.put("radio", listdown);
            radioMap.put("confId", confId);
            UserAllInfo userAllInfo = (UserAllInfo) request.getSession().getAttribute(Consts.SESSION_USERALLINFO);
            radioMap.put("userAllInfo", userAllInfo);
            mssService.saveMailForRadio(radioMap, email);

        }catch (Exception e){
            logger.error("发送录音文件出现异常！", e);
            return "false";
        }
        return "true";
    }

    /**
     * 取得账号所属平台，返回接入号查看地址
     *
     * @return
     */
    @RequestMapping("conf/getConfAccessNum")
    @ResponseBody
    public String getConfAccessNum(HttpServletRequest request){
        // 获取session中的用户信息
        // 从session中拿到 客服登录的实体信息
        UserAllInfo userInfo = (UserAllInfo) request.getSession().getAttribute("userAllInfo");
        String summit = userInfo.getBridgeName();
        String accessUrl;
        if(summit.equals("summit7")){
            accessUrl = AccessNum7;
        }else {
            accessUrl = AccessNum2;
        }
        return accessUrl;
    }

    /**
     * 取得账号所属平台，返回接入号查看地址
     *
     * @return
     */
    @RequestMapping("app/conf/getConfAccessNum")
    @ResponseBody
    public String getAppConfAccessNum(HttpServletRequest request){
        // 获取session中的用户信息
        // 从session中拿到 客服登录的实体信息
        UserAllInfo userInfo = (UserAllInfo) request.getSession().getAttribute("userAllInfo");
        String summit = userInfo.getBridgeName();
        String accessUrl;
        if(summit.equals("summit7")){
            accessUrl = AccessNum7;
        }else {
            accessUrl = AccessNum2;
        }
        return accessUrl;
    }

	@RequestMapping("/app/conf/searchConf")
	public String searchConf(ModelMap model){
		return "/conference/searchConf";
	}

	@RequestMapping("/app/conf/searchConf4Page")
	@ResponseBody
	public Page searchConf4Page(HttpServletRequest request, @RequestParam String keyword, @RequestParam String date, @RequestParam int pageNum,
			@RequestParam(required = false, defaultValue = Consts.CONF_LIST_COUNT_FOR_EACHPAGE + "") int pageSize){
		UserAllInfo userInfo = (UserAllInfo) request.getSession().getAttribute("userAllInfo");
		if(ObjectUtils.isEmpty(userInfo)){
			return null;
		}
		String billingCode = userInfo.getUserBillingcode();
		if(StringUtils.isEmpty(keyword) && StringUtils.isEmpty(date)){
			return null;
		}
		return confService.searchConf4Page(keyword, date, billingCode, pageNum, pageSize);
	}
}

package com.deal.service.create;

import com.deal.entity.conference.ConferenceCountryCode;
import com.deal.entity.create.ConferenceInfo;
import com.deal.entity.create.ConferenceInfoForm;
import com.deal.entity.create.CustomerInfo;
import com.deal.entity.login.UserAllInfo;
import com.deal.util.Page;

import net.fortuna.ical4j.model.ValidationException;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

public interface IConferenceService{
    public ConferenceInfo getConfInfoById(String ConfId);

    public void addConferenceInfo(ConferenceInfoForm confForm, UserAllInfo userInfo) throws ValidationException;

    public void updateConference(ConferenceInfoForm confForm, UserAllInfo userInfo) throws ValidationException;

    ;

    /**
     * 根据会议billingCode获取会议实体
     *
     * @param billingCode
     * @author zhipeng.xu
     */
    public ConferenceInfo getConfByBillingCode(String billingCode);

    /**
     * 根据客户billigncode 和日期 查询会议信息
     *
     * @param billingCode
     * @param beginTime
     * @param endTime
     * @return
     */
    public List<ConferenceInfo> getConfByCreateAndTime(String billingCode, Timestamp beginTime, Timestamp endTime);

    /**
     * 根据会议bc更新会议状态
     *
     * @param billingCode
     * @param status
     */
    public void updateConfStatus(ConferenceInfo conf);

    public Map<Integer, List<CustomerInfo>> getAllCustTypeList(String confId);

    public void delConferenceInfo(String confId);

    /**
     * 根据开始时间 查询开始的会议
     *
     * @param time
     * @return
     */
    public List<ConferenceInfo> getConfByDate(String time);

    /**
     * 根据acm传入的billingCode 判断是否时主动服务会议
     *
     * @param billingCode
     * @return
     */
    public ConferenceInfo getMeeting(String billingCode);

    /**
     * 根据会议结束时间，查询结束时间到，但是状态不是已召开的会议(正在召开，没有召开)
     *
     * @param timeEnd
     * @return
     */
    public List<ConferenceInfo> getEndConf(String timeEnd, int type);

    public void deleteBillingCode();

    public List<Timestamp> getAllConfTodo(String billingCode, Timestamp beginTime);

    public List<ConferenceInfo> getUnendConfListAfterTime4Page(String billingCode, Timestamp beginTime, int pageNum, int pageSize);

    /**
     * 分页查询会议
     *
     * @param keyword     关键字
     * @param date        日期
     * @param billingCode 计费码
     * @param pageNum     当前页码
     * @param pageSize
     * @return
     */
    Page searchConf4Page(String keyword, String date, String billingCode, int pageNum, int pageSize);

    public List<CustomerInfo> getAllCustList(String confId);

    public void addAppConferenceInfo(String data, UserAllInfo userInfo, String confId) throws ValidationException;
    //获取国家码列表
    public List<ConferenceCountryCode> getCountryCode();
    
    public ConferenceInfo getLastConf(String billingCode);
    
}

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../common/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<link rel="stylesheet" href="${CSS_PATH}/createConf.css" />
<link rel="stylesheet" href="${CSS_PATH}/../js/libs/datePicker/pickAdate.css" />
<script type="text/javascript" src="${JS_PATH}/libs/datePicker/DatePicker.js"></script>
<script type="text/javascript" src="${JS_PATH}/deal/create/commonConf.js"></script>
<script type="text/javascript" src="${JS_PATH}/deal/create/modifyConf.js"></script>
 <link rel="stylesheet" href="${CSS_PATH}/modifyConf.css" />
</head>
<body>
<script type="application/javascript">
    var custSize = ${experList.size()+partyList.size()};
</script>
<script id="custTpl" type="text/html">
    <div class="input-block task input-group-lg conf-user-item" data-id="156" id="baseinfo">
        <input type="text" name="custInfo[{{d.i}}].custName" value="" placeholder="姓名" maxlength="20" class="input-pub s-size conf-user-item-name" onblur="return checkName(this)"/>
        <div class="tel-group">
            <span class="drop-list-box countrycode">
                <input type="text" name="custInfo[{{d.i}}].custContryCode" value="+86" maxlength="4" class="input-pub select-menu base conf-user-item-contry" onblur="return checkContryCode({{d.i}})"/>
                <ul id="custInfo{{d.i}}" class="code_list">
                </ul>
            </span>
            <input type="text" name="custInfo[{{d.i}}].custAreacode"  maxlength="4" placeholder="区号" class="num conf-user-item-area" onblur="return checkAreacode(this)"/>
            <input type="tel" name="custInfo[{{d.i}}].custTel" value="" maxlength="20" placeholder="电话号码" class="phone conf-user-item-phone" onblur="return checkCustTel(this)"/>
        </div>
        <input type="text" name="custInfo[{{d.i}}].custEmail" id="email" value="" maxlength="50" placeholder="邮箱" class="input-pub m-size last conf-user-item-email" onblur="return checkEmail(this)"/>
        <input type="hidden" name="custInfo[{{d.i}}].custType" class="conf-user-item-type" value="{{d.t}}"/>
        <input type="hidden" name="custInfo[{{d.i}}].custId" value="0"/>
        <a href="javascript:;" class="icons icons-reduce control-btn reduce-btn" onclick="removeItme(this)"></a>
        <a href="javascript:;" class="icons icons-add-btn control-btn add-more" onclick="addItme(this)"></a>
        <span class="message"></span>
    </div>
</script>
<div class="header">
	<div class="mainbody top">
            <a class="icons icons-logo"></a>
            <div class="user-info">
                <a href="javascript:;" class="top-user"><%=request.getSession().getAttribute("userName")%><span class="arrow"></span></a>
                <div class="menus">
                    <ul class="drop-menu">
                    	<li><a href="javascript:;" id="serchAccess">查询接入号</a></li>
						<li><a href="/deal/login/modifyPassword">修改密码</a></li>
						<li><a href="/deal/login/logout">退出登录</a></li>
					</ul>
                </div>
            </div>
        </div>
</div>
<div class="mainbody navbar">			
	<div class="navfont"><a href="javascript:history.go(-1);" class="icons icons-back"></a> 编辑会议</div>
</div>
    <div class="mainbody" onload="javascript:afterData()">
        <div class="czmm">
            <form id="addTask" class="form-fgpw">
                <div class="form-box add-task">
                    <h4>编辑会议</h4>
                    <div class="input-bt-line">
                        <div class="input-block task input-group-lg">
                            <label for="task-theme" class="input-label">会议主题</label>
                             <input type="hidden" id="confId" name="confId" value="${confInfo.confId}"/>
                            <input type="text" id="confName" name="confName" value="${confInfo.confName}"  maxlength="20" class="input-pub theme last" />
                            <span class="message"></span>
                        </div>
                        <div class="input-block task input-group-lg">
                        <div style="line-height: 11px;" class="warning-login" id="warning-duration"></div>
                            <label for="bg-time" class="input-label">开始时间</label>
                            <div class="conf-begin-time-wrap">
                            	<input type="text" id="beginTime" data-value="${confBegintime}" placeholder="开始时间" class="input-pub xl-size addtask" />
                            	<span class="weekday"></span>
                            	<input type="text" id="beginHours" class="time-s">
                            	<span class="hms">:</span>	
                            	<input type="text" id="beginMinutes" class="time-s">
                            	<ul class="hour-list time-list">
                            	</ul>
                            	<ul class="minute-list time-list">
                            	</ul>
                            	<input id="confStartTime" type="hidden" name="beginTime">
                            </div>
                            <span class="drop-list-box l-size">
                             <span class="time-name">时长：</span>
                                <input type="text" id="confDuration" name="confDuration" value="${confduration}" onblur="checkTime()" placeholder="总时间" class="input-pub  last select-menu time"/>
                                <ul class="task-time addtask">
                                   <li data-time="01:00">1小时</li>
                                   <li data-time="01:30">1.5小时</li>
                                   <li data-time="02:00">2小时</li>
                                   <li data-time="02:30">2.5小时</li>
                                   <li data-time="03:00">3小时</li>
                                   <li data-time="03:30">3.5小时</li>
                                   <li data-time="04:00">4小时</li>
                                   <li data-time="04:30">4.5小时</li>
                                </ul>
                            </span>
                            <span class="message"></span>
                        </div>
                    </div>
                </div>
                <c:set var="index" value="0" /> 
                <div class="form-box add-task">
                    <h4>顾问信息</h4>
                    <div class="input-bt-line" id="zj-info">
                    <c:forEach var="custInfo" items="${experList}">
                        <div class="input-block task input-group-lg conf-user-item" data-id="156" id="baseinfo">
                            <input type="text" name="custInfo[${index}].custName" value="${custInfo.custName}" maxlength="20"  class="input-pub s-size conf-user-item-name" onblur="return checkName(this)"/>
                            <div class="tel-group">
                            	<span class="drop-list-box countrycode">
                                	<input type="text" name="custInfo[${index}].custContryCode" value="${custInfo.custContryCode}" maxlength="4" class="input-pub select-menu conf-user-item-contry" onblur="return checkContryCode(${index})" />
                                	<ul id="custInfo${index}" class="code_list">
	                                </ul>
	                            </span>
                                <input type="text" name="custInfo[${index}].custAreacode" value="${custInfo.custAreacode}" placeholder="区号" maxlength="4" class="num conf-user-item-area" onblur="return checkAreacode(this)"/>
                                <input type="tel" name="custInfo[${index}].custTel" value="${custInfo.custTel}"  maxlength="20" class="phone conf-user-item-phone" onblur="return checkCustTel(this)"/>
                            </div>
                            <input type="text" name="custInfo[${index}].custEmail" value="${custInfo.custEmail}" placeholder="邮箱"  maxlength="50" class="input-pub m-size last conf-user-item-email" onblur="return checkEmail(this)"/>
                            <input type="hidden" name="custInfo[${index}].custType" class="conf-user-item-type" value="0"/>
                            <input type="hidden" name="custInfo[${index}].custId" value="${custInfo.custId}"/>
                             <a href="javascript:;" class="icons icons-reduce control-btn reduce-btn" onclick="removeItme(this)"></a>
                             <a href="javascript:;" class="icons icons-add-btn control-btn add-more" onclick="addItme(this)"></a>
                             <span class="message"></span>
                        </div>
                        <c:set var="index" value="${index+1}"/>
                      </c:forEach>
                    </div>
                </div>
                <div class="form-box add-task">
                    <h4>咨询客户信息</h4>
                    <div class="input-bt-line" id="user-info">
                    <c:forEach var="custInfo" items="${partyList}">
                        <div class="input-block task input-group-lg conf-user-item" data-id="123" id="userbaseinfo">
                            <input type="text" name="custInfo[${index}].custName" value="${custInfo.custName}" maxlength="20"  placeholder="姓名" class="input-pub s-size conf-user-item-name" onblur="return checkName(this)"/>
                            <div class="tel-group">
                            	<span class="drop-list-box countrycode">
	                                <input type="text" name="custInfo[${index}].custContryCode" value="${custInfo.custContryCode}" maxlength="4" class="input-pub select-menu conf-user-item-contry" onblur="return checkContryCode(${index})"/>
	                                <ul id="custInfo${index}" class="code_list">
		                            </ul>
	                            </span>
                                <input type="text" name="custInfo[${index}].custAreacode" value="${custInfo.custAreacode}" placeholder="区号" maxlength="4" class="num conf-user-item-area" onblur="return checkAreacode(this)"/>
                                <input type="tel" name="custInfo[${index}].custTel" value="${custInfo.custTel}"  maxlength="20" placeholder="电话号码" class="phone conf-user-item-phone" onblur="return checkCustTel(this)"/>
                            </div>
                            <input type="text" name="custInfo[${index}].custEmail" value="${custInfo.custEmail}" placeholder="邮箱"  maxlength="50" class="input-pub m-size last conf-user-item-email" onblur="return checkEmail(this)" />
                            <input type="hidden" name="custInfo[${index}].custType" class="conf-user-item-type" value="1"/>
                            <input type="hidden" name="custInfo[${index}].custId" value="${custInfo.custId}"/>
                            <a href="javascript:;" class="icons icons-reduce control-btn reduce-btn" onclick="removeItme(this)"></a>
                            <a href="javascript:;" class="icons icons-add-btn control-btn add-more" onclick="addItme(this)"></a>
                            <span class="message"></span>
                        </div>
                    <c:set var="index" value="${index+1}"/>
                    </c:forEach>
                    </div>
                </div>
                <div class="form-box add-task">
                    <h4>会议设置</h4>
                    <div class="input-bt-line last">
                        <div class="input-block task">
                            <label for="confConfig" class="input-label">外呼设置</label>
                            <span class="drop-list-box theme" style="width: 60%;margin-right: 5%">
                            <input type="text" id="confConfig" name="confConfig" value=
                            <c:if test="${confInfo.confConfig=='1'}">"到达会议预约时间，自动外呼顾问"</c:if>
                            <c:if test="${confInfo.confConfig=='2'}">"有咨询客户入会后，自动外呼顾问"</c:if>
                            <c:if test="${confInfo.confConfig=='3'}">"所有咨询客户入会后，自动外呼顾问"</c:if>
                            <c:if test="${confInfo.confConfig=='4'}">"与咨询客户确认后，由客服外呼顾问"</c:if>
                            placeholder="总时间"  class="input-pub  last select-menu" style="background-position: 95%;"/>
                                <ul class="task-time">
                                    <li class="conf_config_1" data-time="1">到达会议预约时间，自动外呼顾问</li>
                                    <li class="conf_config_2" data-time="2">有咨询客户入会后，自动外呼顾问</li>
                                    <li class="conf_config_3" data-time="3">所有咨询客户入会后，自动外呼顾问</li>
                                    <li class="conf_config_4" data-time="4">与咨询客户确认后，由客服外呼顾问</li>
                                </ul>
                            </span>
                            <label class="input-label advanced-set" onclick="advanced()">高级设置</label>
                            <span class="message pwd0_msg"></span>
                        </div>
                        <div class="advanced">
                            <div>
                            <span class="advanced-item">会议录音</span>
                            <input type="checkbox" name="tapedMark" id="set-luyin" <c:if test="${confInfo.tapedMark=='2'}"> checked </c:if>/>
                            <label for="set-luyin">启用会议自动录音</label>
                            <span class="message pwd0_msg"></span>
                            </div>
                            <div>
                            <span class="advanced-item">会议通知</span>
                                <input type="checkbox" name="notifyemail" id="notifyemail" value="${confInfo.ifMail}" <c:if test="${confInfo.ifMail=='1'}"> checked </c:if> onclick="notify(this)"/>
                            <label for="notifyemail" style="margin-right: 50px;">邮件通知</label>
                                <input type="checkbox" name="notifysms" id="notifysms" value="${confInfo.ifSms}" <c:if test="${confInfo.ifSms=='1'}"> checked </c:if> onclick="notify(this)"/>
                            <label for="notifysms">短信通知</label>
                            <span class="message pwd0_msg"></span>
                            </div>
                            <div>
                            <span class="advanced-item">入会限制</span>
                            <input type="radio" name="join" id="join1" value="1" <c:if test="${confInfo.ifLimitParty=='1'}"> checked </c:if>/>
                            <label for="join1" style="margin-right: 50px;">仅允许邀请的电话主动入会</label>
                            <input type="radio" name="join" id="join" value="0" <c:if test="${confInfo.ifLimitParty=='0'}"> checked </c:if>/>
                            <label for="join">不限制主动入会电话</label>
                            <span class="message pwd0_msg"></span>
                        </div>
                        </div>
                        <input type="hidden" id="config" name="config" value=""/>
                        <input type="hidden" id="duration" name="duration" value=""/>
                    </div>
                    <div class="input-block task btn-row">
                        <div class="input-bt-line button text-center">
                            <button type="button" name="button" class="czmm_button" id="sendtask" onclick="javascript:updateConfInfo();">更新会议信息</button>
                            <button type="button" name="button" class="czmm_button cancle" onclick="javascript:cancelConfInfo();">取消</button>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div>
    <div class="footer">
                              全时云商务服务股份有限公司 Copyright © 2017 京ICP备08005473号
    </div>
    <ul class="conf-user-list"></ul>
</body>
</html>
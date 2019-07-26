<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../common/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<link rel="stylesheet" href="${CSS_PATH}/taskList.css" />
<script type="text/javascript" src="${JS_PATH}/deal/support/taskList.js"></script>
<script src="${JS_PATH}/jquery/layer/layer.js"></script>
</head>

<body>
    <div class="header">
        <div class="mainbody top">
            <a href="javascript:;" class="icons icons-logo"></a>
            <div class="user-info">
                <a href="javascript:;" class="top-user">
                	<span id="noCall" style="display: none" class="icons icons-db-phone service-icons"></span>
                	<span id="inCall" style="display: none" class="icons icons-phone service-icons"></span>
                	<span id="userName"></span><span class="arrow"></span></a>
                <div class="menus">
                    <ul class="drop-menu">
                        <li> <a href="javascript:;" id="hangup" style="display: none">挂断电话</a> </li>
                        <li> <a href="javascript:;" id="callOther2" >呼叫2平台</a> </li>
                        <li> <a href="javascript:;" id="callOther7" >呼叫7平台</a> </li>
                        <li> <a href="javascript:;" id="getConfNum" >任务量查询</a> </li>
                        <li> <a href="javascript:;" id="logout">退出登录</a></li>
                    </ul>
                </div>
                
	            <div id="calldiv2" class="callout-box" style="display: none;">
					<form id="callForm2" class="send-record">
						<p>平台2外呼，内线请加2#</p>
						<div class="td-input-block">
							<input type="email" id="phoneNum2" name="phoneNum2" class="td-email form-control input-pub">
							<div type="button" id="phoneCall2" class="td-send-btn sm-btn">外呼</div>
						</div>
					</form>
				</div>
				
	            <div id="calldiv7" class="callout-box" style="display: none;">
					<form id="callForm7" class="send-record">
						<p>平台7外呼，内线请加2#</p>
						<div class="td-input-block">
							<input type="email" id="phoneNum7" name="phoneNum7" class="td-email form-control input-pub">
							<div type="button" id="phoneCall7" class="td-send-btn sm-btn">外呼</div>
						</div>
					</form>
				</div>
            </div>
        </div>
    </div>
    <div class="mainbody mt30">
        <div class="content-main">
            <div class="table-box show service">
                <table id="taskList2" class="pub-table">
                    <thead>
                        <tr id="title">
                            <td width="35%">(平台2)主题</td>
                            <td width="15%">会议类型</td>
                            <td width="20%">时间</td>
                            <td width="100">在线</td>
                            <td width="100">离线</td>
                            <td width="100">领任务</td>
                        </tr>
                    </thead>
                    <tbody>
                        
                    </tbody>
                </table>
                <table id="taskList7" class="pub-table">
                    <thead>
                        <tr id="title">
                            <td width="35%">(平台7)主题</td>
                            <td width="15%">会议类型</td>
                            <td width="20%">时间</td>
                            <td width="100">在线</td>
                            <td width="100">离线</td>
                            <td width="100">领任务</td>
                        </tr>
                    </thead>
                    <tbody>
                        
                    </tbody>
                </table>
                <div id ="notask" class="no-task" style="display: none;">
                    <span class="icons icons-nobg"></span> 任务池无任务
                </div>
            </div>

        </div>
    </div>
    <div class="footer">
        全时云商务服务股份有限公司 Copyright © 2017 京ICP备08005473号
    </div>

</body>

</html>
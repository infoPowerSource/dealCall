<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../common/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<link rel="stylesheet" href="${CSS_PATH}/taskList.css" />
<script type="text/javascript" src="${JS_PATH}/deal/support/taskHandleAlarm.js"></script>
</head>
<body class="iframe-body service">
<script type="text/javascript">
    var confId = '${confId}';
    var confType = '${confType}';
    var supportID = '${supportID}';
</script>
  <div class="table-box show">  
        <div class="box-fixed service">
            <form action="">
                <div class="task-title">操作提示</div>
                <div class="task-info">
                    <div class="item"><span class="mr5">1.与咨询客户电话沟通</span>
                        <div class="drop-list-box service user">
                            <input type="text" name="bg-time" readonly="true" value="" id="task-uses" class="input-pub select-menu" />
                            <ul id="select-users" class="task-time">
                            </ul>
                        </div>
                        <a href="javascript:;" id="callUser" class="td-btn service">呼叫</a>
                        <a href="javascript:;" id="callBackUser" class="td-btn service send" >送回会中</a>
                    </div>
                    <div class="item"><span class="mr5">2.确认时间后外呼顾问入会</span>
                        <div class="drop-list-box service user">
                            <input type="text" name="bg-time" readonly="true" value="" id="task-hour" class="input-pub select-menu" />
                            <ul id="select-hour" class="task-time">
                                <li data-time="0">立即外呼</li>
                                <li data-time="1">定时外呼</li>
                            </ul>
                        </div>
						<div id="befor_call" class="text_with_input td-btn"> 
							<input id="calltype" name="calltype" value="5" ><label>分钟后呼叫</label> 
						</div> 
						<div id="after_call" class="text_with_input td-btn">
							<font color="#436EEE">
							<label>即将呼叫</label><input id="callRemainTime" name="callRemainTime" value="5" >
							</font>
						</div> 
						<div id="callingStatus" class="text_with_input td-btn"> 
							<font color="#436EEE">
							<label>呼叫中……</label>
							</font>
						</div> 
						<a href="javascript:;" class="td-btn service" id="callProfessional">外呼</a>
						<a href="javascript:;" class="td-btn service" id="cancelProfessional">取消</a>
					</div>
                    <div class="item">
                    	<span>3.确定完成此任务</span>
                    	<a href="javascript:;" class="td-btn service check-task">完成任务</a>
                    	<span class="warning-hint">顾问尚未入会，确定要完成此任务？
                    		<a href="javascript:;" class="check-item yes"><span class="icons icons-db-check"></span>确定</a> 
                    		<a href="javascript:;" class="check-item cancle"><span class="icons icons-del"></span>取消</a>
                    	</span>
                    	<span class="warning-hint-none">确定要完成此任务？
                    		<a href="javascript:;" class="check-item yes"><span class="icons icons-db-check"></span>确定</a> 
                    		<a href="javascript:;" class="check-item cancle"><span class="icons icons-del"></span>取消</a>
                    	</span>
                    </div>
                    <div class="item"><span class="mr5">* 会议问题反馈人：</span><span id="feedbackName"></span><span id="feedbackPhone"></span></div>
                </div>
            </form>
        </div>
        <div style="width:600px; height:250px; overflow-y:auto;">
        <table id="taskList" class="pub-table iframe service" cellspacing="0" width="600px">
            <thead>
                <tr id="title">
                    <td width="15%">角色</td>
                    <td width="15%">姓名</td>
                    <td width="30%">电话</td>
                    <td width="20%">语音</td>
                    <td width="15%">状态</td>
                </tr>
            </thead>
            <tbody>
                <tr id="showCallOther" style="display：none;">
					<td colspan="6" class="last">
						<form id="call-user">
							<div class="input-block task call-tel">
								<span class="drop-list-box layer-drop"> 
									<input type="text" name="1" readonly="true" value="咨询客户"	id="task-user" class="input-pub s-size select-menu" />
									<ul class="task-time">
										<li data-time="1">咨询客户</li>
										<li data-time="0">顾问</li>
									</ul>
								</span> 
								<input type="text" id="otherName" name="" value=""	placeholder="姓名" class="input-pub s-size">
								<div class="tel-group">
									<span id="messagetext" class="message pwd0_msg"></span>
									<span class="drop-list-box countrycode">
		                                <input type="text" id="country" value="+86" maxlength="4" class="input-pub select-menu" />
		                                <ul id="custInfo" class="code_list">
		                                </ul>
		                            </span>
									<input type="text" id="area" placeholder="区号" maxlength="4" class="num"> 
									<input type="tel" id="otherPhone" maxlength="20" name="" value="" placeholder="电话号码" class="phone phone-main">
								</div> 
								<span class="td-control-call td-call-box"> 
									<span id="call-send" class="td-btn call">呼叫</span>
									<span id="calling" class="td-btn">呼叫中</span>
									<span class="td-btn td-del-btn">取消</span>
								</span>
							</div>
						</form>
					</td>
				</tr>
				<tr id="CallOtherBotton" class="call-other-btn-wrap">
					<td colspan="6">
						<a href="javascript:;" class="call-other">
							<span class="icons icons-add-btn"></span>
							<span>呼叫其他号码</span>
						</a>
					</td>
				</tr>
            </tbody>
        </table>
        </div>
    </div>
    <div id="improtantMessage" class="warning-box" style="display：none;">
        <span class="icons icons-Improtant"></span><strong>紧急：</strong>有<span id="taskNum"></span>个紧急任务等待领取，请尽快完成任务并领取紧急任务！
    </div>
</body>
</html>
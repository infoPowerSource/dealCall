<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../common/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<link rel="stylesheet" href="${CSS_PATH}/confDetail.css" />
<script type="text/javascript"
	src="${JS_PATH}/deal/conference/confDetailEdit.js?r=1"></script>	<link rel="stylesheet" href="${CSS_PATH}/confDetailEdit.css" />
	<!-- [if IE]>
		<style>
		#showCallOther .td-control-call.td-call-box{
			left:467px
		}
		</style>
    <![endif]-->
</head>
<body class="iframe-body">
	<input type="hidden" id="confCode"
		value="${confMon.conference.confBillingcode}" />
	<input type="hidden" id="confId" value="${confMon.conference.confId}" />
	<div class="table-box show">
		<div class="box-fixed">
			<div class="task-title">会议详情</div>
			<div class="task-info">
				<p>
					咨询客户入会密码：<span class="task-pwd">${confMon.conference.chairmanPassword}</span>
				</p>
				<p class="task-pwd-record-wrap">
					<span>顾问入会密码：<span class="task-pwd">${confMon.conference.partyPassword}</span></span>
					<span class="record-staus-wrap">
						<span class="no-record record-status-item">
							<span class="record-title" style="margin-right: 5px">录音未开始</span>
							<span class="record-btn btn-start">开始</span>
						</span>
						<span class="recording record-status-item">
							<span class="recording-icon"></span>
							<span class="record-title" style="margin-right: 5px">正在录音...</span>
							<span class="record-btn btn-pause">暂停</span>
						</span>
						<span class="paused record-status-item">
							<span class="pause-icon"></span>
							<span class="pause-icon"></span>
							<span class="record-title" style="margin-right: 5px">录音已暂停</span>
							<span class="record-btn btn-start">开始</span>
						</span>
					</span>
				</p>
			</div>
		</div>
		<table class="pub-table iframe">
			<thead>
				<tr>
					<td width="15%">角色</td>
					<td width="25%">姓名</td>
					<td width="20%">电话</td>
					<td width="12%">状态</td>
					<td width="20%">操作</td>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="party" items="${confMon.partyInfo}">
					<tr class="user-item" data-partyPhone="${party.partyPhone}" data-partyName="${party.partyName}" data-partyRole="${party.partyRole}" data-confBillingcode="${confMon.conference.confBillingcode}">
						<td width="15%"><c:if test="${party.partyRole==0}">
						顾问</c:if> <c:if test="${party.partyRole==1}">
						咨询客户</c:if></td>
						<td width="25%">${party.partyName}</td>
						<td width="20%">${party.partyPhone}</td>
						<c:if test="${confMon.conference.confStatus == 1}">
							<c:if test="${party.partyStatus==0}">
								<td width="12%"><span class="online">在线</span></td>
								<td width="20%"></td>
							</c:if>
							<c:if test="${party.partyStatus==1}">
								<td width="12%"><span class="offline">离线</span></td>
								<td width="20%"><span class="td-control-call call"> <span id="${party.partyPhone}" class="td-btn td-call-btn call">呼叫</span>
								</td>
							</c:if>
							<c:if test="${party.partyStatus==2}">
								<td width="12%"><span class="connect">呼叫中</span></td>
								<td width="20%"></td>
							</c:if>
						</c:if>
						<c:if test="${confMon.conference.confStatus != 1}">
							<td width="12%"></td>
							<td width="20%"></td>
						</c:if>
					</tr>
				</c:forEach>
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
				<tr style="display:none"></tr>
				<tr class="call-other-btn-wrap" style="display: ${confMon.conference.confStatus==1 ? 'block' : 'none'}">
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
</body>
</html>



<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../common/base.jsp"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<meta charset="UTF-8">
	<meta content="yes" name="apple-mobile-web-app-capable">
	<meta content="yes" name="apple-touch-fullscreen">
	<meta content="telephone=no,email=no" name="format-detection">
	<meta content="maximum-dpr=1" name="flexible" />
	<meta name="viewport" content="initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no">
</head>
<body>
	<header id="header" class="mui-bar mui-bar-nav s-bgc-4882bf">
			<h1 class="mui-title">发送会议录音</h1>
			<button class="mui-action-back mui-btn mui-btn-blue mui-btn-link mui-btn-nav mui-pull-left" style="font-size: 15px"><span class="mui-icon mui-icon-left-nav"></span>返回</button>
		</header>
		<div id="app" class="mui-content">
			<form class="mui-input-group f-mt35">
				<input type="hidden" id="confId" value="${confInfo.confId}" />
				<div class="mui-input-row-2">
					<input type="email" id="recode_email" placeholder="邮箱" class="f-inp" v-model.trim="form.huiyi.ema.v" data-id="emai" maxlength="30">
				</div>
			</form>
			<div class="mui-input-row-2 m-btn-2">
				<button type="button" class="mui-btn mui-btn-primary mui-btn-block f-btn f-btn-middle s-bgc-4882bf" id="luyin" data-id="Huiyi" :disabled="form.huiyi.ema.v==''">发送邮件</button>
			</div>
		</div>
		<div class="radioSendRecords" style="padding: 0 28px;
			<c:if test="${confInfo.radioSendRecord.size()==0}">display: none</c:if>
				">
			<p style="margin-top: 14px;line-height: 20px;font-size: 16px;">录音发送记录：</p>
			<ol style="max-height:80px;line-height:20px;font-size: 14px;font-family: PingFangSC-Light;">
				<c:forEach var="item" items="${confInfo.radioSendRecord}" varStatus="status">
					<li style="margin-top: 10px;">
						<span>${status.index + 1}. <fmt:formatDate value="${item.sentTime}" pattern="yyyy年MM月dd日 HH:mm"/></span>
						<br>
						<span style="padding-left: 20px">${item.emailReceiver}</span>
					</li>
				</c:forEach>
			</ol>
		</div>
	<script type="text/javascript" src="${JS_PATH}/appjs/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" src="${JS_PATH}/appjs/vue.js"></script>
	<script type="text/javascript" src="${JS_PATH}/appjs/bus-vue2017.js"></script>
	<script type="text/javascript" src="${JS_PATH}/appjs/layer/layer.js"></script>
	<script type="text/javascript" src="${JS_PATH}/appjs/common.js"></script>
	<script type="text/javascript" src="${JS_PATH}/app/conference/sendConfRecord.js"></script>
</body>
</html>
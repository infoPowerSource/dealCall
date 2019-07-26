<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../common/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<meta charset="UTF-8">
	<meta content="yes" name="apple-mobile-web-app-capable">
	<meta content="yes" name="apple-touch-fullscreen">
	<meta content="telephone=no,email=no" name="format-detection">
	<meta content="maximum-dpr=1" name="flexible"/>
	<meta name="viewport" content="initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no">
	<link rel="stylesheet" href="${CSS_PATH}/confReport.css"/>
</head>
<body>
	<header id="header" class="mui-bar mui-bar-nav s-bgc-4882bf">
		<h1 class="mui-title">会议报告</h1>
		<button class="mui-action-back mui-btn mui-btn-blue mui-btn-link mui-btn-nav mui-pull-left" style="font-size: 15px"><span class="mui-icon mui-icon-left-nav"></span>返回</button>
	</header>
	<div id="app" class="mui-content" v-cloak>
		<div class="report-info">
			<p class="info-item" style="width: 350px;">会议主题：{{title}}</p>
			<p class="info-item">会议时间：{{time}}</p>
			<p class="info-item">会议总时长：{{duration}}</p>
<!-- 			<p class="info-item">会议总方数：{{nums}}方</p> -->
		</div>
		<div class="report-members">
			<ul>
				<li class="head member-item">
					<span class="role">角色</span>
					<span class="name">姓名</span>
					<span class="phone">电话</span>
					<span class="enterTime">进会时间</span>
					<span class="exitTime">退会时间</span>
					<span class="duration">与会时长</span>
				</li>
				<li class="content member-item" v-for="(item, key) of members" key="key">
					<span class="role">{{item.role}}</span>
					<span class="name" v-bind:title="item.name">{{item.name}}</span>
					<span class="phone">{{item.tel}}</span>
					<span class="enterTime">{{item.inTime}}</span>
					<span class="exitTime">{{item.outTime}}</span>
					<span class="duration">{{parseInt(item.durTime) || 0}}分钟</span>
				</li>
				<li class="content member-item" v-for="(item, key) of emptyMembers" key="key">
					
				</li>
			</ul>
		</div>
	</div>
</body>
<script type="text/javascript" src="${JS_PATH}/app/conference/confReport.js"></script>
</html>
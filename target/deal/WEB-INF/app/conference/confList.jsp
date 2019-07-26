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
	<meta content="maximum-dpr=1" name="flexible" />
	<meta name="viewport" content="initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no">
	<link rel="stylesheet" type="text/css" href="${CSS_PATH}/confList.css"/>
</head>

<body>
	<header id="header" class="mui-bar mui-bar-nav s-bgc-4882bf">
		<h1 class="mui-title">
			<div class="m-check-time m-rili" >
				<div class="btn" data-options='{"type":"date","beginYear":2017,"endYear":2050}'>
					<div id="m-nday" class="m-nday">今天</div>
					<div class="m-tit" id="m-days" >
						<div id="m-months" class="m-months" style="font-size: 17px;line-height: 34px;">n月n日</div>
						<div id="m-years" class="m-years" style="display: none">n年</div>
					</div>
					<i class="u-arrows u-a-t">选择日期</i>
				</div>
			</div>
		</h1>
		<a href="#picture" class="mui-icon mui-pull-left">
			<img src="${IMAGE_PATH}/menu.png" class="icon u-more"/>
			<!-- <svg class="icon u-more"><use xlink:href="#more"/></svg> -->
		</a>
		<a href="#" class="mui-icon mui-pull-right">
			<img src="${IMAGE_PATH}/create.png" class="icon u-icon" onclick="createConf()"/>
		</a>
		<a href="#" class="mui-icon mui-pull-right-1">
			<img src="${IMAGE_PATH}/search.png" class="icon u-icon" onclick="searchConf()"/>
		</a>
	</header>
	<div class="mui-content">
		<div class="m-tabs m-tabs-1 g-h100 f-tabs">
			<ul id="tab_li" class="m-tabs-hd f-tabs-hd f-cb li-c2 f-tac" style="position: fixed;z-index: 1;width: 100%;">
				<li onclick="changeTab(1)" class="m-hd-list f-hd-list on">会议列表(<span id="onlineNum"></span>)</li>
				<li onclick="changeTab(2)" class="m-hd-list f-hd-list ">已结束会议(<span id="offlineNum"></span>)</li>
			</ul>
			<div class="m-tabs-bd f-tabs-bd" style="padding-top: 36px;">
				<div id="div_conf_live" class="m-bd-list f-bd-list">
					<div id ="notask1" class="m-tips-img f-tac" style="display: none;" >
							<img src="${IMAGE_PATH}/no_pic.png" class="m-img m-def"/>
							<div class="m-txt">没有会议日程</div>
					</div>
					<ul id="conf_live" class="m-hylb" style="padding-left: 0">
						
					</ul>
					<div class="no-data" style="display: none;">
						<span style="margin-right:10px;"></span><img src="${IMAGE_PATH}/no_data.png"/>
					</div>
				</div>
				<div id="div_conf_end" class="m-bd-list f-bd-list" style="display: none;">
					<div id ="notask2" class="m-tips-img f-tac" style="display: none;" >
							<img src="${IMAGE_PATH}/no_pic.png" class="m-img m-def"/>
							<div class="m-txt">没有会议日程</div>
					</div>
					<ul id="conf_end" class="m-hylb" style="padding-left: 0">
						
					</ul>
					<div class="no-data" style="display: none;">
						<span style="margin-right:10px;"></span><img src="${IMAGE_PATH}/no_data.png"/>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div id="picture" class="mui-popover mui-popover-action mui-popover-bottom">
		<ul id="fuc_other" class="mui-table-view">
			<li class="mui-table-view-cell">
				<a href="#" class="z-hujiao" ontouchstart="searchAccess()">查询接入号</a>
			</li>
			<li class="mui-table-view-cell">
				<a href="#" class="z-hujiao" ontouchstart="modifyPassword()">修改密码</a>
			</li>
			<li class="mui-table-view-cell">
				<a href="#" class="z-hujiao" ontouchstart="loggout()">退出登录</a>
			</li>
		</ul>
		<ul class="mui-table-view">
			<li class="mui-table-view-cell">
				<a href="#picture" class="z-default"><b>取消</b></a>
			</li>
		</ul>
	</div>
</body>
<script type="text/javascript" src="${JS_PATH}/app/conference/confList.js"></script>
</html>
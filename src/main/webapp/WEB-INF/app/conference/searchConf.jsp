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
	<link rel="stylesheet" type="text/css" href="${CSS_PATH}/searchConf.css"/>
</head>
<body>
	<header id="header" class="mui-bar mui-bar-nav s-bgc-4882bf">
		<h1 class="mui-title">查询会议</h1>
		<button class="mui-action-back mui-btn mui-btn-blue mui-btn-link mui-btn-nav mui-pull-left" style="font-size: 15px"><span class="mui-icon mui-icon-left-nav"></span>返回</button>
	</header>
	<div id="app" class="mui-content">
		<div class="mui-input-group f-mt35">
			<div class="search">
				<div class="mui-input-row-2">
					<form action="javascript:return true;">
					<input type="search" id="searchTaskKeywords" placeholder="输入主题、姓名、手机、邮箱搜索会议" class="f-inp" maxlength="50" style="text-align: left">
					</form>
				</div>
				<span class="icon icon-close hide"></span>
				<span class="icon icon-search"></span>
			</div>
		</div>
		<div class="task-search-title task-list-title" style="padding: 13px"></div>
		<div class="m-tabs m-tabs-1 g-h100 f-tabs">
			<div class="m-tabs-bd f-tabs-bd">
				<div id="div_confList" class="m-bd-list f-bd-list">
					<ul id="confList" class="m-hylb">
						
					</ul>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="${JS_PATH}/appjs/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" src="${JS_PATH}/appjs/vue.js"></script>
	<script type="text/javascript" src="${JS_PATH}/appjs/bus-vue2017.js"></script>
	<script type="text/javascript" src="${JS_PATH}/appjs/layer/layer.js"></script>
	<script type="text/javascript" src="${JS_PATH}/appjs/common.js"></script>
	<script type="text/javascript" src="${JS_PATH}/app/conference/searchConf.js"></script>
</body>
</html>
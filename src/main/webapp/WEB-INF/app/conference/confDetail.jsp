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
	<style>
		body {
			overflow-x: auto;
		}
		#custList {
			table-layout: fixed;
		}
	</style>
</head>
<body>
	<header id="header" class="mui-bar mui-bar-nav s-bgc-4882bf">
			<h1 class="mui-title">会议详情</h1>
			<button id="callback" class="mui-btn mui-btn-blue mui-btn-link mui-btn-nav mui-pull-left" style="font-size: 15px"><span class="mui-icon mui-icon-left-nav"></span>返回</button>
			<a href="#picture" class="mui-icon mui-pull-right">
				<img src="${IMAGE_PATH}/menu.png" class="icon u-more"/>
			</a>
		</header>
		<div id="app" class="mui-content">
			<ul class="mui-input-group mui-input-group-2 f-mt15 f-pl24 conf-detail-ul">
				<input type="hidden" id="confCode" value="${confMon.conference.confBillingcode}" />
				<input type="hidden" id="confId" value="${confMon.conference.confId}" />
				<input type="hidden" id="confStatus" value="${confMon.conference.confStatus}" />
				<li class="mui-input-row-2 m-c2 f-cb f-bb-E7E7E7">
					<label for="" class="m-c2-hd">会议主题</label>
					<div class="m-c2-bd">
						<div class="m-txt conf-name">${confMon.conference.confName}</div>
					</div>
				</li>
				<li class="mui-input-row-2 m-c2 f-cb f-bb-E7E7E7">
					<label for="" class="m-c2-hd">预约时间</label>
					<div class="m-c2-bd">
						<div class="m-txt">${confMon.confTime}</div>
					</div>
				</li>
				<li class="mui-input-row-2 m-c2 f-cb f-bb-E7E7E7">
					<label for="" class="m-c2-hd">咨询客户密码</label>
					<div class="m-c2-bd">
						<div class="m-txt">${confMon.conference.chairmanPassword}</div>
					</div>
				</li>
				<li class="mui-input-row-2 m-c2 f-cb ">
					<label for="" class="m-c2-hd">顾问密码</label>
					<div class="m-c2-bd">
						<div class="m-txt">${confMon.conference.partyPassword}</div>
					</div>
				</li>
			</ul>
			<table id="custList"  width="100%" class="m-table m-table-hyxq">
				<thead>
					<tr id="title" class="f-bb-E7E7E7">
						<th width="100px"><div class="m-tit">角色</div></th>
						<th class="nameLength"><div class="m-tit">姓名</div></th>
						<th width="100px"><div class="m-tit">电话</div></th>
						<th width="90px"><div class="m-tit">状态</div></th>
					</tr>
				</thead>
				<tbody>
				</tbody>
			</table>			
		</div>
		
		<div id="picture" class="mui-popover mui-popover-action mui-popover-bottom">
			<ul id="fuc_other" class="mui-table-view">
				<li class="mui-table-view-cell">
					<a href="#edit" class="z-hujiao" ontouchstart="editConf()">编辑会议</a>
				</li>
				<li class="mui-table-view-cell">
					<a href="#del" class="z-hujiao" ontouchstart="deleteConf()">删除会议</a>
				</li>
				<li class="mui-table-view-cell">
					<a href="#hujiao" class="z-hujiao" ontouchstart="callOther()">呼叫其他号码</a>
				</li>
			</ul>
			<ul class="mui-table-view">
				<li class="mui-table-view-cell">
					<a href="#picture" class="z-default"><b>取消</b></a>
				</li>
			</ul>
		</div>
		<div id="hujiaozhuanjia" class="mui-popover mui-popover-action mui-popover-bottom">
			<ul class="mui-table-view">
                <li class="mui-table-view-cell" style="height: 48px">
                    <div class="z-jinru1" style="line-height: 24px;">呼叫<span id="customer"></span>进入会议</div>
                </li>
				<li class="mui-table-view-cell">
					<a href="#hujiaojinru" class="z-jinru" ontouchstart="callout()" style="color: rgba(72,130,191,1) !important;">呼叫</a>
				</li>
			</ul>
			<ul class="mui-table-view">
				<li class="mui-table-view-cell">
					<a href="#hujiaozhuanjia" class="z-default"><b>取消</b></a>
				</li>
			</ul>
		</div>
</body>
<script type="text/javascript" src="${JS_PATH}/app/conference/confDetail.js"></script>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../common/base.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<link rel="stylesheet" href="${CSS_PATH}/font-awesome.min.css" />
<link rel="stylesheet" href="${CSS_PATH}/confList.css" />
<script type="text/javascript"
	src="${JS_PATH}/deal/conference/confList.js"></script>
</head>
<body>
	<div class="header">
		<div class="mainbody top">
			<a class="icons icons-logo"></a>
			<div class="user-info">
				<div class="calendar">
					<div class="show_date">
						<span id="show_date"></span>&nbsp;<span class="close fa fa-close"></span>
					</div>
					<span class="icon icon-date" id="date-time"></span>
				</div>
				<div class="search">
					<input id="searchTaskKeywords" class="input" placeholder="输入主题、姓名、手机、邮箱搜索会议"  />
					<span class="icon icon-close hide"></span>
					<span class="icon icon-search"></span>
				</div>
				<a href="javascript:;" class="top-user"><%=request.getSession().getAttribute("userName")%><span
					class="arrow"></span></a>
				<div class="menus">
					<ul class="drop-menu">
						<li><a href="javascript:;" id="serchAccess">查询接入号</a></li>
						<li><a href="javascript:;" id="modify-password">修改密码</a></li>
						<li><a href="javascript:;" id="logout">退出登录</a></li>
					</ul>
				</div>
			</div>
		</div>
	</div>
	<div class="conflist-container">
		<div class="mainbody">
			<div class="main-tag-box">
				<ul class="tab-wrap task-list-title">
					<li class="first icons current">会议列表( <span class="count"></span> )
					</li>
					<li class="last icons">已结束会议( <span class="count"></span> )
					</li>
				</ul>
				<div class="task-search-title task-list-title"></div>
				<div class="sm-btn-box">
					<a href="" class="sm-btn add-conf">安排会议</a>
				</div>
			</div>
			<div class="content-main">
				<div class="content-confLive-result data-content">
					<div class="no-task data-result-content">
						<span class="icons icons-nobg">没有会议日程 </span> 
					</div>
					
					<div class="task-list data-result-content">
						<div class="conf-result-table-head">
							<table class="pub-table table-conf">
								<thead>
									<tr>
										<td class="conf-date" width="25%"></td>
										<td>主题</td>
										<td width="15%">客户状态</td>
										<td width="15%">顾问状态</td>
										<td width="16%"></td>
									</tr>
								</thead>
							</table>
						</div>
						<div class="conf-result-table-content">
						</div>
					</div>
				</div>
				<div class="content-confOver-result data-content">
					<div class="no-task data-result-content">
						<span class="icons icons-nobg">没有会议日程 </span>
					</div>
					
					<div class="task-list data-result-content">
						<div class="conf-result-table-head">
							<table class="pub-table table-conf">
								<thead>
									<tr>
										<td class="conf-date" width="25%"></td>
										<td width="30%">主题</td>
										<td width="15%"></td>
										<td width="15%">录制</td>
										<td width="15%">报告</td>
									</tr>
								</thead>
							</table>
						</div>
						<div class="conf-result-table-content">
							
						</div>
					</div>
				</div>
				<div class="content-search-result data-content">
					<div class="no-task data-result-content">
						<span class="icons icons-nobg">没有会议日程 </span>
					</div>
					
					<div class="task-list data-result-content">
						<div class="conf-result-table-head">
							<table class="pub-table table-conf">
								<thead>
									<tr>
										<td width="30%">预约时间</td>
										<td colspan="2">主题</td>
										<td width="15%"></td>
										<td width="15%"></td>
									</tr>
								</thead>
							</table>
						</div>
						<div class="conf-result-table-content">
							<table class="pub-table table-conf">
								<tbody></tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="footer">全时云商务服务股份有限公司 Copyright © 2017 京ICP备08005473号</div>
	</div>
<script id="sendRecordsTpl" type="text/html">
	<div class="radioSendRecords"
		 {{#  if(d.length === 0){ }}
		 style="display: none"
		 {{#  } }}
	>
		<p style="margin-top: 14px;line-height: 20px;">录音发送记录：</p>
		<ol style="max-height:80px;overflow-x: hidden;line-height:15px;font-size: 12px;">
			{{#  layui.each(d, function(index, item){ }}
			<li style="margin-top: 10px;">
				<span>{{ index+1 + '. ' +item.sentTime }}</span>
				<br>
				<span>{{ item.emailReceiver }}</span>
			</li>
			{{#  }); }}
		</ol>
	</div>
</script>
<script id="confLiveTpl" type="text/html">
	<tr class="confLive" data-conf-id="{{d.conferenceInfo.confId}}" data-conf-status="{{d.conferenceInfo.confStatus}}">
		<td width="25%">{{d.orderTime}}
			<span class="status offline">
		{{#  if(d.conferenceInfo.confStatus == 1){ }}
		（正在开会）<input id="confId" type="hidden" value="{{d.conferenceInfo.confId}}" />
		{{#  } }}
		{{#  if(d.conferenceInfo.confStatus == 4){ }}
		（会议休息）
		{{#  } }}
		</span>
		</td>
		<td><span class="item-title begin">{{d.conferenceInfo.confName}}</span></td>
		{{#  if(d.conferenceInfo.confStatus == 0 || d.conferenceInfo.confStatus == 5){ }}
		<td width="15%"></td>
		<td width="15%"></td>
		{{#  } else { }}
		<td width="15%"><span class="online">在线({{d.hostonline.length}})</span><span class="offline">离线({{d.hostoffline.length}})</span></td>
		<td width="15%"><span class="online">在线({{d.guestonline.length}})</span><span class="offline">离线({{d.guestoffline.length}})</span></td>
		{{#  } }}
		<td width="16%">
			{{#  if(d.conferenceInfo.confStatus == 0 || d.conferenceInfo.confStatus == 5){ }}
			<span class="td-control"><span class="td-btn td-edit-btn">编辑</span><span class="td-btn td-del-btn del-task-btn">删除</span></span>
			{{#  } }}
		</td>
	</tr>
</script>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<html>
<body>
	<div class="mainbody">
		<div class="content-main">
			<c:if test="${confInfo!=null}">
				<c:if test="${fn:length(confInfo.confLive)!=0}">
					<div class="table-box show">
						<table class="pub-table">
							<thead>
								<tr>
									<td width="240">预约时间</td>
									<td width="300">主题</td>
									<td width="204">客户状态</td>
									<td width="100">顾问状态</td>
									<td></td>
								</tr>
							</thead>

							<tbody>
								<c:forEach var="confOnline" items="${confInfo.confLive}">
									<c:if test="${confOnline.conferenceInfo.confStatus==1}">
										<tr>
											<td>${confOnline.orderTime}<span class="status offline">（正在开会）</span></td>
											<input id="confId" type="hidden"
												value="${confOnline.conferenceInfo.confId}" />
											<td><span
												onclick="showMon('${confOnline.conferenceInfo.confId}','${confOnline.conferenceInfo.confStatus}')"
												class="item-title begin">${confOnline.conferenceInfo.confName}</span></td>
											<td><span class="online">在线（${fn:length(confOnline.hostonline)}）</span><span
												class="offline">离线（${fn:length(confOnline.hostoffline)}）</span></td>
											<td><span class="online">在线（${fn:length(confOnline.guestonline)}）</span></td>
											<td></td>
										</tr>
									</c:if>
									<c:if test="${confOnline.conferenceInfo.confStatus==0}">
										<tr>
											<td>${confOnline.orderTime}</td>
											<td><span
												onclick="showMon('${confOnline.conferenceInfo.confId}','${confOnline.conferenceInfo.confStatus}')"
												class="item-title">${confOnline.conferenceInfo.confName}</span></td>
											<td></td>
											<td></td>
											<td><span > <span 
													onclick="editConf('${confOnline.conferenceInfo.confId}')"
													class="td-btn td-edit-btn">编辑</span> <span
													onclick="deleteConf('${confOnline.conferenceInfo.confId}')"
													class="td-btn td-del-btn del-task-btn">删除</span>
											</span></td>
										</tr>
									</c:if>
								</c:forEach>
							</tbody>

						</table>

					</div>
				</c:if>
			</c:if>
			<c:if test="${confInfo!=null}">
				<c:if test="${fn:length(confInfo.confOver)==0}">
					<c:if test="${fn:length(confInfo.confLive)==0}">
						<div class="no-task">
							<span class="icons icons-nobg"></span> 没有会议日程
						</div>
					</c:if>

				</c:if>

			</c:if>
			<c:if test="${confInfo!=null}">
				<c:if test="${fn:length(confInfo.confOver)!=0}">
					<div class="table-box show">
						<table class="pub-table">
							<thead>
								<tr>
									<td width="240">预约时间</td>
									<td width="560">主题</td>
									<td width="130">录制</td>
									<td>报告</td>
								</tr>
							</thead>

							<tbody>
								<c:forEach var="confOffline" items="${confInfo.confOver}">
									<tr data-id="1588">
										<td>${confOffline.orderTime}</td>
										<td><span class="item-title">${confOffline.confInfo.confName}</span></td>
										<td>
											<div class="record-box">
												<c:if test="${confOffline.radioName !=null}">
													<a onclick="recordFresh()" class="icons icons-luzhi record">
													</a>
												</c:if>
												<div class="td-tips">
													<form id="'+trid+'" class="send-record">
														<p>输入邮箱地址发送会议录音</p>
														<div class="td-input-block">
															<input type="email" id="recordMail" class="td-email"
																placeholder="邮箱地址">
															<button type="button" id="${confOffline.confInfo.confId}"
																class="td-send-btn">发送</button>
														</div>
													</form>
												</div>
												<span class="tips-text">录音已发送</span>
											</div>
										</td>

										<td><c:if test="${confOffline.reportName !=null}">
												<a onclick="downReport(${confOffline.confInfo.confId})"
													class="icons icons-word word"></a>
											</c:if></td>
									</tr>
								</c:forEach>
							</tbody>

						</table>

					</div>

				</c:if>
			</c:if>
		</div>
	</div>
</body>
</html>
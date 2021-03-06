<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../common/baseNoPolyfill.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<script type="text/javascript" src="${JS_PATH}/deal/login/modifyPassword.js"></script>
</head>
<body>
 <div class="header">
	<div class="mainbody top">
            <a class="icons icons-logo"></a>
			<div class="user-info">
				<a href="javascript:;" class="top-user"><%=request.getSession().getAttribute("userName")%><span class="arrow"></span></a>
				<div class="menus">
					<ul class="drop-menu">
						<li><a href="javascript:;" id="serchAccess">查询接入号</a></li>
						<li><a href="/deal/login/modifyPassword">修改密码</a></li>
						<li><a href="/deal/login/logout">退出登录</a></li>
					</ul>
				</div>
			</div>
        </div>
</div>
<div class="mainbody navbar">			
	<div class="navfont"><a href="javascript:history.go(-1);" class="icons icons-back"></a> 修改密码</div>
</div>
		<div class="mainbody">
			<div class="czmm">
				<div class="czmm_title">
					设置并确认新密码
				</div>
				<div class="czmm_hr"></div>
				<form method="post" action="" name="form" id="czmmform" class="form-fgpw">
				<div class="form-box">
					<div class="input-block">
						<input  type="password" maxlength="8" id="oldPassword" name="oldPassword" value="" onblur="checkIsValid()" placeholder="旧密码" class="input-pub username" />
						<span class="message pwd0_msg"></span>
					</div>
					<div class="input-block">
						<input  type="password" maxlength="8" id="password" name="password" value="" onblur="checkIsValid()" placeholder="新密码" class="input-pub username"  />
						<span class="message pwd1_msg"></span>
					</div>
					<div class="input-block">
						<input  type="password" maxlength="8" id="pwd2" name="pwd2" value="" onblur="checkIsValid()" placeholder="确认新密码" class="input-pub username"  />
						<span class="message pwd2_msg"></span>
					</div>
					
					<div class="input-block btn-row">
						<button type="button" name="button" class="czmm_button">提交</button>
					</div>
					</div>
				</form>
				
			</div>
		</div>
		
		<div class="footer user">
			全时云商务服务股份有限公司 Copyright © 2017 京ICP备08005473号
		</div>
</body>
</html>
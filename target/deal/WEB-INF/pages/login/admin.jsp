<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../common/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<link rel="stylesheet" href="${CSS_PATH}/login.css"/>
<script type="text/javascript" src="${JS_PATH}/deal/login/admin.js"></script>
</head>
<body>
	<div class="loginbody">
		<!-- 登录框 -->
		<div class="login-box">
			<h2 class="login-logo">
				<a href="#" class="icons icons-Logo-login"></a>
			</h2>
			<form id="login-form" class="login-form">
				<div class="warning-login" id="warning-login"></div>
				<div class="input-group input-item">
					<span class="icons user  icons-user-active"></span> 
					<input type="text" maxlength="50" data-flag="1" class="input-control input-email" ID="userName" name="userName" onblur="GetPwdAndChk()" autofocus placeholder="邮箱">
					<span class="icons del icons-clear_normal"></span>
				</div>
				<div class="input-group input-item">
					<span class="icons user icons-lock"></span> 
					<input type="password" maxlength="20" data-flag="2" class="input-control input-password" ID="password" name="password" placeholder="密码">
					<span id="showEye" class="icons eye icons-eye"></span> 
					<span class="icons del icons-clear_normal"></span>
				</div>
				<div class="input-group input-item" id="verifyCode" style="display：none;">
					<span class="icons user icons-er-coder"></span> 
					<input type="text" maxlength="4" data-flag="3" id="checkcode" name="checkcode" class="input-control" placeholder="验证码"> 
					<span id="checkcodev" name="checkcodev" class="er-coder"/>
				</div>
				<div class="input-group fg-pwd">
					<label class="rem-pwd-item" for="chkRememberPwd"> 
					<input type="checkbox" class="check-pwd" id="chkRememberPwd"> 
					<span class="icons icons-check-false"></span> 记住密码</label> 
				</div>
				<div class="input-group">
					<button type="button" class="login-btn" onclick="SetPwdAndChk()">登 录</button>
				</div>
			</form>
		</div>
		<div class="copyright">
			<span>POWERED BY 全时</span>
		</div>
	</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../common/basePolyfillNoPass.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<link rel="stylesheet" href="${CSS_PATH}/login.css"/>
<script type="text/javascript" src="${JS_PATH}/deal/login/login.js"></script>
</head>
<body>
	<div class="loginbody">
		<!-- 登录框 -->
		<div class="login-box">
			<h2 class="login-logo">
				<a class="icons icons-Logo-login"></a>
			</h2>
			<input id="importMsgInput_name"  type="hidden"  value="${showContent}" />  
			<form id="checkUser" class="login-form">
				<div class="warning-login" id="warning-login"></div>
				<div class="input-group input-item">
					<span class="icons user  icons-user-active"></span> 
					<input type="text" maxlength="50" data-flag="1" id="username" name="username" value="${username}"  onblur="GetPwdAndChk()"  class="input-control input-email" placeholder="邮箱/手机">
					<span class="icons del icons-clear_normal"></span>
				</div>
				<div class="input-group input-item">
					<span class="icons user icons-lock"></span> 
					<input type="password" maxlength="8" data-flag="2" id="password" name="password" value="${password}" class="input-control input-password" placeholder="密码">
					<span id="showEye" class="icons eye icons-eye"  onclick="eyeClick()"></span> 
					<span class="icons del icons-clear_normal"></span>
				</div>
				<div class="input-group input-item" id="verifyCode">
					<span class="icons user icons-er-coder"></span> 
					<input type="text" maxlength="4" data-flag="3" id="checkcode" name="checkcode" onblur="validatecode()" class="input-control" placeholder="验证码"> 
					<span id="checkcodev" name="checkcodev" class="er-coder"/>
				</div>
				<div class="input-group fg-pwd">
					<label class="rem-pwd-item" for="remember-pwd"> 
					<input type="checkbox" id="remember-pwd" name="rempwd" class="check-pwd"> 
					<span class="icons icons-check-false"></span> 记住密码</label> 
					<a href="/deal/login/forgetpassword" class="fgt-pwd">忘记密码?</a>
				</div>
				<div class="input-group">
					<button type="button" class="login-btn">登 录</button>
				</div>
			</form>
		</div>
		<div class="copyright">
			<p>POWERED BY 全时</p>
		</div>
	</div>
</body>
</body>
</html>
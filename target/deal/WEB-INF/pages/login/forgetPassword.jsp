<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../common/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<script type="text/javascript" src="${JS_PATH}/deal/login/forgetPassword.js"></script>
</head>
<body>
 <div class="header">
	<div class="mainbody top">
            <a class="icons icons-logo"></a>
    </div>
</div>
<div class="mainbody navbar">			
	<div class="navfont"><a href="javascript:history.go(-1);" class="icons icons-back"></a> 忘记密码</div>
</div>
    <div class="mainbody">
        <div class="czmm">
            <div class="czmm_title">
                       通过帐号绑定的邮箱或手机重置密码
            </div>
            <div class="czmm_hr"></div>
            <input id="importMsgInput_name"  type="hidden"  value="${showContent}" />  
            <form method="post" action="" name="form" id="czmmform" class="form-fgpw">
                <div class="form-box">
                    <div class="input-block">
                        <input type="text" maxlength="50" id="username" name="username" onblur="checkUserName()" placeholder="邮箱/手机" class="input-pub username" />
                        <span class="message username_msg"></span>
                    </div>
                    <div class="input-block">
                        <input type="text" name="checkcode" onblur="validateCode()"  class="input-pub checkcode" maxlength="4" placeholder="验证码"/>
                        <!--placeholder="验证码"  -->
                        <span id="checkcodev" name="checkcodev" class="codeimg"></span>
                        <span class="message checkcode_msg"></span>
                    </div>
                    <div class="input-block btn-row">
                        <button type="button" name="button" class="czmm_button">提交</button>
                        <!--  a href="getFromEmail.html">邮箱找回</a>
                        <a href="getFromMobile.html">手机找回</a>-->
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
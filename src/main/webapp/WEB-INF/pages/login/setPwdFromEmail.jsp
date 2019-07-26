<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../common/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<script type="text/javascript" src="${JS_PATH}/deal/login/setPwdFromEmail.js"></script>
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
                                                                        验证邮件已发送到邮箱
                <br /> ${uli.username}
                <input id="importMsgInput_name"  type="hidden"  value="${uli.username}" />  
                <input id="importMsgInput_id"    type="hidden"  value="${uli.id}" />  
                <!--input id="importMsgInput_userDisplayName" type="hidden" value="${uli.userDisplayName}" />  -->
            </div>
            <div class="czmm_title2">点击邮件中的确认链接修改登录密码</div>
            <div class="czmm_hr"></div>
            <form method="post" action="resetPassword.html" name="form" id="czmmform" class="form-fgpw">
                <div class="form-box">
                    <div class="input-block btn-row">
                        <button type="button" name="button" id="button"  onclick="receiveMail()" class="czmm_button">立即查收邮件</button>
                    </div>
                </div>
                <div class="czmm_text2">
                                                                  没有收到邮件？点击这里<a href="#" class="re-send">重新发送邮件</a>
                </div>
            </form>
        </div>
    </div>
    <div class="footer user">
                                                        全时云商务服务股份有限公司 Copyright © 2017 京ICP备08005473号
    </div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../common/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<script type="text/javascript" src="${JS_PATH}/deal/login/setPwdTimeOutFromEmail.js"></script>
</head>
<body>
 <div class="header">
	<div class="mainbody top">
            <a class="icons icons-logo"></a>
    </div>
</div>
<div class="mainbody navbar">			
	<div class="navfont"><a href="${CTX}/login" class="icons icons-back"></a>重置密码</div>
</div>
    <div class="mainbody">
        <div class="czmm">
            <div class="czmm_title">
                                                                        链接已失效，请重新发送验证邮件
                <input id="importMsgInput_id"    type="hidden"  value="${uli.id}" />  
            </div>
            <div class="czmm_hr"></div>
            <form method="post" action="resetPassword.html" name="form" id="czmmform" class="form-fgpw">
                <div class="form-box">
                    <div class="input-block btn-row">
                        <button type="button" name="button" id="button" class="czmm_button">重新发送验证邮件</button>
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
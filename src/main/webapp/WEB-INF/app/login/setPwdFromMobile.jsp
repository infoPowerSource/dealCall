<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="../common/tagContent.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>s
<head>
    <meta charset="UTF-8">
    <meta content="yes" name="apple-mobile-web-app-capable">
    <meta content="yes" name="apple-touch-fullscreen">
    <meta content="telephone=no,email=no" name="format-detection">
    <meta content="maximum-dpr=1" name="flexible"/>
    <meta name="viewport" content="initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no">
    <link rel="stylesheet" href="${CSS_PATH}/mui.min.css">
    <link rel="stylesheet" type="text/css" href="${CSS_PATH}/reset.css"/>
    <link rel="stylesheet" type="text/css" href="${CSS_PATH}/common.css"/>
    <script type="text/javascript" src="${JS_PATH}/appjs/jquery-1.8.3.min.js"></script>
    <script type="text/javascript" src="${JS_PATH}/appjs/vue.min.js"></script>
    <script type="text/javascript" src="${JS_PATH}/appjs/bus-vue2017.js"></script>
    <script type="text/javascript" src="${JS_PATH}/appjs/layer/layer.js"></script>
    <script type="text/javascript" src="${JS_PATH}/deal/login/setPwdFromMobile.js"></script>
</head>
<body>
<header id="header" class="mui-bar mui-bar-nav s-bgc-4882bf">
    <h1 class="mui-title">忘记密码</h1>
    <a class="mui-action-back mui-btn mui-btn-blue mui-btn-link mui-btn-nav mui-pull-left" href="${CTX}/login/forgetpassword" style="font-size: 15px"><span class="mui-icon mui-icon-left-nav"></span>返回</a>
</header>
<div id="app" class="mui-content">
    <div class="m-tips-2 f-tac f-mt10">
        <div class="m-tit">短信验证码已发送到手机</div>
        <div class="m-phone">${uli.username}</div>
        <input id="importMsgInput_name"  type="hidden"  value="${uli.username}" />
        <input id="importMsgInput_id"    type="hidden"  value="${uli.id}" />
        <div class="m-des">请查收短信，并输入短信中的验证码</div>
    </div>
    <form class="mui-input-group" method="post" action="resetPassword.html" name="form" id="czmmform">
        <div class="mui-input-row-2">
            <div class="m-code m-code-0 m-code-2 f-fr">
                <span class="fasong mui-btn" style="line-height:42px;"></span>
            </div>
            <input type="text" id="checkcode" name="checkcode" onblur="checkIsValid()" placeholder="短信验证码" class="f-inp" maxlength="4">
            <span class="message checkcode_msg"></span>
        </div>
    </form>
    <div class="mui-input-row-2 m-btn-2">
        <button type="button" class="czmm_button mui-btn mui-btn-primary mui-btn-block f-btn f-btn-middle s-bgc-4882bf" data-id="MCz">重置密码
        </button>
    </div>
</div>
<%--<script type="text/javascript" src="${JS_PATH}/appjs/common.js"></script>--%>
</body>
</html>
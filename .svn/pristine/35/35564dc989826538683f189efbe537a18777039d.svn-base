<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../common/tagContent.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
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
    <script src="${JS_PATH}/jquery/layui/layui.js"></script>
    <script type="text/javascript" src="${JS_PATH}/deal/login/setPwdFromEmail.js"></script>
</head>
<body>
<header id="header" class="mui-bar mui-bar-nav s-bgc-4882bf">
    <h1 class="mui-title">忘记密码</h1>
    <a class="mui-action-back mui-btn mui-btn-blue mui-btn-link mui-btn-nav mui-pull-left" href="${CTX}/login/forgetpassword" style="font-size: 15px"><span class="mui-icon mui-icon-left-nav"></span>返回</a>
</header>
<div id="app" class="mui-content" v-cloak>
    <form method="post" action="resetPassword.html" name="form" id="czmmform" class="form-fgpw">
    <div class="m-tips-2 f-tac f-mt10">
        <div class="m-tit">验证邮件已发送到邮箱</div>
        <div class="m-phone">${uli.username}</div>
        <input id="importMsgInput_name"  type="hidden"  value="${uli.username}" />
        <input id="importMsgInput_id"    type="hidden"  value="${uli.id}" />
        <div class="m-des">点击邮件中的确认链接修改登录密码</div>
    </div>
    <div class="mui-input-row-2 m-btn-2 f-mt_20">
        <a id="button"  href="${CTX}/login" class="mui-btn mui-btn-primary mui-btn-block f-btn f-btn-middle s-bgc-4882bf" style="font-size: 15px">返回登录</a>
        <div class="m-tips-2 f-tac">
            <div class="m-des">没有收到邮件？点击这里<a class="s-c-4882bf f-tdu re-send">重新发送邮件</a></div>
        </div>
    </div>
    </form>
</div>

<script type="text/javascript" src="${JS_PATH}/appjs/common.js"></script>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
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
    <link rel="stylesheet" type="text/css" href="${CSS_PATH}/login.css"/>
    <script type="text/javascript" src="${JS_PATH}/appjs/jquery-1.8.3.min.js"></script>
    <script type="text/javascript" src="${JS_PATH}/appjs/vue.js"></script>
    <script type="text/javascript" src="${JS_PATH}/appjs/bus-vue2017.js"></script>
    <script type="text/javascript" src="${JS_PATH}/appjs/layer/layer.js"></script>
    <script type="text/javascript" src="${JS_PATH}/app/login/forgetPwd.js"></script>
</head>
<body>
<header id="header" class="mui-bar mui-bar-nav s-bgc-4882bf">
    <h1 class="mui-title">忘记密码</h1>
    <a class="mui-action-back mui-btn mui-btn-blue mui-btn-link mui-btn-nav mui-pull-left" href="${CTX}/login" style="font-size: 15px">
        <span class="mui-icon mui-icon-left-nav"></span>返回
    </a>
</header>
<div id="app" class="mui-content" v-cloak>
    <form class="mui-input-group f-mt35">
        <div class="mui-input-row-2">
            <input type="text" placeholder="邮箱/手机" class="f-inp" v-model.trim="user.username" @blur="checkUserName()" maxlength="50">
        </div>
    </form>
    <form class="mui-input-group f-mt10">
        <div class="mui-input-row-2">
            <div class="m-code m-code-0 m-code-1 f-fr">
                <span class="er-coder m-img" style="width: 125px;" @click="showCaptcha()" v-text="captcha"/>
            </div>
            <input type="text" placeholder="验证码" class="f-inp"
                   @blur="checkCaptcha()" @keydown.enter="submit()"
                   v-model.trim="user.checkcode" maxlength="4">
        </div>
    </form>
    <div class="mui-input-row-2 m-btn-2">
        <button type="button" id="login-btn"
                class="mui-btn mui-btn-primary mui-btn-block f-btn f-btn-middle s-bgc-4882bf"
                @click="submit()" :disabled="user.userName=='' || user.checkcode.length < 4 ">提交
        </button>
    </div>
</div>
</body>
</html>
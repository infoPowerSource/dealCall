<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../common/tagContent.jsp" %>
<html>
    <head>
        <meta charset="UTF-8">
        <meta content="yes" name="apple-mobile-web-app-capable">
        <meta content="yes" name="apple-touch-fullscreen">
        <meta content="telephone=no,email=no" name="format-detection">
        <meta content="maximum-dpr=1" name="flexible"/>
        <meta name="viewport" content="initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no">
        <script type="text/javascript" src="${JS_PATH}/appjs/jquery-1.8.3.min.js"></script>
        <script type="text/javascript" src="${JS_PATH}/appjs/vue.js"></script>
        <script type="text/javascript" src="${JS_PATH}/appjs/bus-vue2017.js"></script>
        <script type="text/javascript" src="${JS_PATH}/appjs/layer/layer.js"></script>
        <link rel="stylesheet" href="${CSS_PATH}/mui.min.css">
        <link rel="stylesheet" type="text/css" href="${CSS_PATH}/common.css"/>
        <link rel="stylesheet" type="text/css" href="${CSS_PATH}/reset.css"/>
        <link rel="stylesheet" type="text/css" href="${CSS_PATH}/login.css"/>
        <script type="text/javascript" src="${JS_PATH}/app/login/login.js"></script>
        <title>登录</title>
    </head>
    <body class="m-login">
        <div class="m-box-logo f-tac">
            <img src="${IMAGE_PATH}/logo_03.png" class="m-img m-logo"/>
        </div>
        <input id="importMsgInput_name"  type="hidden"  value="${showContent}" />
        <div id="login" class="mui-content" >
            <div class="input-group mui-input-row-3 m-c2 f-cb">
                <label class="m-c2-hd-2 f-fl" style="padding-top: 10px;"><span class="icon u-user"></span></label>
                <div class="m-icons f-fr m-code-0" style="padding-top: 15px;">
                    <i title="清除" @click="user.username = ''">
                        <span class="icon" :class="user.username=='' ? '' : 'u-clearPass'"/>
                    </i>
                </div>
                <div class="m-c2-bd f-tar">
                    <input v-model.trim="user.username" type="text" placeholder="邮箱/手机"
                           class="input-control f-inp"
                           @blur="checkUserName()" maxlength="50">
                </div>
            </div>
            <div class="input-group mui-input-row-3">
                <label class="m-c2-hd-2 f-fl" style="padding-top: 10px;"><span class="icon u-lock"></span></label>
                <div class="m-icons f-fr m-code-0" style="padding-top: 15px;">
                    <i :class="{on: showPwd}" title="查看密码" @click="showPwd=!showPwd">
                        <span id="showEye" class="icon u-lookPass" />
                    </i>
                    <i title="清除密码" @click="user.password = ''">
                        <span class="icon" :class="user.password=='' ? '' : 'u-clearPass'"/>
                    </i>
                </div>
                <div class="m-c2-bd">
                    <template v-if="showPwd">
                    <input type="text" placeholder="密码" class="input-control f-inp"
                           autocomplete="off" v-model.trim="user.password" @keydown.enter="login()" maxlength="8"/>
                    </template>
                    <template v-else>
                    <input type="password" placeholder="密码" class="input-control f-inp"
                           autocomplete="off" v-model.trim="user.password" @keydown.enter="login()" maxlength="8"/>
                    </template>
                </div>
            </div>
            <template v-if="captcha != ''">
            <div class="input-group mui-input-row-3">
                <label class="m-c2-hd-2 f-fl" style="padding-top: 10px;"><span class="icon u-verification"></span></label>
                <div class="m-code m-code-0 m-code-1 f-fr">
                    <span class="er-coder m-img" @click="showCaptcha()" v-text="captcha"/>
                </div>
                <div class="m-c2-bd">
                    <input type="text" placeholder="验证码"
                           class="input-control f-inp" @blur="checkCaptcha()" @keydown.enter="login()"
                           v-model.trim="user.checkcode" maxlength="4">
                </div>
            </div>
            </template>
            <div class="input-group mui-input-row-4 f-cb ">
                <div class="f-fr"><a href="${CTX}/login/forgetpassword">忘记密码?</a></div>
                <div class="m-c2-hd-2 f-fl" data-id="tog2" data-jv="che" ref="tog2" @click="">
                    <input type="checkbox" class="icon u-checkbox-on" v-model="rmbPwd">
                    <span class="m-txt" style="cursor: hand" @click="rmbPwd=!rmbPwd">记住密码</span>
                </div>

            </div>
            <div class="input-group mui-input-row-2 m-btn-4 ">
                <button id="login-btn" type="button" class="mui-btn mui-btn-primary mui-btn-block f-btn f-btn-middle s-bgc-4882bf"
                        @click="login()" :disabled="user.userName=='' || user.password=='' ">登录
                </button>
            </div>
        </div>
        <img src="${IMAGE_PATH}/bg_02.jpg" class="m-login-bg"/>

    </body>
</html>
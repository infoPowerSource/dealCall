<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="../common/tagContent.jsp" %>
<!DOCTYPE html>
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
    <script src="${JS_PATH}/jquery/jquery.placeholder.min.js"></script>
    <script type="text/javascript" src="${JS_PATH}/app/login/resetPassword.js"></script>
    <title>修改密码</title>

</head>
<body>
<header id="header" class="mui-bar mui-bar-nav s-bgc-4882bf">
    <h1 class="mui-title">修改密码</h1>
    <a class="mui-action-back mui-btn mui-btn-blue mui-btn-link mui-btn-nav mui-pull-left"
       href="${CTX}/conf/confInfo" style="font-size: 15px"><span class="mui-icon mui-icon-left-nav"></span>返回</a>
</header>
<div id="app" class="mui-content" v-cloak>
    <div class="m-tips-2 f-tac f-mt10">
        <div class="m-tit">设置并确认新密码</div>
    </div>
    <form class="mui-input-group f-mt10">
        <div class="mui-input-row-2">
            <div class="m-icons f-fr m-code-0">
                <i title="清除密码" @click="user.oldPassword = ''">
                    <span class="icon" style="" :class="user.oldPassword=='' ? '' : 'u-clearPass'"/>
                </i>
                <i :class="{on:showOldPwd}" title="查看密码" @click="showOldPwd=!showOldPwd">
                    <span class="icon u-lookPass-blue"/>
                </i>
            </div>

            <input @blur="checkPassword(user.oldPassword)" :type="showOldPwd ? 'text' : 'password'" placeholder="旧密码" class="f-inp" v-model.trim="user.oldPassword" maxlength="8"/>
        </div>
    </form>
    <form class="mui-input-group f-mt35">
        <div class="mui-input-row-2">
            <div class="m-icons f-fr m-code-0">
                <i title="清除密码" @click="user.password = ''">
                    <span class="icon" style="" :class="user.password=='' ? '' : 'u-clearPass'"/>
                </i>
                <i :class="{on:showPwd}" title="查看密码" @click="showPwd=!showPwd">
                    <span class="icon u-lookPass-blue"/>
                </i>
            </div>

            <input @blur="checkPassword(user.password)" :type="showPwd ? 'text' : 'password'" placeholder="新密码" class="f-inp" v-model.trim="user.password" maxlength="8"/>
        </div>
    </form>
    <form class="mui-input-group f-mt10">
        <div class="mui-input-row-2">
            <div class="m-icons f-fr m-code-0">
                <i title="清除密码" @click="user.confirmPwd=''">
                    <span class="icon" :class="user.confirmPwd=='' ? '' : 'u-clearPass'"/>
                </i>
                <i :class="{on:showConfirmPwd}" title="查看密码" @click="showConfirmPwd=!showConfirmPwd">
                    <span class="icon u-lookPass-blue"/>
                </i>
            </div>
            <input @blur="confirmPwd()" :type="showConfirmPwd ? 'text' : 'password'" placeholder="确认新密码" class="f-inp" v-model.trim="user.confirmPwd" maxlength="8"/>
        </div>
    </form>
    <div class="mui-input-row-2 m-btn-2">
        <button id="login-btn" type="button" class="mui-btn mui-btn-primary mui-btn-block f-btn f-btn-middle s-bgc-4882bf" @click="modifyPassword()" :disabled="user.oldPassword=='' || user.password=='' || user.confirmPwd==''">提交
        </button>
    </div>
</div>

</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../common/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
	<head>
		<meta charset="UTF-8">
		<meta content="yes" name="apple-mobile-web-app-capable">
	    <meta content="yes" name="apple-touch-fullscreen">
	    <meta content="telephone=no,email=no" name="format-detection">
	    <meta content="maximum-dpr=1" name="flexible" />
	    <meta name="viewport" content="initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no">
        <title>呼叫其他号码</title>
        
	</head>
	<body>
		<header id="header" class="mui-bar mui-bar-nav s-bgc-4882bf">
			<h1 class="mui-title">呼叫其他号码</h1>
			<button id="callback" class="mui-btn mui-btn-blue mui-btn-link mui-btn-nav mui-pull-left" style="font-size: 15px"><span class="mui-icon mui-icon-left-nav"></span>返回</button>
		</header>
		<div id="app" class="mui-content">
			<form class="mui-input-group f-mt35">
				<input type="hidden" id="billingcode" value="${confDto.billingcode}" />
				<input type="hidden" id="confId" value="${confDto.confId}" />
				<div class="mui-input-row-2 m-c2 f-p015 f-cb">
					<label for="" class="m-c2-hd">姓名</label>
					<div class="m-c2-bd">
						<input type="text" placeholder="" id="name" class="f-inp f-tar" v-model.trim="form.hujiao.nam.v" data-id="nam" @change="inpchange($event.target,form.hujiao.nam)" maxlength="10">
					</div>
				</div>
			</form>
			<form class="mui-input-group f-mt10">
				<div class="mui-input-row-2 m-c2 f-p015 f-cb">
					<label for="" class="m-c2-hd">角色</label>
					<div class="m-c2-bd" id="m-juese">
						<!--<input type="text" placeholder="" class="f-inp f-tar"   @focus="inpfocus($event.target,form.hujiao.jue)" v-model.trim="form.hujiao.jue.v" data-id="jue" @change="inpchange($event.target,form.hujiao.jue)" maxlength="30">-->
						<div class="m-txt f-tar" id="role" v-text="form.hujiao.jue.v" ></div>	
					</div>
				</div>
			</form>
			<form class="mui-input-group f-mt10">
				<div class="mui-input-row-2 m-c2 f-p015 f-cb">
					<div class="m-c2-hd m-inp-guo" id="m-code">
						<input type="tel" class="f-inp" id="country" v-model.trim="form.hujiao.guo.v" data-id="mobguo" maxlength="5">
					</div>
					<div class="m-c2-bd">
						<div class="f-inp-qh">
							<input type="tel" placeholder="区号" id="area" class="f-inp " v-model.trim="form.hujiao.quh.v" data-id="mobqh" @change="inpchange($event.target,form.hujiao.quh)" maxlength="4">
						</div>
						<input type="tel" placeholder="电话号码" id="phoneNum" class="f-inp" v-model.trim="form.hujiao.pho.v" data-id="mob" @change="inpchange($event.target,form.hujiao.pho)" maxlength="20">
					</div>					
				</div>
			</form>
			<div class="mui-input-row-2 m-btn-2">
					<button type="button" class="mui-btn mui-btn-primary mui-btn-block f-btn f-btn-middle s-bgc-4882bf" id="callout" data-id="Hujiao" @click="inpclick($event.target,form.hujiao)" >呼叫</button>
			</div>
		</div>
		<script src="${JS_PATH}/appjs/jquery-1.8.3.min.js"></script>
		<script src="${JS_PATH}/appjs/vue.js"></script>
		<script src="${JS_PATH}/appjs/bus-vue2017.js"></script>
		<script src="${JS_PATH}/appjs/mui.min.js"></script>
		<script src="${JS_PATH}/appjs/mui.picker.min.js"></script>
		<script src="${JS_PATH}/appjs/common.js"></script>
		<script src="${JS_PATH}/appjs/layer/layer.js"></script>
		<script type="text/javascript" src="${JS_PATH}/app/conference/callOtherNum.js"></script>
	</body>
</html>

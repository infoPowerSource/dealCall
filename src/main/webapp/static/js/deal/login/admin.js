$(document).ready(function() {
	document.getElementById("verifyCode").style.display="none";
	//document.getElementById('userName').focus();
	readyToOnBlur = true;
	$("#checkcodev").click(function() {
    	createCode() ;
    });
	
	$("#password").keydown(function(event){
		if (event.keyCode == 13){
			SetPwdAndChk();
		}
	});
		
	$("body").on('click', '.rem-pwd-item', function(event) { 
	    event.preventDefault(); 
	    checkBoxs($(this)); 
	});
	getCookie();
	//document.getElementById("showEye").click();
	
});
var verifycode ;
var readyToOnBlur = false;

function changePwd(){
	//document.getElementById("showEye").click();
	//$("#password").attr('type','password');
}

function isChecked(){
	// 检查checkbox是否选中，并添加状态
	var pwd = $("#chkRememberPwd");
	if(pwd.prop('checked')){
	    pwd.next('.icons').removeClass('icons-check-false').addClass('icons-check-true');
	}else{
	    pwd.next('.icons').removeClass('icons-check-true').addClass('icons-check-false');
	}
}
function checkBoxs(obj) {
    // 设置checkbox状态
    var ckb = obj.find('input');
    var icons = ckb.next('.icons');
    var ckinput = ckb.prop('checked');
    if (ckinput) {
        ckb.prop('checked', false);  
    } else {
        ckb.prop('checked', true);
    }
    isChecked();
}

function Trim(str){ 
    return str.replace(/(^\s*)|(\s*$)/g, ""); 
}

function createCode() {     
  var code ;
  code = "";
  var codeLength = 4;
  //var checkCode = $("checkcodev").text();
  var selectChar = new Array(0,1,2,3,4,5,6,7,8,9,'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z');
       
  for(var i=0;i<codeLength;i++){   
	  var charIndex = Math.floor(Math.random()*36);   
	  code +=selectChar[charIndex];      
  }  
//  $("p").text(code);
  $("#checkcodev").text(code);
  verifycode = code;
  //每当变幻验证码时，清空输入框中的验证码
  $('#checkcode').val("");
  return code; 
}   

function checkEmail(str){
	
	if ($('#userName').val() == '') {
		$("#warning-login").text("邮箱不可为空");
        return false;
    }
    var re = /^(\w-*\.*)+@(\w-?)+(\.\w{2,})+$/;
    //var re=/^[A-Za-zd]+([-_.][A-Za-zd]+)*@([A-Za-zd]+[-.])+[A-Za-zd]{2,5}$/; 
    if(!re.test(str)){
    	$("#warning-login").text("登录邮箱格式不正确");
    	return false;
    }
    return true;
}

function checkIsNone(){
	if ($('#userName').val() == '') {
		$("#warning-login").text("邮箱不可为空");
        return false;
    }else{
    	var usr = document.getElementById('userName').value;
    	usr = Trim(usr);
    	checkEmail(usr);
    }
	if ($('#password').val() == '') {
		$("#warning-login").text("密码不可为空");
        return false;
    }
	if(document.getElementById("verifyCode").style.display == ''){
		if($('#checkcode').val() == ''){
	        $("#warning-login").text("验证码不可为空");
	        createCode();
	        return false;
		}
		
		var inputCode = $('#checkcode').val();
		if(verifycode.toLowerCase() != inputCode.toLowerCase())   
		{
			$("#warning-login").text("验证码输入错误");
			createCode();
			return  false;  
		}
	}
	return true;
}

function showCaptcha(){
	document.getElementById("verifyCode").style.display="";
	createCode();
}

var count=0;
//点击登录时触发客户端事件
function SetPwdAndChk() {
	//检查是否为空
	$("#warning-login").text("");
	if(checkIsNone()){
		//取用户名
		var usr = document.getElementById('userName').value;
		//将最后一个用户信息写入到Cookie
		SetLastUser(usr);
		//如果记住密码选项被选中
		var ischecked = $("#chkRememberPwd").prop('checked');
		if(ischecked == true) {
			//取密码值
			var pwd = document.getElementById('password').value;
			
			var expdate = new Date();
			expdate.setTime(expdate.getTime() + 60 * (24 * 60 * 60 * 1000));
			//将用户名和密码写入到Cookie
			SetCookie(usr, pwd, expdate);
		} 
		else {
			//如果没有选中记住密码,则立即过期
			ResetCookie();
		}
		//登录
		var url = BASE_PATH + "/support/login";
		$.ajax({
			url : url,
			type : "GET",
			data : $('#login-form').serialize(),
			success : function(result) {
					if(result.success == "true"){
						window.location.href =BASE_PATH +"/support/taskList";
					}else{
						count > 1 ? showCaptcha() : count++;
						$("#warning-login").text(result.msg);
					}
				}
	   	});
	}		
}

var id = "49BAC005-7D5B-4231-8CEA-16939BEACD67";

function SetLastUser(usr) {
	var expdate = new Date();
	//当前时间加上两周的时间
	expdate.setTime(expdate.getTime() + 14 * (24 * 60 * 60 * 1000));
	SetCookie(id, usr, expdate);
}

//用户名失去焦点时调用该方法
function GetPwdAndChk() {
	if (readyToOnBlur == false) {return true;}
	$("#warning-login").text("");
	var usr = document.getElementById('userName').value;
	usr = Trim(usr);
	$("#userName").val(usr);
	//验证邮箱格式是否正确
	if(checkEmail(usr)){
		var pwd = GetCookie(usr);
		if (pwd != null) {
			//document.getElementById('chkRememberPwd').checked = true;
			$("#chkRememberPwd").prop('checked',true);
			document.getElementById('password').value = pwd;
			//changePwd();
		} 
		else {
			//document.getElementById('chkRememberPwd').checked = false;
			$("#chkRememberPwd").prop('checked',false);
			document.getElementById('password').value = "";
		}
		isChecked();
	}	
}

function getCookie() {
	var value = GetCookie(id);
    if(null != value && "" != value){
	var values = value.split(id);

	var name = values[0];
	var pwd = GetCookie(name);
		
	$("#userName").removeClass('placeholder');
	document.getElementById('userName').value = name;
	document.getElementById('password').value = pwd;

	if (pwd != null) {
		$("#chkRememberPwd").prop('checked',true);
		document.getElementById('password').value = pwd;
	} 
	else {
		$("#chkRememberPwd").prop('checked',false);
		document.getElementById('password').value = "";
	}
	isChecked();
 }
}

//取Cookie的值
function GetCookie(name) {
	var arg = name + "=";
	var alen = arg.length;
	var clen = document.cookie.length;
	var i = 0;
	while (i < clen) {
		var j = i + alen;
		
		if (document.cookie.substring(i, j) == arg) return getCookieVal(j);
		i = document.cookie.indexOf(" ", i) + 1;
		if (i == 0) break;
	}
	return null;
}

function getCookieVal(offset) {
	var endstr = document.cookie.indexOf(";", offset);
	if (endstr == -1) endstr = document.cookie.length;
	return unescape(document.cookie.substring(offset, endstr));
}

//写入到Cookie
function SetCookie(name, value, expires) {
	var argv = SetCookie.arguments;
	//本例中length = 3
	var argc = SetCookie.arguments.length;
	var expires = (argc > 2) ? argv[2] : null;
	var path = (argc > 3) ? argv[3] : null;
	var domain = (argc > 4) ? argv[4] : null;
	var secure = (argc > 5) ? argv[5] : false;
	document.cookie = name + "=" + escape(value) + ((expires == null) ? "" : ("; expires=" + expires.toGMTString())) + ((path == null) ? "" : ("; path=" + path)) + ((domain == null) ? "" : ("; domain=" + domain)) + ((secure == true) ? "; secure" : "");
}

function ResetCookie() {
	var usr = document.getElementById('userName').value;
	var expdate = new Date();
	SetCookie(usr, null, expdate);
}


$(function() {
    $("#login-form").find(".input-control").on("focus", function() {
        $(this).closest(".input-group").addClass("active");
    }).on("blur", function() {
        $(this).closest(".input-group").removeClass("active");
    });
});


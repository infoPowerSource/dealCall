function checkUser(){
   if(!checkIsValid()){
	  return ;
   }
   var url = BASE_PATH + "/login/checkUser";
   //var url = BASE_PATH + "/login/checkUser?password=" + $('#password').val();
   $.ajax({
   			url : url,
  			data : $('#checkUser').serialize(),
   			type : "POST",
   			success : function(result) {
   					if(result.success){
   					    addCookie();
   						SetCookie("failCount",0);
   						window.location.href = BASE_PATH +"/conf/confInfo";
   					}else{
   						_failCount = _failCount + 1;
   						SetCookie("failCount",_failCount);
   						showVerifyCode();
   						$("#warning-login").text(result.msg);
   					    createCode() ;
//   					    window.location.href = BASE_PATH +"/login";
   					}
   				}
   			});
}

var  _failCount = GetCookie("failCount") != null ? parseInt(GetCookie("failCount")) : 0;
var verifycode ;
var readyToOnBlur = false;
var eyeflag=0;
$(document).ready(function() {
//	$('input, textarea').placeholder();
//	readyToOnBlur = true;
	readyToOnBlur = true;
	getCookie();
	document.getElementById("verifyCode").style.display = 'none';
	$("body").on('click', '.rem-pwd-item', function(event) { 
	    event.preventDefault(); 
	    checkBoxs($(this)); 
	});
	
    $("#checkcodev").click(function() {
    	createCode() ;
    });
    if(!placeholderSupport()){
       document.getElementById("showEye").click();
    }
    showVerifyCode();
    $("#warning-login").text();
    $("#password").keydown(function(event){
    	if (event.keyCode == 13){
    		login();
    	}
    });
    $(".login-btn").click(function() {
    	login();
    });
    
    function login(){
    	var nowTime = new Date().getTime();
 	   	var clickTime = $(".login-btn").attr("ctime");
 	   	if( clickTime != 'undefined' && (nowTime - clickTime < 2000)){
 	   		alert("操作过于频繁，稍后再试");
 	        return;
 	    }else{
 	    	$(".login-btn").attr("ctime",nowTime);
     	    checkUserAndValidate() ;
 	    }
    }
    
    initPassPlaceholder();
    
    if(top.location!=self.location){
    	  //说明你的页面在if框架中显示
    	window.parent.location.reload(); //刷新父页面
    }
    var checkResult =$("#importMsgInput_name").val(); 
    if("1" == checkResult){
    	layui.use("layer",function(){
        	layer.open({
        	title : ['下线通知','font-size:18px;height:55px;background-color:#fff;border:none;padding: 20px 153px;'],
			resize:false,
			skin : 'Rectangle-9',
        	area : [ '380px', '220px' ],
        	shadeClose : false, // 开启遮罩关闭
        	content : '你的帐号已在另一台电脑登录。如非本人操作，则可能密码已泄露，建议立即修改密码',
            btn: '我知道了',
            btnAlign: 'c',
        	scrollbar: false
        	});
        });
//       alert("2222222");
    }
});

function initPassPlaceholder() {
	if (readyToOnBlur == false) {return;}
  if (!placeholderSupport()) { // 判断浏览器是否支持 placeholder
  $('#password').focus(function() {
      var input = $(this);
      if (input.val() == input.attr('placeholder')) {
          input.val('');
          input.removeClass('placeholder');
          if(eyeflag==1){//0:no show ; 1:show
        	 document.getElementById("showEye").click();//alert("a1:");
          }
      }
  }).blur(function() {
      var input = $(this);
      if (input.val() == '' || input.val() == input.attr('placeholder')) {
          input.addClass('placeholder');
          input.val(input.attr('placeholder'));
      	  if(eyeflag==0){
    		 document.getElementById("showEye").click();//alert("a1:");
    	  }
      }
  }).blur();
};
}


function placeholderSupport() {
  return 'placeholder' in document.createElement('input');
}

function eyeClick(){
	if (readyToOnBlur == false) {return true;}
    eyeflag = (eyeflag+1)%2;
}
function isChecked(){
	// 检查checkbox是否选中，并添加状态
	var pwd = $("#remember-pwd");
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

var id = "ABCD49BAC005-7D5B-4231-8CEA-16939BEACD67ABCD";
function addCookie(){
	
	//取用户名
	var usr = document.getElementById('username').value;
	//将最后一个用户信息写入到Cookie
//	SetLastUser(usr);
	//如果记住密码选项被选中
	var ischecked = $("#remember-pwd").prop('checked');
	if(ischecked == true) {
		//取密码值
		var pwd = document.getElementById('password').value;
		
		var expdate = new Date();
		expdate.setTime(expdate.getTime() + 60 * (24 * 60 * 60 * 1000));
		//将用户名和密码写入到Cookie
		SetLastUser(usr, pwd);
//		SetCookie(usr, pwd, expdate);
	} 
	else {
		//如果没有选中记住密码,则立即过期
		DelCookie(id);
	}
}

function showVerifyCode(){
    if(_failCount >= 3){
        createCode() ;
        document.getElementById("checkcode").value = ''; 
        document.getElementById("verifyCode").style.display = '';
    }else{
        document.getElementById("verifyCode").style.display = 'none';
    }
}

function checkIsValidUserName(){
	$('#warning-login').empty();
	if ($('#username').val() == '') {
		$("#warning-login").text("请输入邮箱或手机");
		createCode() ;
        return false;
    }
	var username = $("#username").val();
	username = Trim(username);
	$("#username").val(username);
    if (!is_mobile(username)) {
    	 if (!is_email(username)) {
    		 createCode() ;
    	     $("#warning-login").text("请输入正确的邮箱或手机");
    	     return false;
    	 }
    }
    return true;
}

function checkIsValid(){
	$('#warning-login').empty();
	if ($('#username').val() == ''||$('#username').val() == '邮箱/手机') {
		$("#warning-login").text("请输入邮箱或手机");
		createCode() ;
        return false;
    }
	if ($('#password').val() == '') {
		$("#warning-login").text("请输入登录密码");
		createCode() ;
		return false;
    }
	var username = $("#username").val();
	username = Trim(username);
	$("#username").val(username);
    if (!is_mobile(username)) {
    	 if (!is_email(username)) {
    	     $("#warning-login").text("请输入正确的邮箱或手机");
    	     createCode() ;
    	     return false;
    	 }
    }
    return true;
}

function Trim(str){ 
    return str.replace(/(^\s*)|(\s*$)/g, ""); 
}

function validatecode ()   
{  
	if (readyToOnBlur == false) {return true;}
	$('#warning-login').empty();
    var inputCode = document.getElementById("checkcode").value;  
    if(inputCode.length <=0) 
    {   
        $("#warning-login").text("请输入验证码");
        createCode() ;
        return false;
    }
    else if(inputCode.toLowerCase() != verifycode.toLowerCase()) 
    {   
        $("#warning-login").text("请输入正确的验证码");
        createCode() ;
        return false;
    } 
    return true;
}

function checkUserAndValidate()   
{    
	if(document.getElementById("verifyCode").style.display == 'none'){
		checkUser();
	}else if(document.getElementById("verifyCode").style.display == ''){
	    if(validatecode()){
		    checkUser();
	    }
	}
 }

function createCode()   
{     
  var code ;
  code = "";   
  var codeLength = 4;
//  var checkCode = $("p").text();
  var selectChar = new Array(0,1,2,3,4,5,6,7,8,9,'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z');
       
  for(var i=0;i<codeLength;i++)   
  {   
  var charIndex = Math.floor(Math.random()*36);   
  code +=selectChar[charIndex];      
  }   
//  alert(code);   
//  if(checkCode)   
//  {   
//    $("p").text(code);
      $("#checkcodev").text(code);
  
//  }   
  verifycode = code;
  //每当变幻验证码时，清空输入框中的验证码
  $('#checkcode').val("");
  return code; 
}


function SetLastUser(usr,pwd) {
	var expdate = new Date();
	//当前时间加上两周的时间
	expdate.setTime(expdate.getTime() + 14 * (24 * 60 * 60 * 1000));
	SetCookie(id, usr + id + pwd, expdate);
}


function getCookie() {
	var value = GetCookie(id);
	//alert(value);
    if(null != value && "" != value){
	var values = value.split(id);

//	alert(values.length);
	name = values[0];
	pwd = values[1];
		
//	alert(name+"."+password);
	$("#username").removeClass('placeholder');
	document.getElementById('username').value = name;
	document.getElementById('password').value = pwd;
//	$("#password").removeClass('placeholder');

    if(!placeholderSupport()){
       if(pwd!=null){
        document.getElementById("showEye").click();
       }
    }

	if (name != null) {
		$("#remember-pwd").prop('checked',true);
	} 
	else {
		$("#remember-pwd").prop('checked',false);
	}
	isChecked();
 }
}

//用户名失去焦点时调用该方法
function GetPwdAndChk() {
	if (readyToOnBlur == false) {return true;}
	var usr = document.getElementById('username').value;
	//验证邮箱格式是否正确
	if(checkIsValidUserName()){
//		var pwd = GetCookie(usr);
//		
//		if (pwd != null) {
//			$("#remember-pwd").prop('checked',true);
////			document.getElementById('remember-pwd').checked = true;
//			document.getElementById('password').value = pwd;
//		} 
//		else {
//			$("#remember-pwd").prop('checked',false);
////			document.getElementById('remember-pwd').checked = false;
//			document.getElementById('password').value = "";
//		}
//		isChecked();
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
		//alert(j);
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
	var usr = document.getElementById('username').value;
	var expdate = new Date();
	SetCookie(usr, null, expdate);
}

function DelCookie(name)
//删除Cookie
{
var exp = new Date();
exp.setTime (exp.getTime() - 1);
var cval = GetCookie (name);
document.cookie = name + "=" + cval + "; expires="+ exp.toGMTString();
}

$(function() {
    $("#login-form").find(".input-control").on("focus", function() {
        $(this).closest(".input-group").addClass("active");
    }).on("blur", function() {
        $(this).closest(".input-group").removeClass("active");
    });
});

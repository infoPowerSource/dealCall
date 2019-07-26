var verifycode; 
$(document).ready(function() {
//	$('input, textarea').placeholder();
	readyToOnBlur = true;
	createCode() ;
	var checkResult =$("#importMsgInput_name").val(); 
//	alert(checkResult);
	var showContent = "";
	if(checkResult == ""){
		showContent = "";
	}else if(-1 == checkResult){
		showContent = "无效用户";
	}else if(0 == checkResult){
		showContent = "无效请求";
	}else if(2 == checkResult){
		showContent = "请求超时";
	}
//	alert(showContent+","+checkResult);
	$('.username_msg').text(showContent);
	
        $(".czmm_button").click(function() {
        	   var nowTime = new Date().getTime();
        	   var clickTime = $(this).attr("ctime");
        	   if( clickTime != 'undefined' && (nowTime - clickTime < 2000)){
        	       alert("操作过于频繁，稍后再试");
        	        return;
        	     }else{
        	        $(this).attr("ctime",nowTime);
                    if(checkIsValidUserName()){
                    	if(validateCode()){
                     	   summit_fp();
                     	}
                    }
        	     }  
        });
        $("#checkcodev").click(function() {
        	createCode() ;
        });
    });
var readyToOnBlur = false;
function checkIsValidUserName(){
    var username = $("#username").val();
    var checkcode = $("#checkcode").val();
    $('.username_msg').empty();
    $('.checkcode_msg').empty();
    if (username == '') {
        $('.username_msg').text('请填写正确的邮箱或手机');
        return false;
    } else {
    	var username = $("#username").val();
    	username = Trim(username);
    	$("#username").val(username);
        if (is_mobile(username)) {
//            $("#czmmform").attr('action', 'getFromMobile.html');
        } else if (is_email(username)) {
//            $("#czmmform").attr('action', 'getFromEmail.html');
        } else {
            $('.username_msg').text('请填写正确的邮箱或手机');
            return false;
        }
        return true;
    }
}

function validateCode()   
{ 
  if (readyToOnBlur == false) {return true;}
  var inputCode = $(".checkcode").val();
  if(inputCode == '验证码' || inputCode.length <=0)   
  {   
      createCode();  
      $('.checkcode_msg').text('请填写验证码');
      return false;
  }   
  else if(inputCode.toLowerCase() != verifycode.toLowerCase())   
  {   
	  createCode();  
      $('.checkcode_msg').text('验证码输入错误'); 
//     alert("验证码输入错误:"+code.toLowerCase()+",inputCode:"+inputCode.toLowerCase());   
      return false;
  }   
  $('.checkcode_msg').empty();
	  return true;   
}

function Trim(str){ 
    return str.replace(/(^\s*)|(\s*$)/g, ""); 
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
} 
function summit_fp(){
	   var url = BASE_PATH + "/login/findBackPass";
	   $.ajax({
	   			url : url,
	  			data : $('#czmmform').serialize(),
	   			type : "POST",
	   			success : function(result) {
	   					if(result.success){
//	   						alert(result.userDisplayName);
	   						if(0==result.msg){
		   						window.location.href =BASE_PATH +"/login/setPwdFromEmail?name=" + result.name + "&id=" + result.id;// + "&userDisplayName=" + encodeURI(result.userDisplayName)
	   						}else{
		   						window.location.href =BASE_PATH +"/login/setPwdFromMobile?name=" + result.name + "&id=" + result.id;
	   						}
	   					}else{ 
//	   						alert(result.msg);
	   						$('.username_msg').text(result.msg);
	   						createCode() ;
//	   						window.location.href =BASE_PATH +"/login/forgetpassword"; 
//	   						$('.username_msg').text(result.msg);
	   					}
	   				}

	   			});
	}






$(document).ready(function(){
	            $('input, textarea').placeholder();
	            readyToOnBlur = true;
				$(".czmm_button").click(function(){
			      	   var nowTime = new Date().getTime();
			    	   var clickTime = $(this).attr("ctime");
			    	   if( clickTime != 'undefined' && (nowTime - clickTime < 2000)){
			    	       alert("操作过于频繁，稍后再试");
			    	        return;
			    	     }else{
			    	        $(this).attr("ctime",nowTime);
							if(checkIsValid()){
								resetPass();
							}
			    	     }
				});
			});
var readyToOnBlur = false;
function checkIsValid(){
	if (readyToOnBlur == false) {return true;}
	var pwd1 = $("#password").val();
	var pwd2 = $("#pwd2").val();
	var flag = 0;
	$('.pwd1_msg').empty();
	$('.pwd2_msg').empty();
	if(pwd1 == '' || pwd1.length < 6 ){
		$('.pwd1_msg').text('新密码至少6位');
		return false;
	}
	if(!isValidPass(pwd1)){
		$('.pwd1_msg').text('请输入数字和字母组合 ');
		return false;
	}
	if(pwd2 == '' || pwd2.length < 6 ){
		$('.pwd2_msg').text('确认新密码至少6位');
		return false;
	}
	if(pwd1 != pwd2){
		$('.pwd2_msg').text('两次输入的密码不一致');
		return false;
	}
    return true;
}

function isValidPass(newpass) {
    if ((/^[A-Za-z0-9]{6,8}$/.test(newpass))) {
    	if ((/^[A-Za-z]{6,8}$/.test(newpass)) || (/^[0-9]{6,8}$/.test(newpass))) {
    		return false;
    	}
        return true;
    } else {
        return false;
    }
}


function resetPass(){
       var _session_id =$("#importMsgInput_id").val();     
       //alert(_session_id); 
	   var url = BASE_PATH + "/login/resetPass_h?id="+_session_id;
	   $.ajax({
	   			url : url,
	  			data : $('#czmmform').serialize(),
	   			type : "POST",
	   			success : function(result) {
	   					if(result.success){
	   						alert(result.msg);
//	   						layer.msg('<p class="task-popup">123123123</p>', { time: 15000 });
	   						window.location.href =BASE_PATH + "/login";
	   					}else{
//	   						alert(result.msg);
	   						$('.pwd1_msg').text(result.msg);
//	   						window.location.href =BASE_PATH +"/userLogin";
	   					}
	   				}

	   			});
	}
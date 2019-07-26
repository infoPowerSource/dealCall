$(document).ready(function(){
    $('input, textarea').placeholder();
	readyToOnBlur = true;
	// 顶部用户菜单
	$(".top-user").on('click', function(event) {
	    event.preventDefault();
	    /* Act on the event */
	    if (!$(".menus").is(":hidden")) {
	        $(".menus").show();
	        $(this).find('.arrow').addClass('show');
	    } else {
	        $(".menus").hide();
	        $(this).find('.arrow').removeClass('show');
	    }
	});

	$("#serchAccess").on("click", function () {
        $.ajax({
            type: 'GET',
            url: BASE_PATH + "/conf/getConfAccessNum",
            success: function (data) {
            	layer.open({
                    type: 2,
                    title: false,
                    closeBtn: 1,
                    skin: 'edit-demo-class',
                    area: ['1100px', '600px'],
                    shadeClose: true, // 开启遮罩关闭
                    content: [data, 'yes']
                });
            }
        });
    });
//	$(document).on('click', function(event) {
//
//	    if (!$(event.target).hasClass('top-user')) {
//	        $(".menus").hide();
//	        $('.arrow').removeClass('show');
//	    }
//	});
	
				$(".czmm_button").click(function(){
			      	   var nowTime = new Date().getTime();
			    	   var clickTime = $(this).attr("ctime");
			    	   if( clickTime != 'undefined' && (nowTime - clickTime < 2000)){
			    	       alert("操作过于频繁，稍后再试");
			    	        return;
			    	     }else{
			    	        $(this).attr("ctime",nowTime);
							if(checkIsValid()){
								updatePass();
							}
			    	     }
				});
			});
var readyToOnBlur = false;
function checkIsValid(){
	if (readyToOnBlur == false) {return true;}
	var pwd0 = $("#oldPassword").val();
	var pwd1 = $("#password").val();
	var pwd2 = $("#pwd2").val();
	var flag = 0;
	$('.pwd0_msg').empty();
	$('.pwd1_msg').empty();
	$('.pwd2_msg').empty();
	if(pwd0 == '' || pwd0.length < 6 ){
		$('.pwd0_msg').text('旧密码至少6位');
		return false;
	}
	if(!isValidPass(pwd0)){
		$('.pwd0_msg').text('请输入数字和字母组合 ');
		return false;
	}
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

function updatePass(){
	   var url = BASE_PATH + "/login/updatePass_h";
	   $.ajax({
	   			url : url,
	  			data : $('#czmmform').serialize(),
	   			type : "POST",
	   			success : function(result) {
	   					if(result.success){
	   						alert(result.msg);
	   						window.location.href =BASE_PATH + "/login";
	   					}else{
//	   						alert(result.msg);
	   						$('.pwd0_msg').text(result.msg);
//	   						window.location.href =BASE_PATH +"/userLogin";
	   					}
	   				}

	   			});
	}
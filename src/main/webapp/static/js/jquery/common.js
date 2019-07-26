//全局的ajax访问，处理ajax清求时sesion超时
$.ajaxSetup({
    contentType : "application/x-www-form-urlencoded;charset=utf-8",
    complete : function(XMLHttpRequest, textStatus) {
        var sessionstatus = XMLHttpRequest.getResponseHeader("sessionstatus"); // 通过XMLHttpRequest取得响应头
        if (sessionstatus == "unlogin") {
            // 如果超时就处理 ，指定要跳转的页面
            window.location.replace("/deal/login");
        } else if (sessionstatus == "relogin") {
            // 如果超时就处理 ，指定要跳转的页面
            window.location.replace("/deal/login?type=1");
        } else if (sessionstatus == "timeout") {
        	// 创建倒计时top—tip
        	if(isTopTip==0){
        	   isTopTip=1;
        	   createTopTip();
        	}
//        if (!confirm("是否继续访问网页？"))
//        { 
//        	// 如果超时就处理 ，指定要跳转的页面
//          window.location.replace("/deal/login");
//        } 
        }
    }
});
//请求session状态，防止同一用户同种设备重复登录
$(function () {
    setInterval(function () {
        $.getJSON(BASE_PATH+"/login/sessionStatus?r="+Math.random());
    },3000);
});
var isTopTip=0;
// 创建倒计时top—tip
function createTopTip(){
	  var $topTip = $("<div class='top-tip'>");
	  var $tipText = $("<div class='tip-text'>");
	  var $cancelBtn = $("<button>取消(30S)</button>");

	  var interval = "";
	  var time = 30;
	  interval = setInterval(function(){
		  time--;
		  $cancelBtn.text("取消("+time+"S)");
		  if (time == 0){
			  clearInterval(interval);
			  topTipTimeout();
			  $topTip.remove();
			  isTopTip=0;
		  }
	  },1000);
	  
	  $cancelBtn.click(function(){ 
		  clearInterval(interval); 
		  $topTip.remove(); 
		  topTipCancel(); 
		  isTopTip=0;
	  });
	
	  $tipText.append("<span></span>");
	  $tipText.append("<span>您已长时间未操作，即将为你退出登录</span>");
	  $tipText.append($cancelBtn);
	  $topTip.append($tipText);
	  $(".header").append($topTip);
}

function topTipCancel(){
}

function topTipTimeout(){
	window.location.replace("/deal/login");
}


function clearLogin(){
	   var url = BASE_PATH + "/login/clearLogin";
	   $.ajax({
	   			url : url,
	  			data : $('#czmmform').serialize(),
	   			type : "POST",
	   			success : function(result) {
	   					if(result.success){
//	   						alert(result.userDisplayName);
	   					}else{ 
//	   						alert(result.msg);
	   					}
	   				}

	   			});
	}

/*检查手机号*/
function is_mobile(phone) {
    if (!(/^1[345789]\d{9}$/.test(phone))) {
        return false;
    } else {
        return true;
    }
}
/*检查固话号*/
function is_telephone(phone) {
    if ((/^\+?[0-9]*$/).test(phone)) {
        return true;
    } else {
        return false;
    }
}
/*检查区号*/
function is_areaCode(phone) {
    if ((/^\+?[0-9]*$/).test(phone)) {
        return true;
    } else {
        return false;
    }
}
/*检查邮箱*/
function is_email(str) {
    if ((/^(\w-*\.*)+@(\w-?)+(\.\w{2,})+$/).test(str)) {
        return true;
    } else {
        return false;
    }
}
/*检查数字*/
function is_num(str) {
    return /^[0-9]*$/.test(str);
}

function setPlaceholder() {
    if (!placeholderSupport()) { // 判断浏览器是否支持 placeholder
        $('[placeholder]').focus(function() {
            var input = $(this);
            if (input.val() == input.attr('placeholder')) {
                input.val('');
                input.removeClass('placeholder');
            }
        }).blur(function() {
            var input = $(this);
            if (input.val() == '' || input.val() == input.attr('placeholder')) {
                input.addClass('placeholder');
                input.val(input.attr('placeholder'));
            }
        }).blur();
    };

}

function placeholderSupport() {
    return 'placeholder' in document.createElement('input');
}

$(document).ready(function() {

    // 登录框高度
    var lgheight = $(".login-box").height();

    $(".login-box").css('margin-top', -(lgheight / 2));


    // 顶部用户菜单
    $(".top-user").on('click', function(event) {
        event.preventDefault();
        /* Act on the event */

        if ($(".menus").is(":hidden")) {
            $(".menus").show();
            $(this).find('.arrow').addClass('show');
        } else {
            $(".menus").hide();
            $(this).find('.arrow').removeClass('show');
        }
    });


    $(document).on('click', function(event) {

        if (!$(event.target).hasClass('top-user')) {
            $(".menus").hide();
            $('.arrow').removeClass('show');
        }
    });


    $(".input-group").on('click', '.del', function(event) {
        event.preventDefault();
        /* Act on the event */
        $(this).siblings('.input-control').val("");
    });

    // 显示密码
    var flag = 0;
    $(".input-group").on('click', '.eye', function(event) {
        event.preventDefault();
        /* Act on the event */
        if (flag == 0) {
            $(this).siblings('.input-control').prop({ type: 'text' });
            $(this).addClass('icons-eye-active').removeClass('icons-eye');
            flag = 1;
        } else {
            $(this).siblings('.input-control').prop({ type: 'password' });
            $(this).removeClass('icons-eye-active').addClass('icons-eye');
            flag = 0;
        }

    });

    $(".input-group").on('focus', '.input-control', function(event) {
        event.preventDefault();

        $(this).siblings('.icons-lock').removeClass('icons-lock').addClass('icons-lock-active');
        $(this).siblings('.icons-user').removeClass('icons-user').addClass('icons-user-active');
        $(this).siblings('.icons-er-coder').removeClass('icons-er-coder').addClass('icons-er-coder-active');
    });

    $(".input-group").on('blur', '.input-control', function(event) {
        event.preventDefault();
        /* Act on the event */
        $(this).siblings('.icons-lock-active').removeClass('icons-lock-active').addClass('icons-lock');
        $(this).siblings('.icons-user-active').removeClass('icons-user-active').addClass('icons-user');
        $(this).siblings('.icons-er-coder-active').removeClass('icons-er-coder-active').addClass('icons-er-coder');
    });

    $("body .pub-table>tbody>tr:even").addClass('even');
    $("body .pub-table>tbody>tr:odd").addClass('odd');

    $(".pub-table > tbody > tr").hover(function() {
        $(this).addClass('hover');
        $(this).find('.td-control').show();
    }, function() {
        $(this).removeClass('hover');
        $(this).find('.td-control').hide();
    });



    // 添加新会议
 /*   $("#send-task").click(function(event) {
        var tsname = $("#task-theme").val();

        if (tsname === "") {
            $("#task-theme").addClass('warning').next('.message').text('请输入会议主题');
            return false;
        } else {
            layer.msg('<p class="pub-toast"><span class="icons icons-success sucess-icons"></span>会议安排成功</p>', { time: 1000 });
            setTimeout(function() {
                window.location.href = "index.html";

            }, 1000);


        }

    });
    */
    $("#task-theme").focus(function(event) {
        $(this).removeClass('warning').next('.message').text('')
    });

});


layui.use('layer', function() {
    
	   /* // 点击删除,
	    $(".td-control").on('click', '.del-task-btn', function(event) {
	        event.stopPropagation();
	        layer.open({
	            type: 0,
	            title: false,
	            closeBtn: 2,
	            id: "del-item",
	            resize: false,
	            skin: 'del-demo-class',
	            area: ['360px', '200px'],
	            shadeClose: true, //开启遮罩关闭
	                scrollbar: false,
	            content: '<p class="close-text">确定要取消这场会议吗？</p>',
	            btn: ['取消会议', '暂不取消'],
	            yes: function(index, layero) {
	                //按钮【按钮一】的回调
	                layer.close(index);
	                layer.msg("取消会议成功", { time: 1000 });
	            },
	            btn2: function(index, layero) {
	                layer.close(index);
	            }

	        });
	    });*/

	    /*// 点击查看详情
	    $(".pub-table > tbody").on('click', '.item-title', function(event) {
	        event.preventDefault();
	         Act on the event 
	        if ($(this).hasClass('begin')) {

	            layer.open({
	                type: 2,
	                title: false,
	                closeBtn: 1,
	                skin: 'edit-demo-class',
	                area: ['680px', '430px'],
	                shadeClose: false, //开启遮罩关闭
	                content: ['iframe-table.html', 'yes'],
	                scrollbar: false,
	                cancel: function(index) {
	                    layer.msg('关闭成功！', { time: 1000 })
	                }
	            });
	        } else {
	            layer.open({
	                type: 2,
	                title: false,
	                closeBtn: 1,
	                skin: 'edit-demo-class',
	                area: ['680px', '430px'],
	                shadeClose: false, //开启遮罩关闭
	                content: ['iframe-table-detail.html', 'yes'],
	                scrollbar: false,
	                cancel: function(index) {
	                    layer.msg('关闭成功！', { time: 1000 })
	                }
	            });
	        }

	    });*/

	   /* // 呼叫其它号码
	    $("body").on('click', '.call-other', function(event) {
	        event.preventDefault();
	        $('#call-user,.td-call-box').show();
	    });
	    $("body").on('click', '.call-send', function(event) {
	        event.preventDefault();
	        var tel = $('.phone-main').val();

	        if (tel == "") {
	            $(this).closest('td').siblings('td').find('.message').html('请输入电话号码');
	            $('.phone-main')[0].focus();
	        } else {
	            if (is_mobile(tel)) {
	                $(this).closest('td').siblings('td').find('.message').html('');
	                layer.msg('呼叫成功', { time: 1000 });
	            } else {
	                $(this).closest('td').siblings('td').find('.message').html('请输入正确电话号码');
	                $('.phone-main')[0].focus();

	            }

	        }
	    });

	    $("body").on('click', '.td-del-btn', function(event) {
	        event.preventDefault();
	        $('#call-user,.td-call-box').hide();
	    });*/

	  /*  // 查看录音
	    $(".pub-table .record-box").on('click', '.record', function(event) {
	        event.preventDefault();
	        var trid = $(this).closest('tr').data('id');

	        if ($(this).siblings('.td-tips').is(':hidden')) {

	            $(this).siblings('.td-tips').show();
	            $(this).closest('tr').siblings().find('.td-tips').hide();

	        } else {

	            $(this).siblings('.td-tips').hide();

	        }

	        // 发送录音

	        $(".td-send-btn").on('click', function(event) {
	            event.preventDefault();
	            var pg = $(this).siblings('.td-email').val();


	            if (pg == "") {
	                layer.msg('请输入邮箱地址！');
	                $(this).siblings('.td-email').focus();
	            } else {
	                $(this).closest('.td-tips').siblings(".tips-text").show(0, function(e) {
	                    setTimeout(function() {
	                        $(".tips-text").hide();
	                    }, 5000);
	                });
	                $(this).closest('.td-tips').hide();
	            }

	        });
	    });
	    
	    $(document).on('click', function(event) {
	        // event.preventDefault();

	        if (!$(event.target).is('.td-tips') && !$(event.target).is('.record') && !$(event.target).is('.td-send-btn') && !$(event.target).is('.td-email') && !$(event.target).is('.send-record p') && !$(event.target).is('.send-record') && !$(event.target).is('.td-input-block')) {

	            $('.td-tips').hide();
	        }

	         Act on the event 
	    });*/
	   
//	    // 添加咨询信息
//	    var zjlength=$('#zj-info .input-block').length;
//	    var uselength=$('#user-info .input-block').length;
//	    var count=(zjlength+uselength)-1;
//	    function addItme(obj, id,type,index) {
//	        var tpl = '<div class="input-block task" data-id="' + id + '">'+
//	                  '<input type="text" name="custInfo['+parseInt(index)+'].custName" value="" placeholder="姓名" maxlength="20" class="input-pub s-size" />'+
//	                  '<div class="tel-group"> <input type="text" name="custInfo['+parseInt(index)+'].custContryCode" value="+86" maxlength="4" class="base" onblur="checkContryCode('+parseInt(index)+')"/> '+
//	                  '<input type="text" name="custInfo['+parseInt(index)+'].custAreacode" placeholder="区号" class="num" maxlength="4" onblur="checkAreacode('+parseInt(index)+')"/>'+ 
//	                  '<input type="tel" name="custInfo['+parseInt(index)+'].custTel" value="" placeholder="电话号码" class="phone" maxlength="20" onblur="checkCustTel('+parseInt(index)+')"/>'+
//	                  '</div> <input type="text" name="custInfo['+parseInt(index)+'].custEmail" value="" placeholder="邮箱" maxlength="50" class="input-pub m-size last" onblur="checkEmail('+parseInt(index)+')" />'+
//	                  '<input type="hidden" name="custInfo['+parseInt(index)+'].custType" value="'+type+'"/>'+
//	                  '<a href="javascript:;" class="icons icons-reduce control-btn reduce-btn">'+
//	                  '<a href="javascript:;"  class="icons icons-add-btn control-btn add-more"></div>';
//	        var zjlenght = $(obj).closest('#zj-info').children('.input-block').length + 1;
//	        var userlenght = $(obj).closest('#user-info').children('.input-block').length + 1;
//	        $(obj).closest('.input-bt-line').append(tpl);
//	         if (zjlenght == 2) {
//	        	 $('#zj-info .icons-add-btn').attr('style','display:none');
//	        	 $('#zj-info .reduce-btn').attr('style','display:block');
//	             return;
//	         } else if (userlenght ==10) {
//	        	 $('#user-info .icons-add-btn').attr('style','display:none');
//	        	 $('#user-info .reduce-btn').attr('style','display:block');
//	             return;
//	         }else if(userlenght ==2){
//	        	 $('#user-info .reduce-btn').first().attr('style','display:block');
//	         }
//	    }
//	    // 添加咨询信息
//	    var dataids = parseInt($("#baseinfo").data('id'));
//	    $("#zj-info").on('click', '.icons-add-btn', function(event) {
//	        event.preventDefault();
//	        dataids++;
//	        count++;
//	        addItme($(this), dataids,0,count);
//	    });
//
//	    var usdataids = parseInt($("#userbaseinfo").data('id'));
//	    $("#user-info").on('click', '.icons-add-btn', function(event) {
//	        event.preventDefault();
//	        usdataids++;
//	        count++;
//	        addItme($(this), usdataids,1,count);
//	    });
//	    
//	    
//	    //删除行
//	    $("#zj-info").on('click','.reduce-btn',function(event){
//	    	event.preventDefault();
//	    	 $(this).closest('.input-block').remove();
//	    	 var zjlength=$('#zj-info .icons-add-btn').length;
//	    	 if(zjlength==1){
//	    		 $('#zj-info .reduce-btn').last().attr('style','display:none');
//	    		 $('#zj-info .icons-add-btn').last().attr('style','display:block');
//	    	 }
//	    });
//	    $("#user-info").on('click','.reduce-btn',function(event){
//	    	event.preventDefault();
//	    	$(this).closest('.input-block').remove();
//	    	var userlength=$('#user-info .icons-add-btn').length;
//	    	if(userlength==1){
//	    		$('#user-info .reduce-btn').last().attr('style','display:none');
//	    		$('#user-info .icons-add-btn').last().attr('style','display:block');
//	    	}
//	    });
	    
/*	    $(".tab-wrap").on('click', 'li', function(event) {
	        event.preventDefault();
	         Act on the event 
	        $(this).addClass('current').siblings('li').removeClass('current');
	        $(".table-box").eq($(this).index()).addClass('show').siblings('.table-box').removeClass('show');
	    });*/

//	    $(document).on('click', '.drop-list-box', function(event) {
//	        event.preventDefault();
//	        /* Act on the event */
//	        if ($(this).find('.task-time').is(":hidden")) {
//	            $(this).find('.task-time').slideDown(100);
//	        } else {
//	            $(this).find('.task-time').slideUp(10);
//	        }
//	    });
	    
	    $(document).on('click', function(event) {
	        if (!$(event.target).is('li') && !$(event.target).is('.select-menu')) {
	            $("table.iframe tbody").css('overflow-y', 'auto');
	            $('.task-time').slideUp(10);

	        }
	    });

	    // 自定义的下拉菜单点击事件
	    $(document).on('click', '.task-time li', function(event) {
	        event.preventDefault();
	        if ($(this).closest('.task-time').hasClass('addtask')) {
	            selectTaskTime($(this), $(this).closest('.task-time').siblings('.select-menu'));
	        } else {
	            selectTask($(this), $(this).closest('.task-time').siblings('.select-menu'));
	        }
	        $(this).closest('.task-time').slideUp(10);
	        $("table.iframe tbody").css('overflow-y', 'auto');
	    });

	    function selectTask(obj, target) {
	        var timeText = $(obj).text();
	        var timedata = $(obj).data('time');
	        $(target).prop({
	            value: timeText,
	            name: timedata,
	        });

	    }

	    function selectTaskTime(obj, target) {
	        var timeText = $(obj).text();
	        var timedata = $(obj).data('time');
	        $(target).prop({
	            value: timedata,
	            name: timedata,
	        });
	    }
	});

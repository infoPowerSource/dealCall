/*
 * @Author: Koala 
 * @Date: 2017-06-13 21:22:13 
 * @Last Modified by: huangzz
 * @Last Modified time: 2017-06-23 09:47:28
 */

// 初始化layer
layui.use('layer');
// 登录框高度
loginBoxCenter()
    // 表格隔行换色
setTableColor();


// 检查checkbox是否选中，并添加状态
isCheced();

$("body").on('click', '.set-luyin', function(event) {
    event.preventDefault();
    checkBoxs($(this));
});

$("body").on('click', '.rem-pwd-item', function(event) {
    event.preventDefault();
    checkBoxs($(this));
});




function isCheced(){
// 检查checkbox是否选中，并添加状态
var pwd = $("#remember-pwd");
var ly = $("#set-luyin");
if(pwd.prop('checked')){
    pwd.next('.icons').removeClass('icons-check-false').addClass('icons-check-true');
}else{
    pwd.next('.icons').removeClass('icons-check-true').addClass('icons-check-false');
}
if(ly.prop('checked')){
    ly.next('.icons').removeClass('icons-checkbox-un-bg').addClass('icons-checkbox');
}else{
        ly.next('.icons').removeClass('icons-checkbox').addClass('icons-checkbox-un-bg');
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
     isCheced();
}


// 登录校验
$(".login-btn").click(function(event) {
    event.preventDefault();
    loginCheck();

});

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
lookPwd();

function lookPwd() {
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

}


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


/*$(".pub-table > tbody > tr").hover(function() {
    $(this).addClass('hover');
    $(this).find('.td-control').show();
}, function() {
    $(this).removeClass('hover');
    $(this).find('.td-control').hide();
});*/

function setTableColor() {
    // 设置表格隔行换色
    var tbodys = $("body .pub-table").find('tbody');
    tbodys.find('tr:even').addClass('even');
    tbodys.find('tr:odd').addClass('odd');
}

function loginBoxCenter() {
    // 登录框垂直居中
    var lgheight = $(".login-box").height();

    $(".login-box").css('margin-top', -(lgheight / 2));
}

function loginCheck() {
    // 登录表单校验
    var mm = 0;
    $("#login-form .input-control").each(function(index, el) {
        var vals = $(el).val();
        var plhd = $(el).prop('placeholder');
        if ($(el).val() === '') {
            $("#warning-login").text(plhd + '不能为空');
            $(el)[0].focus();
            return false;
        } else {
            $("#warning-login").text('');
            mm++;
        }
        return mm;
    });
    if (mm == 3) {
        layer.msg('登录成功', {
            time: 1000
        }, function() {
            window.location.href = "index.html";

        });
    }
}
// 添加新会议
$("#send-task").click(function(event) {
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


$("#task-theme").focus(function(event) {
    $(this).removeClass('warning').next('.message').text('')
});



/*// 点击删除,
$(".td-control").on('click', '.del-task-btn', function(event) {
    event.preventDefault();
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

// 客服中心领取任务
$(".pub-table").on('click', '.get-task', function(event) {
    event.preventDefault();
    /* Act on the event */
    layer.msg('<p class="task-popup">任务已被领取</p>', { time: 1500 });
});

// 交易服务
$(".pub-table").on('click', '.task-theme', function(event) {
    event.preventDefault();
    /* Act on the event */

    /*  var tpls = '<div class="iframe-body service" id="iframe-main">' +
          '<div class="table-box show"> <div class="box-fixed service">' +
          '<form action=""> <div class="task-title">操作提示</div>' +
          '<div class="task-info"> <div class="item">' +
          '<span class="mr5">1.与咨询客户电话沟通</span>' +
          '<div class="drop-list-box service user">' +
          '<input type="text" name="bg-time" readonly="true" value="张鹏" id="task-ueses" class="input-pub select-menu" />' +
          '<ul class="task-time">' +
          '<li data-time="0">张鹏</li>' +
          '<li data-time="1">赵四</li> ' +
          '</ul></div>' +
          '<a href="javascript:;" class="td-btn service send">送回会中</a>' +
          '<a href="javascript:;" class="td-btn service ">呼叫</a></div>' +
          ' <div class="item"><span class="mr5">2.确认时间后外呼顾问入会，或设置自动外呼顾问时间</span>' +
          ' <div class="drop-list-box service"> ' +
          '<input type="text" name="bg-time" readonly="true" value="5分钟以后" id="task-huor" class="input-pub select-menu" />' +
          ' <ul class="task-time">' +
          ' <li data-time="0">立即外呼</li>' +
          ' <li data-time="1">5分钟以后</li>' +
          ' <li data-time="1">10分钟以后</li>' +
          ' </ul> </div>' +
          ' <a href="javascript:;" class="td-btn service">外呼</a></div>' +
          ' <div class="item">' +
          '<span>3.确定完成此任务</span><a href="javascript:;" class="td-btn service check-task">完成任务</a>' +
          '<span class="warning-hint">顾问尚未入会，确定要完成此任务？' +
          '<a href="javascript:;" class="check-item yes"><span class="icons icons-db-check"></span>确定</a>' +
          ' <a href="javascript:;" class="check-item cancle"><span class="icons icons-del"></span>取消</a></span> </div>' +
          ' <div class="item"><span class="mr5">* 会议问题反馈人：</span><span>王鹏</span> <span>13912345678</span></div></div> </form> </div> ' +
          '<table class="pub-table iframe service">' +
          ' <thead> <tr> <td width="15%">角色</td> <td width="15%">姓名</td>' +
          ' <td width="30%">电话</td> <td width="20%">语音</td> <td width="15%">状态</td>' +
          ' </tr> </thead> <tbody> <tr> <td width="15%">客户</td> <td width="15%">王鹏</td>' +
          ' <td width="30%">18638281552</td> <td width="20%"><span>发言</span></td>' +
          ' <td width="15%"><span class="online">在线</span></td> </tr>' +
          ' <tr> <td width="15%">顾问</td> <td width="15%">王鹏</td>' +
          ' <td width="30%">18638281552</td> <td width="20%"><span>发言</span></td> ' +
          '<td  width="15%"><span class="online">在线</span></td> </tr>' +
          ' <tr> <td width="15%">顾问</td> <td width="15%">王鹏</td> ' +
          '<td width="30%">18638281552</td> <td width="20%">-</td>' +
          ' <td  width="15%"><span class="offline">静音</span></td>' +
          ' </tr><tr> <td width="15%">&nbsp;</td> <td width="15%"></td>' +
          ' <td width="42%"></td> <td width="20%"></td> <td width="15%"></td> </tr> </tbody> </table> </div> ' +
          '<div class="warning-box"> <span class="icons icons-Improtant"></span><strong>紧急：</strong>有3个紧急任务等待领取，请尽快完成任务并领取紧急任务！ </div></div>';*/
    // layer.open({
    //     type: 2,
    //     title: false,
    //     closeBtn: 2,
    //     skin: 'edit-demo-class',
    //     area: ['1200px', '600px'],
    //     scrollbar: false,
    //     shadeClose: false, //开启遮罩关闭
    //     content: ['http://192.168.95.240:8892/answerPaperIndex.html?qnrId=201706080931349622590', 'yes'],
    //     cancel: function(index) {
    //         layer.msg('关闭成功！', { time: 1000 });
    //     }
    // });

    layer.open({
        type: 2,
        // type: 2,
        title: false,
        closeBtn: 2,
        // btn:false,
        skin: 'service-iframe-class',
        area: ['700px', '550px'],
        scrollbar: false,
        shadeClose: false, //开启遮罩关闭
        // content: tpls,
        content: ['iframe-service-table.html', 'no'],
        cancel: function(index) {
            layer.msg('关闭成功！', { time: 1000 });
        }
    });
});




// 确定完成客服任务
$('body').on('click', '.check-task', function(event) {
    event.preventDefault();

    $(".warning-hint").show();

});

// 点击确定完成任务
$("body").on('click', '.check-item.yes', function(evnet) {
    evnet.preventDefault();
    layer.msg('任务已完成', { time: 1500 });
    $(".warning-hint").hide();
});
// 点击取消任务
$("body").on('click', '.check-item.cancle', function(evnet) {
    evnet.preventDefault();
    $(".warning-hint").hide();
});




$(document).on('click', function(event) {
    // event.preventDefault();

    if (!$(event.target).is('.td-tips') && !$(event.target).is('.record') && !$(event.target).is('.td-send-btn') && !$(event.target).is('.td-email') && !$(event.target).is('.send-record p') && !$(event.target).is('.send-record') && !$(event.target).is('.td-input-block')) {

        $('.td-tips').hide();
    }

    /* Act on the event */
});
// 添加咨询信息
//function addItme(obj, id) {
//    var tpls = '<div class="input-block task" data-id="' + id + '"> <input type="text" name="task-theme" value="" placeholder="姓名" class="input-pub s-size" /> <div class="tel-group"> <input type="text" value="+86" class="base"> <input type="text" placeholder="区号" class="num"> <input type="tel" name="task-theme" value="" placeholder="电话号码" class="phone " /> </div> <input type="text" name="task-theme" value="" placeholder="邮箱" class="input-pub m-size last" /> <a href="javascript:;" class="icons icons-reduce control-btn reduce-btn"></a> <a href="javascript:;"  class="icons icons-add-btn control-btn add-more"></a></div>';
//
//    // var zjlenght = $(obj).closest('#zj-info').children('.input-block').length + 1;
//    // var userlenght = $(obj).closest('#user-info').children('.input-block').length + 1;;
//
//    // if (zjlenght > 2) {
//    //     layer.msg('顾问信息不能超过2条', { time: 1000 });
//    //     return;
//    // } else if (userlenght > 10) {
//    //     layer.msg('咨询客户信息不能超过10条', { time: 1000 });
//    //     return;
//    // } else {
//    // }
//    obj.closest('.input-bt-line').append(tpls);
//}
// 添加咨询信息
//var dataids = parseInt($("#baseinfo").data('id'));
//$("body").on('click', '.icons-add-btn', function(event) {
//    event.preventDefault();
//    // console.log($(this).parents('.input-bt-line'));
//    dataids++;
//    addItme($(this), dataids);
//
//});

// var usdataids = parseInt($("#userbaseinfo").data('id'));
// $("#user-info").on('click', '.icons-add-btn', function(event) {
//     event.preventDefault();
//     usdataids++;
//     addItme($(this), usdataids);

// });

// 删除行
//$("body").on('click', '.reduce-btn', function(event) {
//    event.preventDefault();
//    /* Act on the event */
//    $(this).closest('.input-block').remove();
//});

/*
$(".tab-wrap").on('click', 'li', function(event) {
    event.preventDefault();
     Act on the event 
    $(this).addClass('current').siblings('li').removeClass('current');
    $(".table-box").eq($(this).index()).addClass('show').siblings('.table-box').removeClass('show');
});*/

// 选择小时
$('body').on('click', '.drop-list-box', function(event) {
    event.preventDefault();
    /* Act on the event */

    if ($(this).find('.task-time').is(":hidden")) {
        $(this).find('.task-time').slideDown(100);
    } else {

        $(this).find('.task-time').slideUp(10);
    }

});




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

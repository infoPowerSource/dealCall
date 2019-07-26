/*
 * @Author: Koala 
 * @Date: 2017-06-13 22:29:26 
 * @Last Modified by: mikey.zhaopeng
 * @Last Modified time: 2017-06-14 23:05:02
 */

layui.use('layer');
setTableColor();
// 呼叫其它号码
$("html").on('click', '.call-other', function(event) {
    event.preventDefault();

    $('#call-user,.td-call-box').show();
});

$("body").on('click', '.call-send', function(event) {
    event.preventDefault();
    ckTel($(this));
});

$("body").on('click', '.td-del-btn', function(event) {
    event.preventDefault();
    $('#call-user,.td-call-box').hide();
});


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

function setTableColor() {
    // 设置表格隔行换色
    var tbodys = $("body .pub-table").find('tbody');
    tbodys.find('tr:even').addClass('even');
    tbodys.find('tr:odd').addClass('odd');
}

function ckTel(obj) {
    // 检测电话号码
    var tel = $('.phone-main').val();

    if (tel == "") {
        obj.closest('td').siblings('td').find('.message').html('请输入电话号码');
        $('.phone-main')[0].focus();
    } else {
        if (is_mobile(tel)) {
            obj.closest('td').siblings('td').find('.message').html('');
            layer.msg('呼叫成功', { time: 1000 });
        } else {
            obj.closest('td').siblings('td').find('.message').html('请输入正确电话号码');
            $('.phone-main')[0].focus();

        }

    }
}

function is_mobile(phone) {
    if (!(/^1[345789]\d{9}$/.test(phone))) {
        return false;
    } else {
        return true;
    }
}
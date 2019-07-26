$(document).ready(function () {
    readyToOnBlur = true;
    disabledResetButton();
    countDown();
    $("#checkcode").on('input',function (event) {
        if(event.target.value.length == 4){
            enabledResetButton();
        }else {
            disabledResetButton();
        }
    });
    $(".czmm_button").click(function () {
        var nowTime = new Date().getTime();
        var clickTime = $(this).attr("ctime");
        if (clickTime != 'undefined' && (nowTime - clickTime < 2000)) {
            alert("操作过于频繁，稍后再试");
            return;
        } else {
            $(this).attr("ctime", nowTime);
            if (checkIsValid()) {
                summit_fp_tel();
            }
        }
    });
});

function disabledResetButton() {
    $(".czmm_button").attr("style", "background:#caced3");
    $(".czmm_button").attr("disabled", "disabled");
}

function enabledResetButton() {
    $(".czmm_button").attr("style", "background:#4882bf");
    $(".czmm_button").removeAttr("disabled");
}

var delayTime = 120;
var valid = 1;

function countDown() {
    delayTime--;
    $(".fasong").html('重新发送<small>(' + delayTime + '秒)</small>');
    if (delayTime == 0) {
        $(".fasong").html('<p onclick="resendSMS()" style="background:#4882bf;cursor:pointer">重新发送</p>');
        $(".fasong").removeAttr("disabled");
        valid = 0;
        clearTimeout(t);
    } else {
        t = setTimeout(countDown, 1000);
    }
}

var readyToOnBlur = false;

function checkIsValid() {
    if (readyToOnBlur == false) {
        return true;
    }
    var checkcode = $("#checkcode").val();
    $('.checkcode_msg').empty();
    if (valid == 1 && (checkcode == '' || checkcode == '短信验证码')) {
        $('.checkcode_msg').text('请填写验证码');
        disabledResetButton();
        return false;
    }
    if (valid == 1) {
        enabledResetButton();
    } else {
        disabledResetButton();
    }
    return true;
}

function ResetButton() {
    var checkcode = $("#checkcode").val();
    if (valid == 1 && (checkcode == '' || checkcode == '短信验证码')) {
        $('.checkcode_msg').text('请填写验证码');
        disabledResetButton();
        return false;
    }
    if (valid == 1) {
        enabledResetButton();
    } else {
        disabledResetButton();
    }
    return true;
}

function summit_fp_tel() {
    var _session_mail = $("#importMsgInput_name").val();
    var _session_id = $("#importMsgInput_id").val();
    var checkcode = $("#checkcode").val();
    //alert(_session_mail+","+_session_id);
    if (valid != 1) {
        $('.checkcode_msg').text('验证码失效,请重新获取密码');
        return;
    }
    var url = BASE_PATH + "/login/findBackPassByTel_h?name=" + _session_mail + "&id=" + _session_id + "&checkcode=" + checkcode;
    $.ajax({
        url: url,
        data: {},
        type: "POST",
        success: function (result) {
            if (result.success) {
                window.location.href = BASE_PATH + "/login/resetPassword";
            } else {
                $('.checkcode_msg').text(result.msg);
//  	   						alert(result.msg);
            }
        }

    });
}

function resendSMS() {
    valid = 1;
    disabledResetButton();
    var _session_tel = $("#importMsgInput_name").val();
    var _session_id = $("#importMsgInput_id").val();
    //alert(_session_mail+","+_session_id);
    var url = BASE_PATH + "/login/findBackPassByTel_resendSMS?name=" + _session_tel + "&id=" + _session_id;
    $.ajax({
        url: url,
        data: $('#p').serialize(),
        type: "POST",
        success: function (result) {
            if (result.success) {
// 	   					    alert(result.msg);
                window.location.href = BASE_PATH + "/login/setPwdFromMobile?name=" + _session_tel + "&id=" + _session_id;
            } else {
                $('.checkcode_msg').text(result.msg);
                //alert(result.msg);
            }
        }

    });
}
$(document).ready(function () {
    layui.use('layer', function () {


        $(".re-send").click(function (event) {
            var nowTime = new Date().getTime();
            var clickTime = $(this).attr("ctime");
            if (clickTime != 'undefined' && (nowTime - clickTime < 2000)) {
                alert("操作过于频繁，稍后再试");
                return;
            } else {
                $(this).attr("ctime", nowTime);
                resendMail();
            }
        });
    });
});

function resendMail() {
    var _session_name = $("#importMsgInput_name").val();
    var _session_id = $("#importMsgInput_id").val();
    var _session_userDisplayName = $("#importMsgInput_userDisplayName").val();
    //alert(_session_userDisplayName);
    var url = BASE_PATH + "/login/findBackPassByMail_resendMail?name=" + _session_name + "&id=" + _session_id;
    $.ajax({
        url: url,
        data: $('#czmmform').serialize(),
        type: "POST",
        success: function (result) {
            layer.msg(result.msg, {time: 2E3, shade: 0.3});
        }

    });
}

function receiveMail() {
    var hash = {
        'quanshi.com': 'https://webmail.quanshi.com/',
        'gmail.com': 'http://mail.google.com',
        '163.com': 'http://mail.163.com',
        '126.com': 'http://mail.126.com',
        'qq.com': 'http://mail.qq.com',
        'live.cn': 'http://login.live.com',
        'hotmail.com': 'https://login.live.com',
        'yahoo.com.cn': 'http://mail.cn.yahoo.com',
        'sina.com': 'http://mail.sina.com.cn',
        'sina.cn': 'http://mail.sina.com.cn',
        'bjtu.edu.cn': 'http://mail.bjtu.edu.cn',
        'foxmail.com': 'http://mail.foxmail.com',
        'yeah.net': 'http://yeah.net',
        'sohu.com': 'http://mail.sohu.com',
        '139.com': 'http://mail.10086.cn',
        'yahoo.cn': 'http://mail.cn.yahoo.com',
        'sogou.com': 'http://mail.sogou.com',
        '21cn.com': 'http://mail.21cn.com',
        'tom.com': 'http://mail.tom.com',
        '263.net': 'http://mail.263.net',
        '189.cn': 'http://webmail3.189.cn'
    }
    // 点击登录邮箱
    var nowTime = new Date().getTime();
    var clickTime = $(this).attr("ctime");
    if (clickTime != 'undefined' && (nowTime - clickTime < 2000)) {
        alert("操作过于频繁，稍后再试");
        return;
    } else {
        $(this).attr("ctime", nowTime);
        var _session_mail = $("#importMsgInput_name").val();
        //alert(_session_mail);

        var _mail = _session_mail.split('@')[1];    //获取邮箱域
        var i = 0;
        for (var j in hash) {
            if (j == _mail) {
//	         alert(hash[_mail]);
//	         window.location.href =hash[_mail];
                window.open(hash[_mail]);
                i = 1;
                break;
            }
        }
        if (i == 0) {
//		 _mail = 'wu_923_b_08@1631.com'.split('@')[1];
//		 var _otherMail='http://mail.'+_mail;
//	     alert(_otherMail);
//	     window.location.href =_otherMail;
//		 alert("未匹配到邮箱地址，请自行获取邮件");
            window.open("")
        }
    }
}
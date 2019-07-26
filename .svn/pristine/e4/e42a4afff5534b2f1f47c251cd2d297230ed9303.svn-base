var url = BASE_PATH + "/static/js/appjs/icons.svg";

//生成验证码
function createCode() {
    var code;
    code = "";
    var codeLength = 4;
    var selectChar = new Array(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z');
    for (var i = 0; i < codeLength; i++) {
        var charIndex = Math.floor(Math.random() * 36);
        code += selectChar[charIndex];
    }
    return code;
}

function addCookie() {
    //如果记住密码选项被选中
    if (loginVM.rmbPwd) {
        var expdate = new Date();
        expdate.setTime(expdate.getTime() + 60 * (24 * 60 * 60 * 1000));
        //将用户名和密码写入到Cookie
        SetLastUser(loginVM.user.username, loginVM.user.password);
    }
    else {
        //如果没有选中记住密码,则立即过期
        DelCookie(id);
    }
}

function SetLastUser(usr, pwd) {
    var expdate = new Date();
    //当前时间加上两周的时间
    expdate.setTime(expdate.getTime() + 14 * (24 * 60 * 60 * 1000));
    SetCookie(id, usr + id + pwd, expdate);
}

function getCookie() {
    var value = GetCookie(id);
    if (null != value && "" != value) {
        var values = value.split(id);

        name = values[0];
        pwd = values[1];

        loginVM.user.username = name;
        loginVM.user.password = pwd;
        if(name != null && name != ""){
            loginVM.rmbPwd = true;
        }
    }
}

function isChecked() {
    // 检查checkbox是否选中，并添加状态
    var pwd = $("#remember-pwd");
    if (pwd.prop('checked')) {
        pwd.next('.icons').removeClass('icons-check-false').addClass('icons-check-true');
    } else {
        pwd.next('.icons').removeClass('icons-check-true').addClass('icons-check-false');
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

//删除Cookie
function DelCookie(name) {
    var exp = new Date();
    exp.setTime(exp.getTime() - 1);
    var cval = GetCookie(name);
    document.cookie = name + "=" + cval + "; expires=" + exp.toGMTString();
}

var id = "ABCD49BAC005-7D5B-4231-8CEA-16939BEACD67ABCD";
var _failCount = GetCookie("failCount") != null ? parseInt(GetCookie("failCount")) : 0;
var loginVM = new Vue({
    data: {
        user: {
            username: "",
            password: "",
            checkcode: ""
        },
        showPwd: false,
        clearPwd: false,
        captcha: "",
        //记住密码
        rmbPwd: false,
        loginTime: ""
    },
    created: function (){
        this.showCaptcha();
    },
    methods: {
        checkUserName: function () {
            var username = this.user.username;
            if (username == '') {
                layer.msg('请输入邮箱或手机', {time: 2E3, shade: 0.3});
                return false;
            }
            if (!Vbus.rMob(username)) {
                if (!Vbus.rema(username)) {
                    layer.msg("邮箱/手机格式不正确", {time: 2E3, shade: 0.3});
                    return false;
                }
            }
            return true;
        },
        checkPassword: function () {
            var pwd = this.user.password;
            if (pwd == "") {
                layer.msg('请输入密码', {time: 2E3, shade: 0.3});
                return false;
            }
            if (pwd.length < 6 || pwd.length > 20) {
                layer.msg('密码6~20位', {time: 2E3, shade: 0.3});
                return false;
            }
            return true;
        },
        isRobot: function () {
            var now = new Date().getTime();
            if (now - this.loginTime < 2000) {
                layer.msg('操作过于频繁，稍后再试', {time: 2E3, shade: 0.3});
                return true;
            } else {
                this.loginTime = now;
                return false;
            }
        },
        showCaptcha: function () {
            if(_failCount >= 3) {
                this.captcha = createCode();
                this.user.checkcode="";
            }
        },
        checkCaptcha: function () {
            if (this.user.checkcode == "") {
                layer.msg('请输入验证码', {time: 2E3, shade: 0.3});
                return false;
            }
            if (this.captcha.toUpperCase() != this.user.checkcode.toUpperCase()) {
                layer.msg('验证码错误', {time: 2E3, shade: 0.3});
                return false;
            }
            return true;
        },
        login: function () {
            if (this.isRobot()) {
                return;
            }
            if (!this.checkUserName()) {
                return;
            }
            if (!this.checkPassword()) {
                return;
            }
            if (_failCount >= 3 && !this.checkCaptcha()) {
                return;
            }
            layer.msg('登录中', {time: 6E3, shade: 0.3});
            $.ajax({
                type: "post",
                url: BASE_PATH + '/login/checkUser?username=' + this.user.username + '&password=' + this.user.password + '&checkcode=' + this.user.checkcode,
                dataType: "json",
                data: this.user,
                success: function (result) {
                    if (result.success) {
                        addCookie();
                        SetCookie("failCount", 0);
                        window.location.href = BASE_PATH + "/conf/confInfo";
                    } else {
                        _failCount += 1;
                        SetCookie("failCount", _failCount);
                        layer.msg(result.msg, {time: 2E3, shade: 0.3});
                        loginVM.showCaptcha();
                    }
                },
                error: function (jqXHR) {
                    layer.msg('提交失败！请刷新后重试', {time: 2E3, shade: 0.3});
                    $("#login-btn").attr('disabled', 1).css('pointer-events', 'none');
                }
            });
        }
    }
});
$(function () {
    var div = document.createElement("div");
    div.style.display = "none";
// 载入SVG
    if (localStorage.getItem(url)) {
        div.innerHTML = localStorage.getItem(url);
    } else {
        var xhr = new XMLHttpRequest();
        xhr.open("get", url);
        xhr.onload = function () {
            if (xhr.responseText) {
                div.innerHTML = xhr.responseText;
                // 本地存储
                localStorage.setItem(url, xhr.responseText);
            }
        };
        xhr.send(null);
    }
    loginVM.$mount("#login");
    getCookie();
    console.log(_failCount);
    if (top.location != self.location) {
        //说明你的页面在if框架中显示
        window.parent.location.reload(); //刷新父页面
    }
    var checkResult = $("#importMsgInput_name").val();
    if ("1" == checkResult) {
            layer.open({
                title : ['下线通知','font-size:18px;height:55px;background-color:#fff;border:none;padding: 20px 153px;'],
                resize:false,
                skin : 'Rectangle-9',
                area : [ '380px', '220px' ],
                shadeClose : false, // 开启遮罩关闭
                content : '你的帐号已在另一台手机登录。如非本人操作，则可能密码已泄露，建议立即修改密码',
                btn: '我知道了',
                btnAlign: 'c',
                scrollbar: false
            });
    }

});

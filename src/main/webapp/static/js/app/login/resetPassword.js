var url = BASE_PATH + "/static/js/appjs/icons.svg";
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
var resetPwdVM = new Vue({
    data: {
        user: {
            oldPassword:"",
            password: "",
            confirmPwd: ""
        },
        showOldPwd: false,
        showPwd: false,
        showConfirmPwd: false,
        loginTime: ""
    },
    methods: {
        confirmPwd: function () {
            if(this.user.confirmPwd == ""){
                layer.msg('请确认密码！', {time: 2E3, shade: 0.3});
                return false;
            }
            if(this.user.confirmPwd != this.user.password){
                layer.msg('两次输入的密码不一致!', {time: 2E3, shade: 0.3});
                return false;
            }
            return true;
        },
        checkPassword: function (pwd) {
            if (pwd == "") {
                layer.msg('请输入密码', {time: 2E3, shade: 0.3});
                return false;
            }
            if (pwd.length < 6 || pwd.length > 8) {
                layer.msg('密码6~8位', {time: 2E3, shade: 0.3});
                return false;
            }
            if(!isValidPass(pwd)){
                layer.msg('请输入数字和字母组合', {time: 2E3, shade: 0.3});
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
        submit: function () {
            if (this.isRobot()) {
                return;
            }
            if (!this.checkPassword(this.user.password)) {
                return;
            }
            if(!this.confirmPwd()){
                return;
            }
            layer.msg('提交中', {time: 6E3, shade: 0.3});
            var _session_id =$("#importMsgInput_id").val();
            $.ajax({
                type: "post",
                url: BASE_PATH + "/login/resetPass_h?id=" + _session_id + "&password=" + this.user.password,
                dataType: "json",
                data: this.user,
                success: function (result) {
                    layer.msg(result.msg, {shade: 0.3},function () {
                        if (result.success) {
                            window.location.href = BASE_PATH + "/login";
                        }
                    });
                },
                error: function (jqXHR) {
                    layer.msg('提交失败！请刷新后重试', {time: 2E3, shade: 0.3});
                    $("#login-btn").attr('disabled', 1).css('pointer-events', 'none');
                }
            });
        },
        modifyPassword:function () {
            if (this.isRobot()) {
                return;
            }
            if (!this.checkPassword(this.user.oldPassword)) {
                return;
            }
            if (!this.checkPassword(this.user.password)) {
                return;
            }
            if(!this.confirmPwd()){
                return;
            }
            layer.msg('提交中', {time: 6E3, shade: 0.3});
            $.ajax({
                type: "post",
                url: BASE_PATH + "/app/login/updatePass_h",
                dataType: "json",
                data: this.user,
                success: function (result) {
                    layer.msg(result.msg, {shade: 0.3},function () {
                        if (result.success) {
                            window.location.href = BASE_PATH + "/login";
                        }
                    });
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
    resetPwdVM.$mount("#app");
    if (top.location != self.location) {
        //说明你的页面在if框架中显示
        window.parent.location.reload(); //刷新父页面
    }

});
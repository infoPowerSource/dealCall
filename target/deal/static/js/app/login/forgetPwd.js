var url = BASE_PATH + "/static/js/appjs/icons.svg";
var uai = navigator.userAgent.toLowerCase();
var div = document.createElement("div");
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
var forgetPwdVM = new Vue({
    data: {
        user: {
            username: "",
            checkcode: ""
        },
        userNameType:"",
        captcha: "",
        //记住密码
        loginTime: ""
    },
    mounted: function () {
        this.showCaptcha();
    },
    methods: {
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
        checkUserName: function () {
            var username = this.user.username;
            if (username == '') {
                layer.msg('请输入邮箱或手机', {time: 2E3, shade: 0.3});
                return false;
            }
            if (!Vbus.rMob(username) && !Vbus.rema(username)) {
                    layer.msg("邮箱/手机格式不正确", {time: 2E3, shade: 0.3});
                    return false;
            }
            if(Vbus.rMob(username)){
                this.userNameType = "mob";
            }
            if(Vbus.rema(username)){
                this.userNameType = "eml";
            }
            return true;
        },
        showCaptcha: function () {
                this.captcha = createCode();
                this.user.checkcode="";
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
        submit: function () {
            if (this.isRobot()) {
                return;
            }
            if (!this.checkUserName()) {
                return;
            }
            if (!this.checkCaptcha()) {
                return;
            }
            layer.msg('提交中', {time: 6E3, shade: 0.3});
            $.ajax({
                type: "post",
                url: BASE_PATH + '/app/login/findBackPass',
                dataType: "json",
                data: this.user,
                success: function (result) {
                    if (result.success) {
                        if (0 == result.msg) {
                            window.location.href = BASE_PATH + "/login/setPwdFromEmail?name=" + result.name + "&id=" + result.id;
                        } else {
                            window.location.href = BASE_PATH + "/login/setPwdFromMobile?name=" + result.name + "&id=" + result.id;
                        }
                    } else {
                        layer.msg(result.msg, {time: 2E3, shade: 0.3});
                        forgetPwdVM.showCaptcha();
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
    //-----------------common.js--------------------
    div.style.display = "none";
    document.body.appendChild(div);
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
    //-----------------common.js--------------------

    forgetPwdVM.$mount("#app");

    //-----------------normal old--------------------
    createCode();
    var checkResult = $("#importMsgInput_name").val();
    var showContent = "";
    if (checkResult == "") {
        showContent = "";
    } else if (-1 == checkResult) {
        showContent = "无效用户";
    } else if (0 == checkResult) {
        showContent = "无效请求";
    } else if (2 == checkResult) {
        showContent = "请求超时";
    }
    $('.username_msg').text(showContent);

    $(".czmm_button").click(function () {
        var nowTime = new Date().getTime();
        var clickTime = $(this).attr("ctime");
        if (clickTime != 'undefined' && (nowTime - clickTime < 2000)) {
            alert("操作过于频繁，稍后再试");
            return;
        } else {
            $(this).attr("ctime", nowTime);
            if (checkIsValidUserName()) {
                if (validateCode()) {
                    summit_fp();
                }
            }
        }
    });
    $("#checkcodev").click(function () {
        createCode();
    });

    //-----------------normal old--------------------
});

$(window).load(function () {
    if (uai.match(/iphone/gi)) {
        $('body').append('<style>.mui-input-row-2 input{padding-top:17px}</style>');
    }
    try {
        mui('body').on('shown', '.mui-popover', function (e) {
            //console.log('shown', e.detail.id);//detail为当前popover元素
        });
        mui('body').on('hidden', '.mui-popover', function (e) {
            ////console.log('hidden', e.detail.id);//detail为当前popover元素
        });
        mui('body').on('tap', '.mui-popover-action li>a', function (e) {
            //alert(9)
            var a = this,
                parent;
            //console.log(e.detail)
            //console.log(e.target.hash)
            //根据点击按钮，反推当前是哪个actionsheet
            for (parent = a.parentNode; parent != document.body; parent = parent.parentNode) {
                if (parent.classList.contains('mui-popover-action')) {
                    break;
                }
            }
            //console.log('parent.id:'+parent.id)

            //关闭actionsheet
            mui('#' + parent.id).popover('toggle');
        });

        mui('.mui-content .mui-switch').each(function () { //循环所有toggle
            //alert(5)
            //toggle.classList.contains('mui-active') 可识别该toggle的开关状态
            //this.parentNode.querySelector('span').innerText = '状态：' + (this.classList.contains('mui-active') ? 'true' : 'false');
            /**
             * toggle 事件监听
             */
            this.addEventListener('toggle', function (event) {
                //console.log('event.detail.isActive:'+event.detail.isActive)
                //event.detail.isActive 可直接获取当前状态
                //this.parentNode.querySelector('span').innerText = '状态：' + (event.detail.isActive ? 'true' : 'false');
                vm.form.anpai.cfg.ly = event.detail.isActive;
            });
        });
    } catch (e) {
    }
});
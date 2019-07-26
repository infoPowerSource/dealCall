// 将分钟数量转换为小时和分钟字符串
function toHourMinute(minutes){
    var hour = Math.floor(minutes/60);
    var min = minutes%60;
    hour = hour < 10 ? "0"+hour : hour;
    min = min < 10 ? "0"+min : min;
    return hour + ":" + min;
    // 也可以转换为json，以方便外部使用
    // return {hour:Math.floor(minutes/60),minute:(minutes%60)};
}
var url = BASE_PATH + "/static/js/appjs/icons.svg";
var uai = navigator.userAgent.toLowerCase();
var myDate = new Date();
//nYear=myDate.getYear();        //获取当前年份(2位)
var nYear=myDate.getFullYear();    //获取完整的年份(4位,1970-????)
var nMoth=myDate.getMonth()+1;       //获取当前月份(0-11,0代表1月)
var nDate=myDate.getDate();        //获取当前日(1-31)
var nDay=myDate.getDay();         //获取当前星期X(0-6,0代表星期天)
myDate.getTime();        //获取当前时间(从1970.1.1开始的毫秒数)
var nhou=myDate.getHours();       //获取当前小时数(0-23)
var nmin=myDate.getMinutes();     //获取当前分钟数(0-59)
var nsec=myDate.getSeconds();     //获取当前秒数(0-59)
myDate.getMilliseconds();    //获取当前毫秒数(0-999)
var ndat=myDate.toLocaleDateString();     //获取当前日期
var mytime=myDate.toLocaleTimeString();     //获取当前时间
myDate.toLocaleString( );        //获取日期与时间  

if(nMoth<10){nMoth='0'+nMoth}
if(nDate<10){nDate='0'+nDate}
var nPdate=nDate-1;
var nNdate=nDate+1;

vm = new Vue({
    el: '#app',
    data: {
        form: {
            //会议
            huiyi: {
                //邮箱
                ema: {
                    v: "",
                    e: "",
                    s: 0,
                    //邮箱链接
                    u: ""
                }
            },
            //呼叫
            hujiao: {
                nam: {
                    v: "",
                    e: "",
                    s: 0
                },
                jue: {
                    v: "",
                    e: "",
                    s: 0
                },
                //国家
                guo: {
                    v: "+86",
                    e: "",
                    s: 0
                },
                //区号
                quh: {
                    v: "",
                    e: "",
                    s: 0
                },
                //电话
                pho: {
                    v: "",
                    e: "",
                    s: 0
                }
            },
           
            //登录
            L: {
                //邮箱或手机
                emP: {
                    v: "",
                    e: "",

                    s: 0
                },
                pas: {
                    v: "",
                    e: "",
                    s: 0,
                    //查看密码
                    l: false,
                    //清空密码
                    c: false
                },
                cod: {
                    v: "",
                    e: "",

                    s: 0
                },
                //记住密码
                che: false,
                suc: "",
                wat: ""
            }
            //找回密码的
            , G: {
                //邮箱或手机
                emP: {
                    v: "",
                    e: "",

                    s: 0
                },
                cod: {
                    v: "",
                    e: "",

                    s: 0
                },
                //手机验证码
                mcod: {
                    v: "",
                    e: "",

                    s: 0
                },
                //邮箱
                ema: {
                    v: "",
                    e: "",

                    s: 0,
                    //邮箱链接
                    u: ""
                },
                suc: "",
                wat: ""
            }
            //重置密码
            , C: {
                pas: {
                    v: "",
                    e: "",
                    s: 0,
                    //查看密码
                    l: false,
                    //清空密码
                    c: false
                },
                //确认新密码
                spas: {
                    v: "",
                    e: "",
                    s: 0,
                    //查看密码
                    l: false,
                    //清空密码
                    c: false
                },
                suc: "",
                wat: ""
            }

        }
    },
    mounted: function () {
        _this = this;
        //程序GG用token还是啥来判断手机找回密码
        if (Y.Loc.pathname.indexOf('wangjimima-sj.html') != -1) {
            _this.form.G.emP.v = '18311111111';//JSON.parse(localStorage.LGTj).v;
            Vbus.Stime('#m-succ-djs');
        }
        //程序GG用token还是啥来判断邮箱找回密码
        if (Y.Loc.pathname.indexOf('wangjimima-yx.html') != -1) {
            _this.form.G.emP.v = 'dawei.feng@quanshi.com';
        }
//        _this.form.anpai.zj.push(Y.zj());
//        _this.form.anpai.kh.push(Y.zj());
        ////console.log(_this.form.G)

    },
    methods: {
        inpfocus: function (a, b, c) {
        },
        inpchange: function (a, b, c) {
            //console.log('a:'+JSON.stringify(a))
            //console.log("索引"+JSON.stringify(b))
            //console.log(c)
            var self = this,
                Dd = a.dataset,
                //Dt=a.type,
                Dv = a.value,
                //DI=a.selectedIndex,
                //Di=Dd.i,
                //Dm=Dd.msg,
                //k=key这个在删除时会是数组的索引，长度是增减
                //Dk=Dd.k,
                Did = Dd.id,
                Dcl = Dd.cla,
                //Ddp=Dd.dp,
                Djv = Dd.jv;
            /*if(typeof DI!=="undefined"){
                CDd=a.childNodes[DI].dataset;
            }*/
            switch (Did) {
                //密码
                case "pas":
                //确认密码
                case "spas":
                    Vbus.Gpass(b);
                    break;
                //验证码
                case "code":
                    Vbus.Gcode(b, Djv);
                    break;
                //邮箱
                case "emai":
                    if (Vbus.rema(b.v) == false) {
                        b.e = Vbus.M.e + Vbus.M.e1;
                        b.s = 0;
                        //console.log(JSON.stringify(b))
                    } else {
                        b.e = '';
                        b.s = 1;
                    }
                    break;
        		//电话
    			case "pho":
//    				if(Vbus.checkEquanl(datas,obj.name,key,data,Did)==false){
//    					layer.msg('该手机号码已经存在',{time:2E3,shade:0.3});
//    				}
//    				if(Vbus.rMob(b.v)==false){
//    					layer.msg('请输入正确的手机号码',{time:2E3,shade:0.3});
//    					return;
//    				}
    				break;	
                default:
                    break;
            }
        }
        , inpclick: function (a, b, c) {
            /*a=event
             b:数据
             c:索引
             */
            //console.log('a:'+JSON.stringify(a))
            //console.log("b:"+JSON.stringify(b))
            //console.log(c)
            //console.log(typeof a)
            //console.log(typeof a.dataset)
            var _self = this;
            //console.log(typeof a)
            //console.log(_self)
            //console.log(_self.$refs)
            //console.log(_self.$refs[c])
            //return false;
            if (typeof a.dataset === "undefined") {
                //console.log('有子级')
                //console.log(_self.$refs[c])
                if (typeof a === "number") {
                    //console.log('循环的数组中需要用到')
                    //循环的数组中需要用到
                    var Dd = _self.$refs[c][a].dataset;
                } else {
                    //console.log('正常的')
                    var Dd = _self.$refs[c].dataset;
                }
                //console.log(Dd)
            } else {
                //console.log('无子级')
                var Dd = a.dataset,
                    Dv = a.value;
            }

            //console.log(event.target)
            //console.log(a)
            //console.log(Dd)
            //return false;
            var Dk = Dd.k,
                Did = Dd.id,
                Djv = Dd.jv;
            //console.log(Did)
            //console.log('DK:'+Dk)
            switch (Did) {

                //重新发送
                case "sendtime":
                    Vbus.Stime('#m-succ-djs');
                    break;
                //清空密码
                case "clear":
                    console.log(4185)
                    //清空
                    b[Djv] = '';
                    break;
                case "tog":
                case "tog2":
                    b[Djv] = !b[Djv];
                    //console.log('b[Djv]:'+b[Djv])
                    break;
                //新增专家
                //登录时的验证码
                case "mcod":
                    Vbus.Gcode(b, Djv);
                    break;
                //安排会议
                case "anpaihuiyi":

                    layer.msg('安排会议成功', {time: 2E3, shade: 0.3});

                    break;
                //更新会议
                case "gengxinhuiyi":

                    layer.msg('更新会议成功', {time: 2E3, shade: 0.3});

                    break;
                //添加会议
                case "tianjiahuiyi":

                    layer.msg('添加会议成功', {time: 2E3, shade: 0.3});

                    break;
                //发送会议录音
                case "Huiyi":
                    if (b.ema.v == "") {
                        layer.msg('请填写邮箱！', {time: 2E3, shade: 0.3});
                        return false;
                    }
                    ;
                    if (b.ema.s == 0) {
                        //mui.toast('欢迎体验Hello MUI');
                        layer.msg('邮箱不合法，请重新填写！', {time: 2E3, shade: 0.3});
                        return false;
                    }
                    ;
                    //layer.msg('会议录音已发送！', {time: 2E3, shade: 0.3});

                    
                    break;
                //呼叫其他号码
                case "Hujiao":
//                    if (b.nam.v == "") {
//                        layer.msg('请填写姓名！', {time: 2E3, shade: 0.3});
//                        return false;
//                    }
//                    ;
                    if (b.jue.v == "") {
                        layer.msg('请输入角色！', {time: 2E3, shade: 0.3});
                        return false;
                    }
                    ;
//                    if (b.quh.v == "") {
//                        layer.msg('请输入区号！', {time: 2E3, shade: 0.3});
//                        return false;
//                    }
//                    ;
                    if (b.pho.v == "") {
                        layer.msg('请输入电话号码！', {time: 2E3, shade: 0.3});
                        return false;
                    }
                    ;
//                    if (b.pho.s == 0) {
//                        layer.msg('电话号码有误！', {time: 2E3, shade: 0.3});
//                        return false;
//                    }
//                    ;
//                    layer.msg('正在呼叫中！', {time: 2E3, shade: 0.3});
//                    $('#m-hujiao').click();
                    break;
                //找回密码第一步提交
                case "GTj":

                    if (Vbus.rMob(b.emP.v) == false && Vbus.rema(b.emP.v) == false) {
                        layer.msg('请输入正确的邮箱或手机', {time: 2E3, shade: 0.3});
                        return false;
                    }
                    if (b.cod.s == 0) {
                        layer.msg('验证码为4位', {time: 2E3, shade: 0.3});
                        return false;
                    }
                    ;
                    var strG = {v: b.emP.v};
                    if (Vbus.rMob(b.emP.v) == true) {
                        strG.typ = 0;
                        strG.uri = 'wangjimima-sj.html';
                    }
                    if (Vbus.rema(b.emP.v) == true) {
                        strG.typ = 1;
                        strG.uri = 'wangjimima-yx.html';
                    }
                    localStorage.LGTj = JSON.stringify(strG);
                    $(a).attr('disabled',1).css('pointer-events','none');
                    $.ajax({
                        type:"post",
                        url: BASE_PATH + '/login/findBackPass',
                        dataType:"json",
                        timeout:8E3,
                        data:{
                            'username':b.emP.v,
                            'checkcode':b.cod.v
                        },
                        success:function(result){
                            if(result.success){
                                console.log(result.userDisplayName);
                                var setPwdPath = 'setPwdFromMobile';
                                if(0==result.msg){
                                    setPwdPath = 'setPwdFromEmail';
                                }
                                window.location.href =BASE_PATH +"/login/" + setPwdPath + "?name=" + result.name + "&id=" + result.id;
                            }else{
                                console.log(result.msg);
                                $('.username_msg').text(result.msg);
                                createCode() ;
                            }
                        },error:function(jqXHR){
                            layer.msg('提交失败！请刷新后重试',{time:2E3,shade:0.3});
                        }
                    });
                    break;
                //手机重置密码
                case "MCz":
                    if (b.mcod.s == 0) {
                        layer.msg('验证码为4位', {time: 2E3, shade: 0.3});
                        return false;
                    };
                    $(a).attr('disabled', 1).css('pointer-events', 'none');
                    $.ajax({
                        type:"post",
                        url: BASE_PATH + "/login/findBackPassByTel_h?name=" + b.mcod.v + "&id=" + b.mcod.v,
                        dataType:"json",
                        timeout:8E3,
                        data:{},
                        success:function(result){
                            if(result.success){
                                window.location.href =BASE_PATH +"/login/resetPassword";
                            }else{
                                $('.checkcode_msg').text(result.msg);
                                console.log(result.msg);
                            }
                        },error:function(jqXHR){
                            layer.msg('提交失败！请刷新后重试',{time:2E3,shade:0.3});
                        }
                    });
                    break;
                //重置密码
                case "CPs":

                    if (b.pas.s == 0 || b.spas.s == 0) {
                        layer.msg('密码6~20位', {time: 2E3, shade: 0.3});
                        return false;
                    }
                    ;
                    if (b.pas.v !== b.spas.v) {
                        layer.msg('两次输入的密码不一致', {time: 2E3, shade: 0.3});
                        return false;
                    }
                    ;
                    layer.msg('重置密码成功', {time: 2E3, shade: 0.3});
                    /*程序GG在这里发送请求
                        $(a).attr('disabled',1).css('pointer-events','none');
                        $.ajax({
                             type:"post",
                            url:'',
                            dataType:"json",
                            timeout:8E3,
                            data:{
                                'email':b.ema.v
                            },success:function(data){

                                $(a).removeAttr('disabled').css('pointer-events','auto');
                            },error:function(jqXHR){
                                    layer.msg('提交失败！请刷新后重试',{time:2E3,shade:0.3});
                            }
                        });*/
                    break;
                //快捷登录
                case "Log":
                    if (b.emP.v == "") {
                        layer.msg('请输入邮箱或手机', {time: 2E3, shade: 0.3});
                        return false;
                    }
                    ;
                    if (b.pas.v == "") {
                        layer.msg('请输入密码', {time: 2E3, shade: 0.3});
                        return false;
                    };
                    /*if (b.cod.v == "") {
                        layer.msg('请输入验证码', {time: 2E3, shade: 0.3});
                        return false;
                    }
                    ;*/
                    if (b.pas.s == 0) {
                        layer.msg('密码6~20位', {time: 2E3, shade: 0.3});
                        return false;
                    }
                    ;
                    /*if (b.cod.s == 0) {
                        layer.msg('验证码为4位', {time: 2E3, shade: 0.3});
                        return false;
                    }
                    ;
                    if (b.pas.s == 0 || b.cod.s == 0) {
                        layer.msg('请完善信息后再提交！', {time: 2E3, shade: 0.3});
                        return false;
                    }
                    ;*/
                    layer.msg('登录中', {time: 6E3, shade: 0.3});
                    //程序GG在这里发送请求
                        $(a).attr('disabled',1).css('pointer-events','none');
                        $.ajax({
                            type:"post",
                            url: BASE_PATH + '/login/checkUser',
                            dataType:"json",
                            timeout:8E3,
                            data:{
                                'username':b.emP.v,
                                'password':b.pas.v,
                                'checkcode':b.cod.v
                            },
                            success:function(data){
                                window.location.href = BASE_PATH +"/conf/confInfo"  //"/conf/confInfo";
                                $(a).removeAttr('disabled').css('pointer-events','auto');
                            },error:function(jqXHR){
                                    layer.msg('提交失败！请刷新后重试',{time:2E3,shade:0.3});
                            }
                        });
                    break;
                default:
                    break;
            }
        }

    }
});

$(window).load(function () {
    wiv = [
        window.outerWidth,
        window.outerHeight,
        document.getElementById('m-main'),
        document.getElementById('loading'),
        document.getElementById('loadNum'),
        document.getElementById('container'),
        document.getElementsByClassName('slide'),
        document.getElementsByClassName('pagination-bar'),
        document.getElementsByClassName('swiper-pagination-current'),
    ];
    //  alert(wiv)
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

            //info.innerHTML = "你刚点击了\"" + a.innerHTML + "\"按钮";
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
    //点击重新发送按钮
    $('#m-fasong').on('touchstart', function () {
        layer.msg('邮件已重新发送', {time: 2E3, shade: 0.3});
    });


    //切换
    $('.f-hd-list').on('touchstart', function () {
        var i = $(this).index('.f-hd-list');
        $(this).addClass('on').siblings('.f-hd-list').removeClass('on').end().parent('.f-tabs-hd').siblings('.f-tabs-bd').find('.f-bd-list:eq(' + i + ')').css('display', 'block').siblings('.f-bd-list').css('display', 'none');

    });
    
    var div = document.createElement("div");
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
});

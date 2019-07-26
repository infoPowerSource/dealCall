var laytpl;
var custSize = 0;
var onblurReady = false;
function advanced() {
    $(".advanced").toggle();
}
function checkTime() {
    var regTime = /^(0\d{1}|1\d{1}|2[0-3]):([0-5]\d{1})$/;
    var duration = $('#confDuration').val();
    $("#beginTime").closest(".conf-begin-time-wrap").siblings(".message").text("");
    if (regTime.test(duration)) {
        return true;
    } else {
        $("#beginTime").closest(".conf-begin-time-wrap").siblings(".message").text("时长的时间格式不正确");
        return false;
    }
}
function checkContryCode(index) {
    if (onblurReady == false) {
        return true;
    }
    var regCode = /^\+([1-9]|[1-9][0-9]*)$/;
    var data = $("input[name='custInfo[" + index + "].custContryCode']").val().trim();
    var type = $("input[name='custInfo[" + index + "].custType']").val();
    if (data.length && data[0] !== "+") {
        data = "+" + data;
    }

    $("input[name='custInfo[" + index + "].custContryCode']").val(data);

    if (regCode.test(data)) {
        return true;
    } else {
        $("input[name='custInfo[" + index + "].custContryCode']").nextAll(".message").text("");
        $("input[name='custInfo[" + index + "].custContryCode']").val("+86");
        return false;
    }
}
function checkAreacode(input) {
    if (onblurReady == false) {
        return true;
    }
    var custItem = $(input).closest(".conf-user-item");
    var custType = custItem.find(".conf-user-item-type").val();
    var tel = custItem.find(".conf-user-item-phone").val().trim();
    var contryCode = custItem.find(".conf-user-item-contry").val().trim();
    //咨询客户信息可不填,填了任意项须校验
    if(custType == 1){
        var name = custItem.find(".conf-user-item-name").val();
        var areacode = custItem.find(".conf-user-item-area").val();
        var email = custItem.find(".conf-user-item-email").val();
        if((name == "" || name == "姓名")
            && (areacode == "" || areacode == "区号")
            && (tel == "" || tel == "电话号码")
            && (email == "" || email == "邮箱")
        ){
            return true;
        }
    }
    //国内电话号码校验规则,国外不校验
    if(contryCode =="+86"){
        if(tel.length > 0 && tel.length < 11){
            if(input.value == ""||input.value == input.placeholder){
                custItem.find(".message").text("请输入区号");
                cableAction($(input),$(input).parent());
                return false;
            }
            if(!is_areaCode(input.value)){
                custItem.find(".message").text("请输入正确的区号");
                cableAction($(input),$(input).parent());
                return false;
            }
        }
    }else{
        if(!is_num(input.value)){
            custItem.find(".message").text("请输入正确的区号");
            cableAction($(input),$(input).parent());
            return false;
        }
    }
    return true;
}
function checkCustTel(input) {
    if (onblurReady == false) {
        return true;
    }
    var custItem = $(input).closest(".conf-user-item");
    var custType = custItem.find(".conf-user-item-type").val();
    var contryCode = custItem.find(".conf-user-item-contry").val().trim();
    //咨询客户信息可不填,填了任意项须校验
    var name = custItem.find(".conf-user-item-name").val();
    var areacode = custItem.find(".conf-user-item-area").val();
    var tel = custItem.find(".conf-user-item-phone").val();
    var email = custItem.find(".conf-user-item-email").val();
    //咨询客户信息可不填,填了任意项须校验
    if(custType == 1){
        if((name == "" || name == "姓名")
            && (areacode == "" || areacode == "区号")
            && (tel == "" || tel == "电话号码")
            && (email == "" || email == "邮箱")
        ){
            return true;
        }

        if((tel == "" || tel == "电话号码") && (email == "" || email == "邮箱")){
            custItem.find(".message").text("请输入电话号码或者邮箱");
            cableAction($(input),$(input).parent());
            return false;
        }
    }

    if(custType == 0){
        if(tel == "" || tel == "电话号码"){
            custItem.find(".message").text("请输入顾问的电话号码");
            cableAction($(input),$(input).parent());
            return false;
        }
    }

    if(input.value != "" && input.value != input.placeholder){
        //国内电话号码校验规则,国外不校验
        if(contryCode =="+86"){
            if(input.value.length == 11){
                if(!is_mobile(input.value)){
                    custItem.find(".message").text("请输入正确的手机号码");
                    cableAction($(input),$(input).parent());
                    return false;
                }
            }else {
                if(!is_telephone(input.value)){
                    custItem.find(".message").text("请输入正确的电话号码");
                    cableAction($(input),$(input).parent());
                    return false;
                }
                if(!custItem.find(".conf-user-item-area").get(0).onblur()){
                    return false;
                }
            }
        }else{
            if(!is_num(input.value)){
                custItem.find(".message").text("请输入正确的电话号码");
                cableAction($(input),$(input).parent());
                return false;
            }
        }
        if(!checkExsit(input)){
            custItem.find(".message").text("该电话号码已经存在");
            cableAction($(input),$(input).parent());
            return false;
        }
    }

    return true;
}
function checkExsit(sourceInput) {
    if(sourceInput.value == ""){
        return true;
    }
    var className = sourceInput.className.split(" ").pop();
    var targetInputs = $("input." + className);
    for(var i=0;i<targetInputs.length;i++){
        if(sourceInput.name == targetInputs[i].name){
            continue;
        }
        if(sourceInput.value == targetInputs[i].value){
            return false;
        }
    }
    return true;
}
function checkName(input){
    if (onblurReady == false) {
        return true;
    }
    var custItem = $(input).closest(".conf-user-item");
    var custType = custItem.find(".conf-user-item-type").val();
    //咨询客户信息可不填,填了任意项须校验
    if(custType == 1){
        var name = custItem.find(".conf-user-item-name").val();
        var contryCode = custItem.find(".conf-user-item-contry").val();
        var areacode = custItem.find(".conf-user-item-area").val();
        var tel = custItem.find(".conf-user-item-phone").val();
        var email = custItem.find(".conf-user-item-email").val();
        if((name == "" || name == "姓名")
            && (areacode == "" || areacode == "区号")
            && (tel == "" || tel == "电话号码")
            && (email == "" || email == "邮箱")
        ){
            return true;
        }
    }

    if(input.value == ""||input.value == input.placeholder){
        custItem.find(".message").text("请输入姓名");
        cableAction($(input));
        return false;
    }

    custItem.find(".conf-user-item-name").val(input.value.trim());
    if(!checkExsit(input)){
        custItem.find(".message").text("该姓名已经存在");
        cableAction($(input));
        return false;
    }
    return true;
}
function checkEmail(input) {
    if (onblurReady == false) {
        return true;
    }
    var custItem = $(input).closest(".conf-user-item");
    var custType = custItem.find(".conf-user-item-type").val();
    //咨询客户信息可不填,填了任意项须校验
    if(custType == 1){
        var name = custItem.find(".conf-user-item-name").val();
        var contryCode = custItem.find(".conf-user-item-contry").val();
        var areacode = custItem.find(".conf-user-item-area").val();
        var tel = custItem.find(".conf-user-item-phone").val();
        var email = custItem.find(".conf-user-item-email").val();
        if((name == "" || name == "姓名")
            && (areacode == "" || areacode == "区号")
            && (tel == "" || tel == "电话号码")
            && (email == "" || email == "邮箱")
        ){
            return true;
        }
    }
    //邮箱非必填
    if(input.value == ""||input.value == input.placeholder){
        return true;
    }
    if (!is_email(input.value)) {
        custItem.find(".message").text("请输入正确的邮箱");
        cableAction($(input));
        return false;
    }
    if(!checkExsit(input)){
        custItem.find(".message").text("邮件地址已经存在");
        cableAction($(input));
        return false;
    }
    return true;
}
function clearMsg() {
    $(".message").text("");
    $("input").removeClass("input-warnning");
    $(".tel-group").removeClass("input-warnning");
}
function verifyInputData() {
    clearMsg();
    var custs = $(".conf-user-item");
    for(var i=0;i<custs.length;i++){
        if(!$(custs[i]).find(".conf-user-item-name").get(0).onblur()
            || !$(custs[i]).find(".conf-user-item-contry").get(0).onblur()
            || !$(custs[i]).find(".conf-user-item-phone").get(0).onblur()
            || !$(custs[i]).find(".conf-user-item-email").get(0).onblur()){
            return false;
        }
    }
    return true;
}
function validateConfig(config) {
    if(config == 3) {
        var custs = $(".conf-user-item");
        for(var i=0;i<custs.length;i++){
            if($(custs[i]).find(".conf-user-item-phone").get(0).value.trim() == ""
                && ($(custs[i]).find(".conf-user-item-name").get(0).value.trim() != ""
                    || $(custs[i]).find(".conf-user-item-email").get(0).value.trim() != "")){
                layer.msg("会议设置'所有咨询客户入会，自动外呼顾问'，请正确填写所有咨询客户电话!");
                return false;
            }
        }
    }
    return true;
}
function cancelConfInfo() {
    window.location.href = BASE_PATH + "/conf/confInfo";
}

function isValidChar(str){
    var invalidChar=/^([\u4E00-\uFA29]|[\uE7C7-\uE7F3]|[a-zA-Z0-9_-]|\.|@)+$/;
    if(invalidChar.test(str)){
        return true;
    }else{
        return false;
    }
}
function getConfigIndex(config) {
    if (config == "到达会议预约时间，自动外呼顾问") {
        return 1;
    } else if (config == "有咨询客户入会后，自动外呼顾问") {
        return 2;
    } else if (config == "所有咨询客户入会后，自动外呼顾问") {
        return 3;
    } else if (config == "与咨询客户确认后，由客服外呼顾问") {
        return 4;
    }
}
function cableAction($targetDom,$colorDom){
    $targetDom.focus();
    if(!$colorDom){
        $colorDom = $targetDom;
    }
    $colorDom.addClass("input-warnning");
}
function addItme(el) {
    if($(".conf-user-item").length >= 12){
        return false;
    }
    var cust = $(el).closest('.input-bt-line');
    if(cust.attr("id") == "zj-info"){
        var custItem = laytpl(custTpl.innerHTML).render({
            i: custSize,
            t: 0
        });
        cust.append(custItem);
        setPlaceholder();
        //初始化国家码列表
        getCountryCode(custSize,false);
        custSize++;
        cust.find(".add-more").hide();
        if(cust.find(".conf-user-item").length < 2){
            cust.find(".add-more").last().show();
        }
    }
    if(cust.attr("id") == "user-info"){
        cust.append(laytpl(custTpl.innerHTML).render({
            i: custSize,
            t: 1
        }));
        cust.append(custItem);
        setPlaceholder();
        //初始化国家码列表
        getCountryCode(custSize,false);
        custSize++;
        cust.find(".add-more").hide();
        if(cust.find(".conf-user-item").length < 10){
            cust.find(".add-more").last().show();
        }
    }
    if(cust.find(".conf-user-item").length == 1){
        cust.find(".reduce-btn").hide();
    }else cust.find(".reduce-btn").first().show();
}
//function hideSelectItem(){
//    var custs = $(".conf-user-item");
//    var khlen=0;
//    for(var i=0;i<custs.length;i++){
//        var custType = $(custs[i]).find(".conf-user-item-type").val();
//        var tel=$(custs[i]).find(".conf-user-item-phone").val();
//        var name =$(custs[i]).find(".conf-user-item-name").val();
//        if(custType == 1 && name!=""){
//            if(tel==""||tel == "电话号码"){
//                khlen++;
//            };
//        }
//    }
//    if(khlen>0){
//        return false;
//    }
//    return true;
//}
function removeItme(el) {
    var cust = $(el).closest('.input-bt-line');
    $(el).closest('.conf-user-item').remove();
    cust.find(".add-more").hide().last().show();
    if(cust.find(".conf-user-item").length == 1){
        cust.find(".reduce-btn").hide();
    }
}
layui.use('layer', function() {
    var ifshow = true;
    //定义下拉列表
    $(document).on('click', '.drop-list-box', function(event) {
        event.preventDefault();
        /* Act on the event */
        if ($(this).find('.code_list').is(":hidden")) {
            $(this).find('.code_list').slideDown(100);
        } else {
            if(ifshow){
                $(this).find('.code_list').slideUp(10);
            }
        }
        ifshow = true;
    });
    $(document).on('click', function(event) {
        if (!$(event.target).is('li') && !$(event.target).is('.select-menu')) {
            $("table.iframe tbody").css('overflow-y', 'auto');
            $('.code_list').slideUp(10);
        }
    });

    $(document).on('click', '.drop-list-box', function(event) {
        event.preventDefault();
        /* Act on the event */
        if ($(this).find('.task-time').is(":hidden")) {
//            if(!hideSelectItem()){
//                $(this).find('.conf_config_3').hide();
//            }else{
//                $(this).find('.conf_config_3').show();
//            }
            $(this).find('.task-time').slideDown(100);
        } else {
            $(this).find('.task-time').slideUp(10);
        }
    });
    // 自定义的下拉菜单点击事件
    $(document).on('click', '.code_list li', function(event) {
        event.preventDefault();
        var timeText = $(this).text();
        var timedata = $(this).data('time');
        var target = $(this).closest('.code_list').siblings('.select-menu')
        if(timeText == "查看更多国家"){
            getCountryCode(timedata,true);
            ifshow=false;
            $(target).prop({
                value: "+86"
            });
        }else{
            $(target).prop({
                value: "+"+ timedata
            });
        }
        if(ifshow){
            $(this).closest('.code_list').slideUp(10);
        }
    });
});
//初始化国家码，type=true表明把数据库中所有国家列出，否则只显示5个常用国家
function getCountryCode(itemid,type){
    $.ajax({
        type: 'GET',
        url: BASE_PATH + "/create/countrycode",
        success: function (data) {
            $("#custInfo"+itemid+" li").remove();
            if(type==true){
                $.each(data,function(index,item){
                    $("#custInfo"+itemid).append("<li data-time='"+item.code+"'>"+item.country+"</li>");
                });
            }else{
                $.each(data,function(index,item){
                    if(index<6){
                        $("#custInfo"+itemid).append("<li data-time='"+item.code+"'>"+item.country+"</li>");
                    }
                });
                //最后添加一条链接
                $("#custInfo"+itemid).append("<li data-time='"+itemid+"'>查看更多国家</li>");
            }
        }
    });
}
function notify(el){
    if(el.checked){
        el.value=1
    }else {
        if($(el).siblings("input").val()==0){
            event.preventDefault();
            layer.msg("请至少选择一项通知！");
        }else {
            el.value=0
        }
    }
}
$(function() {
    var confUserManager = {
        elements: {
            container: $("#addTask"),
            userList: $(".conf-user-list")
        },
        init: function() {
            this.bindEvent();
        },
        bindEvent: function() {
            var self = this;
            this.elements.container.on("input", ".conf-user-item-name", function() {
                var name = $(this).val();
                if (name === "") {
                    return;
                }

                var phone = $(this).siblings(".tel-group").find(".conf-user-item-phone").val();
                var email = $(this).siblings(".conf-user-item-email").val();
                self.searchResult({
                    name: name,
                    phone: phone,
                    email: email
                }, $(this));
            }).on("input", ".conf-user-item-phone", function() {
                var phone = $(this).val();
                if (phone === "") {
                    return;
                }

                var name = $(this).closest(".tel-group").siblings(".conf-user-item-name").val();
                var email = $(this).closest(".tel-group").siblings(".conf-user-item-email").val();
                self.searchResult({
                    name: name,
                    phone: phone,
                    email: email
                }, $(this));
            }).on("input", ".conf-user-item-email", function() {
                var email = $(this).val();
                if (email === "") {
                    return;
                }

                var phone = $(this).siblings(".tel-group").find(".conf-user-item-phone").val();
                var name = $(this).siblings(".conf-user-item-name").val();
                self.searchResult({
                    name: name,
                    phone: phone,
                    email: email
                }, $(this));
            });

            $(document).on("click", function() {
                self.elements.userList.hide();
            });
        },
        searchResult: function(query, $input) {
            var url = BASE_PATH + "/conf/getConfUser/";
            var self = this;

            if (self.searchResult.timer) {
                clearTimeout(self.searchResult.timer);
                self.searchResult.timer = null;
            }

            self.searchResult.timer = setTimeout(function() {
                $.ajax({
                    type : "POST",
                    // 提交的数据
                    data : query,
                    url : url,
                    cache : false,
                    success : function(data) {
                        if (data) {
                            data = JSON.parse(data);
                        }
                        if (Object.prototype.toString.call(data) !== "[object Array]") {
                            return self.showResult([], $input);
                        }

                        self.showResult(data, $input);
                    }
                });
            }, 500);
        },
        showResult: function(data, $input) {
            if (data.length === 0) {
                return this.elements.userList.empty().hide();
            }

            var self = this;
            var $confUserItem = $input.closest(".conf-user-item");
            var offset = $confUserItem.offset();
            var html = "";
            for (var i = 0, len = data.length; i < len; i++) {
                html += "<li class='user-item'>"
                    + "<span class='user-item-info user-name'>" + (data[i].name || "") + "</span>"
                    + "<span class='user-item-info user-contry'>" + (data[i].contry || "") + "</span>"
                    + "<span class='user-item-info user-area'>" + (data[i].area || "") + "</span>"
                    + "<span class='user-item-info user-phone'>" + (data[i].phone || "") + "</span>"
                    + "<span class='user-item-info user-email' " + (data[i].email ? "title='" + data[i].email + "'" : "") + ">" + (data[i].email || "") + "</span>"
                    +"</li>";
            }

            this.elements.userList.css({
                left: offset.left,
                top: offset.top + $confUserItem.height()
            }).html(html)
                .show()
                .off("click", ".user-item")
                .on("click", ".user-item", function() {
                    var $user = $(this);
                    $confUserItem.find(".conf-user-item-name").val($user.find(".user-name").text()).nextAll(".message").text("");
                    $confUserItem.find(".conf-user-item-contry").val($user.find(".user-contry").text()).nextAll(".message").text("");
                    $confUserItem.find(".conf-user-item-area").val($user.find(".user-area").text()).closest(".tel-group").nextAll(".message").text("");
                    $confUserItem.find(".conf-user-item-phone").val($user.find(".user-phone").text()).closest(".tel-group").nextAll(".message").text("");
                    $confUserItem.find(".conf-user-item-email").val($user.find(".user-email").text()).nextAll(".message").text("");

                    setTimeout(function() {
                        $confUserItem.find(".conf-user-item-name").blur();
                        $confUserItem.find(".conf-user-item-contry").blur();
                        $confUserItem.find(".conf-user-item-area").blur();
                        $confUserItem.find(".conf-user-item-phone").blur();
                        $confUserItem.find(".conf-user-item-email").blur();
                    }, 300);

                    self.elements.userList.hide();
                });
        }
    };

    confUserManager.init();
    $("input, textarea").attr("spellcheck", false);

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

    onblurReady = true;
    $('#sendtask').removeAttr("disabled");

    $(document).on('click', '.task-time li', function(event) {
        event.preventDefault();
        if ($(this).closest('.task-time').hasClass('addtask')) {
            $("#beginTime").closest(".conf-begin-time-wrap").siblings(".message").text("");
        }
    });
    $("body").on('click', '.set-luyin', function(event) {
        event.preventDefault();
        checkBoxs($(this));
        if ($("#set-luyin").prop("checked")) {
            $("input[name='tapedMark']").prop("checked", true);
        } else {
            $("input[name='tapedMark']").prop("checked", false);
        }
    });

    $("#addTask").on("blur", "input", clearMsg);
    $("#beginTime").on("blur",function(){
        var $this = $(this);
        $this.siblings(".message").text("").show();
        $this.closest(".tel-group").siblings(".message").text("");
        $this.removeClass("input-warnning");
    });
    $("#beginTime").keydown(function(event){
        var _this = this;
        if (event.keyCode == 13){
            console.log($(_this).val());
            event.stopPropagation();
            _this.setSelectionRange(_this.value.length, _this.value.length);
            return;
        }
    });
    /*安排会议页面，日期默认为今天，时间默认为当前时间的下一个整点（以半小时为一个单位）；*/
    var nowTime = new Date();

    var nowHours = nowTime.getHours() < 10 ? "0" + nowTime.getHours() : nowTime.getHours();
    var nowMinutes = nowTime.getMinutes();
    if (nowMinutes > 30){
        nowTime = new Date(nowTime.getTime() + 1 * 60 * 60 * 1000);
        nowMinutes = "00";
    }else{
        nowMinutes = "30";
    }
    var beginHours = $("#beginHours");
    var beginMinutes = $("#beginMinutes");
    beginHours.val(nowHours);
    beginMinutes.val(nowMinutes);
    var nowYear = nowTime.getFullYear();
    var nowMonth = (nowTime.getMonth()+1) < 10 ? "0"+(nowTime.getMonth()+1) : (nowTime.getMonth()+1);
    var nowDate = nowTime.getDate() < 10 ? "0"+nowTime.getDate() : nowTime.getDate();
    var days = [ "周日", "周一", "周二", "周三", "周四", "周五", "周六" ];

    $("#beginTime").val(nowYear+"/"+nowMonth+"/"+nowDate);
    $(".weekday").text("(" + days[nowTime.getDay()] + ")");

    new DatePicker(".conf-begin-time-wrap", "beginTime", function(date) {
        $(".weekday").text(date ? "(" + days[date.getDay()] + ")" : "");
        $("#beginTime").closest(".conf-begin-time-wrap").siblings(".message").text("");
    }).selectedRang=[new Date(), null];
    //handle the fucking DatePicker can't see in the firefox
    $("#beginTime").click( function () {
        var sp = $("html,body").scrollTop();
        $("html,body").scrollTop(sp+1);
        setTimeout(function () {
            $("html,body").scrollTop(sp);
        },1);
    });

    var hourList = $(".hour-list");
    var minuteList = $(".minute-list");
    var hourHtml = "";
    var minuteHtml = "";
    for (var i = 0; i < 24; i++) {
        hourHtml += "<li data-time='" + (i < 10 ? "0" + i : i) + "'>" + (i < 10 ? "0" + i : i) + "</li>"
    }

    for (var i = 0; i < 60; i++) {
        minuteHtml += "<li data-time='" + (i < 10 ? "0" + i : i) + "'>" + (i < 10 ? "0" + i : i) + "</li>";
    }

    hourList.empty().html(hourHtml);
    minuteList.empty().html(minuteHtml);

    beginHours.click(function(event) {
        event.stopPropagation();
        $(".pickAdate-inner").hide();
        minuteList.hide();
        hourList.show();
        var hour = $(this).val();
        hourList.find(".active").removeClass("active").end().find("li[data-time=" + hour + "]").addClass("active");
        var offsetTop = hourList.find(".active").length === 0 ? 0 : hourList.find(".active")[0].offsetTop - 2;
        hourList.animate({scrollTop: offsetTop}, 200);
        $("#beginTime").closest(".conf-begin-time-wrap").siblings(".message").text("");
    }).on("blur", function() {
        var time = $(this).val().replace(/[^0-9]/g,'').slice(0, 2);
        time = time === "" || time - 0 > 23 ? "00" : time;
        $(this).val(time.length === 1 ? "0" + time : time);
    });

    beginMinutes.click(function(event) {
        event.stopPropagation();
        $(".pickAdate-inner").hide();
        var hour = $(this).val();
        hourList.hide();
        minuteList.show();
        var minute = $(this).val();
        minuteList.find(".active").removeClass("active").end().find("li[data-time=" + minute + "]").addClass("active");
        var offsetTop = minuteList.find(".active").length === 0 ? 0 : minuteList.find(".active")[0].offsetTop - 2;
        minuteList.animate({scrollTop: offsetTop}, 200);
        $("#beginTime").closest(".conf-begin-time-wrap").siblings(".message").text("");
    }).on("blur", function() {
        var time = $(this).val().replace(/[^0-9]/g,'').slice(0, 2);
        time =  time === "" || time - 0 > 59 ? "00" : time;
        $(this).val(time.length === 1 ? "0" + time : time);
    });


    hourList.on("click", "li", function() {
        $(this).addClass("active").siblings(".active").removeClass("active");
        beginHours.val($(this).text());
    });

    minuteList.on("click", "li", function() {
        $(this).addClass("active").siblings(".active").removeClass("active");
        beginMinutes.val($(this).text());
    });

    $(document).click(function (e) {
        hourList.hide();
        minuteList.hide();
    });

    layui.use(["layer","laytpl"], function () {
        laytpl = layui.laytpl;
        if($("#zj-info .conf-user-item").length == 0){
            addItme($("#zj-info"));
        }
        if($("#user-info .conf-user-item").length == 0){
            addItme($("#user-info"));
        }
        //初始化国家码列表
        var obj = document.getElementsByClassName("code_list");
        for(var i=0;i<obj.length;i++){
            getCountryCode(i,false);
        }
    });

    if($("#zj-info .conf-user-item").length == 1){
        $("#zj-info").find(".reduce-btn").hide();
        $("#zj-info").find(".add-more").show();
    }
    if($("#zj-info .conf-user-item").length > 1){
        $("#zj-info").find(".reduce-btn").show();
        $("#zj-info").find(".add-more").hide();
        if($("#zj-info .conf-user-item").length <= 2){
            $("#zj-info").find(".add-more").last();
        }
    }
    if($("#user-info .conf-user-item").length == 1){
        $("#user-info").find(".reduce-btn").hide();
        $("#user-info").find(".add-more").show();
    }
    if($("#user-info .conf-user-item").length > 1){
        $("#user-info").find(".reduce-btn").show();
        $("#user-info").find(".add-more").hide();
        if($("#user-info .conf-user-item").length < 10){
            $("#user-info").find(".add-more").last().show();
        }
    }
});
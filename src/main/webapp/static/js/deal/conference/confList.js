;(function () {
    var laytpl;
    var conference = {
        layer: null,
        dateThink: [],
        currentPage: 1,
        initD: 0,
        needRefreshConfList: true,
        refreshConfListTimer: null,
        refreshConfListFrequency: 3000,
        commonController: {
            init: function () {
                this.bindDomEvent();
            },
            bindDomEvent: function () {
                var self = this;
                $(".tab-wrap").on('click.menuActiveChange', 'li', function (event) {
                    event.preventDefault();
                    $(this).addClass('current').siblings('li').removeClass('current');
                });

                $(".top-user").on('click', function (event) {
                    event.preventDefault();
                    /* Act on the event */
                    if (!$(".menus").is(":hidden")) {
                        $(".menus").show();
                        $(this).find('.arrow').addClass('show');
                    } else {
                        $(".menus").hide();
                        $(this).find('.arrow').removeClass('show');
                    }
                });

                $(".add-conf").on("click", function () {
                    window.location.href = BASE_PATH + "/create/createConference";
                });

                $("#modify-password").on("click", function () {
                    window.location.href = BASE_PATH + "/login/modifyPassword";
                });

                $("#serchAccess").on("click", function () {
                    $.ajax({
                        type: 'GET',
                        url: BASE_PATH + "/conf/getConfAccessNum",
                        success: function (data) {
                        	conference.layer.open({
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

                $("#logout").on("click", function () {
                    window.location.href = BASE_PATH + "/login/logout";
                });

                // 查看录音
                $(".content-main").on('click', '.record', function (event) {
                    event.stopPropagation();

                    if ($(this).siblings('.td-tips').is(':hidden')) {
                        $(this).siblings('.td-tips').show();
                        $(this).closest('tr').siblings().find('.td-tips').hide();
                        conference.confLiveController.needRefreshConfList = false;
                    } else {
                        $(this).siblings('.td-tips').hide();
                        conference.confLiveController.needRefreshConfList = true;
                    }
                }).on("click",".td-tips",function (e) {
                    e.stopPropagation();
                }).on("click",".td-email",function (e) {
                    e.stopPropagation();
                }).on('click', ".td-send-btn", function (e) {
                    e.stopPropagation();
                    var confId = e.currentTarget.id;
                    var pg = $(this).siblings('.td-email').val();

                    if (pg == "") {
                        conference.layer.msg('请输入邮箱地址！');
                        $(this).siblings('.td-email').focus();
                    } else if (!is_email(pg)) {
                        conference.layer.msg('请输入正确的邮箱地址！');
                        $(this).siblings('.td-email').focus();
                    } else {
                        // 调用后台接口，发送录音文件
                        self.downRecord(confId, pg, this);
                    }
                }).on("click", ".td-edit-btn", function (e) {
                    e.stopPropagation();
                    var $tr = $(this).closest("tr");
                    self.editConf($tr.attr("data-conf-id"));
                }).on("click", ".td-del-btn", function (e) {
                    e.stopPropagation();
                    var $tr = $(this).closest("tr");
                    conference.confLiveController.needRefreshConfList = false;
                    self.deleteConf($tr.attr("data-conf-id"), $tr);
                }).on("click", ".download-report", function (e) {
                    e.stopPropagation();
                    var confId = $(this).closest("tr").attr("data-conf-id");
                    self.downReport(confId);
                }).on("click", ".pub-table tr", function () {
                    var $tr = $(this);
                    if ($tr.attr("data-conf-id")) {
                        self.showMon($tr.attr("data-conf-id"), $tr.attr("data-conf-status"));
                    }
                });

                $(document).on('click', function (event) {
                    event.preventDefault();
                    if (!$(event.target).is('.td-tips')
                        && !$(event.target).is('.record')
                        && !$(event.target).is('.td-send-btn')
                        && !$(event.target).is('.td-email')
                        && !$(event.target).is('.send-record p')
                        && !$(event.target).is('.send-record')
                        && !$(event.target).is('.td-input-block')) {
                            $('.td-tips').hide();
                            if ($(".content-confLive-result").is(":visible")) {
                                conference.confLiveController.needRefreshConfList = true;
                            }
                    }
                });

                $(".content-main").on("mouseover", "tr", function () {
                    $(this).addClass('hover');
                    $(this).find('.td-control').show();
                    conference.confLiveController.needRefreshConfList = false;
                }).on("mouseout", "tr", function () {
                    $(this).removeClass('hover');
                    $(this).find('.td-control').hide();
                    if ($(".content-confLive-result").is(":visible")) {
                        conference.confLiveController.needRefreshConfList = true;
                    }
                });

                var $contentMain = $(".conflist-container").find(".content-main");
                $contentMain.css("height", document.documentElement.clientHeight - $contentMain.offset().top - $(".footer").height() - 30);
                $(window).on("resize", function () {
                    $contentMain.css("height", document.documentElement.clientHeight - $contentMain.offset().top - $(".footer").height() - 30);
                });
            },
            deleteConf: function (confId, $tr) {
                var url = BASE_PATH + "/create/deleteConference/" + confId;
                conference.layer.open({
                    type: 0,
                    title: false,
                    closeBtn: 2,
                    id: "del-item",
                    resize: false,
                    skin: 'del-demo-class',
                    area: ['360px', '200px'],
                    shadeClose: true, // 开启遮罩关闭
                    content: '<p class="close-text">确定要取消这场会议吗？</p>',
                    btn: ['取消会议', '暂不取消'],
                    yes: function (index, layero) {
                        // 按钮【按钮一】的回调
                        conference.layer.close(index);
                        $.ajax({
                            type: 'GET',
                            url: url,
                            success: function (data) {
                                if (data.success == 'true') {
                                    conference.layer.msg("删除会议成功", {
                                        time: 3000
                                    });

                                    // 删除成功后，界面移除该会议，移除的时候要特殊处理一下
                                    var $confLiveContainer = $(".content-confLive-result");
                                    if ($confLiveContainer.is(":visible")) {
                                        // 如果只有一条数据，删除成功后，显示无数据
                                        if ($confLiveContainer.find(".conf-result-table-content").find("tr").length === 1) {
                                            $confLiveContainer.find(".conf-result-table-content").empty();
                                            $confLiveContainer.find(".task-list").hide();
                                            $confLiveContainer.find(".no-task").show();
                                            return;
                                        }

                                        // 如果当前数据删除成功后，当天没有数据了，则删除掉当天
                                        var $confDayBox = $tr.closest(".conf-day-box");
                                        if ($confDayBox.find("tr").length === 1) {
                                            return $confDayBox.remove();
                                        }

                                        $tr.remove();
                                        return;
                                    }


                                    var $confSearchContainer = $(".content-search-result");
                                    if ($confSearchContainer.is(":visible")) {
                                        if ($confSearchContainer.find("tr").length === 1) {
                                            $confSearchContainer.find(".conf-result-table-content").empty();
                                            $confSearchContainer.find(".task-list").hide();
                                            $confSearchContainer.find(".no-task").show();
                                            return;
                                        }

                                        $tr.remove();
                                    }
                                }

                                if (data.success == 'false') {
                                    conference.layer.msg("取消会议失败", {
                                        time: 3000
                                    });
                                }

                                conference.confLiveController.needRefreshConfList = true;
                            }
                        });
                    },
                    btn2: function (index, layero) {
                        conference.layer.close(index);
                        conference.confLiveController.needRefreshConfList = true;
                    }
                });
            },
            downReport: function (confId) {
                window.location.href = BASE_PATH + "/conf/report/" + confId;
            },
            editConf: function (confId) {
                var url = BASE_PATH + "/create/modifyConference/" + confId;
                $.ajax({
                    type: 'GET',
                    url: url,
                    success: function (data) {
                        window.location.href = url;
                    }
                });
            },
            downRecord: function (confId, email, thisid) {
                var
                    self = this,
                    url = BASE_PATH + "/conf/sendRecord?confId=" + confId + "&email=" + email;

                $.ajax({
                    type: 'GET',
                    url: url,
                    success: function (data) {
                        if (data == "true") {
                            $(thisid).next('.checked').fadeToggle("slow").fadeToggle("slow",function(){
                                $(thisid).prev('.td-email').val("");
                            });
                            $.getJSON(BASE_PATH + "/create/getConferenceData/" + confId, function(json){
                                laytpl(sendRecordsTpl.innerHTML).render(json.radioSendRecord,function(html){
                                    $(thisid).closest('.td-tips').find(".radioSendRecords").replaceWith(html);
                                });
                                $(thisid).closest('.td-tips').fadeOut("slow");
                            });

                        }
                    }
                });
            },
            // 显示会议监控
            showMon: function (confId, status) {
                var self = this, url = BASE_PATH + "/conf/confMon/" + confId;

                conference.confLiveController.needRefreshConfList = false;
                conference.layer.open({
                    type: 2,
                    title: false,
                    closeBtn: 1,
                    skin: 'edit-demo-class',
                    area: ['720px', '420px'],
                    shadeClose: true, // 开启遮罩关闭
                    content: [url, 'yes'],
                    end: function () {
                        conference.confLiveController.needRefreshConfList = true;
                    }
                });
            },
            getConfCount: function (callback) {
                $.ajax({
                    type: 'POST',
                    url: BASE_PATH + "/conf/getOnlineAndEndConfNum",
                    success: function (data) {
                        data = data ? JSON.parse(data) : "";
                        var count = Object.prototype.toString.call(data) === "[object Array]" ? data[0] : {onlineNum: 0, endNum: 0};

                        $(".main-tag-box .tab-wrap").find(".first .count").text(count.onlineNum);
                        $(".main-tag-box .tab-wrap").find(".last .count").text(count.endNum);
                        //会议总数小于30,不显示滚动条
                        if(count.onlineNum < 30){
                            $(".content-confLive-result").find(".conf-result-table-content").attr("style","");
                        }
                        if (typeof callback === "function") {
                            callback();
                        }
                    },
                    error: function (err) {
                        console.log("getConfCount error, err: ", err, "interrupt callback!");
                    }
                });
            }
        },
        confLiveController: {
            needRefreshConfList: true,
            refreshAjax:null,//滚动数据是需要中断定时刷新的请求,所以在此处声明变量
            isLoading: false,
            currentPage: 1,
            init: function () {
                this.bindDomEvent();
                this.setRefreshConfsLive();
            },
            bindDomEvent: function () {
                var self = this;

                $(".tab-wrap").on('click.confLiveShow', 'li', function (event) {
                    event.preventDefault();
                    if ($(this).hasClass("first")) {
                        $(".content-confLive-result").show().find(".conf-result-table-content").empty();
                        $(".content-confOver-result").hide();
                        $(".content-search-result").hide();
                        this.currentPage = 1;
                        conference.confLiveController.needRefreshConfList = true;
                        conference.confLiveController.setRefreshConfsLive();
                    }
                });

                $(".content-confLive-result").find(".conf-result-table-content").on("scroll", function () {
                    var $confDayList = $(this).find(".conf-day-box");
                    var headHeight = $(".content-confLive-result").find(".conf-result-table-head")[0].offsetHeight;
                    var scrollTop = this.scrollTop;
                    var confDate = "";
                    //滚动数据时,停止定时刷新,并中断现有的请求
                    self.needRefreshConfList = false;
                    self.refreshAjax.abort();


                    $confDayList.each(function () {
                        if (scrollTop - 60 >= this.offsetTop - headHeight) {
                            confDate = $(this).attr("data-time");
                        }
                    });

                    if (confDate) {
                        var startDate = confDate.split("-");
                        $(".content-confLive-result").find(".conf-result-table-head").find(".conf-date").text(startDate[1] + "月" + startDate[2] + "日");
                    }

                    if (this.scrollTop + this.clientHeight < this.scrollHeight - 20 && this.scrollTop > 20) {
                        return;
                    }

                    if (!self.isLoading) {
                        if(self.currentPage >1 && this.scrollTop < 20){
                            console.log("previous");
                            self.currentPage -= 1;
                            self.needRefreshConfList = false;
                            self.getConfsLive();
                            return;
                        }
                        if(this.scrollTop + this.clientHeight > this.scrollHeight - 20){
                            console.log("next");
                            self.currentPage += 1;
                            self.needRefreshConfList = false;
                            self.getConfsLive();
                            return;
                        }
                    }
                });
            },
            setRefreshConfsLive: function () {
                var self = this;

                if (this.setRefreshConfsLive.timer) {
                    window.clearTimeout(this.setRefreshConfsLive.timer);
                    this.setRefreshConfsLive.timer = null;
                }

                this.setRefreshConfsLive.timer = setTimeout(function () {
                    self.setRefreshConfsLive();
                }, 5000);

                if (this.needRefreshConfList) {
                    conference.commonController.getConfCount(function () {
                        conference.confLiveController.getConfsLive();
                    });
                }
            },
            getConfsLive: function (callback) {
                if (this.isLoading) {
                    return;
                }

                var self = this;
                this.isLoading = true;
                console.log("loding........");
                if(!this.needRefreshConfList){
                    this.showLoading();
                }
                var _ajax = $.ajax({
                    type: 'POST',
                    url: BASE_PATH + "/conf/unend/page/" + self.currentPage,
                    success: function (data) {
                        var data = data ? data : {confLive: []};
                        self.beforeRenderConfsLive(data);
                        self.renderConfList(data);
                        if (typeof callback === "function") {
                            callback();
                        }
                    },
                    error: function (err) {
                        console.log("getConfsLive error, err: ", err);
                        self.isLoading = false;
                        if (typeof callback === "function") {
                            callback();
                        }
                    }
                });
                if(self.needRefreshConfList){
                    self.refreshAjax = _ajax;
                }
            },
            showLoading: function () {
                if ($(".content-confLive-result").find(".task-loading").length === 0) {
                    $(".content-confLive-result").find(".conf-result-table-head").append("<div class='task-loading' style='z-index: 9999;position: relative;'><i class='fa fa-spinner' aria-hidden='true'></i>第"
                        + this.currentPage +"页加载中……</div>");
                }
            },
            beforeRenderConfsLive: function (data) {
                var self = this;
                var $taskLoading = $(".content-confLive-result").find(".task-loading");
                if (!data.confLive || data.confLive.length === 0) {
                    $taskLoading.html("没有更多了");
                    setTimeout(function () {
                        $taskLoading.remove();
                        self.isLoading = false;
                    }, 2000);
                } else {
                    $taskLoading.remove();
                    this.isLoading = false;
                }
            },
            renderConfList: function (data) {
                var confLive = data.confLive || [];
                var $container = $(".content-confLive-result");
                if ($(this).hasClass("last") && this.currentPage === 1) {
                    $container.show();
                    $(".content-confOver-result").hide();
                    $(".content-search-result").hide();
                }

                if (this.currentPage === 1 && confLive.length === 0) {
                    $container.find(".no-task").show().end().find(".task-list").hide();
                    return
                }

                if (this.currentPage === 1) {
                    $container.find(".conf-result-table-head").css("visibility", "visible");
                    $container.find(".no-task").hide().end().find(".task-list").show();
                }
                //分页加载时如果没有新数据,页码要回退减一
                if (confLive.length === 0) {
                    if(!this.needRefreshConfList){
                        this.currentPage -= 1;
                    }
                    this.fillEmptyTr();
                    return;
                }

                var result = {};
                var startDate = "";
                confLive.forEach(function (item, index) {
                    result[item.confTime] = result[item.confTime] || [];

                    var html = laytpl(confLiveTpl.innerHTML).render(item);
                    result[item.confTime].push(html);

                    if (index === 0) {
                        startDate = item.confTime;
                    }
                });
                $container.find(".conf-result-table-content").empty();

                for (var k in result) {
                    var confDate = k.split("-");
                    var html = "<div class='conf-day-box' data-time='" + k + "'>"
                            + "<div class='conf-day-head'>" + confDate[1] + "月" + confDate[2] + "日" + "</div>"
                            + "<div class='conf-day-content'><table class='pub-table'>" + result[k].join("") + "</table></div>"
                            + "</div>";

                    $container.find(".conf-result-table-content").append(html);
                }

                if (this.currentPage === 1) {
                    var startDate = startDate.split("-");
                    $container.find(".conf-result-table-head").find(".conf-date").text(startDate[1] + "月" + startDate[2] + "日");
                }

                //$container.find("table").last().css("margin-bottom", "0");
                this.fillEmptyTr();
                //加载完设置滚动条位置
                if(this.currentPage > 1){
                    $container.find("table").last().css("margin-top", "20px");
                    $(".content-confLive-result").find(".conf-result-table-content").scrollTop(20);
                }
            },
            fillEmptyTr: function () {

                var $container = $(".content-confLive-result");
                var length = Math.floor(($container.find(".task-list").height() - ($container.find("tr:last").offset().top + 40 - $container.offset().top)) / 40);
                if (length < 1) {
                    return;
                }

                var html = "";
                for (var i = 0; i < length; i++) {
                    html += "<tr class='empty-tr'><td></td><td></td><td></td><td></td><td></td></tr>";
                }
                //让最后一页滚动条可以上滚触发上一页加载
                if(this.currentPage > 1){
                    html += "<tr class='empty-tr'><tdcolspan='10'></td></tr>";
                }

                $container.find("tbody:last").append(html);
            }
        },
        confOverController: {
            currentPage: 1,
            isLoading: false,
            init: function () {
                this.bindDomEvent();
            },
            bindDomEvent: function () {
                var self = this;

                $(".tab-wrap").on('click.confOverShow', 'li', function (event) {
                    event.preventDefault();
                    if ($(this).hasClass("last")) {
                        conference.confLiveController.needRefreshConfList = false;
                        $(".content-confLive-result").hide();
                        $(".content-confOver-result").find(".conf-result-table-content").empty().end().show();
                        $(".content-search-result").hide();
                        self.currentPage = 1;
                        conference.commonController.getConfCount(function () {
                            self.getConfsOver();
                        });
                    }
                });

                $(".content-confOver-result").find(".conf-result-table-content").on("scroll", function () {
                    var $confDayList = $(this).find(".conf-day-box");
                    var headHeight = $(".content-confOver-result").find(".conf-result-table-head")[0].offsetHeight;
                    var scrollTop = this.scrollTop;
                    var confDate = "";

                    $confDayList.each(function () {
                        if (scrollTop - 60 >= this.offsetTop - headHeight) {
                            confDate = $(this).attr("data-time");
                        }
                    });

                    if (confDate) {
                        var startDate = confDate.split("-");
                        $(".content-confOver-result").find(".conf-result-table-head").find(".conf-date").text(startDate[1] + "月" + startDate[2] + "日");
                    }

                    if (this.scrollTop + this.clientHeight < this.scrollHeight - 80 || this.scrollTop + this.clientHeight == this.scrollHeight) {
                        return;
                    }

                    if (!self.isLoading) {
                        self.currentPage += 1;
                        self.getConfsOver();
                    }
                });
            },
            getConfsOver: function () {
                if (this.isLoading) {
                    return;
                }

                var self = this;
                this.isLoading = true;
                this.showLoading();
                $.ajax({
                    type: 'POST',
                    url: BASE_PATH + "/conf/end/page/"+ self.currentPage,
                    data: {},
                    success: function (data) {
                        self.beforeRenderConfsOver(data);
                        self.renderConfList(data);
                    },
                    error: function (err) {
                        console.log("getConfsOver error, err: ", err);
                        self.isLoading = false;
                    }
                });
            },
            showLoading: function () {
                if ($(".content-confOver-result").find(".task-loading").length === 0) {
                    $(".content-confOver-result").find(".conf-result-table-head").append("<div class='task-loading' style='z-index: 9999;position: relative;'><i class='fa fa-spinner' aria-hidden='true'></i>第"
                        + this.currentPage +"页加载中……</div>");
                }
            },
            beforeRenderConfsOver: function (data) {
                var self = this;
                var $taskLoading = $(".content-confOver-result").find(".task-loading");
                if (!data || data.length === 0) {
                    $taskLoading.html("没有更多了");
                    setTimeout(function () {
                        $taskLoading.remove();
                        self.isLoading = false;
                    }, 2000);
                } else {
                    $taskLoading.remove();
                    this.isLoading = false;
                }
            },
            renderConfList: function (data) {
                var confOver = data || [];
                var $container = $(".content-confOver-result");

                if ($(this).hasClass("last") && this.currentPage === 1) {
                    $container.show();
                    $(".content-confLive-result").hide();
                    $(".content-search-result").hide();
                }

                if (this.currentPage === 1 && confOver.length === 0) {
                    $container.find(".no-task").show().end().find(".task-list").hide();
                    return
                }

                if (this.currentPage === 1) {
                    $container.find(".conf-result-table-head").css("visibility", "visible");
                    $container.find(".no-task").hide().end().find(".task-list").show();
                }

                if (confOver.length === 0) {
                    this.currentPage -= 1;
                    return;
                }

                var result = {};
                var startDate = "";
                var self = this;
                confOver.forEach(function (item, index) {
                    var beginDate = item.beginTime.substr(0,10);
                    result[beginDate] = result[beginDate] || [];

                    var html = self.getConfOverHtml(item);
                    result[beginDate].push(html);

                    if (index === 0) {
                        startDate = beginDate;
                    }
                });

                for (var k in result) {
                    var $box = $container.find("div[data-time=" + k + "]");
                    if ($box.length) {
                        $box.find("table").append(result[k].join(""));
                        continue;
                    }

                    var confDate = k.split("-");
                    var html = "<div class='conf-day-box' data-time='" + k + "'>"
                            + "<div class='conf-day-head'>" + confDate[1] + "月" + confDate[2] + "日" + "</div>"
                            + "<div class='conf-day-content'><table class='pub-table'>" + result[k].join("") + "</table></div>"
                            + "</div>"

                    $container.find(".conf-result-table-content").append(html);
                }

                if (this.currentPage === 1) {
                    var startDate = startDate.split("-");
                    $container.find(".conf-result-table-head").find(".conf-date").text(startDate[1] + "月" + startDate[2] + "日");
                }

                $container.find("table").last().css("margin-bottom", "0");
            },
            getConfOverHtml: function (confData, keywords) {
                var havRadio = false;
                var havReport = false;
                //已经结束的会议不一定有录音和报告
                if(confData){
                    if(confData.confRadios && confData.confRadios[0] && confData.confRadios[0].fileName){
                        havRadio = true;
                    }
                    if(confData.confReports && confData.confReports[0] && confData.confReports[0].reportName){
                        havReport = true;
                    }
                }
                var sendRecords = laytpl(sendRecordsTpl.innerHTML).render(confData.radioSendRecord);
                return [
                    '<tr data-conf-id="' + confData.confId + '" data-conf-status="' + confData.confStatus + '">',
                    '<td width="25%">' + conference.confSearchController.getSearchConfStartTime(confData.beginTime, confData.endTime) + '</td>',
                    '<td colspan="2"><span class="item-title">'
                    + (keywords ? confData.confName.replace(new RegExp(keywords, "g"), "<span class='important'>" + keywords + "</span>") : confData.confName) + '</span></td>',
                    '<td width="15%"><div class="record-box">'
                    + (havRadio ? '<a class="icons icons-luzhi record"> </a>' : '') + '',
                    '<div class="td-tips" style="height:auto;max-height: 200px;"><form class="send-record"><p>输入邮箱地址发送会议录音</p><div class="td-input-block"><input type="email" id="recordMail" class="td-email" placeholder="邮箱地址"><button type="button" id="'
                    + confData.confId
                    + '" class="td-send-btn">发送</button><i class="checked" style="left:155px;display:none;"/></div></form>'
                    + sendRecords
                    +'</div><span class="tips-text">录音已发送</span></div></td>',
                    '<td width="15%">'
                    + (havReport ? '<a class="icons icons-word word download-report"></a>'
                        : '') + '</td></tr>'].join("");
            }
        },
        confSearchController: {
            currentPage: 1,
            searchAjax:null,
            isSearching: false,
            init: function () {
                this.currentPage = 1;
                this.isSearching = false;
                this.bindDomEvent();
            },
            bindDomEvent: function () {
                var self = this;
                $("#searchTaskKeywords").val();
                $(".user-info .search input").on("input propertychange", function () {
                    var params = {
                        keywords: $(this).val() === "输入主题、姓名、手机、邮箱搜索会议" ? "" : $(this).val(),
                        date: calendar.searchDate
                    };

                    if (params.keywords === "" && params.date === "" && !$(".content-search-result").is(":visible")) {
                        return;
                    }

                    if (params.keywords === "" && params.date === "") {
                        self.boforeRefreshConfList();
                        return;
                    }

                    if (self.beforeSearchTask()) {
                        self.searchTask(params)
                    }
                });

                $(".user-info .search .icon-search").on("click", function (event) {
                    var params = {
                        keywords: $("#searchTaskKeywords").val() === "输入主题、姓名、手机、邮箱搜索会议" ? "" : $("#searchTaskKeywords").val(),
                        date: calendar.searchDate
                    };

                    if (params.keywords === "" && params.date === "") {
                        return;
                    }

                    if (self.beforeSearchTask()) {
                        self.searchTask(params);
                    }

                    return false;
                });

                $(".user-info .search .icon-close").on("click", function (event) {
                    $("#searchTaskKeywords").val("");
                    var params = {
                        keywords: "",
                        date: calendar.searchDate
                    };

                    $(".user-info .search").find(".icon-close").addClass("hide");
                    if (params.keywords === "" && params.date === "") {
                        self.boforeRefreshConfList();
                        return;
                    }

                    if (self.beforeSearchTask()) {
                        self.searchTask(params);
                    }

                    return false;
                });


                $(".content-search-result").find(".conf-result-table-content").off("scroll").on("scroll", function () {
                    if (self.isSearching) {
                        return;
                    }

                    if (this.scrollTop + this.clientHeight < this.scrollHeight - 80 || this.scrollTop + this.clientHeight == this.scrollHeight) {
                        return;
                    }

                    if (self.beforeSearchTask(true)) {
                        self.currentPage += 1;
                        self.searchTask({
                            keywords: $("#searchTaskKeywords").val() === "输入主题、姓名、手机、邮箱搜索会议" ? "" : $("#searchTaskKeywords").val(),
                            date: calendar.searchDate
                        });
                    }
                });
            },
            beforeSearchTask: function (isScroll) {
                if (this.isSearching) {
                    this.searchAjax.abort();
                }

                conference.confLiveController.needRefreshConfList = false;

                if (!isScroll) {
                    this.currentPage = 1;
                }

                this.isSearching = true;
                return true;
            },
            boforeRefreshConfList: function () {
                $(".content-confLive-result").show();
                $(".content-confOver-result").hide();
                $(".content-search-result").hide();
                $(".task-search-title").hide();
                $(".user-info .search").find(".icon-close").hide();
                $(".tab-wrap").find(".first").addClass("current").end().find(".last").removeClass("current").end().show();
                conference.confLiveController.needRefreshConfList = true;
                conference.confLiveController.setRefreshConfsLive();
            },
            searchTask: function (params) {
                this.showLoadingSearch();
                var self = this;
                self.searchAjax = $.ajax({
                    type: 'POST',
                    url: BASE_PATH + "/conf/searchConf4Page",
                    data: {keyword: params.keywords, date: params.date, pageNum: self.currentPage},
                    success: function (data) {
                        self.isSearching = false;
                        // 搜搜完成后，如果已经切换到其他界面，则不渲染
                        if (!$(".content-search-result").is(":visible") && self.currentPage !== 1) {
                            return;
                        }

                        var result = data ? data : {recordCount: 0, recordList: [[{}]]};
                        self.beforeRenderSearchData(result, params);
                        self.renderSearchData(result, params);
                    },
                    error: function () {
                        self.isSearching = false;
                    }
                })
            },
            showLoadingSearch: function () {
                if ( $(".content-search-result").find(".task-loading").length === 0) {
                    $(".content-confLive-result").hide();
                    $(".content-confOver-result").hide();
                    $(".task-list-title").hide();
                    $(".task-search-title").show()
                    $(".content-search-result").show();
                    $(".content-search-result").find(".conf-result-table-head").append("<div class='task-loading' style='z-index: 9999;position: relative;'><i class='fa fa-spinner' aria-hidden='true'></i>第"
                        + this.currentPage +"页加载中……</div>");
                }
            },
            beforeRenderSearchData: function (data, params) {
                var keywords = params.keywords;
                var $taskLoading = $(".content-search-result").find(".task-loading");
                if (!data.recordList || data.recordList.length === 0){
                    $taskLoading.html("没有更多了");
                    setTimeout(function () {
                        $taskLoading.remove();
                    }, 2000);
                } else {
                    $taskLoading.remove();
                }

                $(".task-list-title").hide();
                $(".task-search-title").show().html("搜索结果：共<span class='important'>" + data.total + "</span>场" + (keywords ? "与关键词“<span class='important'>" + keywords + "</span>”相关的会议" : params.date ? "“<span class='important'>" + params.date + "</span>”的会议" : "会议" ));

                if (this.currentPage === 1) {
                    $(".content-search-result").show().find("tbody").empty();
                    $(".content-confLive-result").hide();
                    $(".content-confOver-result").hide();
                }

                if (keywords) {
                    $(".user-info .search").find(".icon-close").show();
                } else {
                    $(".user-info .search").find(".icon-close").hide();
                }
            },
            renderSearchData: function (data, params) {
                var keywords = params.keywords;
                var $container = $(".content-search-result");

                var confList = data.recordList;
                if (this.currentPage === 1 && confList.length === 0) {
                    return $container.find(".no-task").show().end().find(".task-list").hide();
                }
                if (this.currentPage === 1) {
                    $container.find(".no-task").hide().end().find(".task-list").show();
                }
                if (confList.length === 0) {
                    this.currentPage -= 1;
                }

                var self = this;
                if (confList.length) {
                    var html = "";
                    $.each(confList, function (index, confData) {
                        var template = '';
                        if (confData.confStatus == 2) {
                            template = conference.confOverController.getConfOverHtml(confData, keywords)
                        } else {
                            var tempHtml='', btnHtml = '';
                            if (confData.confStatus == 0 || confData.confStatus == 5) {//没有召开
                                btnHtml = '<span class="td-control"><span class="td-btn td-edit-btn">编辑</span><span class="td-btn td-del-btn del-task-btn">删除</span></span>';
                            }
                            if (confData.confStatus == 1) {//召开中
                                tempHtml = '<span class="status offline">（正在开会）</span><input id="confId" type="hidden" value="' + confData.confId + '" />';
                            }
                            if (confData.confStatus == 4) {//会中休息
                                tempHtml = '<span class="status offline">（会议休息）</span>';
                            }
                            template = [
                                '<tr data-conf-id="' + confData.confId + '" data-conf-status="' + confData.confStatus + '">',
                                '<td width="30%">' + self.getSearchConfStartTime(confData.beginTime, confData.endTime) + tempHtml + '</td>',
                                '<td colspan="2"><span class="item-title">'
                                + (keywords ? confData.confName.replace(new RegExp(keywords, "g"), "<span class='important'>" + keywords + "</span>") : confData.confName) + '</span></td>',
                                '<td width="15%"></td>',
                                '<td width="15%">' + btnHtml + '</td>',
                                '</tr>'
                            ].join("");
                        }
                        html += template;
                    });

                    $container.find("tbody").append(html);
                }
                this.fillEmptyTr();
                },
            //搜索结果行的会议时间处理
            getSearchConfStartTime: function (beginTime, endTime) {
                    var today = new Date();
                    var dates = beginTime.substr(0,10).split("-");
                    var time = beginTime.substr(11,5) + "-" + endTime.substr(11,5);
                    if (today.getFullYear() === dates[0] - 0) {
                        return "<span class='date'>" + dates[1] + "月" + dates[2] + "日</span><span class='time'>" + time + "</span>";
                    }

                    return "<span class='date'>" + dates[0] + "年" + dates[1] + "月" + dates[2] + "日</span><span class='time'>" + time + "</span>";
                },
            fillEmptyTr: function () {
                    if (this.currentPage > 1) {
                        return;
                    }

                    var $container = $(".content-search-result");
                    var length = Math.floor(($container.find(".task-list").height() - ($container.find("tr:last").offset().top + 40 - $container.offset().top)) / 40);
                    if (length < 1) {
                        return;
                    }

                    var html = "";
                    for (var i = 0; i < length; i++) {
                        html += "<tr><td colspan='10'></td></tr>";
                    }

                    $container.find("tbody:last").append(html);
                }
            }
        };

    var calendar = {
        laydate: null,
        dateThink: [],
        initD: 0,
        choosedTime: "",
        searchDate: "",
        init: function () {
            this.choosedTime = this.getNow();
            if (self.laydate.now()) {
                $("#date-time").val('今天');
            }

            this.bindEvent();
        },
        loadTodo: function (callback) {
            var self = this, url = BASE_PATH + "/conf/confTodo";

            $.ajax({
                type: 'POST',
                url: url,
                dataType: "json",
                async: false,
                success: function (data) {
                    self.dateThink = data === "" ? [] : JSON.parse(data);
                    if (typeof callback === "function") {
                        callback();
                    }
                }
            });
        },
        backLog: function () {
            var dateThink = this.dateThink;
            var today = new Date();

            today = new Date(today.getFullYear(), today.getMonth(), today
                .getDate()) / 1000;
            // 添加待办事项
            $(".laydate_table > tbody > tr > td").each(function (index, el) {
                var dateY = $(el).attr("y");
                var dateM = $(el).attr("m");
                dateM = (dateM < 10 ? "0" + dateM : dateM);
                var dateD = $(el).attr("d");
                dateD = (dateD < 10 ? "0" + dateD : dateD);
                var dateMain = dateY + "-" + dateM + "-" + dateD;

                var arr = dateThink.filter(function (item) {
                    return item.dates === dateMain;
                });

                if (arr.length > 0) {
                    var date = new Date(dateY, dateM - 1, dateD) / 1000;
                    $(el).addClass(date < today ? "backlog-done" : "backlog");
                } else {
                    $(el).removeClass('backlog, backlog-done');
                }
            });
        },
        bindDateInputEvent: function () {
            var self = this;
            $("#laydate_y").off("change").on("change", function () {
                self.loadTodo(function () {
                    self.backLog();
                });
            });

            $("#laydate_m").off("change").on("change", function () {
                self.loadTodo(function () {
                    self.backLog();
                });
            });
        },
        bindEvent: function () {
            var self = this;
            // 选择日期时间
            $("#date-time").on('click', function (event) {
                event.preventDefault();
                self.laydate({
                    elem: $("#date-time")[0],
                    event: 'click', // 触发事件
                    format: 'YYYY-MM-DD',
                    istoday: false,
                    isclear: false,
                    issure: false,
                    choose: function (e) { // 选择好日期的回调
                        self.isNow(e);
                        self.searchDate = e;
                        var keywords = $("#searchTaskKeywords").val() === "输入主题、姓名、手机、邮箱搜索会议" ? "" : $("#searchTaskKeywords").val();
                        $("#date-time").html("");

                        $(".calendar .show_date").css("display", "inline-block");
                        $("#show_date").html(laydate.now(self.initD, "YYYY年MM月DD日"));
                        if (conference.confSearchController.beforeSearchTask()) {
                            conference.confSearchController.searchTask({
                                keywords: keywords,
                                date: self.searchDate
                            });
                        }
                    }
                });

                $("#date-time").html("");
                self.loadTodo(function () {
                    self.backLog();
                });

                self.bindDateInputEvent();
            });

            $(".calendar .close").unbind("click").click(function () {
                $(".calendar .show_date").hide();
                self.choosedTime = self.getNow();
                ;
                self.searchDate = "";
                self.initD = 0;
                $("#show_date").html("");

                var keywords = $("#searchTaskKeywords").val() === "输入主题、姓名、手机、邮箱搜索会议" ? "" : $("#searchTaskKeywords").val();
                if (keywords === "") {
                    conference.confSearchController.boforeRefreshConfList();
                    return;
                }

                if (conference.confSearchController.beforeSearchTask()) {
                    conference.confSearchController.searchTask({
                        keywords: keywords,
                        date: self.searchDate
                    });
                }

            });
        },
        isNow: function (dateTime) {
            var now = this.getNow();
            this.choosedTime = dateTime;
            // 取得与当前日期的天数差值
            this.initD = this.DateDiff(dateTime, now);

        },
        DateDiff: function (sDate1, sDate2) { // sDate1和sDate2是2006-12-18格式
            var aDate, iDays, newDate1, newDate2;
            aDate = sDate1.split("-");
            newDate1 = new Date(aDate[0], aDate[1] - 1, aDate[2]);
            aDate = sDate2.split("-");
            newDate2 = new Date(aDate[0], aDate[1] - 1, aDate[2]);

            iDays = (newDate1 - newDate2) / 1000 / 60 / 60 / 24; // 把相差的毫秒数转换为天数
            return iDays;
        },
        getNow: function () {
            if (this.getNow.time) {
                return this.getNow.time;
            }

            var date = new Date;
            var year = date.getFullYear();
            var day = date.getDate();
            var month = date.getMonth() + 1;
            month = (month < 10 ? "0" + month : month);
            day = (day < 10 ? "0" + day : day);
            this.getNow.time = (year.toString() + '-' + month.toString() + "-" + day);

            return this.getNow.time;
        }
    };

    function checkScroll(ele) {
        if (ele.scrollTop + ele.clientHeight >= ele.scrollHeight) {
            return true;
        } else {
            return false;
        }
    }

    $(function () {
        layui.use(["layer", "laydate","laytpl"], function () {
            laytpl = layui.laytpl;
            conference.layer = layui.layer;
            conference.commonController.init();
            conference.confLiveController.init();
            conference.confOverController.init();
            conference.confSearchController.init();

            calendar.laydate = layui.laydate;
            calendar.init();
        });

        $("input, textarea").attr("spellcheck", false);
    });
})();
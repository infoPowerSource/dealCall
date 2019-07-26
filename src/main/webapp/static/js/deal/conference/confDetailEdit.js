$(function() {
	//设置刷新
	var tab = 1;
	// 重复提交
	var Stch = false
	
	//callingStatus
	var callingStatus = {
			0:{en:"online",zh:"在线"},
			1:{en:"offline",zh:"离线"},
			2:{en:"connect",zh:"呼叫中"},
	}
	
	// 刷新
	window.getChangedData = function() {
		if (getChangedData.timer) {
			clearTimeout(getChangedData.timer);
			getChangedData.timer = null;
		}

		getConfUsers();
		getChangedData.timer = setTimeout(getChangedData, 5000);
	};
	
	$("input, textarea").attr("spellcheck", false);
	
	//隐藏外呼其他人那一行
	document.getElementById("showCallOther").style.display = "none";
	
	window.getConfUsers = function() {
		$.ajax({
			type : 'POST',
			url : BASE_PATH + "/conf/confMonRefresh",
			data: {
				confId: $("#confId").val()
			},
			success : function(data) {
				if (!data || !data.partyInfo) {
					return;
				}
				
				var html = "";
				
				$.each(data.partyInfo, function(index, party) {
					html += [
						'<tr class="user-item"  data-partyPhone="' + party.partyPhone + '" data-partyName="' + party.partyName + '" data-partyRole="' + party.partyRole + '" data-confBillingcode="' + data.conference.confBillingcode + '">',
						'<td width="15%">' + (party.partyRole == 0 ? '顾问' : party.partyRole == 1 ? '咨询客户' : '') + '</td>',
						'<td width="25%">' + party.partyName + '</td>',
						'<td width="20%">' + party.partyPhone + '</td>',
						(data.conference.confStatus != 1 && data.conference.confStatus != 4 && data.conference.confStatus != 5 ? '<td width="12%"></td>' : '<td width="12%"><span class="' + (callingStatus[party.partyStatus].en) + '">' + (callingStatus[party.partyStatus].zh) + '</span></td>'),
						((data.conference.confStatus != 1 && data.conference.confStatus != 4 && data.conference.confStatus != 5) || party.partyPhone =="" ? '<td width="20%"></td>' : '<td width="20%">' + (party.partyStatus==0 || party.partyStatus==2? '' : '<span class="td-control-call call"><span class="td-btn td-call-btn call">呼叫</span></span>') + '</td>')
					].join("");
				});

				$(".pub-table").css("visibility", "visible").find("tbody").find(".user-item").remove().end().prepend(html);
				if (data.conference.confStatus==1 || data.conference.confStatus==4 || data.conference.confStatus==5) {
					$(".call-other-btn-wrap").show();
				} else {
					$(".call-other-btn-wrap").hide();
				}
				
				fillEmptyTr();
			},
			error: function(err) {
				console.log("getConfUsers error, err: ",  err);
			}
		});
	}

	function bindEvt() {
		$(".pub-table").on("click.call", ".td-call-btn", function() {
			var $userItem = $(this).closest(".user-item");
			callout($userItem.attr("data-partyPhone"), $userItem.attr("data-partyName"), $userItem.attr("data-partyRole"), $userItem.attr("data-confBillingcode"));
		});
	}

	bindEvt();
	
	function fillEmptyTr() {
		if ($(".call-other-btn-wrap").is(":visible")) {
			return;
		}
		
		var $container = $(".pub-table");
		var length = Math.floor(($container.find("tbody").height() - ($container.find("tr:visible:last").offset().top + 40 - $container.find("tbody").offset().top))/40);
		
		if (length < 1) {
			return;
		}
		
		var html = "";
		for (var i = 0; i < length; i++) {
			html += "<tr class='user-item'><td></td><td></td><td></td><td></td><td></td></tr>";
		}
		
		$(html).insertBefore($container.find("#showCallOther"));
	}
	
	fillEmptyTr();
	// 间隔刷新
	getChangedData();

	function timeout() {
		setTimeout(getChangedData, 5000);
	}
	// 外呼
	function callout(phone, name, role, billingCode) {
		var confId = $("#confId").val(); // 会议Id
		if (Stch == true) {
			layer.msg("外呼正在处理，请稍候", {time : 3000});
			//$("#messagealert").text("外呼正在处理，请稍候");
			return;
		}
		$("#"+phone+"").text("外呼中");
		Stch = true;
		// 防止刷新
		tab = 0;
		var url = BASE_PATH + "/conf/callout/";
		$.ajax({
			type : 'POST',
			url : url,
			// 提交的数据
			data : {
				phone : phone,
				name : name,
				role : role,
				billingCode : billingCode,
				confId : confId
			},
			cache : false,
			success : function(data) {
				if (data == 0) {
					Stch = false;
					tab = 1
					getChangedData();
				}
				if (data == 2) {
					layer.msg("正在连接，请稍候", {time : 3000});
					tab = 1;
					Stch = false;
					timeout();
				}
				if (data == 1) {
					layer.msg('外呼失败，请联系管理员', { time: 3000 });
					tab = 1
					Stch = false;
					getChangedData();
				}
			},
			error : function(error) {
				layer.msg('外呼失败，请联系管理员', { time: 3000 });
				tab = 1
				Stch = false;
				getChangedData();
			}
		});

	}
	
	// 外呼其他号码
	function callother() {
		document.getElementById("call-send").style.display = "none";
		document.getElementById("calling").style.display = "";
		$("#messagetext").text("");
		// 防止刷新
		tab = 0;
		var role = $('#task-user').prop("name");
		var billingCode = $("#confCode").val(); // 会议bc
		var confId = $("#confId").val(); // 会议Id
		var phone = $("#otherPhone").val(); // 外呼号码
		var country = $("#country").val(); // 国家码
		var area = $("#area").val(); // 区号
		var name = $("#otherName").val(); // 姓名
		if (name == "" || name == "姓名") {
			//如果姓名为空，保存显示为电话号码
			name = phone;
		}
		if (area == "" || area == "区号") {
			area = " ";
		}
		var url = BASE_PATH + "/conf/calloutOther/";
		$.ajax({
			type : "POST",
			// 提交的数据
			data : {
				phone : phone,
				name : name,
				role : role,
				billingCode : billingCode,
				area : area,
				country : country,
				confId : confId
			},
			url : url,
			cache : false,
			success : function(data) {
				if (data == 0) {
					closeCallOther();
					tab = 1;
					getChangedData();
				}
				if (data == 2) {
					closeCallOther();
					tab = 1;
					timeout();
				}
				if (data == 1) {
					$("#messagetext").text("外呼失败，请联系管理员");
					//alert("外呼失败，请联系管理员");
					tab = 1;
					document.getElementById("call-send").style.display = "";
					document.getElementById("calling").style.display = "none";
					getChangedData();
				}
				if (data == 3) {
					$("#messagetext").text("电话号码重复");
					//alert("电话号码重复");
					tab = 1;
					document.getElementById("call-send").style.display = "";
					document.getElementById("calling").style.display = "none";
					getChangedData();
				}
			}
		});
	}

	// 初始时，将正在呼叫隐藏
	document.getElementById("calling").style.display = "none";
	$('#call-send').click(function() {
		if ($("#otherName").val()){
			for (var i=0; i<$("tr.user-item").length; i++){
				if ($("tr.user-item")[i].childNodes[1].textContent.indexOf($("#otherName").val()) != -1){
					$("#messagetext").text("新增外呼不能和参会人姓名相同");
					return false;
				}
			}
		}
		
		// 防止刷新
		tab = 0;
		//var regArea = /^0\d{2,3}$/;
		var regTel = /^\+?[1-9][0-9]*$/;
		var regCode = /^\+([1-9]|[1-9][0-9]*)$/;
		var regArea = /^\+?[0-9]*$/;

		var tel = $('.phone-main').val();
		var area = $('.num').val();
		var country = $("#country").val(); // 国家码
		if (!regCode.test(country)) {
			$("#messagetext").text("国家码不正确，请修正!");
			$("#country").val("+86");
			return false;
		}
		if (tel == "" || tel == "电话号码") {
			$("#messagetext").text("请输入电话号码");
			$('.phone-main')[0].focus();
		} else {
			if(country == "+86"){
				if (tel.length != 11 && (area == "" || area == "区号")) {
					$("#messagetext").text("请输入区号");
					return false;
				}
				if (tel.length == 11) {
					if (!is_mobile(tel)) {
						$("#messagetext").text("手机号码不正确,请修正!");
						$('.phone-main')[0].focus();
					} else {
						$("#messagetext").text("");
						callother();
					}
				} else {
					if (regTel.test(tel)) {
						if (!regArea.test(area)) {
							$("#messagetext").text("电话区号不正确，请修正!");
							return false;
						} else {
							$("#messagetext").text("");
							callother();
						}
					} else {
						$("#messagetext").text("电话号码格式不正确,请修正!");
						$('.phone-main')[0].focus();
					}
				}
			} else {
				if (!regTel.test(tel)) {
					$("#messagetext").text("电话号码格式不正确,请修正!");
					return false;
				}
				if(area != "" && area != "区号"){
					if (!regArea.test(area)) {
						$("#messagetext").text("电话区号不正确，请修正!");
						return false;
					} else {
						$("#messagetext").text("");
						callother();
					}
				} else {
					$("#messagetext").text("");
					callother();
				}
			}
			
			$(".call-other").show();
		}
	});

	layui.use('layer', function() {
		// 呼叫其它号码
		$("body").on('click', '.call-other', function(event) {
			$(".call-other").hide();
			// 防止刷新
			tab = 0;
			event.preventDefault();
			$('#call-user,.td-call-box').show();
			document.getElementById("showCallOther").style.display = "";
			getCountryCode(false);
		});

		$("body").on('click', '.td-del-btn', function(event) {
			event.preventDefault();
			closeCallOther();
			tab = 1;
			$(".call-other").show();
			getChangedData();
		});
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

		$(document).on('click', '.drop-list-box', function(event) {
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
	            $('.code_list').slideUp(10);
	        }
	    });
		// 自定义的下拉菜单点击事件
	    $(document).on('click', '.code_list li', function(event) {
	        event.preventDefault();
	        var timeText = $(this).text();
	        var timedata = $(this).data('time');
	        var target = $(this).closest('.code_list').siblings('.select-menu')
	        if(timeText == "查看更多国家"){
	        	getCountryCode(true);
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
	
	function closeCallOther(){
		document.getElementById("call-send").style.display = "";
		document.getElementById("calling").style.display = "none";
		//添加的电话号码清空
		$("#task-user").prop({
	        value: "咨询客户",
	        name: 1,
		});
		$("#otherName").val("");
		$("#area").val("");
		$("#otherPhone").val("");
		$("#messagetext").text("");
		$('#call-user,.td-call-box').hide();
		document.getElementById("showCallOther").style.display = "none";
	}
	
	if (navigator.userAgent.indexOf("Chrome") == -1){
		$("#showCallOther .td-control-call.td-call-box ").css({
			"margin-left": "13px"
		})
	}
	
	var recordManager = {
		elements: {
			noRecordBox: $(".no-record"),
			recordingBox: $(".recording"),
			pausedBox: $(".paused"),
			recordStatusBox: $(".record-status-item")
		},
		init: function() {
			this.bindEvent();
			this.getRecordStatus();
		},
		bindEvent: function() {
			var self = this;
			$(".record-staus-wrap").on("click", ".btn-start", function() {
				self.startRecord();
			}).on("click", ".btn-pause", function() {
				self.stopRecord();
			});
		},
		getRecordStatus: function() {
			var confId = $("#confId").val(); // 会议Id
			var url = BASE_PATH + "/conf/confRecordStatus/";
			var self = this;
			$.ajax({
				type : "POST",
				// 提交的数据
				data : {
					confId : confId
				},
				url : url,
				cache : false,
				success : function(data) {
					self.elements.recordStatusBox.hide();
					if (data.status == -1) {
						return;
					}
					
					if (data.status == 0) {
						self.elements.noRecordBox.show();
					}
					
					if (data.status == 1) {
						self.elements.recordingBox.show();
					}
					
					if (data.status == 2) {
						self.elements.pausedBox.show();
					}
				}
			});
			

			setTimeout(function() {
				self.getRecordStatus();
			}, 3000);
		},
		startRecord: function() {
			var confId = $("#confId").val(); // 会议Id
			var url = BASE_PATH + "/conf/confRecordStatusUpdate/";
			var self = this;
			$.ajax({
				type : "POST",
				// 提交的数据
				data : {
					confId : confId,
					status: 1
				},
				url : url,
				cache : false,
				success : function(data) {
					if (data.code == 1) {
						self.elements.recordStatusBox.hide();
						self.elements.recordingBox.show();
					}
					
					if (data.code != 1 && data.msg) {
						layer.msg(data.msg, {time : 3000});
					}
				}
			});
		},
		stopRecord: function() {
			var confId = $("#confId").val(); // 会议Id
			var url = BASE_PATH + "/conf/confRecordStatusUpdate/";
			var self = this;
			$.ajax({
				type : "POST",
				// 提交的数据
				data : {
					confId : confId,
					status: 2
				},
				url : url,
				cache : false,
				success : function(data) {
					if (data.code == 1) {
						self.elements.recordStatusBox.hide();
						self.elements.pausedBox.show();
					}
					if (data.code != 1 && data.msg) {
						layer.msg(data.msg, {time : 3000});
					}
				}
			});
		}
	};
	
	recordManager.init();
});

//初始化国家码，type=true表明把数据库中所有国家列出，否则只显示5个常用国家
function getCountryCode(type){
	$.ajax({
        type: 'GET',
        url: BASE_PATH + "/create/countrycode",
        success: function (data) {
        	$("#custInfo li").remove();
        	$("#country").val("+86");
        	if(type==true){
        		$.each(data,function(index,item){
            		$("#custInfo").append("<li data-time='"+item.code+"'>"+item.country+"</li>");
            	});
        	}else{
        		$.each(data,function(index,item){
        			if(index<6){
        				$("#custInfo").append("<li data-time='"+item.code+"'>"+item.country+"</li>");
        			}        			
        		});
        		//最后添加一条链接
        		$("#custInfo").append("<li data-time='0'>查看更多国家</li>");
        	}        	
        }
    });
}
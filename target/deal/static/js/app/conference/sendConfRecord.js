var confId = $("#confId").val();

$(document).ready(function () {
	$("#luyin").click(function () {
		var email=$('#recode_email').val();
		if(Vbus.rema(email) == false){
			 layer.msg('请输入正确的邮箱', {time: 2E3, shade: 0.3});
             return false;
		}
		var url = BASE_PATH + "/app/conf/sendRecord?confId=" + confId + "&email=" + $("#recode_email").val();
		$.ajax({
			type : 'GET',
			url : url,
			success : function(data) {
				if (data == "true") {
					layer.msg("会议录音已发送", {time:1000, shade: 0.3});
					$("#recode_email").val("");
                    setTimeout(function() {
                        window.location.reload(true);
                    }, 1000);
				} else {
                    layer.msg("录音发送失败！", {time:1000, shade: 0.3});
				}
			}
		});
	});
});


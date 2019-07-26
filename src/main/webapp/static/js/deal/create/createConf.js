function addConfInfo() {
    if ($('#confName').val() == "" || $('#confName').val() == "会议主题") {
        $("#confName").siblings(".message").text("请填写会议主题");
        cableAction($('#confName'));
        return false;
    }else{
        if(!isValidChar($('#confName').val())){
            $("#confName").siblings(".message").text("会议主题只能填写中文、英文、数字");
            cableAction($('#confName'));
            return false;
        }
    }

    var nowDate = new Date();
    var selectDate = $('#beginTime').val();
    if (selectDate == "" || selectDate == "开始时间") {
        $("#beginTime").closest(".conf-begin-time-wrap").siblings(".message").text("请输入召开会议时间");
        cableAction($('#beginTime'));
        return false;
    }
    var dates=selectDate+' '+$("#beginHours").val()+':'+$("#beginMinutes").val();
    var selData=new Date(Date.parse(dates));
    var selectData = new Date(selData.getTime()-1000*60);
    if (nowDate > selectData) {
        $("#beginTime").closest(".conf-begin-time-wrap").siblings(".message").text("会议召开时间不能早于当前时间");
        cableAction($('#beginTime'));
        return false;
    }

    $("#confStartTime").val(selData.getFullYear() + "/" + (selData.getMonth() + 1) + "/" + selData.getDate() + " " + selData.getHours() + ":" + selData.getMinutes());
    if (!verifyInputData()) {
        return false;
    }

    $('#duration').val($('#confDuration').val());
    $('#config').val(getConfigIndex($('#confConfig').val()));
    if(!validateConfig($('#config').val())){
        return false;
    }
    $('#sendtask').attr('disabled',"true");
    var url = BASE_PATH + '/create/addConference';

    $.ajax({
        url: url,
        data: $('#addTask').serialize(),
        type: "POST",
        success: function(result) {
            if (result.success == "true") {
                layer.msg('<p class="pub-toast"><span class="icons icons-success sucess-icons"></span>会议安排成功</p>',{time : 3000});
                setTimeout(function(){window.location.href = BASE_PATH + "/conf/confInfo";},2000);
            } else {
                layer.msg("开通会议失败", {time : 3000});
                $('#sendtask').removeAttr("disabled");
                $.ajax({
                    url : BASE_PATH + "/create/delBillingCode",
                    type : "POST",
                });
            }
        }
    });
}


$(function() {
     getLastConfConfig();
});
//初始化会议高级属性，继承账号最后创建的一场会议配置
function getLastConfConfig(){
      $.ajax({
          type: 'GET',
          url: BASE_PATH + "/conf/last/conference",
          success: function (data) {
              if(data.success == "true"){
                  var confConfigText = "到达会议预约时间，自动外呼顾问";
                  switch(data.confConfig){
                      case 2:
                          confConfigText = "有咨询客户入会后，自动外呼顾问";
                          break;
                      case 3:
                          confConfigText = "所有咨询客户入会后，自动外呼顾问";
                          break;
                      case 4:
                          confConfigText = "与咨询客户确认后，由客服外呼顾问";
                          break;
                  }
                  $("#confConfig").val(confConfigText);
                  if(data.ifMail == 1){
                      $("#notifyemail").attr('checked', true);
                      $("#notifyemail").val(1);
                  }else{
                      $("#notifyemail").attr('checked', false);
                      $("#notifyemail").val(0);
                  }
                  if(data.ifSms == 1){
                      $("#notifysms").attr('checked', true);
                      $("#notifysms").val(1);
                  }else{
                      $("#notifysms").attr('checked', false);
                      $("#notifysms").val(0);
                  }
	    	  }
	      }
	  });
	}
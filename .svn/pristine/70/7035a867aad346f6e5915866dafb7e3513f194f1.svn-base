function updateConfInfo(){
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

    $('#sendtask').attr('disabled',"true");
    var url=BASE_PATH + '/create/updateConference';
    $('#duration').val($('#confDuration').val());
    $('#config').val(getConfigIndex($('#confConfig').val()));
    if(!validateConfig($('#config').val())){
        return false;
    }
    $.ajax({
        url: url,
        data: $('#addTask').serialize(),
        type: "POST",
        success: function(result) {
            if(result.success == "true"){
                layer.msg('<p class="pub-toast"><span class="icons icons-success sucess-icons"></span>修改会议信息成功</p>',{time : 2000});
                setTimeout(function(){window.location.href = BASE_PATH + "/conf/confInfo";},2000)
            }else{
                layer.msg("修改会议信息失败", {time : 2000});
            }
        }
    });
}


$(function(){
	    var confStartTime = $("#beginTime").attr("data-value");
	    var confStartTimes = confStartTime.split(" ");
	    var confStartYear = confStartTimes[0].split("-");
	    var confStartHM = confStartTimes[1].split(":");
	    var nowTime = new Date(confStartYear[0] - 0, confStartYear[1] - 1, confStartYear[2] - 0, confStartHM[0] - 0, confStartHM[1] - 0, confStartHM[2] - 0);
	     var nowHour = nowTime.getHours() < 10 ? "0" + nowTime.getHours() : nowTime.getHours();
	     var nowMinute = nowTime.getMinutes() < 10 ? "0" + nowTime.getMinutes() : nowTime.getMinutes();
	     
    $("#beginHours").val(nowHour);
    $("#beginMinutes").val(nowMinute);
});
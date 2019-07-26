var opStatus = true;
var opOnline = false;
//标识是否第一次登陆
var firstFlash = true;
var opPhone ="";
var opBridge = "";
var summit2 = "summit2";
var summit7 = "summit7";

window.onbeforeunload = onbeforeunload_handler;
window.onunload = onunload_handler;
function onbeforeunload_handler(){
	//异步调用退出方法，挂断电话
	//用户点击浏览器右上角关闭按钮
    if(event.clientX>document.body.clientWidth&&event.clientY<0||event.altKey)   
    {
		var ajaxUrl = BASE_PATH +"/support/logout";
		$.ajax({
			type:"GET", //请求方式
			url:ajaxUrl,
			dataType:"json",
			async:false,
			contentType : "application/json",
			success:function (data) {
				window.location.href =BASE_PATH +"/admin";
	        }
	    });
	//用户点击任务栏，右键关闭
	}else if(event.clientY > document.body.clientHeight || event.altKey){
		var ajaxUrl = BASE_PATH +"/support/logout";
		$.ajax({
			type:"GET", //请求方式
			url:ajaxUrl,
			dataType:"json",
			async:false,
			contentType : "application/json",
			success:function (data) {
				window.location.href =BASE_PATH +"/admin";
	        }
	    });
    }
}

function onunload_handler(){
	//异步调用退出方法，挂断电话
	//用户点击浏览器右上角关闭按钮
    if(event.clientX>document.body.clientWidth&&event.clientY<0||event.altKey)   
    {
		var ajaxUrl = BASE_PATH +"/support/logout";
		$.ajax({
			type:"GET", //请求方式
			url:ajaxUrl,
			dataType:"json",
			async:false,
			contentType : "application/json",
			success:function (data) {
				window.location.href =BASE_PATH +"/admin";
	        }
	    });
	//用户点击任务栏，右键关闭
	}else if(event.clientY > document.body.clientHeight || event.altKey)
    {
		var ajaxUrl = BASE_PATH +"/support/logout";
		$.ajax({
			type:"GET", //请求方式
			url:ajaxUrl,
			dataType:"json",
			async:false,
			contentType : "application/json",
			success:function (data) {
				window.location.href =BASE_PATH +"/admin";
	        }
	    });
    }
}

function convertType(type){
	var confType="";
	//会议类型  （0:交易 1：掉线 2：大方 3：重要）
	if(type ==0){
		confType = "交易";
	}else if(type ==1){
		confType = "掉线";
	}else if(type ==2){
		confType = "大方数";
	}else if(type ==3){
		confType = "重要会议";
	}
	return confType;
}

function getLocalTime(inputTime) {
	var date = new Date(inputTime);  
    var y = date.getFullYear();    
    var m = date.getMonth() + 1;    
    m = m < 10 ? ('0' + m) : m;    
    var d = date.getDate();    
    d = d < 10 ? ('0' + d) : d;    
    var h = date.getHours();  
    h = h < 10 ? ('0' + h) : h;  
    var minute = date.getMinutes();  
    var second = date.getSeconds();  
    minute = minute < 10 ? ('0' + minute) : minute;    
    second = second < 10 ? ('0' + second) : second;   
    //return y + '-' + m + '-' + d+' '+h+':'+minute+':'+second; 
    return h+':'+minute+':'+second; 
}

function taskDone(this_so){
	var confId = $(this_so).attr("confId");
	var confType = $(this_so).attr("confType");
	var supportID = $(this_so).attr("supportID");
	var bridgeName = $(this_so).attr("bridgeName");
	if(bridgeName==opBridge){
		if(opOnline){
			//领取任务
			$.ajax({    
		        type:"GET",    
		        url: BASE_PATH +"/support/task/open/"+confId+"/"+confType+"/"+supportID,    
		        dataType:"json",    
		        global:false,     
		        success: function(data){              
		          if(data.success == "true"){
		        	  //将客服的状态标示为占用
		        	  opStatus=false;
		        	  //领取任务成功，返回处理页面
		        	  showTaskList();
		        	  layer.open({
		                  type: 2,
		                  title: false,
		                  closeBtn: 2,
		                  skin: 'service-iframe-class',
		                  area: ['700px', '550px'],
		                  scrollbar: false,
		                  shadeClose: false, //开启遮罩关闭
		                  content: [BASE_PATH +'/support/taskInfo/'+ confId+"/"+confType+"/"+supportID, 'no'],
		                  cancel: function(index, layero){
		                	    var custid = $(layero).find("iframe")[0].contentWindow.custId
		                	  	if(custid ==0){
		                	  		//任务回退到未领取
									closeWindow(confId,confType,supportID);
									//客服状态返回未处理
									opStatus=true;
									layer.close(index);
									return false;
		                	  	}else{
		                	  		$(layero).find("iframe")[0].contentWindow.callBackCustomer();
		                	  		//任务回退到未领取
									closeWindow(confId,confType,supportID);
									//客服状态返回未处理
									opStatus=true;
									layer.close(index);
		                	  		//layer.msg('请先将咨询客户送回会中', { time: 1500 });
		                	  		return false;
		                	  	}							 
		      	          }
		              });
		        	  
		          }else if(data.success == "false"){
		        	  //任务已被领取
		        	  layer.msg('<p class="task-popup">任务已被领取</p>', { time: 1500 });
		        	  //将客服的状态标示为可用
		        	  opStatus=true;
		        	  showTaskList();
		          }else {
		        	  //将客服的状态标示为可用
		        	  opStatus=true;
		        	  showTaskList();
		        	  //任务领取出现异常
		        	  layer.msg('<p class="task-popup">任务领取出现异常</p>', { time: 1500 });
		          }
		        }
		    });
		}else{
			layer.msg('<p class="task-popup">您没有呼叫自己，无法领取任务</p>', { time: 1500 });
		}
	}else{
		layer.msg('<p class="task-popup">您使用的账号平台与客户平台不一致，请更换账号</p>', { time: 1500 });
	}	
}


function autoDealTask(confId,confType,supportID){
	//领取任务
	$.ajax({    
        type:"GET",    
        url: BASE_PATH +"/support/task/open/"+confId+"/"+confType+"/"+supportID,    
        dataType:"json",    
        global:false,     
        success: function(data){              
          if(data.success == "true"){
        	  //将客服的状态标示为占用
        	  opStatus=false;
        	  //领取任务成功，刷新处理列表
        	  showTaskList();
        	  //播放提示音
        	  playSound();
        	  layer.open({
                  type: 2,
                  title: false,
                  closeBtn: 2,
                  skin: 'service-iframe-class',
                  area: ['700px', '550px'],
                  scrollbar: false,
                  shadeClose: false, //开启遮罩关闭
                  content: [BASE_PATH +'/support/taskInfo/'+ confId+"/"+confType+"/"+supportID, 'no'],
                  cancel: function(index, layero){
                	  var custid = $(layero).find("iframe")[0].contentWindow.custId
                	  if(custid ==0){
              	  			//任务回退到未领取
							closeWindow(confId,confType,supportID);
							//客服状态返回未处理
							opStatus=true;
							layer.close(index);
							return false;
	              	  }else{
		              		$(layero).find("iframe")[0].contentWindow.callBackCustomer();
	            	  		//任务回退到未领取
							closeWindow(confId,confType,supportID);
							//客服状态返回未处理
							opStatus=true;
							layer.close(index);
	              	  		//layer.msg('请先将咨询客户送回会中', { time: 1500 });
	              	  		return false;
	              	  }	
      	          }
              });
          }
        }
    });
}

function closeWindow(confId,confType,supportID){
	//关闭任务
	$.ajax({    
        type:"GET",    
        url: BASE_PATH +"/support/task/reNew/"+confId+"/"+confType+"/"+supportID,    
        dataType:"json",    
        global:false,     
        success: function(data){              
          if(data.success == "true"){
        	  showTaskList();
          }else {
        	  //任务领取出现异常
        	  layer.msg('<p class="task-popup">任务关闭出现异常</p>', { time: 1500 });
          }
        }  
    }); 	
}

$(document).ready(function () {
	//加载列表
	showTaskList();
	refreshForm();
		
	//挂断op电话
    $('#hangup').click(function () {
		var ajaxUrl = BASE_PATH +"/support/hangUp";
		$.ajax({
			type:"GET", //请求方式
			url:ajaxUrl,
			dataType:"json",
			contentType : "application/json",
			success:function (data) {
				if(data.success == "true"){
					opOnline=false;
					showOpStatus();
		        }				
	        }
	    });
    });
    
    //显示op自己电话,可外呼其他号码
    $('#callOther2').click(function () {
    	var ajaxUrl = BASE_PATH +"/support/showOpNum/"+summit2;
		$.ajax({
			type:"GET", //请求方式
			url:ajaxUrl,
			dataType:"json",
			contentType : "application/json",
			success:function (data) {
				$("#phoneNum2").val(data.telNum);
				opPhone = data.telNum;
	        }
	    });
		document.getElementById("calldiv2").style.display="";
		document.getElementById("calldiv7").style.display="none";
    });
  
    //显示op自己电话,可外呼其他号码
    $('#callOther7').click(function () {
    	var ajaxUrl = BASE_PATH +"/support/showOpNum/"+summit7;
		$.ajax({
			type:"GET", //请求方式
			url:ajaxUrl,
			dataType:"json",
			contentType : "application/json",
			success:function (data) {
				$("#phoneNum7").val(data.telNum);
				opPhone = data.telNum;
	        }
	    });
		document.getElementById("calldiv7").style.display="";
		document.getElementById("calldiv2").style.display="none";
    });
    
    //外呼op自己summit2
    $('#phoneCall2').click(function () {
    	if($("#phoneNum2").val() != ""){
    		//判断号码是否修改，没有修改，直接外呼
        	if(opPhone == $("#phoneNum2").val()){
        		reCallOp(summit2);
    	    	document.getElementById("calldiv2").style.display="none";
        	}else{
        		//保存新号码到数据库
            	saveOpTel2();
        	}
    	}else{
    		layer.msg('<p class="task-popup">电话号码不能为空</p>', { time: 1500 });
    	}
    });

    //外呼op自己summit7
    $('#phoneCall7').click(function () {
    	if($("#phoneNum7").val() != ""){
    		//判断号码是否修改，没有修改，直接外呼
        	if(opPhone == $("#phoneNum7").val()){
        		reCallOp(summit7);
    	    	document.getElementById("calldiv7").style.display="none";
        	}else{
        		//保存新号码到数据库
            	saveOpTel7();
        	}
    	}else{
    		layer.msg('<p class="task-popup">电话号码不能为空</p>', { time: 1500 });
    	}
    });
    

    //任务量查询
    $('#getConfNum').click(function () {
		var ajaxUrl = BASE_PATH +"/support/queryConfNum";
		
        layer.open({
        	type: 2,
            title: false,
            closeBtn: 2,
            skin: 'edit-demo-class',
            area: ['700px', '450px'],
            shadeClose: true, // 开启遮罩关闭
            content: [ajaxUrl, 'no']
        });
        
    });     
    
    //退出登录
    $('#logout').click(function () {
		var ajaxUrl = BASE_PATH +"/support/logout";
		$.ajax({
			type:"GET", //请求方式
			url:ajaxUrl,
			dataType:"json",
			contentType : "application/json",
			success:function (data) {
				window.location.href =BASE_PATH +"/admin";
	        }
	    });
    });     
});

function saveOpTel2(){
	//保存电话号码
	var ajaxUrl = BASE_PATH +"/support/saveOpTelNum";
	$.ajax({
		type:"POST", //请求方式
		url:ajaxUrl,
		data: {
			bridgeName: summit2,
			phoneNum: $("#phoneNum2").val()
		},
		success:function (data) {
			if(data.success == "true"){
				//保存成功后，调用外呼接口，并隐藏号码编辑窗口
				reCallOp(summit2);
		    	document.getElementById("calldiv2").style.display="none";
	        }
        }
    });
}

function saveOpTel7(){
	//保存电话号码
	var ajaxUrl = BASE_PATH +"/support/saveOpTelNum";
	$.ajax({
		type:"POST", //请求方式
		url:ajaxUrl,
		data: {
			bridgeName: summit7,
			phoneNum: $("#phoneNum7").val()
		},
		success:function (data) {
			if(data.success == "true"){
				//保存成功后，调用外呼接口，并隐藏号码编辑窗口
				reCallOp(summit7);
		    	document.getElementById("calldiv7").style.display="none";
	        }
        }
    });
}

function reCallOp(bridgeName){
	//外呼自己的电话号码
	var ajaxUrl = BASE_PATH +"/support/callIn/"+bridgeName;
	$.ajax({
		type:"GET", //请求方式
		url:ajaxUrl,
		dataType:"json",
		contentType : "application/json",
		success:function (data) {
			if(data.success == "true"){
				//外呼自己成功，可以领取任务
				opOnline=true;
				showOpStatus();
	        }
        }
    });
}

//定时5秒刷新列表
function refreshForm(){
	if(opStatus){
		//当客服没有处理任务时，刷新
		showTaskList();
		showOpStatus();
		//定时10秒刷新,检查是否有任务未领取，自动分配给未处理任务的客服
		getTaskList();
	}	
    setTimeout(refreshForm, 5000);
}

function showTaskList(){
	//获取任务池中summit2/7任务列表
    $.ajax({    
        type:"GET",    
        url: BASE_PATH +"/support/list",    
        dataType:"json",
        global:false,
        timeout:5000,
        success: function(data){
          $("#taskList2 tr:not(#title)").remove();
          $("#taskList7 tr:not(#title)").remove();
          if(null != data && "" != data){     
        	  //判断有数据，隐藏空图片
        	  document.getElementById("notask").style.display="none";
        	  //播放提示音
        	  playSound();
        	  $.each(data,function(index,item){
        		  if(item.bridgeName ==summit7){
        			  $("#taskList7").append(  
                              "<tr>"
         		              +"<td><span class='task-theme urgency'>" + item.confName + "</span></td>"
         		              +"<td><span >" + convertType(item.confType) + "</span></td>"
         		              +"<td>" + getLocalTime(item.beginTime) + "-" + getLocalTime(item.endTime) + "</td>"
         		              +"<td><span class='online'>" + item.onlineMan + "</span></td>"
         		              +"<td><span class='offline'>" + item.offlineMan + "</span></td>"
         		              +"<td>"
	    		              +"<span class='td-btn get-task' confId="+item.confID+" confType="+item.confType+" supportId="+item.supportID+" bridgeName="+item.bridgeName+" onclick='taskDone(this)'>" + "领取" + "</span>"
         		              +"</td>"
         		              +"</tr>");
        		  }else{
        			  $("#taskList2").append(  
 	                         "<tr>"
 	    		              +"<td><span class='task-theme urgency'>" + item.confName + "</span></td>"
 	    		              +"<td><span >" + convertType(item.confType) + "</span></td>"
 	    		              +"<td>" + getLocalTime(item.beginTime) + "-" + getLocalTime(item.endTime) + "</td>"
 	    		              +"<td><span class='online'>" + item.onlineMan + "</span></td>"
 	    		              +"<td><span class='offline'>" + item.offlineMan + "</span></td>"
 	    		              +"<td>"
 	    		              +"<span class='td-btn get-task' confId="+item.confID+" confType="+item.confType+" supportId="+item.supportID+" bridgeName="+item.bridgeName+" onclick='taskDone(this)'>" + "领取" + "</span>"
 	    		              +"</td>"
 	    		              +"</tr>");        			  
        		  }
              });
          }else{
        	  //判断数据为空，显示空图片
          	  document.getElementById("notask").style.display="";
          }            
        }  
    });  
}

function getTaskList(){
	//查询任务池中，查询第一个超出30秒没有领取的任务,自动分配给未处理任务的客服
	if(opOnline){
		$.ajax({    
	        type:"GET",    
	        url: BASE_PATH +"/support/firstTaskToDo",    
	        dataType:"json",
	        global:false,     
	        success: function(data){
	          if(opStatus && data.success == "true"){
	        	  //本客服闲置，可以分配
	        	  autoDealTask(data.taskInfo.confID,data.taskInfo.confType,data.taskInfo.supportID);
	          }
	        }  
	    }); 
	}		
}

function showOpStatus(){
	//检查客服人员是否在线，显示相关图标和文字
    $.ajax({    
        type:"GET",
        url: BASE_PATH +"/support/isOnline?"+new Date().getTime(),    
        dataType:"json",
        global: false,
        success: function(data){
        	if(data.success == "true"){
        		//客服在线，显示客服在线状态
        		opOnline =true;
        		$("#userName").text(data.username);
        		opBridge = data.bridgeName;
        		//显示挂断按钮，隐藏呼叫按钮
        		document.getElementById("noCall").style.display="none";
        		document.getElementById("hangup").style.display="";
        		document.getElementById("inCall").style.display="";
        		//document.getElementById("callin").style.display="none";
        		firstFlash =false;
        	}else if(data.success == "false"){
        		//客服不在线,不能领取任务
        		opOnline=false;
        		$("#userName").text(data.username);
        		opBridge = data.bridgeName;
        		//显示呼叫按钮，隐藏挂断按钮
        		document.getElementById("noCall").style.display="";
        		document.getElementById("hangup").style.display="none";
        		document.getElementById("inCall").style.display="none";
        		//document.getElementById("callin").style.display="";
        		//当第一次打开页面时，弹出下面提示，以后刷新不提示
        		if(firstFlash){
        			layer.confirm('是否需要立即接通电话？', {
            			  btn: ['接通2平台','接通7平台','等等再说'] //按钮
            			}, function(){
            				layer.closeAll('dialog');
            				reCallOp(summit2);
            			}, function(){
            				layer.closeAll('dialog');
            				reCallOp(summit7);
            			}, function(){
            				layer.closeAll('dialog');
            			});
        		}
        		firstFlash =false;
        	}else if(data.success == "error"){
        		window.location.href =BASE_PATH +"/admin";
        	}
        }
    });
}

//弹出处理页面时，发出提示音
var au = document.createElement("audio");
au.preload="auto";
au.src = SOUND_PATH +"/ringtone.mp3";
function playSound() {
    au.play();
}


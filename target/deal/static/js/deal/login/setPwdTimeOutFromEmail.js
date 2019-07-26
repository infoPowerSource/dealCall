 $(document).ready(function() {
        layui.use('layer', function() {
            $("#button").click(function(event) {
		      	   var nowTime = new Date().getTime();
		    	   var clickTime = $(this).attr("ctime");
		    	   if( clickTime != 'undefined' && (nowTime - clickTime < 2000)){
		    	       alert("操作过于频繁，稍后再试");
		    	        return;
		    	     }else{
		    	        $(this).attr("ctime",nowTime);
		                resendMail();
		    	     }
            });
        });
    });
 
 
 function resendMail(){
	   var _session_name =$("#importMsgInput_name").val();  
	   var _session_id =$("#importMsgInput_id").val();   
	   var _session_userDisplayName =$("#importMsgInput_userDisplayName").val();  
	   //alert(_session_userDisplayName); 
	   var url = BASE_PATH + "/login/findBackPassByMail_resendMail?id=" +_session_id;//+ "&userDisplayName=" + encodeURI(_session_userDisplayName)
	   $.ajax({
	   			url : url,
	  			data : $('#czmmform').serialize(),
	   			type : "POST",
	   			success : function(result) {
                    layer.msg(result.msg, {time: 2E3, shade: 0.3});
	   				}
	   			});
	}
 

 
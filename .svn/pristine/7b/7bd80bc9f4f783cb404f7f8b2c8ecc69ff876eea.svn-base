$(document).ready(function () {
	
	getConfNumlist();
});

function getConfNumlist(){
    //获取会议量列表
    $.ajax({
        type:"GET",    
        url: BASE_PATH +"/support/getConfNum",
        async:false, 
        dataType:"json",
        global:false,
        success: function(data){
        	if(null != data && "" != data){
	        	$.each(data,function(index,item){
	        		$("#confList").append(
		                 "<tr>"
			              +"<td width='35%'>" + item.begin_time + "</td>"
			              +"<td width='30%' align='center'>" + item.all_num + "</td>"
			              +"<td width='35%' align='center'>" + item.op_num + "</td>"
			              +"</tr>");
	        	});
        	}
        }
    });  
}

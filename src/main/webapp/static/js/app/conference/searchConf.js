var page =1;
var isScrolling = false;
var keywords = "";

(function($) {
	$(".mui-content").on('click', '.u-yuyin', function(event) {
		var confId = this.parentNode.parentNode.getAttribute("data-conf-id");
		//跳转到发送录音页面
		window.location.href = BASE_PATH + "/conf/confRecord/" + confId;
	});
	
	$(".mui-content").on('click', '.u-wendang', function(event) {
		var confId = this.parentNode.parentNode.getAttribute("data-conf-id");
		window.location.href = BASE_PATH + "/app/conf/confReport?confId=" + confId;
	});
	
})(mui);

$(document).ready(function () {

    $("#searchTaskKeywords").keyup(function(event){
        if(event.keyCode ==13){
    		keywords = Trim($("#searchTaskKeywords").val());
            if(keywords === "输入主题、姓名、手机、邮箱搜索会议" || keywords == ""){
                return false;
            }else{
                $(".search").find(".icon-close").show();
                searchConfList(keywords,1);
            }
        }
    });
	$(".search .icon-search").on("click", function (event) {
		keywords = Trim($("#searchTaskKeywords").val());
		if(keywords === "输入主题、姓名、手机、邮箱搜索会议" || keywords == ""){
			 return false;
		}else{
			$(".search").find(".icon-close").show();
            page =1;
			searchConfList(keywords,1);
		}
	});
	
	$(".search .icon-close").on("click", function (event) {
		$(".task-search-title").hide();
		$("#searchTaskKeywords").val("");
		$("#confList li").remove();
		$(".search").find(".icon-close").hide();
		page =1;
	});
	
	var timer = null;
	var dataTimer = null;
	$(window).on("scroll", function() {
		isScrolling = true;
		
		clearTimeout(timer);
		timer = setTimeout(function() {
			isScrolling = false;
		}, 2000);
		
		var scrollTop = $(window).scrollTop() - 36 < 0 ? 0 : $(window).scrollTop() - 36; //滚出去的高
		var clientHeight = $(document.body)[0].clientHeight - 44; //可视区域的高
		var scrollHeight = $(document.body)[0].scrollHeight - 36 < 0 ? 0 : $(document.body)[0].scrollHeight - 36; // 总高
		
		clearTimeout(dataTimer);
		if (scrollTop + clientHeight > scrollHeight - 120) {
			dataTimer = setTimeout(function() {
				page += 1;
				searchConfList(keywords,0);
			}, 500);
		}
	});
});

function Trim(str){ 
    return str.replace(/(^\s*)|(\s*$)/g, ""); 
}

function searchConfList(keywords,type){
	var url = BASE_PATH + "/app/conf/searchConf4Page";
	$.ajax({
		type : 'POST',
		url : url,
		data: {keyword: keywords, date: null, pageNum: page},
		success : function(data) {
			if(null != data && "" != data){
				$(".task-search-title").show().html("搜索结果：共<span class='important'>" + data.total + "</span>场" + (keywords ? "与关键词“<span class='important'>" + keywords + "</span>”相关的会议" : "会议" ));
				if(type==1){
	        		//第一次查询，清空列表，如果是下滑加载更多，不清空
		        	$("#confList li").remove();
	        	}
				if(data.total>0){
					var confList = data.recordList;
					$.each(confList, function (index, item) {
			            var linktxt = '';
			            if (item.confStatus == 2) {
			            	if(item.confRadios && item.confRadios[0] && item.confRadios[0].fileName){
			  				  	linktxt = "<svg class='icon u-yuyin'><use xlink:href='#mic'/></svg>";
			  			  	}
			            	if(item.confReports && item.confReports[0] && item.confReports[0].reportName){
			  			  		linktxt = linktxt + "<svg class='icon u-wendang'><use xlink:href='#report'/></svg>";
			  			  	}
			  			  	$("#confList").append(
			      				  "<li data-conf-id="+item.confId+">"
			  					    +"<div class='f-fr m-icons-1'>"
			  					    + linktxt
			  					    +"</div>"
			  						+"<a href=''>"
			  						+"<div class='m-tit'><span class='f-fr m-des z-lixian'></span>"+ item.confName +"</div>"
			  						+"<span class='m-time'>"+getSearchConfStartTime(item.beginTime, item.endTime)+"</span>"
			  						+"</a>"
			  					  +"</li>");           	
			            	
			            }else {
			            	$("#confList").append(
			      				  "<li id='conf-live-" + item.confId + "'>"
										+"<a href='#' onclick='gotoDetail("+item.confId+")'>"
										+"<div class='m-tit'><span class='f-fr m-des z-lixian'>"+transferConfStatus(item.confStatus)+"</span>"+item.confName+"</div>"
										+"<span class='m-time'>"+getSearchConfStartTime(item.beginTime, item.endTime)+"</span>"
										+"</a>"
									  +"</li>");
			            }
			        })
				}
			}
		}
	});
}
function transferConfStatus(confStatus) {
	if (confStatus == 0 || confStatus == 5) return "未召开";
	if (confStatus == 1) return "正在开会";
	if (confStatus == 4) return "会中休息";
	return confStatus;
}

//搜索结果行的会议时间处理
function getSearchConfStartTime(beginTime, endTime) {
        var today = new Date();
        var dates = beginTime.substr(0,10).split("-");
        var time = beginTime.substr(11,5) + "-" + endTime.substr(11,5);
        if (today.getFullYear() === dates[0] - 0) {
            return "<span class='date'>" + dates[1] + "月" + dates[2] + "日</span><span class='time'>" + time + "</span>";
        }

        return "<span class='date'>" + dates[0] + "年" + dates[1] + "月" + dates[2] + "日</span><span class='time'>" + time + "</span>";
}

//查看会议详情
function gotoDetail(confId) {
	window.location.href = BASE_PATH + "/app/conf/confDetail/" + confId;
}

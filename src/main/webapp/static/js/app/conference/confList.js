
var listDate;
var nowdate = nYear+'-'+nMoth+'-'+nDate;
var page =1;
var currentTab=1;
var isScrolling = false;
var onlinedate = nowdate;
var enddate = nowdate;
(function($) {
				$.init();
				var btns = $('.btn'),nSel,
					days=document.getElementById('m-days'),
					nday=document.getElementById('m-nday'),
					months=document.getElementById('m-months'),
					years=document.getElementById('m-years'),
					dNext=document.getElementById("m-days-next"),
					dPrev=document.getElementById("m-days-prev");
				years.innerText=nYear+'年';
				months.innerText=dayqianzhui(nMoth)+'月'+dayqianzhui(nDate)+'日';
				
				years.setAttribute('data-years',nYear),
	    		months.setAttribute('data-months',nMoth),
	    		days.setAttribute('data-days',nDate);
				nday.innerText ='今天';
				nSel=years.getAttribute('data-years')+'-'+dayqianzhui(months.getAttribute('data-months'))+'-'+dayqianzhui(days.getAttribute('data-days'));
				btns.each(function(i, btn) {
					btn.addEventListener('tap', function() {
						var optionsJson = this.getAttribute('data-options') || '{}';
						var options = JSON.parse(optionsJson);
						var id = this.getAttribute('id'),
						days =document.getElementById('m-days'),
						months =document.getElementById('m-months'),
						years =document.getElementById('m-years');
						
						nSel=years.getAttribute('data-years')+'-'+dayqianzhui(months.getAttribute('data-months'))+'-'+dayqianzhui(days.getAttribute('data-days'));
						picker = new $.DtPicker(options);
						picker.setSelectedValue(nSel);
						picker.show(function(rs) {
							if(nYear==rs.y.value && dayqianzhui(nMoth)==rs.m.value && dayqianzhui(nDate)==rs.d.value){
								nday.innerText ='今天';
							}else{
								nday.innerText ='';
							}
							//如果跨年，则显示年，否则只显示月日
							if(rs.y.value == nYear){
								nday.innerText = rs.m.value+'月'+rs.d.value+'日';
							}else{
								days.innerHTML ='<div id="m-months" class="m-months" data-months="'+rs.m.value+'">'+rs.m.value+'月'+rs.d.value+'日</div><div id="m-years" class="m-years" data-years="'+rs.y.value+'">'+rs.y.value+'年</div>';
							}
							days.setAttribute('data-days',rs.d.value);
							picker.dispose();
							listDate=rs.y.value + '-' + rs.m.value + '-' + rs.d.value;
							var comdate=nYear+'-'+nMoth+'-'+nDate;
							changeListTab(comdate,listDate);
						});
					}, false);
				});
				muiinitcontent();
				listDate = nSel;
				$(".mui-content").on('click', '.u-yuyin', function(event) {
					var confId = this.parentNode.parentNode.getAttribute("data-conf-id");
					//跳转到发送录音页面
					window.location.href = BASE_PATH + "/conf/confRecord/" + confId;
				});
				
				$(".mui-content").on('click', '.u-wendang', function(event) {
					var confId = this.parentNode.parentNode.getAttribute("data-conf-id");
					window.location.href = BASE_PATH + "/app/conf/confReport?confId=" + confId;
				});
				
				getChangedData();

				//查询会议数量
				getConfNum();
			})(mui);
function scrollTarget(listDate) {
    if($('#'+listDate)[0]){
    	var lockTop = $("#conf_live")[0].offsetTop == 0 ? $("#conf_end")[0].offsetTop : $("#conf_live")[0].offsetTop;
        var target_top = $('#'+listDate)[0].offsetTop - lockTop;
        console.log($('#'+listDate)[0].offsetTop,$(".m-hylb")[0].offsetTop,$(".m-hylb")[1].offsetTop,lockTop,target_top);
    	$("html,body").animate({scrollTop: target_top+5}, 500);
    }
}
function dayqianzhui(a){
	if(a<10){
		a='0'+Number(a);
	}else{
		a=a;
	}
	return a;
}
function muiinitcontent() {
    //加减点击
    mui('.m-rili').on('tap', '.m-days-prev,.m-days-next', function() {
    	var days=document.getElementById('m-days'),
    		nday=document.getElementById('m-nday'),
			months=document.getElementById('m-months'),
			years=document.getElementById('m-years'),
			dNext=document.getElementById("m-days-next"),
			dPrev=document.getElementById("m-days-prev"),
			Dy=years.getAttribute('data-years'),
    		Dm=months.getAttribute('data-months'),
    		Dd=days.getAttribute('data-days');
    	
        switch (this.id) {
            case 'm-days-next':
            	var day = new Date(new Date(Dy, Dm - 1, Dd) - 0 + 24 * 3600000);
            	
            	Dy = day.getFullYear();
            	Dm = day.getMonth() + 1;
            	Dd = day.getDate();
            	
            	years.setAttribute('data-years',Dy);
                years.innerText=Dy+'年';
                
                months.setAttribute('data-months',Dm);
                days.setAttribute('data-days',Dd);
                months.innerText=dayqianzhui(Dm)+'月'+dayqianzhui(Dd)+'日';
              
                break;
            case 'm-days-prev':
            	var day = new Date(new Date(Dy, Dm - 1, Dd) - 24 * 3600000);
            	
            	Dy = day.getFullYear();
            	Dm = day.getMonth() + 1;
            	Dd = day.getDate();
            	
            	years.setAttribute('data-years',Dy);
                years.innerText=Dy+'年';
                
                months.setAttribute('data-months',Dm);
                days.setAttribute('data-days',Dd);
                months.innerText=dayqianzhui(Dm)+'月'+dayqianzhui(Dd)+'日';
                
                break; 
            default:
                break;
        }
        var nSel=years.getAttribute('data-years')+'-'+dayqianzhui(months.getAttribute('data-months'))+'-'+dayqianzhui(days.getAttribute('data-days'));

    	var comdate=nYear+'-'+nMoth+'-'+nDate;
    	listDate = nSel;
    	changeListTab(comdate,nSel);
    });
}


//根据选择的天数，显示不同的标签
function changeListTab(nowDay,selectDay){
	var nday=document.getElementById('m-nday');
	//切换tab页，切换日期，重置查询页数
	page =1;
	//查询会议数量
	getConfNum();
	if(nowDay == selectDay){
		location.reload();
	}else if(selectDay > nowDay){
		currentTab=1;
		$("#tab_li li").remove();
		$("#tab_li").append("<li class='m-hd-list f-hd-list on'>会议列表(<span id='onlineNum'></span>)</li>");
		document.getElementById("conf_live").style.display="";
		document.getElementById("conf_end").style.display="none";
		document.getElementById("div_conf_live").style.display="";
		document.getElementById("div_conf_end").style.display="none";
		getOnlineConfList(page,selectDay);
	}else{
		currentTab=2;
		$("#tab_li li").remove();
		$("#tab_li").append("<li class='m-hd-list f-hd-list on'>已结束会议(<span id='offlineNum'></span>)</li>");
		document.getElementById("conf_live").style.display="none";
		document.getElementById("conf_end").style.display="";
		document.getElementById("div_conf_live").style.display="none";
		document.getElementById("div_conf_end").style.display="";
		getEndConfList(page,selectDay);
	}
}

//获取会议数量
function getConfNum(){
  $.ajax({    
      type:"GET",
      url: BASE_PATH + "/app/conf/getConfNum",
      data : {
			date: listDate
			},
      success: function(data){
        if(null != data && "" != data){
    	  $("#onlineNum").text(data.onlineNum);
    	  $("#offlineNum").text(data.offlineNum);
        }
      }  
  });  
}

function changeTab(tab){
	currentTab = tab;
	//切换tab页，重置查询页数
	page =1;
	onlinedate = nowdate;
	enddate = nowdate;
	//查询会议数量
	getConfNum();
	if(currentTab ==1){
		getOnlineConfList(page,listDate);
	}else{
		getEndConfList(page,listDate);
	}
}

//获取有效会议列表
function getOnlineConfList(currPage,date){
    $.ajax({    
        type:"GET",
        url: BASE_PATH + "/app/conf/getOnlineConfList",
        data : {
			currPage : currPage,
			date: date
			},
        success: function(data){
          $("#conf_live li").remove();
          var onlineNum=0;
          if(null != data && "" != data){
        	  //判断有数据，隐藏空图片
        	  document.getElementById("notask1").style.display="none";
        	  onlinedate = nowdate;
        	  $.each(data,function(index,item){
        		  if(onlinedate < item.confDate){
        			  onlinedate = item.confDate;
        			  var dates = onlinedate.split("-");
        			  $("#conf_live").append("<li id=" + onlinedate + " name="+ onlinedate +" style='padding-left: 24px;padding-top:10px;background-color:#f2f3f5' class='date-li conf-live-item-page-" + currPage + "'> <B>"+dates[1]+"月"+dates[2]+"日 </B></li>");
        		  }
        		  
    			  $("#conf_live").append(  
        				  "<li style='padding-left: 24px' id='conf-live-" + item.confId + "' class='conf-live-item-page-" + currPage + "'>"
							+"<a href='#' onclick='gotoDetail("+item.confId+")'>"
							+"<div class='m-tit'><span class='f-fr m-des z-lixian'>"+item.confStatus+"</span>"+item.confName+"</div>"
							+"<span class='m-time'>"+item.orderTime+"</span>"
							+"</a>"
						  +"</li>");
    			  onlineNum++;		  
              });
        	  
        	  if(onlineNum==0 && currPage == 1){
        		  document.getElementById("notask1").style.display="";
        	  }else{
        		  // 每次数据加载完，必须重置
        	  }
          }else{
        	  //判断数据为空，显示空图片
          	  document.getElementById("notask1").style.display="";
        	  // 锁定
          }
			scrollTarget(date);
        }  
    });  
}

//获取已结束会议列表
function getEndConfList(currPage,date){
  $.ajax({    
      type:"GET",
      url: BASE_PATH + "/app/conf/getEndConfList",
      data : {
			currPage : currPage,
			date: date
			},
      success: function(data){
        $("#conf_end li").remove();
        var offlineNum=0;
        if(null != data && "" != data){
      	  //判断有数据，隐藏空图片
      	  document.getElementById("notask2").style.display="none";
      	  enddate = nowdate;
      	  $.each(data,function(index,item){
      		  var linktxt = "";
      		  if(enddate > item.confDate){
	      		  enddate = item.confDate;
	      		  var dates = enddate.split("-");
    			  $("#conf_end").append("<li id=" + enddate + " name="+ enddate +" style='padding-left: 24px;padding-top:10px;background-color:#f2f3f5' class='date-li'> <B>"+dates[1]+"月"+dates[2]+"日 </B> </li>");
      		  }
      		  
			  if(item.radioName != null && item.radioName != ""){
				  linktxt = "<svg class='icon u-yuyin'><use xlink:href='#mic'/></svg>";
			  }
			  if(item.reportName != null && item.reportName != ""){
				  linktxt = linktxt + "<svg class='icon u-wendang'><use xlink:href='#report'/></svg>";
			  }
			  $("#conf_end").append(
    				  "<li style='padding-left: 24px' data-conf-id="+item.confId+">"
					    +"<div class='f-fr m-icons-1'>"
					    + linktxt
					    +"</div>"
						+"<a href=''>"
						+"<div class='m-tit'><span class='f-fr m-des z-lixian'></span>"+item.confName+"</div>"
						+"<span class='m-time'>"+item.orderTime+"</span>"
						+"</a>"
					  +"</li>");
			  offlineNum++; 
      	  });
      	  
      	  if(offlineNum==0){
      		  document.getElementById("notask2").style.display="";
      	  }else{
    		  // 每次数据加载完，必须重置
    	  }
        }else{
      	  	//判断数据为空，显示空图片
        	document.getElementById("notask2").style.display="";
      	  	// 锁定
        }
			scrollTarget(date);
      }  
  });  
}

$(document).ready(function () {
	pushHistory(); 
	window.addEventListener("popstate", function(e) {
		pushHistory(); 
		//alert("我监听到了浏览器的返回按钮事件啦");//根据自己的需求实现自己的功能 
	}, false); 

	function pushHistory() { 
		var state = {
				title: "title", 
				url: "#"
		}; 
		window.history.pushState(state, "title", "#"); 
	}
	
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
		
		// 
		if(nowdate > listDate){
			currentTab=2;
		}
		//console.log("scrollTop: ", scrollTop);
		//console.log("clientHeight: ", clientHeight);
		//console.log("scrollHeight: ", scrollHeight);
		//console.log("scrollHeight----: ", scrollTop + clientHeight);
		//console.log("scrollHeight---1111---: ", scrollHeight - scrollTop - clientHeight);
		
		clearTimeout(dataTimer);
		if (scrollTop + clientHeight > scrollHeight - 120) {
			//console.log("-----------------send---------------");
			dataTimer = setTimeout(function() {
				page += 1;
				if(currentTab ==1){
					appendOnlineConfList(page, listDate);
				}else{
					appendEndConfList(page,listDate);
				}
			}, 500);
		}
        var lockTop = $("#conf_live")[0].offsetTop == 0 ? $("#conf_end")[0].offsetTop : $("#conf_live")[0].offsetTop;
		var date_lis = $(".date-li");
        for (var i=0;i<date_lis.length;i++)
        {
            if(scrollTop <= date_lis[i].offsetTop-date_lis[i].clientHeight-lockTop){
                var head_date = date_lis[i-1].clientHeight == 0 ? date_lis[i].id : date_lis[i-1].id ;
                var head_date_year = head_date.substring(0,4);
                var head_date_month = head_date.substring(5,7);
                var head_date_day = head_date.substring(8,10);
                $("#m-nday")[0].innerText = "";
                //如果跨年，则显示年，否则只显示月日
                if(head_date_year == nYear){
                    $("#m-nday")[0].innerText = head_date_month+'月'+head_date_day+'日';
                }else{
                    $("#m-days")[0].innerHTML ='<div id="m-months" class="m-months" data-months="'+head_date_month+'">'+head_date_month+'月'+head_date_day+'日</div><div id="m-years" class="m-years" data-years="'+head_date_year+'">'+head_date_year+'年</div>';
                }
                break;
            }
        }
	});
	
	getOnlineConfList(page, listDate);
});


//刷新
function getChangedData() {
	if (getChangedData.timer) {
		clearTimeout(getChangedData.timer);
		getChangedData.timer = null;
	}
	//查询会议数量
	getConfNum();

	if(nowdate == listDate && currentTab == 1 && !isScrolling){
		refreshTodayConfList();
	}
	
	setTimeout(getChangedData, 5000);
	
}

function refreshTodayConfList() {
	var page = getClientConfPage();
	if (page.start === page.end) {
		return this.startRefresh(page.start);
	}
	
	var self = this;
	this.startRefresh(page.start, function() {
		self.startRefresh(page.end);
	});
	
}

function startRefresh(page, callback) {
	//console.log("----------startRefresh-----, page: ", page);
	$.ajax({    
	    type:"GET",
	    url: BASE_PATH + "/app/conf/getOnlineConfList",
	    data : {
			currPage : page,
			date: nowdate
			},
	    timeout:3000,
	    //async:false,
	    success: function(data){
	      if(null != data && "" != data){
	    	  var html = "";
	    	  onlinedate = nowdate;
	    	  $.each(data,function(index,item){
	    		  if(onlinedate < item.confDate){
        			  onlinedate = item.confDate;
        			  var dates = onlinedate.split("-");
        			  html +="<li id=" + onlinedate + " name="+ onlinedate +" style='padding-left: 24px;padding-top:10px;background-color:#f2f3f5' class='date-li conf-live-item-page-" + page + "'> <B>"+dates[1]+"月"+dates[2]+"日 </B> </li>";
        		  }
	    		  
	    		  html += "<li style='padding-left: 24px' id='conf-live-" + item.confId + "' class='conf-live-item-page-" + page + "'>"
					+"<a href='#' onclick='gotoDetail("+item.confId+")'>"
					+"<div class='m-tit'><span class='f-fr m-des z-lixian'>"+item.confStatus+"</span>"+item.confName+"</div>"
					+"<span class='m-time'>"+item.orderTime+"</span>"
					+"</a>"
				  +"</li>";
	          });
	    	  
	    	  $(".conf-live-item-page-" + page).remove();
	    	  if (page == 1) {
	    		   	$("#conf_live").prepend(html);
	    		   	if (typeof callback === "function") {
	 	    		  callback();
	 	    	  	}	    		   	
	    		   	return
	    	  }
	    	  
	    	  $(html).insertAfter($("#conf_live").find(".conf-live-item-page-" + (page-1)).last());
	    	  if (typeof callback === "function") {
	    		  callback();
	    	  }
	      }
	    }  
	}); 
}
 
// 获取可视窗口的页码
function getClientConfPage() {
	var scrollTop = $(window).scrollTop() - 36 < 0 ? 0 : $(window).scrollTop() - 36; //滚出去的高
	var clientHeight = $(document.body)[0].clientHeight - 44; //可视区域的高
	var pageStart = 1;
	var pageEnd = 1;
	var pageSize = 30;	// 每页条数
	var lineHeight = 58; //每个会议数据的高
	
	if (scrollTop !== 0) {
		pageStart = Math.ceil(Math.ceil(scrollTop / 58) / 30);
		pageEnd = Math.ceil(Math.ceil((scrollTop + clientHeight) / 58) / 30);
	}
	
	return {
		start: pageStart,
		end: pageEnd
	}
}
//查看会议详情
function gotoDetail(confId) {
	window.location.href = BASE_PATH + "/app/conf/confDetail/" + confId;
}

function createConf(){
	window.location.href = BASE_PATH + "/app/create/createConference";
}

function searchConf(){
	window.location.href = BASE_PATH + "/app/conf/searchConf";
}
function loggout() {
	window.location.href = BASE_PATH + "/login/logout";
}

function searchAccess() {	
	$.ajax({
		type : 'GET',
		url : BASE_PATH + "/app/conf/getConfAccessNum",
		success : function(data) {
			window.location.href = data;
		}
	});	
}

function modifyPassword() {
	window.location.href = BASE_PATH + "/login/modifyPassword";
}

//获取有效会议列表，后刷新
function appendOnlineConfList(currPage,date){
    $.ajax({    
        type:"GET",
        url: BASE_PATH + "/app/conf/getOnlineConfList",
        data : {
			currPage : currPage,
			date: date
			},
        timeout:3000,
        //async:false,
        success: function(data){
          if(null != data && "" != data){
        	  $.each(data,function(index,item){
        		  if(onlinedate < item.confDate){
        			  onlinedate = item.confDate;
        			  var dates = onlinedate.split("-");
        			  $("#conf_live").append("<li id=" + onlinedate + " name="+ onlinedate +" style='padding-left: 24px;padding-top:10px;background-color:#f2f3f5' class='date-li conf-live-item-page-" + currPage + "'> <B>"+dates[1]+"月"+dates[2]+"日 </B> </li>");
    	      	  }

        		  $("#conf_live").append(
        				  "<li style='padding-left: 24px' id='conf-live-" + item.confId + "' class='conf-live-item-page-" + currPage + "'>"
							+"<a href='#' onclick='gotoDetail("+item.confId+")'>"
							+"<div class='m-tit'><span class='f-fr m-des z-lixian'>"+item.confStatus+"</span>"+item.confName+"</div>"
							+"<span class='m-time'>"+item.orderTime+"</span>"
							+"</a>"
						  +"</li>");
              });
          }else{
        	  page=page-1;
              $("#div_conf_live .no-data span").text("没有更多会议了!")
              $("#div_conf_live .no-data").fadeToggle("slow");
          }
        }  
    });  
}

//获取已结束会议列表，后刷新
function appendEndConfList(currPage,date){
	$.ajax({    
	    type:"GET",
	    url: BASE_PATH + "/app/conf/getEndConfList",
	    data : {
				currPage : currPage,
				date: date
				},
	    timeout:3000,
        //async:false,
	    success: function(data){
	      if(null != data && "" != data){
	    	  $.each(data,function(index,item){
	    		var linktxt = "";
	    		if(enddate > item.confDate){
		      		  enddate = item.confDate;
        			  var dates = enddate.split("-");
        			  $("#conf_end").append("<li id=" + enddate + " name="+ enddate +" style='padding-left: 24px;padding-top:10px;background-color:#f2f3f5' class='date-li'> <B>"+dates[0]+"年"+dates[1]+"月"+dates[2]+"日 </B> </li>");
	      		}

	    		if(item.radioName != null && item.radioName != ""){
					  linktxt = "<svg class='icon u-yuyin'><use xlink:href='#mic'/></svg>";
				  }
				  if(item.reportName != null && item.reportName != ""){
					  linktxt = linktxt + "<svg class='icon u-wendang'><use xlink:href='#report'/></svg>";
				  }
				  $("#conf_end").append(
	  				  "<li style='padding-left: 24px' data-conf-id="+item.confId+">"
						    +"<div class='f-fr m-icons-1'>"
						    + linktxt
						    +"</div>"
							+"<a href=''>"
							+"<div class='m-tit'><span class='f-fr m-des z-lixian'></span>"+item.confName+"</div>"
							+"<span class='m-time'>"+item.orderTime+"</span>"
							+"</a>"
						  +"</li>");  
	          });
	      }else{
	    	  page=page-1;
              $("#div_conf_end .no-data span").text("没有更多会议了!")
              $("#div_conf_end .no-data").fadeToggle("slow");
	      }
	    }  
	});  
}
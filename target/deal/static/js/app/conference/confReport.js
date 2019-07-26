window.onload = function() {
	var vm = new Vue({
		el: "#app",
		data: {
			title: "",
			time: "",
			duration: "",
			nums: "",
			members: [
			     
			],
			emptyMembers: []
		},
		
	});
	
	function fullInMembers() {
		var bodyHeight = document.body.clientHeight;
		var $head = $("li.head");
		var top = ($head.offset().top - 0) + ($head.height() - 0);
		var count = Math.floor((bodyHeight - top) / 40);
		var length = count - vm.members.length;
		
		
		vm.emptyMembers = [];
		if (length <= 0) {
			return;
		}
		
		for (var i = 0; i < length; i++) {
			vm.emptyMembers.push({});
		}
	}
	
	fullInMembers();
	
	window.addEventListener("orientationchange", function() {
		fullInMembers();
	}, false);
	
	function getServerData() {
		var search = location.search;
		if (search.indexOf("confId") !== -1) {
			search = search.split("=");
		}
		var confId = search[1];
		var url = BASE_PATH + '/app/conf/reportData/';
		$.ajax({
	 		type:"POST",
			url:url,
            data:{confId:confId},
            dataType:"json",
            success:function(data){
            	if (!data) {
            		return;
            	}
            	
            	data = JSON.parse(data);
            	vm.title = data.confName;
    			vm.time = data.confTime;
    			vm.duration = data.confDuration;
    			vm.members = data.custList;
//    			vm.nums = data.nums;
            	fullInMembers();
            },
            error:function(jqXHR){
               
            }
        });
	}
	
	getServerData();
}
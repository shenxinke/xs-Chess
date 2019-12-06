function coursewareTeach(Vue){
	loadLatelyJob();
	var vm = new Vue({
		el: "#mainWrapper",
		data: {
			pageinfo: {}
		},
		methods: {
			page: function (pageNum) {
                var pageinfo = this.pageinfo;
                console.log(pageinfo)
                loadLatelyJob({ pageNum: pageNum})
            },
            goTo:function(id,index){
            	window.location.href='coursewareTeach.html?chessid='+id+'&classId='+jsonlink().classId+'&index='+index;
            }
		},
		mounted:function(){
		}
	});
	function loadLatelyJob(condition) {
		var params = condition || {};
		params.pageNum = params.pageNum || 1;
		params.pageSize = 18;
		params.classId = jsonlink().classId;
		params.titlesource = 1;
		$.ajax({
			url: '/gobangteach/QuestionbankController/getQuestionbankByClassId',
			type: "POST",
			dataType: "json",
			data: $.param(params),
			success: function(data) {
				console.log('棋谱列表', data.data)
				if(data.data.list.length == 0) {
					
				} else {
					$('.imgBox').removeClass('wrong right');
					vm.pageinfo = data.data;
				}
			}
		});
	}
}
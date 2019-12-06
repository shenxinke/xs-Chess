window.currentSize = 19;
window.step = 0;

//切换状态
function changeFirst(val){
	game.changeFirst(val)
}

//显示手数
function showLot(){	
	game.isShowLot ^= 1 //1显示 0隐藏
}

//清除标记
function delLabel(){
	game.delLabel()
}

//显示笑脸
function smilingFace(){
	game.isSmilingFace^=1 //1显示 0隐藏
}

//进入棋谱
function enterGame(game,data){
	var gameInfo = data.chessData;
	//设置棋盘路数
	window.currentSize = gameInfo.roadX
	game.switchChess(window.currentSize);
	//更新棋谱
	game.svg = gameInfo.listAction; 
}


$(function(){
	//搜索
	$("#collection .swiper-slide").eq(0).find('div').click(function(){
		$(this).addClass('active').siblings().removeClass('active');
		var _index = $(this).index();
		$(".colle").eq(_index).show().siblings('.colle').hide();
	});

	$(".content-right").find('ul').each(function () {
			$(this).click(function (i) {
				$(this).addClass('active').siblings('ul').removeClass('active');
			})
		});
	$(".board").click(function(){
		$(this).addClass('active').siblings().removeClass('active')
	})
	//selectListKnowledge();  //根据类型 棋力 查询知识点
	//初始化判断  是否在历史对局中进入
	var historyGameId = Tool.queryString("historyGameId");
	//var isTeacher = Tool.queryString("isTeacher");
	if(historyGameId){
		vmManualModal.loadChess(historyGameId);
  
		$(".content-right ul").eq(3).attr('class','active').siblings().removeAttr('class');
	}else{
		clearChessboard();
	}
	laydate.render({
	  elem: '#test10'
	  ,type: 'datetime'
	  ,range: true
	});
	window.dataMsg=['','','']
})

/* 棋盘相关 */
$(function() {
	//链接成功回调
	eve.on('socketOpen',function(){
		console.log('链接成功')
	})
	
	//棋局登入成功回调
	eve.on('signInResponse',function(){
		console.log('登入成功');
	})
	
});
	function popRightboard() {
		$('#section').canvasAlert({content:'答对'});
	}
	function popWrongboard() {
		$('#section').canvasAlert({content:'答错'});
	}	
	
	//
	var vmCollection = new Vue({
		el : "#collection",
		data : {
				info: {
					list: []
				},
				lately: {
					list: []
				},
				pageinfoNative: {
					list: []
				},
				levelList:["25k", "20k", "15k", "10k", "5k", "2k", "1k", "1D", "2D", "3D", "4D", "5D"],//等级列表
				knowledgeList:'',//知识点列表
		},
		mounted:function(){
			this.getCollectChess();
		},
		methods : {
			//获取收藏 2:棋谱  1:习题    
			getCollectChess : function(pageNum,type,pageSize) {
				var param =  formSerialize('#searchStock');
				param.pageNum = pageNum||'1';
				param.type = type||'2';
				param.pageSize = pageSize||'5';
				param.uid = uid;
	  			param.token = token;
				$.ajax({
					type : "POST",
					url : http+"/gobangteach/QuestionbankController/getCollectChess",
					data : $.param(param),
					dataType : 'json',
					success : function(data) {
						console.log("vmCollection", data);
						vmCollection.info = data.data;
					},
					error : function(XMLHttpRequest, data) {
						layerTC("获取房间信息失败");
					}
				});
			},
			//获取最近收藏 2:棋谱  1:习题 
			getLatelyChess : function(pageNum,type,pageSize) {
				var param = {
						pageNum:pageNum,
						pageSize:pageSize,
						type:type,
						token:token,
						uid:uid
				};
				param.pageNum = pageNum||'1';
				param.type = type||'2';
				param.pageSize = pageSize||'5';
				$.ajax({
					type : "POST",
					url : http+"/gobangteach/QuestionbankController/getLatelyChess",
					data : $.param(param),
					dataType : 'json',
					success : function(data) {
						console.log("vmCollection", data);
						vmCollection.lately = data.data;
					},
					error : function(XMLHttpRequest, data) {
						layerTC("获取房间信息失败");
					}
				});
			},
			//获取最近
			nativeChess : function(pageNum) {
				var param = {};
				param.pageNum = pageNum||'1';
				param.pageSize = '5';
				param.uid = uid;
	  			param.token = token;
				$.ajax({
					type : "POST",
					url : http+"/gobangteach/classroom/listUploadFile",
					data : $.param(param),
					dataType : 'json',
					success : function(data) {
						console.log("loadChessBEN", data);
						playEnd();
						vmCollection.pageinfoNative = data.data;
					},
					error : function(XMLHttpRequest, data) {
						layerTC("获取房间信息失败");
					}
				});
			},
			//双击 最近收藏习题缩略图
			loadQuestion : function(questionId,flag) {
				if(flag==0){
					loadQuestionDemo(questionId);
					layer.closeAll();
				}else{
					loadQuestionDemo(questionId);
				}
				//addLatelyChess({type:1,chessId:questionId});
				
			},
			//双击 最近收藏棋谱
			loadChess : function(chessId) {
				$.ajax({
					type : "POST",
					url : http+"/gobangteach/classroom/loadChess",
					data : "chessid=" + chessId+"&token="+token+"&uid="+uid,
					dataType : 'json',
					success : function(data) {
						console.log("loadChess", data);
						window.currentGameId = data.data.chessid;
						playEnd();
						layer.closeAll();
						enterGame(game,data.data);
						//addLatelyChess({type:2,chessId:chessId});
					},
					error : function(XMLHttpRequest, data) {
						layerTC("获取房间信息失败");
					}
				});
			},
			dele:function(id,el){
				var _el = el.currentTarget;
				var $this =this;
				layerTC('你确定要删除该棋谱吗？',function(){
					deleUpFile(id)
				});
				
				function deleUpFile(id){
					$.ajax({
						type:'post',
						url:http+'/gobangteach/classroom/deleteUploadFileById',
						data:{
							id:id,
							uid:uid,
							token:token
						},
						success:function(data){
							//$(_el).parent().parent().remove();
							$this.nativeChess();
						}
					})
				}
			},
			loadFile : function(id) {
				var param = {};
				param.uploadFileId = id;
				param.uid = uid;
	  			param.token = token;
				$.ajax({
					type : "POST",
					url : http+"/gobangteach/classroom/loadUploadFile",
					data : $.param(param),
					dataType : 'json',
					success : function(data) {
						console.log("loadUploadFile", data);
						window.currentGameId = data.data.chessid;
						layer.closeAll();
						enterGame(game,data.data);
					},
					error : function(XMLHttpRequest, data) {
						layerTC("获取房间信息失败");
					}
				});
			},
			formatTime : function(time) {
				return DateFormat.format(new Date(time), 'yyyy-MM-dd hh:mm:ss');
			},
			refresh: function(type,pageinfo,pageSize,searchType) {
				if(searchType=='0'){
					if (pageinfo.hasNextPage) {
						vmCollection.getLatelyChess(pageinfo.nextPage,type,pageSize);
	                } else {
	                	vmCollection.getLatelyChess(pageinfo.prePage,type,pageSize);
	                }
				}else if(searchType=='1'){
					if (pageinfo.hasNextPage) {
						vmCollection.getLatelyChess(pageinfo.nextPage,type,pageSize);
	                } else {
	                	vmCollection.getLatelyChess(pageinfo.prePage,type,pageSize);
	                }
				}
				
			},
            formatResult:function (res){
                 return res == 1 ? "黑棋胜" : res == 2 ? "白棋胜" : res == 3 ? "平局"  : "无效对局";
            },
			refsh: function(pageNum) {
				this.getCollectChess(pageNum);
			},
			selectLevel:function(){
				var $this = this;
				$.ajax({
					type:'get',
					url:http+'/gobangteach/classroom/listTypeLevel?questionType='+$('#questionType').val(),
					success:function(data){
						$this.levelList=data.data;
						if($('#questionType').val()==0){
							$this.levelList=["25k", "20k", "15k", "10k", "5k", "2k", "1k", "1D", "2D", "3D", "4D", "5D"];
						}
						setTimeout(function(){
							$this.getAbility();
						},100);
					}
				})
			},
			selectStock:function(){
				this.getAbility()
			},
			//请求知识点
			getAbility:function(){
				var $this = this;
				$.ajax({
					type:'get',
					url:http+'/gobangteach/classroom/listKnowledge?'+$('#searchStock').serialize(),
					success:function(data){
						$this.knowledgeList=data.data;
					}
				})
			},
			itemAc:function(el){
				var _el = el.currentTarget;
				$(_el).addClass('active').siblings('img').removeClass('active');
				var dataId = $(_el).attr('data-id');
				var acId = $("#btnClt").attr('data-id');
				if(dataId==acId){
					$("#btnClt").addClass('active');
				}else{
					$("#btnClt").removeClass('active');
				}
			},
			//收藏
			collect:function(el){
				var id = $("#content-body img.active").attr('data-id');
				console.log(id)
				if(id==undefined){
					layerTC('请先选择习题')
				}else{
					layerTC('你确定要取消收藏吗？',function(){
						postCollect(id);
						$("#btnClt").addClass('active').attr('data-id',id);
					})
				}
				
				function postCollect(id){
					
					$.ajax({
						type:'post',
						url:'/gobangteach/QuestionbankController/collectChess',
						data:{
							type:1,
							chessId:id,
							collect:2
						},
						success:function(data){
							//$("#btnClt").addClass('active').attr('data-id',id);
						}
					})
				}
				
			},
			formatTime : function(time) {
				return DateFormat.format(new Date(time), 'yyyy-MM-dd hh:mm:ss');
			}
		}
		
	});
	function playEnd(){
		if(game.config.isTry == 1){
			game.endTry();
		}
		$('.discuss').removeClass('active').text('研讨');
	}
	//棋谱
	var vmManualModal = new Vue({
		el : "#manualModal",
		data : {
				pageinfo: {
					list: []
				},
				pageinfoOther: {
					list: []
				}
		},
		methods : {
			loadChess : function(chessId) {
				$.ajax({
					type : "POST",
					url : http+"/gobangteach/classroom/loadChess",
					data : "chessid=" + chessId+"&token="+token+"&uid="+uid,
					dataType : 'json',
					success : function(data) {
						console.log("loadChess", data);
						window.currentGameId = data.data.chessid;
						layer.closeAll();
						addLatelyChess({type:2,chessId:chessId,	token:token,uid:uid});
						playEnd();
						enterGame(game,data.data);
					},
					error : function(XMLHttpRequest, data) {
						layerTC("获取房间信息失败");
					}
				});
			},
			formatTime : function(time) {
				return DateFormat.format(new Date(time), 'yyyy-MM-dd hh:mm:ss');
			},
			page: function (pageNum) {
                var pageinfo = this.pageinfo;
                if($("#manualModal .modal-header div").eq(0).hasClass('active')){
                	loadManual({
	                	userId:uid,
	                	pageNum: pageNum,
						name:dataMsg[0].value,
                	});
                }else{
                	loadManual({ 
	                	pageNum: pageNum,
						name:dataMsg[0].value,
                	});
                }
            },
            formatResult:function (res){
                 return res == 1 ? "黑棋胜" : res == 2 ? "白棋胜" : res == 3 ? "平局"  : "无效对局";
            }
		}
	});
	var vm = new Vue({
		el : "#libraryModal",
		data : {
			pageinfo: {
				list: []
			},
            listKnowledge: {
                list: ''
            },
            
		},
		methods : {
			loadQuestion : function(questionId,flag) {
				if(flag==0){
					loadQuestionDemo(questionId);
					layer.closeAll();
				}else{
					loadQuestionDemo(questionId);
				}
				addLatelyChess({type:1,chessId:questionId,	token:token,uid:uid});
			},
			refresh: function(pageNum) {

				var pageinfo = this.pageinfo;
				if (pageinfo.hasNextPage) {
					listQuestions({pageNum: pageNum});
                } else {
                	listQuestions({pageNum: pageNum});
                }
			}
		}
		
	});
	function addLatelyChess(condition){
		$.ajax({
			url : http+'/gobangteach/QuestionbankController/addLatelyChess',
			type : "POST",
			dataType : "json",
			data : $.param(condition),
			success : function(data) {
				console.log("最近使用", data);
			}
		});
	}
	//棋谱
	function loadManual(condition) {
		var params = condition || {};
		params.pageNum = params.pageNum || 1;
		params.pageSize = 5;
		params.uid = uid;
	  	params.token = token;
		$.ajax({
			url : http+'/gobangteach/classroom/listChesses',
			type : "POST",
			dataType : "json",
			data : $.param(params),
			success : function(data) {
				console.log("list chesses", data)
				vmManualModal.pageinfo = data.data;
			}
		});
	}
	//习题
	function listQuestions(condition) {
        var params = condition || {};
        params.pageNum = params.pageNum || 1;
		 var questionType = $("#gobangType").val();
		 var level = $("#gobangLevel option:selected").text()
		 var QuestionName = $("#knowledge option:selected").text();
		 params = {
		 		pageNum:1,
				pageSize:12,
				questionType:questionType, //类型
				level:level,   //棋力等级
				QuestionName:QuestionName, //知识点
		 }
		 params.uid = uid;
	  params.token = token;
        $.ajax({
            url: http+'/gobangteach/classroom/searchQuestions',
            type: "POST",
            timeout: 5000,
            dataType: "json",
            data: $.param(params),
            success: function (data) {
                console.log("----------------------");
                console.log(data.data);
                if(data.data.list.length==0){
                	$("#content-body,.modal-footer,aside").hide();
        			$("#noDetail").show();
                }else{
                	$("#content-body,.modal-footer,aside").show();
        			$("#noDetail").hide();
                    vm.pageinfo= data.data;
                    console.log('l----;',listQuestions)
                }
            }
        });
    }
	function clearChessboard() {
		game.switchChess(window.currentSize);
	}
	function changeChessboard() {
		if(document.getElementById("chessSize9").classList.contains('active')==true) {
			window.currentSize = 9;
		} else {
			window.currentSize = 19;
		}
		layer.closeAll();
		clearChessboard();
	}
	function loadQuestionDemo(questionId){
		$.ajax({
			type : "POST",
			url : http+"/gobangteach/classroom/loadQuestionDemo",
			data : "questionid=" + questionId+"&token="+token+"&uid="+uid,
			dataType : 'json',
			success : function(data) {
				console.log("loadQuestion", data);
				var gameInfo = JSON.parse(data.data.questionbank.chessdata);
				//更新棋谱
				window.currentSize = gameInfo.roadX;
				game.switchChess(window.currentSize);
				game.svg = gameInfo.listAction;
				game.config.first = 0;
			},
			error : function(XMLHttpRequest, data) {
				layerTC("获取习题失败");
			}
		});
	}
	
	//listQuestions()
	
	//加载题库
	$(".library").click(function(){
		layerOpen({area: ['6.9rem', '10.6rem'],skin:'layerOpen tk',content: $("#libraryModal")});
	});
	//changeChessboardModal 切换棋盘
	$(".changeChessboardModal").click(function(){
		layerOpen({area: ['6rem', '4.27rem'],skin:'layerOpen tk',content: $("#changeChessboardModal")});
	});
	//manualModal 棋谱
	$(".manualModal").click(function(){
		layerOpen({area: ['6.9rem', '10.2rem'],skin:'layerOpen tk',content: $("#manualModal")});
		loadManual({userId:''});
	});
	//清空 clearboard
	$(".clearboard").click(function(){
		layerTC('您确定清空棋盘吗?',function(){clearChessboard()},layer.closeAll())
	});
	//collection 收藏
	$(".collection").click(function(){
		layerOpen({area: ['6.9rem', '10.6rem'],skin:'layerOpen tk',content: $("#collection")});
		var $node = $("#collection .swiper-slide").eq(0).find('div');
		if($node.eq(0).hasClass('active')){
			vmCollection.getCollectChess('1','1','12');
		}else if($node.eq(1).hasClass('active')){
			vmCollection.getLatelyChess('1','2','5');
		}else if($node.eq(2).hasClass('active')){
			vmCollection.getLatelyChess('1','1','12');
		}
	});
	$(".discuss").click(function(){
		if($(this).hasClass('active')){
			
			$(this).removeClass('active').text('研讨');
			showLot();
			//开始试下
			game.endTry();
		}else{
			$(this).addClass('active').text('结束研讨');
			//结束试下
			game.startTry();
		}
	});
 	//加载题库
	function loadQuestionsStore(condition) {
         var params = condition || {};
         params.pageNum = params.pageNum || 1;
		 var questionType = $("#gobangType").val();
		 var level = $("#gobangLevel option:selected").text();
		 var QuestionName = $("#knowledge option:selected").text();
		 params = {
		 		pageNum:1,
				pageSize:12,
				questionType:questionType, //类型
				level:level,   //棋力等级
				QuestionName:QuestionName, //知识点
				token:token,
				uid:uid
		 }
         $.ajax({
             url: http+'/gobangteach/classroom/searchQuestions',
             type: "POST",
             timeout: 5000,
             dataType: "json",
             data: $.param(params),
             success: function (data) {
                 console.log("----------------------");
                 console.log(data.data);
                 if(data.data.list.length==0){
                 	$("#content-body,.modal-footer,aside").hide();
         			$("#libraryModal .noDetail").show();
                 }else{
                	 $("#content-body,.modal-footer,aside").show();
                	 $("#libraryModal .noDetail").hide();
	                // vm.loadQuestion(data.data.list[0].id);
	                 vm.pageinfo = data.data;
                 }
             }
         });
     } 
	function selectType($this){
		console.log($this.val())
    	if($this.val()==0&&$this.attr('id')=='gobangType'){
    		$("#knowledge").attr('disabled','disabled');
    	}else if($this.val()!=0&&$this.attr('id')=='gobangType'){
    		$("#knowledge").attr('disabled',false);
    	}
		selectListLevel();
    }
	function selectListLevel(){
   	 	$.ajax({
			type:'get',
			url:http+'/gobangteach/classroom/listTypeLevel?questionType='+$('#gobangType').val()+"&token="+token+"&uid="+uid,
			success:function(data){
				console.log(data.data);
				var data = data.data;
				var str = '';
				for(var i=0;i<data.length;i++){
					str +='<option>'+data[i]+'</option>';
				}
				$("#gobangLevel").html(str);
				setTimeout(function(){
					selectListKnowledge();
				},100);
				
			}
		})
     }
	function selectStock(){
		selectListKnowledge();
    }
    function selectListKnowledge(val){
    	var questionType = $("#gobangType option:selected").attr('value');
		var level = $("#gobangLevel option:selected").text();
		var params = {
				questionType:questionType, //类型
				level:level,   //棋力等级
				token:token,
				uid:uid
		}
		$.ajax({
    		type: "GET",
    		url: http+"/gobangteach/classroom/listKnowledge",
    		data : $.param(params),
    		dataType: 'json',
    		success: function(data) {
    			console.log('知识点',data);
    			vm.listKnowledge = data.data;
    			console.log(data.data)
    		},
    		error: function(XMLHttpRequest,data) {
    			layerTC("查询知识点失败");
    		}
    	});
    }
	
	//棋谱   条件搜锁
	$('#searchqq .btn-default').click(function(){
		window.dataMsg = $('#searchqq').serializeArray();
		console.log(dataMsg)
		var user ='';
		$.ajax({
			url : http+'/gobangteach/classroom/listChesses',
			type : "POST",
			dataType : "json",
			data : {
				name:dataMsg[0].value,
				userId:user,
				pageNum:1,
				pageSize:5,
				token:token,
				uid:uid
			},
			success : function(data) {
				console.log("list 88888", data)
				vmManualModal.pageinfo = data.data;
			}
		}); 
		
		
	})

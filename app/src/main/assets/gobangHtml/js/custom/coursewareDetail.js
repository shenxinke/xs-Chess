function coursewareDetail(Vue){
	//01 加载棋盘列表
	if(readCookie('historyJobTime')) {
		loadLatelyJob({
			createTime: readCookie('historyJobTime')
		});
		delCookie('historyJobTime');
	} else {
		loadLatelyJob();
	}

	var vm = new Vue({
		el: "#questionStore",
		data: {
			pageinfo: {},
			arrTitle: {
                     msg: [
                           '01 围棋礼仪',
                           '02 棋盘棋子',
                           '03 棋子的气',
                           '04 紧气提子',
                           '05 打吃,逃子',
                           '06 双方互相打吃',
                           '07 虎口',
                           '08 禁入点（禁着点）！',
                           '09 打劫',
                           '10 打二还一和打多还一',
                           '11 连接和切断',
                           '12 一、二线吃子的秘密！',
                           '13 双打吃',
                           '14 征吃',
                           '15 门吃',
                           '16 抱吃',
                           '17 枷吃',
                           '18 扑与倒扑',
                           '19 接不归',
                           '20 利用对方缺陷逃子',
                           '21 可逃之子与应弃之子',
                           '22 两眼活棋',
                           '23 基础死活常型(一)',
                           '24 基础死活常型(二)',
                           '25 杀棋基础',
                           '26 获取基础',
                           '27 布局基础(一)',
                           '28 布局基础(二)',
                           '29 对杀基础(一)',
                           '30 对杀基础(二)',
                           '31 综合吃子(一)',
                           '32 综合吃子(二)',
                           '33 好形和坏形',
                           '34 官子入门',
                           '35 胜负计算'
                           ],
				title: '',
			},
			description:'111',
			judge:false
		},
		methods: {
			//根据棋谱id 加载棋盘
			loadMyJob: function(questionId) {
				$.ajax({
					type: "POST",
					//contentType: "application/json",
					url: http+"/gobangteach/classroom/loadQuestion",
					data: {
						questionid : questionId,
						uid:uid,
						token:token
					},
					
					dataType: 'json',
					success: function(data) {
						console.log("棋盘信息", data);
						window.currentGameId = data.data.chessid;
						window.quesionID = questionId;

						$('.imgBox').removeClass('qipuActive')
						$('.imgBox[data-id=' + window.quesionID + ']').addClass('qipuActive');
						eve.f("HandlerSignInReq", uid)();
						console.log('---[', 'ssss')
						setTimeout(function(){
							eve.f("HandlerHideStep")();
						}, 100);
						vm.description = data.data.questionbank.titlerequs===null||data.data.questionbank.titlerequs===''?
								data.data.questionbank.description.split('_')[0]:
								data.data.questionbank.titlerequs;
						//棋局开始提示信息
						$('#canvasBox').canvasAlert({
							content:data.data.questionbank.titlerequs===null||
							data.data.questionbank.titlerequs===''?
							data.data.questionbank.description.split('_')[0]:
							data.data.questionbank.titlerequs
						})
					},
					error: function(XMLHttpRequest, data) {
						alert("获取房间信息失败");
					}
				});
			},
			//分页
			page: function(pageNum) {
				var pageinfo = this.pageinfo;
				console.log(pageinfo)
				if(pageinfo.hasNextPage) {
					loadLatelyJob({
						pageNum: pageinfo.nextPage
					});
				} else {
					loadLatelyJob({
						pageNum: pageinfo.firstPage
					});
				}
			},
			formatTime: function(time) {
				return DateFormat.format(new Date(time), 'yyyy-MM-dd hh:mm:ss');
			},
			aswRight:function(){
				$('.imgBox[data-id=' + window.quesionID + ']').removeClass('wrong').addClass('right');
				layerTC({
					skin:'layerRight',
					closeBtn:0,
				}, function() {
					window.next()
				});
			},
			aswWrong:function(){
				$('.imgBox[data-id=' + window.quesionID + ']').removeClass('right').addClass('wrong');
				layerTC({
					skin:'layerWrong',
					closeBtn:0,
				}, function() {
					window.next()
				});
			}
		},
		updated: function() {
			//修改棋谱列表标题
			this.arrTitle.title = this.arrTitle.msg[jsonlink().classId - 1]
		},
		mounted:function(){
			if(jsonlink().classId==8){
				this.judge = true
			}
		}

	});

	function loadLatelyJob(condition) {
		var params = condition || {};
		params.pageNum = params.pageNum || 1;
		params.pageSize = 15;
		params.classId = Tool.queryString("classId");
		params.uid=uid;
		params.token=token;
		$.ajax({
			url: http+'/gobangteach/QuestionbankController/getQuestionbankByClassId',
			type: "POST",
			dataType: "json",
			data: $.param(params),
			success: function(data) {
				console.log('棋谱列表', data.data)
				if(data.data.list.length == 0) {
					//如果没有列表则加载一个随机棋盘
					$("#content-body,.content-footer").hide();
					$("#noDetail").show();
					$.ajax({
						type: "POST",
						url: http+"/gobangteach/classroom/createGame",
						data: "size=" + 19+"&token="+token+"&uid="+uid,
						dataType: 'json',
						success: function(data) {
							console.log("ClearChessboard", data);
							window.currentGameId = data.data.chessid;
							eve.f("HandlerSignInReq", uid)();
						},
						error: function(XMLHttpRequest, data) {
							alert("获取房间信息失败");
						}
					});
				} else {
					$('.imgBox').removeClass('wrong right');
					vm.loadMyJob(data.data.list[0].id);
					vm.pageinfo = data.data;
				}
			}
		});
	}

	// 棋盘加载完毕通知
	eve.on("AppOnEnter", function() {
		eve.f("SocketStart")();
		console.log(1)
	});
	eve.on("SocketOpen", function() {
		console.log(2)
		console.log("SocketOpen");
		eve.f("HandlerSignInReq", uid)();
	});
	//落子声音
	eve.on("HandlerAction", function(takeBlack, takeWhite) {
		$("#stepmusic")[0].play();
           $("#aswError").attr('src','');
           $("#aswRight").attr('src','');
           $("#aswError")[0].play();
           $("#aswRight")[0].play();
	});
	eve.on("SignInResponse", function() {
		console.log(3)
		//layer.close(layerload);
		console.log("SignInResponse, gameid is ", window.currentGameId);
		eve.f("HandlerEnterGame", window.currentGameId)()
		//;

	});

	eve.on("_ConfirmTouchEnded_", function(p1, p2) {
		console.log(4)
		eve.f(p1, p2)();
	});

	/**
	 *  游戏结束
	 */
	eve.on("_NotifyGameOver_", function(status, blackSum, whiteSum) {

		var info;
		if(status === 12) {
			layerTC({
				content:'你再好好想想哦~',
				skin:'layerTC skin1'
			}, function() {
				next()
			})
            document.location.href = "myschema://go?a=16&music=aswError";
			$('.imgBox[data-id=' + window.quesionID + ']').removeClass('right').addClass('wrong');
			updateErrorQuestionsStates(1)
			addRightWrong('2')
		} else if(status === 13) {
			layerTC({
				content:'你真棒！答对了！',
				skin:'layerTC skin1'
			}, function() {
				next()
			})
           document.location.href = "myschema://go?a=16&music=aswRight";
			$('.imgBox[data-id=' + window.quesionID + ']').removeClass('wrong').addClass('right');
			addRightWrong('1')
		} else {
			swal({
				text: "游戏结束 " + status,
				button: "确认"
			});
		}

		/*function next() {
			var oId = $('.imgBox[data-id=' + window.quesionID + ']').next().attr('data-id');
			if(oId) {
				vm.$options.methods.loadMyJob(oId)
			}
		}*/
		//更新题_____
		function addRightWrong(sign) {
			var param = {
					chessId: window.quesionID,
					rightWrong: sign,   //答题的对错情况   1 代表对 2 代表错
					source:4 ,           //题来源    1 题库 2.错题本3作业4课件
           uid:uid,
           token:token
			}
			console.log(param)
			$.ajax({
				url: http+'/gobangteach/QuestionbankController/addRightWrong',
				type: "POST",
				dataType: "json",
				data: $.param(param),
				success: function(data) {
					console.log("ClearChessboard", data);
				},
				error: function(XMLHttpRequest, data) {
					console.log("更新该题失败");
				}
			});
		}
	});
	window.next = function() {
		var oId = $('.imgBox:not(.right,.wrong)').attr('data-id');
		if(oId) {
			vm.loadMyJob(oId)
		}
	}
	//更新题
	function updateErrorQuestionsStates(states) {
		var param = {
			states: states,
			quesionID: window.quesionID,
			uid:uid,
			token:token
		}
		console.log(param)
		$.ajax({
			url: http+'/gobangteach/classroom/updateErrorQuestionsStates',
			type: "POST",
			dataType: "json",
			data: $.param(param),
			success: function(data) {
				console.log("ClearChessboard", data);

			},
			error: function(XMLHttpRequest, data) {
				//alert("更新该题失败");
			}
		});
	}



	
}

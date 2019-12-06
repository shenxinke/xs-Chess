var deferTime = 5000;// 好友邀请点击限制，5秒
var showTimeNum = 100;// 阴影进度条 10秒
var submited = false;// 好友邀请对弈
var savedGameId; // 当前对局的棋局ID
$.ajax({
	type : "GET",
	url : http+"/gobangteach/UserBaseController/getUserFullInfoById?userid="+uid+"&token="+token+"&uid="+uid,
	success : function(data) {
		var base = data.data.base;
		var detail = data.data.detail;
					var level ="";
					if( detail.level<26 && detail.level>0){
						level = 26-detail.level+"K";
					}else if( detail.level>=26){
						level = detail.level-25+"D";
					}else{
						level = "尚未定级";
					}
					setCookie('level', level);
			setCookie('userid',base.id);
			setCookie('usertype', base.usertype);
			setCookie('username', base.username);
			if(data.error.returnCode==='10048' && !$('.login')[0] && !$('.loginContent')[0]){
			
				layerTC('用户登入过期请重新登入', function() {
					document.location.href = "myschema://go?a=2"
				}, function() {
					document.location.href = "myschema://go?a=2"
				}, function() {
					document.location.href = "myschema://go?a=2"
				})
			}
	}
});
function loadingChess(time){
    $('#optLoading').css('display','block')
    var timeNum = time*1000;
    var num = timeNum/100;
    var step = 100/num;
    var index =1
    var timer = setInterval(function() {
                            num --;
                            
                            if(num<=0){
                            clearInterval(timer)
                            if(window.checkGameInfo) {
                            
                            checkGameInfo(true);
                            }
                            }else{
                            index++;
                            $(".loadingBar img").css('left',(-100+step*index)+'%');
                            $(".loadingBar .timeNum").text(index)
                            console.log('ss',step*index)
                            }
                            }, 100)
    
    $('#optLoading button').click(function(){
                                  clearInterval(timer)
                                  $('#optLoading').hide();
                                  document.location.href = "myschema://go?a=1";
                                  })
    
}
//$(document).on('click','.layui-layer-ico',function(){
//    document.location.href = "myschema://go?a=1";
//})
/**  
 * 创建棋局
 */
function createHome(info) {
	// 创建对局房间
	if(info.ruletype==null||info.ruletype=='null'){
		info.ruletype = 0;
	}
	//info.ruletype==null?info.ruletype:0;
	$.ajax({
		type : "POST",
		url :  http+"/gobangteach/ChessController/creatFirsHome_ios",
		data : $.param({
			'friendsId' : info.userid,
			'ruletype' : info.ruletype,
			'size' : info.size,
			'blackuserid' : info.blackuserid,
			'whiteuserid' : info.whiteuserid,
			'token':token,
			'uid':uid
		}),
		dataType : 'json',
		success : function(data) {
			if (data.error.returnCode == 0) {
				//layerTC("房间创建成功,准备进入房间。。。");
				var pushMsg = "{\"code\":\"1\",\"info\":{\"userTyp\":\""
						+ data.data + "\"}}";
				var request = Base.encode(pushMsg);
				pushToUserMessage("{'users':['" + info.userid
						+ "'],'pushMsg':'" + request + "'}");
           
				setTimeout(function(){
					document.location.href = "myschema://go?a=3";
				},1000);
			}
           else if (data.error.returnCode == 10048){
           
           layerTC("创建房间失败：" + data.error.returnMessage, function() {
                   document.location.href = "myschema://go?a=2"
                   }, function() {
                   document.location.href = "myschema://go?a=2"
                   }, function() {
                   document.location.href = "myschema://go?a=2"
                   });
           }
           else {
           
           layerTC("创建房间失败：" + data.error.returnMessage, function() {
                   document.location.href = "myschema://go?a=1"
                   }, function() {
                   document.location.href = "myschema://go?a=1"
                   }, function() {
                   document.location.href = "myschema://go?a=1"
                   });
			}
		},
		error : function(XMLHttpRequest, data) {
           layerTC("创建房间失败：系统错误！", function() {
                   document.location.href = "myschema://go?a=1"
                   }, function() {
                   document.location.href = "myschema://go?a=1"
                   }, function() {
                   document.location.href = "myschema://go?a=1"
                   });
		}
	});
}
// 邀请对弈
function randomBattle() {
	console.log("随机对战");
	var userid = readCookie('username');
	console.log(userid)
	
	if(userid=='' || userid == null){
		//$("#myModal-login").modal('show');
		//$("#modalTeaching").modal('hide');
	}else if(readCookie('level')=='尚未定级'){
		/*$("#hintDY").modal('toggle').find(".btn-primary").click(function(){
			$("#hintDY").modal('toggle');
			window.location.href = "/gobangteach/testTitle.jsp";
		})*/
		
		//
        layerTC('在您对弈前,请让我们了解您的棋力！',function(){
                document.location.href = "myschema://go?a=4";
                },function(){
			document.location.href = "myschema://go?a=1";
		},function(){
			document.location.href = "myschema://go?a=1";
		});
	}else{
		// 显示
		$.ajax({
			url : http+ '/gobangteach/ChessController/randomBattle',
			type : "POST",
			timeout : 5000, // 单位毫秒
			contentType : "application/json",
			dataType: 'json',
			data:"token="+token+"&uid="+uid,
			beforeSend : function(XMLHttpRequest) {
				loadingChess(10)
			},
			success : function(data) {
				if (data.error.returnCode == 0 || data.error.returnCode == 2) {
					// hideModal();
					// layerTC("创建成功！");
				} else if (data.error.returnCode == 10054) {
					// 进入教师界面
//                     window.location.href = "/gobangteach/quickgame.jsp";
				} else if (data.error.returnCode == 10048) {
					//$("#myModal-login").modal("show");
				} else {
					//layerTC("系统错误: " + data.error.returnUserMessage);
				}
			},
			error : function(XMLHttpRequest, textStatus) {
				//layerTC("服务器错误！！！");
			}
		});
	}
	
}

// 发起方弹出规则选项
function instantFight(user) {
	var userId = user.userId;
	var usertype = readCookie("usertype");
	$.ajax({
		type: "POST",
		url: http+ "/gobangteach/ChessController/getGameInfoByUserId",
		dataType: 'json',
		data:"token="+token+"&uid="+uid,
		success: function(data) {
			console.log("GetGameInfoByUserId ", data, usertype);
			if (data.error.returnCode==0) {
				if(usertype != 1) {
					
           layerTC("您所在用户组不允许多人对弈",function(){
                   document.location.href = "myschema://go?a=1";
                   },function(){
                   document.location.href = "myschema://go?a=1";
                   },function(){
                   document.location.href = "myschema://go?a=1";
                   });
					return;
				}
			}
			$.ajax({
				type : "GET",
				url : http+ "/gobangteach/ChessController/getGameInfoByOtherUserId",
				dataType : 'json',
				data:"userId=" + userId+"&token="+token+"&uid="+uid,
				success : function(data) {
					console.log(data);
					if (data.error.returnCode == 0) {
                        layerTC('该用户已在对战中。是否观战？',function(){
							if(Tool.isArray(data.data)) {
								document.location.href = "myschema://go?a=3&targetGameId=" + data.data[0].gameId;
							} else {
								document.location.href = "myschema://go?a=3&targetGameId=" + data.data.gameId;
							}
						},function(){
							document.location.href = "myschema://go?a=1";
                                },function(){
                                document.location.href = "myschema://go?a=1";
                                });
                   }  else if (data.error.returnCode == 1) {
                   layerTC("邀请失败。",function(){
                           document.location.href = "myschema://go?a=1";
                           },function(){
                           document.location.href = "myschema://go?a=1";
                           },function(){
                           document.location.href = "myschema://go?a=1";
                           });
                   }
                   else if (data.error.returnCode == 10056) {
                   layerTC("该用户不存在。",function(){
                           document.location.href = "myschema://go?a=1";
                           },function(){
							document.location.href = "myschema://go?a=1";
						},function(){
							document.location.href = "myschema://go?a=1";
						});
					} else if (data.error.returnCode == 10053) {
						$("#fight_userid").val(userId);
						$("#instant_step").val(1);
						
						$("#instant_name").html(user.nick);
						$("#instant_level").html(user.level);
						
						$("#instant_state").html("将发起友谊赛，是否发起？");

						$("#instant_confirm").bind("click", function() {
							
							confirmRule();
							changeRule();
						});
						$("#instant_modify").bind("click", function() {
							changeRule();
						});
						$("#instant_refuse").bind("click", function() {
							document.location.href = "myschema://go?a=1";
							$(".invitationFriend").find('select').attr('disabled', false);
						});
						$("#instant_modify").hide();
						layerOpen({area: ['7.38rem', '4.89rem'],skin:'layerOpen invitationModal',id:'sss',content: $("#invitation"),cancel:function(){document.location.href = "myschema://go?a=8";}});
					}
				}
			});
		}
	});
}
// checked = 0 - 未确认规则，须先确认规则才能点击， = 1 - 已确认规则，发起确认等待对方确认
function confirmRule(checked, type) {
	console.log("confirm rule", checked, type);
	var userId = $("#fight_userid").val();
	var rule_checked = $("#rule_checked").val();
	
	if(checked) {
		var longUserId = uid;
		var longUserNm = readCookie('username');

		var code = "2";
		var info = {
			userid : longUserId,
			usernm : longUserNm,
			type : type,
			state : 1
		};

		var pushMsg = {
			code : code,
			info : info
		};
		var request = Base.encode(JSON.stringify(pushMsg));
		// 发送消息
		pushToUserMessage("{'users':['" + userId + "'],'pushMsg':'"+ request + "'}");
		$("#instant_state").html("已确认规则，等待对方同意。");
	} else {
		$("#instant_state").html("请先确认规则，并等待对方同意。");
	}
}

function onRuleTypeChanged() {
	var ruletype = $("#fight_type_1").val();
	if(ruletype == 2 || ruletype == 3) {
		$("#fight_type_2").val('2');
	}
	if(ruletype == 3||ruletype == 2){
		$("#fight_type_3 option").eq(0).prop("selected",true);
		$("#fight_type_3").prop('disabled',true);
	}else{
		$("#fight_type_3").prop('disabled',false);
	}
}

function changeRule() {
	var userId = $("#fight_userid").val();

	var types = {
		type1 : $("#fight_type_1").val(),
		type2 : $("#fight_type_2").val(),
		type3 : $("#fight_type_3").val(),
		type4 : $("#fight_type_4").val(),
		type5 : $("#fight_type_5").val(),
		type6 : $("#fight_type_6").val(),
		type7 : $("#fight_type_7").val()
	};

	var longUserId = uid;
	var longUserNm = readCookie('username');

	var code = "2";
	var info = {
		userid : longUserId,
		usernm : longUserNm,
		type : types,
		state : 2
	};

	var pushMsg = {
		code : code,
		info : info
	};
	var request = Base.encode(JSON.stringify(pushMsg));
	// 发送消息
	pushToUserMessage("{'users':['" + userId + "'],'pushMsg':'" + request + "'}");
	$("#instant_state").html("已向对方发起规则确认，请等待对方反馈。");
	$(".invitationFriend").find('select').attr('disabled', true);
}
function receiveChangeRule(info) {
    document.location.href = "myschema://go?a=200";
	console.log("receiveChangeRule", info);
	$("#instant_confirm").text('同意对弈');
	$("#instant_name").html(info.usernm);
	$("#instant_modify").show();
	$("#fight_userid").val(info.userid);
	$("#fight_type_1").val(info.type.type1);
	$("#fight_type_2").val(info.type.type2 == 1 ? 2 : 1);
	$("#fight_type_3").val(info.type.type3);
	$("#fight_type_4").val(info.type.type4);
	$("#fight_type_5").val(info.type.type5);
	$("#fight_type_6").val(info.type.type6);
	$("#fight_type_7").val(info.type.type7);
	$(".invitationFriend").find('select').attr('disabled', false);
	$("#instant_state").html("对方发起友谊赛规则，是否接受？");

	// confirm 确认规则，等待对方确认及开始对弈
	// modify 改变规则，等待对方确认或修改规则
	// refuse 取消
	$("#instant_confirm").unbind("click");
	$("#instant_modify").unbind("click");
	$("#instant_refuse").unbind("click");

	$("#instant_confirm").bind("click", function() {
		confirmRule(1, info.type);
	});
	$("#instant_modify").bind("click", function() {
		changeRule();
	});
	$("#instant_refuse").bind("click", function() {
		
		$(".invitationFriend").find('select').attr('disabled', false);
		var code = "2";
		var longUserId = uid;
		var longUserNm = readCookie('username');
		var info = {
			userid : longUserId,
			usernm : longUserNm,
			state : 3
		};

		var pushMsg = {
			code : code,
			info : info
		};
		var request = Base.encode(JSON.stringify(pushMsg));
		// 发送消息
		pushToUserMessage("{'users':['" + $("#fight_userid").val() + "'],'pushMsg':'"+ request + "'}");
		document.location.href = "myschema://go?a=1";
	});
    layerOpen({area: ['7.38rem', '4.89rem'],skin:'layerOpen invitationModal',id:'sss',content: $("#invitation"),cancel:function(){document.location.href = "myschema://go?a=8";}});
}
function receiveConfirmRule(info) {
	$("#invitation-modify").show();
	$("#instant_confirm").text('同意对弈');
	$("#instant_state").html("对方确认友谊赛规则，是否接受？");
	$("#instant_modify").show();
	// confirm 确认规则，开始比赛
	// modify 改变规则，等待对方确认或修改规则
	// refuse 取消
	$("#instant_confirm").unbind("click");
	$("#instant_modify").unbind("click");
	$("#instant_refuse").unbind("click");

	$("#instant_confirm").bind("click", function() {
		console.log("instant_confirm", info);
		createHome({
			userid : info.userid,
			ruletype : info.type.type1,
			size : info.type.type3,
			blackuserid : info.type.type2 == 1 ? uid : info.userid,
			whiteuserid : info.type.type2 == 2 ? uid : info.userid,
		});
	});
	$("#instant_modify").bind("click", function() {
		changeRule();
	});
	$("#instant_refuse").bind("click", function() {
		
		$(".invitationFriend").find('select').attr('disabled', false);
		var code = "2";
		var longUserId = uid;
		var longUserNm = readCookie('username');
		var info = {
			userid : longUserId,
			usernm : longUserNm,
			state : 3
		};

		var pushMsg = {
			code : code,
			info : info
		};
		var request = Base.encode(JSON.stringify(pushMsg));
		// 发送消息
		pushToUserMessage("{'users':['" +  $("#fight_userid").val() + "'],'pushMsg':'"+ request + "'}");
                              setTimeout(function(){
                                         layer.closeAll();
                                         document.location.href = "myschema://go?a=1";
                                         },1000);
	});
	
	layerOpen({area: ['7.38rem', '4.89rem'],skin:'layerOpen invitationModal',id:'sss',content: $("#invitation"),cancel:function(){document.location.href = "myschema://go?a=8";}});
}

// 发起方确认规则
function instantFight0() {
	var userId = $("#fight_userid").val();
	var instantStep = $("#instant_step").val();

	var types = {
		type1 : $("#fight_type_1").val(),
		type2 : $("#fight_type_2").val(),
		type3 : $("#fight_type_3").val(),
		type4 : $("#fight_type_4").val(),
		type5 : $("#fight_type_5").val(),
		type6 : $("#fight_type_6").val(),
		type7 : $("#fight_type_7").val()
	};

	var longUserId = uid;
	var longUserNm = readCookie('username');

	var info = {
		userid : longUserId,
		usernm : longUserNm,
		type : types
	};

	if (instantStep == 2) {
		createHome({
			userid : userId
		});
	} else {
		if (!submited) {
			submited = true;
			var code = "2";
			var pushMsg = {
				code : code,
				info : info
			};
			var request = Base.encode(JSON.stringify(pushMsg));
			// 发送消息
			pushToUserMessage("{'users':['" + userId + "'],'pushMsg':'"
					+ request + "'}");
			var t = setTimeout("submited=false;", deferTime)
		} else {
			layerTC("邀请已发出，请5秒后再进行操作");
		}
	}

	$("#instantFightModal").modal('hide');
}
function instantFight1(info) {
	$("#instant_step").val(2);
	$("#fight_userid").val(info.userid);
	$("#fight_type_1").val(info.type.type1);
	$("#fight_type_2").val(info.type.type2);
	$("#fight_type_3").val(info.type.type3);
	$("#fight_type_4").val(info.type.type4);
	$("#fight_type_5").val(info.type.type5);
	$("#fight_type_6").val(info.type.type6);
	$("#fight_type_7").val(info.type.type7);
	$("#fight_type_8").val(info.type.type8);
	$("#fight_type_9").prop("checked", info.type.type9);
	$("#fight_type_10").prop("checked", info.type.type10);
	$("#fight_type_11").prop("checked", info.type.type11);

	$("#instantFightModal").modal('show');
	$('#instantFightModal').modal({
		backdrop : 'static',
		keyboard : false
	});
}

// 观战
function matchGame(friendId) {
	alert("对局时不能进入观战");
}

function checkGameInfo(quiet) {
	$.ajax({
		type: "POST",
		url:  http+"/gobangteach/ChessController/getGameInfoByUserId",
		
		dataType: 'json',
		data:"token="+token+"&uid="+uid,
		success: function(data) {
			console.log("GetGameInfoByUserId ", data);
           layer.closeAll();
			if (data.error.returnCode==0) {
				if(quiet) {
					var playType;
                	if(data.data.userId == data.data.blackUserId) {
                		playType = data.data.blackPlayType;
                	} else {
                		playType = data.data.whitePlayType;
                	}
                	document.location.href = "myschema://go?a=6";
					return;
				} else {
						var playType;
                    	if(data.data.userId == data.data.blackUserId) {
                    		playType = data.data.blackPlayType;
                    	} else {
                    		playType = data.data.whitePlayType;
						}
						document.location.href = "myschema://go?a=5";
						return;
				}
			} else if (quiet && data.error.returnCode == '10053') {
           
           var lev_ai = levelAI||'1';
           console.log(lev_ai)
				$.ajax({
					url : http+ '/gobangteach/ChessController/createAIMatch',
					type : "POST",
					timeout : 5000, // 单位毫秒
                       data : $.param({ai_level: lev_ai,token:token,uid:uid}),
					success : function(data) {
						if (data.error.returnCode == 0) {
							document.location.href = "myschema://go?a=3";
                       // window.location.reload();
//                            window.location.href = "play.html";
						} else {
							//layerTC("系统错误: " + data.error.returnUserMessage);
						}
					},
					error : function(XMLHttpRequest, textStatus) {
						//layerTC("服务器错误！！！");
					}
				});
			}
		}
	});
}

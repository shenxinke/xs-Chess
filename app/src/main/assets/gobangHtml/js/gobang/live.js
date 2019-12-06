window.currentSize = 19;
var rtmpUrl = "rtmp://pb401d0d7.live.126.net/live/d20dfe62f07440a19f8d9e5bf33dbb1d?wsSecret=9008bc0d9b4587d20faaac1268e7ed4c&wsTime=1524454548";
var channel = "test2";
var pullUrl = "rtmp://vb401d0d7.live.126.net/live/d20dfe62f07440a19f8d9e5bf33dbb1d";
var liveStates = 0;
var classState = 0;

var retry = true;								// 拉流失败，是否自动重试
var maxRetryDuration = 1000 * 120;				// 拉流重试的最大时长，单位为毫秒
var streamTimeoutTime = 1000 * 30;				// 拉流超时时间，单位为毫秒，默认30秒

/* 直播相关 */
function initRtc() {
	SDK.NIM.use(WebRTC);

	window.netcall = WebRTC.getInstance({
		nim : nim,
		container : document.getElementById('live_me'),
		remoteContainer : document.getElementById('live_player'),
		// 是否开启日志打印
		debug : true
	})

	console.log("初始化RTC完成");
}

function initRtcListener() {
	netcall.on('joinChannel', function (obj) {
		console.log('${obj.account} 加入了互动房间。');
	});

	netcall.on('leaveChannel', function (obj) {
		console.log('${obj.account} 离开了互动房间。')
		netcall.stopRemoteStream({
			account: obj.account
		});
	});
	
	netcall.on('control', function(obj) {
		console.log('收到指令', obj);
	})
	
	netcall.on('audioVolumn', function(obj) {
		console.log('音量', obj);
	})
	
	netcall.on('deviceStatus', function(obj) {
    	console.log('设备状态发生变化', obj);

	    // 检查摄像头
	    netcall.getDevicesOfType(WebRTC.DEVICE_TYPE_VIDEO).then(function (devices) {
	        console.log('摄像头', devices)
	    });
	
	    // 检查麦克风
	    netcall.getDevicesOfType(WebRTC.DEVICE_TYPE_AUDIO_IN).then(function (devices) {
	        console.log('麦克风', devices)
	    });
	})
	
	netcall.on('remoteTrack', function(obj) {
		console.log('收到远程轨道信息', obj);
		// 音频：播放对方的音频
		if (obj.track.kind === 'audio') {
			// 播放对方声音
			netcall.startDevice({
				type: WebRTC.DEVICE_TYPE_AUDIO_OUT_CHAT
			})
			.catch(function() {
				console.log('播放对方的声音失败');
			});
	  	}

		
		// 视频：展示对方的画面
		if (obj.track.kind === 'video') {
			// 预览加入的同学的视频流
			if(vmChatroom.role == "teacher"){
				netcall.startRemoteStream({
					uid: obj.uid,
					node: document.getElementById('live_me')
				});
				// 设置对方预览画面大小
				netcall.setVideoViewRemoteSize({
					uid: obj.uid,
					width: 150,
					height: 150,
					cut: true
				});
			}else{
				netcall.startRemoteStream({
					uid: obj.uid,
					node: document.getElementById('live_player')
				});
				// 设置对方预览画面大小
				netcall.setVideoViewRemoteSize({
					uid: obj.uid,
					width: 150,
					height: 150,
					cut: true
				});
			}
		}
		
	});
	
	console.log("初始化RTC 监听器完成");
}

function createAndJoinChannel(channel, rtmpUrl) {
	netcall.createChannel({
		channelName: channel,
		custom: '测试自定义数据',
		webrtcEnable: true
	}).then(function(obj) {
		console.log("创建互动房间完成: " + channel);
		joinChannel(channel, rtmpUrl); // 创建者（主播）必须是第一个加入的
	}).catch(function(err){
        console.log("创建互动房间错误：", err);
        joinChannel(channel, rtmpUrl); // 创建者（主播）必须是第一个加入的
    });;
}

function joinChannel(channel, rtmpUrl, callback) {
	const sessionConfig = {
		liveEnable: true, // 开启互动直播
		videoQuality: WebRTC.CHAT_VIDEO_QUALITY_HIGH,
		videoFrameRate: WebRTC.CHAT_VIDEO_FRAME_RATE_15,
		rtmpUrl: rtmpUrl,
		rtmpRecord: false, // 开启推流录制
		splitMode: WebRTC.LAYOUT_SPLITBOTTOMHORFLOATING,
		layout: '',
		highAudio: true
	}
	netcall.joinChannel({
		channelName: channel, // 必填
		type: WebRTC.NETCALL_TYPE_VIDEO,
		liveEnable: true, // 开启互动直播
		sessionConfig: sessionConfig
	}).then(function(obj) {
		console.log("我自己加入互动房间成功");
		if(callback) {
			callback();
		}
	}).catch(function(err) {
		console.log("加入房间错误", err);
	});
}

function initTeacherChannel() {
	createAndJoinChannel(channel, rtmpUrl);
}
var refreshTimes = 1;
function leaveChannel() {
	$("#live_player").hide();
	netcall.leaveChannel().then(function(obj) {
		clientPlayer.setMute(false);
		console.log("我自己已经离开了互动房间");
	});
}



// 只打开音频
function openAudio() {
	const netcall = this.netcall
	const promise = Promise.resolve()
	
	promise.then(function() {
	  // 开启麦克风
	  return netcall.startDevice({
	    type: WebRTC.DEVICE_TYPE_AUDIO_IN
	  }).catch(function(err) {
	    console.log('启动麦克风失败')
	    console.error(err)
	  })
	})
	.then(function() {
		console.log("麦克风已启动");
	  // 设置采集音量
	  netcall.setCaptureVolume(255);
	});
}

// 打开音视频
function openVideo(callback) {
	const netcall = this.netcall
	const promise = Promise.resolve()
	
	promise.then(function() {
	  // 开启麦克风
	  return netcall.startDevice({
	    type: WebRTC.DEVICE_TYPE_AUDIO_IN
	  }).catch(function(err) {
	    console.log('启动麦克风失败')
	    console.error(err)
	  })
	})
	.then(function() {
		console.log("麦克风已启动");
	  // 设置采集音量
	  netcall.setCaptureVolume(255)
	  // 开启摄像头
	  return netcall.startDevice({
	      type: WebRTC.DEVICE_TYPE_VIDEO,
	      width: 640,
	      height: 480
	    })
	  .catch(function(err) {
	    console.log('启动摄像头失败')
	    console.error(err)
	  })
	})
	.then(function() {
		console.log("摄像头已启动");
		if(vmChatroom.role == "teacher"){
			netcall.startLocalStream(document.getElementById("live_pull"));
			$("#live_player").hide();
		}else{
			netcall.startLocalStream(document.getElementById("live_me"));
			//clientPlayer.pause();
			clientPlayer.setMute(true);
			console.log('reset');
		}
	})
	.then(function() {
	  // 设置本地预览画面大小
	  netcall.setVideoViewSize({
	    width: '4.24rem',
	    height: '2.55rem',
	    cut:true
	  })
	})
	.then(function() {
	  // 主播、连麦者请设置互动者角色
	  netcall.changeRoleToPlayer()
	  // 开启RTC连接
	  console.log("开始webrtc")
	  netcall.startRtc()
	})
	.then(function() {
	  console.log("webrtc连接成功")
	  liveStates = 1;
	  if(callback) {
		  callback();
	  }
	})
	.catch(function(err) {
	  console.log('发生错误, 挂断通话')
	  console.log(err)
	  netcall.hangup()
	})
}

// 停止视频
function closeVideo() {
	// 停止设备摄像头
	netcall.stopDevice(WebRTC.DEVICE_TYPE_VIDEO)
	console.log("已关闭视频。");
}

function closeDevice() {
	// 停止本地视频预览
	netcall.stopLocalStream()
	// 停止对端视频预览
	netcall.stopRemoteStream()

	// 停止设备麦克风
	netcall.stopDevice(WebRTC.DEVICE_TYPE_AUDIO_IN)
	// 停止设备摄像头
	netcall.stopDevice(WebRTC.DEVICE_TYPE_VIDEO)

	// 停止播放本地音频
	netcall.stopDevice(WebRTC.DEVICE_TYPE_AUDIO_OUT_LOCAL)
	// 停止播放对端音频
	netcall.stopDevice(WebRTC.DEVICE_TYPE_AUDIO_OUT_CHAT)
	
	console.log('已关闭所有设备');
	
	liveStates = 0;
}
/* ./直播相关 */

//function initPlayer() {
//    var clientPlayer = window.clientPlayer = neplayer("live_pull", {}, function() {
//        if(vmChatroom.role == "teacher"){
//
//        }else{
//            clientPlayer.setDataSource({src:pullUrl, type:"rtmp/flv"});
//        }
//        //
//    });
//}

function initPlayer() {
    var clientPlayer = window.clientPlayer = neplayer("live_pull", {}, function() {
                                                      clientPlayer.setDataSource(
                                                                                 {type: "application/x-mpegURL",src: "https://pullhlsb401d0d7.live.126.net/live/"+window.cid+"/playlist.m3u8"},
                                                                                 {type: "video/mp4",src: "https://flvb401d0d7.live.126.net/live/"+window.cid+".flv?netease=flvb401d0d7.live.126.net"},
                                                                                 {type: "video/x-flv",src: "https://flvb401d0d7.live.126.net/live/"+window.cid+".flv?netease=flvb401d0d7.live.126.net"},
                                                                                 {src:pullUrl, type:"rtmp/flv"}
                                                                                 );
                                                      });
    
}

/* 主播 */
function initTeacher() {
	console.log("当前角色为老师");
	vmChatroom.role = "teacher";
    var state = Tool.queryString("state");
    if(state==1||liveStates==1){
        $(".stuPop,#start_tag").hide();
        $(".stuPop").hide();
        $(".game_chatBg").show();
    }else{
        $(".stuPop,#start_tag").show();
        $(".game_chatBg").hide();
    }
	createAndJoinChannel(channel, rtmpUrl);
	//initPlayer();
	
	console.log(state);
}
function startMasterLive(callback) {
	openVideo(function() {
		doplay();
		
		if(callback) {
			callback();
		}
	});
}
/* ./主播 */
	
/* 学员 */
function initStudent() {
	console.log("当前角色为学生");
	vmChatroom.role = "student";
	var state = Tool.queryString("state");

	if(state==1||liveStates==1){
		$(".stuPop,#start_tag").hide();
		$(".stuPop").hide();
		$(".game_chatBg").show();
	}else{
		$(".stuPop,#start_tag").show();
		$(".game_chatBg").hide();
	}
    //initPlayer();
}

function doplay() {
	if(clientPlayer.getPlayState() != 1) {
		clientPlayer.play();
		console.log("播放")
	}
}

function ncall() {
	joinChannel(channel, null, function() {
		$("#live_player").show();
		openVideo();
	});
}

function openPlayerDevice() {
	openVideo();
}
/* ./学员 */

/* 棋盘相关 */
(function() {
	/**
     * 连接成功
     */
    eve.on("SocketOpen", function () {

        eve.f("HandlerReEnterGame")();

         
        eve.f("HandlerSignInReq",uid)();
       
        //
    });
	eve.on("AppOnEnter", function() {
		console.log("AppOnEnter");
		eve.f("SocketStart")();
	});

	eve.on("SocketOpen", function() {
		console.log("SocketOpen");
		eve.f("HandlerSignInReq", uid)();
	});
	
	eve.on("SignInResponse", function() {
		//clearChessboard();
		console.log("SignInResponse, gameid is ", window.currentGameId);
		eve.f("HandlerEnterGame", window.currentGameId)();
	});
   //落子声音
    eve.on("HandlerAction", function(takeBlack, takeWhite) {
		$("#stepmusic")[0].play();
	});
    eve.on("_ConfirmTouchEnded_",function(p1,p2){
		eve.f(p1,p2)();
	});
})();



/* ./教学相关 */

/* Chatroom */
var chatroom_data = { msgs: [], members: [], role: "student" };
function initChatroom(chatroomId) {
	nim.getChatroomAddress({
	  chatroomId: chatroomId,
	  done: getChatroomAddressDone
	});
	function getChatroomAddressDone(error, obj) {
	  console.log('获取聊天室地址' + (!error?'成功':'失败'), error, obj);
	  
	  	// 注意这里, 引入的 SDK 文件不一样的话, 你可能需要使用 SDK.Chatroom.getInstance 来调用接口
		// 非匿名方式登录
		var chatroom = window.chatroom = SDK.Chatroom.getInstance({
	  		appKey: "f2ee483b27648349f2da06d6d01456c2",
			account: uid,
			token: "qisheng123",
		  	chatroomId: obj.chatroomId,
		  	chatroomAddresses: obj.address,
			onconnect: onChatroomConnect,
			onerror: onChatroomError,
			onwillreconnect: onChatroomWillReconnect,
			ondisconnect: onChatroomDisconnect,
			// 消息
			onmsgs: onChatroomMsgs
		});
		function onChatroomConnect(obj) {
		  console.log('进入聊天室', obj);
		  
		  $("#chatroom_send_btn").click(function() {
			  if($("#chatroom_input").val()) {
				  var msg = chatroom.sendText({
				    text: $("#chatroom_input").val(),
				    done: function sendChatroomMsgDone (err, msgObj) {
				    	$("#chatroom_input").val("");
			    		chatroom_data.msgs.push(msgObj);
			    		refreshChatUI();
			    		
				    }
				  });
			  }
		  });
		  
		  // 连接成功后才可以发消息
		  var msg = chatroom.sendText({
		    text: 'hello',
		    done: function sendChatroomMsgDone (err, msgObj) {
		    	console.log("消息已发送", msgObj);
		    	if(msgObj) {
		    		chatroom_data.msgs.push(msgObj);
		    		refreshChatUI();
		    	}
		    }
		  })
		}
		function onChatroomWillReconnect(obj) {
		  // 此时说明 `SDK` 已经断开连接, 请开发者在界面上提示用户连接已断开, 而且正在重新建立连接
		  console.log('即将重连', obj);
		}
		function onChatroomDisconnect(error) {
		  // 此时说明 `SDK` 处于断开状态, 开发者此时应该根据错误码提示相应的错误信息, 并且跳转到登录页面
		  console.log('连接断开', error);
		  if (error) {
		    switch (error.code) {
		    // 账号或者密码错误, 请跳转到登录页面并提示错误
		    case 302:
		      break;
		    // 被踢, 请提示错误后跳转到登录页面
		    case 'kicked':
		      break;
		    default:
		      break;
		    }
		  }
		}
		function onChatroomError(error, obj) {
		  console.log('发生错误', error, obj);
		}
		function onChatroomMsgs(msgs) {
		  console.log('收到聊天室消息', msgs);
		  if(msgs) {
			  for(var i = 0; i < msgs.length; i++) {
					var msg = msgs[i];
					chatroom_data.msgs.push(msgs[i]);
			  	
					if(msg.type == "notification" && msg.attach.type == "memberEnter") {
						if(msg.attach.from != uid) {
							chatroom_data.members.push({account: msg.attach.from, nick: msg.attach.fromNick});
							if(vmChatroom.role == "teacher") {
								sendTipMsg("welcome", JSON.stringify({gameid:window.currentGameId, members:chatroom_data.members, state:window.classState}));
							}
							refreshMembersUI();
						}
					} else if(msg.type == "notification" && msg.attach.type == "memberExit") {
						for(var i = 0; i < chatroom_data.members.length; i++) {
							if(chatroom_data.members[i].account == msg.attach.from) {
								chatroom_data.members.splice(i, 1);
							}
						}
						refreshMembersUI();
					} else if(msg.type == "tip") {
						if(msg.tip == "invite-netcall") {
							console.log("tip", msg);
							var custom = JSON.parse(msg.custom);
							if(custom.type == 1 && custom.account == uid) { // type = 1 邀请连麦
								// 连麦邀请
								swal("老师邀请你加入连麦", {
						            closeOnClickOutside: false,
						            buttons: {
						                case1: {text:"接受", value: "case1"},
						                case2: {text:"拒绝", value: "case2"}
						            }
						        }).then(function (value) {
						            switch (value) {
						                case "case1":
						                	ncall();
						                	break;
						                case "case2":
						                	sendTipMsg("invite-netcall", JSON.stringify({type:2, account:msg.from}), msg.from);
						                	/*$("#inviteNetcall").hide();
						                	$("#cancelNetcall").show();*/
						                	break;
						            }
						        });
							} else if(custom.type == 2 && custom.account == uid) { // type = 2 拒绝连麦
								alert(msg.fromNick + " 拒绝了您的邀请。");
							}
						}else if(msg.tip == "cancel-netcall"){
							console.log("tip", msg);
							var custom = JSON.parse(msg.custom);
							if(custom.type == 1 && custom.account == uid) { // type = 1 取消连麦
								swal("老师与你断开连麦", {
						            closeOnClickOutside: false,
						            buttons: {
						                case1: {text:"知道了", value: "case1"}
						            }
						        }).then(function (value) {
						        	
						        });
								leaveChannel();
								/*$("#inviteNetcall").show();
			                	$("#cancelNetcall").hide();*/
							} 
						}else if(msg.tip == "clear-chessboard") {
							var custom = JSON.parse(msg.custom);
							window.currentGameId = custom.gameid;
							eve.f("HandlerSignInReq", uid)();
						} else if(msg.tip == "enable-btns") { // 权限管理
							var custom = JSON.parse(msg.custom);
							var enableMe = false;
							for(var i = 0; i < custom.accounts.length; i++) {
								if(custom.accounts[i] == uid) {
									enableMe = true;
									break;
								}
							}

							if(enableMe) {
								$("#researchBtnsWrapper2").show();
							} else {
								$("#researchBtnsWrapper2").hide();
							}
						} else if(msg.tip == "welcome") {
							if(vmChatroom.role != "teacher") {
								var custom = JSON.parse(msg.custom);
								window.currentGameId = custom.gameid;
								window.classState = custom.state;
								eve.f("HandlerSignInReq", uid)();
								chatroom_data.members = custom.members;
								
								if(window.classState == 1) {
									//doplay();
								}
							}
						}
					} else {
						if(msg.text == "开始上课") {
							popStart();
                            document.location.href = "myschema://go?a=9";
							//doplay();
						} else if(msg.text == "课件休息") {
							popBreak();
                        } else if(msg.text == "答对") {
                            popRightboard();
                        } else if(msg.text == "答错") {
                            popWrongboard();
                        }
					}
			  }
			  refreshChatUI();
		  }
		}
	}
	
	function sendText(msg, callback) {
		var msg = chatroom.sendText({
		    text: msg,
		    done: function sendChatroomMsgDone (err, msgObj) {
		    	console.log("消息已发送", msgObj);
	    		chatroom_data.msgs.push(msgObj);
	    		refreshChatUI();
	    		
	    		if(callback) {
	    			callback(msgObj);
	    		}
		    }
 		});
	}
	
	function refreshChatUI() {
		console.log("刷新对话框");
		vmChatroom.msgs = chatroom_data.msgs;
		setTimeout(function(){
			$("#chat-wrapper ").scrollTop($("#chat-wrapper")[0].scrollHeight);
		},1000);
	}
	
	function refreshMembersUI() {
		vmChatroom.members = chatroom_data.members;
		console.log('直播间的观看人员',chatroom_data.members)
	}
	
	function sendTipMsg(tip, custom, account, callback) {
		var msg = chatroom.sendTipMsg({
		  scene: 'p2p',
		  to: account,
		  tip: tip,
		  custom: custom,
		  done: function(err, msgObj) {
			  	console.log("消息已发送", msgObj);
				//chatroom_data.msgs.push(msgObj);
				//refreshChatUI();

				if(callback) {
					callback(msgObj);
				}
		  }
		});
		console.log('正在发送聊天室提醒消息, id=' + msg.idClient);
	}
	
	function getChatroomMembers(callback) {
		chatroom.getChatroomMembers({
		    guest: false,
		    limit: 100,
		    done: getChatroomMembersDone
		});
		function getChatroomMembersDone(error, obj) {
		    console.log('获取聊天室成员' + (!error?'成功':'失败'), error, obj.members);
		}
	}
	
//    function toggleEmoji() {
//        $("#emoji").toggle();
//    }
	
	window.useEmoji = function useEmoji(emoji) {
		var tc = document.getElementById("chatroom_input");  
		var emoNode = document.getElementById("emoji");
	    var tclen = tc.value.length;
	    tc.focus();
	    emoNode.style.display="none";
	    if(typeof document.selection != "undefined") {
	        document.selection.createRange().text = emoji;
	    }  
	    else {  
	        tc.value = tc.value.substr(0,tc.selectionStart)+emoji+tc.value.substring(tc.selectionStart,tclen);  
	    }
	}
	
	window.sendText = sendText;
	window.sendTipMsg = sendTipMsg;
	window.getChatroomMembers = getChatroomMembers;
}
/* ./Chatroom */





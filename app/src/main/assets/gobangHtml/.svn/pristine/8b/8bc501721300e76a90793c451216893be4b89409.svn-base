(function() {///"wss://test.xswq361.cn:4446/"
	var socket = null;
    var wsUri = socketUri;
//	var wsUri = "ws://192.168.1.115:4446/";
	
	function start() {
		if (socket === null) {
			socket = new WebSocket(wsUri);
			socket.binaryType = 'arraybuffer';
			socket.onopen = function(event) {
				eve.f("SocketOpen")();
			};
			socket.onclose = function(event) {
				eve.f("SocketClose")();
				socket = null;
			};

			socket.onmessage = function(event) {
				SocketOnMessage(event.data);
			};

		} else {
			console.log("reusing connection to " + wsUri);
		}
	}

	/**
	 * 响应消息
	 * 
	 * @param data
	 */
	function SocketOnMessage(data) {
		console.log("onMessage");
		var buf = new Uint8Array(data);
		var message = proto.Message.deserializeBinary(buf);

		switch (message.getType()) {
		case proto.Type.PUSHTOUSERSMESSAGENOTIY:
			eve.f("NotifyPushToUserMessage", message.getAck(),
					message.getPushMsg())();
			break;
		case proto.Type.SIGNINREPLY:
			eve.f("SignInReply", message)();
			break;
		default:
			console.log("unknow message" + message.getType());
		}
	}

	function socketSend(data) {
		socket.send(data);
	}

	eve.on("SocketStart", function() {
		start();
	});

	/**
	 * Created by thy on 2018/3/4.
	 */
	// 用户登录请求
	eve.on("HandlerSignInReq", function(userId) {
		console.log("HandlerSignInReq");
		var message = new proto.Message();
		message.setType(proto.Type.SIGNINREQ);
		message.setUserid(userId);
		var bf = message.serializeBinary();
		socketSend(bf);
	});

	/**
	 * 响应用户登录
	 * 
	 * @param message
	 * @returns {number}
	 */

	eve.on("SignInReply", function(message) {
		eve.f("SignInResponse", message)();
	});

	/**
	 * 推送消息给用户
	 */
	eve.on("HandlerPushToUserMessage", function(ack, pushMsg) {
		console.log("HandlerPushToUserMessage");
		var message = new proto.Message();
		message.setType(proto.Type.PUSHTOUSERSMESSAGE);
		message.setAck(ack);
		message.setPushMsg(pushMsg);
		var bf = message.serializeBinary();
		socketSend(bf);
           
	});
})();

(function() {
	console.log("开始初始化页面推送接受事件");
	/**
	 * 消息通知
	 */
	eve.on("NotifyPushToUserMessage", function(ack, msg) {
		console.log("MessageNotify " + ack);
		console.log("MessageNotify " + msg);
		var msgInf = Base.decode(msg);
		console.log("MessageNotify " + msgInf);
		var result = JSON.parse(msgInf);
		var code = result.code;
		var info = result.info;
		console.log("Message_info: " + msg);
		switch (code) {
		case '1':
			document.location.href = "myschema://go?a=3"
			break;
		case '2': // 协商对弈
           
			if(info.state == 1) { // 对方确认规则，此时我方确认即开始
				receiveConfirmRule(info);
			} else if(info.state == 2) { // 对方修改规则，我方确认后,对方确认则开始
				receiveChangeRule(info);
			} else if(info.state == 3) {
				
				layer.closeAll();
				layerTC("对方拒绝了你的邀请",function(){
                    document.location.href = "myschema://go?a=1";
                    },function(){
                    document.location.href = "myschema://go?a=1";
                    },function(){
                    document.location.href = "myschema://go?a=1";
                    });
				$(".invitationFriend-bg,.invitationFriend").hide();
				$(".invitationFriend").find('select').attr('disabled', false);
			}
			break;
		case '3': // 再来一局
			if(info.gameInfo) {
				swal(info.usernm + " 邀请你再来一局", {
	                closeOnClickOutside: false,
	                buttons: {
	                    case1: {text:"确定", value: "case1"},
	                    case2: {text:"取消", value: "case2"},
	                }
	            }).then(function (value) {
	            	switch (value) {
	    			case "case1":
	    				var gameInfo = info.gameInfo;
	    				gameInfo.userid = info.userid;
	    				createHome(gameInfo);
	    				break;
	    			case "case2":
	    				var pushMsg = {
	    					code : '3',
	    					info : {
	    						userid: uid,
	    						usernm: readCookie('username'),
	    						state: 3
	    					}
	    				};
	    				var request = Base.encode(JSON.stringify(pushMsg));
	    				// 发送消息
	    				pushToUserMessage("{'users':['" + info.userid + "'],'pushMsg':'" + request + "'}");
	    				break;
	    			}
	            });
			} else if(info.state == 3) {
				layerTC("对方拒绝了你的邀请",function(){
                    document.location.href = "myschema://go?a=1";
                    },function(){
                    document.location.href = "myschema://go?a=1";
                    },function(){
                    document.location.href = "myschema://go?a=1";
                    });
			}
			break;
		case '4':
			break;
		case '5':
			break;
		case '6':
			break;
		case '7':
			break;
		default:
			console.log("无用消息" + msgInf);
		}
	});

	/**
	 * 连接成功
	 */
	eve.on("SocketOpen", function() {
		console.log("Socket Open");
		eve.f("HandlerSignInReq", uid)();
	});
	eve.on("SignInResponse", function() {
		console.log("用户登录成功");
		checkGameInfo();
	});

	console.log("完成初始化页面推送接受事件");
})();

function socketStart() {
	console.log("Socket will start.");
	eve.f("SocketStart")();
}
// 推送消息给用户
function pushToUserMessage(msg) {
	console.log("推送消息:" + msg);
	eve.f("HandlerPushToUserMessage", 1, msg)();
}

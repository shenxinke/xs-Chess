

var socket = null;

var wsUri = GsocketUri;

function startWebSocket() {

    if (socket === null) {
        socket = new WebSocket(wsUri);
        socket.binaryType = 'arraybuffer';
        socket.onopen = function (event) {
			console.log('SocketOpen')
			//enterGame();
			eve.f('socketOpen')()
        };

        socket.onclose = function (event) {
		    console.log('SocketClose')
            socket = null;
        };

        socket.onmessage = function (event) {
            SocketOnMessage(event.data);
        };

        socket.onerror =function (event) {
			
        };

    } else {
        //port.postMessage("reusing connection to " + wsUri);
		
    }
}


/**
 * 响应消息
 * @param data
 */
function SocketOnMessage(data) {
    var buf = new Uint8Array(data);
    var message = proto.Message.deserializeBinary(buf);
	switch (message.getType()) {
		case proto.Type.ENTERGAMEREPLY://响应进入棋局
			//登陆成功并进入棋局
			var obj = {};
			obj.first = message.getFirst(); //黑先还是白先
			obj.moveList = message.getMoveList(); //默认落子数据
			obj.getGameId = message.getGameId()	//棋局id
			obj.whiteSeconds = message.getWhiteSeconds() //白棋已用时
			obj.blackSeconds = message.getBlackSeconds() //黑棋已用时
			obj.blackCounts = message.getBlackCounts() //黑棋读秒次数
			obj.whiteCounts = message.getWhiteCounts() //白棋读秒次数
			obj.resultList = message.getResultList() //提字数
			obj.pushMsg = message.getPushMsg()//时间相关
			
			// baseTime: 10 	基础时间（秒）
			// countNum: 3		基础读秒次数
			// stepTime: 30		基础读秒时间
			// blackRest: 30 	黑棋剩余读秒
			// whiteRest: 30	白棋剩余读秒
			
			
			
			//console.log('登入成功返回信息',message.getBlackSeconds())
			console.log('白棋已用时：'+message.getWhiteSeconds())
			console.log('黑棋已用时：'+message.getBlackSeconds())
			console.log('黑棋剩余读秒次数：'+message.getBlackCounts())
			console.log('白棋剩余读秒次数：'+message.getWhiteCounts())
			console.log('提字数：'+message.getResultList())
			eve.f('signInResponse',obj)()
			//eve.f('test')();
			//signInResponse(obj)
			
			
			
			
			/* First：黑先还是白先，0代表黑先，1代表白先
			MatchStatus：棋局状态，0对局中，其他均为已结束
			BlackUserId：黑棋用户ID
			WhiteUserId：白棋用户ID
			BlackCounts：黑棋数棋次数
			WhiteCounts：白棋数棋次数 */

			break;
		case proto.Type.ACKNOWLEDGMENTNOTIFY://响应落子
			//console.log('落子结果：'+ message.getMoveList());
			//返回落子结果
			console.log(222,message.getGameId());
			console.log('提字数2：'+message.getResultList())
			if(message.getMoveList()!==3){
				eve.f('handlerActionResult',message.getMoveList(),message.getResultList())()
			}
			break;
		case proto.Type.OVERNOTIY://通知棋局结束
			console.log(333,message.getGameId());
			//console.log('棋局结束，status为：'+message.getMatchStatus());
			var status = message.getMatchStatus()
			eve.f('notifyGameOver',status,message.getResultList())() 
			//类型为同意数棋
			if(status>7 && status<12){
				//eve.f("jugementResult", message.getResultList())();
			}

			//写相应逻辑
			break;
		case proto.Type.ACTIONNOTIFY:
			eve.f("NotiyAction", message)();
			break;
		case proto.Type.TURNEDITSTATUSNOTIFY:
			eve.f("NotifyEditStatus", message)();
			break;
		case proto.Type.UNDONOTIY:
			eve.f("UndoNotiy", gameId)();
			break;
		case proto.Type.ANSWERUNDOYESNOTIFY:
			//eve.f("AnswerUndoYesNotify", message)();
			console.log('悔棋',message.getMoveList());
			eve.f("AnswerUndoYesNotify", message.getMoveList())();
			break;
		case proto.Type.ANSWERUNDONONOTIFY:
			eve.f("AnswerUndoNoNotify", message)();
			break;
		case proto.Type.REQUESTDRAWNOTIY:
			eve.f("NotifyRequestDraw", message)();
			break;
		case proto.Type.ANSWERDRAWNONOTIFY:
			eve.f("AnswerDrawNoNotify", message)();
			break;
		case proto.Type.ANSWERDRAWLIMIT:
			eve.f("AnswerDrawNoLimit", message)();
			break;
		case proto.Type.ANSWERUNDOLIMIT:
			//悔棋达到限制
			eve.f("AnswerUndoLimit", message)();
			break;
		case proto.Type.NEWGAMEREPLY:
			eve.f("NewGameReply", message)();
			break;
		case proto.Type.CALCULATENOTIFY:
			eve.f("CalculateNotify", message)();
			break;
		case proto.Type.STATISTICALNOTIFY:
			eve.f("StatisticalNotify", message)();
			break;
		case proto.Type.STATISTICALNONOTIFY:
			//console.log('拒绝数棋')
			//eve.f("StatisticalNoNotify", message)();
			break;
		case proto.Type.STATISTICALLIMIT:
			eve.f("StatisticalLimit", message)();
			break;
		case proto.Type.STATISTICALFORCEREFUSE:
			//alert('存在公气，请继续下棋')
			eve.f("StatisticalForceRefuse", message)();
			break;
		case proto.Type.AIHELPERREPLY:
			//console.log('AI支招结果'+message.getResultList())
			eve.f("AIHelperReply", message.getResultList())();
			break;
		case proto.Type.JUDGEMENTREPLY:
		
			//console.log('形势判断结果：'+message.getResultList())
			eve.f("jugementResult", message.getResultList())();
			break;

		case proto.Type.TRAVERSEREPLY:
			eve.f("TraverseReply", message)();
			break;
		case proto.Type.TRAVERSESYNCREPLY:
			eve.f("TraverseSyncReply", message)();
			break;
		case proto.Type.TRYPLAYSTARTREPLY:
			eve.f("TryPlayStartReply", message)();
			break;
		case proto.Type.TRYPLAYENDREPLY:
			eve.f("TryPlayEndReply", message)();
			break;
		case proto.Type.DROPVOTESTARTREPLY:
			eve.f("DropVoteStartReply", message)();
			break;
		case proto.Type.DROPVOTEENDREPLY:
			eve.f("DropVoteEndReply", message)();
			break;
		case proto.Type.SETTINGFIRSTREPLY:
			eve.f("SettingFirstReply", message)();
			break;
		case proto.Type.SETTINGACTIONREPLY:
			eve.f("SettingActionReply", message)();
			break;
		case proto.Type.RESEARCHPLAYSTARTREPLY:
			eve.f("ResearchPlayStartReply", message)();
			break;
		case proto.Type.RESEARCHPLAYENDREPLY:
			eve.f("ResearchPlayEndReply", message)();
			break;

		case proto.Type.SHOWSTEPTEACHREPLY:
			eve.f("ShowStepTeachReply", message)();
			break;
		case proto.Type.HIDESTEPTEACHREPLY:
			eve.f("HideStepTeachReply", message)();
			break;
		case proto.Type.PRESSLIMIT:
			//停一手超次数响应
			eve.f("PressLimit", message)();
			break;
		case proto.Type.PUSHTOUSERSMESSAGENOTIY:
			console.log('收到响应消息:',message.getPushMsg());
    		console.log('类型为:',message.getAck());
			eve.f("informReply", message.getAck())();
			break;
    }
}


function socketSend(data){
    if(socket!==null)
    socket.send(data);
}

//登陆棋局
eve.on('enterGame',function(gameId,userId){
	var message = new proto.Message();
		message.setType(proto.Type.ENTERGAMEREQ);
		message.setGameId(gameId);
		message.setUserid(userId);
		socketSend(message.serializeBinary());
})


//落子逻辑 
eve.on('handlerAction',function(gameId,action){
	var message = new proto.Message();
		message.setType(proto.Type.ACTION);
		message.setGameId(gameId);
		message.setAction(action);//'坐标 B[aa]'
		socketSend(message.serializeBinary());
})

//认输
eve.on('giveUp',function(userId,gameId){
	var message = new proto.Message();
		message.setType(proto.Type.ADMITDEFEAT);
		message.setUserid(userId);
		message.setGameId(gameId);
		socketSend(message.serializeBinary());
})

//形式判断
eve.on('jugement',function(userId,arr){
	var message = new proto.Message();
		message.setType(proto.Type.JUDGEMENTREQ);
		message.setUserid(userId);
		arr.forEach(function(item){
			message.addMove(item);
		})
		socketSend(message.serializeBinary());
})

//推送消息
eve.on('inform',function(userId,type){
	var message = new proto.Message();
		message.setType(proto.Type.PUSHTOUSERSMESSAGE);//类型
		message.setUserid(''+userId);//要推送给谁，对方的userid
		message.setAck(type);//消息类型，自己定义，比如1申请数棋
		message.setPushMsg('1111');
		socketSend(message.serializeBinary());
})

//同意数棋
eve.on('statisticalYes',function(userId,gameId){
	var message = new proto.Message();
	    message.setType(proto.Type.STATISTICALYES);
	    message.setUserid(''+userId);//userid为自己的id
	    message.setGameId(gameId);
	    socketSend(message.serializeBinary());
})

//拒接数棋 暂时没用到
eve.on('statisticalNo',function(userId,gameId){
	var message = new proto.Message();
		message.setType(proto.Type.STATISTICALNO);
		message.setUserid(''+userId);//这个userid是已方id
		message.setGameId(gameId);
		socketSend(message.serializeBinary());
})

//同意和棋
eve.on('answerdrawYes',function(userId,gameId){
	var message = new proto.Message();
	    message.setType(proto.Type.ANSWERDRAWYES);
	    message.setUserid(''+userId);//自己的id
	    message.setGameId(gameId);
	    socketSend(message.serializeBinary());
})
//强制数棋
eve.on('statisticalForcereq',function(userId,gameId){
	var message = new proto.Message();
	    message.setType(proto.Type.STATISTICALFORCEREQ);
	    message.setUserid(''+userId);//自己的id
	    message.setGameId(gameId);
	    socketSend(message.serializeBinary());
})

//AI支招
eve.on('AIhelperreq',function(userId,arr){
	var message = new proto.Message();
	    message.setType(proto.Type.AIHELPERREQ);
	    message.setUserid(''+userId);//自己的id
	    //message.setGameId(gameId);
		arr.forEach(function(item){
			message.addMove(item);
		})
	    socketSend(message.serializeBinary());
})
/* var message = new proto.Message();
message.setType(proto.Type.AIHELPERREQ);
message.setUserid('37');
message.setGameId('gameId');
socketSend(message.serializeBinary()); */


//改变读秒次数
eve.on('countingCounts',function(userId,gameId){
	var message = new proto.Message();
	    message.setType(proto.Type.COUNTINGCOUNTS);
	    message.setUserid(userId);
	    message.setGameId(gameId);
	    socketSend(message.serializeBinary());
})


//同意悔棋
eve.on('answerundoyes',function(gameId){
	var message = new proto.Message();
	    message.setType(proto.Type.ANSWERUNDOYES);
	    message.setGameId(gameId);
	    socketSend(message.serializeBinary());
})
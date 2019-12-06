/* 
	对弈和比赛页面公用方法
 */
function playChess(o){
	//查询对弈信息
	o.getGameInfoByUserId=function (callback){
		var $this = this;
		$.ajax({
			type : "POST",
			url : http+"/gobangteach/ChessController/getGameInfoByUserId",
			dataType : 'json',
			data:{
				token:token,
				uid:uid
			},
			success : function(data){
				if (data.error.returnCode == 0) {
					//option.gameInfo = data.data[0];
					$this.gameInfo = data.data[0]
					eve.f('enterGame',data.data[0].gameId,uid)();
					if(data.data[1].status > 0){
					    eve.f('notifyGameOver',data.data[1].status)();
					}
				}
				if(data.error.returnCode == 10053){
					layerTC('对局信息不存在')
					console.log("getGameInfoByUserId")
					callback && callback()
				}
				//document.location.href = "myschema://go?a=14"
			},
			error : function(XMLHttpRequest, data) {
				
			},
			complete:function(){
				document.location.href = "myschema://go?a=14"
			}
		});
	};
	o.getGameInfoByOtherUserId=function (userId){
		var $this = this;
		$.ajax({
			type : "get",
			url : http+"/gobangteach/ChessController/getGameInfoByOtherUserId",
			dataType : 'json',
			data:{
				token:token,
				uid:uid,
				userId:userId
			},
			success : function(data){
				if (data.error.returnCode == 0) {
					//option.gameInfo = data.data[0];
					$this.gameInfo = data.data[0]
					eve.f('enterGame',data.data[0].gameId,uid)();
					if(data.data[1].status > 0){
					    eve.f('notifyGameOver',data.data[1].status)();
					}
				}
				if(data.error.returnCode == 10053){
					layerTC('对局信息不存在')
					console.log("getGameInfoByUserId")
					//callback && callback()
				}
				//document.location.href = "myschema://go?a=14"
			},
			error : function(XMLHttpRequest, data) {
				
			},
			complete:function(){
				document.location.href = "myschema://go?a=14"
			}
		});
	};
	//关闭棋局
	o.closeGame=function(callback){
		var $this = this;
		this.gameInfo.token = token;
		this.gameInfo.uid = uid;
		$.ajax({
			type : "POST",
			url : http+"/gobangteach/ChessController/closeGame",
			data :$this.gameInfo,
			dataType : 'json',
			success : function(data) {
				if (data.error.returnCode > 0) {
					console.log("更新对局状态失败。", data.error);
				} else {
					$this.closePlaying($this.gameInfo.gameId)
					callback && callback(data.data)
				}
			},
			error : function(XMLHttpRequest, data) {}
		})
	};
	// 用户退出时
	o.closePlaying=function (chessid) {
		$.ajax({
			type : "POST",
			url : http+"/gobangteach/ChessController/closePlaying",
			data : {
				chessid: chessid,
				token:token,
				uid:uid
			},
			dataType : 'json',
			success : function(data) {
				if (data.error.returnCode > 0) {
					console.log("更新用户比赛状态失败。", data.error);
				} else {
					
				}
			},
			error : function(XMLHttpRequest, data) {}
		});
	}

	//认输
	o.giveUp=function(){
		var $this = this
		layerTC('确定认输吗？',function(){
			eve.f('giveUp',uid,$this.gameInfo.gameId)();
		})
	};
	//形式判断
	o.jugement=function(){
		this.jugementType = 1;
		eve.f('jugement',uid,this.game.svgFormatting())(); 
		if(!this.game.config.isJugement){
			//形式判断结果
			layerOpen({
				content:'#jugementLayer',//节点
				area:['6.07rem','4.77rem'],//可选默认 2rem 2rem  宽度 高度(带单位)
			})
		}
	},
	//强制数棋
	o.statisticalForcereq=function (){
		eve.f('statisticalForcereq',uid,this.gameInfo.gameId)();
	};
	//消息推送
	o.inform=function(num){
		var $this = this;
		//推送消息
		eve.f('inform',this.opponentId,num)(); 
		switch(num){
			case 4:
			if($this.gameInfo.blackUserId==uid){
				eve.f('handlerAction',$this.gameInfo.gameId,'B[]')();
			}else{
				eve.f('handlerAction',$this.gameInfo.gameId,'W[]')();
			}
			break;
		}
	}
	
	//复盘
	o.replay=function(){
		this.game.config.type = 1,//棋盘类型改为电子棋盘
		this.game.svg = this.game.svgFormatting() //重新加载一遍棋子
		this.isReplay = 1;
	};

	//显示手数
	o.showLot=function () {
		this.game.isShowLot ^= 1 //1显示 0隐藏
	};
	//AI支招
	o.AIhelperreq=function(num){
		eve.f('AIhelperreq',uid,this.game.svgFormatting())();
	};
	//开始试下
	o.startTry=function (){
		this.game.startTry()
		this.isTry = this.game.config.isTry
	};
	//结束试下
	o.endTry=function (){
		this.game.endTry()
		this.isTry = this.game.config.isTry
	};
	
	//根据形势判断结果返回谁胜，胜多少目
	o.getInfo=function (blackSum, whiteSum) {
		if (blackSum === undefined || whiteSum === undefined) 	
	    return "";
		var sum =0;
		if((blackSum+whiteSum)==361){
			sum = blackSum - 180.5 - 3.75;
		}else{
			sum = blackSum - whiteSum - 3.75;
		}
		var sumstr = Math.abs(sum)+"";
		var result = sumstr.substr(0,sumstr.indexOf("."));
		var desimal = sumstr.substr(sumstr.indexOf(".")+1,sumstr.length);
		if(desimal=="25"){
			desimal = "¼";
		}else {
			desimal = "¾";
		}
		if(result=="0"){
			result = desimal;
		}else{
			result += desimal;
		}
		
	    let obj = {};
	    if (sum > 0) {
	    	obj.title = '黑方胜利';
	    	obj.content = "黑棋胜" + result + "子";
	        return obj
	    } else if (sum < 0) {
	    	obj.title = '白方胜利';
	    	obj.content = "白棋胜" + result + "子";
	        return obj
	    }else {
	        obj.title = '双方平手';
	        obj.content = "双方平手";
	    	return obj;
	    }
	};
	
	//播放音频
	o.playAudio=function(val){
		let oAudio = document.getElementsByTagName('audio');
		for (let i=0;i<oAudio.length;i++) {
			oAudio[i].pause();
			oAudio[i].currentTime = 0;
		}
		switch (val){
			case 1:
				//$("#winmusic")[0].play()	//恭喜您取得胜利
				document.location.href = "myschema://go?a=10&music=CELEBRATE";//恭喜您取得胜利
				break;
			case 2:
				//$("#losemusic")[0].play() //您辛苦了感谢您的精彩对弈
				document.location.href = "myschema://go?a=10&music=COMFORT";//您辛苦了感谢您的精彩对弈
				break;
			case 3:
				//$("#losemusic")[0].play()
				document.location.href = "myschema://go?a=10&music=COMFORT";//您辛苦了感谢您的精彩对弈
				break;
			case 4:
				//$("#losemusic")[0].play()
				document.location.href = "myschema://go?a=10&music=COMFORT";//您辛苦了感谢您的精彩对弈
				break;
			default:
				$('#'+val)[0].play()
				break;
		}
	}
	
	//能力图数据
	o.drawChart=function (gameInfo) {
		let gameId = gameInfo.gameId;
		let level = jsonlink().level ||1;
		$.ajax({
			type : "GET",
			url : http+"/gobangteach/RadarController/RadarAbility",
			data:{
				level:level,
				userId:uid,
				chessid:gameId,
				token:token,
				uid:uid
			},
			dataType : 'json',
			success : function(data) {
				if(data.data != null && data.data){
					console.log('能力',data.data);
					if(level>25){
						if(level>=26&&level<=28){
							showGraphData(data.data,2)
							console.log('等级区间1-3D')
						}else{
							showGraphData(data.data,2)
							console.log('等级区间4D及以上')
						}
					}else if(level>25<=25){
						if(level>=1&&level<=10){
							showGraphData(data.data,0)
							console.log('等级区间25-16k')
						}else if(level>=11&&level<=15){
							
							showGraphData(data.data,1)
							console.log('等级区间15-11k')
						}else if(level>=16&&level<=25){
							showGraphData(data.data,2)
							console.log('等级区间10-1k')
						}
					}else{
						//return '尚未定级'
					}
				}
			},
			error : function(XMLHttpRequest, data) {
				layerTC("系统错误！," + data);
			}
		})
	};
	
	
	
	(function($this){
		
		//棋盘缩放功能
		new RTP.PinchZoom($(".chessBox"), {
			animationDuration:200,
			maxZoom:3
		});
		
		//棋盘实例
		$this.game = new xsgo('gameChess',{
			type:3,
			bgColor:'#ffd687',
			isShowCoord:1,
			affirmMovelater:1,//开启确认落子
		})
		
		//确认落子回调
		$this.game.qrlzState = function(val){
			$this.qrlzState = val
		}
		
		
		//电子棋盘播放落子声
		$this.game.playAudio =function(flag){
			if(flag){
				//$this.playAudio('takemusic') //提子声
				document.location.href = "myschema://go?a=10&music=ChipMany";
			}else{
				//$this.playAudio('stepmusic') //落子声
				document.location.href = "myschema://go?a=10&music=STONE0";
			}
		}
		
		var game = $this.game;
		
		//棋局登入成功回调
		eve.on('signInResponse',function(data){
			console.log(data)
			$this.isReplay = 0;
			let pushMsg = JSON.parse(data.pushMsg);
			// pushMsg.baseTime: 10 	基础总时间（秒）
			// pushMsg.countNum: 3		基础读秒次数
			// pushMsg.stepTime: 30		基础读秒时间
			// pushMsg.blackRest: 30 	黑棋剩余读秒
			// pushMsg.whiteRest: 30	白棋剩余读秒
			// pushMsg.takeLimit: 		提几字胜如果为空则说明是19路
			$this.totalTime = pushMsg.baseTime;
			$this.stepTime = pushMsg.stepTime;
			$this.blackSeconds = pushMsg.blackRest;
			$this.whiteSeconds = pushMsg.whiteRest;
			$this.countNum = pushMsg.countNum
			
			//提示胜利规则
			pushMsg.takeLimit?$this.rulesName = "提"+pushMsg.takeLimit+"子胜":$this.rulesName = "分先 黑贴3又3/4子"
			
			
			//更新棋盘路数 RuleType2 == 1 19路 ==2  9路
			$this.game.road = $this.gameInfo.RuleType2===1?19:9  
			
			//设置当前用户所执棋子颜色
			//如果棋局黑棋id  等于当前用户id  则当前用户执黑 否则执白
			if($this.gameInfo.blackUserId==uid){
				$this.game.config.userFirst = 0;
				$this.opponentId = $this.gameInfo.whiteUserId //对手id
			}else{
				$this.game.config.userFirst = 1;
				$this.opponentId = $this.gameInfo.blackUserId //对手id
			}
			//设置 谁先 0黑先 1白先
			$this.game.config.first = data.first
		
			/* 
				判断当前棋局是否有落子信息，防止刷新数据丢失
				如果有落子信息则渲染落子否则初始化整个棋盘
			 */
		
			if(data.moveList.length){ //如果有落子信息()
				//更新棋谱
				$this.game.svg = data.moveList;
			}else{
				$this.game.init() 
			}
			
			//落子回调
			$this.game.playCall = function(d){
				//落子
				console.log(d)
				eve.f('handlerAction',$this.gameInfo.gameId,d)(); 
			}
			
			$this.vsInfo = data;
			//所剩基础时间 = totalTime基础总用时 - blackSeconds已用时间(已用时间不得小于0)
			$this.blackTime = $this.totalTime - data.blackSeconds<0?0:$this.totalTime - data.blackSeconds;
			$this.whiteTime = $this.totalTime - data.whiteSeconds<0?0:$this.totalTime - data.whiteSeconds
			
			//当前该谁落子
			$this.activeUser = game.config.first
			
			if($this.game.config.first){
				timer.timout($this.whiteTime)
			}else{
				timer.timout($this.blackTime )
			}
			console.log('登入成功');
			//$this.playAudio('startmusic') //对局开始
			document.location.href = "myschema://go?a=10&music=START"; //对局开始

		})
		//落子结果
		eve.on("handlerActionResult", function(data,resultList) {
			console.log('落子结果:',data);
			var str = data.join('').indexOf('AE');
			if(str>0){
				//$this.playAudio('takemusic') //提子声
				document.location.href = "myschema://go?a=10&music=ChipMany"; //提子声
			}else{
				//$this.playAudio('stepmusic') //落子声
				document.location.href = "myschema://go?a=10&music=STONE0";
			}
			game.addQueue(data)
			
			console.log('当前谁下',game.config.first)
			
			
			timer.flag = 0; //关闭上一次的基础计时
			djs.flag = 0	//关闭上一次的读秒计时
			if(game.config.first){
				timer.timout($this.whiteTime) //从新开始本次计时
			}else{
				timer.timout($this.blackTime)
			}
			
			if($this.blackTime===0){ // 如果基础时间用完 则每次落子重置 读秒时间为30秒
				$this.blackSeconds = $this.stepTime;
			}
			if($this.whiteTime===0){
				$this.whiteSeconds = $this.stepTime
			}
			$this.vsInfo.resultList = resultList
			
			$this.activeUser = game.config.first
		});
		//停一手次数限制
		eve.on('PressLimit',function(){
			layerTC('停一手次数达到限制。')
		})
		eve.on('AnswerUndoLimit',function(){
			layerTC('悔棋次数达到限制。')
		})
	})(o)
	
	
};


//对弈倒计时
function countDown(callback){
	var flag = 1;
	this.timout=function(total){
		this.flag = 1
		var $this =this;
		if(total<=0){
			callback(total);
			return
		}
		this.time = setInterval(function(){
			if($this.flag){
				total --;
				if(total<=0){
					clearInterval($this.time)
				}
				callback(total)
			}else{
				clearInterval($this.time)
			}
		},1000)
	}
	Object.defineProperty(this,'flag',{
		get:function(){
			return flag
		},
		set:function(val){
			flag = val;
			if(!val){
				clearInterval(this.time)
			}
		},
	})
}

//显示能力图
function showGraphData(data,index){
	var myChart2 = echarts.init(document.getElementById('power'));
	var indicator,value;
	function filter(o,val){
		if(o){
			return o
		}else{
			return 0
		}
	}

	switch(index)
	{
	case 0:
	indicator = [
	   { name: '吃子能力', max: 100},
	   { name: '切断能力', max: 100},
	   { name: '逃子能力', max: 100},
	];
	value = [filter(data.eatZ),filter(data.abscission),filter(data.fleeZ)];
	  break;
	case 1:
		indicator = [
		   { name: '吃子能力', max: 100},
		   { name: '逃子能力', max: 100},
		   { name: '布局能力', max: 100},
		   { name: '计算能力', max: 100},
		   { name: '终局能力', max: 100} 
		];
	 value = [filter(data.eatZ) ,filter(data.fleeZ),filter(data.layout),filter(data.calculate),filter(data.final)];
	  break;
		case 2:
		indicator = [
		   { name: '杀棋能力', max: 100},
		   { name: '活棋能力', max: 100},
		   { name: '布局能力', max: 100},
		   { name: '计算能力', max: 100},
		   { name: '官子能力', max: 100} 
		];
		value = [filter(data.eatZ),filter(data.fleeZ),filter(data.layout),filter(data.calculate),filter(data.final)];
	  break;
	  case 3 :
		indicator = [
		   { name: '大局观', max: 100},
		   { name: '棋子效率', max: 100},
		   { name: '布局能力', max: 100},
		   { name: '计算能力', max: 100},
		   { name: '官子能力', max: 100} 
		];
		value = [filter(data.chessView),filter(data.chessEfficiency),filter(data.layout),filter(data.calculate),filter(data.final)];
	  break;
	  case 4 :
		indicator = [
		   { name: '大局观', max: 100},
		   { name: '棋子效率', max: 100},
		   { name: '布局能力', max: 100},
		   { name: '计算能力', max: 100},
		   { name: '官子能力', max: 100} 
		];
		value = [filter(data.chessView),filter(data.chessEfficiency),filter(data.layout),filter(data.calculate),filter(data.final)];
	  break;
	 
	}
	console.log('data',data)
	console.log(value)
   
	$("#djg span").text(indicator[0].name+":"+ Math.floor(value[0]*100)/100+'分');
	$("#gj span").text(indicator[1].name+":"+Math.floor(value[1]*100)/100+'分');
	$("#wk span").text(indicator[2].name+":"+Math.floor(value[2]*100)/100+'分');
	if(indicator.length>3){
		$("#pk span").text(indicator[3].name+":"+Math.floor(value[3]*100)/100+'分');
		$("#fs span").text(indicator[4].name+":"+Math.floor(value[4]*100)/100+'分');
	}
	var option2 = {
			textStyle: {
				color: '#000',
				fontSize : '0.18rem'
			},
			tooltip:{
				show:true
			},
			radar: {

				name: {
					textStyle: {
						color: '#000',
						fontSize : '80%'
					}
				},
				splitLine: {
					lineStyle: {
						color: '#5ebdff',
							fontSize : '0.18rem'
					}
				},
				splitArea: {
					areaStyle: {
						color: ['rgba(0,0,0,0)']
					}
				},
				radius: '60%',
				center: ['50%','50%'],
				indicator: indicator,
			},
			legend: {
				data: [],
				show:false
			},
			series: [{
				type: 'radar',
				data: [{
					areaStyle: {
						normal: {
							color: '#5ebdff',
							fontSize : '0.18rem'
						}
					},
					lineStyle: {
						normal: {
							color: '#5ebdff',
							fontSize : '0.18rem'
						}
					},
					value: value,
					name : '个人能力图',
				}],
				itemStyle: {
					normal: {
						color: '#1c7dfa',
						lineStyle: {
							color: '#388fff',
							fontSize : '0.18rem'
						}
					}
				},
			}]
		};
	if(document.documentElement.offsetWidth<=1300){
		option2.radar.radius = 45;
	}else if(document.documentElement.offsetWidth<=1556){
		option2.radar.radius = 55;
	}else{
		option2.radar.radius = 70;
	}
	myChart2.setOption(option2);
	
}
function xsgo(el,option){
	var initData = {
		road:9, 		//围棋路数
		lineWidth:1, 	//线宽 单位px
		lineColor:'#fedd7f', //线 颜色
		bgColor:'#ffeb70', //棋盘背景颜色
		//chessData:'',//围棋棋谱 B[AB]格式那种
		rightAnswers:[], //正确答案
		
		
		type:1, //1 电子棋盘 2 题库 3对弈
		sgfstate:1, //题库的类型 1  落子题  2判断题  3选择题 4 好坏 5 官子6 数气 7 布局(三四线吃子)
		queue:[],
		queueCopy:'', //当前正在展示的棋谱 比如前进后退几步后所展示的
		queueTry:[],
		
		editStatus:'BW', // BW 黑白 WB 白黑 B单黑 W单白 TR 画三角 SQ正方形 CR圈 LB1数字 LBA 字母  --特殊
		
		LBA : ["A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"],
		LB1:5,
		acStep:'', //前进后退时  当前的在第几手
		isShowLot:0, //1显示手数  0隐藏
		lotNum:1, //起始手数
		isSmilingFace:0, //1显示笑脸 0隐藏
		isTry:0, //是否开启了试下功能
		isShowCoord:0, //1显示坐标 0 隐藏
		userFirst:'', //当前用户是 执黑0 还是 执白1 仅在对弈中有效
		first:0, //(默认黑先)当前要下的子，0表示黑子，1表示白子，交替。
		log:console.log,
		
		life:[],//临时存储当前块的气元素
		lump:[], //临时纪录当前块的每个元素
		rob:'' ,//记录打劫的棋位
		
		isJugement:0,//形式判断开启状态 1开 0关
		isAIHelper:0,//ai支招 开启状态 1开 0关
		chessManual:0, //有05 代表是题库 没有代表对弈棋盘 不允许落子只允许试下
		affirmMovelater:0//确认落子 0关 1开
		
	};
	
	
	var extend=function(o,n){
		if(!n){n = {}};
		for (x in n){
			
			o[x]=n[x];
		}
		return o
	};
	
	var config = extend(initData,option);
	this.config = config;
	
	var initConfig = JSON.stringify(config) //棋盘初始信息  清空棋盘切换路数 重置回来要用
	
	
	var $this =this
	
	
	//arr.remove('fd');  删除数组
	Array.prototype.remove = function(val) { 
		var index = this.indexOf(val); 
		if (index > -1) { 
			this.splice(index, 1); 
		} 
	}; 
	
	/* 
	画圆
	传入当前棋位
	 */
	var drawCircle = function(me){
		var CR = document.createElement('div');
		CR.className = 'circle';
		if(!me.label){
			CR.style.opacity='0.3'
		}
		CR.style.borderWidth = me.offsetWidth*0.09+'px';
		if(me.dot < 0){ //无子情况
			CR.style.borderColor = 'red';
		};
		if(me.dot === 0){
			CR.style.borderColor = 'white';
		};
		if(me.dot === 1){
			CR.style.borderColor = 'black';
		}
		
		me.getElementsByClassName('label')[0].appendChild(CR);
	}
	/* 画黑子 */
	
	var drawBlack = function(me){
		me.getElementsByClassName('pieces')[0].className += ' black';
		if(me.dot===-1){
			//me.getElementsByClassName('pieces')[0].style.opacity='0.5'
			me.getElementsByClassName('pieces')[0].className += ' move';
			
		}
		if(config.isShowLot){
			me.getElementsByClassName('pieces')[0].innerHTML = '<div class="showLot" style="display:block;">'+me.LotNum+'</div>'
		}else{
			me.getElementsByClassName('pieces')[0].innerHTML = '<div class="showLot" style="display:none;">'+me.LotNum+'</div>'
			
		}
	}
	/* 画白子 */
	var drawWhite = function(me){
		me.getElementsByClassName('pieces')[0].className += ' white';
		if(me.dot===-1){
			//me.getElementsByClassName('pieces')[0].style.opacity='0.7'
			me.getElementsByClassName('pieces')[0].className += ' move';
		}
		if(config.isShowLot){
			me.getElementsByClassName('pieces')[0].innerHTML = '<div class="showLot" style="display:block;">'+me.LotNum+'</div>'
		}else{
			me.getElementsByClassName('pieces')[0].innerHTML = '<div class="showLot" style="display:none;">'+me.LotNum+'</div>'
			
		}
	}
	
	/* 
		画方块
	 */
	var drawSquare = function(me){
		var SQ = document.createElement('div');
		SQ.className = 'square';
		if(!me.label){
			SQ.style.opacity='0.3'
		}
		if(me.dot < 0){ //无子情况
			SQ.style.background = 'red';
		}
		if(me.dot === 0){
			SQ.style.background = 'white';
		};
		if(me.dot === 1){
			SQ.style.background = 'black';
		}
		me.getElementsByClassName('label')[0].appendChild(SQ);
	}
	/* 
		三角形
	 */
	var drawTR = function(me){
		
		var svgDom = document.createElementNS('http://www.w3.org/2000/svg','svg');
		svgDom.setAttribute('class','xs_triangle');
		
		if(me.dot < 0){ //无子情况
			svgDom.setAttribute('fill','red');
		}
		if(me.dot === 0){
			svgDom.setAttribute('fill','white');
		};
		if(me.dot === 1){
			svgDom.setAttribute('fill','black');
		}
		svgDom.setAttribute('width',me.offsetWidth*0.5+"px");
		svgDom.setAttribute('height',me.offsetWidth*0.5+"px");
		svgDom.setAttribute('viewBox','0 0 28.336 24.539');
		var polygon = document.createElementNS('http://www.w3.org/2000/svg','polygon');
		polygon.setAttribute('points','14.168,0 21.254,12.269 28.336,24.539 14.168,24.539 0,24.539 7.084,12.269');
		svgDom.appendChild(polygon)
		
		if(!me.label){
			svgDom.style.opacity='0.3'
		}

		me.getElementsByClassName('label')[0].appendChild(svgDom);
	}
	
	var drawLB = function(me,val){
		var LB = document.createElement('div');
		LB.className = 'LB';
		if(!me.label){
			LB.style.opacity='0.3'
		}
		LB.innerHTML = val;
		LB.style.lineHeight = me.offsetHeight*0.6+'px';
		
		LB.style.width = me.offsetHeight*0.6+'px'
		LB.style.height = me.offsetHeight*0.6+'px'
		if(val*1>99){
			LB.style.fontSize =  $this.chess.offsetWidth / 76+'px'
		}else{
			LB.style.fontSize =  $this.chess.offsetWidth / 50+'px' 
		}
		
		if(me.dot < 0){ //无子情况
			LB.style.color = "red";
			LB.className+=' bgLB'
		}
		if(me.dot === 0){
			LB.style.color = "white";
		};
		if(me.dot === 1){
			LB.style.color ="black";
		}
		me.getElementsByClassName('label')[0].appendChild(LB);
	}
	//删除子
	var drawAE = function(me){
		me.getElementsByClassName('pieces')[0].className='pieces';
		me.getElementsByClassName('pieces')[0].innerHTML='';
		if(me.getElementsByClassName('AC')[0]){
			me.removeChild(me.getElementsByClassName('AC')[0])
		}
		me.dot = -1;
		me.LotNum = '';
	}
	
	var drawTS = function(me){
		var TS = document.createElement('div');
	
		//题库的类型 1  落子题  2判断题  3选择题 4 好坏 5 官子6 数气 7 布局
		switch (config.sgfstate){
			case 6:
			TS.className = 'shuqi';
				break;
			case 5:
			TS.className = 'guanzi';
				break;
			case 7:
			TS.className = 'buju';
				break;
			default:
				break;
		}
		
		
		if(!me.label){
			TS.style.opacity='0.3'
		}
		
		me.getElementsByClassName('label')[0].appendChild(TS);
	}
	
	
	
	/* 
		坐标转对象
		根据坐标 和 当前下棋人 返回 棋位如 dom.x=0 dom.y=0 ,当前执白=1  则返回 {W:'aa'}
	 */
	function coordObj (dom,first){
		var a,x,y;
		var obj = {};
		x =  String.fromCharCode(dom.x+97);
		y =  String.fromCharCode(dom.y+97);
		switch (first){
			case 1:
				a='W';
				obj[a]=x+y;
				if(config.isTry){
					obj.lotNum = config.queueTry.length;
				}else{
					if($this.queue[0]){
						if($this.queue[0].B || $this.queue[0].W){
							obj.lotNum = $this.queue.length+1;
						}else{
							obj.lotNum = $this.queue.length;
						}
					}else{
						obj.lotNum = $this.queue.length+1;
					}
				}
				return obj;
				break;
			case 0:
				a='B'
				obj[a]=x+y;
				
				if(config.isTry){
					obj.lotNum = config.queueTry.length;
				}else{
					if($this.queue[0]){
						if($this.queue[0].B || $this.queue[0].W){
							obj.lotNum = $this.queue.length+1;
						}else{
							obj.lotNum = $this.queue.length;
						}
					}else{
						obj.lotNum = $this.queue.length+1;
					}
					
				}
				
				return obj;
				break;
			/* case 'AE':
				a='AE'
				break;
			case 'TR':
				a='TR'
				break;
			case 'SQ':
				a='SQ'
				break;
			case 'CR':
				a='CR'
				break;*/
			case 'LB1':
				a='LB1';
				obj[a]={};
				obj[a].coord = x+y;
				if($this.queue[config.acStep-1].LB1 && $this.queue[config.acStep-1].LB1.length){
					var d = $this.queue[config.acStep-1].LB1.slice(-1);
					obj[a].data = d[0].data+1;
				}else{
					obj[a].data = 1;
				}
				return obj;
				break;
			case 'LBA':
				a='LBA';
				obj[a]={};
				obj[a].coord = x+y;
				if($this.queue[config.acStep-1].LBA && $this.queue[config.acStep-1].LBA.length){
					var d = $this.queue[config.acStep-1].LBA.slice(-1);
					var index = (config.LBA.indexOf(d[0].data)+1)%config.LBA.length;
					//console.log(index)

					obj[a].data = config.LBA[index];
				}else{
					obj[a].data = 'A';
				}
				return obj;
				break;
			default:
				a = first;
				obj[a]=x+y
				return obj;
				break;
		}
		
	}
	
	//对象转换坐标
	function objCoord (dom,first){
		x =  String.fromCharCode(dom.x+97);
		y =  String.fromCharCode(dom.y+97);
		
		if(first===1){
			return 'W'+'['+x+y+']'
		}else if(first===0){
			return 'B'+'['+x+y+']'
		}else if(first===6){
			return '06'+'['+x+y+']'
		}

	}
	
	
	/* 
		获取坐标
	 */
	var xyz = function(msg){
		/* if(!msg){
			return false
		} */
		var data = msg.split('');
		var obj = {};
		var x = msg[0].charCodeAt() - 97;
		var y = msg[1].charCodeAt() - 97;
		obj.x = x;obj.y =y;
		return obj
	}


	

	
	
	//标注最后一手棋
	this.drawAC=function(queue){
		if(!queue.length){  // ||config.isSmilingFace
			return false
		}
		var a = queue[queue.length-1].B || queue[queue.length-1].W || queue[queue.length-1].T;
		if(!a){
			return;
		}
		var xy = xyz(a);
		var me = this.chessBoard[xy.x][xy.y];
		var c = me.getElementsByClassName('AC');
		if(c.length){
			me.removeChild(c[0])
		}
		
		if(me.getElementsByClassName('showLot')[0]){
			me.getElementsByClassName('showLot')[0].style.color='red'
		}
		var AC = document.createElement('div');
		AC.className = 'AC';
		if(this.isShowLot || this.isSmilingFace){
			AC.style.opacity=0
		}
		me.appendChild(AC);

	}

	
	/* 
		格式化 棋谱格式 
		传入 ["B[jj]","W[jk]"] 数据
	 */
	this.queueFormatting=function(d){
		console.log('原棋谱信息：',config.queue)
		var arr = [];
		var index = 0
		for (var x=0;x<d.length;x++) {
			var obj = {};
			var str = d[x].substring(0,d[x].length-1)
			var a = str.split('[');

			if(a[0]==='B' || a[0]==='W'){
				//if(a[1]){
					obj[a[0]] = a[1];
					arr.push(obj);
					index = arr.length-1;
					if(arr[0].B || arr[0].W){
						obj.lotNum = index+1
					}else{
						obj.lotNum = index
					}
				//}
			}else if(a[0] === 'AE' || a[0] === 'TR' || a[0] === 'SQ' || a[0] === 'CR'){
				if(!arr.length){
					arr.push(obj);
				}
				
				if(arr[index][a[0]]){
					arr[index][a[0]].push(a[1]);
				}else{
					arr[index][a[0]]=[];
					arr[index][a[0]].push(a[1]);
				}
			}else if(a[0] === 'LB'){
				var o = {};
				var p = ''
				o.coord = a[1].split(':')[0]
				o.data = a[1].split(':')[1]
				if(o.data*1 === o.data*1){
					p='LB1';
					o.data = o.data*1
				}else{
					p='LBA';
				}
				if(arr[index][p]){
					arr[index][p].push(o);
				}else{
					arr[index][p]=[];
					arr[index][p].push(o);
				}
			}else if(a[0]==='06'){
				if(!arr.length){
					arr.push(obj);
				}
				if(arr[index]['TS']){
					arr[index]['TS'].push(a[1]);
				}else{
					arr[index]['TS']=[];
					arr[index]['TS'].push(a[1]);
				}

			}else if(a[0]==='05'){ //如果最后一手棋 有 05 则不标注最后一手棋
				if(!arr.length){
					arr.push(obj);
				}
				if(arr[index]['TS5']){
					arr[index]['TS5'].push('');
				}else{
					arr[index]['TS5']=[];
					arr[index]['TS5'].push('');
				}

			}
		}
		console.log('转换格式后的：',arr)
		return arr;
	}
	
	//转好格式后的 棋谱 ['B[aa]','W[ab]'} [B:'aa',W:'ab',]
	this.queue = this.queueFormatting(config.queue);
	//转棋谱 把对象 转成 svg 格式[] 用于形式判断等 需要传整个棋谱的功能
	this.svgFormatting =function(array){
		var queue = array || this.queue;
		var arr = []
		for(var i=0;i<queue.length;i++){
			if(queue[i].W){
				arr.push('W'+'['+queue[i].W+']') 
			}
			if(queue[i].B){
				arr.push('B'+'['+queue[i].B+']') 
			}
			if(queue[i].AE){
				for (var j=0;j<queue[i].AE.length;j++){
					arr.push('AE'+'['+queue[i].AE[j]+']') 
				}
			}
		}
		
		return arr;
	}
	
	this.svgFormattingAll =function(){
		
		var arr = []
		for(var i=0;i<this.queue.length;i++){
			if(this.queue[i].W){
				arr.push('W'+'['+this.queue[i].W+']')
				if(this.queue[i].LBA){
					this.queue[i].LBA.forEach(function(item,index){
						arr.push('LB'+'['+item.coord+ ':'+ item.data+']')
					})
				}
			}
			if(this.queue[i].B){
				arr.push('B'+'['+this.queue[i].B+']') 
				if(this.queue[i].LBA){
					this.queue[i].LBA.forEach(function(item,index){
						arr.push('LB'+'['+item.coord+ ':'+ item.data+']')
					})
				}
			}
			if(this.queue[i].AE){
				for (var j=0;j<this.queue[i].AE.length;j++){
					arr.push('AE'+'['+this.queue[i].AE[j]+']') 
				}
			}
		}
		
		return arr;
	}
	
	config.acStep =  this.queue.length
	
	/* 
		填充棋盘
	 */
	
	this.fill=function(queue){
		var sign = 0;//判断第1手棋 是否仅仅是标记没有棋子 
		var q = this.queue.slice(0,config.acStep)
		config.queueCopy = q
		if(queue.length){
			if(queue[0].B || queue[0].W){
				sign = 1
			}
		}
		//if(当前开启了形势判断) {落子关闭形势判断}
		if(config.isJugement){
			$this.jugement()
		}
		if(config.isAIHelper){
			$this.AIHelper()
		}
		
		for (var i=0;i<queue.length;i++) {
			if(queue[i].B){
				var xy = xyz(queue[i].B);
				var me = this.chessBoard[xy.x][xy.y];
				me.dot = 0;
				//if(config.isTry){
				//	me.LotNum=''
				//}else{
					//sign?me.LotNum = i*1+1:me.LotNum = i*1;
					
					me.LotNum = queue[i].lotNum
					config.lotNum++ 
				//}
				console.log('黑子B',xy)
				drawBlack(me);
			}
			
			if(queue[i].W){
				var xy = xyz(queue[i].W);
				var me = this.chessBoard[xy.x][xy.y];
				me.dot = 1;
				//if(config.isTry){
				//	me.LotNum=''
				//}else{
					//sign?me.LotNum = i*1+1:me.LotNum = i*1;
					me.LotNum = queue[i].lotNum
					config.lotNum++
				//}
				console.log('白子W',xy)
				drawWhite(me);
			}
			if(queue[i].AE){
				for (var j=0;j<queue[i].AE.length;j++) {
					var xy = xyz(queue[i].AE[j]);
					//当前棋位信息
					var me = this.chessBoard[xy.x][xy.y];
					
					me.dot = -1;
					console.log('删除子AE',xy)
					//删除子
					drawAE(me)
				}
			}
			if(queue[i].TR && i == queue.length-1){
				console.log('三角形')
				
				for (var j=0;j<queue[i].TR.length;j++) {
					var xy = xyz(queue[i].TR[j]);
					//当前棋位信息
					var me = this.chessBoard[xy.x][xy.y];
					me.label = 'TR';
					//绘方块
					drawTR(me)
				}
			}
			if(queue[i].SQ && i == queue.length-1){
				console.log('正方形')
				for (var j=0;j<queue[i].SQ.length;j++) {
					var xy = xyz(queue[i].SQ[j]);
					//当前棋位信息
					var me = this.chessBoard[xy.x][xy.y];
					me.label = 'SQ';
					//绘方块
					drawSquare(me)
				}
				
			}
			if(queue[i].CR && i == queue.length-1){
				console.log('圈')
				for (var j=0;j<queue[i].CR.length;j++) {
					var xy = xyz(queue[i].CR[j]);
					//当前棋位信息
					var me = this.chessBoard[xy.x][xy.y];
					me.label = 'CR';
					//绘制圆
					drawCircle(me)
				}
			}
			if(queue[i].LB1 && i == queue.length-1){
				console.log('标记数子')
				for (var j=0;j<queue[i].LB1.length;j++) {
					var xy = xyz(queue[i].LB1[j].coord);
					//当前棋位信息
					var me = this.chessBoard[xy.x][xy.y];
					me.label = 'LB1';
					me.labelVal = queue[i].LB1[j].data
					//绘制文字
					drawLB(me,queue[i].LB1[j].data)
				}
			}
			if(queue[i].LBA && i == queue.length-1){
				console.log('标记字母')
				for (var j=0;j<queue[i].LBA.length;j++) {
					var xy = xyz(queue[i].LBA[j].coord);
					//当前棋位信息
					var me = this.chessBoard[xy.x][xy.y];
					me.label = 'LBA';
					me.labelVal = queue[i].LBA[j].data
					//绘制文字
					drawLB(me,queue[i].LBA[j].data)
				}
			}
			
			if(queue[i].TS && i == queue.length-1){
				console.log('特殊')
				for (var j=0;j<queue[i].TS.length;j++) {
					var xy = xyz(queue[i].TS[j]);
					//当前棋位信息
					var me = this.chessBoard[xy.x][xy.y];
					me.label = 'TS';
					//绘制圆
					drawTS(me)
				}
			}
		}
		if(queue.length>0 && !queue[queue.length-1].TS5){
			if(queue.length===1){
				if(sign){
					//标注最后一手棋
					this.drawAC(queue)
				}
			}else{
				//标注最后一手棋
				this.drawAC(queue)
			}
		}
	};


	//鼠标经过
	 
	this.pass = function(e){
		//如果棋局类型为对弈 并且 当前用户颜色 不等于 当前落子颜色 则不允许 使用鼠标经过事件
		if(config.type===3 && config.userFirst!==config.first){
			return false;
		}
		
		//如果开启了确认落子 则 禁用鼠标滑动事件
		if(config.affirmMovelater){
			return false;
		}
		
		var $this = this.$this;
		
		//有05 代表是题库 没有代表 对弈棋盘不允许落子只允许试下  对弈除外
		if(config.type!==3 && config.chessManual && config.isTry == 0){
			return false;
		}
		
		var x = this.x;
		var y = this.y;
		var me = $this.chessBoard[x][y]; //当前棋位信息
		
		if(!this.label){

			switch (config.editStatus){
				case 'CR':
					drawCircle(me) //绘圈
					break;
				case 'SQ':
					drawSquare(me) //绘方块
					break;
				case 'TR':
					drawTR(me) //绘方块
					break;	
				case 'LB1':
					var val=1
					if($this.queue[config.acStep-1].LB1 && $this.queue[config.acStep-1].LB1.length){
						var d = $this.queue[config.acStep-1].LB1.slice(-1)
						val = d[0].data+1
					}
					drawLB(me,val) //绘文字
					break;
				case 'LBA':
					var val='A'
					if($this.queue[config.acStep-1].LBA && $this.queue[config.acStep-1].LBA.length){
						var d = $this.queue[config.acStep-1].LBA.slice(-1);
						var index = (config.LBA.indexOf(d[0].data)+1)%config.LBA.length;
						//console.log(index)
					
						val = config.LBA[index];
					}

					drawLB(me,val) //绘文字
					break;	
				default:
					break;
			}
		}
		if(this.dot===-1){
			
			if(config.editStatus =='BW'||config.editStatus =='WB' ||config.editStatus =='B'||config.editStatus =='W'){
				if(config.first){
					drawWhite(me)
				}else{
					drawBlack(me) 
					
				}
			}
		}

	}
	//移除
	this.pullOut = function(e){
		var $this = this.$this;
		if(!this.label){
			var x = this.x;
			var y = this.y;
			var me = $this.chessBoard[x][y]; //当前棋位信息
			var label = me.getElementsByClassName('label')[0];
			label.innerHTML = '';
		}
		if(this.dot===-1){
			var x = this.x;
			var y = this.y;
			var me = $this.chessBoard[x][y]; //当前棋位信息
			var pieces = me.getElementsByClassName('pieces')[0];
			pieces.className = 'pieces'
		}
		
	}
	//落子事件
	this.play=function(qw){
		/* console.log(5899,qz)
		return false; */
		var me;
		if(qw.dot!=undefined){
			me = qw;
		}else{
			me = this;
		}
		
		console.log(5555,me)
		
		//题库
		if(config.type === 2){
			//数气题
			if(config.sgfstate ===6 || config.sgfstate ===5){
				if(!me.label && me.dot<0){
					$this.playCall(objCoord(me,6))
				}
			}else if(config.sgfstate ===7){ //布局（不是三四线）
				if(!me.label){
					$this.playCall(objCoord(me,6))
				}
			}else if(config.sgfstate ===4){ //好坏题 禁止落子
				return false;
			}else if(config.sgfstate ===2){ //判断题 禁止落子
				return false;
			}
			else{
				if(me.dot<0){
					$this.playCall(objCoord(me,config.first))
				}
			}
			return false;
		}
		//对弈
		if(config.type === 3){
			if(me.dot<0 && config.userFirst===config.first){
				$this.playCall(objCoord(me,config.first))
			}
			return false;
		}
		

		//落子
		if(['BW','WB','B','W'].indexOf(config.editStatus)>=0){
			if(config.chessManual && config.isTry == 0){
				return false;
			}
			
			
			//如果当前棋位 无子
			if(me.dot<0){
				//如果前进后退中落子 则已当前显示的棋谱数据为基准增加棋子
				$this.queue = config.queueCopy;

				//判断是否可以提子
				var removeBlock = $this.removeBlock(me.x, me.y , config.first)
				if(removeBlock.length){

					//打劫  比如当前是 黑下  黑子落在 B[cc]位置，正好提了一个子，下手棋白落子也正好是提了一个子并且提的子时B[cc] 此时为打劫 
					
					//先判断是否仅仅提了1个子
					if(removeBlock.length === 1){
						//config.rob=上次提1个子的落子点，如果等于 当前提的子的点  则是打劫
						if(config.rob === removeBlock[0].x + removeBlock[0].y){
							alert('打劫！')
							return false;
						}else{
							//否则记录当前落子位置 防止下次打劫
							config.rob = me.x + me.y;
						}

					}else{
						//如果提子数超过1 清空 打劫记录
						config.rob = ''
					}
					console.log('可以提子',removeBlock);
					
					//先落下子
					if(config.isTry){
						config.queueTry.push(coordObj(me,config.first))
					}
					$this.queue.push(coordObj(me,config.first))
					//1代表有提子播放提子的音效
					$this.playAudio && $this.playAudio(1)
					
					//同时改变当前当前为最后一手棋 防止再次进退出问题
					config.acStep = $this.queue.length

					for(var i=0;i<removeBlock.length;i++){
						//得到提子的坐标 数组
						if($this.queue[$this.queue.length-1].AE){
							$this.queue[$this.queue.length-1].AE.push(coordObj(removeBlock[i],'AE').AE)
						}else{
							$this.queue[$this.queue.length-1].AE = [];
							$this.queue[$this.queue.length-1].AE.push(coordObj(removeBlock[i],'AE').AE)
						}
					}
					

				}else{	//如果不能提子 则判断是否气紧,落子必须有气，否则属于自杀不允许
					
					
					config.lump = []; //清空块
					config.life =[]; //清空气
					me.dot = config.first
					if($this.countLiberty(me.x,me.y,config.first)>0){ //如果有气则可以落子 否则禁入点
						
						//落子清空打劫记录
						config.rob = ''

						//先落下子
						if(config.isTry){
							config.queueTry.push(coordObj(me,config.first))
						}
						$this.queue.push(coordObj(me,config.first))
						//0代表没有提子播放落子的音效
						$this.playAudio && $this.playAudio(0)
						//同时改变当前当前为最后一手棋 防止再次进退出问题
						config.acStep = $this.queue.length

					}else{
						me.dot = -1
						//alert('禁入点！')
						return false
					}
				}

				console.log($this.queue)
				
				
				if(config.isSmilingFace){
					$this.init()
				}else{
					$this.addChess()
				}
				
				
				//$this.init()
				if(config.editStatus!=='B'&& config.editStatus!=='W'){
					config.first^=1
				}
				
			}
		};
		
		//标记

		var index;
		config.acStep===0? index = 0 : index = config.acStep-1;
		
		switch (config.editStatus){
			case 'TR':
				if(this.label){
					$this.queue[index][config.editStatus].remove(coordObj(this,'TR').TR)
				}else{
					if(!$this.queue[index]){
						$this.queue[index] ={}
					}
					if($this.queue[index][config.editStatus]){
						$this.queue[index][config.editStatus].push(coordObj(this,'TR').TR)
					}else{
						$this.queue[index][config.editStatus]=[]
						$this.queue[index][config.editStatus].push(coordObj(this,'TR').TR)
					}
				}
	//			config.acStep = $this.queue.length
				$this.init()
				break;
			case 'SQ':
				if(this.label){
					$this.queue[index][config.editStatus].remove(coordObj(this,'SQ').SQ)
				}else{
					if(!$this.queue[index]){
						$this.queue[index] ={}
					}
					if($this.queue[index][config.editStatus]){
						$this.queue[index][config.editStatus].push(coordObj(this,'SQ').SQ)
					}else{
						$this.queue[index][config.editStatus]=[]
						$this.queue[index][config.editStatus].push(coordObj(this,'SQ').SQ)
					}
				}
	//			config.acStep = $this.queue.length
				$this.init()
				break;
			case 'CR':
				if(this.label){
					$this.queue[index][config.editStatus].remove(coordObj(this,'CR').CR)
				}else{
					if(!$this.queue[index]){
						$this.queue[index] ={}
					}
					if($this.queue[index][config.editStatus]){
						$this.queue[index][config.editStatus].push(coordObj(this,'CR').CR)
					}else{
						$this.queue[index][config.editStatus]=[]
						$this.queue[index][config.editStatus].push(coordObj(this,'CR').CR)
					}
				}
	//			config.acStep = $this.queue.length
				$this.init()
				break;
			case 'LB1':
				if(this.label){
					var d = coordObj(this,'LB1').LB1;
					for (var i=0;i<$this.queue[index][config.editStatus].length;i++) {
						if($this.queue[index][config.editStatus][i].coord === d.coord){
							$this.queue[index][config.editStatus].splice(i,1)
						}
					}
				}else{
					if($this.queue[index][config.editStatus]){
						$this.queue[index][config.editStatus].push(coordObj(this,'LB1').LB1)
					}else{
						if(!$this.queue[index]){
							$this.queue[index] ={}
						}
						$this.queue[index][config.editStatus]=[]
						$this.queue[index][config.editStatus].push(coordObj(this,'LB1').LB1)
					}
				}
	//			config.acStep = $this.queue.length
				$this.init()
				break;
			case 'LBA':
				if(this.label){
					var d = coordObj(this,'LBA').LBA;
					for (var i=0;i<$this.queue[index][config.editStatus].length;i++) {
						if($this.queue[index][config.editStatus][i].coord === d.coord){
							$this.queue[index][config.editStatus].splice(i,1)
						}
					}
				}else{
					if($this.queue[index][config.editStatus]){
						$this.queue[index][config.editStatus].push(coordObj(this,'LBA').LBA)
					}else{
						if(!$this.queue[index]){
							$this.queue[index] ={}
						}
						$this.queue[index][config.editStatus]=[]
						$this.queue[index][config.editStatus].push(coordObj(this,'LBA').LBA)
					}
				}
	//			config.acStep = $this.queue.length
				$this.init()
				break;
			default:
				break;
		}
		

	}
	
	function adf(a){
		var dom = a.getElementsByClassName('pieces')[0];
		var p = document.createElement('div');
		if(config.first){
			p.className = 'illusory white';
		}else{
			p.className = 'illusory black';
		}
		p.style.width = '100%';
		p.style.height = '100%';
		p.style.opacity = 0.8
		dom.appendChild(p)
	}
	
	//开启确认落子后 返回当前状态 0标记了未落子 1已经落子了
	this.qrlzState = function(Val){
		
	}
	
	//如果开启了确认落子 不再执行play 执行
	this.aPlay = function(){
		if(config.type===3){
			if(config.userFirst===config.first){
				var ps = $this.chess.getElementsByClassName('illusory')[0]
				if(ps){
					ps.parentNode.removeChild(ps)
				}
				$this.qrlzState(1) //落子状态 1 是虚的  0 已经落子了
				adf(this)
			}
			
		}else{
			var ps = $this.chess.getElementsByClassName('illusory')[0]
			if(ps){
				ps.parentNode.removeChild(ps)
			}
			$this.qrlzState(1) //落子状态 1 是虚的  0 已经落子了
			adf(this)
		}
	}
	this.steering = function(wsade){
		var ps = $this.chess.getElementsByClassName('illusory')[0];
		if(ps){
			fn(ps.parentNode.parentNode,wsade)
		}else{
			return false;
		}

		function fn(me,wsade){
			var x = me.x,y=me.y;

			switch (wsade){
				case 'w': //上
					y ++;
					break;
				case 's': //下
					y --
					break;
				case 'a': //左
					x --
					break;
				case 'd': //右
					x ++
					break;
				case 'e': //确认
					ps.parentNode.removeChild(ps);
					$this.play(me);
					$this.qrlzState(0) 
					return false;
					break;
				default:
					break;
			}
			
			var qw = $this.GetSite(x,y)
			
			if(qw){
				if(qw.dot!==-1){ //说明有子
					fn(qw,wsade)
				}else{ //当前棋位无子
					ps.parentNode.removeChild(ps);
					adf(qw)
				}
			}
			
			
		}

	}
	//棋盘构造函数
	this.drawChess = function($this){
		var chess = document.getElementById(el);
		//棋盘宽度 通过计算每一个棋位 反推棋盘宽度 取整防止浮点数带来的定位偏差
		var oW = Math.floor(chess.offsetWidth*0.95 / config.road) * config.road 
		
		//初始化元素防止重复
		var num = chess.childNodes.length
		for (var i=0;i<num;i++) {
			chess.removeChild(chess.childNodes[0])
		}
		
		chess.className +=' xswq skin1';
		chess.style.backgroundColor = config.bgColor;
		chess.style.position = 'relative';
		
		
		/* 线 */
		var chessline = document.createElement("div");
		/* 棋位 */
		var chessroad = document.createElement("div");
		/* 坐标 */
		var coordX = document.createElement('div');
		var coordY = document.createElement('div');
		coordX.className = 'coordX';
		coordY.className = 'coordY';
		
		coordX.style.fontSize = chess.offsetWidth/60+'px'
		coordX.style.width = oW+'px'
		coordY.style.fontSize = chess.offsetWidth/60+'px'
		coordY.style.height = oW+'px'
		
		if(config.isShowCoord){
			coordX.style.display = 'flex';
			coordY.style.display = 'flex';
		}else{
			coordX.style.display = 'none';
			coordY.style.display = 'none';
		}
		
		
		chess.appendChild(coordX)
		chess.appendChild(coordY)
		
		var chessOutline = document.createElement("div");

		chessroad.className = 'chessroad'
		chessroad.style.width = oW+'px';
		chessroad.style.height = oW+'px';
		chessroad.style.margin='auto';
		chessroad.style.position = 'absolute';
		chessroad.style.top='0px';
		chessroad.style.left='0px';
		chessroad.style.bottom='0px';
		chessroad.style.right='0px';
		chessroad.style.zIndex =3;
		chess.appendChild(chessroad)
		
		chessline.className ='chessline';
		chessline.style.margin='auto';
		chessline.style.position = 'absolute';
		chessline.style.top='0px';
		chessline.style.left='0px';
		chessline.style.bottom='0px';
		chessline.style.right='0px';
		chessline.style.zIndex=2;
		chessline.style.width = chessroad.offsetWidth - chessroad.offsetWidth / config.road +'px' ;
		chessline.style.height = chessroad.offsetHeight - chessroad.offsetHeight / config.road +'px' ;
		chess.appendChild(chessline)

		this.chess = chess
		//return chess
	}
	this.drawChess()
	
	//棋盘的线
	this.chessLine = function(){
		var chessline = this.chess.getElementsByClassName('chessline')[0];
			chessline.style.outline= config.lineWidth +'px'+' solid '+config.lineColor
			
			var lineXBox = document.createElement("div"); //横线
			var lineYBox = document.createElement("div"); //纵线
			lineXBox.className = 'lineBox'
			lineYBox.className = 'lineBox'
			
			for(var x=1;x < config.road-1;x++){
				var lineY = document.createElement("div");
				var lineX = document.createElement("div");
				lineY.className = 'lineY';
				lineY.style.width = chessline.offsetWidth / (config.road-1)+'px'
				lineY.style.borderRight = config.lineWidth +'px'+' solid '+config.lineColor;

				lineX.className = 'lineX';
				lineX.style.height = chessline.offsetWidth / (config.road-1)+'px'
				lineX.style.borderBottom = config.lineWidth +'px'+' solid '+config.lineColor;
				// if(x==1){
				// 	lineY.style.borderLeft = config.lineWidth +'px'+' solid '+config.lineColor;
				// 	lineX.style.borderTop = config.lineWidth +'px'+' solid '+config.lineColor;
				// }
				lineYBox.appendChild(lineY);
				lineXBox.appendChild(lineX)
				
			}
			
		/* for(var x=1;x < config.road-1;x++){
			var lineX = document.createElement("div"); //横线
			var lineY = document.createElement("div"); //纵线
			
			lineX.style.width = '100%';
			lineX.style.height = config.lineWidth+'px';
			lineX.style.position = 'absolute'
			lineX.style.background= config.lineColor;
			lineX.style.top= x*(100/(config.road-1))+'%'
			lineX.style.left = '0px'
			
			lineY.style.width = config.lineWidth+'px';
			lineY.style.height = '100%'
			lineY.style.position = 'absolute'
			lineY.style.background= config.lineColor;
			lineY.style.top='0px'
			lineY.style.left = x*(100/(config.road-1))+'%'
			chessline.appendChild(lineX)
			chessline.appendChild(lineY)
		} */
		
		chessline.appendChild(lineXBox);
		chessline.appendChild(lineYBox);
		
		var bk = document.createElement("div"); //纵线
			bk.className = 'outline'

			
			bk.style.width = chessline.offsetWidth +15+'px';
			bk.style.height = chessline.offsetWidth+15+'px';
			
			bk.style.position = 'absolute'
			bk.style.margin = 'auto'
			bk.style.top = '0px'
			bk.style.bottom = '0px'
			bk.style.left = '0px'
			bk.style.right = '0px'
			
			var outline = document.createElement("div")
			outline.style.width = chessline.offsetWidth +8+'px';
			outline.style.height = chessline.offsetWidth+8+'px';
			outline.style.border='2px solid '+config.lineColor;
			outline.style.position = 'absolute'
			outline.style.margin = 'auto'
			outline.style.top = '0px'
			outline.style.bottom = '0px'
			outline.style.left = '0px'
			outline.style.right = '0px'
			bk.appendChild(outline)
			this.chess.appendChild(bk)
	}
	
	
	
	//棋位构造函数
	this.createroad= function(x, y){ //棋位类的构造函数
		
		
	
		var chessroad = this.chess.getElementsByClassName('chessroad')[0];
		var coordX = this.chess.getElementsByClassName('coordX')[0];
		var coordY = this.chess.getElementsByClassName('coordY')[0];
		var me = document.createElement("div"); //建一个div对象，将其扩展并封装成棋位。

		me.x = x;   //记录棋位的X坐标
		me.y = y;   //记录棋位的Y坐标
		me.dot = -1; //棋位状态 -1空 0黑  1白
		me.label = ''; //标记 CR 圈
		me.LotNum = ''; 
		
		me.onmouseenter = this.pass;
		me.onmouseleave = this.pullOut;
		
		
		
		if(config.affirmMovelater){
			me.onclick = this.aPlay; //确认落子开启时绑定的落子处理事件
		}else{
			me.onclick = this.play; //绑定落子处理事件
		}
		
		me.$this = this;
		//使其坐标从左下角开始 0,0
		var meW = chessroad.offsetWidth/config.road;
		me.style.position= 'absolute';
		me.style.width = 100/(config.road)+'%';
		me.style.height = 100/(config.road)+'%';
		me.style.left= x*meW+'px';
		me.style.bottom = y*meW+'px';
		
		if(x && y && x<=15 && y<=15  && x%3===0 && y%3===0 && x%6!==0 && y%6!==0 &&config.road ===19){
			console.log(x,y)
			me.className = 'xingwei'
		}
		if(x && y && x<=6 && y<=6  && x%2===0 && y%2===0 && x%4!==0 && y%4!==0 &&config.road ===9){
			console.log(x,y)
			me.className = 'xingwei'
		}
		
		if(y===0 ){
			var scale = document.createElement("div"); //坐标刻度
			scale.className = 'scaleStr';
			scale.innerText = config.LBA[x]
			coordX.appendChild(scale)
		}
		if(x===0 ){
			var scale = document.createElement("div"); //坐标刻度
			scale.className = 'scaleNum';
			scale.innerText = config.road- y
			coordY.appendChild(scale)
		}
		
		var pieces = document.createElement("div"); //棋子层
		var label = document.createElement("div")
		pieces.className = 'pieces';
		label.className = 'label'
		
		me.appendChild(pieces);
		me.appendChild(label);
		chessroad.appendChild(me);
		
		
		
		return me;              //返回棋位对象，其实是一个封装了的div对象。
	};
	
	//对应棋盘二维数组
	this.chessArr = function(){
		
		var chessBoard = new Array(config.road);
		for (var x=0;x<chessBoard.length;x++) {
			chessBoard[x] = new Array(config.road);
			for (var y=0;y<chessBoard[x].length;y++) {
				chessBoard[x][y] = this.createroad(x,y)
			}
		}
		var chessroad = this.chess.getElementsByClassName('chessroad')[0];
		var chessline = this.chess.getElementsByClassName('chessline')[0];
		
		//config.chessroad = chessroad.innerHTML;
		//config.chessBoard = chessBoard
		console.log(chessBoard)
		this.chessBoard = chessBoard;
		this.chessLine();
	};
	
	//如果越界 避免报错
	this.GetSite=function(x, y){ 
		if (x>=0 && x<config.road && y>=0 && y<config.road)
			return this.chessBoard[x][y]
	};
	//数气(块)
	this.countLiberty=function(x,y,color){
		var q = 0;
		config.lump.push(this.chessBoard[x][y])
		for(var dx=-1;dx<=1;dx++){
			for(var dy=-1;dy<=1;dy++){
				if(!dx^!dy) //异或,有且只有一个为真,可对应到相邻子.
				{
				//console.log(this.GetSite(x+dx,y+dy))
				// console.log(x+dx,y+dy) //当前子的上下左右四个棋位 
				 if(this.GetSite(x+dx,y+dy)&& this.GetSite(x+dx,y+dy).dot === -1 && config.life.indexOf(this.chessBoard[x+dx][y+dy])<0) {
					 q++;
					 config.life.push(this.GetSite(x+dx,y+dy))
				 }
				 if(this.GetSite(x+dx,y+dy) && this.GetSite(x+dx,y+dy).dot === color && config.lump.indexOf(this.chessBoard[x+dx][y+dy])<0) //同颜色
					q+= this.countLiberty(x+dx,y+dy,color)
				}
				if(this.GetSite(x+dx,y+dy) && this.GetSite(x+dx,y+dy).dot !== -1  && this.GetSite(x+dx,y+dy).dot !== color){}
			}
		} 
		console.log(this.chessBoard[x][y],q)
		return q;
	};
	
	//计算可以提子的块
	this.removeBlock=function(x,y,color){
		var unlike = []; //相邻异色棋位
		var dels = [];
		for(var dx=-1;dx<=1;dx++){
			for(var dy=-1;dy<=1;dy++){
				if(!dx^!dy){
					if(this.GetSite(x+dx,y+dy) && this.GetSite(x+dx,y+dy).dot !== -1  && this.GetSite(x+dx,y+dy).dot !== color){
						unlike.push(this.GetSite(x+dx,y+dy));
					}
				}
			}
		}
		if(unlike.length)
		for (var i=0;i<unlike.length;i++ ) {
			
			//console.log(55,this.countLiberty(unlike[i].x,unlike[i].y,unlike[i].dot))
			//console.log('块',config.lump)
			config.lump = []; //清空块
			config.life =[]; //清空气
			 if(this.countLiberty(unlike[i].x,unlike[i].y,unlike[i].dot)==1){
				dels = dels.concat(config.lump)
			} 
		}
		
		//去重
		function unique1(arr){
			var hash=[];
			for (var i = 0; i < arr.length; i++) {
				if(hash.indexOf(arr[i])==-1){
					hash.push(arr[i]);
				}
			}
			return hash;
		}
		return unique1(dels)
	};
	
	
	
	
	
	/* 
		方法
	 */
	
	/* 初始化 */
	this.init = function(){
		
		var q = this.queue.slice(0,config.acStep)
		var chessroad = this.chess.getElementsByClassName('chessroad')[0];
		var chessline = this.chess.getElementsByClassName('chessline')[0];
		
		chessline.style.width = chessroad.offsetWidth - chessroad.offsetWidth / config.road +'px' ;
		chessline.style.height = chessroad.offsetHeight - chessroad.offsetHeight / config.road +'px' ;
		
		
		//chessroad.innerHTML = '';
		//chessline.innerHTML = '';
		console.time()
		if(this.chessBoard){
			
			for(var x =0;x<this.chessBoard.length;x++){
				for(var y=0;y<this.chessBoard[x].length;y++){
					//if(this.chessBoard[x][y].dot!=-1 || this.chessBoard[x][y].label!==''){
						this.chessBoard[x][y].dot = -1;
						this.chessBoard[x][y].label = '';
						this.chessBoard[x][y].LotNum = '';
						this.chessBoard[x][y].innerHTML = '<div class="pieces"></div><div class="label"></div>';
					//}
				}
			}
			
		}else{
			this.chessArr(); //重置棋位
		}
		

		this.fill(q); //重置落子信息
		console.timeEnd()
		if(config.isSmilingFace){
			this.smilingFace()
		}
		//
		if($this.queueTraverse){
			$this.TryShowLot()
		}
		
		
		
		

		//callback?callback():''
		/* if(this.isShowLot === undefined){
			this.isShowLot = config.isShowLot
		} */
	};
	
	// 添加子  前进
	this.addChess = function(queue){
		var q ;
		
		if(queue){
			q = queue
		}else{
			q = $this.queue.slice(-2)
		}
		
		
		for(var i = 0;i<q.length;i++){
			
			if(q[i].B){
				clearChess(xyz(q[i].B).x,xyz(q[i].B).y)
			}
			if(q[i].W){
				clearChess(xyz(q[i].W).x,xyz(q[i].W).y)
			}
			if(q[i].TR){
				
				for(var j=0;j<q[i].TR.length;j++){
					clearLebel(xyz(q[i].TR[j]).x,xyz(q[i].TR[j]).y)
				}
			}
			if(q[i].SQ){
				for(var j=0;j<q[i].SQ.length;j++){
					clearLebel(xyz(q[i].SQ[j]).x,xyz(q[i].SQ[j]).y)
				}
			}
			if(q[i].CR){
				for(var j=0;j<q[i].CR.length;j++){
					clearLebel(xyz(q[i].CR[j]).x,xyz(q[i].CR[j]).y)
				}
			}
			if(q[i].LBA){
				for(var j=0;j<q[i].LBA.length;j++){
					clearLebel(xyz(q[i].LBA[j].coord).x,xyz(q[i].LBA[j].coord).y)
				}
			}
			if(q[i].LB1){
				for(var j=0;j<q[i].LB1.length;j++){
					clearLebel(xyz(q[i].LB1[j].coord).x,xyz(q[i].LB1[j].coord).y)
				}
			}
			
		}
		
		function clearChess(x,y){
			$this.chessBoard[x][y].dot = -1;
			$this.chessBoard[x][y].label = '';
			$this.chessBoard[x][y].LotNum = '';
			$this.chessBoard[x][y].innerHTML = '<div class="pieces"></div><div class="label"></div>';
		}
		function clearLebel(x,y){
			$this.chessBoard[x][y].label = '';
			$this.chessBoard[x][y].getElementsByClassName('label')[0].innerHTML = ''
		}
		
		$this.fill(q)
		if(config.isSmilingFace){
			this.smilingFace()
		}
	}
	

	/* 第0手 */
	this.first = function(){
		if(config.acStep>0){
			config.acStep = 0;

			//重新初始化棋谱
			this.init()
		}
	}
	
	/* 后退一手 */
	this.next = function(){
		if(config.acStep>0){
			config.acStep --;
			
			if(config.editStatus =='BW' || config.editStatus =='WB'){
				if(config.acStep>0){
					//判断上一手棋是黑白
					if(this.queue[config.acStep-1].B){
						config.first = 1;
					}else{
						config.first = 0;
					}
				}
				
			}

			//重新初始化棋谱
			this.init()
			/* var q = this.queue.slice(0,config.acStep)
			console.log(q)
			this.addChess(q) */
		}
		
	}
	
	/* 后退5手 */
	this.nexts = function(){

		if(config.acStep>0){
			config.acStep -=5;
			if(config.acStep<0){
				config.acStep =0;
			}
			
			if(config.editStatus =='BW' || config.editStatus =='WB'){
				if(config.acStep>0){
					if(this.queue[config.acStep-1].B){
						config.first = 1;
					}else{
						config.first = 0;
					}
				}
			}
			
			//重新初始化棋谱
			this.init()
		}
		
	}
	
	/* 前进一手 */
	this.prev = function(){
		if(config.acStep<this.queue.length){
			var start = config.acStep-1<0?0:config.acStep-1
			config.acStep ++;
			if(config.editStatus =='BW' || config.editStatus =='WB'){
				if(config.acStep>0){
					if(this.queue[config.acStep-1].B){
						config.first = 1;
					}else{
						config.first = 0;
					}
				}
			}
			var end = config.acStep;
			var q = this.queue.slice(start,end)
			//重新初始化棋谱
			//this.addChess(q)
			
			if(config.isSmilingFace){
				this.init()
			}else{
				this.addChess(q)
			}
			
			
		}
	}
	/* 快进5手 */
	this.prevs = function(){
		
		if(config.acStep < this.queue.length){
			var start = config.acStep-1
			
			config.acStep +=5;
			if(config.acStep > this.queue.length){
				config.acStep =this.queue.length;
			}
			var end = config.acStep
			
			var q = this.queue.slice(start,end)
			
			//重新初始化棋谱
			//this.addChess(q)
			if(config.isSmilingFace){
				this.init()
			}else{
				this.addChess(q)
			}
		}
	}
	/* 最后一手 */
	this.last = function(){
		if(config.acStep < this.queue.length){
			config.acStep = this.queue.length;
			//重新初始化棋谱
			this.init()
		}
		
	}
	
	//隐藏手数
	this.hideLot = function(){
		var lot = this.chess.getElementsByClassName('showLot');
		for (var i=0;i<lot.length;i++) {
			lot[i].style.display = 'none'
		}
		this.drawAC(config.queueCopy)
	}
	//关闭console.log
	this.closeLog = function(){
		console.log = function(){}
	}
	//this.closeLog()
	//开启console.log
	this.startLog =function(){
		console.log = config.log
	}
	
	//切换 落子状态
	
	this.changeFirst=function(d){
		config.first = d;
		switch (d){
			case 'BW':
				config.editStatus = d;
				config.first = 0;
				break;
			case 'B':
				config.editStatus = d;
				config.first = 0;
				break;
			case 'WB':
				config.editStatus = d;
				config.first = 1;
				break;
			case 'W':
				config.editStatus = d;
				config.first = 1;
				break;
			case 'TR':
				config.editStatus = d;
				break;
			case 'SQ':
				config.editStatus = d;
				break;
			case 'CR':
				config.editStatus = d;
				break;
			case 'LB1':
				config.editStatus = d;
				break;
			case 'LBA':
				config.editStatus = d;
				break;
			default:
				break;
		}
		
		
		
		
	}
	
	this.delLabel = function(){
		if(config.acStep===1){
			this.queue.splice(0,1)
		}else{
			var obj = {};
			for (var i in this.queue[config.acStep-1]) {
				if(i === 'B'){
					obj.B = this.queue[config.acStep-1].B
					obj.lotNum = this.queue[config.acStep-1].lotNum
				}else if(i === 'W'){
					obj.W = this.queue[config.acStep-1].W
					obj.lotNum = this.queue[config.acStep-1].lotNum
				}else if(i === 'AE'){
					obj.AE = this.queue[config.acStep-1].AE
				}
			}
			this.queue[config.acStep-1] = obj
		}
		this.init()
	}
	//开始试下
	this.startTry=function(){
		this.queueTraverse = this.queue;
		config.acStepTyr = config.acStep;
		config.isTry = 1;
		if( this.queue.length){
			config.temp = this.queue[this.queue.length-1].lotNum
			this.queue[this.queue.length-1].lotNum = ''
		}
		
		
		var dom = $this.chess.getElementsByClassName('showLot');
		for(var i=0;i<dom.length;i++){
			dom[i].style.display = 'none'
		}
		console.log('开启试下')
	}
	//结束试下
	this.endTry=function(){
		this.queue = this.queueTraverse;
		if( this.queue.length){
			this.queue[this.queue.length-1].lotNum = config.temp
		}
		
		this.queueTraverse = '';
		config.acStep = config.acStepTyr;
		config.acStepTyr = '';
		config.isTry = 0;
		config.queueTry = []
		this.init()
		console.log('试下结束')
	}
	
	
	//切换笑脸
	this.smilingFace = function(){
		config.lump = []; //清空块
		config.life =[]; //清空气
		var arr=[]
		for (var i=0;i<config.queueCopy.length;i++) {
			
			if(config.queueCopy[i].B){
				var xy = xyz(config.queueCopy[i].B)
				var me = this.chessBoard[xy.x][xy.y]
				console.log(arr.indexOf(me))
				if(arr.indexOf(me)<0){
					fn(me)
				}
				
			}else if(config.queueCopy[i].W){
				var xy = xyz(config.queueCopy[i].W)
				var me = this.chessBoard[xy.x][xy.y]
				if(arr.indexOf(me)<0){
					fn(me)
				}
				
			}
		}
		function fn(me){
			var lifeNum = $this.countLiberty(me.x,me.y,me.dot)
			arr=arr.concat(config.lump)
			
			//关闭标注最后一手
			var c = me.getElementsByClassName('AC');
			if(c.length){
				me.removeChild(c[0])
			}
			if( lifeNum>=4){
				for(var j=0;j<config.lump.length;j++){
					if(config.lump[j].dot){
						config.lump[j].getElementsByClassName('pieces')[0].className+=' white_4'
					}else{
						config.lump[j].getElementsByClassName('pieces')[0].className+=' black_4'
					}
				}
				
				
			}else if(lifeNum ==3){
				for(var j=0;j<config.lump.length;j++){
					if(config.lump[j].dot){
						config.lump[j].getElementsByClassName('pieces')[0].className+=' white_3'
					}else{
						config.lump[j].getElementsByClassName('pieces')[0].className+=' black_3'
					}
				}
				/* if(me.dot){
					me.getElementsByClassName('pieces')[0].className+=' white_3'
				}else{
					me.getElementsByClassName('pieces')[0].className+=' black_3'
				} */
			}else if(lifeNum ==2){
				for(var j=0;j<config.lump.length;j++){
					if(config.lump[j].dot){
						config.lump[j].getElementsByClassName('pieces')[0].className+=' white_2'
					}else{
						config.lump[j].getElementsByClassName('pieces')[0].className+=' black_2'
					}
				}
				/* if(me.dot){
					me.getElementsByClassName('pieces')[0].className+=' white_2'
				}else{
					me.getElementsByClassName('pieces')[0].className+=' black_2'
				} */
			}else if(lifeNum ==1){
				for(var j=0;j<config.lump.length;j++){
					if(config.lump[j].dot){
						config.lump[j].getElementsByClassName('pieces')[0].className+=' white_1'
					}else{
						config.lump[j].getElementsByClassName('pieces')[0].className+=' black_1'
					}
				}
				/* if(me.dot){
					me.getElementsByClassName('pieces')[0].className+=' white_1'
				}else{
					me.getElementsByClassName('pieces')[0].className+=' black_1'
				} */
			}
			config.lump = []; //清空块
			config.life =[]; //清空气
		}
	}
	
	
	//添加棋子坐标
	
	this.addQueue=function(data){
		var q = this.queueFormatting(data)
		
		if(q[0].TS){
			console.log(q)
			if(this.queue[this.queue.length-1].TS){
				this.queue[this.queue.length-1].TS.push(q[0].TS[0])
			}else{
				this.queue[this.queue.length-1].TS = []
				this.queue[this.queue.length-1].TS.push(q[0].TS[0])
			}
			console.log(this.queue)
			this.init()
		}else{
			this.queue = this.queue.concat(q);
			//根据最后一手棋判断下一手棋该谁下
			if($this.queue[$this.queue.length-1].B || $this.queue[$this.queue.length-1].B === ''){
				config.first = 1
			}else{
				config.first = 0
			}
			
			this.addChess()
		}
	}
	
	// 形式判断 返回黑棋白棋所占的目数 B黑 W 白
	
	this.jugement = function(data){
		
		if(config.isJugement){
			config.isJugement = 0;
			var W = this.chess.getElementsByClassName('jugementW')
			var B = this.chess.getElementsByClassName('jugementB')
			for(var i=0;i<W.length;){
				W[0].parentNode.removeChild(W[0])
			}
			for(var i=0;i<B.length;){
				B[0].parentNode.removeChild(B[0])
			}

			return false;
		}
		
		config.isJugement = 1;

		var f = this.queue[this.queue.length-1];
		
		if(!f.B && !f.W){ //如果都为空可能是双方停一手时 最后一首为空棋子
			f = this.queue[this.queue.length-2];
		}
		
		var roleA,roleB;
		var A = 0,B=0;
		if(f.B){
			roleA = 'jugementW';
			roleB = 'jugementB';
		}else if(f.W){
			roleA = 'jugementB';
			roleB = 'jugementW';
		}else{
			return false;
		}
		for(var i=1;i<=data.length;i++){
			if(data[i-1]){
				var x  =  (i-1)%19;
				var y = 19-Math.ceil(i/19);
				var me = this.chessBoard[x][y];
				var xs = document.createElement('div');
				if(data[i-1]===1){
					xs.className = roleA
					A++
				}else if(data[i-1]===2 && me.opt===0){
					xs.className = roleA
					A++
				}else if(data[i-1]===-1){
					xs.className = roleB
					B++
				}else if(data[i-1]===-2 && me.opt===1){
					xs.className = roleB
					B++
				}
				me.appendChild(xs)
				
			} 
		}
		if(roleA === 'jugementB'){
			return {B:A,W:B}
		}else{
			return {B:B,W:A}
		}
		
	};
	
	//AI支招
	this.AIHelper = function(data){
		
		if(config.isAIHelper){
			config.isAIHelper = 0;
			var d = this.chess.getElementsByClassName('aiHelper')
			for(var i=0;i<d.length;){
				d[0].parentNode.removeChild(d[0])
			}
			return false;
		}
		config.isAIHelper = 1;
		
		for(var i=0;i<1;i++){ //data.length
			var x = data[i]%19 //-1;
			var y = 19-Math.floor(data[i]/19) -1
			var me = this.chessBoard[x][y];
			var xs = document.createElement('div');
			if(i===0){
				xs.className = 'aiHelper red'
			}else if(i===1){
				xs.className = 'aiHelper yellow'
			}else if(i===2){
				xs.className = 'aiHelper gray'
			}
			me.appendChild(xs)
		}
	};
	//切换棋盘
	this.switchChess=function(road){
		config = JSON.parse(initConfig) //重置配置信息
		config.road = road; //重置棋盘路数
		this.config = config;//重置config
		this.queue = []; //重置棋子信息
		this.drawChess(); //重新创建棋盘
		this.chessArr()	//重置棋位 数组
		this.init()
	}
	//试下状态中数棋
	this.TryShowLot =function(){
		
		for(var i=0;i<this.queueTraverse.length;i++){
			if(this.queueTraverse[i].B){
				var xy = xyz(this.queueTraverse[i].B);
				if(this.chessBoard[xy.x][xy.y].getElementsByClassName('showLot')[0]){
					this.chessBoard[xy.x][xy.y].getElementsByClassName('showLot')[0].style.display='none'
				}
			}else if(this.queueTraverse[i].W){
				var xy = xyz($this.queueTraverse[i].W);
				if(this.chessBoard[xy.x][xy.y].getElementsByClassName('showLot')[0]){
					this.chessBoard[xy.x][xy.y].getElementsByClassName('showLot')[0].style.display='none'
				}
				
			}
		}
		
	}
	
	//监听是否 启动显示手数
	
	Object.defineProperty(this,'isShowLot',{
		get:function(){
			return config.isShowLot
		},
		set:function(val){
			console.log('监听到 isShowLot 改变，值为：',val)
			config.isShowLot = val;
			var dom = $this.chess.getElementsByClassName('showLot');
			var AC = $this.chess.getElementsByClassName('AC')[0]
			if(val ===1){
				//开启显示手数 自动关闭 显示笑脸
				
				$this.isSmilingFace = 0
				for(var i=0;i<dom.length;i++){
					dom[i].style.display = 'block'
				}
				if(AC){
					AC.style.opacity = 0
				}
			}else{
				for(var i=0;i<dom.length;i++){
					dom[i].style.display = 'none'
				}
				if(AC){
					AC.style.opacity = 1
				}
			}
			
			if(val ===1 && config.isTry){
				$this.TryShowLot()
			}
		},
	})
	//显示笑脸
	Object.defineProperty(this,'isSmilingFace',{
		get:function(){
			return config.isSmilingFace
		},
		set:function(val){
			console.log('监听到 isSmilingFace 改变，值为：',val)
			
			if(val){
				$this.isShowLot = 0
			}
			
			config.isSmilingFace = val;
			val?this.smilingFace():this.init()
			//this.init(config.queueCopy) //重新渲染棋谱
		},
	})
	//路数改变重置棋盘
	Object.defineProperty(this,'road',{
		get:function(){
			return config.road
		},
		set:function(val){
			console.log('监听到 road 改变，值为：',val)
			if(val!==config.road){
				$this.switchChess(val)
			}
			config.road = val;
			
		},
	})
	//显示坐标
	Object.defineProperty(this,'isShowCoord',{
		get:function(){
			return config.isShowCoord
		},
		set:function(val){
			config.isShowCoord = val;
			var coordX = $this.chess.getElementsByClassName('coordX')[0];
			var coordY = $this.chess.getElementsByClassName('coordY')[0];
			if(val){
				coordX.style.display = 'flex';
				coordY.style.display = 'flex';
			}else{
				coordX.style.display = 'none';
				coordY.style.display = 'none';
			}

		},
	});
	
	 //监听是否更新落子数据若有则初始化棋盘并渲染棋子
	Object.defineProperty(this,'svg',{
		get:function(){
			return config.queue
		},
		set:function(val){
			console.log(val)
			
			if(val.indexOf('05[]')<0){
				config.chessManual = 1
			}
			console.log('chessManual',config.chessManual)
			config.queue = val;
			$this.queue = $this.queueFormatting(config.queue);
			config.acStep =  $this.queue.length
			if (config.type!==2 && val.length) {
				if($this.queue[$this.queue.length-1].B || $this.queue[$this.queue.length-1].B ===''){
					config.first = 1
				}else{
					config.first = 0
				}
			}else{
				config.first = 0
			}
			//$this.chessBoard = '' //清空棋位
			$this.init()
		},
	});
	//棋盘加载完成链接webSocket 此时并未初始化棋子数据
	(function(){
		//电子棋盘不需要链接
		//if($this.config.type!==1){
			startWebSocket();
		//}
	})()
	
	
}


/* prev  前进一步

naxt 后退一步

last 最后一步

first 第0步 */
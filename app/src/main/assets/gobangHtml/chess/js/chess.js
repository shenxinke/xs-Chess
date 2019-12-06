function Chess(el,option){
	var initData = {
		road:9,
		lineWidth:2.5,
		lineColor:'#ff4022',
		bgColor:'#ffeb70',
		current:0,
		chessData:'',//围棋棋谱 B[AB]格式那种
		rightAnswers:[],
		Tracks: []
	}
	
	var config = Object.assign(initData,option)
	console.log(config)
	
	this.road= config.road;//几路
	this.lineWidth= config.lineWidth,//线宽
	this.lineColor= config.lineColor; //线颜色
	this.current= config.current;    //(默认黑先)当前要下的子，0表示黑子，1表示白子，交替。
	
	//默认显示的棋子
	this.Tracks=config.Tracks;
	this.chessData=config.chessData;//围棋棋谱 B[AB]格式那种
	this.rightAnswers = config.rightAnswers; //正确答案
	
	this.isPlay=1;
	
	this.life=[];//临时存储当前块的气元素
	this.lump=[]; //临时纪录当前块的每个元素
	this.createroad= function(x, y){ //棋位类的构造函数
		var chessroad = this.chess.getElementsByClassName('chessroad')[0];
		var chessline = this.chess.getElementsByClassName('chessline')[0];
	
		var me = document.createElement("div"); //建一个div对象，将其扩展并封装成棋位。
		var line = document.createElement("div"); //线
		me.className = 'td';
		line.className = 'td';
		me.x = x;   //记录棋位的X坐标
		me.y = y;   //记录棋位的Y坐标
		me.dot = -1; //棋位状态 -1空 0黑  1白
		me.onclick = this.play; //绑定落子处理事件
		me.$this = this;
		//使其坐标从左下角开始 0,0
		var meW = chessroad.offsetWidth/this.road;
		me.style.position= 'absolute';
		me.style.width = meW+'px';
		me.style.height = meW+'px';
		me.style.left= x*meW+'px';
		me.style.bottom = y*meW+'px';
		line.style.boxSizing = 'border-box';//不需考虑线宽问题了
		chessroad.appendChild(me);
		
		//填充线;
		if(x < this.road-1 && y < this.road-1){
			
			line.style.width = 'calc(100% / '+(this.road-1)+')';
			line.style.height = 'calc(100% / '+(this.road-1)+')';
			line.style.float='left';
			line.style.borderRight = this.lineWidth+'px '+'solid '+this.lineColor;
			line.style.borderBottom = this.lineWidth+'px '+'solid '+this.lineColor;
			if(!x){
				line.style.borderTop=this.lineWidth+'px '+'solid '+this.lineColor;
			}
			if(!y){
				line.style.borderLeft=this.lineWidth+'px '+'solid '+this.lineColor;
			}
			chessline.appendChild(line)
		}
		return me;              //返回棋位对象，其实是一个封装了的div对象。
	};
	this.play=function(){
		//console.log(this.$this)
		var site = this.$this
		
		if(site.isPlay===0){
			console.log('已结束')
			return false
		}
		
		// 落子判断当前棋位是否有子
		if(this.dot<0){
			var dot = site.current;
			var x = this.x;
			var y = this.y;
			var qw = dot+','+x+','+y;
			
			if(site.rightAnswers.length){ //如果存在正确答案
				var rightData = [];
				for(var i=0;i<site.rightAnswers.length;i++){
					var data = site.rightAnswers[i].split(',');
					var rxy = data[1]+data[2];
					rightData.push(rxy);
				}

				var xy = x.toString()+ y.toString()
				if(rightData.indexOf(xy)>=0){ //做对
					site.onRight();
					site.isPlay = 0; //禁止在落子
				}else{ //做错
					site.onError()
					var e = document.createElement('div')
					e.className = 'playError animated shake'
					site.chessBoard[x][y].appendChild(e)
					setTimeout(function(){
						site.chessBoard[x][y].removeChild(e)
					},1500)
					return false;
				}
			}
			
			
			
			//判断是否可以提子
			if(site.removeBlock(x,y,site.current).length){
				site.del(site.removeBlock(x,y,site.current))
				site.Tracks.push(qw); //纪录当前下棋位置
				site.fill(); //填充
				site.current ^= 1; //反转颜色 白下完该黑下黑下完该白下
			}else{ //如果不能提子则判断是否气紧,落子必须有气，否则属于自杀不允许
				site.lump = []; //清空块
				site.life =[]; //清空气
				if(site.countLiberty(x,y,site.current)>0){
					site.Tracks.push(qw); //纪录当前下棋位置
					site.fill(); //填充
					site.current ^= 1; //反转颜色 白下完该黑下，黑下完该白下
				}else{
					alert('禁入点！')
				}
				
			}
		}
	};
	this.fill=function(){ //填充棋子
		for (var i =0;i<this.Tracks.length;i++) {
			var dot = this.Tracks[i].split(',')[0]*1;
			var x = this.Tracks[i].split(',')[1]*1;
			var y = this.Tracks[i].split(',')[2]*1;
			
			this.chessBoard[x][y].dot = dot;
			if(dot){
				this.chessBoard[x][y].className = 'white'
				
			}else{
				this.chessBoard[x][y].className = 'black'
			}
			console.log("当前落子坐标：",x,y)
		}
	};
	this.GetSite=function(x, y){ //如果越界 避免报错
		if (x>=0 && x<this.road && y>=0 && y<this.road)
			return this.chessBoard[x][y]
	};
	//数气
	this.countLiberty=function(x,y,color){
		var q = 0;
		//var color = chessBoard[x][y].dot; //当前棋位颜色
		this.lump.push(this.chessBoard[x][y])
		for(var dx=-1;dx<=1;dx++){
			for(var dy=-1;dy<=1;dy++){
				if(!dx^!dy) //异或,有且只有一个为真,可对应到相邻子.
				{
					console.log(this.GetSite(x+dx,y+dy))
				// console.log(x+dx,y+dy) //当前子的上下左右四个棋位 
				 if(this.GetSite(x+dx,y+dy)&& this.GetSite(x+dx,y+dy).dot === -1 && this.life.indexOf(this.chessBoard[x+dx][y+dy])<0) {
					 q++;
					 this.life.push(this.GetSite(x+dx,y+dy))
				 }
				 if(this.GetSite(x+dx,y+dy) && this.GetSite(x+dx,y+dy).dot === color && this.lump.indexOf(this.chessBoard[x+dx][y+dy])<0) //同颜色
					//console.log(x+dx,y+dy)
					q+= this.countLiberty(x+dx,y+dy,color)
				}
				if(this.GetSite(x+dx,y+dy) && this.GetSite(x+dx,y+dy).dot !== -1  && this.GetSite(x+dx,y+dy).dot !== color){
				//	console.log(this.GetSite(x+dx,y+dy))
				}
				
				
			}
		} 
		
		return q;
	};
	//计算可以提子的块
	this.removeBlock=function(x,y,color){
		//var color = chessBoard[x][y].dot; //当前棋位颜色
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
			
			console.log(55,this.countLiberty(unlike[i].x,unlike[i].y,unlike[i].dot))
			console.log('块',this.lump)
			this.lump = []; //清空块
			this.life =[]; //清空气
			 if(this.countLiberty(unlike[i].x,unlike[i].y,unlike[i].dot)==1){
				dels = dels.concat(this.lump)
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
	
	//提子操作
	this.del=function(arr){
		for (var i=0;i<arr.length;i++) {
			var qw = arr[i].dot+','+arr[i].x+','+arr[i].y;
			this.chessBoard[arr[i].x][arr[i].y].dot=-1;//设置无字状态
			this.chessBoard[arr[i].x][arr[i].y].className = '';
			var index = this.Tracks.indexOf(qw);
			this.Tracks.splice(index,1);
		}
	};
	
	this.format=function($this){
		
		function format(data){
			var msg = data.split('');
			var dot = msg[0]=='B'?0:1;
			var x = msg[2].charCodeAt() - 97;
			var y = msg[3].charCodeAt() - 97;
			var qw = dot+','+x+','+y;
			return qw;
		}
		
		if($this.chessData){ //如果传了棋谱 自动转格式
			var chessData = JSON.parse($this.chessData);
			console.log(chessData)
			var Tracks = [];
			for (var i = 0;i<chessData.listAction.length-1;i++) {
				var qw  = format(chessData.listAction[i])
				Tracks.push(qw)
			};
			console.log(Tracks)
			$this.Tracks = Tracks;
			
			//计算正确答案
			var rightAnswers=[];
			for (var i=0;i<chessData.correctResultsList.length;i++) {
				var r = chessData.correctResultsList[i]
				rightAnswers.push(format(r[r.length-1]))
			};
			$this.rightAnswers = rightAnswers;
			console.log('正确答案',rightAnswers)
		}
	}(this);
	//显示正确答案
	this.showRight = function(){
		if(!this.rightAnswers.length){ //若没有 不做处理
			return false;
		}
		var msg = this.rightAnswers[0].split(',');
		var x = msg[1];
		var y = msg[2];
		var f = `<div class='fingerBox'>
				<div class='ripple'></div><div class='ripple2'></div><div class='ripple3'></div>
				<img class='finger' src ='img/chess/finger.png'>
			</div>`
		var c = this.chessBoard[x][y];//当前棋位元素
		c.innerHTML += f;
		c.getElementsByClassName('fingerBox')[0].onclick=function(){
			c.removeChild(this)
		}
	};
	
	
	this.onRight=function(){
		console.log('回答正确！')
	};
	this.onError = function(){
		console.log('回答错误！')
	};
	
	
	//画棋盘
	this.chess = function($this){
		var chess = document.getElementById(el);
		
		//初始化元素防止重复
		var num = chess.childNodes.length
		for (var i=0;i<num;i++) {
			chess.removeChild(chess.childNodes[0])
		}
		
		chess.className +=' chess';
		chess.style.backgroundColor = config.bgColor;
		chess.style.position = 'relative';
		
		
		
		var chessline = document.createElement("div");
		var chessroad = document.createElement("div");
		var chessOutline = document.createElement("div");
		
		
		chessroad.className = 'chessroad'
		chessroad.style.width = '100%';
		chessroad.style.height = '100%';
		chessroad.style.position = 'relative';
		chessroad.style.zIndex =2;
		chess.appendChild(chessroad)
		
		chessline.className ='chessline';
		chessline.style.margin='auto';
		chessline.style.position = 'absolute';
		chessline.style.top='0px';
		chessline.style.left='0px';
		chessline.style.bottom='0px';
		chessline.style.right='0px';
		chessline.style.width = chessroad.offsetWidth - chessroad.offsetWidth / $this.road +'px' ;
		chessline.style.height = chessroad.offsetHeight - chessroad.offsetHeight / $this.road +'px' ;
		chess.appendChild(chessline)

		return chess
	}(this);
	this.chessBoard = function($this){ //对应棋盘二维数组
		console.log($this)
		var chessBoard = new Array($this.road);
		for (var x=0;x<chessBoard.length;x++) {
			chessBoard[x] = new Array($this.road);
			for (var y=0;y<chessBoard[x].length;y++) {
				chessBoard[x][y] = $this.createroad(x,y)
			}
		}
		return chessBoard
	}(this);
	
}
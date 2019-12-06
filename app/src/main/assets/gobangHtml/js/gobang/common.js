// 工具方法
const Tool = {
	isEmpty : function(obj) {
		return (obj.length === 0 || !obj.trim());
	},
	queryString : function(val) {
		var uri = window.location.search;
		var re = new RegExp("" + val + "=([^&?]*)", "ig");
		return uri.match(re) ? uri.match(re)[0].substr(val.length + 1) : null;
	},
	isMobile : function(val) {
		var reg = /^[1][3,4,5,7,8][0-9]{9}$/;
		return reg.test(val);
	},
	isEmail : function(val) {
		var reg = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
		return reg.test(val);
	},
	isArray: function(obj) {
		return typeof obj === 'object' && !isNaN(obj.length);
	},
    readAloudSrc:function(text){
           var tok = Tool.queryString('tok') || '24.f4c39d160ba001437497d7e4409c0f84.2592000.1576205041.282335-17755101' ;
           var t = text.replace( /\+/g , '加' );
           return 'http://tsn.baidu.com/text2audio?lan=zh&ctp=1&cuid=abcdxxx&tok='+tok+'&tex='+t+'&vol=9&per=0&spd=5&pit=5&aue=3'
   }
}

//公共配置信息

let http = 'https://android.xswq361.cn';
let uid = Tool.queryString("uid"); //uid
let token =  Tool.queryString("token");//token
let levelAI = Tool.queryString("levelAI");
let socketUri = 'wss://android.xswq361.cn/WebSocket?uid='+uid+'&token='+token; // socket.js 通知 webSocket地址
let GsocketUri = 'wss://android.xswq361.cn:4447/'; // Gsocket.js 棋盘 webSocket地址

//let http = 'http://192.168.1.212';//接口地址
//let uid = Tool.queryString("uid"); //uid
//let token =  Tool.queryString("token");//token
//let levelAI = Tool.queryString("levelAI");
//let socketUri = 'ws://192.168.1.212/WebSocket?uid='+uid+'&token='+token; // socket.js 通知 webSocket地址
//let GsocketUri = 'ws://192.168.1.212:4447/'; // Gsocket.js 棋盘 webSocket地址
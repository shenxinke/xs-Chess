document.documentElement.style.fontSize=document.documentElement.offsetWidth/7.5+"px";
/*window.onresize=function(){
	document.documentElement.style.fontSize=document.documentElement.offsetWidth/7.5+"px";
}*/

var html = document.getElementsByTagName('html')[0];
	var settedFs = parseInt(html.style.fontSize); 
	var realFs = parseInt(window.getComputedStyle(html).fontSize);
	var whileCount = 0;
	
	
	while(true) {
		var realFs = parseInt(window.getComputedStyle(html).fontSize);
		var delta = realFs - settedFs;
		console.log(settedFs,realFs)
		if (Math.abs(delta) != 0) //不相等
		{
			html.setAttribute('style', 'font-size:'+settedFs *(settedFs/realFs) + 'px!important');
		} else 
			break;
			
		if (whileCount++ > 100)
			break
}

//写cookies 
function setCookie(name, value) {
  localStorage.setItem(name,value);
}

//读取cookies 
function readCookie(name) {
 return localStorage.getItem(name)
}

//删除cookies 
function delCookie(name) {
  localStorage.removeItem(name)
}
/* 格式化日期 */
Date.prototype.Format = function (fmt) {
    var o = {
        "M+": this.getMonth() + 1,  // 月份
        "d+": this.getDate(),		// 日
        "h+": this.getHours(),		// 小时
        "m+": this.getMinutes(),	// 分
        "s+": this.getSeconds(),	// 秒
        "q+": Math.floor((this.getMonth() + 3) / 3), // 季度
        "S": this.getMilliseconds() // 毫秒
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}

/* 获取url参数部分转换为json */
function jsonlink(link){
	var url = link || window.location.search;
  	var search = window.location.search;
	var ksbz = url.indexOf("?");
 	var hrefStr = url.substr(ksbz+1);  
 	var splitStr = hrefStr.split("&");  // 返回结果：["userName=cyq", "age=24", "sex=f"]
 	var urlObj = {}  // 等价于 var urlObj = new Object()
 	for(var i = 0; i < splitStr.length; i++){
 	  urlObj[splitStr[i].split("=")[0]] = splitStr[i].split("=")[1];
 	}
 	return urlObj
}


/*如果该数组里存在该值则删除
 *返回最新数组
 * 用法 arr.toggleArr(val)
 * */
Array.prototype.toggleArr=function(val){
	var arr = this;
	var ind = arr.indexOf(val);
	if(ind>=0){
		arr.splice(ind,1)
		return arr
	}
}


layer.config({
  extend: 'confirm/style.css', //加载您的扩展样式
});

//答题对错 skin传入 msg-yes  对    msg-no 错 cancel关闭后回调

function msgYN(skin,cancel){
	layer.msg('', {
	  time: 1500, //2秒关闭（如果不配置，默认是3秒）
	  skin:skin||'msg-yes',
	  shade: [0.8,'#000'],//0代表加载的风格，支持0-2
	}, function(){
		cancel?cancel():''
	});
}
/*
 *layer弹窗自定义皮肤
 *layerTC('提示内容',确定回调,取消回调)
 * */
function layerTC(content,callbackYes,callbackNo,cancel){
	//content  可以是字符串  也可以是  json
	var option={
		content:'',
		skin:'layerTC',
		shade:[0.8,'#000'],
		closeBtn: 1,
		btn:['下一题','返回'], //按钮
	};
	//如果传过来字符串 则直接等于内容  如果是对象 咋等于配置
	if(typeof content == 'string'){
		option.content=content
	}else{
		$.extend(option,content)
	}

	
	var index = layer.confirm(option.content, {
		skin: option.skin,
		title: false, //不显示标题
		area:'6.07rem 4.07rem',
	  	btn: option.btn, //按钮
	  	shade: option.shade,
	  	closeBtn: option.closeBtn,
	  	cancel: function(){
	  		cancel?cancel():''
	  	 }
	}, function(){
		callbackYes?callbackYes():''
		layer.close(index)
	}, function(){
		callbackNo?callbackNo():''
	});
}
/*
 //layer自定义模态框
 //启动模态框
 var open1 = layerOpen({
 	content:'#node',//节点
 	area:'width height'//可选默认 2rem 2rem  宽度 高度(带单位)
 	cancel:function(){} //可选  右上角关闭回调
 })
 //手动关闭模态框
 layer.close(open1)
 
*/

function layerOpen(option){
	var opt={
		area:['2rem','2rem'],
		content:'#node',
		shadeClose:false,
		id:'',
		
		closeBtn:1,
		skin:'layerOpen',
		shade: [0.3, '#000'],
		offset: 'auto',
		cancel: function(index, layero){},
		success: function(layero, index){}
	};
	$.extend(opt,option)
    var index = layer.open({
    	  type: 1,
    	  title: false,
				closeBtn: opt.closeBtn,
				id:opt.id,
    	  area:opt.area,
		
    	  skin: opt.skin, //没有背景色
				shade: opt.shade,
    	  shadeClose: opt.shadeClose,
    	  offset:opt.offset,
    	  content: $(opt.content),
    	  cancel: opt.cancel,
    	  success:opt.success
    });
    return index
}
//loding 加载中
function layerLoad(option){
	var index = layer.load(0, {
		shade: [0.8,'#000'],//0代表加载的风格，支持0-2
		skin: 'layerLoad'
	}); 
	return index
}
//表单序列化，转 json
function formSerialize(dom){
	var data = $(dom).serializeArray();
	var obj = {}
	$.each(data, function(index,itm) {
		if(itm.name=='area2'){
			obj['area']+=('-'+itm.value);
		}else{
			obj[itm.name]=itm.value;
		}
	});
	return obj
};


$.fn.extend({
	/*
	 * 棋盘提示
	 * $('#canvasBox').canvasAlert({content:'标题',ms:2000})
	 * */
	canvasAlert: function(opt) {
    var option = {
    	content:'标题',
    	ms : 1000,
    }
    $.extend(option,opt);
    console.log(option)
    //debugger
    var id = 'timleMsg'+$('.timleMsg').length
     var dom = '\
       <div id='+id+' class="timleMsg">\
			<span>\
				'+option.content+'\
			</span>\
		</div>'; 
     var style = $('<style type="text/css"></style>')
       style.text('.timleMsg{width:100%;height:100%;margin:auto;position:absolute;top:0;left:0;right:0;bottom:0;background:url(img/questionStore/titleMsg.png)no-repeat center/7.75rem 3.7rem;z-index:10;display:-webkit-box;display:-webkit-flex;display:-ms-flexbox;display:flex;-webkit-box-align:center;-webkit-align-items:center;-ms-flex-align:center;align-items:center;-webkit-box-pack:center;-webkit-justify-content:center;-ms-flex-pack:center;justify-content:center;-webkit-animation:fangda 0.6s 1;animation:fangda 0.6s 1;}.timleMsg span{font-size:0.5rem;font-weight:bold;color:yellow;}@-webkit-keyframes fangda{from{-webkit-transform:scale(0.2);transform:scale(0.2)}to{-webkit-transform:scale(1);transform:scale(1)}}@keyframes fangda{from{-webkit-transform:scale(0.2);transform:scale(0.2)}to{-webkit-transform:scale(1);transform:scale(1)}}')
       $('head').append(style);
       $(this).append(dom);
       setTimeout(function() {
       		$("#"+id+"").remove()
       }, option.ms)
    },
    
    /**
     * [时间倒计时]
     * time 定义时长，默认5分钟
     * timeText 定义时间格式
     * callback 倒计时结束回调
     */
    timeDown:function(opt){
        var dom = this;
        var option={
            time:5,//倒计时5分钟
            timeText:function(date){//定义格式 date包含 天 时 分 秒
                return '倒计时:'+ date.minute+'分'+ date.seconds+'秒' 
            },
            callback:function(){
                console.log('倒计时结束')
            }
        };
        $.extend(option,opt);
        var nowtime = new Date();
        var starttime = nowtime*1+option.time*1000*60;
        var time = starttime - nowtime;
        fnTime();
        function fnTime(){
            var dateObj = {}
            dateObj.day = parseInt(time / 1000 / 60 / 60 / 24);
            dateObj.hour = parseInt(time / 1000 / 60 / 60 % 24);
            dateObj.minute = parseInt(time / 1000 / 60 % 60);
            dateObj.seconds = parseInt(time / 1000 % 60);
            $(dom).html(option.timeText(dateObj))
            if(time<=0){
                clearInterval(date)
                option.callback()
            }
            time-=1000
        };
        var date = setInterval(function() {
            fnTime(); 
        }, 1000);
        
        return date
    },

	/*
	 小天出场动画
	 
	 */
	comeOn:function(option){
		var $this = $(this)
		var dOption={
			start:1,//初始起始帧
			end:107,//播放结束帧
			//播放完出场 会一直播放嘴型的动作
			loopStart:94, //开始循环帧  
			loopEnd:107, //循环结束帧
			anTime:6000, //动画执行时长
			titles:2500,//延长时间，一般音频从嘴型开始动播放，要加上嘴唇动之前的动画
			src:'img/guide/student/xiaotianR/xiaotian',
			collback:function(){},
			destroy:function(){ //销毁，结束运行
				clearInterval(time);
				clearTimeout(time2)
			}
		}
		$.extend(dOption,option);
		
		fn(dOption.start,dOption.end)
		var time,time2
		function fn(min,max){
			var i=min
			time = setInterval(function(){
				i++
				if(i===max){
					clearInterval(time);
					fn(dOption.loopStart,dOption.loopEnd)
					
				}
				
				$this.attr('src',dOption.src+i+'.png')
				
			},55)
		};
		time2=setTimeout(function(){
			clearInterval(time);
			console.log(5555666)
			$this.attr('src',dOption.src+107+'.png')
		},dOption.anTime+2500)
		return dOption.destroy;	
	}


});

/*$(function(){
	//resize()
	$(window).resize(function(){
		//resize()
	});
})
function resize(){
	if(document.documentElement.offsetWidth<=1300){
		$('#gameCanvas').attr('width',400).attr('height',400);//.width('480').height('480')
		$('body').css('overflow-x','hidden');
	}else if(document.documentElement.offsetWidth<=1556){
		$('#gameCanvas').attr('width',480).attr('height',480);//
	}else{
		$('#gameCanvas').attr('width',600).attr('height',600);//
	}
}
*/

////用户是否过期验证
$.ajax({
    url:http+ "/gobangteach/UserBaseController/getUserFullInfoById",
    data: {
        userid: uid,
        token:token,
            uid:uid
    },
    success: function(data) {
        if(data.error.returnCode==='10048' && !$('.login')[0] && !$('.loginContent')[0]){
			document.location.href = "myschema://go?a=14"
            layerTC('用户登入过期请重新登入', function() {
                document.location.href = "myschema://go?a=2"
            }, function() {
                document.location.href = "myschema://go?a=2"
            })
        }
    }
});



var playAudio=function(val){
	/* let oAudio = document.getElementsByTagName('audio');
	for (let i=0;i<oAudio.length;i++) {
		oAudio[i].pause();
		oAudio[i].currentTime = 0;
	} */
	switch (val){
		case 1:
			$("#winmusic")[0].play()	//恭喜您取得胜利
			break;
		case 2:
			$("#losemusic")[0].play() //您辛苦了感谢您的精彩对弈
			break;
		case 3:
			$("#losemusic")[0].play()
			break;
		case 4:
			$("#losemusic")[0].play()
			break;
		default:
			$('#'+val)[0].play()
			break;
	}
}

/* function closeLoding(time){
	setTimeout(function(){
		document.location.href = "myschema://go?a=14"
	},time || 1000)
} */
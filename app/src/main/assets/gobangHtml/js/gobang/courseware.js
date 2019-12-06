document.documentElement.style.fontSize=document.documentElement.offsetWidth/20.3+"px";
window.onresize=function(){
	document.documentElement.style.fontSize=document.documentElement.offsetWidth/20.3+"px";
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

/* 获取cookie */
function readCookie(cname)
{
  var name = cname + "=";
  var ca = document.cookie.split(';');
  for(var i=0; i<ca.length; i++) 
  {
    var c = ca[i].trim();
    if (c.indexOf(name)==0) return c.substring(name.length,c.length);
  }
  return "";
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



function nextPage($this){
	if($this.attr('class')=='next'){
		$this.attr('class','prev');
		$(".conten .info").eq(1).show().siblings('.info').hide();
	}else{
		$this.attr('class','next');
		$(".conten .info").eq(0).show().siblings('.info').hide();
	}
	//$(".conten .info").eq(1).show().siblings('.info').hide();
}

//权限





$(function(){
	
	
	
	
	var userid = uid;
	if(!userid){
		window.location.href = '/gobangteach/index.jsp';
	 }else{
		$('body').show() 
	 }
	
	
	$('.list .lt a').hover(
		function(){
			$(this).parents(".lt").addClass('lthove')
		},
		function(){
			$(this).parents(".lt").removeClass('lthove')
		}
	)
	$('.list .rt a').hover(
		function(){
			$(this).parents(".rt").addClass('rthove')
		},
		function(){
			$(this).parents(".rt").removeClass('rthove')
		}
	)
	$('.list .rtt a').hover(
		function(){
			$(this).parents(".rtt").addClass('rtthove')
		},
		function(){
			$(this).parents(".rtt").removeClass('rtthove')
		}
	)
	$('.list .rtb a').hover(
		function(){
			$(this).parents(".rtb").addClass('rtbhove')
		},
		function(){
			$(this).parents(".rtb").removeClass('rtbhove')
		}
	)
	
	
	
	resize()
	$(window).resize(function(){
		resize()
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

function coursewareMp4(){
	document.oncontextmenu = function () { return false; };
	document.onkeydown = function () {
        if (window.event && window.event.keyCode == 123||window.event.altKey||window.event.ctrlKey) {
            event.keyCode = 0;
            event.returnValue = false;
            return false;
        }
    };
	var cardType = readCookie('cardType') ;
	var videoUrl = 'http://vodyq6aqp0d.vod.126.net/vodyq6aqp0d/';
	if(jsonlink().videoUrl){
		$('#example_video_1').attr('src',videoUrl+jsonlink().videoUrl);
		
		$('#example_video_1')[0].play()
	}else{
		power(cardType,jsonlink().videoName)
	}
	$('h3').text(decodeURI(jsonlink().name) )
	function power(val,index){
    	if(val=='0' && index*1>4){
			layerTC('该体验卡没有此视频播放权限！')
		}else{
	       	$.ajax({
	            url:'/gobangteach/videos/getVideoByName',
	            type: "GET",
	            dataType: "json",
	            data:{
	            	videoName:'courseware_'+index
	            },
	            success: function (data) {
	            console.log('66',data.data.VideoUrl)
	                $('#example_video_1').attr('src',videoUrl+data.data.VideoUrl);
					$('#example_video_1')[0].load();
					/* 
						谷歌浏览器禁止音频自动播放，所以首次加载视频时无法自动播放
					 */
	            }
	        });
		}
	}
}

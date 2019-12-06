require.config({
	paths : {
    	'jquery' : 'libs/jquery/jquery',
    	'rem':'js/lib/rem',
    	'config':'js/lib/config',
    	//video 兼容类库
    	'video':'//api.html5media.info/1.2.2/html5media.min',
    	'layer':'libs/layer/layer-v3.1.1/layer/layer',
    	'laydate':'libs/laydate/laydate',
    	'swiper':'libs/swiper/js/idangerous.swiper.min',
    	//swf播放插件
    	'swfobject':'libs/swf/swfobject',
    	'Vue':'libs/vuejs/vue',
    	'VueRouter':'libs/vuejs/vue-router.min',
    	'mint':'libs/mint/index',
    	'courseware' : 'js/custom/courseware',
    	'coursewareMp4' : 'js/custom/coursewareMp4',
    	'coursewareSwf' : 'js/custom/coursewareSwf',
    	'coursewareLookBack' : 'js/custom/coursewareLookBack',
    	'coursewareDetail' : 'js/custom/coursewareDetail',
    	//cocos2d
    	'eve':'cocos2d/src/eve',
    	'game':'game.min',
    	'city':'libs/city/linkageProvinceCity',
    	//表单验证
    	'Validform':'js/lib/Validform_v5.3.2',
    	//滚动条美化
    	'mCustomScrollbar':'libs/jquery.mCustomScrollbar/jquery.mCustomScrollbar.concat.min',
    	'common':'js/gobang/common'

    },
   	map :{
	   	'*': {
	    	'CSS' : 'libs/require/plugins/css.min'
	  	}
	},
	shim:{
		config:{
			deps: ['jquery','common']
		},
		VueRouter:{
   			deps: ['Vue']
   		},
   		mint:{
   			deps: ['Vue']
   		},
   		//layer 弹窗插件
   		layer:{
   			deps: ['jquery','CSS!libs/layer/layer-v3.1.1/layer/theme/default/layer.css']
   		},
   		//日期插件
   		laydate:{
   			deps: ['jquery','CSS!libs/laydate/theme/default/laydate.css']
   		},
   		//swiper 轮播插件
   		swiper:{
   			deps: ['jquery', 'CSS!libs/swiper/css/idangerous.swiper.css']
   		},
   		//rem.js 公用方法
   		rem:{
   			deps: ['jquery','config','layer','CSS!css/lib/public.css','CSS!css/custom/webStyle.css']
   		},
   		game:{
   			deps:['eve','rem']
   		},
   		city:{
   			deps:['jquery']
   		},
   		Validform:{
   			deps:['jquery']
   		},
   		mCustomScrollbar:{
   			deps:['jquery','CSS!libs/jquery.mCustomScrollbar/jquery.mCustomScrollbar.css']
   		},
   		//视频课件列表页
   		courseware:{
   			deps:['rem','swiper']
   		},
   		//视频课件-播放页
   		coursewareMp4:{
   			deps:['rem','video']
   		},
   		//视频课件-swf播放页
   		coursewareSwf:{
   			deps:['rem','swfobject']
   		},
   		//视频课件-习题
   		/*coursewareDetail:{
   			deps:['rem']
   		},*/
   		//视频课件-知识点回顾
   		coursewareLookBack:{
   			deps:['rem']
   		}
   	}
})
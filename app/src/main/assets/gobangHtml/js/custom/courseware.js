function courseware(Vue) {

    var list = [
        [{
                name: '01 围棋礼仪',
                imgL: '',
            },
            {
                name: '02 棋盘棋子',
                imgL: '',
            },
            {
                name: '03 棋子的气',
                imgL: '',
            },
            {
                name: '04 紧气提子',
                imgL: '',
            },
            {
                name: '05 打吃,逃子',
                imgL: '',
            },
            {
                name: '06 双方互相打吃',
                imgL: '',
            },
            {
                name: '07 虎口',
                imgL: '',
            },
            {
                name: '08 禁入点（禁着点）！',
                imgL: '',
            }

        ],
        [{
                name: '09 打劫',
                imgL: '',
            },
            {
                name: '10 打二还一和打多还一',
                imgL: '',
            },
            {
                name: '11 连接和切断',
                imgL: '',
            },
            {
                name: '12 一、二线吃子的秘密！',
                imgL: '',
            },
            {
                name: '13 双打吃',
                imgL: '',
            },
            {
                name: '14 征吃',
                imgL: '',
            },
            {
                name: '15 门吃',
                imgL: '',
            },
            {
                name: '16 抱吃',
                imgL: '',
            }

        ],
        [{
                name: '17 枷吃',
                imgL: '',
            },
            {
                name: '18 扑与倒扑',
                imgL: '',
            },
            {
                name: '19 接不归',
                imgL: '',
            },
            {
                name: '20 利用对方缺陷逃子',
                imgL: '',
            },
            {
                name: '21 可逃之子与应弃之子',
                imgL: '',
            },
            {
                name: '22 两眼活棋',
                imgL: '',
            },
            {
                name: '23 基础死活常型(一)',
                imgL: '',
            },
            {
                name: '24 基础死活常型(二)',
                imgL: '',
            },
         {
         name: '25 杀棋基础',
         imgL: '',
         },
         {
         name: '26 获取基础',
         imgL: '',
         },
         {
         name: '27 布局基础(一)',
         imgL: '',
         },
         {
         name: '28 布局基础(二)',
         imgL: '',
         },
         {
         name: '29 对杀基础(一)',
         imgL: '',
         },
         {
         name: '30 对杀基础(二)',
         imgL: '',
         },
         {
         name: '31 综合吃子(一)',
         imgL: '',
         },
         {
         name: '32 综合吃子(二)',
         imgL: '',
         },
         {
         name: '33 好形和坏形',
         imgL: '',
         },
         {
         name: '34 官子入门',
         imgL: '',
         },
         {
         name: '35 胜负计算',
         imgL: '',
         }
        ]
    ]
    

    new Vue({
        el: '#courseware',
        data: {
            msg: list,
            aa:'sssssss0'
        },
		//数据渲染完成执行
        mounted: function() {
            /*课件轮播*/
           		//swiper初始化
            var coursewareSwiper = new Swiper('#courseware .swiper-container', {
                loop: true,
                keyboardControl: true,
            });
            	//swiper 向左切换
            $("#courseware").on("click", ".bg_cw_btnL", function() {
                coursewareSwiper.swipePrev();
            });
            	//swiper 向右切换
            $("#courseware").on("click", ".bg_cw_btnR", function() {
                coursewareSwiper.swipeNext();
            });
            /*end 课件轮播*/
            
          //添加视频连接
           /* $('.cwLST').each(function(index, itm) {
                index++
                $(itm).attr('href', '/gobangteach/gobangPc/coursewareMp4.html?videoName=' + index+'&name='+$(itm).attr('title')+'')
            })*/

            /*//添加swf连接
            $('.cwRST').each(function(index, itm) {
                index++
                $(itm).attr('href', '/gobangteach/gobangPc/coursewareSwf.html?swfId=' + index)
            })*/
            
        	//添加习题连接
           /* $('.cwRSB').each(function(index, itm) {
                index++
                $(itm).attr('href', '/gobangteach/gobangPc/coursewareDetail.html?classId=' + index)
            })*/
            
            
        }
    })
   
}
function dialog($this){
	var classId = $this.attr('data-text');
	$("#dialog img").attr('data-classId',classId);
	layerOpen({area: ['8.36rem', '5.26rem'],content: $("#dialog")});
}
function skip($this){
	var _index = $this.index();
	var classId = $this.attr('data-classId');
	_index==0&&classId!=7&&classId!=8&&classId!=10?window.location.href="/gobangteach/gobangPc/coursewareTeachList.html?classId="+classId:window.location.href="/gobangteach/gobangPc/coursewareDetail.html?classId="+classId
}

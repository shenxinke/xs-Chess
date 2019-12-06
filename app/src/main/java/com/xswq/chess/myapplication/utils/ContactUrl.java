package com.xswq.chess.myapplication.utils;

//接口地址
public class ContactUrl {
    public static String APP_NAME = "xsqx.apk";
    public final static String BASE_PATH = "https://android.xswq361.cn";//公共网址链接
    public final static String BASE_PATH3 = "https://android.xswq361.cn";//正式地址
    public final static String BASE_WEB_SCOKET_PATH3 = "wss://android.xswq361.cn";//正式SCOKET
//    public final static String BASE_PATH3 = "http://192.168.1.212";
//    public final static String BASE_WEB_SCOKET_PATH3 = "ws://192.168.1.212";
//    public final static String BASE_PATH3 = "http://192.168.1.43";//本地地址
//    public final static String BASE_WEB_SCOKET_PATH3 = "ws://192.168.1.43";//本地SCOKET

    /**
     * apk路径
     */
    //正式包下载地址
    public static String APK_PATH = "https://www.xswq361.cn/files/download/xsqx/xsqx.apk";
    public static String APK_INCREMENT_PATH = "https://www.xswq361.cn/files/download/xsqx/";
    //本地包下载地址
//    public static String APK_PATH = "http://192.168.1.212/files/download/xsqx/xsqx.apk";
//    public static String APK_INCREMENT_PATH = "http://192.168.1.212/files/download/xsqx/";

    //webview本地加载地址
    public final static String WEBVIEW_PATH = "file:///android_asset/gobangHtml/";

    //游戏
    public final static String GAME_PATH = "/gobangteach/web-mobile/index.html?";
    public final static String GAME = "game";

    //专项训练
    public final static String SPECIALIZED_TRAINING_PATH = "/gobangteach/edugame/index.html?";
    public final static String SPECIALIZED_TRAINING = "specializedTraining";

    //题库
    public final static String QUESTIONSTORE_PATH = "questionStore.html?";
    public final static String QUESTIONSTORE = "questionStore";

    //错题本
    public final static String WRONGQUESTION_PATH = "wrongQuestion.html?";
    public final static String WRONGQUESTION = "wrongQuestion";

    //测评
    public final static String TESTTITLE_PATH = "testTitle.html?";
    public final static String TESTTITLE = "testTitle";

    //复盘
    public final static String ANALYSE_PATH = "qx_fp.html?";
    public final static String ANALYSE = "analyse";

    //直播课堂
    public final static String LIVE_PATH = "live.html?";
    public final static String LIVE = "live";

    //电子棋盘与历史对局
    public final static String ELECBOARD_PATH = "ElecBoard.html?";
    public final static String ELECBOARD = "ElecBoard";

    //学生作业
    public final static String MYWORD_PATH = "myWord.html?";
    public final static String MYWORD = "myWord";

    //历史作业
    public final static String MYWORDHISTORY_PATH = "myWordHistory.html?";
    public final static String MYWORDHISTORY = "myWordHistory";

    //检查作业学生
    public final static String WRONGSTUDENT_PATH = "wrongStudent.html?";

    //检查作业老师
    public final static String WRONGGATHER_PATH = "wrongGather.html?";

    //检查作业列表
    public final static String TESTINGWORK_PATH = "testingWork.html?";
    public final static String TESTINGWORK = "testingWork";

    //课件习题
    public final static String COURSEWAREDETAIL_PATH = "coursewareDetail.html?";
    public final static String COURSEWAREDETAIL = "coursewareDetail";

    //课件讲解
    public final static String COURSEWARETEACH_PATH = "coursewareTeach.html?";
    public final static String COURSEWARETEACH = "coursewareTeach";

    //老师每日任务做题
    public final static String TEACHER_EVERY_DAY_PATH = "teacherWord.html?";
    public final static String TEACHER_EVERY_DAY = "teacherWord";

    //老师每日任务测试
    public final static String TEACHER_EVERY_DAY_TEST_PATH = "dailyTaskTest.html?";
    public final static String TEACHER_EVERY_DAY_TEST = "dailyTaskTest";

    //对弈
    public final static String MATCHPLAY_PATH = "matchPlay.html?";
    public final static String MATCHPLAY = "matchPlay";

    //play
    public final static String PLAY_PATH = "play.html?";
    public final static String PLAY = "play";

    //班级管理
    public final static String CLASSMANAGE_PATH = "classManage.html?";//classManage
    public final static String CLASSMANAGE = "classManage";

    //收藏
    public final static String COLLECTION_PATH = "collection.html?";//
    public final static String COLLECTION = "collection";

    //布置作业
    public final static String SCHOOLHOMEWORK_PATH = "schoolHomework.html?";//
    public final static String SCHOOLHOMEWORK = "schoolHomework";

    //视频地址
    public final static String VIDEO_PATH = "http://vodyq6aqp0d.vod.126.net/vodyq6aqp0d/";

    //教务管理
    public final static String TEACHMANAGEMENT_PATH = "teaching.html?";
    public final static String TEACHMANAGEMENT = "teaching";

    //学务管理
    public final static String STUDENTMANGEMENT_PATH = "branchCampus.html?";
    public final static String STUDENTMANGEMENT = "branchCampus";

    //老师判棋页面
    public final static String TEACHERJUDGE_PATH = "fromChess.html?";
    public final static String TEACHERJUDGE = "fromChess";


    //主页切换相关的字段
    //登陆接口路径
    public final static String REGISTER_PATH = "/gobangteach/UserBaseController/otherRegister";//用户注册接口
    public final static String LOGIN_PATH = "/gobangteach/UserBaseController/login";//用户登录接口
    public final static String PHONESENDMESSAGE_PATH = "/gobangteach/SysHomeController/sendMessage";//获取验证码接口
    public final static String FINDPASSWORD_PATH = "/gobangteach/UserBaseController/forgetPassword";//找回密码接口

    public final static String LOGIN = "login";
    public final static String REGISTER = "register";
    public final static String FINDPASSWORD = "forgetPassword";
    public final static String PHONESENDMESSAGE = "sendMessage";

    //个人中心接口
    public final static String PERSONAL_RadarAll_PATH = "/gobangteach/RadarController/RadarAll";//五维能力图接口
    public final static String PERSONAL_GrowthData_PATH = "/gobangteach/GrowthController/queryGrowthData";//成长日志接口
    public final static String PERSONAL_LEVELUPORDOWN_PATH = "/gobangteach/UserBaseController/levelUpOrDown";//升降局接口
    public final static String PERSONAL_HISTORYGAMES_PATH = "/gobangteach/UserBaseController/historygames";//胜负情况统计
    public final static String UPDATEMYUSERINFOANDHEAD = "/gobangteach/UserBaseController/updateMyUserInfo_ios";//修改用户头像接口和修改资料

    public final static String RADARALL = "radarall";
    public final static String GROWTHDATA = "growthData";
    public final static String LEVELUPORDOWN = "levelupdown";
    public final static String HISTORYGAME = "historygame";
    public final static String UPDATEMYUSERINFO = "updateMyUserInfo";

    //用户退出
    public final static String EXITLOGIN = "/gobangteach/UserBaseController/exitLongin";

    //课件播放
    public final static String VIDEOBYNAME = "/gobangteach/videos/getVideoByName";
    public final static String GETVIDEOBYNAME = "getVideoByName";

    //题库
    public final static String QUESTION_LISTLEVEL_PATH = "/gobangteach/classroom/listTypeLevel";//题库题型的等级列表接口

    //题库
    public final static String QUESTION_LISTKNOWLEDGE_PATH = "/gobangteach/classroom/listKnowledge";//题库根据棋力等级获取相关知识点
    public final static String QUESTION_LISTKNOWLEDGE = "listKnowledge";//题库题型的知识列表

    //根据学生查询所属机构下老师
    public final static String BELONGED_PATH = "/gobangteach/SchoolController/belonged";//查找老师接口

    //11.根据老师查询创建班级
    public final static String TEACHERCLASS_PATH = "/gobangteach/SchoolController/TcheckClass";//查找班级
    public final static String TEACHERCLASS = "TcheckClass";//查找老师接口

    //查找更新
    public final static String UPDATEVERSION_PATH = ContactUrl.BASE_PATH3 + "/gobangteach/pcUpdateController/getNewMobileVersion";

    //讲解模式
    public final static String QUESTIONBANKBYCLASSID_PATH = "/gobangteach/QuestionbankController/getQuestionbankByClassId";
    public final static String QUESTIONBANKBYCLASSID = "getQuestionbankByClassId";

    public final static String GETUSERSLIST = "/gobangteach/UserBaseController/listUsers";

    //指导视频
    public final static String JINIORVIDEO_PATH = "/gobangteach/videos/getRecordedVideo";
    public final static String JINIORVIDEO = "getRecordedVideo";

    //教学体系
    public final static String TEACHSTRUCTURE_PATH = "/gobangteach/PreparelessonController/getTeachStructure";

    //获取备课列表
    public final static String GETPREPARELESSONS = "/gobangteach/PreparelessonController/getPreparelessons_new";

    //获取教学数据
    public final static String GETPREPARELESSONSBYID = "/gobangteach/PreparelessonController/getMyPreparelessonsById_ios";

    //开始备课
    public final static String GETPREPARELESSONSBYIDNEW = BASE_PATH3 + "/gobangteach/PreparelessonController/getPreparelessonsById_new_ios";

    //提交备课
    public final static String GETCREATEPREARELESSONSNEW = "/gobangteach/PreparelessonController/createPreparelessons_new";

    //系统设置
    public final static String SETUPMYOPTIONS_PATH = "/gobangteach/UserBaseController/updateMyOptions_ios";

    //我的信息中展示的四个成就  
    public final static String GET_FOUR_ACHIEVEMENT = BASE_PATH3 + "/gobangteach/AchievementController/selectRecentAchievement";

    //已经获取的成就
    public final static String GET_ALREADY_ACHIEVEMENT = BASE_PATH3 + "/gobangteach/AchievementController/selectAlreadyAchievement";

    //未获取的成就
    public final static String GET_DID_NOT_ACHIEVEMENT = BASE_PATH3 + "/gobangteach/AchievementController/selectNotReachAchievement";

    //统计接口
    public final static String GET_STATISTICS = BASE_PATH3 + "/gobangteach/StatisticsController/addModuleClick";

    //视频积分
    public final static String GET_VIDEO_INTEGRAL = BASE_PATH3 + "/gobangteach/videos/lookVideoTime";

    //学生作业
    public final static String GET_DAYTASK = BASE_PATH3 + "/gobangteach/DaytaskController/getDayTask_new";

    //获取积分商品分类列表
    public final static String GET_COMMODITY_CLASSIFY = BASE_PATH3 + "/gobangteach/MallController/getProductClassifyList";

    //获取积分商品列表
    public final static String GET_SHOPPING_CLASSIFY = BASE_PATH3 + "/gobangteach/MallController/getProductList";

    //获取积分商城购买记录
    public final static String GET_SHOPPING_FOR_RECORD = BASE_PATH3 + "/gobangteach/MallController/getOrderList";

    //积分商城购买
    public final static String GET_SHOPPING_BUY = BASE_PATH3 + "/gobangteach/MallController/purchaseProduct";

    //积分商城商品使用
    public final static String GET_SHOPPING_USE = BASE_PATH3 + "/gobangteach/MallController/useProduct";

    //围棋故事
    public final static String GET_WI_QI_STORY = BASE_PATH3 + "/gobangteach/GoCultureController/getGoStoryList";

    //老师当天的每日任务
    public final static String GET_TEACHER_TASK = BASE_PATH3 + "/gobangteach/PushTeacherController/teacherDayTask";

    //老师所有的每日任务
    public final static String GET_TEACHER_ALL_TASK = BASE_PATH3 + "/gobangteach/PushTeacherController/getTeacheAll";

    //老师的视频
    public final static String GET_TEACHER_VIDEO = BASE_PATH3 + "/gobangteach/videos/getVideoByType";

    //老师完成视频任务
    public final static String GET_GO_DAY_TEACHER_TASK = BASE_PATH3 + "/gobangteach/PushTeacherController/goTeacherDayTask";

    //学生完成故事任务
    public final static String GET_GO_DAY_STUDENT_STORY_TASK = BASE_PATH3 + "/gobangteach/DaytaskController/goDayTask";

    //学生完成引导
    public final static String GET_STUDENT_GUIDANCE = BASE_PATH3 + "/gobangteach/UserBaseController/updateStuNoviceGuidance";

    //创建AD棋局
    public final static String GET_IS_PLAY_CHESS = BASE_PATH3 + "/gobangteach/ChessController/createAIChess";

    //获取分校管理列表
    public final static String GET_SCHOOL_MANAGEMENT = BASE_PATH3 + "/gobangteach/BranSchoolController/branchCampus";

    //删除分校管理列表数据
    public final static String GET_DELATE_SCHOOL_MANAGEMENT = BASE_PATH3 + "/gobangteach/BranSchoolController/delBranchCampus";

    //校长端查询老师页面
    public final static String GET_TEACHER_DATA = BASE_PATH3 + "/gobangteach/BranSchoolController/searchTeacher";

    //校长端提交新建分校信息
    public final static String GET_NEW_SCHOOL_DATA = BASE_PATH3 + "/gobangteach/BranSchoolController/createBranchCampus";

    //校长端提交修改分校信息
    public final static String GET_UPDATE_SCHOOL_DATA = BASE_PATH3 + "/gobangteach/BranSchoolController/updateBranchCampus";

    //获取师训数据
    public final static String GET_QUALIFICATIONS_DATA = BASE_PATH3 + "/gobangteach/presidentController/getPresidentList";

    //获取学校列表
    public final static String GET_SCHOOL_NAME_DATA = BASE_PATH3 + "/gobangteach/BranSchoolController/branchCox";

    //视频观看记录接口
    public final static String GET_VIDEO_RECORD = BASE_PATH3 + "/gobangteach/videos/addVideoRecord";

    //获取下拉机构信息
    public final static String GET_ORG_LIST = BASE_PATH3 + "/gobangteach/presidentController/getOrgList";

    //校长端看老师打卡情况
    public final static String GET_TEACHER_ORG_LIST = BASE_PATH3 + "/gobangteach/PushTeacherController/getTeacheProgressByOrg";

    //查看机构分润情况
    public final static String GET_ORG_PROFIT_LIST = BASE_PATH3 + "/gobangteach/presidentController/getOrgProfitList";

    //查看介绍分润
    public final static String GET_INTRODUCER_PROFIT_LIST = BASE_PATH3 + "/gobangteach/presidentController/getIntroducerProfitList";

    //微信支付生成订单
    public final static String POST_WX_PAY_PATH = BASE_PATH3 + "/gobangteach/wxpay/wxpay";

    //微信支付校验
    public final static String POST_SELECT_ORDER_PATH = BASE_PATH3 + "/gobangteach/wxpay/getPayInfoByOrderId";

    //使用激活码
    public final static String POST_UPDATE_KEY_PATH = BASE_PATH3 + "/gobangteach/CardController/updateKEY";

    //查询所有机构
    public final static String GET_SELECT_ALL_ORGNO = BASE_PATH3 + "/gobangteach/BranSchoolController/selectAllOrgNo";

    //查该机构下面的老师
    public final static String GET_ALL_USER_ORGNO = BASE_PATH3 + "/gobangteach/BranSchoolController/selectAllUserByOrgNo";

    //获取视频信息
    public final static String GET_VIDEO_COURSE_URISDICTION = BASE_PATH3 + "/gobangteach/videos/getCourseUrisdictionByClassId";

    //修改个人完善信息
    public final static String UPDATAMYUSERINFO_PATH = BASE_PATH3 + "/gobangteach/UserBaseController/updateMyUserInfo_ios";

    //查询所有的主院
    public final static String GET_PRIMARY_PATH = BASE_PATH3 + "/gobangteach/presidentController/getPrimary";

    //查询所有分园
    public final static String GET_BRANCH_PATH = BASE_PATH3 + "/gobangteach/presidentController/getBranch";

    //实训列表
    public final static String GET_PRESODENT_LIST_PATH = BASE_PATH3 + "/gobangteach/presidentController/getPresidentList_new";

    //分校列表
    public final static String GET_BRAN_LIST_PATH = BASE_PATH3 + "/gobangteach/presidentController/getBranList";

    //根据主校id查询分校数据
    public final static String GET_BRAN_LIST_PRIMARY_PATH = BASE_PATH3 + "/gobangteach/presidentController/getBranListByPrimary";

    //根据主园查询对应的 视频权限
    public final static String GET_COURSE_URISDICTION_PATH = BASE_PATH3 + "/gobangteach/videos/getCourseUrisdictionByOrgNo";

    //主园保存权限
    public final static String GET_SUBMIT_COURSE_ORGNO_PATH = BASE_PATH3 + "/gobangteach/videos/submitCourseOrgNo";

    //校长端比赛列表
    public final static String GET_MATCH_PATH = BASE_PATH3 + "/gobangteach/SchoolMasterMatchController/getMatchList";

    //报名管理列表
    public final static String POST_CREATE_UPDATE_MATCH = BASE_PATH3 + "/gobangteach/SchoolMasterMatchController/createUpdateMatch";

    //根据比赛id查询详细信息
    public final static String POST_MATCH_INFO_BYID_PATH = BASE_PATH3 + "/gobangteach/SchoolMasterMatchController/getMatchInfoById";

    //添加组别
    public final static String POST_CREATE_UPDATE_GROUP_PATH = BASE_PATH3 + "/gobangteach/SchoolMasterMatchController/createUpdateGroup";

    //学生端比赛列表
    public final static String POST_STU_MATCH_LIST_PATH = BASE_PATH3 + "/gobangteach/StuMatchController/getStuMatchList";

    //未报名已报名查看详情
    public final static String POST_STU_MATCH_BY_ID_PATH = BASE_PATH3 + "/gobangteach/StuMatchController/getStuMatchById";

    //学生报名
    public final static String POST_CREATE_ENROLL_PATH = BASE_PATH3 + "/gobangteach/StuMatchController/createEnroll";

    //学生取消报名
    public final static String POST_DELETE_ENROLL_PATH = BASE_PATH3 + "/gobangteach/StuMatchController/deleteEnroll";

    //学生对阵详情
    public final static String POST_GETARENAINFO_PATH = BASE_PATH3 + "/gobangteach/StuMatchController/getArenaInfo";

    //校长报名管理列表
    public final static String POST_ENROLL_MANEGER_LIST_PATH = BASE_PATH3 + "/gobangteach/SchoolMasterMatchController/getEnrollManegerList";

    //更改组别
    public final static String POST_UPDATE_ENROLL_PATH = BASE_PATH3 + "/gobangteach/SchoolMasterMatchController/updateEnroll";

    //判棋列表
    public final static String POST_GETJUDELIST_PATH = BASE_PATH3 + "/gobangteach/SchoolMasterMatchController/getJudgeList";

    //成绩表
    public final static String POST_ACHIECEMENTS_INFO_PATH = BASE_PATH3 + "/gobangteach/StuMatchController/getAchievementsInfo";

    //对阵列表
    public final static String POST_ARENA_INFO_PATH = BASE_PATH3 + "/gobangteach/StuMatchController/getArenaInfo";

    //老师班级列表
    public final static String POST_CLASS_INFO_LIST_PATH = BASE_PATH3 + "/gobangteach/SchoolController/classInfoList";

    //删除分组
    public final static String POST_DELETE_GROUP_PATH = BASE_PATH3 + "/gobangteach/SchoolMasterMatchController/deleteGroup";

    //修改密码
    public final static String POST_UPDATE_PWD_PATH = BASE_PATH3 + "/gobangteach/UserBaseController/updatePwd";

    //申请判棋
    public final static String POST_APPPLAY_JUDGE_PATH = BASE_PATH3 + "/gobangteach/SchoolMasterMatchController/appplyJudge";

    //好友列表
    public final static String POST_FRIENDS_INFO_PATH = BASE_PATH3 + "/gobangteach/FriendsController/getFriendsInfo";

    //添加好友
    public final static String POST_FRIENDS_SEND_INVITE_PATH = BASE_PATH3 + "/gobangteach/FriendsController/FriendsendInvite";

    //申请好友列表
    public final static String POST_FRIENDS_INVITE_LIST_PATH = BASE_PATH3 + "/gobangteach/FriendsController/FriendInviteList";

    //在线对弈
    public final static String POST_RANDOM_BATTLE_PATH = BASE_PATH3 + "/gobangteach/ChessController/randomBattle";

    //查询对弈状态
    public final static String POST_GAME_INFOR_PATH = BASE_PATH3 + "/gobangteach/ChessController/getGameInfoByUserId";

    //在线对弈匹配AI
    public final static String POST_CREATE_AI_MATCH_PATH = BASE_PATH3 + "/gobangteach/ChessController/createAIMatch";

    //邀请对弈
    public final static String POST_INVITE_PLAY_CHESS_PATH = BASE_PATH3 + "/gobangteach/FriendsController/InvitePlayChess";

    //邀请同意对弈
    public final static String POST_CREAT_INFOR_CHESS_PATH = BASE_PATH3 + "/gobangteach/ChessController/creatInformChess";

    //获取对弈棋局
    public final static String POST_GAME_INFO_USERID_PATH = BASE_PATH3 + "/gobangteach/ChessController/getGameInfoByUserId";

    //根据类型获取知识点
    public final static String POST_LIST_KNOWLEDGE_PATH = BASE_PATH3 + "/gobangteach/classroom/listKnowledge";

    //根据题目类型知识点获取级别
    public final static String POST_LEVEL_TYPE_KNOWLEDGE_PATH = BASE_PATH3 + "/gobangteach/classroom/getLevelByTypeAndKnowledge";

    //判断用户是否轮空
    public final static String POST_USER_IS_NULL_PATH = BASE_PATH3 + "/gobangteach/StuMatchController/getUserIsNull";

    //查询题库所有级别
    public final static String POST_QUESTION_LEVEL_PATH = BASE_PATH3 + "/gobangteach/classroom/getQuestionLevel";

    //历史对局接口
    public final static String PERSONAL_GETHISTORYRECORD_PATH = BASE_PATH3 + "/gobangteach/UserBaseController/getdetail";

    //历史对局收藏
    public final static String HISTORYCOLLECTCHESS_PATH = BASE_PATH3 + "/gobangteach/QuestionbankController/collectChess";

    //历史作业
    public final static String HISTORYTASK_PATH = BASE_PATH3 + "/gobangteach/SchoolController/historyTask";

    //对弈列表接口
    public final static String GETVSLIST = BASE_PATH3 + "/gobangteach/ChessController/getVsList";

    //获取用户信息接口
    public final static String GET_USER_INFOR_BY_ID_PATH = BASE_PATH3 + "/gobangteach/UserBaseController/getUserFullInfoById";

    //百度音频
    public final static String BAIDU_GET_TOKEN_PATH = BASE_PATH3 + "/gobangteach/BDController/getToken";

    //作业判断
    public final static String CURRENT_TAST_PATH = BASE_PATH3 + "/gobangteach/SchoolController/currentTask";

    //历史作业判断
    public final static String HISTORY_DETAIL_PATH = BASE_PATH3 + "/gobangteach/SchoolController/historyDetail";

    //获取最高的课程数
    public final static String MAX_SGF_STATE_PATH = BASE_PATH3 + "/gobangteach/QuestionbankController/getMaxSgfState";


}

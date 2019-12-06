package com.xswq.chess.myapplication.http.RequestService;

import com.xswq.chess.myapplication.utils.ContactUrl;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RegisterService {

    /**
     * 注册功能
     */
    @GET(ContactUrl.REGISTER_PATH)
    Observable<String> userRegister(@Query("userId") String userId, @Query("md5pass") String md5pass,@Query("client") String client);

    /**
     * 登录功能
     */
    @POST(ContactUrl.LOGIN_PATH)
    Observable<String> userLogin(@Query("idFlg") int idFlag, @Query("userId") String userId, @Query("md5pass") String md5pass,@Query("client") String client);

    /**
     * 验证码功能
     */
    @POST(ContactUrl.PHONESENDMESSAGE_PATH)
    Observable<String> getSendMessage(@Query("idFlg") String idFlg, @Query("userType") String userTyp, @Query("user_id") String userId,@Query("client") String client);

    /**
     * 找回密码
     */
    @POST(ContactUrl.FINDPASSWORD_PATH)
    Observable<String> getFindPassword(@Query("code") String code, @Query("idFlg") String idFlg, @Query("md5pass") String md5pass, @Query("userId") String userId);

    /**
     * 五维能力图
     */
    @GET(ContactUrl.PERSONAL_RadarAll_PATH)
    Observable<String> getRadarAll(@Query("userId") String userId, @Query("stage") int stage, @Query("token") String token, @Query("uid") String uid);


    /**
     * 成长日志
     */
    @POST(ContactUrl.PERSONAL_GrowthData_PATH)
    Observable<String> getGrowthData(@Query("userId") String userId, @Query("token") String token, @Query("uid") String uid);


    /**
     * 生降局对比
     */
    @GET(ContactUrl.PERSONAL_LEVELUPORDOWN_PATH)
    Observable<String> getLevelUpOrDown(@Query("userId") String userId, @Query("token") String token, @Query("uid") String uid);


    /**
     * 胜负情况统计
     */
    @GET(ContactUrl.PERSONAL_HISTORYGAMES_PATH)
    Observable<String> getHistoryGames(@Query("userId") String userId, @Query("token") String token, @Query("uid") String uid);


    /**
     * 修改头像和用户消息
     */
    @POST(ContactUrl.UPDATEMYUSERINFOANDHEAD)
    Observable<String> getupdateuserhead(@Query("id") String userId, @Query("address") String address, @Query("birthday") String birthday, @Query("sex") int sex, @Query("username") String username, @Query("headImg") String headImg, @Query("token") String token, @Query("uid") String uid);

    /**
     * 课件列表
     */
    @GET(ContactUrl.VIDEOBYNAME)
    Observable<String> getVideoByName(@Query("videoName") String videoName, @Query("token") String token, @Query("uid") String uid);

    /**
     * 解题模式
     */
    @POST(ContactUrl.QUESTIONBANKBYCLASSID_PATH)
    Observable<String> getExplanationPatterns(@Query("classId") String classId, @Query("pageNum") String pageNum, @Query("pageSize") String pageSize, @Query("titlesource") String titlesource, @Query("token") String token, @Query("uid") String uid);

    /**
     * 查询分类等级
     */
    @GET(ContactUrl.QUESTION_LISTLEVEL_PATH)
    Observable<String> getLevelList(@Query("questionType") int questionType, @Query("token") String token, @Query("uid") String uid);

    /**
     * 查询分类等级详细信息
     */
    @GET(ContactUrl.QUESTION_LISTKNOWLEDGE_PATH)
    Observable<String> getLevelListDetail(@Query("level") String level, @Query("questionType") int questionType, @Query("token") String token, @Query("uid") String uid);

    /**
     * 查询学生的老师
     */
    @POST(ContactUrl.BELONGED_PATH)
    Observable<String> geTeachers(@Query("id") String id, @Query("token") String token, @Query("uid") String uid);

    /**
     * 查询班级
     */
    @POST(ContactUrl.TEACHERCLASS_PATH)
    Observable<String> getClass(@Query("teacherId") String teacherId, @Query("token") String token, @Query("uid") String uid);


    /**
     * 指导视频
     */
    @GET(ContactUrl.JINIORVIDEO_PATH)
    Observable<String> getGuideVideo(@Query("JuniorVideoname") String juniorVideoname, @Query("pageNum") String pageNum, @Query("pageSize") String pageSize, @Query("token") String token, @Query("uid") String uid);

    /**
     * 查询用户列表
     * 查询更新
     **/
    @GET(ContactUrl.GETUSERSLIST)
    Observable<String> getUserList(@Query("token") String token, @Query("uid") String uid);

    /**
     * 教学体系
     **/
    @POST(ContactUrl.TEACHSTRUCTURE_PATH)
    Observable<String> getTeacherSystem(@Query("classType") String classType, @Query("teachingType") String teachingType, @Query("pageNum") String pageNum, @Query("pageSize") String pageSize, @Query("token") String token, @Query("uid") String uid);

    /**
     * 查询用户列表
     * 查询更新
     **/
    @POST(ContactUrl.GETPREPARELESSONS)
    Observable<String> getPreparelessons(@Query("prepareLessinStage") String classType, @Query("pageNum") String pageNum, @Query("pageSize") String pageSize, @Query("token") String token, @Query("uid") String uid, @Query("userId") String userId);


    /**
     * 获取七步教学数据
     * 查询更新
     **/
    @POST(ContactUrl.GETPREPARELESSONSBYID)
    Observable<String> getPreparelessonsById(@Query("outsideId") String outsideId, @Query("token") String token, @Query("uid") String uid);

    /**
     * 开始备课列表
     **/
    @POST(ContactUrl.GETPREPARELESSONSBYIDNEW)
    Observable<String> getPreparelessonsByIdNew(@Query("headLine") String headLine, @Query("prepareLessinStage") String prepareLessinStage, @Query("token") String token, @Query("uid") String uid);

    /**
     * 提交备课
     **/
    @POST(ContactUrl.GETCREATEPREARELESSONSNEW)
    Observable<String> getCreatePreareLessonsNew(@Query("userId") String userId, @Query("tempId") String tempId, @Query("myHandouts1") String myHandouts1, @Query("myHandouts2") String myHandouts2, @Query("myHandouts3") String myHandouts3, @Query("myHandouts4") String myHandouts4, @Query("myHandouts5") String myHandouts5, @Query("myHandouts6") String myHandouts6, @Query("myHandouts7") String myHandouts7, @Query("token") String token, @Query("uid") String uid);

    /**
     * 系统设置
     **/
    @POST(ContactUrl.SETUPMYOPTIONS_PATH)
    Observable<String> getSetupMyOption(@Query("id") String userid, @Query("matchOpts") int matchOpts, @Query("soundOpts") int soundOpts, @Query("imOpts") int imOpts, @Query("token") String token, @Query("uid") String uid);

}

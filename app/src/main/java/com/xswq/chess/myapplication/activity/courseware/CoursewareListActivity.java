package com.xswq.chess.myapplication.activity.courseware;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.xswq.chess.myapplication.R;
import com.xswq.chess.myapplication.activity.main.TeacherUserHelpActivity;
import com.xswq.chess.myapplication.adapter.CoursewareListAdapter;
import com.xswq.chess.myapplication.androidtojs.JsWebViewActivity;
import com.xswq.chess.myapplication.base.BaseCompatActivity;
import com.xswq.chess.myapplication.bean.CoursewareImage;
import com.xswq.chess.myapplication.bean.CoursewareVideoInforBean;
import com.xswq.chess.myapplication.config.Const;
import com.xswq.chess.myapplication.http.HttpCallBackLisener;
import com.xswq.chess.myapplication.http.httpparameterapi.CoursewareListApi;
import com.xswq.chess.myapplication.http.httpparameterapi.ExplanationPatternApi;
import com.xswq.chess.myapplication.http.retrofitHttp.RetrofitManager;
import com.xswq.chess.myapplication.http.subscriber.CoursewareListSub;
import com.xswq.chess.myapplication.http.subscriber.ExplanationPatternSub;
import com.xswq.chess.myapplication.utils.ContactUrl;
import com.xswq.chess.myapplication.utils.GsonUtil;
import com.xswq.chess.myapplication.utils.LogUtil;
import com.xswq.chess.myapplication.utils.SPUtil;
import com.xswq.chess.myapplication.utils.StatisticsUtil;
import com.xswq.chess.myapplication.utils.ToastUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * 智能课件
 */
public class CoursewareListActivity extends BaseCompatActivity implements View.OnClickListener, HttpCallBackLisener {

    private CoursewareListAdapter mCoursewareListAdapter;
    public List<CoursewareImage> mVideoList;
    private String[] video_titles = new String[]{"01 围棋礼仪", "02 棋盘棋子", "03 棋子的气", "04 紧气提子", "05 打吃逃子"
            , "06 双方互相打吃", "07 虎口", "08 禁入点", "09 打劫", "10 打二还一和打多还一", "11 连接和切断"
            , "12 一二线吃子的秘密", "13 双打吃", "14 征吃", "15 门吃", "16 抱吃", "17 枷吃", "18 扑与倒扑", "19 接不归"
            , "20 利用对方缺陷逃子", "21 可逃与应弃之子", "22 两眼活棋", "23 基础死活常型(1)", "24 基础死活常型(2)"
            , "25 杀棋基础", "26 活棋基础", "27 布局基础(一)", "28 布局基础(二)", "29 对杀基础(一)"
            , "30 对杀基础(二)", "31 综合吃子(一)", "32 综合吃子(二)", "33 好形和坏形", "34 官子入门", "35 胜负计算"
            , "36 双枪定式", "37 尖顶定式", "38 拆边思维", "39 扩张模样", "40 打入意识", "41 有眼杀无眼(一)"
            , "42 有眼杀无眼(二)", "43 延气对杀", "44 延气对杀(二)", "45 制造征吃", "46 制造枷吃", "47 1目2目官子", "48 先手官子"
    };

    private int[] videoImage = new int[]{R.drawable.courseware_weiqietiquette_selector, R.drawable.courseware_chessboardflag_selector,
            R.drawable.connection_cuts, R.drawable.courseware_now_mention_selector, R.drawable.courseware_5,
            R.drawable.courseware_to_manys_selector, R.drawable.courseware_eatandeat, R.drawable.courseware_tiger_mouth_selector,
            R.drawable.courseware_tiger_mouth1_selector, R.drawable.courseware_looting_selector, R.drawable.courseware_fight_escape_selector,
            R.drawable.courseware_wisdom_flag_selector, R.drawable.courseware_eating_code_selector, R.drawable.courseware_doubles_eating_selector,
            R.drawable.courseware_eatandeat_selector, R.drawable.courseware_door_eat_selector, R.drawable.courseware_embrace_eat_selector,
            R.drawable.courseware_gal_eat_selector, R.drawable.courseware_flutter_selector, R.drawable.courseware_no_return_selector,
            R.drawable.courseware_normal_death_selector, R.drawable.courseware_prohibit_point_selector, R.drawable.courseware_two_eyed_chess_selector,
            R.drawable.courseware_normal_death1, R.drawable.courseware_normal_death1_selector, R.drawable.courseware_26, R.drawable.courseware_27,
            R.drawable.courseware_28, R.drawable.courseware_29, R.drawable.courseware_30, R.drawable.courseware_31, R.drawable.courseware_32, R.drawable.courseware_33,
            R.drawable.courseware_34, R.drawable.courseware_35, R.drawable.courseware_36, R.drawable.courseware_37, R.drawable.courseware_38, R.drawable.courseware_39,
            R.drawable.courseware_40, R.drawable.courseware_41, R.drawable.courseware_42, R.drawable.courseware_43, R.drawable.courseware_44, R.drawable.courseware_45,
            R.drawable.courseware_46, R.drawable.courseware_47, R.drawable.courseware_48};

    private CoursewareListApi mCoursewareListApi;
    private CoursewareListSub mCoursewareListSub;
    private int positions;//获取当前item的位置
    private TextView jumpVideoHead;
    private int isJumpVideoHead = 1;
    private String classInfoId;
    private String title;

    @Override
    protected int getLayoutId() {
        return R.layout.coursewarelist_layout;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void loadData() {
        classInfoId = SPUtil.getString("classInfoId", "");
        StatisticsUtil.getStatistics(token, userId, 3, CoursewareListActivity.this);
        if (!TextUtils.isEmpty(classInfoId) && !Const.STRING_NULL.equals(classInfoId)) {
            //判断视频是否可以观看
            getVideoInfor();
        } else {
            //判断视频都可观看
            initialData();
            init();
            if (mVideoList != null && mVideoList.size() > 0) {
                for (int i = 0; i < mVideoList.size(); i++) {
                    mVideoList.get(i).setCanWatch(true);
                }
                mCoursewareListAdapter.addAll(mVideoList);
            }
        }
    }


    private void init() {
        getWindow().getDecorView().setBackgroundResource(R.mipmap.courseware_bg);
        TextView login_back = findViewById(R.id.login_back);
        login_back.setVisibility(View.VISIBLE);
        login_back.setOnClickListener(this);
        TextView login_titles = findViewById(R.id.login_titles);
        login_titles.setText("标准化智能互动课件");
        jumpVideoHead = findViewById(R.id.login_queding);
        jumpVideoHead.setVisibility(View.VISIBLE);
        jumpVideoHead.setTextSize(12);
        jumpVideoHead.setText("跳过片头");
        jumpVideoHead.setOnClickListener(this);
        final GridLayoutManager manager = new GridLayoutManager(CoursewareListActivity.this, 2);
        mCoursewareListAdapter = new CoursewareListAdapter(CoursewareListActivity.this);
        RecyclerView courseware_view = findViewById(R.id.courseware_view);
        courseware_view.setLayoutManager(manager);
        courseware_view.setAdapter(mCoursewareListAdapter);
        mCoursewareListAdapter.setOnItemClickListener(new CoursewareListAdapter.OnRecyclerviewItemClickListener() {

            @Override
            public void onItemClick(View view, int position) {
                try {
                    if (view.getId() == R.id.courseware_video) {
                        mCoursewareListApi = new CoursewareListApi(CoursewareListActivity.this);
                        mCoursewareListApi.setMethod(ContactUrl.GETVIDEOBYNAME);
                        mCoursewareListApi.setUid(userId);
                        mCoursewareListApi.setToken(token);
                        String videoRequest = "courseware_" + (position + 1);
                        mCoursewareListApi.setVideoName(videoRequest);
                        mCoursewareListSub = new CoursewareListSub(mCoursewareListApi);
                        RetrofitManager.getRetrofitInstance().handleHttp(mCoursewareListSub, mCoursewareListApi);
                    } else {
                        positions = position;
                        title = video_titles[positions];
                        getRequestParamer(positions);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_back:
                finish();
                break;
            case R.id.login_queding:
                if (jumpVideoHead.getText().toString().equals("跳过片头")) {
                    jumpVideoHead.setText("取消跳过");
                    isJumpVideoHead = 2;
                } else if (jumpVideoHead.getText().toString().equals("取消跳过")) {
                    jumpVideoHead.setText("跳过片头");
                    isJumpVideoHead = 1;
                }
                break;
            default:
                break;
        }


    }

    @Override
    public void onNext(Object json, String method) {
        try {
            JSONObject error = ((JSONObject) json).getJSONObject("error");
            int returnCode = error.getInt("returnCode");
            JSONObject data = ((JSONObject) json).getJSONObject("data");
            if (returnCode == 0) {
                if (method.equals(ContactUrl.GETVIDEOBYNAME)) {
                    Intent popIntert = new Intent(CoursewareListActivity.this, VideoPlaybackActivity.class);
                    popIntert.putExtra("videoUrl", data.getString("VideoUrl"));
                    popIntert.putExtra("ID", data.getString("ID"));
                    popIntert.putExtra("touken", token);
                    popIntert.putExtra("userId", userId);
                    popIntert.putExtra("userType", userType);
                    popIntert.putExtra("isJumpVideoHead", isJumpVideoHead);
                    startActivity(popIntert);
                } else {
                    int total = data.optInt("total");
                    if (total == 0) {
                        jumpJsIntent();
                    } else {
                        Intent mIntent = new Intent(CoursewareListActivity.this, PracticeDialogActivity.class);
                        mIntent.putExtra("classId", (positions + 1));
                        mIntent.putExtra("title", title);
                        startActivity(mIntent);
                    }
                }
            } else if (returnCode == 10048) {
                quiteApp(CoursewareListActivity.this, error.optString("returnMessage"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onError(Throwable e) {

    }

    //公共跳转JsActivity页面
    private void jumpJsIntent() {
        Intent mIntent = new Intent(CoursewareListActivity.this, JsWebViewActivity.class);
        mIntent.putExtra("uid", userId);
        mIntent.putExtra("token", token);
        mIntent.putExtra("classId", (positions + 1));
        mIntent.putExtra("title", video_titles[positions]);
        mIntent.putExtra("prefix", ContactUrl.COURSEWAREDETAIL);
        startActivity(mIntent);
    }

    //初始数据
    private void initialData() {
        mVideoList = new ArrayList<>();
        for (int i = 0; i < video_titles.length; i++) {
            CoursewareImage mCoursewareImage = new CoursewareImage();
            mCoursewareImage.setTitle(video_titles[i]);
            mCoursewareImage.setVideo(videoImage[i]);
            if (i > 1 && i != 34) {
                mCoursewareImage.setPractice(R.drawable.courseware_eat_each1_selector);
            }
            mVideoList.add(mCoursewareImage);
        }
    }

    //获取视频信息
    private void getVideoInfor() {
        try {
            OkHttpUtils.post().url(ContactUrl.GET_VIDEO_COURSE_URISDICTION)
                    .addParams("token", token)
                    .addParams("uid", userId)
                    .addParams("classId", classInfoId)
                    .build().execute(new StringCallback() {

                @Override
                public void onError(Call call, Exception e, int id) {
                    ToastUtils.show(Const.CONS_STR_ERROR);
                }

                @Override
                public void onResponse(String response, int id) {
                    initialData();
                    init();
                    if (!TextUtils.isEmpty(response)) {
                        CoursewareVideoInforBean coursewareVideoInforBean = GsonUtil.gsonToBean(response, CoursewareVideoInforBean.class, CoursewareListActivity.this);
                        if (coursewareVideoInforBean != null) {
                            CoursewareVideoInforBean.DataBean data = coursewareVideoInforBean.getData();
                            if (data != null) {
                                String canWatch = data.getCanWatch();
                                if (!TextUtils.isEmpty(canWatch) && canWatch.length() > 0) {
                                    String[] split = canWatch.split(",");
                                    for (String strIndext : split) {
                                        if (!TextUtils.isEmpty(strIndext)) {
                                            int index = Integer.parseInt(strIndext) - 1;
                                            mVideoList.get(index).setCanWatch(true);
                                            LogUtil.e(strIndext);
                                        }
                                    }
                                }
                            }
                            mCoursewareListAdapter.addAll(mVideoList);
                        }
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getRequestParamer(int positions) {
        ExplanationPatternApi mExplanationPatternApi = new ExplanationPatternApi(CoursewareListActivity.this);
        mExplanationPatternApi.setMethod(ContactUrl.QUESTIONBANKBYCLASSID);
        mExplanationPatternApi.setClassid(String.valueOf(positions + 1));
        mExplanationPatternApi.setTitleResource(title);
        mExplanationPatternApi.setToken(token);
        mExplanationPatternApi.setUid(userId);
        ExplanationPatternSub mExplanationPatternSub = new ExplanationPatternSub(mExplanationPatternApi);
        RetrofitManager.getRetrofitInstance().handleHttp(mExplanationPatternSub, mExplanationPatternApi);

    }

}

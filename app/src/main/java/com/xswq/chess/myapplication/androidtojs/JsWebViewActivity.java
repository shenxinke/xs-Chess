package com.xswq.chess.myapplication.androidtojs;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xswq.chess.myapplication.R;
import com.xswq.chess.myapplication.activity.main.SwitchMainActivity;
import com.xswq.chess.myapplication.activity.main.TeacherUserHelpActivity;
import com.xswq.chess.myapplication.activity.mywork.HistoryTaskActivity;
import com.xswq.chess.myapplication.activity.start.LoginActivity;
import com.xswq.chess.myapplication.application.XSWQApplication;
import com.xswq.chess.myapplication.base.BaseActivity;
import com.xswq.chess.myapplication.utils.ContactUrl;
import com.xswq.chess.myapplication.utils.LogUtil;
import com.xswq.chess.myapplication.utils.PreferencesUtils;
import com.xswq.chess.myapplication.utils.SPUtil;
import com.xswq.chess.myapplication.utils.StatisticsUtil;
import com.xswq.chess.myapplication.utils.ToastUtils;
import com.xswq.chess.myapplication.utils.Util;
import com.gyf.immersionbar.ImmersionBar;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

import java.net.URLDecoder;

public class JsWebViewActivity extends BaseActivity implements View.OnClickListener {

    private Intent mIntent;
    private TextView title_backs;
    private TextView title_texts;
    private ConstraintLayout courseware_xiti_id;
    private MediaPlayer player;
    private int index;
    private String historyGameId;
    private int pageType1 = 8;
    private int pageType2 = 9;
    private int pageType3 = 10;
    private int webViewType;
    private WebView mWebView;
    private String titleCoursewareTeach;
    private String prefix1;
    private String intro;
    private String myWordTitle;
    private int total;

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jswebview_layout);
        showProgressDialog(true);
        mIntent = getIntent();
        index = mIntent.getIntExtra("index", 0);
        historyGameId = mIntent.getStringExtra("historyGameId");
        intro = mIntent.getStringExtra("Intro");
        webViewType = mIntent.getIntExtra("webViewType", 0);
        titleCoursewareTeach = mIntent.getStringExtra("title");
        prefix1 = mIntent.getStringExtra("prefix");
        if (TextUtils.isEmpty(prefix1)) {
            prefix1 = SPUtil.getString("prefix", "");
            SPUtil.remove("prefix");
        }
        init();
    }

    @SuppressLint("ResourceAsColor")
    private void init() {
        ImageView loginUserHelp = findViewById(R.id.user_help);
        if (userType != 3) {
            if (webViewType == 1 || webViewType == 2 || webViewType == 3) {
                loginUserHelp.setVisibility(View.VISIBLE);
                loginUserHelp.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //1布置作业 2棋力测评 3电子棋盘
                        if (webViewType == 1) {
                            TeacherUserHelpActivity.openActivity(JsWebViewActivity.this, pageType1);
                        } else if (webViewType == 2) {
                            TeacherUserHelpActivity.openActivity(JsWebViewActivity.this, pageType2);
                        } else if (webViewType == 3) {
                            TeacherUserHelpActivity.openActivity(JsWebViewActivity.this, pageType3);
                        }
                    }
                });
            } else {
                loginUserHelp.setVisibility(View.GONE);
            }
        }
        title_backs = findViewById(R.id.title_backs);
        title_backs.setOnClickListener(this);
        title_texts = findViewById(R.id.title_texts);
        courseware_xiti_id = findViewById(R.id.courseware_xiti_id);
        TextView title_quedingb_btn = findViewById(R.id.title_quedingb_btn);
        mWebView = findViewById(R.id.jstoandroid_id);
        mWebView.setBackgroundColor(0); // 设置背景色
        WebView.setWebContentsDebuggingEnabled(true);
        mWebView.clearCache(true);
        mWebView.clearHistory();
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setAllowContentAccess(true);
        webSettings.setBlockNetworkImage(false);
        webSettings.setLoadsImagesAutomatically(true);
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webSettings.setAllowFileAccessFromFileURLs(true);
        webSettings.setAppCacheEnabled(true);
        webSettings.setSupportMultipleWindows(true);
        webSettings.setAppCacheMaxSize(Long.MAX_VALUE);
        webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);

        switch (prefix1) {
            case ContactUrl.COURSEWAREDETAIL: //课件习题
                int classId = mIntent.getIntExtra("classId", 0);
                String title = mIntent.getStringExtra("title");
                mWebView.loadUrl(ContactUrl.WEBVIEW_PATH + ContactUrl.COURSEWAREDETAIL_PATH + "uid=" + userId + "&token=" + token + "&classId=" + classId);
                if (!TextUtils.isEmpty(title)) {
                    title_texts.setText(Util.signString(title.split(" ")[1]));
                }
                getWindow().getDecorView().setBackgroundResource(R.mipmap.evaluation_bg);
                break;
            case ContactUrl.QUESTIONSTORE: //题库
                int intentType = mIntent.getIntExtra("intentType", 0);
                String courseNum = mIntent.getStringExtra("courseNum");
                String level = mIntent.getStringExtra("level");
                String questionType = mIntent.getStringExtra("questionType");
                String questionName = mIntent.getStringExtra("questionName");
                switch (intentType) {
                    case 1:
                        mWebView.loadUrl(ContactUrl.WEBVIEW_PATH + ContactUrl.QUESTIONSTORE_PATH + "uid=" + userId + "&token=" + token + "&courseNum=" + courseNum + "&type=" + intentType);
                        break;
                    case 2:
                        mWebView.loadUrl(ContactUrl.WEBVIEW_PATH + ContactUrl.QUESTIONSTORE_PATH + "uid=" + userId + "&token=" + token + "&level=" + level + "&questionType=" + questionType + "&type=" + intentType);
                        break;
                    case 3:
                        if ("全部".equals(questionName)) {
                            mWebView.loadUrl(ContactUrl.WEBVIEW_PATH + ContactUrl.QUESTIONSTORE_PATH + "uid=" + userId + "&token=" + token + "&level=" + level + "&questionName=" + "&questionType=" + questionType + "&type=" + intentType);
                        } else {
                            String utf8QuestionName = Util.toUtf8(questionName);
                            mWebView.loadUrl(ContactUrl.WEBVIEW_PATH + ContactUrl.QUESTIONSTORE_PATH + "uid=" + userId + "&token=" + token + "&level=" + level + "&questionName=" + utf8QuestionName + "&questionType=" + questionType + "&type=" + intentType);
                        }
                        break;
                    default:
                        break;
                }
                title_texts.setText("题库");
                getWindow().getDecorView().setBackgroundResource(R.mipmap.evaluation_bg);
                break;
            case ContactUrl.ELECBOARD: //教师与学生的电子棋盘
                title_texts.setText("电子棋盘");
                getWindow().getDecorView().setBackgroundResource(R.mipmap.evaluation_bg);
                if (historyGameId != null) {
                    mWebView.loadUrl(ContactUrl.WEBVIEW_PATH + ContactUrl.ELECBOARD_PATH + "uid=" + userId + "&token=" + token + "&historyGameId=" + historyGameId);
                } else {
                    mWebView.loadUrl(ContactUrl.WEBVIEW_PATH + ContactUrl.ELECBOARD_PATH + "uid=" + userId + "&token=" + token);
                }
                break;
            case ContactUrl.TESTTITLE: //测评页面
                StatisticsUtil.getStatistics(token, userId, 19, JsWebViewActivity.this);
                mWebView.loadUrl(ContactUrl.WEBVIEW_PATH + ContactUrl.TESTTITLE_PATH + "uid=" + userId + "&token=" + token);
                title_texts.setText("测评");
                getWindow().getDecorView().setBackgroundResource(R.mipmap.evaluation_bg);
                break;
            case ContactUrl.MYWORD:
            case ContactUrl.MYWORDHISTORY:
                StatisticsUtil.getStatistics(token, userId, 6, JsWebViewActivity.this);
                String jobId = getIntent().getStringExtra("jobId");
                myWordTitle = getIntent().getStringExtra("title");
                if (TextUtils.isEmpty(jobId)) {
                    total = getIntent().getIntExtra("total", 0);
                    String titleString;
                    if ("历史作业".equals(myWordTitle)) {
                        if (total == 0) {
                            titleString = "历史作业";
                        } else {
                            titleString = "历史作业(1/" + total + ")";
                        }
                    } else {
                        title_quedingb_btn.setVisibility(View.VISIBLE);
                        title_quedingb_btn.setText("历史作业");
                        title_quedingb_btn.setOnClickListener(this);
                        title_quedingb_btn.setTextColor(this.getResources().getColor(R.color.glod));
                        if (total == 0) {
                            titleString = "作业";
                        } else {
                            titleString = "作业(1/" + total + ")";
                        }
                    }
                    title_texts.setText(titleString);
                    mWebView.loadUrl(ContactUrl.WEBVIEW_PATH + ContactUrl.MYWORD_PATH + "uid=" + userId + "&token=" + token);
                } else {
                    if ("历史作业".equals(myWordTitle)) {
                        title_texts.setText("历史作业");
                    } else {
                        title_quedingb_btn.setVisibility(View.VISIBLE);
                        title_quedingb_btn.setText("历史作业");
                        title_quedingb_btn.setOnClickListener(this);
                        title_quedingb_btn.setTextColor(this.getResources().getColor(R.color.glod));
                        title_texts.setText("作业");
                    }
                    mWebView.loadUrl(ContactUrl.WEBVIEW_PATH + ContactUrl.MYWORDHISTORY_PATH + "uid=" + userId + "&token=" + token + "&jobid=" + jobId);
                }
                getWindow().getDecorView().setBackgroundResource(R.mipmap.aionline_gamebg);
                break;
            case ContactUrl.WRONGQUESTION:
                StatisticsUtil.getStatistics(token, userId, 4, JsWebViewActivity.this);
                mWebView.loadUrl(ContactUrl.WEBVIEW_PATH + ContactUrl.WRONGQUESTION_PATH + "uid=" + userId + "&token=" + token + "&userId=" + userId);
                title_texts.setText("错题本");
                getWindow().getDecorView().setBackgroundResource(R.mipmap.evaluation_bg);
                break;
            case ContactUrl.PLAY:
                int levels = SPUtil.getInt("level", 0);
                String blackUserId = mIntent.getStringExtra("blackUserId");
                if (!TextUtils.isEmpty(blackUserId)) {
                    mWebView.loadUrl(ContactUrl.WEBVIEW_PATH + ContactUrl.PLAY_PATH + "uid=" + userId + "&token=" + token + "&userId=" + blackUserId);
                } else {
                    String AIPlay = mIntent.getStringExtra("AIplay");
                    String titleName;
                    if (!TextUtils.isEmpty(AIPlay)) {
                        titleName = "AI对弈";
                        title_texts.setText(titleName);
                        if ("1".equals(AIPlay)) {
                            mWebView.loadUrl(ContactUrl.WEBVIEW_PATH + ContactUrl.PLAY_PATH + "uid=" + userId + "&token=" + token + "&Intro=" + "0" + "&level=" + levels);
                        } else {
                            mWebView.loadUrl(ContactUrl.WEBVIEW_PATH + ContactUrl.PLAY_PATH + "uid=" + userId + "&token=" + token + "&level=" + levels);
                        }
                    } else {
                        titleName = "在线对弈";
                        title_texts.setText(titleName);
                        mWebView.loadUrl(ContactUrl.WEBVIEW_PATH + ContactUrl.PLAY_PATH + "uid=" + userId + "&token=" + token + "&level=" + levels);
                    }
                }
                ImmersionBar.with(this).fitsSystemWindows(true).addTag("Play").init();
                getWindow().getDecorView().setBackgroundResource(R.mipmap.chess_bg);
                break;
            case ContactUrl.GAME:
                StatisticsUtil.getStatistics(token, userId, 5, JsWebViewActivity.this);
                String username = mIntent.getStringExtra("username");
                courseware_xiti_id.setVisibility(View.GONE);
                mWebView.loadUrl(ContactUrl.BASE_PATH + ContactUrl.GAME_PATH + "userid=" + userId + "&username=" + username);
                getWindow().getDecorView().setBackgroundResource(R.color.black);
                break;
            case ContactUrl.CLASSMANAGE:
                mWebView.loadUrl(ContactUrl.WEBVIEW_PATH + ContactUrl.CLASSMANAGE_PATH + "uid=" + userId + "&token=" + token);
                getWindow().getDecorView().setBackgroundResource(R.mipmap.prepare_lessons_backgroud);
                courseware_xiti_id.setVisibility(View.GONE);
                break;
            case ContactUrl.COLLECTION:
                mWebView.loadUrl(ContactUrl.WEBVIEW_PATH + ContactUrl.COLLECTION_PATH + "uid=" + userId + "&token=" + token);
                getWindow().getDecorView().setBackgroundResource(R.mipmap.prepare_lessons_backgroud);
                courseware_xiti_id.setVisibility(View.GONE);
                break;
            case ContactUrl.SCHOOLHOMEWORK:
                mWebView.loadUrl(ContactUrl.WEBVIEW_PATH + ContactUrl.SCHOOLHOMEWORK_PATH + "uid=" + userId + "&token=" + token);
                getWindow().getDecorView().setBackgroundResource(R.mipmap.prepare_lessons_backgroud);
                courseware_xiti_id.setVisibility(View.GONE);
                break;
            case ContactUrl.COURSEWARETEACH:
                String chessid = mIntent.getStringExtra("chessid");
                classId = mIntent.getIntExtra("class_id", 0);
                mWebView.loadUrl(ContactUrl.WEBVIEW_PATH + ContactUrl.COURSEWARETEACH_PATH + "uid=" + userId + "&token=" + token + "&chessid=" + chessid + "&classId=" + classId + "&index=" + index);
                getWindow().getDecorView().setBackgroundResource(R.mipmap.prepare_lessons_backgroud);
                courseware_xiti_id.setVisibility(View.VISIBLE);
                title_texts.setText(titleCoursewareTeach);
                break;
            case ContactUrl.TESTINGWORK:
                title_texts.setText("检查作业");
                mWebView.loadUrl(ContactUrl.WEBVIEW_PATH + ContactUrl.TESTINGWORK_PATH + "uid=" + userId + "&token=" + token);
                courseware_xiti_id.setVisibility(View.GONE);
                getWindow().getDecorView().setBackgroundResource(R.mipmap.prepare_lessons_backgroud);
                break;
            case ContactUrl.TEACHER_EVERY_DAY:
                String questions = mIntent.getStringExtra("questions");
                String accomplish = mIntent.getStringExtra("accomplish");
                String achieveQuestion = mIntent.getStringExtra("achieveQuestion");
                title_texts.setText("做题");
                mWebView.loadUrl(ContactUrl.WEBVIEW_PATH + ContactUrl.TEACHER_EVERY_DAY_PATH + "uid=" + userId + "&token=" + token + "&questions=" + questions + "&accomplish=" + accomplish + "&achieveQuestion=" + achieveQuestion);
                courseware_xiti_id.setVisibility(View.VISIBLE);
                getWindow().getDecorView().setBackgroundResource(R.mipmap.playchesss_bg);
                break;
            case ContactUrl.TEACHMANAGEMENT:
                title_texts.setText("教务管理");
                mWebView.loadUrl(ContactUrl.WEBVIEW_PATH + ContactUrl.TEACHMANAGEMENT_PATH + "uid=" + userId + "&token=" + token);
                courseware_xiti_id.setVisibility(View.GONE);
                getWindow().getDecorView().setBackgroundResource(R.mipmap.prepare_lessons_backgroud);
                break;
            case ContactUrl.STUDENTMANGEMENT:
                title_texts.setText("学务管理");
                mWebView.loadUrl(ContactUrl.WEBVIEW_PATH + ContactUrl.STUDENTMANGEMENT_PATH + "uid=" + userId + "&token=" + token);
                courseware_xiti_id.setVisibility(View.GONE);
                getWindow().getDecorView().setBackgroundResource(R.mipmap.president_bg);
                break;
            case ContactUrl.SPECIALIZED_TRAINING:
                title_texts.setText("专项训练");
                username = mIntent.getStringExtra("username");
                mWebView.loadUrl(ContactUrl.BASE_PATH + ContactUrl.SPECIALIZED_TRAINING_PATH + "uid=" + userId + "&token=" + token + "&username=" + username);
                courseware_xiti_id.setVisibility(View.GONE);
                ImmersionBar.with(this).fitsSystemWindows(true).addTag("Specialized_training").init();
                getWindow().getDecorView().setBackgroundResource(R.color.black);
                break;
            case ContactUrl.TEACHER_EVERY_DAY_TEST:
                title_texts.setText("测试");
                mWebView.loadUrl(ContactUrl.WEBVIEW_PATH + ContactUrl.TEACHER_EVERY_DAY_TEST_PATH + "uid=" + userId + "&token=" + token);
                courseware_xiti_id.setVisibility(View.VISIBLE);
                ImmersionBar.with(this).fitsSystemWindows(true).addTag("Teacher_Test").init();
                getWindow().getDecorView().setBackgroundResource(R.mipmap.chess_bg);
                break;
            case ContactUrl.MATCHPLAY:
                title_texts.setText("比赛");
                mWebView.loadUrl(ContactUrl.WEBVIEW_PATH + ContactUrl.MATCHPLAY_PATH + "uid=" + userId + "&token=" + token);
                courseware_xiti_id.setVisibility(View.VISIBLE);
                ImmersionBar.with(this).fitsSystemWindows(true).addTag("Match_play").init();
                getWindow().getDecorView().setBackgroundResource(R.mipmap.chess_bg);
                break;
            case ContactUrl.TEACHERJUDGE:
                title_texts.setText("判棋");
                String matchId = mIntent.getStringExtra("matchId");
                String fromChessArenaId = mIntent.getStringExtra("arenaId");
                String chessids = mIntent.getStringExtra("chessid");
                mWebView.loadUrl(ContactUrl.WEBVIEW_PATH + ContactUrl.TEACHERJUDGE_PATH + "uid=" + userId + "&token=" + token + "&matchId=" + matchId + "&arenaId=" + fromChessArenaId + "&chessid=" + chessids);
                courseware_xiti_id.setVisibility(View.VISIBLE);
                ImmersionBar.with(this).fitsSystemWindows(true).addTag("Teacher_Judge").init();
                getWindow().getDecorView().setBackgroundResource(R.mipmap.chess_bg);
                break;
            case ContactUrl.ANALYSE:
                title_texts.setText("复盘");
                mWebView.loadUrl(ContactUrl.WEBVIEW_PATH + ContactUrl.ANALYSE_PATH + "uid=" + userId + "&token=" + token);
                getWindow().getDecorView().setBackgroundResource(R.mipmap.main_analyse_bg);
                break;
        }

        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url.contains("myschema://go?a=6")) {
                    mIntent.putExtra("prefix", "");
                    view.loadUrl("about:blank");
                    view.loadUrl(ContactUrl.WEBVIEW_PATH + ContactUrl.PLAY_PATH + "uid=" + userId + "&token=" + token);
                    ImmersionBar.with(JsWebViewActivity.this).fitsSystemWindows(true).addTag("Play").init();
                    getWindow().getDecorView().setBackgroundResource(R.mipmap.chess_bg);
                    return true;
                } else if (url.equals("myschema://go?a=1")) {
                    finish();
                    return true;
                } else if (url.contains("myschema://go?a=2")) {
                    PreferencesUtils.putString(XSWQApplication.mContext, "uid", "");
                    startActivity(new Intent(JsWebViewActivity.this, LoginActivity.class));
                    return true;
                } else if (url.contains("myschema://go?a=401")) {
                    view.reload();
                    return true;
                } else if (url.contains("myschema://go?a=301")) {
                    title_backs.setOnClickListener(null);
                    midPlayerIsReading();
                    player = MediaPlayer.create(JsWebViewActivity.this, R.raw.question_select_1);
                    player.start();
                    return true;
                } else if (url.contains("myschema://go?a=302")) {
                    midPlayerIsReading();
                    player = MediaPlayer.create(JsWebViewActivity.this, R.raw.question_select_2);
                    player.start();
                    return true;
                } else if (url.contains("myschema://go?a=303")) {
                    midPlayerIsReading();
                    player = MediaPlayer.create(JsWebViewActivity.this, R.raw.question_select_3);
                    player.start();
                    return true;
                } else if (url.contains("myschema://go?a=304")) {
                    midPlayerIsReading();
                    player = MediaPlayer.create(JsWebViewActivity.this, R.raw.question_select_4);
                    player.start();
                    return true;
                } else if (url.contains("myschema://go?a=305")) {
                    midPlayerIsReading();
                    player = MediaPlayer.create(JsWebViewActivity.this, R.raw.question_select_5);
                    player.start();
                    return true;
                } else if (url.contains("myschema://go?a=306")) {
                    midPlayerIsReading();
                    player = MediaPlayer.create(JsWebViewActivity.this, R.raw.question_select_6);
                    player.start();
                    return true;
                } else if (url.contains("myschema://go?a=307")) {
                    SPUtil.put("storyGuideOver", 1);
                    Intent intent = new Intent(JsWebViewActivity.this, SwitchMainActivity.class);
                    startActivity(intent);
                    finish();
                    return true;
                } else if (url.contains("myschema://go?a=308")) {
                    title_backs.setOnClickListener(JsWebViewActivity.this);
                    return true;
                } else if (url.contains("myschema://go?a=309")) {
                    title_backs.setOnClickListener(JsWebViewActivity.this);
                    return true;
                } else if (url.contains("myschema://go?a=3")) {
                    mIntent.putExtra("prefix", "");
                    view.loadUrl("about:blank");
                    view.loadUrl(ContactUrl.WEBVIEW_PATH + ContactUrl.PLAY_PATH + "uid=" + userId + "&token=" + token);
                    getWindow().getDecorView().setBackgroundResource(R.mipmap.chess_bg);
                    return true;
                } else if (url.contains("myschema://go?a=3&targetGameId=3")) {
                    mIntent.putExtra("prefix", "");
                    view.loadUrl("about:blank");
                    view.loadUrl(ContactUrl.WEBVIEW_PATH + ContactUrl.PLAY_PATH + "targetGameId=3" + "&uid=" + userId + "&token=" + token);
                    return true;
                } else if (url.contains("myschema://go?a=4")) {
                    title_texts.setText("棋力测评");
                    view.loadUrl(ContactUrl.WEBVIEW_PATH + ContactUrl.TESTTITLE_PATH + "uid=" + userId + "&token=" + token);
                    title_backs.setOnClickListener(JsWebViewActivity.this);
                    return true;
                } else if (url.contains("myschema://go?a=7") || url.contains("gobangteach/gobangApp/html/")) {//俩种方式一种直接关闭确保没有其他页面打开，一种是关闭所有页面一次开启
                    startActivity(new Intent(JsWebViewActivity.this, SwitchMainActivity.class));
                    finish();
                    return true;
                } else if (url.contains("myschema://go?a=9")) {
                    view.loadUrl("about:blank");
                    view.loadUrl(ContactUrl.WEBVIEW_PATH + ContactUrl.LIVE_PATH + "roomId=" + "&state=" + "&uid=" + userId + "&token=" + token);//参数没有赋值
                    return true;
                } else if (url.contains("myschema://go?a=10")) {
                    try {
                        String musicname = url.split("10&music=")[1] + ".wav";
                        AssetManager assetManager = getAssets();
                        AssetFileDescriptor fileDescriptor = assetManager.openFd("gobangHtml/music/" + musicname);
                        midPlayerIsReading();
                        player.setDataSource(fileDescriptor.getFileDescriptor(), fileDescriptor.getStartOffset(), fileDescriptor.getStartOffset());
                        player.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mp) {
                                mp.start();
                            }
                        });
                        player.prepareAsync();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    //添加完成事件监听器，用于当音乐播放完毕后，重新开始播放音乐
                    player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer arg0) {
                            player.release();
                            player = null;
                        }
                    });
                    return true;
                } else if (url.contains("myschema://go?a=11")) {
                    view.loadUrl("about:blank");
                    view.loadUrl(ContactUrl.WEBVIEW_PATH + ContactUrl.MYWORD_PATH + "uid=" + userId + "&token=" + token + "&jobid=");//参数没有赋值
                    return true;
                } else if (url.contains("myschema://go?a=8")) {
                    return true;
                } else if (url.contains("myschema://go?a=12")) {//检查作业-学生-查看
                    try {
                        courseware_xiti_id.setVisibility(View.VISIBLE);
                        String jobid = url.split("jobid=")[1].split("&")[0];
                        String userName = url.split("userName=")[1];
                        String studentid = url.split("userid=")[1].split("&jobid")[0];
                        title_texts.setText(URLDecoder.decode(userName, "UTF-8"));
                        view.loadUrl(ContactUrl.WEBVIEW_PATH + ContactUrl.WRONGSTUDENT_PATH + "uid=" + userId + "&token=" + token + "&jobid=" + jobid + "&userid=" + studentid + "&userName=" + userName);
                        getWindow().getDecorView().setBackgroundResource(R.mipmap.prepare_lessons_backgroud);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return true;
                } else if (url.contains("myschema://go?a=13")) {//检查作业-老师-查看（错题集锦）
                    courseware_xiti_id.setVisibility(View.VISIBLE);
                    String classInfoId = url.split("classInfoId=")[1].split("&")[0];
                    String jobid = url.split("jobid=")[1];
                    title_texts.setText("错题集锦");
                    view.loadUrl(ContactUrl.WEBVIEW_PATH + ContactUrl.WRONGGATHER_PATH + "uid=" + userId + "&token=" + token + "&classInfoId=" + classInfoId + "&jobid=" + jobid);
                    getWindow().getDecorView().setBackgroundResource(R.mipmap.prepare_lessons_backgroud);
                    return true;
                } else if (url.contains("myschema://go?a=14")) {//关闭loading
                    dismissProgressDialog();
                    return true;
                } else if (url.contains("myschema://go?a=15")) {//语音播报
                    try {
                        String baiDuToken = SPUtil.getString("baiDuToken", "");
                        String title = url.split("title=")[1];
                        if (!TextUtils.isEmpty(baiDuToken) && !TextUtils.isEmpty(title)) {
                            String urls = "http://tsn.baidu.com/text2audio?lan=zh&ctp=1&cuid=abcdxxx&tok=" + baiDuToken + "&tex=" + title + "&vol=9&per=0&spd=5&pit=5&aue=3";
                            midPlayerIsReading();
                            player.setDataSource(JsWebViewActivity.this, Uri.parse(urls));
                            player.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                                @Override
                                public void onPrepared(MediaPlayer mp) {
                                    mp.start();
                                }
                            });
                            player.prepareAsync();
                        } else {
                            ToastUtils.show("语音模块初始化失败!");
                        }
                    } catch (Exception e) {
                        ToastUtils.show("语音模块初始化失败!");
                        e.printStackTrace();
                    }
                    return true;
                } else if (url.contains("myschema://go?a=16")) {
                    try {
                        String musicname = url.split("16&music=")[1] + ".mp3";
                        AssetManager assetManager = getAssets();
                        AssetFileDescriptor fileDescriptor = assetManager.openFd("gobangHtml/music/" + musicname);
                        midPlayerIsReading();
                        player.setDataSource(fileDescriptor.getFileDescriptor(), fileDescriptor.getStartOffset(), fileDescriptor.getStartOffset());
                        player.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mp) {
                                mp.start();
                            }
                        });
                        player.prepareAsync();
                        //添加完成事件监听器，用于当音乐播放完毕后，重新开始播放音乐
                        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer arg0) {
                                player.release();
                                player = null;
                            }
                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return true;
                } else if (url.contains("myschema://go?a=17")) {
                    try {
                        String pageNum = url.split("pageNum=")[1];
                        String title = title_texts.getText().toString();
                        String titleString = null;
                        if (title.contains("作业")) {
                            if ("历史作业".equals(myWordTitle)) {
                                if (total == 0) {
                                    titleString = "历史作业";
                                } else {
                                    titleString = "历史作业(" + pageNum + "/" + total + ")";
                                }
                            } else {
                                if (total == 0) {
                                    titleString = "作业";
                                } else {
                                    titleString = "作业(" + pageNum + "/" + total + ")";
                                }
                            }
                        } else if (title.contains("错题本")) {
                            if (total == 0) {
                                titleString = "错题本";
                            } else {
                                titleString = "错题本(" + pageNum + "/" + total + ")";
                            }
                        } else if (title.contains("错题集锦")) {
                            if (total == 0) {
                                titleString = "错题集锦";
                            } else {
                                titleString = "错题集锦(" + pageNum + "/" + total + ")";
                            }
                        }
                        title_texts.setText(Util.signString(titleString));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return true;
                }
                return false;
            }

            @Override
            public void onReceivedError(WebView var1, int var2, String var3, String var4) {
                dismissProgressDialog();
                ToastUtils.show("页面加载失败");
                LogUtil.e(var2+":"+var3+":"+var4);
            }
        });

        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100) {
                    LogUtil.e("加载完成");
                    if (ContactUrl.SPECIALIZED_TRAINING.equals(prefix1) || ContactUrl.GAME.equals(prefix1)) {
                        dismissProgressDialog();
                    }
                }
            }
        });
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.title_backs:
                finish();
                break;
            case R.id.title_quedingb_btn:
                Intent mIntent = new Intent(JsWebViewActivity.this, HistoryTaskActivity.class);
                mIntent.putExtra("uid", userId);
                mIntent.putExtra("token", token);
                startActivity(mIntent);
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (mWebView != null && mWebView.canGoBack()) {
                mWebView.goBack();
                return true;
            }
            return super.onKeyDown(keyCode, event);
        }
        return super.onKeyDown(keyCode, event);
    }

    //播放器是否准备就绪
    private void midPlayerIsReading() {
        if (player == null) {
            player = new MediaPlayer();
        }
        if (player.isPlaying()) {
            player.stop();
            player.release();
            player = null;
            player = new MediaPlayer();
        }
        player.reset();//每次使用MediaPlarer都是要先初始化和释放资源的，也就是要reset()的，因为java里面的mediaplayer对象的状态和native的对象状态发生了不一致。
    }

    @Override
    protected void onDestroy() {
        try {
            if (player != null) {
                if (player.isPlaying()) {
                    player.stop();//停止音频的播放
                }
                player.release();//释放资源
            }
            dismissProgressDialog();
            super.onDestroy();
            if (mWebView != null) mWebView.destroy();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

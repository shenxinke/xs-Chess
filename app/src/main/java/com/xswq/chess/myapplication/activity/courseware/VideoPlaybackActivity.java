package com.xswq.chess.myapplication.activity.courseware;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.xswq.chess.myapplication.R;
import com.xswq.chess.myapplication.activity.main.SwitchMainActivity;
import com.xswq.chess.myapplication.base.BaseBean;
import com.xswq.chess.myapplication.bean.IntegralBean;
import com.xswq.chess.myapplication.bean.StatisticsBean;
import com.xswq.chess.myapplication.dialog.NetWorkDialog;
import com.xswq.chess.myapplication.phoneiscall.Observer;
import com.xswq.chess.myapplication.phoneiscall.PhoneCallStateObserver;
import com.xswq.chess.myapplication.utils.ContactUrl;
import com.xswq.chess.myapplication.utils.DateUtil;
import com.xswq.chess.myapplication.utils.GsonUtil;
import com.xswq.chess.myapplication.utils.LogUtil;
import com.xswq.chess.myapplication.utils.SPUtil;
import com.xswq.chess.myapplication.utils.ToastUtils;
import com.google.gson.Gson;
import com.netease.neliveplayer.playerkit.sdk.PlayerManager;
import com.netease.neliveplayer.playerkit.sdk.VodPlayer;
import com.netease.neliveplayer.playerkit.sdk.VodPlayerObserver;
import com.netease.neliveplayer.playerkit.sdk.constant.CauseCode;
import com.netease.neliveplayer.playerkit.sdk.model.MediaInfo;
import com.netease.neliveplayer.playerkit.sdk.model.StateInfo;
import com.netease.neliveplayer.playerkit.sdk.model.VideoBufferStrategy;
import com.netease.neliveplayer.playerkit.sdk.model.VideoOptions;
import com.netease.neliveplayer.playerkit.sdk.model.VideoScaleMode;
import com.netease.neliveplayer.playerkit.sdk.view.AdvanceTextureView;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * 视频播放页面
 */
public class VideoPlaybackActivity extends AppCompatActivity {

    public final static String TAG = VideoPlaybackActivity.class.getSimpleName();
    private static final int SHOW_PROGRESS = 0;

    private String videoUril;
    private String token;
    private String userId;
    private String couse;
    private String videoID;
    private int userType;
    private int isJumpVideoHead;
    private boolean isFirstLookVideo = true;
    private boolean mPaused = false;
    private boolean mIsFullScreen = false;
    private boolean mIsShowView = true;
    private ImageView integral;
    private LinearLayout mplayBottom;
    private View mBuffer; //用于指示缓冲状态
    private RelativeLayout mPlayToolbar;
    private ImageView mPauseButton;
    private ImageView mSetPlayerScaleButton;
    private SeekBar mProgressBar;
    private TextView mEndTime;
    private TextView mCurrentTime;
    private AdvanceTextureView textureView;
    protected VodPlayer player;
    private String mVideoPath;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            long position;
            if (msg.what == SHOW_PROGRESS) {
                position = setProgress();
                msg = obtainMessage(SHOW_PROGRESS);
                sendMessageDelayed(msg, 1000 - (position % 1000));
            }
        }
    };

    private long setProgress() {
        if (player == null) {
            return 0;
        }
        int position = (int) player.getCurrentPosition();
        if (isJumpVideoHead == 2 && position < 109999) {
            player.seekTo(110000);
        }
        int duration = (int) player.getDuration();

        if (mProgressBar != null) {
            if (duration > 0) {
                com.netease.neliveplayer.playerkit.common.log.LogUtil.i(TAG, "setProgress,position:" + position + "duration:" + duration);
                long pos = 100L * position / duration;
                mProgressBar.setProgress((int) pos);
            }
        }
        if (mEndTime != null && duration > 0) {
            mEndTime.setText(DateUtil.stringForTime(duration));
        } else {
            mEndTime.setText("--:--:--");
        }
        if (mCurrentTime != null) {
            mCurrentTime.setText(DateUtil.stringForTime(position));
        }
        setSomePost(position, duration);
        return position;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.courseware_videoplay_layout);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON); //保持屏幕常亮
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        PhoneCallStateObserver.getInstance().observeLocalPhoneObserver(localPhoneObserver, true);
        userId = SPUtil.getString("uid", "");
        token = SPUtil.getString("token", "");
        userType = SPUtil.getInt("userType", 0);
        Intent intent = getIntent();
        videoUril = intent.getStringExtra("videoUrl");
        videoID = intent.getStringExtra("ID");
        couse = intent.getStringExtra("couse");
        isJumpVideoHead = intent.getIntExtra("isJumpVideoHead", 1);
        init();
    }

    private void init() {
        findViews();
        initPlayer();
        getVideoPlayRecord();
    }

    protected void findViews() {
        integral = findViewById(R.id.integral);
        textureView = findViewById(R.id.live_texture);
        mPlayToolbar = findViewById(R.id.play_toolbar);
        mplayBottom = findViewById(R.id.play_bottom);
        ImageButton mPlayBack = findViewById(R.id.player_exit);//退出播放
        mPlayBack.getBackground().setAlpha(0);
        mBuffer = findViewById(R.id.buffering_prompt);
        mPauseButton = findViewById(R.id.mediacontroller_play_pause); //播放暂停按钮
        mPauseButton.setOnClickListener(mPauseListener);
        mPlayBack.setOnClickListener(mPlayBackOnClickListener); //监听退出播放的事件响应
        mSetPlayerScaleButton = findViewById(R.id.video_player_scale);  //画面显示模式按钮
        mSetPlayerScaleButton.setOnClickListener(mSetPlayerScaleListener);
        mProgressBar = findViewById(R.id.mediacontroller_seekbar);  //进度条
        mProgressBar.setOnSeekBarChangeListener(mProgressSeekListener);
        mEndTime = findViewById(R.id.mediacontroller_time_total); //总时长
        mEndTime.setText("--:--:--");
        mCurrentTime = findViewById(R.id.mediacontroller_time_current); //当前播放位置
        mCurrentTime.setText("--:--:--");
        mHandler.sendEmptyMessage(SHOW_PROGRESS);
        FrameLayout frameLayout = findViewById(R.id.render_layout);
        frameLayout.setOnClickListener(frameListener);

        mVideoPath = ContactUrl.VIDEO_PATH + videoUril;
        LogUtil.e(mVideoPath);
    }

    private void initPlayer() {
        VideoOptions options = new VideoOptions();
        options.hardwareDecode = false;
        options.isPlayLongTimeBackground = false;
        options.bufferStrategy = VideoBufferStrategy.ANTI_JITTER;
        player = PlayerManager.buildVodPlayer(this, mVideoPath, options);
        player.registerPlayerObserver(playerObserver, true);
        player.setupRenderView(textureView, VideoScaleMode.FIT);
        show();

        boolean remember = SPUtil.getBoolean("netWork", true);
        if (remember) {
            getNetWorkType();
        } else {
            start();
        }
    }

    private void start() {
        if (player == null) {
            return;
        }
//        com.netease.neliveplayer.playerkit.common.log.LogUtil.i(TAG, "startPlayer");
        player.start();
        mPauseButton.setImageResource(R.drawable.nemediacontroller_play);
        mPaused = false;
    }

    private void pause() {
        if (player == null) {
            return;
        }
//        com.netease.neliveplayer.playerkit.common.log.LogUtil.i(TAG, "pausePlayer");
        mPauseButton.setImageResource(R.drawable.nemediacontroller_pause);
        player.pause();
        mPaused = true;
    }

    private void releasePlayer() {
        if (player == null) {
            return;
        }
//        com.netease.neliveplayer.playerkit.common.log.LogUtil.i(TAG, "releasePlayer");
        player.registerPlayerObserver(playerObserver, false);
        PhoneCallStateObserver.getInstance().observeLocalPhoneObserver(localPhoneObserver, false);
        player.setupRenderView(null, VideoScaleMode.NONE);
        textureView.releaseSurface();
        textureView = null;
        player.stop();
        player = null;
        mHandler.removeCallbacksAndMessages(null);

    }

    //满五分钟弹积分图标
    private void getIntegral() {
        try {
            OkHttpUtils.post().url(ContactUrl.GET_VIDEO_INTEGRAL)
                    .addParams("token", token)
                    .addParams("uid", userId)
                    .addParams("userId", userId)
                    .addParams("lookTime", "5")
                    .build().execute(new StringCallback() {
                @Override
                public void onError(Call call, Exception e, int id) {

                }

                @Override
                public void onResponse(String response, int id) {
                    Gson gson = new Gson();
                    IntegralBean integralBean = gson.fromJson(response, IntegralBean.class);
                    String returnCode = integralBean.getError().getReturnCode();
                    if (returnCode.equals("0")) {
                        integral.setVisibility(View.VISIBLE);
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                integral.setVisibility(View.GONE);
                            }
                        }, 3000);//3秒后执行Runnable中的run方法
                    } else if (returnCode.equals("10048")) {
                        ToastUtils.show(integralBean.getError().getReturnMessage());
                        Intent quiteIntent = new Intent(VideoPlaybackActivity.this, SwitchMainActivity.class);
                        quiteIntent.putExtra("exist", true);
                        startActivity(quiteIntent);
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //获取当前网络状态
    private void getNetWorkType() {
        /** 获得系统级联网管理员对象 */
        ConnectivityManager manager = (ConnectivityManager) this
                .getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
        if (info == null) { // 无网情况下
            ToastUtils.show("当前无网络");
        } else { // 有网情况下
            if (info.isAvailable()) { // 网络可用时
                /** 是手机自带的联网方式 */
                if (info.getType() == ConnectivityManager.TYPE_MOBILE) {
                    if (info.getState() == NetworkInfo.State.CONNECTED) {
                        NetWorkDialog dialog = new NetWorkDialog(this, "当前为非Wifi环境，已为您暂停播放") {
                            @Override
                            public void onSureClick() {
                                dialog.dismiss();
                                start();
                            }

                            @Override
                            public void onFalseClick() {
                                dialog.dismiss();
                                pause();
                            }
                        };
                        dialog.showDialog();
                    }
                } else {
                    start();
                }
            } else {
                ToastUtils.show("当前网络不可用");
            }
        }
    }

    //老师完成视频任务
    private void getTeacherCourse() {
        try {
            OkHttpUtils.post().url(ContactUrl.GET_GO_DAY_TEACHER_TASK)
                    .addParams("token", token)
                    .addParams("uid", userId)
                    .addParams("couse", couse)
                    .build().execute(new StringCallback() {
                @Override
                public void onError(Call call, Exception e, int id) {

                }

                @Override
                public void onResponse(String response, int id) {
                    GsonUtil.gsonToBean(response, BaseBean.class, VideoPlaybackActivity.this);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //视频观看记录接口
    private void getVideoPlayRecord() {
        try {
            OkHttpUtils.post().url(ContactUrl.GET_VIDEO_RECORD)
                    .addParams("token", token)
                    .addParams("uid", userId)
                    .addParams("videoId", videoID)
                    .addParams("terminalType", "3")
                    .build().execute(new StringCallback() {
                @Override
                public void onError(Call call, Exception e, int id) {

                }

                @Override
                public void onResponse(String response, int id) {
                    if (!TextUtils.isEmpty(response)) {
                        Gson gson = new Gson();
                        StatisticsBean statisticsBean = gson.fromJson(response, StatisticsBean.class);
                        if (statisticsBean != null) {
                            StatisticsBean.ErrorBean error = statisticsBean.getError();
                            if ("0".equals(error)) {
                                LogUtil.e(videoID + "StatisticsResponse :" + response);
                            } else if ("10048".equals(error)) {
                                Toast.makeText(VideoPlaybackActivity.this, error.getReturnMessage(), Toast.LENGTH_LONG).show();
                                Intent quiteIntent = new Intent(VideoPlaybackActivity.this, SwitchMainActivity.class);
                                quiteIntent.putExtra("exist", true);
                                startActivity(quiteIntent);
                            }
                        }
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //走请求接口
    private void setSomePost(int position, int duration) {
        try {
            if (position > 0) {
                long i = position / 1000;
                if (i >= 300 && isFirstLookVideo) {
                    isFirstLookVideo = false;
                    getIntegral();
                }
            }
            if (position > 0 && duration > 0) {
                long i = duration / 3 * 2;
                if (position >= i && !TextUtils.isEmpty(couse)) {
                    getTeacherCourse();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private VodPlayerObserver playerObserver = new VodPlayerObserver() {

        @Override
        public void onCurrentPlayProgress(long currentPosition, long duration, float percent, long cachedPosition) {
        }

        @Override
        public void onSeekCompleted() {
            com.netease.neliveplayer.playerkit.common.log.LogUtil.i(TAG, "onSeekCompleted");
            mHandler.sendEmptyMessageDelayed(SHOW_PROGRESS, 1000);
        }

        @Override
        public void onCompletion() {
            //视频播放完成的监听
            pause();
            if (mIsShowView) {
                show();
            }
        }

        @Override
        public void onAudioVideoUnsync() {
        }

        @Override
        public void onNetStateBad() {
        }

        @Override
        public void onDecryption(int ret) {
        }

        @Override
        public void onPreparing() {
        }

        @Override
        public void onPrepared(MediaInfo info) {
        }

        @Override
        public void onError(int code, int extra) {
            mBuffer.setVisibility(View.INVISIBLE);
            mPauseButton.setImageResource(R.drawable.nemediacontroller_pause);
            player.pause();
            mPaused = true;
            if (code == CauseCode.CODE_VIDEO_PARSER_ERROR) {
                ToastUtils.show("视频解析出错，请检查网络!");
            } else {
                ToastUtils.show("网络连接失败，请检查网络!");
            }
        }

        @Override
        public void onFirstVideoRendered() {
        }

        @Override
        public void onFirstAudioRendered() {

        }

        @Override
        public void onBufferingStart() {
            mBuffer.setVisibility(View.VISIBLE);
        }

        @Override
        public void onBufferingEnd() {
            mBuffer.setVisibility(View.GONE);
        }

        @Override
        public void onBuffering(int percent) {
//            com.netease.neliveplayer.playerkit.common.log.LogUtil.d(TAG, "缓冲中..." + percent + "%");
            mProgressBar.setSecondaryProgress(percent);
        }

        @Override
        public void onVideoDecoderOpen(int value) {
//            showToast("使用解码类型：" + (value == 1 ? "硬件解码" : "软解解码"));
        }

        @Override
        public void onStateChanged(StateInfo stateInfo) {
        }

        @Override
        public void onHttpResponseInfo(int code, String header) {
            Log.i(TAG, "onHttpResponseInfo,code:" + code + " header:" + header);
        }
    };


    private View.OnClickListener mPlayBackOnClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View view) {
            Log.i(TAG, "player_exit");
            if (mIsFullScreen) {
                mSetPlayerScaleButton.setImageResource(R.drawable.nemediacontroller_scale01);
                mIsFullScreen = false;
                player.setupRenderView(textureView, VideoScaleMode.FIT);
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//竖屏设置
            } else {
                finish();
                releasePlayer();
            }
        }
    };

    private View.OnClickListener frameListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (mIsShowView) {
                show();
            } else {
                hint();
            }
        }
    };

    private View.OnClickListener mPauseListener = new View.OnClickListener() {
        public void onClick(View v) {
            if (player != null && player.isPlaying()) {
                pause();
            } else {
                start();
            }
        }
    };

    private View.OnClickListener mSetPlayerScaleListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            player.setupRenderView(null, VideoScaleMode.NONE);
            if (mIsFullScreen) {
                mSetPlayerScaleButton.setImageResource(R.drawable.nemediacontroller_scale01);
                mIsFullScreen = false;
                player.setupRenderView(textureView, VideoScaleMode.FIT);
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//竖屏设置
            } else {
                mSetPlayerScaleButton.setImageResource(R.drawable.nemediacontroller_scale02);
                mIsFullScreen = true;
                player.setupRenderView(textureView, VideoScaleMode.FULL);
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);//横屏设置
            }
        }
    };

    private SeekBar.OnSeekBarChangeListener mProgressSeekListener = new SeekBar.OnSeekBarChangeListener() {

        @Override
        public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
            mHandler.removeMessages(SHOW_PROGRESS);

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            player.seekTo(player.getDuration() * seekBar.getProgress() / 100);
        }
    };

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart");
        if (player != null && mPaused) {
            start();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume");
        if (player != null && !mPaused) {
            player.onActivityResume(false);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause");
        if (player != null && player.isPlaying()) {
            pause();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop");
        if (player != null) {
            player.onActivityStop(false);
        }
    }

    @Override
    public void onBackPressed() {
        Log.i(TAG, "onBackPressed");
        finish();
        releasePlayer();
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy");
        releasePlayer();
    }

    //处理与电话逻辑
    private Observer<Integer> localPhoneObserver = new Observer<Integer>() {

        @Override
        public void onEvent(Integer phoneState) {
            if (phoneState == TelephonyManager.CALL_STATE_IDLE) {
                player.start();
            } else if (phoneState == TelephonyManager.CALL_STATE_RINGING) {
                player.stop();
            } else {
                Log.i(TAG, "localPhoneObserver onEvent " + phoneState);
            }

        }
    };

    private void show() {
        mPlayToolbar.setVisibility(View.VISIBLE);
        mplayBottom.setVisibility(View.VISIBLE);
        mIsShowView = false;
    }

    private void hint() {
        mPlayToolbar.setVisibility(View.GONE);
        mplayBottom.setVisibility(View.GONE);
        mIsShowView = true;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if (mIsFullScreen) {
                mSetPlayerScaleButton.setImageResource(R.drawable.nemediacontroller_scale01);
                mIsFullScreen = false;
                player.setupRenderView(textureView, VideoScaleMode.FIT);
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//竖屏设置
            } else {
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}

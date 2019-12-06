package com.xswq.chess.myapplication.activity.weiqistory;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.xswq.chess.myapplication.R;
import com.xswq.chess.myapplication.base.BaseCompatActivity;
import com.xswq.chess.myapplication.bean.WeiQiStoryListBean;
import com.xswq.chess.myapplication.customview.WeiQiStoryImageView;
import com.xswq.chess.myapplication.utils.DateUtil;
import com.xswq.chess.myapplication.utils.ToastUtils;

import java.io.Serializable;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 围棋故事播放页面
 */
public class WeiqiStoryActivity extends BaseCompatActivity implements View.OnClickListener {
    private TextView login_titles;
    private String storySound;
    private MediaPlayer mediaPlayer;//媒体播放器
    private boolean isSeekBarChanging;//互斥变量，防止进度条与定时器冲突。
    private int currentPosition = 0;//当前音乐播放的进度
    private SeekBar seekBar;
    private Timer timer;
    private ImageView weiQiIamgeSuspend;
    private ImageView weiQiIamgePlay;
    private TextView startTime;
    private TextView endTime;
    private WeiQiStoryImageView storyImage;
    private ImageView storyRebroadcast;
    private String[] storyImgUrl;
    private String[] changeTimer;
    private int inSubscript = 0;
    private List<WeiQiStoryListBean.DataBean.ListBean> list;
    private int position;
    public static WeiqiStoryActivity instance = null;
    private long soundTime;


    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        // 该方法运行在主线程中
        // 接收到handler发送的消息，对UI进行操作
        @Override
        public void handleMessage(Message msg) {
            try {
                if (msg.what == 1) {
                    long currentPositions = (long) msg.getData().getInt("currentPosition");//接受msg传递过来进度条的进度
                    String dateToString = DateUtil.getDateToString(currentPositions, "mm:ss");
                    startTime.setText(dateToString);
                    long changeTimes = (currentPositions / 1000);
                    long changeTimes2 = Long.parseLong(changeTimer[inSubscript]);
                    if (changeTimes >= changeTimes2) {
                        Glide.with(WeiqiStoryActivity.this)
                                .load(storyImgUrl[inSubscript])
                                .asBitmap()
                                .error(R.mipmap.default_xswq)
                                .placeholder(R.mipmap.default_xswq)
                                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                                .into(storyImage);
                        if (inSubscript < storyImgUrl.length - 1) {
                            inSubscript++;
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };


    @Override
    protected int getLayoutId() {
        return R.layout.activity_wei_qi_story_layout;
    }

    @Override
    protected void initView() {
        instance = this;
        init();
        play();
    }

    private void init() {
        getWindow().getDecorView().setBackgroundResource(R.mipmap.wei_qi_story_list_bg);
        Intent intent = getIntent();
        position = intent.getIntExtra("Position", 0);
        String itemStoryID = intent.getStringExtra("itemStoryID");
        int intentType = intent.getIntExtra("intentType", 0);
        list = (List<WeiQiStoryListBean.DataBean.ListBean>) getIntent().getSerializableExtra("List");
        login_titles = findViewById(R.id.login_titles);
        weiQiIamgeSuspend = findViewById(R.id.wei_qi_iamge_suspend);
        weiQiIamgeSuspend.setOnClickListener(this);
        weiQiIamgePlay = findViewById(R.id.wei_qi_iamge_play);
        weiQiIamgePlay.setOnClickListener(this);
        TextView login_back = findViewById(R.id.login_back);
        login_back.setVisibility(View.VISIBLE);
        login_back.setOnClickListener(this);
        startTime = findViewById(R.id.start_time);
        storyImage = findViewById(R.id.story_image);
        storyRebroadcast = findViewById(R.id.story_rebroadcast);
        storyRebroadcast.setOnClickListener(this);
        endTime = findViewById(R.id.end_time);
        ImageView imageLast = findViewById(R.id.wei_qi_image_last);
        imageLast.setOnClickListener(this);
        ImageView imageNext = findViewById(R.id.wei_qi_iamge_next);
        imageNext.setOnClickListener(this);
        ImageView image_List = findViewById(R.id.wei_qi_image_list);
        image_List.setOnClickListener(this);
        //实例化媒体播放器
        mediaPlayer = new MediaPlayer();
        seekBar = findViewById(R.id.story_seekBar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            /*滚动时,应当暂停后台定时器*/
            public void onStartTrackingTouch(SeekBar seekBar) {
                isSeekBarChanging = true;
            }

            /*滑动结束后，重新设置值*/
            public void onStopTrackingTouch(SeekBar seekBar) {
                isSeekBarChanging = false;
                mediaPlayer.seekTo(seekBar.getProgress());
            }
        });
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                onOver();
            }
        });

        if (intentType == 1) {
            for (int i = 0; i < list.size(); i++) {
                if (itemStoryID.equals(list.get(i).getID())) {
                    getData(i);
                }
            }
        } else {
            getData(position);
        }
    }

    @Override
    protected void loadData() {

    }

    /*播放处理*/
    private void play() {
        if (!TextUtils.isEmpty(storySound)) {
            try {
                seekBar.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        return false;
                    }
                });
                mediaPlayer.reset();
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);//设置音频类型
                mediaPlayer.setDataSource(storySound);//设置mp3数据源
                mediaPlayer.prepareAsync();//数据缓冲
                /*监听缓存 事件，在缓冲完毕后，开始播放*/
                mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    public void onPrepared(MediaPlayer mp) {
                        seekBar.setProgress(currentPosition);
                        int duration = mediaPlayer.getDuration();
                        soundTime = (long) duration;
                        String endToString = DateUtil.getDateToString(soundTime, "mm:ss");
                        endTime.setText(endToString);
                        mp.start();
                        mp.seekTo(currentPosition);
                        seekBar.setMax(mediaPlayer.getDuration());
                    }
                });
                //监听播放时回调函数
                timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        try {
                            if (!isSeekBarChanging && seekBar != null) {
                                if (mediaPlayer == null) {
                                    seekBar.setProgress(0);
                                    currentPosition = 0;
                                } else {
                                    if (!mediaPlayer.isPlaying()) {//如果不在播放状态，则停止更新//播放器进度条，防止界面报错
                                        return;
                                    }
                                    int currentPosition = mediaPlayer.getCurrentPosition();
                                    seekBar.setProgress(currentPosition);
                                    Message msg = new Message();
                                    msg.what = 1;
                                    Bundle bundle = new Bundle();
                                    bundle.putInt("currentPosition", currentPosition);//往Bundle中存放数据
                                    msg.setData(bundle);//mes利用Bundle传递数据
                                    handler.sendMessage(msg);//用activity中的handler发送消息
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, 0, 1000);
            } catch (Exception e) {
                mediaPlayer.reset();
                Toast.makeText(WeiqiStoryActivity.this, "播放失败", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.story_rebroadcast:
                onBroadcast();
                break;
            case R.id.wei_qi_iamge_suspend:
                onStarts();
                break;
            case R.id.wei_qi_iamge_play:
                onPauses();
                break;
            case R.id.login_back:
                finish();
                break;
            case R.id.wei_qi_image_last:
                onLast();
                break;
            case R.id.wei_qi_iamge_next:
                onNext();
                break;
            case R.id.wei_qi_image_list:
                Intent intent = new Intent(WeiqiStoryActivity.this, WeiqiStoryNameListActivity.class);
                intent.putExtra("List", (Serializable) list);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    private void getData(int position) {
        String storyName = list.get(position).getStoryName();
        login_titles.setText(storyName);
        String changeTime = list.get(position).getChangeTime();
        String storyImg = list.get(position).getStoryImg();
        storySound = list.get(position).getStorySound();

        storyImgUrl = new String[]{};
        if (!TextUtils.isEmpty(storyImg)) {
            storyImgUrl = storyImg.split(";");
        }
        if (!TextUtils.isEmpty(changeTime)) {
            changeTimer = changeTime.split(";");
        }

        inSubscript = 0;
        Glide.with(WeiqiStoryActivity.this)
                .load(storyImgUrl[inSubscript])
                .asBitmap()
                .error(R.mipmap.default_xswq)
                .placeholder(R.mipmap.default_xswq)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(storyImage);
    }

    //下一首
    private void onNext() {
        if (position < list.size() - 1) {
            position++;
            currentPosition = 0;
            seekBar.setProgress(0);
            startTime.setText("00:00");
            isSeekBarChanging = false;
            weiQiIamgePlay.setVisibility(View.VISIBLE);
            weiQiIamgeSuspend.setVisibility(View.GONE);
            storyRebroadcast.setVisibility(View.GONE);
            storyImage.setVisibility(View.VISIBLE);
            getData(position);
            play();
        } else {
            ToastUtils.show("最后一首");
        }
    }

    //上一首
    private void onLast() {
        if (position > 0) {
            position--;
            currentPosition = 0;
            seekBar.setProgress(0);
            startTime.setText("00:00");
            isSeekBarChanging = false;
            weiQiIamgePlay.setVisibility(View.VISIBLE);
            weiQiIamgeSuspend.setVisibility(View.GONE);
            storyRebroadcast.setVisibility(View.GONE);
            storyImage.setVisibility(View.VISIBLE);
            getData(position);
            play();
        } else {
            ToastUtils.show("第一首");
        }
    }

    //暂停
    private void onPauses() {
        seekBar.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
        currentPosition = mediaPlayer.getCurrentPosition();//记录播放的位置
        mediaPlayer.stop();//暂停状态
        timer.purge();//移除所有任务;
        isSeekBarChanging = true;
        weiQiIamgePlay.setVisibility(View.INVISIBLE);
        weiQiIamgeSuspend.setVisibility(View.VISIBLE);
    }

    //重播
    private void onBroadcast() {
        seekBar.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });
        storyRebroadcast.setVisibility(View.GONE);
        storyImage.setVisibility(View.VISIBLE);
        isSeekBarChanging = false;
        weiQiIamgePlay.setVisibility(View.VISIBLE);
        weiQiIamgeSuspend.setVisibility(View.INVISIBLE);
        play();
    }

    //播放
    private void onStarts() {
        seekBar.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });
        storyRebroadcast.setVisibility(View.GONE);
        storyImage.setVisibility(View.VISIBLE);
        isSeekBarChanging = false;
        weiQiIamgePlay.setVisibility(View.VISIBLE);
        weiQiIamgeSuspend.setVisibility(View.INVISIBLE);
        play();
    }

    //结束
    private void onOver() {
        currentPosition = 0;
        seekBar.setProgress(0);
        startTime.setText("00:00");
        isSeekBarChanging = true;
        storyImage.setVisibility(View.GONE);
        weiQiIamgePlay.setVisibility(View.INVISIBLE);
        weiQiIamgeSuspend.setVisibility(View.VISIBLE);
        storyRebroadcast.setVisibility(View.VISIBLE);
        seekBar.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
    }

    @Override
    protected void onDestroy() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
        super.onDestroy();
    }
}

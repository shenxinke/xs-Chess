package com.xswq.chess.myapplication.activity.onlinegame.inform;

import android.content.Intent;
import android.os.CountDownTimer;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xswq.chess.myapplication.R;
import com.xswq.chess.myapplication.application.XSWQApplication;
import com.xswq.chess.myapplication.base.BaseCompatActivity;
import com.xswq.chess.myapplication.bean.ScoketMessageBean;
import com.xswq.chess.myapplication.config.RxCode;
import com.xswq.chess.myapplication.utils.ContactUrl;
import com.xswq.chess.myapplication.utils.DateUtil;
import com.xswq.chess.myapplication.utils.LogUtil;
import com.xswq.chess.myapplication.utils.Util;
import com.google.gson.Gson;

import gorden.rxbus2.RxBus;
import gorden.rxbus2.ThreadMode;

/**
 * 比赛提前进入等待页面
 */
public class MatchCountdownActivity extends BaseCompatActivity {
    private CountDownTimer mTimer;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_match_countdown_layout;
    }

    @Override
    protected void initView() {
        RxBus.get().register(this);
        getWindow().getDecorView().setBackgroundResource(R.mipmap.chess_bg);
        Intent intent = getIntent();
        String message = intent.getStringExtra("message");
        Gson gson = new Gson();
        ScoketMessageBean scoketMessageBean = gson.fromJson(message, ScoketMessageBean.class);
        findViewById(R.id.login_back).setVisibility(View.VISIBLE);
        TextView title = findViewById(R.id.login_titles);
        title.setVisibility(View.VISIBLE);
        title.setText("比赛");
        ImageView whitehead = findViewById(R.id.head_image_left);
        ImageView whitehead2 = findViewById(R.id.head_image_left2);
        ImageView blackImage = findViewById(R.id.head_image_right);
        ImageView blackImage2 = findViewById(R.id.head_image_right2);

        TextView whiteName = findViewById(R.id.text_name_left);
        TextView whiteName2 = findViewById(R.id.text_name_left2);
        TextView blackName = findViewById(R.id.text_name_right);
        TextView blackName2 = findViewById(R.id.text_name_right2);
        final TextView textCountDown = findViewById(R.id.text_count_down);
        findViewById(R.id.button_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        String whiteHeading = ContactUrl.BASE_PATH + "/" + scoketMessageBean.getWhiteUserHead();
        String blackHeading = ContactUrl.BASE_PATH + "/" + scoketMessageBean.getBlackUserHead();
        String whiteUserName = scoketMessageBean.getWhiteUserName();
        String blackUserName = scoketMessageBean.getBlackUserName();

        Glide.with(XSWQApplication.getmContext()).load(whiteHeading).dontAnimate().into(whitehead);
        Glide.with(XSWQApplication.getmContext()).load(whiteHeading).dontAnimate().into(whitehead2);
        Glide.with(XSWQApplication.getmContext()).load(blackHeading).dontAnimate().into(blackImage);
        Glide.with(XSWQApplication.getmContext()).load(blackHeading).dontAnimate().into(blackImage2);
        whiteName.setText(Util.signString(whiteUserName));
        whiteName2.setText(Util.signString(whiteUserName));
        blackName.setText(Util.signString(blackUserName));
        blackName2.setText(Util.signString(blackUserName));

        long roundsStartTime = scoketMessageBean.getRoundsStartTime();
        long nowTime = System.currentTimeMillis();
        long distanceStartTime = roundsStartTime - nowTime;
        LogUtil.e("开始时间：" + roundsStartTime + "当前时间：" + nowTime + "剩余开始时间：" + distanceStartTime);
        if (distanceStartTime > 0) {
            if (mTimer == null) {
                mTimer = new CountDownTimer(distanceStartTime, 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        if (!MatchCountdownActivity.this.isFinishing()) {
                            LogUtil.e("剩余开始时间：" + millisUntilFinished);
                            String dateToString = "比赛将于 <font color='#ff0000'>" + DateUtil.getSjcSec(millisUntilFinished) + "</font> 后开始";
                            textCountDown.setText(Html.fromHtml(dateToString));
                        }
                    }

                    @Override
                    public void onFinish() {
                        finish();
                    }
                };
                mTimer.start();
            }
        } else {
            String dateToString = "比赛将于 <font color='#ff0000'>0分0秒</font> 秒后开始";
            textCountDown.setText(Html.fromHtml(dateToString));
        }
    }

    @Override
    protected void loadData() {

    }

    @gorden.rxbus2.Subscribe(code = RxCode.CODE_FINISH_INFORM_MESSAGE, threadMode = ThreadMode.MAIN)
    public void upData() {
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
        }
        RxBus.get().unRegister(this);
    }
}

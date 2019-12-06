package com.xswq.chess.myapplication.utils;


import android.graphics.Color;
import android.os.CountDownTimer;
import android.widget.Button;

public class TimerCountdown extends CountDownTimer {

    private Button mview;

    public TimerCountdown(long millisInFuture, long countDownInterval, Button view) {
        super(millisInFuture, countDownInterval);
        mview = view;
    }

    @Override
    public void onTick(long millisUntilFinished) {
        mview.setBackgroundColor(Color.parseColor("#B6B6D8"));
        mview.setClickable(false);
        mview.setText("(" + millisUntilFinished / 1000 + ") 秒");
    }

    @Override
    public void onFinish() {
        mview.setText("重新获取");
        mview.setClickable(true);
        mview.setBackgroundColor(Color.parseColor("#4EB84A"));

    }


}

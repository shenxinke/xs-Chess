package com.xswq.chess.myapplication.activity.onlinegame.inform;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.xswq.chess.myapplication.bean.ScoketMessageBean;
import com.xswq.chess.myapplication.R;
import com.xswq.chess.myapplication.base.BaseActivity;
import com.xswq.chess.myapplication.config.RxCode;
import com.xswq.chess.myapplication.utils.DateUtil;
import com.google.gson.Gson;

import gorden.rxbus2.RxBus;
import gorden.rxbus2.ThreadMode;

/**
 * 比赛前十分钟
 */
public class TenMinutesActivity extends BaseActivity implements View.OnClickListener {

    private String message;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ten_minutes_layout);
        RxBus.get().register(this);
        getWindow().getDecorView().setBackgroundResource(R.color.half);
        findViewById(R.id.button_close).setOnClickListener(this);
        findViewById(R.id.button_confirm).setOnClickListener(this);
        findViewById(R.id.button_cancel).setOnClickListener(this);
        TextView textMassage = findViewById(R.id.text_massage);
        Intent intent = getIntent();
        message = intent.getStringExtra("message");
        Gson gson = new Gson();
        ScoketMessageBean scoketMessageBean = gson.fromJson(message, ScoketMessageBean.class);
        String matchName = scoketMessageBean.getMatchName();
        long roundsStartTime = scoketMessageBean.getRoundsStartTime();
        String dateToString = DateUtil.getDateToString(roundsStartTime, "HH:mm");
        String massage = "您报名的：" + matchName + "将于" + dateToString + "开始，请准时参加比赛";
        textMassage.setText(massage);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_close:
            case R.id.button_cancel:
                finish();
                break;
            case R.id.button_confirm:
                Intent intent = new Intent(TenMinutesActivity.this, MatchCountdownActivity.class);
                intent.putExtra("message",message);
                startActivity(intent);
                finish();
                break;
            default:
                break;
        }
    }
    @gorden.rxbus2.Subscribe(code = RxCode.CODE_FINISH_INFORM_MESSAGE, threadMode = ThreadMode.MAIN)
    public void upData() {
        finish();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        RxBus.get().unRegister(this);
    }
}

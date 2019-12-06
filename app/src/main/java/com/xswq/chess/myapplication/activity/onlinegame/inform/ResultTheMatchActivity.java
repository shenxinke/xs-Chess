package com.xswq.chess.myapplication.activity.onlinegame.inform;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.xswq.chess.myapplication.R;
import com.xswq.chess.myapplication.base.BaseActivity;
import com.xswq.chess.myapplication.config.RxCode;

import gorden.rxbus2.RxBus;
import gorden.rxbus2.ThreadMode;

/**
 * 老师提交判棋结果两个学生端查看判棋结果
 */
public class ResultTheMatchActivity extends BaseActivity implements View.OnClickListener {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_the_match_layout);
        RxBus.get().register(this);
        getWindow().getDecorView().setBackgroundResource(R.color.half);
        Intent intent = getIntent();
        int result = intent.getIntExtra("result", 0);
        findViewById(R.id.button_close).setOnClickListener(this);
        findViewById(R.id.button_confirm).setOnClickListener(this);
        TextView textMassage = findViewById(R.id.text_massage);
        String message;
        switch (result) {
            case 1:
                message = "黑棋胜";
                break;
            case 2:
                message = "白棋胜";
                break;
            case 3:
                message = "平局";
                break;
            default:
                message = "无效对局";
                break;
        }
        textMassage.setText(message);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_close:
            case R.id.button_confirm:
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

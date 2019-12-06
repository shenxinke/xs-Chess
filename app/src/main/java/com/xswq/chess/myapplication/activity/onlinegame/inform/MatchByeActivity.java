package com.xswq.chess.myapplication.activity.onlinegame.inform;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import com.xswq.chess.myapplication.R;
import com.xswq.chess.myapplication.base.BaseActivity;
import com.xswq.chess.myapplication.config.RxCode;
import gorden.rxbus2.RxBus;
import gorden.rxbus2.ThreadMode;

public class MatchByeActivity extends BaseActivity implements View.OnClickListener {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_bye_layout);
        RxBus.get().register(this);
        getWindow().getDecorView().setBackgroundResource(R.color.half);
        findViewById(R.id.button_close).setOnClickListener(this);
        findViewById(R.id.button_confirm).setOnClickListener(this);
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

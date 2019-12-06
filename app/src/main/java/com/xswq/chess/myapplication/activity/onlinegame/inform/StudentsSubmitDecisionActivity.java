package com.xswq.chess.myapplication.activity.onlinegame.inform;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.xswq.chess.myapplication.R;
import com.xswq.chess.myapplication.activity.competition.teacher.TeacherCompetitionManagementActivity;
import com.xswq.chess.myapplication.base.BaseActivity;
import com.xswq.chess.myapplication.config.RxCode;

import gorden.rxbus2.RxBus;
import gorden.rxbus2.ThreadMode;

/**
 * 学生提交判棋老师端弹框
 */
public class StudentsSubmitDecisionActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_submit_decision_layout);
        RxBus.get().register(this);
        getWindow().getDecorView().setBackgroundResource(R.color.half);
        findViewById(R.id.button_confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StudentsSubmitDecisionActivity.this, TeacherCompetitionManagementActivity.class);
                intent.putExtra("type", 1);
                startActivity(intent);
                finish();
            }
        });
        findViewById(R.id.button_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        findViewById(R.id.button_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
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

package com.xswq.chess.myapplication.activity.onlinegame.inform;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.xswq.chess.myapplication.R;
import com.xswq.chess.myapplication.activity.competition.principal.CompetitionManagementActivity;
import com.xswq.chess.myapplication.base.BaseActivity;
import com.xswq.chess.myapplication.config.RxCode;

import gorden.rxbus2.RxBus;
import gorden.rxbus2.ThreadMode;

public class EntryNoticeActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry_notice_layout);
        RxBus.get().register(this);
        getWindow().getDecorView().setBackgroundResource(R.color.half);
        Intent intent = getIntent();
        final String schoolName = intent.getStringExtra("schoolName");
        String matchName = intent.getStringExtra("matchName");
        final int state = intent.getIntExtra("state", 0);
        final TextView schoolText = findViewById(R.id.school_name);
        final TextView matchText = findViewById(R.id.match_name);
        Button buttonOk = findViewById(R.id.button_ok);
        Button buttonSkip = findViewById(R.id.button_skip);

        String strSchool;
        String strMatch;
        if (state == 1) {
            strSchool = "比赛邀请";
            schoolText.setText(strSchool);
            strMatch = schoolName + "邀请贵方参加比赛";
            matchText.setText(strMatch);
            buttonOk.setText("查看详情");
            schoolText.setVisibility(View.VISIBLE);
            buttonSkip.setVisibility(View.VISIBLE);
        } else if (state == 2) {
            strSchool = schoolName + "已同意";
            schoolText.setText(strSchool);
            strMatch = "“" + matchName + "”" + "的比赛邀请";
            matchText.setText(strMatch);
            schoolText.setVisibility(View.VISIBLE);
        } else if (state == 3) {
            strSchool = schoolName + "已拒绝";
            schoolText.setText(strSchool);
            strMatch = "“" + matchName + "”" + "的比赛邀请";
            matchText.setText(strMatch);
            schoolText.setVisibility(View.VISIBLE);
        } else {
            schoolText.setVisibility(View.GONE);
            String endString = "您报名的：" + matchName + "，已结束";
            matchText.setText(endString);
        }

        buttonSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (state == 1) {
                    startActivity(new Intent(EntryNoticeActivity.this, CompetitionManagementActivity.class));
                    finish();
                } else {
                    finish();
                }
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

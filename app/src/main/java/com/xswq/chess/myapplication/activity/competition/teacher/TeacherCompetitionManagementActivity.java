package com.xswq.chess.myapplication.activity.competition.teacher;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.xswq.chess.myapplication.R;
import com.xswq.chess.myapplication.activity.competition.CreateCompetitionActivity;
import com.xswq.chess.myapplication.base.BaseCompatActivity;
import com.xswq.chess.myapplication.base.BaseFragmentAdapter;
import com.xswq.chess.myapplication.fragment.TeacherCompetitionManagementFragment;
import com.xswq.chess.myapplication.fragment.TeacherJudgeManagementFragment;

import java.util.ArrayList;
import java.util.List;

public class TeacherCompetitionManagementActivity extends BaseCompatActivity implements View.OnClickListener {
    String[] switchTitles = new String[]{"比赛管理", "判棋管理"};

    @Override
    protected int getLayoutId() {
        return R.layout.activity_teacher_competition_management_layout;
    }

    @Override
    protected void initView() {
        getWindow().getDecorView().setBackgroundResource(R.mipmap.wei_qi_story_list_bg);
        TextView loginTitles = findViewById(R.id.login_titles);
        loginTitles.setText("比赛管理");
        TextView loginBack = findViewById(R.id.login_back);
        loginBack.setVisibility(View.VISIBLE);
        loginBack.setOnClickListener(this);
        TextView loginQueDing = findViewById(R.id.login_queding);
        loginQueDing.setVisibility(View.VISIBLE);
        loginQueDing.setText("创建比赛");
        loginQueDing.setOnClickListener(this);
    }

    @Override
    protected void loadData() {
        List<Fragment> listFragment = new ArrayList<>();
        listFragment.add(new TeacherCompetitionManagementFragment());
        listFragment.add(new TeacherJudgeManagementFragment());
        BaseFragmentAdapter baseFragmentAdapter = new BaseFragmentAdapter(getSupportFragmentManager(), switchTitles, listFragment);
        TabLayout tabLayout = findViewById(R.id.tab_layout);
        ViewPager viewPage = findViewById(R.id.view_page);
        viewPage.setAdapter(baseFragmentAdapter);
        tabLayout.setupWithViewPager(viewPage);
        Intent intent = getIntent();
        int type = intent.getIntExtra("type", 0);
        if (type == 1) {
            viewPage.setCurrentItem(1);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_back:
                finish();
                break;
            case R.id.login_queding:
                Intent intent = new Intent(TeacherCompetitionManagementActivity.this, CreateCompetitionActivity.class);
                intent.putExtra("teacherType", true);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}

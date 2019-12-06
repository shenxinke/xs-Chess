package com.xswq.chess.myapplication.activity.competition;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.xswq.chess.myapplication.R;
import com.xswq.chess.myapplication.base.BaseCompatActivity;
import com.xswq.chess.myapplication.bean.StudentPlayByPlayBean;
import com.xswq.chess.myapplication.fragment.AgainstTheTableFragment;
import com.xswq.chess.myapplication.fragment.GradeTableFragment;
import com.xswq.chess.myapplication.utils.SPUtil;
import com.xswq.chess.myapplication.utils.Util;

/**
 * 成绩表 对阵表
 */
public class ResultOfTheMatchActivity extends BaseCompatActivity implements View.OnClickListener {
    private Button gradeTable;
    private Button againstTheTable;
    private FragmentManager fragmentManager;
    private GradeTableFragment gradeTableFragment;
    private AgainstTheTableFragment againstTheTableFragment;
    private StudentPlayByPlayBean.DataBean listBean;
    private int nowType = 1;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_result_match_layout;
    }

    @Override
    protected void initView() {
        getWindow().getDecorView().setBackgroundResource(R.mipmap.wei_qi_story_list_bg);
        Intent intent = getIntent();
        listBean = (StudentPlayByPlayBean.DataBean) intent.getSerializableExtra("competitionManagementBean");
        String gradeData = SPUtil.getString("gradeData", "");
        String againstData = SPUtil.getString("againstData", "");
        if (!TextUtils.isEmpty(gradeData)) {
            SPUtil.remove("gradeData");
        }
        if (!TextUtils.isEmpty(againstData)) {
            SPUtil.remove("againstData");
        }
        ImageView userHelp = findViewById(R.id.user_help);
        userHelp.setBackgroundResource(R.mipmap.full_screen);
        userHelp.setVisibility(View.VISIBLE);
        userHelp.setOnClickListener(ResultOfTheMatchActivity.this);
        TextView loginBack = findViewById(R.id.login_back);
        loginBack.setVisibility(View.VISIBLE);
        loginBack.setOnClickListener(ResultOfTheMatchActivity.this);
        TextView login_titles = findViewById(R.id.login_titles);
        login_titles.setText("比赛结果");
        gradeTable = findViewById(R.id.grade_table);
        againstTheTable = findViewById(R.id.against_the_table);

        fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        gradeTableFragment = GradeTableFragment.getInstances(listBean);
        fragmentTransaction.add(R.id.frame_bg, gradeTableFragment).commit();
    }

    @Override
    protected void loadData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_back:
                finish();
                break;
            case R.id.user_help:
                if (Util.isClickable()) {
                    return;
                }
                if (nowType == 1) {
                    startActivity(new Intent(this, GradeTableActivity.class));
                } else {
                    startActivity(new Intent(this, AgainstTheTableActivity.class));
                }
                break;
            default:
                break;
        }
    }

    private void hidefragment() {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        //如果Fragment不为空并且已经添加,就隐藏
        if (gradeTableFragment != null && gradeTableFragment.isAdded()) {
            fragmentTransaction.hide(gradeTableFragment);
        }
        if (againstTheTableFragment != null && againstTheTableFragment.isAdded()) {
            fragmentTransaction.hide(againstTheTableFragment);
        }
        fragmentTransaction.commit();
    }

    public void buttonOnClick(View view) {
        hidefragment();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        switch (view.getId()) {
            case R.id.grade_table:
                nowType = 1;
                gradeTable.setBackgroundResource(R.drawable.tab_friend_select);
                gradeTable.setTextColor(getResources().getColor(R.color.white));
                againstTheTable.setBackgroundResource(R.drawable.tab_friend_unselect);
                if (gradeTableFragment == null) {
                    gradeTableFragment = GradeTableFragment.getInstances(listBean);
                    fragmentTransaction.add(R.id.frame_bg, gradeTableFragment).commit();
                } else {
                    fragmentTransaction.show(gradeTableFragment).commit();
                }
                break;
            case R.id.against_the_table:
                nowType = 2;
                gradeTable.setBackgroundResource(R.drawable.tab_friend_unselect);
                gradeTable.setTextColor(getResources().getColor(R.color.color_0461d2));
                againstTheTable.setBackgroundResource(R.drawable.tab_friend_select);
                againstTheTable.setTextColor(getResources().getColor(R.color.white));
                if (againstTheTableFragment == null) {
                    againstTheTableFragment = AgainstTheTableFragment.getInstances(listBean);
                    fragmentTransaction.add(R.id.frame_bg, againstTheTableFragment).commit();
                } else {
                    fragmentTransaction.show(againstTheTableFragment).commit();
                }
                break;
            default:
                break;
        }
    }
}

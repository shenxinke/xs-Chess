package com.xswq.chess.myapplication.activity.achievement;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;
import com.xswq.chess.myapplication.R;
import com.xswq.chess.myapplication.adapter.FriendListAdapter;
import com.xswq.chess.myapplication.base.BaseCompatActivity;
import com.xswq.chess.myapplication.fragment.HasWonSuccessFragment;
import com.xswq.chess.myapplication.fragment.UnderachievementFragment;
import com.xswq.chess.myapplication.utils.JumpIntent;
import java.util.ArrayList;
import java.util.List;

/**
 * 成就页面
 */

public class AchievementSystemActivity extends BaseCompatActivity implements View.OnClickListener {

    private String[] switch_titles = new String[]{"已获成就", "尚未获得"};

    @Override
    protected int getLayoutId() {
        return R.layout.achievement_systen_activity;
    }

    @Override
    protected void initView() {
        getWindow().getDecorView().setBackgroundResource(R.mipmap.achievement_bg);
        TextView loginBack = findViewById(R.id.login_back);
        loginBack.setVisibility(View.VISIBLE);
        loginBack.setOnClickListener(this);
        TextView loginTitles = findViewById(R.id.login_titles);
        loginTitles.setText("成就详情");
        TabLayout achievementTablayout = findViewById(R.id.prepare_tablayout);
        ViewPager friends_viewpage = findViewById(R.id.prepare_viewpage);
        //初始化相应权限下的内容信息（fragment）
        List<Fragment> listFragment = new ArrayList<>();
        listFragment.add(new HasWonSuccessFragment());
        listFragment.add(new UnderachievementFragment());
        FriendListAdapter mFriendListAdapter = new FriendListAdapter(getSupportFragmentManager(), switch_titles, listFragment);
        friends_viewpage.setOffscreenPageLimit(2);
        friends_viewpage.setAdapter(mFriendListAdapter);
        achievementTablayout.setupWithViewPager(friends_viewpage);
    }

    @Override
    protected void loadData() {

    }


    public static void openActivity(Context context) {
        JumpIntent.jump((Activity) context, AchievementSystemActivity.class);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_back:
                finish();
                break;
            default:
                break;
        }
    }
}

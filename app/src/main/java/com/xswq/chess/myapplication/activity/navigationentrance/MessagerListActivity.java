package com.xswq.chess.myapplication.activity.navigationentrance;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.xswq.chess.myapplication.R;
import com.xswq.chess.myapplication.adapter.MessageListAdapter;
import com.xswq.chess.myapplication.base.BaseCompatActivity;
import com.xswq.chess.myapplication.fragment.AddFriendMessagerFragment;
import com.xswq.chess.myapplication.fragment.MessagerFragment;

import java.util.ArrayList;
import java.util.List;

public class MessagerListActivity extends BaseCompatActivity implements View.OnClickListener {

    private String[] switch_titles = new String[]{"系统消息", "申请好友"};

    @Override
    protected int getLayoutId() {
        return R.layout.messager_list_layout;
    }

    @Override
    protected void initView() {
        getWindow().getDecorView().setBackgroundResource(R.mipmap.navigation_bg);
        TextView login_back = findViewById(R.id.login_back);
        login_back.setVisibility(View.VISIBLE);
        login_back.setOnClickListener(this);
        TextView login_titles = findViewById(R.id.login_titles);
        login_titles.setText("消息");
        //初始化相应权限下的内容信息（fragment）
        List<Fragment> listFragment = new ArrayList<>();
        listFragment.add(new MessagerFragment());
        listFragment.add(new AddFriendMessagerFragment());
        MessageListAdapter mMessageListAdapter = new MessageListAdapter(getSupportFragmentManager(), switch_titles, listFragment);
        TabLayout messagerTablayout = findViewById(R.id.messagers_tablayout);
        ViewPager messagerViewpage = findViewById(R.id.messagers_viewpage);
        messagerViewpage.setOffscreenPageLimit(2);
        messagerViewpage.setAdapter(mMessageListAdapter);
        messagerTablayout.setupWithViewPager(messagerViewpage);
        messagerViewpage.setCurrentItem(1);
    }

    @Override
    protected void loadData() {

    }

    @Override
    public void onClick(View v) {
        finish();
    }
}

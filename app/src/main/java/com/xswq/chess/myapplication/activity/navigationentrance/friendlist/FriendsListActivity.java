package com.xswq.chess.myapplication.activity.navigationentrance.friendlist;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.xswq.chess.myapplication.R;
import com.xswq.chess.myapplication.adapter.FriendListAdapter;
import com.xswq.chess.myapplication.base.BaseCompatActivity;
import com.xswq.chess.myapplication.fragment.FriendFragment;
import com.xswq.chess.myapplication.fragment.ListOfInstitutionsFragment;
import com.xswq.chess.myapplication.fragment.UserListFragment;
import java.util.ArrayList;
import java.util.List;

public class FriendsListActivity extends BaseCompatActivity implements View.OnClickListener {

    private String[] switchTitles = new String[]{"好友列表", "同机构好友", "用户列表"};

    @Override
    protected int getLayoutId() {
        return R.layout.friends_list_layout;
    }

    @Override
    protected void initView() {
        getWindow().getDecorView().setBackgroundResource(R.mipmap.navigation_bg);
        TextView loginBack = findViewById(R.id.login_back);
        loginBack.setVisibility(View.VISIBLE);
        loginBack.setOnClickListener(this);
        TextView loginTitles = findViewById(R.id.login_titles);
        loginTitles.setText("好友列表");
        getFragmentInfo();
    }

    @Override
    protected void loadData() {

    }

    @Override
    public void onClick(View v) {
        finish();
    }

    private void getFragmentInfo() {
        //初始化相应权限下的内容信息（fragment）
        List<Fragment> listFragment = new ArrayList<>();
        listFragment.add(new FriendFragment());
        listFragment.add(new ListOfInstitutionsFragment());
        listFragment.add(new UserListFragment());
        FriendListAdapter mFriendListAdapter = new FriendListAdapter(getSupportFragmentManager(), switchTitles, listFragment);
        TabLayout friendsTabLayout = findViewById(R.id.friends_tablayout);
        ViewPager friendsViewPage = findViewById(R.id.friends_viewpage);
        friendsViewPage.setOffscreenPageLimit(3);
        friendsViewPage.setAdapter(mFriendListAdapter);
        friendsTabLayout.setupWithViewPager(friendsViewPage);
    }
}

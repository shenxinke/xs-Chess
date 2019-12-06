package com.xswq.chess.myapplication.activity.questionstore;


import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.xswq.chess.myapplication.R;
import com.xswq.chess.myapplication.activity.main.TeacherUserHelpActivity;
import com.xswq.chess.myapplication.adapter.FriendListAdapter;
import com.xswq.chess.myapplication.base.BaseCompatActivity;
import com.xswq.chess.myapplication.fragment.CurriculumFragment;
import com.xswq.chess.myapplication.fragment.KnowledgePointFragment;
import com.xswq.chess.myapplication.fragment.QuestionLevelFragment;
import com.xswq.chess.myapplication.utils.StatisticsUtil;
import java.util.ArrayList;
import java.util.List;
import io.reactivex.annotations.Nullable;

/**
 * 题库
 */
public class QuestionStoreActivity extends BaseCompatActivity implements View.OnClickListener {
    private String[] switchTitles = new String[]{"按课程选题", "按级别选题", "按知识点选题"};

    @Override
    protected int getLayoutId() {
        return R.layout.questionstore_layout;
    }

    @Override
    protected void initView() {
        getWindow().getDecorView().setBackgroundResource(R.mipmap.question_bank_bg);
        TextView login_back = findViewById(R.id.login_back);
        login_back.setVisibility(View.VISIBLE);
        login_back.setOnClickListener(this);
        TextView login_titles = findViewById(R.id.login_titles);
        login_titles.setText("题库");
        getFragmentInfo();
    }

    @Override
    protected void loadData() {
        StatisticsUtil.getStatistics(token, userId, 2, QuestionStoreActivity.this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.login_back) {
            finish();
        }
    }

    private void getFragmentInfo() {
        //初始化相应权限下的内容信息（fragment）
        List<Fragment> listFragment = new ArrayList<>();
        listFragment.add(new CurriculumFragment());
        listFragment.add(new QuestionLevelFragment());
        listFragment.add(new KnowledgePointFragment());
        FriendListAdapter mFriendListAdapter = new FriendListAdapter(getSupportFragmentManager(), switchTitles, listFragment);
        TabLayout friendsTabLayout = findViewById(R.id.friends_tablayout);
        ViewPager friendsViewPage = findViewById(R.id.friends_viewpage);
        friendsViewPage.setAdapter(mFriendListAdapter);
        friendsTabLayout.setupWithViewPager(friendsViewPage);
    }
}

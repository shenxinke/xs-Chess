package com.xswq.chess.myapplication.activity.main;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;
import com.xswq.chess.myapplication.R;
import com.xswq.chess.myapplication.base.BaseCompatActivity;
import com.xswq.chess.myapplication.base.BaseFragmentAdapter;
import com.xswq.chess.myapplication.bean.AlwaysParkListBean;
import com.xswq.chess.myapplication.config.Const;
import com.xswq.chess.myapplication.fragment.IntroduceShareMoneyFragment;
import com.xswq.chess.myapplication.fragment.ThisCourtShareMoneyFragment;
import com.xswq.chess.myapplication.utils.ContactUrl;
import com.xswq.chess.myapplication.utils.GsonUtil;
import com.xswq.chess.myapplication.utils.ToastUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

public class ShareTheMoneyActivity extends BaseCompatActivity {
    private String[] switch_titles;
    private List<Fragment> listFragment;
    private String userId;
    private String token;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_share_the_money_layout;
    }

    @Override
    protected void initView() {
        getWindow().getDecorView().setBackgroundResource(R.mipmap.prepare_lessons_backgroud);
        TextView login_titles = findViewById(R.id.login_titles);
        login_titles.setText("续费情况");
        TextView login_back = findViewById(R.id.login_back);
        login_back.setVisibility(View.VISIBLE);
        login_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        getPrimaryName();
    }

    @Override
    protected void loadData() {

    }

    //查询所有的主院
    private void getPrimaryName() {
        try {
            OkHttpUtils.post().url(ContactUrl.GET_PRIMARY_PATH)
                    .addParams("token", token)
                    .addParams("uid", userId)
                    .build().execute(new StringCallback() {
                @Override
                public void onError(Call call, Exception e, int id) {
                    ToastUtils.show(Const.CONS_STR_ERROR);
                }

                @Override
                public void onResponse(String response, int id) {
                    AlwaysParkListBean alwaysParkListBean = GsonUtil.gsonToBean(response, AlwaysParkListBean.class, ShareTheMoneyActivity.this);
                    if (alwaysParkListBean != null) {
                        AlwaysParkListBean.DataBean data = alwaysParkListBean.getData();
                        if (data != null) {
                            AlwaysParkListBean.DataBean.NowBean now = data.getNow();
                            if (now != null) {
                                int dealerRank = now.getDealerRank();
                                //初始化相应权限下的内容信息（fragment）
                                listFragment = new ArrayList<>();
                                listFragment.add(new ThisCourtShareMoneyFragment());
                                if (dealerRank == 1) {
                                    listFragment.add(new IntroduceShareMoneyFragment());
                                    switch_titles = new String[]{"本园续费", "介绍分润"};
                                } else {
                                    switch_titles = new String[]{"本园续费"};
                                }
                                BaseFragmentAdapter baseFragmentAdapter = new BaseFragmentAdapter(getSupportFragmentManager(), switch_titles, listFragment);
                                TabLayout tabLayout = findViewById(R.id.tablayout);
                                ViewPager viewPage = findViewById(R.id.viewpage);
                                viewPage.setAdapter(baseFragmentAdapter);
                                tabLayout.setupWithViewPager(viewPage);
                            }
                        }
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

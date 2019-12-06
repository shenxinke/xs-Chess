package com.xswq.chess.myapplication.activity.personalCenter;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.support.design.widget.TabLayout;
import android.widget.TextView;
import com.xswq.chess.myapplication.R;
import com.xswq.chess.myapplication.adapter.FriendListAdapter;
import com.xswq.chess.myapplication.base.BaseCompatActivity;
import com.xswq.chess.myapplication.bean.IntegralShoppinggListBean;
import com.xswq.chess.myapplication.customview.ViewPagerSlide;
import com.xswq.chess.myapplication.fragment.IntegralShoppingMallFragment;
import com.xswq.chess.myapplication.utils.ContactUrl;
import com.xswq.chess.myapplication.utils.SPUtil;
import com.xswq.chess.myapplication.utils.StatisticsUtil;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import java.util.ArrayList;
import java.util.List;
import okhttp3.Call;

/**
 * 积分商城
 */
public class IntegralShoppingMallActivity extends BaseCompatActivity implements View.OnClickListener {
    private String userId;
    private String token;
    private Button integralShoppingMallAll;
    private Button integralShoppingMallHave;
    private Button integralShoppingMallNoHave;
    private ViewPagerSlide integralViewpage;
    private TabLayout integralTablayout;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_integral_shopping_mall_layout;
    }

    @Override
    protected void initView() {
        userId = SPUtil.getString("uid", "");
        token = SPUtil.getString("token", "");
        getWindow().getDecorView().setBackgroundResource(R.mipmap.achievement_bg);
        TextView title_backs =  findViewById(R.id.login_back);
        title_backs.setVisibility(View.VISIBLE);
        title_backs.setOnClickListener(this);
        TextView title_texts = findViewById(R.id.login_titles);
        title_texts.setText("积分商城");
        integralShoppingMallAll = findViewById(R.id.integral_shopping_mall_all);
        integralShoppingMallAll.setOnClickListener(this);
        integralShoppingMallAll.setText("全\n部");
        integralShoppingMallHave = findViewById(R.id.integral_shopping_mall_have);
        integralShoppingMallHave.setOnClickListener(this);
        integralShoppingMallHave.setText("已\n拥\n有");
        integralShoppingMallNoHave = findViewById(R.id.integral_shopping_mall_no_have);
        integralShoppingMallNoHave.setOnClickListener(this);
        integralShoppingMallNoHave.setText("未\n拥\n有");
        integralTablayout = findViewById(R.id.integral_tablayout);
        integralViewpage = findViewById(R.id.integral_viewpage);
        integralViewpage.setScanScroll(false);

        ImageView forRecord = findViewById(R.id.for_record);
        forRecord.setOnClickListener(this);
    }

    @Override
    protected void loadData() {
        StatisticsUtil.getStatistics(token, userId, 27,IntegralShoppingMallActivity.this);
        getIntegralShoppingAll("全部");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.integral_shopping_mall_all:
                integralShoppingMallAll.setBackgroundResource(R.mipmap.integral_shopping_mall_all);
                integralShoppingMallHave.setBackgroundResource(R.mipmap.integral_shopping_mall_no);
                integralShoppingMallNoHave.setBackgroundResource(R.mipmap.integral_shopping_mall_no);
                getIntegralShoppingAll("全部");
                break;
            case R.id.integral_shopping_mall_have:
                integralShoppingMallAll.setBackgroundResource(R.mipmap.integral_shopping_mall_no);
                integralShoppingMallHave.setBackgroundResource(R.mipmap.integral_shopping_mall_all);
                integralShoppingMallNoHave.setBackgroundResource(R.mipmap.integral_shopping_mall_no);
                getIntegralShoppingAll("已拥有");
                break;
            case R.id.integral_shopping_mall_no_have:
                integralShoppingMallAll.setBackgroundResource(R.mipmap.integral_shopping_mall_no);
                integralShoppingMallHave.setBackgroundResource(R.mipmap.integral_shopping_mall_no);
                integralShoppingMallNoHave.setBackgroundResource(R.mipmap.integral_shopping_mall_all);
                getIntegralShoppingAll("未拥有");
                break;
            case R.id.login_back:
                finish();
                break;
            case R.id.for_record:
                Intent intent = new Intent(IntegralShoppingMallActivity.this, IntegralShoppingForRecordActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    //获取商品分类列表
    private void getIntegralShoppingAll(final String title) {
        try {
            OkHttpUtils.post().url(ContactUrl.GET_COMMODITY_CLASSIFY)
                    .addParams("token", token)
                    .addParams("uid", userId)
                    .build().execute(new StringCallback() {
                @Override
                public void onError(Call call, Exception e, int id) {

                }

                @Override
                public void onResponse(String response, int id) {
                    Gson gson = new Gson();
                    IntegralShoppinggListBean integralShoppinggListBean = gson.fromJson(response, IntegralShoppinggListBean.class);
                    String returnCode = integralShoppinggListBean.getError().getReturnCode();
                    if (returnCode.equals("0")) {
                        List<IntegralShoppinggListBean.DataBean> data = integralShoppinggListBean.getData();
                        if (data.size() > 0) {
                            getTabViewPage(data, title);
                        }
                    } else if (returnCode.equals("10048")) {
                        quiteApp(IntegralShoppingMallActivity.this, integralShoppinggListBean.getError().getReturnMessage());
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getTabViewPage(List<IntegralShoppinggListBean.DataBean> dataList, String title) {
        String[] switch_titles = new String[dataList.size()];
        //初始化fragment
        List<Fragment> listFragment = new ArrayList<>();
        for (int i = 0; i < dataList.size(); i++) {
            switch_titles[i] = dataList.get(i).getClassifyName();
            listFragment.add(IntegralShoppingMallFragment.getInstances(dataList.get(i).getProductClassify(), userId, token, title, dataList.get(i).getClassifyName()));
        }
        FriendListAdapter mFriendListAdapter = new FriendListAdapter(getSupportFragmentManager(), switch_titles, listFragment);
        integralViewpage.setAdapter(mFriendListAdapter);
        integralTablayout.setupWithViewPager(integralViewpage);
    }
}

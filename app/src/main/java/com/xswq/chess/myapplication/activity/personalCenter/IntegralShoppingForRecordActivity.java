package com.xswq.chess.myapplication.activity.personalCenter;

import android.text.TextUtils;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import com.xswq.chess.myapplication.R;
import com.xswq.chess.myapplication.adapter.IntegralShoppingForRecordAdapter;
import com.xswq.chess.myapplication.base.BaseCompatActivity;
import com.xswq.chess.myapplication.bean.IntegralShoppingForRecordBean;
import com.xswq.chess.myapplication.utils.ContactUrl;
import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import java.util.List;
import okhttp3.Call;

/**
 * 积分兑换记录
 */
public class IntegralShoppingForRecordActivity extends BaseCompatActivity {

    private ListView listView;
    private TextView textView;
    private int pageNum = 1;
    private TextView title_backs;
    private TextView title_texts;
    private IntegralShoppingForRecordAdapter integralShoppingForRecordAdapter;
    private List<IntegralShoppingForRecordBean.DataBean.ListBean> list;
    private SmartRefreshLayout smartRefreshLayout;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_integral_shopping_record_layout;
    }

    @Override
    protected void initView() {
        listView = findViewById(R.id.listview);
        textView = findViewById(R.id.textView);
        smartRefreshLayout = findViewById(R.id.smartRefreshLayout);
        title_backs =  findViewById(R.id.login_back);
        title_backs.setVisibility(View.VISIBLE);
        title_texts = findViewById(R.id.login_titles);
        title_texts.setText("兑换记录");
        title_backs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        getWindow().getDecorView().setBackgroundResource(R.mipmap.achievement_bg);
    }

    @Override
    protected void loadData() {
        getData();
        initListener();
    }

    private void getData() {
        try {
            OkHttpUtils.post().url(ContactUrl.GET_SHOPPING_FOR_RECORD)
                    .addParams("token", token)
                    .addParams("uid", userId)
                    .addParams("pageNum", String.valueOf(pageNum))
                    .addParams("pageSize", "50")
                    .build().execute(new StringCallback() {
                @Override
                public void onError(Call call, Exception e, int id) {

                }

                @Override
                public void onResponse(String response, int id) {
                    if(!TextUtils.isEmpty(response)){
                        Gson gson = new Gson();
                        IntegralShoppingForRecordBean integralShoppingForRecordBean = gson.fromJson(response, IntegralShoppingForRecordBean.class);
                        String returnCode = integralShoppingForRecordBean.getError().getReturnCode();
                        if (returnCode.equals("0")) {
                            IntegralShoppingForRecordBean.DataBean data = integralShoppingForRecordBean.getData();
                            if(data!= null){
                                int total = data.getTotal();
                                if (total > 0) {
                                    list = data.getList();
                                    getFragmentAssembly(list);
                                } else {
                                    textView.setVisibility(View.VISIBLE);
                                }
                            }
                        } else if (returnCode.equals("10048")) {
                            quiteApp(IntegralShoppingForRecordActivity.this, integralShoppingForRecordBean.getError().getReturnMessage());
                        }
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getFragmentAssembly(List<IntegralShoppingForRecordBean.DataBean.ListBean> list) {
        if (integralShoppingForRecordAdapter == null) {
            integralShoppingForRecordAdapter = new IntegralShoppingForRecordAdapter(IntegralShoppingForRecordActivity.this, list);
            listView.setAdapter(integralShoppingForRecordAdapter);
        } else {
            integralShoppingForRecordAdapter.upListDate(list);
        }
    }

    /**
     * 上拉加载下拉刷新
     */
    private void initListener() {
        smartRefreshLayout.setOnRefreshLoadmoreListener(new OnRefreshLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                pageNum++;
                getData();
                smartRefreshLayout.finishLoadmore(1000);
            }

            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                pageNum = 1;
                if (list != null) {
                    list.clear();
                }
                getData();
                smartRefreshLayout.finishRefresh(500);
            }
        });
    }
}

package com.xswq.chess.myapplication.activity.weiqistory;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import com.xswq.chess.myapplication.R;
import com.xswq.chess.myapplication.adapter.WeiQiListAdapter;
import com.xswq.chess.myapplication.base.BaseCompatActivity;
import com.xswq.chess.myapplication.bean.WeiQiStoryListBean;
import com.xswq.chess.myapplication.utils.ContactUrl;
import com.xswq.chess.myapplication.utils.StatisticsUtil;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.Serializable;
import java.util.List;

import okhttp3.Call;

/**
 * 围棋故事列表
 */
public class WeiqiStoryListActivity extends BaseCompatActivity {

    private ListView storyList;
    private TextView text;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_wei_qi_story_list_layout;
    }

    @Override
    protected void initView() {
        getWindow().getDecorView().setBackgroundResource(R.mipmap.wei_qi_story_list_bg);
        TextView login_titles = findViewById(R.id.login_titles);
        login_titles.setText("围棋故事");
        TextView login_back = findViewById(R.id.login_back);
        login_back.setVisibility(View.VISIBLE);
        storyList = findViewById(R.id.story_list);
        text = findViewById(R.id.text);
        login_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void loadData() {
        StatisticsUtil.getStatistics(token, userId, 26,WeiqiStoryListActivity.this);
        getDate();
    }

    //获取数据
    private void getDate() {
        try {
            OkHttpUtils.post().url(ContactUrl.GET_WI_QI_STORY)
                    .addParams("token", token)
                    .addParams("uid", userId)
                    .addParams("pageNum", "1")
                    .addParams("pageSize", "50")
                    .build().execute(new StringCallback() {
                @Override
                public void onError(Call call, Exception e, int id) {
                }

                @Override
                public void onResponse(String response, int id) {
                    if (response != null) {
                        Gson gson = new Gson();
                        WeiQiStoryListBean weiQiStoryListBean = gson.fromJson(response, WeiQiStoryListBean.class);
                        WeiQiStoryListBean.ErrorBean error = weiQiStoryListBean.getError();
                        String returnCode = error.getReturnCode();
                        if (returnCode.equals("0")) {
                            final WeiQiStoryListBean.DataBean data = weiQiStoryListBean.getData();
                            if (data.getTotal() > 0) {
                                text.setVisibility(View.GONE);
                                final List<WeiQiStoryListBean.DataBean.ListBean> list = data.getList();
                                WeiQiListAdapter weiQiListAdapter = new WeiQiListAdapter(WeiqiStoryListActivity.this, list);
                                storyList.setAdapter(weiQiListAdapter);
                                storyList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                        Intent intent = new Intent(WeiqiStoryListActivity.this, WeiqiStoryActivity.class);
                                        intent.putExtra("Position", position);
                                        intent.putExtra("List", (Serializable) list);
                                        startActivity(intent);
                                    }
                                });
                            } else {
                                text.setVisibility(View.VISIBLE);
                            }
                        } else if (returnCode.equals("10048")) {
                            quiteApp(WeiqiStoryListActivity.this, error.getReturnUserMessage());
                        }
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

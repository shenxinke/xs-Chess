package com.xswq.chess.myapplication.activity.navigationentrance;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.xswq.chess.myapplication.R;
import com.xswq.chess.myapplication.adapter.PlayChessFragmentAdapter;
import com.xswq.chess.myapplication.androidtojs.JsWebViewActivity;
import com.xswq.chess.myapplication.base.BaseCompatActivity;
import com.xswq.chess.myapplication.config.Const;
import com.xswq.chess.myapplication.greendao.entity.PlayingChess;
import com.xswq.chess.myapplication.utils.ContactUrl;
import com.xswq.chess.myapplication.utils.GsonUtil;
import com.xswq.chess.myapplication.utils.ToastUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.builder.GetBuilder;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

public class PlayingChessActivity extends BaseCompatActivity implements View.OnClickListener {

    private EditText playingchess_user;
    private ListView mListView;
    private ImageView imageBackgroud;
    private TextView textBackgroud;
    private String whiteUsername;
    private SmartRefreshLayout smartRefreshLayout;
    private int pageNum = 1;
    private List<PlayingChess.DataBean.ListBean> listBeans = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.navigation_playingchess_layout;
    }

    @Override
    protected void initView() {
        getWindow().getDecorView().setBackgroundResource(R.mipmap.navigation_bg);
        TextView login_back = findViewById(R.id.login_back);
        login_back.setVisibility(View.VISIBLE);
        login_back.setOnClickListener(this);
        TextView login_titles = findViewById(R.id.login_titles);
        login_titles.setText("对弈列表");
        smartRefreshLayout = findViewById(R.id.smartRefreshLayout);
        playingchess_user = findViewById(R.id.playingchess_user);
        imageBackgroud = findViewById(R.id.image_background);
        textBackgroud = findViewById(R.id.text_background);
        mListView = findViewById(R.id.playingchess_view);
        ImageView playingchess_search = findViewById(R.id.playingchess_search);
        playingchess_search.setOnClickListener(this);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent mIntent = new Intent(PlayingChessActivity.this, JsWebViewActivity.class);
                mIntent.putExtra("blackUserId", listBeans.get(i).getBlackUserId());
                mIntent.putExtra("prefix", ContactUrl.PLAY);
                startActivity(mIntent);
            }
        });
    }

    @Override
    protected void loadData() {
        getRequsetParameter();
        initListener();
    }

    private void getRequsetParameter() {
        try {
            GetBuilder getBuilder = OkHttpUtils.get();
            getBuilder.url(ContactUrl.GETVSLIST)
                    .addParams("token", token)
                    .addParams("uid", userId)
                    .addParams("pageNum", String.valueOf(pageNum))
                    .addParams("pageSize", Const.STR20);
            if (!TextUtils.isEmpty(whiteUsername)) {
                getBuilder.addParams("_WhiteUsername", whiteUsername);
            }

            getBuilder.build().execute(new StringCallback() {
                @Override
                public void onError(Call call, Exception e, int id) {
                    dismissProgressDialog();
                    ToastUtils.show(Const.CONS_STR_ERROR);
                }

                @Override
                public void onResponse(String response, int id) {
                    PlayingChess playingChess = GsonUtil.gsonToBean(response, PlayingChess.class, PlayingChessActivity.this);
                    if (playingChess != null) {
                        PlayingChess.DataBean data = playingChess.getData();
                        if (data != null) {
                            listBeans.addAll(data.getList());
                            if (listBeans != null && !listBeans.isEmpty()) {
                                imageBackgroud.setVisibility(View.GONE);
                                textBackgroud.setVisibility(View.GONE);
                                mListView.setVisibility(View.VISIBLE);
                                PlayChessFragmentAdapter historyTaskAdapter = new PlayChessFragmentAdapter(PlayingChessActivity.this, listBeans);
                                mListView.setAdapter(historyTaskAdapter);
                            } else {
                                imageBackgroud.setVisibility(View.VISIBLE);
                                textBackgroud.setVisibility(View.VISIBLE);
                                mListView.setVisibility(View.GONE);
                            }
                        } else {
                            imageBackgroud.setVisibility(View.VISIBLE);
                            textBackgroud.setVisibility(View.VISIBLE);
                            mListView.setVisibility(View.GONE);
                        }
                    } else {
                        imageBackgroud.setVisibility(View.VISIBLE);
                        textBackgroud.setVisibility(View.VISIBLE);
                        mListView.setVisibility(View.GONE);
                    }
                }

            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_back:
                finish();
                break;
            case R.id.playingchess_search:
                whiteUsername = playingchess_user.getText().toString().trim();
                if (!TextUtils.isEmpty(whiteUsername)) {
                    getRequsetParameter();
                    if (listBeans != null) {
                        listBeans.clear();
                    }
                }
                break;
        }
    }

    private void initListener() {
        smartRefreshLayout.setOnRefreshLoadmoreListener(new OnRefreshLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                pageNum++;
                getRequsetParameter();
                smartRefreshLayout.finishLoadmore(1000);
            }

            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                pageNum = 1;
                if (listBeans != null) {
                    listBeans.clear();
                }
                getRequsetParameter();
                smartRefreshLayout.finishRefresh(500);
            }
        });
    }
}

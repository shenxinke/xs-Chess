package com.xswq.chess.myapplication.activity.onlinegame;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.xswq.chess.myapplication.R;
import com.xswq.chess.myapplication.activity.main.TeacherUserHelpActivity;
import com.xswq.chess.myapplication.activity.navigationentrance.friendlist.FriendsListActivity;
import com.xswq.chess.myapplication.base.BaseCompatActivity;
import com.xswq.chess.myapplication.bean.RandomBattleBean;
import com.xswq.chess.myapplication.config.Const;
import com.xswq.chess.myapplication.utils.ContactUrl;
import com.xswq.chess.myapplication.utils.SPUtil;
import com.xswq.chess.myapplication.utils.StatisticsUtil;
import com.xswq.chess.myapplication.utils.ToastUtils;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * 在线对弈页面
 */
public class OnlineGameActivity extends BaseCompatActivity implements View.OnClickListener {

    @Override
    protected int getLayoutId() {
        return R.layout.online_game_layout;
    }

    @Override
    protected void initView() {
        getWindow().getDecorView().setBackgroundResource(R.mipmap.aionline_gamebg);
        TextView login_back = findViewById(R.id.login_back);
        login_back.setVisibility(View.VISIBLE);
        login_back.setOnClickListener(OnlineGameActivity.this);
        TextView login_titles = findViewById(R.id.login_titles);
        login_titles.setText("在线对弈");
        ImageView loginUserHelp = findViewById(R.id.user_help);
        //学生端不可见
        if (userType != 3) {
            loginUserHelp.setVisibility(View.VISIBLE);
            loginUserHelp.setOnClickListener(this);
        } else {
            loginUserHelp.setVisibility(View.GONE);
        }
        ImageView imageView3_up = findViewById(R.id.imageView3_up);
        imageView3_up.setOnClickListener(this);
        ImageView imageView2_friend = findViewById(R.id.imageView2_friend);
        imageView2_friend.setOnClickListener(this);
        ImageView imageView_ai = findViewById(R.id.imageView_ai);
        imageView_ai.setOnClickListener(this);
        boolean aBoolean = SPUtil.getBoolean("isStudentOnLinGuide", true);
    }

    @Override
    protected void loadData() {
        StatisticsUtil.getStatistics(token, userId, 1, OnlineGameActivity.this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.login_back:
                finish();
                break;
            case R.id.imageView3_up:
                int level = SPUtil.getInt("level", 0);
                Intent onIntent;
                if (level == 0) {
                    onIntent = new Intent(OnlineGameActivity.this, IncompleteChessGameActivity.class);
                    onIntent.putExtra("intenType", 1);
                    startActivity(onIntent);
                } else {
                    randomBattle();
                }
                break;
            case R.id.imageView2_friend:
                startActivity(new Intent(OnlineGameActivity.this, FriendsListActivity.class));
                break;
            case R.id.imageView_ai:
                startActivity(new Intent(OnlineGameActivity.this, AIOnlineGameActivity.class));
                break;
            case R.id.user_help:
                int pageType = 6;
                TeacherUserHelpActivity.openActivity(OnlineGameActivity.this, pageType);
                break;
            default:
                break;
        }
    }


    private void randomBattle() {
        try {
            OkHttpUtils.post().url(ContactUrl.POST_RANDOM_BATTLE_PATH)
                    .addParams("token", token)
                    .addParams("uid", userId)
                    .build().execute(new StringCallback() {
                @Override
                public void onError(Call call, Exception e, int id) {
                    ToastUtils.show(Const.CONS_STR_ERROR);
                }

                @Override
                public void onResponse(String response, int id) {
                    if (!TextUtils.isEmpty(response)) {
                        Gson gson = new Gson();
                        RandomBattleBean randomBattleBean = gson.fromJson(response, RandomBattleBean.class);
                        String returnCode = randomBattleBean.getError().getReturnCode();
                        switch (returnCode) {
                            case "0": {
                                Intent mIntent = new Intent(OnlineGameActivity.this, ProgressBarActivity.class);
                                startActivity(mIntent);
                                break;
                            }
                            case "2": {
                                Intent mIntent = new Intent(OnlineGameActivity.this, IncompleteChessGameActivity.class);
                                mIntent.putExtra("intenType", 2);
                                startActivity(mIntent);
                                break;
                            }
                            case "10048":
                                quiteApp(OnlineGameActivity.this, randomBattleBean.getError().getReturnMessage());
                                break;
                        }
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

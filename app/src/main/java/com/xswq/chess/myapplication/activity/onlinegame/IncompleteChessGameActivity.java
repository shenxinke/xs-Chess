package com.xswq.chess.myapplication.activity.onlinegame;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.xswq.chess.myapplication.R;
import com.xswq.chess.myapplication.androidtojs.JsWebViewActivity;
import com.xswq.chess.myapplication.base.BaseActivity;
import com.xswq.chess.myapplication.bean.GameInfoUseridBean;
import com.xswq.chess.myapplication.utils.ContactUrl;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import java.util.List;
import okhttp3.Call;

/**
 * 棋局提示页面
 */
public class IncompleteChessGameActivity extends BaseActivity implements View.OnClickListener {

    private int intenType;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_incomplete_chess_game_layout);
        getWindow().getDecorView().setBackgroundResource(R.color.half);
        Intent intent = getIntent();
        intenType = intent.getIntExtra("intenType", 0);
        TextView text = findViewById(R.id.text);
        Button incomplete_yes = findViewById(R.id.integral_shopping_mall_yes);
        incomplete_yes.setOnClickListener(this);
        Button button_skip = findViewById(R.id.button_skip);
        button_skip.setOnClickListener(this);
        ImageView shutdown = findViewById(R.id.shutdown);
        shutdown.setOnClickListener(this);
        if (intenType == 1) {
            text.setText("在您对弈前，请让我们了解您的棋力！");
        } else if (intenType == 2) {
            text.setText("当前有未完成的棋局！");
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.shutdown:
            case R.id.button_skip:
                finish();
                break;
            case R.id.integral_shopping_mall_yes:
                //1、未测棋力 跳棋力页面  2、有未完成棋局 跳未完成棋局页面
                if (intenType == 1) {
                    Intent mIntent = new Intent(IncompleteChessGameActivity.this, JsWebViewActivity.class);
                    mIntent.putExtra("webViewType", 2);
                    mIntent.putExtra("prefix", ContactUrl.TESTTITLE);
                    startActivity(mIntent);
                    finish();
                } else if (intenType == 2) {
                    getGameInfo();
                }
                break;
            default:
                break;

        }
    }

    private void getGameInfo() {
        try {
            OkHttpUtils.post().url(ContactUrl.POST_GAME_INFO_USERID_PATH)
                    .addParams("token", token)
                    .addParams("uid", userId)
                    .build().execute(new StringCallback() {
                @Override
                public void onError(Call call, Exception e, int id) {
                }

                @Override
                public void onResponse(String response, int id) {
                    if (!TextUtils.isEmpty(response)) {
                        Gson gson = new Gson();
                        GameInfoUseridBean gameInfoUseridBean = gson.fromJson(response, GameInfoUseridBean.class);
                        if (gameInfoUseridBean != null) {
                            List<GameInfoUseridBean.DataBean> data = gameInfoUseridBean.getData();
                            if (data != null && !data.isEmpty()) {
                                int gameType = data.get(0).getGameType();
                                Intent intent;
                                switch (gameType) {
                                    case 1:
                                    case 2:
                                    case 3:
                                    case 4:
                                    case 5:
                                        intent = new Intent(IncompleteChessGameActivity.this, JsWebViewActivity.class);
                                        intent.putExtra("prefix", ContactUrl.PLAY);
                                        startActivity(intent);
                                        break;
                                    case 6:
                                        intent = new Intent(IncompleteChessGameActivity.this, JsWebViewActivity.class);
                                        intent.putExtra("prefix", ContactUrl.MATCHPLAY);
                                        startActivity(intent);
                                        break;
                                    default:
                                        break;
                                }
                                finish();
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

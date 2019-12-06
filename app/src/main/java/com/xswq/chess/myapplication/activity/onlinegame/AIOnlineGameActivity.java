package com.xswq.chess.myapplication.activity.onlinegame;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.xswq.chess.myapplication.R;
import com.xswq.chess.myapplication.androidtojs.JsWebViewActivity;
import com.xswq.chess.myapplication.base.BaseCompatActivity;
import com.xswq.chess.myapplication.bean.IsPlayingChessBean;
import com.xswq.chess.myapplication.customview.StringScrollPicker;
import com.xswq.chess.myapplication.utils.ContactUrl;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.builder.GetBuilder;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import okhttp3.Call;

/**
 * AI对弈
 */
public class AIOnlineGameActivity extends BaseCompatActivity implements View.OnClickListener {

    @Override
    protected int getLayoutId() {
        return R.layout.ai_onlinegame_layout;
    }

    @Override
    protected void initView() {
        getWindow().getDecorView().setBackgroundResource(R.mipmap.aionline_gamebg);
        TextView login_back = findViewById(R.id.login_back);
        login_back.setVisibility(View.VISIBLE);
        login_back.setOnClickListener(this);
        TextView login_titles = findViewById(R.id.login_titles);
        login_titles.setText("AI对弈");
        ImageView textViewInit = findViewById(R.id.imageView3);
        textViewInit.setOnClickListener(this);
        ImageView textViewsimple = findViewById(R.id.imageView4);
        textViewsimple.setOnClickListener(this);
        ImageView textViewnormal = findViewById(R.id.imageView5);
        textViewnormal.setOnClickListener(this);
        ImageView textViewhard = findViewById(R.id.imageView6);
        textViewhard.setOnClickListener(this);
        ImageView textViewveyhard = findViewById(R.id.imageView7);
        textViewveyhard.setOnClickListener(this);
    }

    @Override
    protected void loadData() {

    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_back:
                finish();
                break;
            case R.id.imageView3:
                getAIPlayChess();
                break;
            case R.id.imageView4:
                Intent intent = new Intent(AIOnlineGameActivity.this, CreatAIOnlineGameActivity.class);
                intent.putExtra("type", 1);
                startActivity(intent);
                break;
            case R.id.imageView5:
                intent = new Intent(AIOnlineGameActivity.this, CreatAIOnlineGameActivity.class);
                intent.putExtra("type", 2);
                startActivity(intent);
                break;
            case R.id.imageView6:
                intent = new Intent(AIOnlineGameActivity.this, CreatAIOnlineGameActivity.class);
                intent.putExtra("type", 3);
                startActivity(intent);
                break;
            case R.id.imageView7:
                intent = new Intent(AIOnlineGameActivity.this, CreatAIOnlineGameActivity.class);
                intent.putExtra("type", 4);
                startActivity(intent);
                break;
        }

    }
    //创建棋局
    private void getAIPlayChess() {
        try {
            GetBuilder getBuilder = OkHttpUtils.get();
            getBuilder.url(ContactUrl.GET_IS_PLAY_CHESS);
            getBuilder.addParams("token", token);
            getBuilder.addParams("uid", userId);
            getBuilder.addParams("ai_level", "25k");
            getBuilder.addParams("color", "");
            getBuilder.addParams("count", "");
            getBuilder.addParams("size", "9");
            getBuilder.build().execute(new StringCallback() {
                @Override
                public void onError(Call call, Exception e, int id) {

                }

                @Override
                public void onResponse(String response, int id) {
                    Gson gson = new Gson();
                    Intent intent;
                    IsPlayingChessBean studentGuidanceBean = gson.fromJson(response, IsPlayingChessBean.class);
                    String returnCode = studentGuidanceBean.getError().getReturnCode();
                    if (returnCode.equals("0")) {
                        intent = new Intent(AIOnlineGameActivity.this, JsWebViewActivity.class);
                        intent.putExtra("prefix", ContactUrl.PLAY);
                        intent.putExtra("AIplay", "1");
                        startActivity(intent);
                    } else if (returnCode.equals("10051")) {
                        intent = new Intent(AIOnlineGameActivity.this, IncompleteChessGameActivity.class);
                        intent.putExtra("intenType", 2);
                        startActivity(intent);
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

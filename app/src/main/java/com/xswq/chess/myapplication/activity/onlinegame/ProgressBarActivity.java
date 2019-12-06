package com.xswq.chess.myapplication.activity.onlinegame;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.xswq.chess.myapplication.R;
import com.xswq.chess.myapplication.androidtojs.JsWebViewActivity;
import com.xswq.chess.myapplication.base.BaseActivity;
import com.xswq.chess.myapplication.config.Const;
import com.xswq.chess.myapplication.customview.MyBarPercentView;
import com.xswq.chess.myapplication.utils.ContactUrl;
import com.xswq.chess.myapplication.utils.ToastUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Timer;
import java.util.TimerTask;

import okhttp3.Call;

public class ProgressBarActivity extends BaseActivity {
    private MyBarPercentView myProgressBar;
    private int mCurrentProgress = 0;
    private Timer timer = new Timer();
    private TextView textSchedule;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_bar_layout);
        getWindow().getDecorView().setBackgroundResource(R.color.half);
        Button button = findViewById(R.id.green_button);
        myProgressBar = findViewById(R.id.my_progress_bar);
        textSchedule = findViewById(R.id.text_schedule);
        //主线程中调用：
        timer.schedule(timerTask, 1000, 500);//延时1s
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timer.cancel();
                timer = null;
                timerTask.cancel();
                timerTask = null;
                finish();
                overridePendingTransition(0, 0);
            }
        });

    }

    final Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if(mCurrentProgress < 11){
                myProgressBar.setPercentage(mCurrentProgress, 10);
                textSchedule.setText(mCurrentProgress * 10 + "%");
            }else if (mCurrentProgress == 11) {
                getRandomBatle();
            }
            super.handleMessage(msg);
        }
    };

    TimerTask timerTask = new TimerTask() {
        @Override
        public void run() {
            Message message = new Message();
            mCurrentProgress++;
            message.what = mCurrentProgress;
            handler.sendMessage(message);
        }
    };

    private void getRandomBatle() {
        try {
            OkHttpUtils.post().url(ContactUrl.POST_GAME_INFOR_PATH)
                    .addParams("token", token)
                    .addParams("uid", userId)
                    .build().execute(new StringCallback() {

                @Override
                public void onError(Call call, Exception e, int id) {
                    ToastUtils.show(Const.CONS_STR_ERROR);
                }

                @Override
                public void onResponse(String response, int id) {
                    try {
                        if (!TextUtils.isEmpty(response)) {
                            JSONObject object = new JSONObject(response);
                            JSONObject error = object.getJSONObject("error");
                            int returnCode = error.getInt("returnCode");
                            if (returnCode == 0) {
                                Intent intent = new Intent(ProgressBarActivity.this, JsWebViewActivity.class);
                                intent.putExtra("prefix", ContactUrl.PLAY);
                                startActivity(intent);
                                finish();
                            } else if (returnCode == 10053) {
                                getCreatAiMatch();
                            } else if (returnCode == 10048) {
                                quiteApp(ProgressBarActivity.this, error.getString("returnMessage"));
                            } else {
                                ToastUtils.show(error.getString("returnMessage"));
                            }
                        } else {
                            ToastUtils.show(Const.CONS_STR_ERROR);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void getCreatAiMatch() {
        try {
            OkHttpUtils.post().url(ContactUrl.POST_CREATE_AI_MATCH_PATH)
                    .addParams("token", token)
                    .addParams("uid", userId)
                    .build().execute(new StringCallback() {

                @Override
                public void onError(Call call, Exception e, int id) {
                    ToastUtils.show(Const.CONS_STR_ERROR);
                }

                @Override
                public void onResponse(String response, int id) {
                    try {
                        if (!TextUtils.isEmpty(response)) {
                            JSONObject object = new JSONObject(response);
                            JSONObject error = object.getJSONObject("error");
                            int returnCode = error.getInt("returnCode");
                            if (returnCode == 0) {
                                Intent intent = new Intent(ProgressBarActivity.this, JsWebViewActivity.class);
                                intent.putExtra("prefix", ContactUrl.PLAY);
                                startActivity(intent);
                                finish();
                            } else if (returnCode == 10051) {
                                Intent intent = new Intent(ProgressBarActivity.this, IncompleteChessGameActivity.class);
                                intent.putExtra("intenType", 2);
                                startActivity(intent);
                                finish();
                            } else if (returnCode == 10001 || returnCode == 1) {
                                ToastUtils.show("操作失败");
                            } else if (returnCode == 10048) {
                                quiteApp(ProgressBarActivity.this, error.getString("returnMessage"));
                            } else {
                                ToastUtils.show(error.getString("returnMessage"));
                            }
                        } else {
                            ToastUtils.show(Const.CONS_STR_ERROR);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
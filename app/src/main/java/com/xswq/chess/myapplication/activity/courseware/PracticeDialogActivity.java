package com.xswq.chess.myapplication.activity.courseware;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.xswq.chess.myapplication.R;
import com.xswq.chess.myapplication.androidtojs.JsWebViewActivity;
import com.xswq.chess.myapplication.base.BaseActivity;
import com.xswq.chess.myapplication.http.HttpCallBackLisener;
import com.xswq.chess.myapplication.http.httpparameterapi.ExplanationPatternApi;
import com.xswq.chess.myapplication.http.retrofitHttp.RetrofitManager;
import com.xswq.chess.myapplication.http.subscriber.ExplanationPatternSub;
import com.xswq.chess.myapplication.utils.ContactUrl;
import com.xswq.chess.myapplication.utils.StatisticsUtil;

import org.json.JSONObject;

public class PracticeDialogActivity extends BaseActivity implements View.OnClickListener {

    private Intent mIntent;
    private int class_id;
    private String title;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.practicedialog_layout);
        getWindow().getDecorView().setBackgroundResource(R.color.half);
        mIntent = getIntent();
        class_id = mIntent.getIntExtra("classId", 0);
        title = mIntent.getStringExtra("title");
        init();
    }

    private void init() {
        ImageView practices_id = findViewById(R.id.practices_id);
        practices_id.setOnClickListener(this);
        ImageView courseware_id = findViewById(R.id.courseware_id);
        courseware_id.setOnClickListener(this);
        ImageView practices_dialog_id = findViewById(R.id.practices_dialog_id);
        practices_dialog_id.setOnClickListener(this);
    }



    @Override
    public void onClick(View v) {
        Intent mIntent;
        switch (v.getId()) {
            case R.id.practices_id:
                jumpJsIntent();
                finish();
                break;
            case R.id.courseware_id:
                mIntent = new Intent(PracticeDialogActivity.this, ExplanationActivity.class);
                mIntent.putExtra("classId", class_id);
                mIntent.putExtra("title", title);
                mIntent.putExtra("prefix", ContactUrl.COURSEWAREDETAIL);
                startActivity(mIntent);
                finish();
                break;
            case R.id.practices_dialog_id:
                finish();
                break;
        }

    }



    //公共跳转JsActivity页面
    private void jumpJsIntent() {
        StatisticsUtil.getStatistics(token, userId, 9, PracticeDialogActivity.this);
        mIntent = new Intent(PracticeDialogActivity.this, JsWebViewActivity.class);
        mIntent.putExtra("classId", class_id);
        mIntent.putExtra("title", title);
        mIntent.putExtra("prefix", ContactUrl.COURSEWAREDETAIL);
        startActivity(mIntent);
    }
}

package com.xswq.chess.myapplication.activity.navigationentrance;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.xswq.chess.myapplication.R;
import com.xswq.chess.myapplication.base.BaseCompatActivity;

public class HelpcenterActivity extends BaseCompatActivity implements View.OnClickListener {

    @Override
    protected int getLayoutId() {
        return R.layout.help_center_layout;
    }

    @Override
    protected void initView() {
        getWindow().getDecorView().setBackgroundResource(R.mipmap.navigation_bg);
        TextView login_back = findViewById(R.id.login_back);
        login_back.setVisibility(View.VISIBLE);
        login_back.setOnClickListener(this);
        TextView login_titles = findViewById(R.id.login_titles);
        login_titles.setText("帮助中心");

        TextView help_content_id1 = findViewById(R.id.help_content_id1);
        help_content_id1.setText(getResources().getString(R.string.help_content));
        TextView help_content_id2 = findViewById(R.id.help_content_id2);
        help_content_id2.setText(getResources().getString(R.string.help_content2));
    }

    @Override
    protected void loadData() {

    }

    @Override
    public void onClick(View v) {
        finish();
    }
}

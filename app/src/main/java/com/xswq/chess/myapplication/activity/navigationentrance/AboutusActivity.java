package com.xswq.chess.myapplication.activity.navigationentrance;

import android.view.View;
import android.widget.TextView;
import com.xswq.chess.myapplication.R;
import com.xswq.chess.myapplication.base.BaseCompatActivity;

public class AboutusActivity extends BaseCompatActivity implements View.OnClickListener {

    @Override
    protected int getLayoutId() {
        return R.layout.about_us_layout;
    }

    @Override
    protected void initView() {
        getWindow().getDecorView().setBackgroundResource(R.mipmap.navigation_bg);
        TextView login_back = findViewById(R.id.login_back);
        login_back.setVisibility(View.VISIBLE);
        login_back.setOnClickListener(this);
        TextView login_titles = findViewById(R.id.login_titles);
        login_titles.setText("关于我们");
        TextView aboutus_content_id1 = findViewById(R.id.aboutus_content_id1);
        aboutus_content_id1.setText("      " + getResources().getString(R.string.about_us_content1));
        TextView aboutus_content_id2 = findViewById(R.id.aboutus_content_id2);
        aboutus_content_id2.setText("      " + getResources().getString(R.string.about_us_content2));
        TextView aboutus_content_id3 = findViewById(R.id.aboutus_content_id3);
        aboutus_content_id3.setText("      " + getResources().getString(R.string.about_us_content3));
        TextView aboutus_content_id4 = findViewById(R.id.aboutus_content_id4);
        aboutus_content_id4.setText("      " + getResources().getString(R.string.about_us_content4));
    }

    @Override
    protected void loadData() {

    }

    @Override
    public void onClick(View v) {
        finish();
    }
}

package com.xswq.chess.myapplication.activity.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.xswq.chess.myapplication.R;
import com.xswq.chess.myapplication.base.BaseActivity;

public class SetCourseHintActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_course_hint_layout);
        getWindow().getDecorView().setBackgroundResource(R.color.half);
        ImageView imageView = findViewById(R.id.shutdown);
        Button button = findViewById(R.id.button_ok);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}

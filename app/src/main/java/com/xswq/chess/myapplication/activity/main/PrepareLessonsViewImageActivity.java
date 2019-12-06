package com.xswq.chess.myapplication.activity.main;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.xswq.chess.myapplication.R;
import com.xswq.chess.myapplication.base.BaseActivity;
import com.xswq.chess.myapplication.utils.JumpIntent;
import com.xswq.chess.myapplication.utils.Util;

/**
 * 我要备课查看图片
 */
public class PrepareLessonsViewImageActivity extends BaseActivity {

    private ImageView imageView;
    private ImageView imageOut;
    private String imageUrl;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.prepare_lessons_image_view);
        getWindow().getDecorView().setBackgroundResource(R.color.half);
        imageView = findViewById(R.id.parent_lessons_image);
        imageOut = findViewById(R.id.image_out);
        imageOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Intent intent = getIntent();
        imageUrl = intent.getStringExtra("imageUrl");

        Glide.with(PrepareLessonsViewImageActivity.this)
                .load(Util.signString(imageUrl))
                .asBitmap()
                .error(R.mipmap.default_xswq)
                .placeholder(R.mipmap.default_xswq)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(imageView);
    }

    public static void openActivity(Context context, String imageUrl) {
        Bundle bundle = new Bundle();
        bundle.putString("imageUrl", imageUrl);
        JumpIntent.jump((Activity) context, PrepareLessonsViewImageActivity.class, bundle);
    }
}

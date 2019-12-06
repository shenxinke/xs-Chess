package com.xswq.chess.myapplication.activity.personalCenter;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.xswq.chess.myapplication.R;
import com.xswq.chess.myapplication.base.BaseActivity;
import com.xswq.chess.myapplication.utils.ContactUrl;

public class IntegralShoppingMallGuideActivity extends BaseActivity {

    private ImageView imageUse;
    private ImageView imageBuy;
    private Button button;
    private ImageView imageHead;
    private ImageView imageLight;
    private String headUrl;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_integral_shopping_guide_layout);
        getWindow().getDecorView().setBackgroundResource(R.color.half);
        imageBuy = findViewById(R.id.integral_shoppingl_buy_succeed);
        imageUse = findViewById(R.id.integral_shoppingl_use_succeed);
        imageHead = findViewById(R.id.integral_shopping_head);
        imageLight = findViewById(R.id.integral_shoppingl_light);
        button = findViewById(R.id.but_yes);
        Intent intent = getIntent();
        int type = intent.getIntExtra("type", 0);
        headUrl = intent.getStringExtra("headUrl");
        String userHeading = ContactUrl.BASE_PATH + "/" + headUrl;
        Glide.with(IntegralShoppingMallGuideActivity.this).load(userHeading).dontAnimate().into(imageHead);
        if (type == 1) {
            imageBuy.setVisibility(View.VISIBLE);
            imageUse.setVisibility(View.GONE);
        } else {
            imageUse.setVisibility(View.VISIBLE);
            imageBuy.setVisibility(View.GONE);
        }
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}

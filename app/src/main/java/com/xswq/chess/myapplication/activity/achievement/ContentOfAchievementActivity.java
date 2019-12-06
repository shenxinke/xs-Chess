package com.xswq.chess.myapplication.activity.achievement;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.xswq.chess.myapplication.R;
import com.xswq.chess.myapplication.activity.onlinegame.OnlineGameActivity;
import com.xswq.chess.myapplication.androidtojs.JsWebViewActivity;
import com.xswq.chess.myapplication.base.BaseActivity;
import com.xswq.chess.myapplication.customview.MyProgressBar;
import com.xswq.chess.myapplication.greendao.GreenDaoManager;
import com.xswq.chess.myapplication.utils.ContactUrl;
import com.xswq.chess.myapplication.utils.JumpIntent;
import com.xswq.chess.myapplication.utils.Util;

/**
 * 成就详情页
 */
public class ContentOfAchievementActivity extends BaseActivity {
    private String touken;
    private String userId;
    private MyProgressBar myProgressBar;
    private String mAchieveName;
    private String mActualName;
    private String imageUrl;
    private int detailednessType;
    private int getMaxValue;
    private int getNum;
    private ImageView imageBlack;
    private ImageView imageView;
    private TextView textView;
    private TextView textNumber;
    private Button button;
    private TextView textTitle;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_achievement_layout);
        getData();
        initView();

    }

    private void getData() {
        Intent intent = getIntent();
        touken = intent.getStringExtra("touken");
        userId = intent.getStringExtra("userId");
        mAchieveName = intent.getStringExtra("mAchieveName");
        mActualName = intent.getStringExtra("mActualName");
        imageUrl = intent.getStringExtra("imageUrl");
        detailednessType = intent.getIntExtra("detailednessType", 1);
        getMaxValue = intent.getIntExtra("getMaxValue", 1);
        getNum = intent.getIntExtra("getNum", 1);
    }

    private void initView() {
        getWindow().getDecorView().setBackgroundResource(R.color.half);
        imageBlack = findViewById(R.id.content_image_black);
        imageView = findViewById(R.id.image);
        textView = findViewById(R.id.text_view);
        textTitle = findViewById(R.id.text_title);
        myProgressBar = findViewById(R.id.my_progress_bar);
        textNumber = findViewById(R.id.text_number);
        button = findViewById(R.id.button);
        imageBlack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        if (getNum <= 5 && getMaxValue >= 90 && getMaxValue <= 180 ) {
            myProgressBar.setTotalAndCurrentCount(getMaxValue, getNum * 5);
        } else if(getNum <= 5 && getMaxValue >= 180 ){
            myProgressBar.setTotalAndCurrentCount(getMaxValue, getNum  * 10);
        }else {
            myProgressBar.setTotalAndCurrentCount(getMaxValue, getNum);
        }
        textView.setText(mActualName);
        textTitle.setText(mAchieveName);
        switch (detailednessType) {
            case 1:
                button.setVisibility(View.GONE);
                textNumber.setText("登录" + getNum + "/" + getMaxValue);
                break;
            case 2:
                textNumber.setText("题库" + getNum + "/" + getMaxValue);
                break;
            case 3:
                String num = Util.getChessLevel(getNum);
                String maxValue = Util.getChessLevel(getMaxValue);
                textNumber.setText("等级" + num + "/" + maxValue);
                break;
            case 4:
                textNumber.setText("游戏" + getNum + "/" + getMaxValue);
                break;
            case 5:
                textNumber.setText("对弈" + getNum + "/" + getMaxValue);
                break;
            default:
                break;
        }
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (detailednessType) {
                    case 3:
                        Intent intent = new Intent(ContentOfAchievementActivity.this, OnlineGameActivity.class);
                        startActivity(intent);
                        finish();
                        break;
                    case 4:
                        try {
                            Intent mIntent = new Intent(ContentOfAchievementActivity.this, JsWebViewActivity.class);
                            mIntent.putExtra("uid", userId);
                            String name = java.net.URLEncoder.encode(userId, "utf-8");
                            String name1 = java.net.URLEncoder.encode(name, "utf-8");
                            mIntent.putExtra("username", name1);
                            mIntent.putExtra("prefix", ContactUrl.GAME);
                            startActivity(mIntent);
                            GreenDaoManager.closeConnection();
                            finish();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    default:
                        break;

                }
            }
        });

        StringBuffer imageUri1 = new StringBuffer();
        imageUri1.append(ContactUrl.BASE_PATH).append("/gobangteach/gobangPc/").append(imageUrl);
        Glide.with(ContentOfAchievementActivity.this)
                .load(imageUri1.toString())
                .asBitmap()
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(imageView);
    }

    public static void openActivity(Context context, String touken, String userId, int detailednessType, String mAchieveName, String mActualName, String imageUrl, int getMaxValue, int getNum) {
        Bundle bundle = new Bundle();
        bundle.putString("touken", touken);
        bundle.putString("userId", userId);
        bundle.putString("mAchieveName", mAchieveName);
        bundle.putString("mActualName", mActualName);
        bundle.putString("imageUrl", imageUrl);
        bundle.putInt("detailednessType", detailednessType);
        bundle.putInt("getMaxValue", getMaxValue);
        bundle.putInt("getNum", getNum);
        JumpIntent.jump((Activity) context, ContentOfAchievementActivity.class, bundle);
    }
}

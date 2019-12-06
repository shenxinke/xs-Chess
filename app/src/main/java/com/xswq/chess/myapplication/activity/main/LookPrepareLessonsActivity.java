package com.xswq.chess.myapplication.activity.main;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.xswq.chess.myapplication.R;
import com.xswq.chess.myapplication.adapter.LookPrepareLessonsPagerAdapter;
import com.xswq.chess.myapplication.base.BaseCompatActivity;
import com.xswq.chess.myapplication.base.Config;
import com.xswq.chess.myapplication.bean.LookPrepareLessonsBean;
import com.xswq.chess.myapplication.customview.CustomRoundAngleImageView;
import com.xswq.chess.myapplication.http.HttpCallBackLisener;
import com.xswq.chess.myapplication.http.httpparameterapi.PrepareLessonsByIdApi;
import com.xswq.chess.myapplication.http.retrofitHttp.RetrofitManager;
import com.xswq.chess.myapplication.http.subscriber.PrepareLessonsByIdSub;
import com.xswq.chess.myapplication.utils.JumpIntent;
import com.xswq.chess.myapplication.utils.Util;
import com.xswq.chess.myapplication.widget.ShowImagesDialog;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class LookPrepareLessonsActivity extends BaseCompatActivity implements HttpCallBackLisener, ViewPager.OnPageChangeListener {
    private TextView login_back;
    private ViewPager viewPager;
    private String userId;
    private String touken;
    private String id;
    private int prepareLessinStage;
    private List<LookPrepareLessonsBean.DataBean> dataList;
    private List<ImageView> mImageList;
    private List<String> imageString;
    private TextView titleName;
    private TextView titleText;
    private ImageView largerMap;


    @Override
    protected int getLayoutId() {
        return R.layout.look_prepare_layout;
    }

    @Override
    protected void initView() {
        getDeviceDensity();
        Intent intent = getIntent();
        userId = intent.getStringExtra("userId");
        touken = intent.getStringExtra("touken");
        prepareLessinStage = intent.getIntExtra("prepareLessinStage", 1);
        id = intent.getStringExtra("id");
        getWindow().getDecorView().setBackgroundResource(R.mipmap.prepare_lessons_backgroud);
        viewPager = findViewById(R.id.look_prepare_pager);
        titleName = findViewById(R.id.title_name);
        largerMap = findViewById(R.id.larger_map);
        titleText = findViewById(R.id.title_text);
        viewPager.addOnPageChangeListener(this);
        login_back = findViewById(R.id.login_back);
        login_back.setVisibility(View.VISIBLE);
        TextView login_titles = findViewById(R.id.login_titles);
        login_titles.setText("查看备课");
        login_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void loadData() {
        PrepareLessonsByIdApi prepareLessonsApi = new PrepareLessonsByIdApi(this);
        prepareLessonsApi.setLessonsId(id);
        prepareLessonsApi.setToken(touken);
        prepareLessonsApi.setUid(userId);
        PrepareLessonsByIdSub mGuideVideoSub = new PrepareLessonsByIdSub(prepareLessonsApi);
        RetrofitManager.getRetrofitInstance().handleHttp(mGuideVideoSub, prepareLessonsApi);
    }

    public static void openActivity(Context context, String id, String touken, String userId, int prepareLessinStage) {
        Bundle bundle = new Bundle();
        bundle.putString("id", id);
        bundle.putString("touken", touken);
        bundle.putString("userId", userId);
        bundle.putInt("prepareLessinStage", prepareLessinStage);
        JumpIntent.jump((Activity) context, LookPrepareLessonsActivity.class, bundle);
    }

    private void getBannerData() {
        mImageList = new ArrayList<>();
        imageString = new ArrayList<>();
        CustomRoundAngleImageView iv;
        for (int i = 0; i < dataList.size(); i++) {
            iv = new CustomRoundAngleImageView(LookPrepareLessonsActivity.this);
            iv.setScaleType(ImageView.ScaleType.FIT_XY);
            Glide.with(LookPrepareLessonsActivity.this)
                    .load(Util.signString(dataList.get(i).getHandoutsImg()))
                    .asBitmap()
                    .error(R.mipmap.default_xswq)
                    .placeholder(R.mipmap.default_xswq)
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .into(iv);
            mImageList.add(iv);
            imageString.add(dataList.get(i).getHandoutsImg());
        }
    }

    @Override
    public void onNext(Object t, String method) {
        String toString = String.valueOf(t);
        Gson gson = new Gson();
        LookPrepareLessonsBean lookPrepareLessonsBean = gson.fromJson(toString, LookPrepareLessonsBean.class);
        dataList = lookPrepareLessonsBean.getData();
        getBannerData();
        LookPrepareLessonsPagerAdapter mAdapter = new LookPrepareLessonsPagerAdapter(mImageList, viewPager);
        viewPager.setAdapter(mAdapter);//第二步：设置viewpager适配器
        setFirstLocation();
    }

    @Override
    public void onError(Throwable e) {

    }

    private void setFirstLocation() {
        titleName.setText("第 1 步: 导入");
        titleText.setText(dataList.get(0).getMyHandouts());
        viewPager.setCurrentItem(0);
    }

    @Override
    public void onPageScrolled(final int position, float positionOffset, int positionOffsetPixels) {
        // 把当前选中的点给切换了, 还有描述信息也切换
        switch (position) {
            case 0:
                titleName.setText("第" + (position + 1) + "步: 导入");
                break;
            case 1:
                titleName.setText("第" + (position + 1) + "步: 分组");
                break;
            case 2:
                if (prepareLessinStage == 1) {
                    titleName.setText("第" + (position + 1) + "步: 自学");
                } else {
                    titleName.setText("第" + (position + 1) + "步: 练兵");
                }
                break;
            case 3:
                titleName.setText("第" + (position + 1) + "步: 导学");
                break;
            case 4:
                titleName.setText("第" + (position + 1) + "步: 试练");
                break;
            case 5:
                if (prepareLessinStage == 1) {
                    titleName.setText("第" + (position + 1) + "步: 摆题");
                } else {
                    titleName.setText("第" + (position + 1) + "步: 对弈");
                }
                break;
            case 6:
                titleName.setText("第" + (position + 1) + "步: 小结");
                break;
            default:
                break;
        }
        titleText.setText(dataList.get(position).getMyHandouts());
        largerMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               new ShowImagesDialog(LookPrepareLessonsActivity.this,imageString).show();
            }
        });
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
    protected void getDeviceDensity() {
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        Config.EXACT_SCREEN_HEIGHT = metrics.heightPixels;
        Config.EXACT_SCREEN_WIDTH = metrics.widthPixels;
    }
}

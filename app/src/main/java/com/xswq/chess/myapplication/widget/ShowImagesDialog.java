package com.xswq.chess.myapplication.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import com.xswq.chess.myapplication.R;
import com.xswq.chess.myapplication.base.Config;

import java.util.ArrayList;
import java.util.List;

import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by Administrator on 2017/5/3.
 * 嵌套了viewpager的图片浏览
 */

public class ShowImagesDialog extends Dialog {

    private View mView ;
    private Context mContext;
    private TextView mIndexText;
    private List<String> mImgUrls;
    private List<String> mTitles;
    private List<View> mViews;
    private ShowImagesAdapter mAdapter;
    private ShowImagesViewPager mViewPager;

    public ShowImagesDialog(@NonNull Context context, List<String> imgUrls) {
        super(context, R.style.transparentBgDialog);
        this.mContext = context;
        this.mImgUrls = imgUrls;
        initView();
        initData();
    }

    private void initView() {
        try{
            mView = View.inflate(mContext, R.layout.dialog_images_brower, null);
            mViewPager = mView.findViewById(R.id.vp_images);
            mIndexText = mView.findViewById(R.id.tv_image_index);
            mTitles = new ArrayList<>();
            mViews = new ArrayList<>();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(mView);
        Window window = getWindow();
        WindowManager.LayoutParams wl = window.getAttributes();
        wl.x = 0;
        wl.y = 0;
        wl.height = Config.EXACT_SCREEN_HEIGHT;
        wl.width = Config.EXACT_SCREEN_WIDTH;
        wl.gravity = Gravity.CENTER;
        window.setAttributes(wl);
    }

    private void initData() {
        for (int i = 0; i < mImgUrls.size(); i++) {
            final PhotoView photoView = new uk.co.senab.photoview.PhotoView(mContext);
            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            photoView.setLayoutParams(layoutParams);
            String url = mImgUrls.get(i);
            Glide.with(mContext)
                    .load(url)
                    .placeholder(R.mipmap.default_xswq)
                    .error(R.mipmap.default_xswq)
                    .into(new SimpleTarget<GlideDrawable>() {
                @Override
                public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                    photoView.setImageDrawable(resource);
                }
            });
            mViews.add(photoView);
            mTitles.add(i + "");

            photoView.setOnPhotoTapListener(new PhotoViewAttacher.OnPhotoTapListener() {
                @Override
                public void onPhotoTap(View view, float x, float y) {
                    dismiss();
                }
            });

            photoView.setOnViewTapListener(new PhotoViewAttacher.OnViewTapListener() {
                @Override
                public void onViewTap(View view, float x, float y) {
                    dismiss();
                }
            });
        }

        mAdapter = new ShowImagesAdapter(mViews, mTitles);
        mViewPager.setAdapter(mAdapter);
        mIndexText.setText(1 + "/" + mImgUrls.size());
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mIndexText.setText(position + 1 + "/" + mImgUrls.size());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

}

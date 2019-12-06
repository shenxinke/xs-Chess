package com.xswq.chess.myapplication.utils;


import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.xswq.chess.myapplication.R;
import com.xswq.chess.myapplication.application.XSWQApplication;

public class ImageLoader {

    private static RequestManager manager;

    private static RequestManager getImageManager() {
        if (manager == null) {
            manager = Glide.with(XSWQApplication.getInstance());
        }
        return manager;
    }


    /**
     * 获取RequestManager对象
     *
     * @return
     */
    public RequestManager getManager() {
        return manager;
    }

    /**
     * 加载普通图片
     *
     * @param object
     * @param view
     */
    public static void loadImage(Object object, ImageView view) {
        getImageManager().load(object)
                .error(R.color.transparent)
                .into(view);
    }

    public void loadErroImage(Object object, int error, ImageView view) {
        getImageManager().load(object)
                .error(error)
                .placeholder(error)
                .into(view);
    }

    /**
     * 加载圆形图片
     *
     * @param object
     * @param view
     */
    public static void loadHeadImage(Object object, ImageView view) {
        getImageManager().load(object)
                .error(R.mipmap.tourist_head)
                .transform(new GlideCircleTransform(XSWQApplication.getInstance()))
                .into(view);
    }
}


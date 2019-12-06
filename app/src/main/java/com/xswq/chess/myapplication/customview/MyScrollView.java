package com.xswq.chess.myapplication.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

/**
 * 自定义ScrollView  拦截ScrollView的滑动监听
 */
public class MyScrollView extends ScrollView {
    public MyScrollView(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
    }

    public MyScrollView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        // TODO Auto-generated constructor stub
    }

    //重写该方法，让ScrollView不进行事件拦截
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        return false;
    }

}

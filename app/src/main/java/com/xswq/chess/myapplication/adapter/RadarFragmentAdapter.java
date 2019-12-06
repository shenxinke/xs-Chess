package com.xswq.chess.myapplication.adapter;

import android.support.v4.view.PagerAdapter;

import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import java.util.List;

public class RadarFragmentAdapter extends PagerAdapter {

    private List<WebView> lists;

    public RadarFragmentAdapter() {

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public void finishUpdate(ViewGroup container) {
    }

    @Override
    public int getCount() {
        return getLists().size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        WebView view = getLists().get(position);
        view.loadUrl("javascript:createChart('radar',[89,78,77]);");
        container.addView(view);
        return view;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        // TODO Auto-generated method stub
        return view == object;
    }

    public List<WebView> getLists() {
        return lists;
    }

    public void setLists(List<WebView> lists) {
        this.lists = lists;


    }
}
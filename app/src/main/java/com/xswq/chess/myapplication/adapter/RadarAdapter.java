package com.xswq.chess.myapplication.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.xswq.chess.myapplication.fragment.GrowthDataFragment;
import com.xswq.chess.myapplication.fragment.RadarFragment;

public class RadarAdapter extends FragmentPagerAdapter {

    private Context mContext;
    private String[] titles ;
    private String mUserId;

    public RadarAdapter(FragmentManager fm, Context mContext, String [] listTitle,String userId) {
        super(fm);
        this.mContext = mContext;
        this.titles = listTitle;
        this.mUserId = userId;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 1) {
            return GrowthDataFragment.newInstance(titles[0], mUserId) ;
        }
        return RadarFragment.newInstance();
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {

        return  titles[position];
    }


}

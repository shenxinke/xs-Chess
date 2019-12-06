package com.xswq.chess.myapplication.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import java.util.List;


public class SwitchMainAdapter extends FragmentStatePagerAdapter {

    private List<Fragment> mListFragment;
    private String [] mTitles;
    public SwitchMainAdapter(FragmentManager fm, String [] titles, List<Fragment> listFragment) {
        super(fm);
        mTitles = titles;
        mListFragment = listFragment;
    }


    @Override
    public Fragment getItem(int position) {

        return mListFragment.get(position);
    }

    @Override
    public int getCount() {
        return mListFragment.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

    }
}



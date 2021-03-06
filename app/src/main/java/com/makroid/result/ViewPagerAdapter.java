package com.makroid.result;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;


public class ViewPagerAdapter extends FragmentPagerAdapter {
    private final ArrayList<Fragment> mFragments;
    private final List<String> mFragmentTitles = new ArrayList<>();

    ViewPagerAdapter(FragmentManager fm, ArrayList<Fragment> list) {
        super(fm);
        mFragments = list;
    }

    void addFragment(Fragment fragment, String title) {
        mFragments.add(fragment);
        mFragmentTitles.add(title);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0)
            return "Information";
        else
            return position + "Semester";
    }
}
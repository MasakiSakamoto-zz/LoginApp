package com.masakisakamoto.loginapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import static com.masakisakamoto.loginapp.R.string.articles;

/**
 * Created by masakisakamoto on 8/1/17.
 */

public class ArticlePagerAdapter extends FragmentPagerAdapter {

    public ArticlePagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @Override
    public Fragment getItem(int position) {
        return ArticlesFragment.newInstance(String.valueOf(position + 1));
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "GROUP ID = " + (position + 1);
    }
}

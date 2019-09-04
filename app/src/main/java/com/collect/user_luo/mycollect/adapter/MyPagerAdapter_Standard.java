package com.collect.user_luo.mycollect.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * 说明：普通版的Viewpage
 * 作者：User_luo on 2018/4/19 10:16
 * 邮箱：424533553@qq.com
 */
public class MyPagerAdapter_Standard extends FragmentPagerAdapter {

    private List<Fragment> lrcs;

    public MyPagerAdapter_Standard(FragmentManager fm, List<Fragment> lrcs) {
        super(fm);
        this.lrcs = lrcs;
    }

    @Override
    public Fragment getItem(int position) {
        return lrcs.get(position);
    }

    @Override
    public int getCount() {
        return lrcs==null?0:lrcs.size();
    }
}

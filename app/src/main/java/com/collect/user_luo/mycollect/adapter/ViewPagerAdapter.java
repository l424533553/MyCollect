package com.collect.user_luo.mycollect.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * 说明：
 * 作者：User_luo on 2018/5/30 17:56
 * 邮箱：424533553@qq.com
 */
public class ViewPagerAdapter extends    FragmentPagerAdapter {
    private FragmentManager mfragmentManager;
    private List<Fragment> mlist;

    public ViewPagerAdapter(FragmentManager fm, List<Fragment> list) {
        super(fm);
        this.mlist = list;
    }

    @Override
    public Fragment getItem(int arg0) {
        return mlist.get(arg0);//显示第几个页面
    }

    @Override
    public int getCount() {
        return mlist.size();//有几个页面
    }
}

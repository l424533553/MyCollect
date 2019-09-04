package com.collect.user_luo.mycollect.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import java.util.ArrayList;
import java.util.List;

/**
 * 说明：进阶版的ViewPageAdapter
 * 1.使用ViewPager+Fragment的方式进行页面切换， 但是当前的Fragmen只关联左右两个Fragment,第三个会被销毁。
 * 但是本Adapter进行了判断处理，不会销毁Fragment,但是Fragment数量多了之后可能存在问题，不建议多数量的Fragment使用
 * 作者：User_luo on 2018/4/19 10:16
 * 邮箱：424533553@qq.com
 */
public class MyPagerAdapter_Advance extends BaseFragmentPagerAdapter {
    private List<Fragment> lrcs;

    public MyPagerAdapter_Advance(FragmentManager fm, List<Fragment> lrcs) {
        super(fm);
        this.lrcs = lrcs;
    }

    public void addDatas(List<Fragment> lrcs) {
        this.lrcs.addAll(lrcs);
        notifyDataSetChanged();
    }

    @Override
    public Fragment getItem(int position) {
        return lrcs.get(position);
    }

    //除了给定位置，其他位置的Fragment进行刷新
    public void notifyChangeWithoutPosition(int position) {
        String valueP = tags.valueAt(position);
        tags.clear();
        tags.put(position, valueP);
        notifyDataSetChanged();
    }

    @Override
    public void notifyFragmentByPosition(int position) {
        super.notifyFragmentByPosition(position);
    }

    @Override
    public List<Fragment> getFragments() {
        return super.getFragments();
    }

    @Override
    public Fragment getFragmentByPosition(int position) {
        return super.getFragmentByPosition(position);
    }

    @Override
    public int getCount() {
        return lrcs == null ? 0 : lrcs.size();
    }
}

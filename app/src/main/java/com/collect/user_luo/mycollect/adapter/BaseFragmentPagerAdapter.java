package com.collect.user_luo.mycollect.adapter;



import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.SparseArray;
import android.view.ViewGroup;

import java.util.List;

/**
 * 说明：Viewpage的抽象类
 * 作者：User_luo on 2018/4/19 10:11
 * 邮箱：424533553@qq.com
 */
public abstract class BaseFragmentPagerAdapter extends FragmentPagerAdapter {

    private FragmentManager mFragmentManager;

    //保存每个Fragment的Tag，刷新页面的依据
    SparseArray<String> tags = new SparseArray<>();

    public BaseFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
        mFragmentManager = fm;
    }

    @NonNull
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        //得到缓存的fragment
        Fragment fragment = (Fragment) super.instantiateItem(container, position);
        String tag = fragment.getTag();
        //保存每个Fragment的Tag
        tags.put(position, tag);
        return fragment;
    }

    //拿到指定位置的Fragment
    public Fragment getFragmentByPosition(int position) {
        return mFragmentManager.findFragmentByTag(tags.get(position));
    }

    public List<Fragment> getFragments(){
        return mFragmentManager.getFragments();
    }

    //刷新指定位置的Fragment
    public void notifyFragmentByPosition(int position) {
        tags.removeAt(position);
        notifyDataSetChanged();
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        Fragment fragment = (Fragment) object;
        /* 如果Item对应的Tag存在，则不进行刷新 */
        if (tags.indexOfValue(fragment.getTag()) > -1) {
            return super.getItemPosition(object);
        }
        return POSITION_NONE;
    }

}

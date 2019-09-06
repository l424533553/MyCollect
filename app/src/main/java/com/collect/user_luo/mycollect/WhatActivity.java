package com.collect.user_luo.mycollect;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.collect.user_luo.R;
import com.collect.user_luo.mycollect.activity.eventbus.LiveEventBusFragment;
import com.collect.user_luo.mycollect.activity.refresh.smartfragment.SmartRefreshFragment;
import com.collect.user_luo.mycollect.activity.refresh.smartfragment.SwipeRefreshFragment;
import com.collect.user_luo.mycollect.config.IConstants;
import com.collect.user_luo.mycollect.config.IDataConstants;

/**
 * 说明：各个跳转界面
 * 作者：User_luo on 2018/4/18 11:10
 * 邮箱：424533553@qq.com
 */
public class WhatActivity extends FragmentActivity implements IConstants, IDataConstants {
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_what);
        fragmentManager = getSupportFragmentManager();
        init();

    }

    /**
     * 测试数据
     */
    private void init() {
        String sourceType = getIntent().getStringExtra(JUMP_FROM_SOURCE);
        String type = getIntent().getStringExtra(JUMP_FROM_SOURCE_REFRESH);
        switch (sourceType) {
            case JUMP_FROM_SOURCE_REFRESH: //从刷新Activity中跳转而来
                decideJumpFragment(type);
                break;
            case JUMP_FROM_SOURCE_EVENTBUS: //从刷新Activity中跳转而来
                type = sourceType;
                decideJumpFragment(type);
                break;
            default:
                break;
        }
    }

    /**
     * 其他类型的可以通用
     * <p>
     * 决定到底调到那个Fragment中去
     */
    private void decideJumpFragment(String type) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        Fragment fragment = null;
        switch (type) {
            case REFRESH_TYPE_SWIPE: //官方默认的刷新方式
                fragment = new SwipeRefreshFragment();
                break;
            case REFRESH_TYPE_SMART: //官方默认的刷新方式
                fragment = new SmartRefreshFragment();
                break;
            case JUMP_FROM_SOURCE_EVENTBUS: // LiveEventBus Fragment
                fragment = new LiveEventBusFragment();
                break;
            default:
                break;
        }
        if (fragment != null) {
            transaction.add(R.id.fragmentLayout, fragment);
            transaction.commit();
        }
    }


}

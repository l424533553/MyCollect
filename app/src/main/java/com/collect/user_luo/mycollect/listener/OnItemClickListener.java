package com.collect.user_luo.mycollect.listener;

import android.view.View;

/**
 * 说明： 点击监听
 * 作者：User_luo on 2018/5/4 16:59
 * 邮箱：424533553@qq.com
 */
public interface OnItemClickListener{
    //条目被点击时触发的回调
    void onItemClick(View view, int position);
    //长按时触发的回调
    void onLongClick(View view,int position);
}
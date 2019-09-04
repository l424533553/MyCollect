package com.collect.user_luo.mycollect.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * 说明：自定义RecyclerView 实现图片相册查看效果
 * 作者：User_luo on 2018/5/4 16:06
 * 邮箱：424533553@qq.com
 */
public class MyRecyclerView extends RecyclerView {
    private onItemScrollChangeListener mItemScrollChangeListener;
    private View mCurrentView;
    public MyRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    // 回调的接口
    public interface onItemScrollChangeListener {
        void onChange(View view, int position);
    }

    // 对外暴露设置滚动接口的方法
    public void setOnItemScrollChangeListener(onItemScrollChangeListener itemScrollChangeListener) {
        this.mItemScrollChangeListener = itemScrollChangeListener;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        // TODO Auto-generated method stub
        super.onLayout(changed, l, t, r, b);
        mCurrentView = getChildAt(0);

        if (mItemScrollChangeListener != null) {
            mItemScrollChangeListener.onChange(mCurrentView, getChildAdapterPosition(mCurrentView));
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent e) {
        if (e.getAction() == MotionEvent.ACTION_MOVE) {
            mCurrentView = getChildAt(0);
            if (mItemScrollChangeListener != null) {
                mItemScrollChangeListener.onChange(mCurrentView, getChildAdapterPosition(mCurrentView));
            }
        }
        return super.onTouchEvent(e);
    }

}

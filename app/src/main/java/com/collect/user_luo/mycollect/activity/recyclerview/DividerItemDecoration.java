package com.collect.user_luo.mycollect.activity.recyclerview;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * 说明：
 * 作者：User_luo on 2018/5/4 16:14
 * 邮箱：424533553@qq.com
 */
public class DividerItemDecoration extends RecyclerView.ItemDecoration {

    public DividerItemDecoration() {
        super();
        // TODO Auto-generated constructor stub
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                               RecyclerView.State state) {
        // TODO Auto-generated method stub
        super.getItemOffsets(outRect, view, parent, state);
    }

    @Override
    @Deprecated
    public void onDraw(Canvas c, RecyclerView parent) {
        // TODO Auto-generated method stub
        super.onDraw(c, parent);
    }

}

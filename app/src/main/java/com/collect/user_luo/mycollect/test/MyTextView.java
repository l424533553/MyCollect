package com.collect.user_luo.mycollect.test;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * 作者：罗发新
 * 时间：2019/1/10 0010    15:41
 * 邮件：424533553@qq.com
 * 说明：自定义的控件，使用功能。
 */
public class MyTextView extends View {
    public MyTextView(Context context) {
        super(context);
    }

    public MyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

//    public MyTextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
//        super(context, attrs, defStyleAttr, defStyleRes);
//    }

    Paint paint = new Paint();

    /**
     * 主题内容绘制
     *
     * @param canvas 绘制   Canvas 主要类
     *               内容裁切和 几何变换
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(114, 116, 88, paint);

    }


}

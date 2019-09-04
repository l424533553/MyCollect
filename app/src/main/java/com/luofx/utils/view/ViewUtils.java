package com.luofx.utils.view;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.DisplayMetrics;

/**
 * author: luofaxin
 * date： 2018/11/6 0006.
 * email:424533553@qq.com
 * describe:
 */
public class ViewUtils {

    /**
     * 得到应用程序的包名
     */
    public  String getPackageName(Context context) {
        return context.getPackageName();
    }


    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public int dip2px(Context context,float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public int px2dip(Context context,float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    //获取屏幕宽度:
    public int getScreenWidthPixels(Activity activity) {
        //获取屏幕的宽高的像素
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm.widthPixels;
    }

    //获取屏幕宽度:
    public int getScreenHighPixels(Activity activity) {
        //获取屏幕的高的像素
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm.heightPixels;
    }

    //判断处理快击  按钮事件
    private static long lastClickTime;
    public synchronized static boolean isFastClick() {
        boolean flag = false;
        long curClickTime = System.currentTimeMillis();
        if ((curClickTime - lastClickTime) >= 1000) {
            flag = true;
        }
        lastClickTime = curClickTime;
        return flag;
    }

    /**
     * get toolbar height   获取控件的 toolbar高度
     * @param context  上下文
     * @return     返回toolbar的高度
     */
    public  int getToolbarHeight(Context context) {
        final TypedArray styledAttributes = context.getTheme().obtainStyledAttributes(
                new int[]{android.R.attr.actionBarSize});
        int toolbarHeight = (int) styledAttributes.getDimension(0, 0);
        styledAttributes.recycle();
        return toolbarHeight;
    }

}

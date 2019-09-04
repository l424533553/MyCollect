package com.luofx.base;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.WindowManager;

import com.collect.user_luo.mycollect.R;
import com.collect.user_luo.mycollect.utils.SharedPreferencesUtils;

import static android.graphics.Paint.Align.LEFT;
import static android.graphics.PixelFormat.TRANSLUCENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;
import static android.view.WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
import static android.view.WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL;
import static android.view.WindowManager.LayoutParams.TYPE_SYSTEM_ERROR;
@SuppressLint("Registered")
public class BaseActivity extends AppCompatActivity implements IBaseConstants {
    /**
     * 返回点击  退出时间
     */
    protected long backTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * 超过两秒 才能进行下一次点击操作
     */
    protected  boolean isClickAble(){
        if (System.currentTimeMillis() - backTime > BACK_TIME_DEFAULT) {
            backTime = System.currentTimeMillis();
            return true;
        }
        return  false;
    }

    /**
     * 测试开发
     */
    private void test(){

//        wManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
//        mParams = new WindowManager.LayoutParams(WindowManager.LayoutParams.TYPE_SYSTEM_ERROR,
//                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE*, PixelFormat.TRANSPARENT);
//        mParams.type = WindowManager.LayoutParams.TYPE_SYSTEM_ERROR*;
//        // 系统提示window
//        mParams.format = PixelFormat.TRANSLUCENT;// 支持透明 //
//        mParams.format = PixelFormat.RGBA_8888;
//        mParams.flags |= WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
//        //窗口的透明度
//        LayoutInflater layoutInflater = LayoutInflater.from(getApplicationContext());
//        windowView = (FloatButtonLayout) layoutInflater.inflate(R.layout.float_button_layout, null);
//        wManager.addView(windowView, mParams);// 添加窗口

    }

}

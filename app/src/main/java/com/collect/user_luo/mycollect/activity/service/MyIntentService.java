package com.collect.user_luo.mycollect.activity.service;

import android.app.IntentService;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * 说明： 可做耗时操作的服务
 * 作者：User_luo on 2018/5/4 14:48
 * 邮箱：424533553@qq.com
 */
public class MyIntentService extends IntentService {


    /**
     * 一定要实现不带参数的构造函数
     */
    public MyIntentService() {
        super("MyIntentService");
    }


    @Override
    public void onCreate() {
        super.onCreate();
    }

    /**
     * 软件 启动
     *
     * @param intent 意图
     * @return 返回bind
     */
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return super.onBind(intent);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }


    @Override
    // 这个方法已经在子线程中运行了。
    protected void onHandleIntent(Intent intent) {

        Log.d("MyIntentService", "Thread id is" + Thread.currentThread().getId());

    }

    @Override
    // 当子线程中的代码执行完，就会调用它。
    public void onDestroy() {
        super.onDestroy();
        Log.d("MyIntentService", "onDestroy executed");
    }
}
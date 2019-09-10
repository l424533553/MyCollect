package com.collect.user_luo.mycollect.activity.service;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;

import com.collect.user_luo.R;
import com.collect.user_luo.mycollect.MainActivity;
import com.xuanyuan.library.utils.log.MyLog;

/**
 * 说明：Service
 * 1.sartService  （stopService或stopSelf） 和bindService （绑定了生命周期） 两种方法  ，有区别的
 * 内存不足 或 强制kill  也可以杀死Service （UnbindService要指明需要断开哪个连接）
 * <p>
 * 生命周期  1. onCreate>> onStartCommand>>onDestory   onCreate>>onBind>>OnUnbind>>onDestory    Service 是单例的
 * onCreate 调用一次（onbind不调用），onstartCommand可调用多次（bind一次都不调用）
 * <p>
 * 作者：User_luo on 2018/5/4 09:32
 * 邮箱：424533553@qq.com
 */


//实现进度监控(两个要素)：
//ServicerConnection:用与绑定客户端和Service->见MainActivity.java有说明
//IBinder:在android中用于远程操作对象的一个基本接口
//因为IBinder强制要求我们实现一些方法，而Binder类是给所有强制方法实现了一个空方法
//在开发中我们会自己写一个内部类，继承Binder类,在里边写自己的方法
public class MyService extends Service {

    private int i;

    public MyService() {
    }

    /**
     * 创建
     */
    @Override
    public void onCreate() {
        super.onCreate();
        showIntent();

        MyLog.log("服务创建了");
        //开启一个线程，模拟耗时任务
        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    for (i = 0; i < 100; i++) {
                        sleep(1000);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    private void showIntent() {
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pi = PendingIntent.getActivity(this, 0, intent, 0);
        Notification notification = new NotificationCompat.Builder(this)
                .setContentTitle("This is content title")
                .setContentText("This is content text")
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                .setContentIntent(pi)
                .build();
        startForeground(1, notification);
    }

    /**
     * 启动
     *
     * @param intent
     * @param flags
     * @param startId
     * @return
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        MyLog.log("服务启动了");
        return super.onStartCommand(intent, flags, startId);
    }

    /**
     * 绑定
     *
     * @param intent
     * @return
     */
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        MyLog.log("服务绑定了");
        return new MyBinder();

    }

    class MyBinder extends Binder {
        public int getProcess() {
            return i;
        }
    }

    /**
     * 解绑
     *
     * @param intent
     * @return
     */
    @Override
    public boolean onUnbind(Intent intent) {
        MyLog.log("服务解绑了");
        return super.onUnbind(intent);
    }

    /**
     * 销毁
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        MyLog.log("服务销毁了");
    }

}

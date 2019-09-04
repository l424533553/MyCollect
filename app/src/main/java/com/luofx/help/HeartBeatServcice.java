package com.luofx.help;

import android.app.Service;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;

import com.alibaba.fastjson.JSON;
import com.android.volley.VolleyError;
import com.luofx.entity.ResultInfo;
import com.luofx.listener.VolleyListener;
import com.luofx.utils.log.MyLog;

import org.json.JSONObject;

/**
 * author: luofaxin
 * date： 2018/9/11 0011
 * email:424533553@qq.com
 * describe:
 */
public class HeartBeatServcice extends Service implements VolleyListener {
    private boolean isLooper = true; //是否需要循环
    /**
     * 定义我们自己写的Binder对象
     */
    private int marketid;   // 市场编号
    private int terid;     // 秤/编号
    private int NOTIFY = 555;

    public void setMarketid(int marketid) {
        this.marketid = marketid;
    }

    public void setTerid(int terid) {
        this.terid = terid;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new MyBinder();
    }

    private Handler handler;

    private void initHandler() {
        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                if (msg.what == NOTIFY) {
                    //TODO 定时 执行任务

                }
                return false;
            }
        });
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initHandler();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (isLooper) {
                    try {
                        Thread.sleep(120000);//2分钟
                        handler.sendEmptyMessage(NOTIFY);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }


    @Override
    public void onDestroy() {
        isLooper = false;
        super.onDestroy();

    }

    @Override
    public void unbindService(ServiceConnection conn) {
        isLooper = false;
        super.unbindService(conn);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        isLooper = false;
        return super.onUnbind(intent);
    }

    @Override
    public void onResponse(VolleyError volleyError, int flag) {
        MyLog.myInfo("错误" + volleyError.getMessage());

    }

    private boolean isDisable;

    @Override
    public void onResponse(JSONObject jsonObject, int flag) {
        MyLog.myInfo("成功" + jsonObject.toString());
        ResultInfo resultInfo = JSON.parseObject(jsonObject.toString(), ResultInfo.class);
        if (resultInfo.getStatus() == 0) {
            if ("1".equals(resultInfo.getData())) {//禁用
//                MyToast.c
                //TODO
                if (!isDisable) {
                    //TODO 启动新的界面
//                    Intent intent = new Intent(this, LockActivity.class);
//                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    startActivity(intent);
                }
                isDisable = true;
            } else {
                if (isDisable) {// 恢复操作
                    //TODO 发送新的 广播
//                    Intent intent = new Intent();
//                    intent.setAction("fafafstrtyhgsfghdfyry5ry");
//                    sendBroadcast(intent);
                }
                isDisable = false;
            }
        }
    }

    /**
     * 自定义的内部类,继承Binder
     * 在该内部类里,我们写对服务进行操作的方法
     * 通过该对象实现对服务的操作,并从服务中获取数据
     */
    public class MyBinder extends Binder {
        public HeartBeatServcice getService() {
            return HeartBeatServcice.this;
        }
    }

}

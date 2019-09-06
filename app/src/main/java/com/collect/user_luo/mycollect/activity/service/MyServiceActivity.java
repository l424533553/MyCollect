package com.collect.user_luo.mycollect.activity.service;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.collect.user_luo.mycollect.R;

public class MyServiceActivity extends AppCompatActivity {


    /**
     * 是否绑定了服务
     */
    private boolean isBindService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_service);
    }


    private ServiceConnection conn = new ServiceConnection() {
        //当客户端正常连接着Service时，执行服务的绑定操作会被调用
        //如果没有ServiceConnection，会出现绑定后解绑，无法再次绑定的情况
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d("TAG", "hello,bind again");
            //强制转换
            MyService.MyBinder mb = (MyService.MyBinder) service;
            int step = mb.getProcess();
            Mylog.log("当前进度为：" + step);
        }

        //当客户端和服务的连接丢失了
        @Override
        public void onServiceDisconnected(ComponentName name) {




        }
    };

    public void onClick(View view) {

        //start 与 stop为一组
        //bind 与 unbind为一组
        switch (view.getId()) {
            case R.id.start:
                //如果服务已经创建，重复启动不会重新创建，操作的是同一个服务，除非先销毁
                Intent it = new Intent(this, MyService.class);
                startService(it);
                break;

            case R.id.stop:
                //这里虽然是两个intent，但确实针对同一个对象
                Intent it1 = new Intent(this, MyService.class);
                stopService(it1);
                break;

            case R.id.bind:
                //绑定服务：最大作用是对Service执行的任务进行监控，如下载到哪了，音乐播放到哪了
                Intent it2 = new Intent(this, MyService.class);
                bindService(it2, conn, BIND_AUTO_CREATE);
                isBindService = true;
                break;

            case R.id.unbind:
                //要指明解绑哪个连接，所以conn要设置为全局
                /**
                 * 没有绑定服务解绑容易报错，退出
                 */


                try {
                    unbindService(conn);
                    isBindService = false;
                } catch (Exception e) {
                    Mylog.log(e.getMessage()+"原因"+e.getCause());
                    e.printStackTrace();
                }
                break;
        }

    }
}

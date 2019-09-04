package com.luofx.utils.system;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.net.wifi.WifiManager;

import java.util.List;

/**
 * author: luofaxin
 * date： 2018/11/1 0001.
 * email:424533553@qq.com
 * describe:
 */
public class SysUtils {

    /**
     * 获取  设备的唯一标识 MAC
     *
     * @param context 上下文
     * @return 唯一标识 mac
     */
    @SuppressLint("HardwareIds")
    public String getIMEI(Context context) {
        // 94:a1:a2:a4:70:66
        WifiManager wm = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        String mac = "";
        if (wm != null) {
            mac = wm.getConnectionInfo().getMacAddress();
        }
//        return "10:00:11:6d:1c:57";
        return mac;
    }

    private long getCurrentThreadId() {
        // 得到当前的主线程id
        return android.os.Process.myTid();
    }

    /**
     * 关闭程序不推荐如下两种方法
     * 能直接退出程序，但是可能未调用其中的 onDestory 或者 onStart方法
     * 关闭程序最好使用Activity控制器 控制打开和关闭
     */
    public void finishProject() {
        //方法一     （这是Dalvik VM的本地方法）
        android.os.Process.killProcess(android.os.Process.myPid());
        // 方法二  返回为0  则为正常退出
        System.exit(0);
    }

    //获取栈顶的activity
    public String getTopActivity(Activity context) {
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> runningTaskInfos = null;
        if (manager != null) {
            runningTaskInfos = manager.getRunningTasks(1);
        }

        if (runningTaskInfos != null)
            return (runningTaskInfos.get(0).topActivity).getClassName();
        else
            return null;
    }

}

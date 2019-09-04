package com.luofx.manager;

import android.content.ComponentName;
import android.content.Context;

import static android.content.Context.ACTIVITY_SERVICE;

public class ActivityManager {

    /**
     * 获取顶部的 ActivityName
     *
     * @return
     */
    public static String getTopActivityName(Context context) {
        android.app.ActivityManager am = (android.app.ActivityManager) context.getSystemService(ACTIVITY_SERVICE);
        if (am != null) {
            ComponentName cn = am.getRunningTasks(1).get(0).topActivity;
            if (cn != null) {
                return cn.getClassName();
            }
        }
        return null;
    }


}

package com.collect.user_luo.mycollect.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;


/**
 * 说明：
 * 作者：User_luo on 2018/5/14 14:56
 * 邮箱：424533553@qq.com
 */
public class SharedPreferencesUtils {

    /**
     *
     * @param fileName
     */

    /**
     * 获取 SharedPreferences
     *
     * @param fileName SharedPreferences的文件名
     * @param MODE     Context.MODE_PRIVATE：为默认操作模式,代表该文件是私有数据,只能被应用本身访问,在该模式下,写入的内容会覆盖原文件的内容
     *                 Context.MODE_APPEND：模式会检查文件是否存在,存在就往文件追加内容,否则就创建新文件。
     *                 Context.MODE_WORLD_READABLE 和  Context.MODE_WORLD_WRITEABLE用来控制其他应用是否有权限读写该文件。
     *                 MODE_WORLD_READABLE：表示当前文件可以被其他应用读取。
     *                 MODE_WORLD_WRITEABLE：表示当前文件可以被其他应用写入。
     *                 MODE_ENABLE_WRITE_AHEAD_LOGGING   数据库打开默认启用了写前日志记录
     */
    public SharedPreferences getSharedPreferences(Context context, String fileName, int MODE) {
        return context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
    }

    public SharedPreferences getSharedPreferences(Activity activity, int MODE) {
        return activity.getPreferences(MODE);
    }

    public SharedPreferences getSharedPreferences(PreferenceManager preferenceManager) {
        return preferenceManager.getSharedPreferences();
    }


}

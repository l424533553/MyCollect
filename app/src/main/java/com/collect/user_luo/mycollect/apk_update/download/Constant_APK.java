package com.collect.user_luo.mycollect.apk_update.download;

import android.os.Environment;

import com.collect.user_luo.mycollect.application.MyApplication;


/**
 * 创建时间：2018/3/7
 * 编写人：czw
 * 功能描述 ：
 */

public interface Constant_APK {
    String APP_ROOT_PATH = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + MyApplication.getInstance().getPackageName();
//    String APP_ROOT_PATH = Environment.getExternalStorageDirectory().getAbsolutePath() + "/";
    String DOWNLOAD_DIR = "/downlaod/";


}

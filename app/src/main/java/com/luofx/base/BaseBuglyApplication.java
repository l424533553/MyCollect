package com.luofx.base;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ComponentCallbacks2;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Environment;
import android.support.multidex.MultiDexApplication;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.collect.user_luo.mycollect.R;
import com.collect.user_luo.mycollect.activity.bugly.BuglyUPHelper;
import com.collect.user_luo.mycollect.activity.bugly.BuglyOtherActivity;
import com.collect.user_luo.mycollect.utils.Mylog;
import com.tencent.bugly.Bugly;
import com.tencent.bugly.BuglyStrategy;
import com.tencent.bugly.beta.Beta;
import com.tencent.bugly.beta.UpgradeInfo;
import com.tencent.bugly.beta.interfaces.BetaPatchListener;
import com.tencent.bugly.beta.ui.UILifecycleListener;
import com.tencent.bugly.beta.upgrade.UpgradeStateListener;
import com.tencent.bugly.crashreport.CrashReport;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

/**
 * 说明：
 * 作者：User_luo on 2018/7/24 13:52
 * 邮箱：424533553@qq.com
 * Bugly框架 包  ，必须继承MultiDexApplication  ，以为方法数超过了限制
 * <p>
 * 1.Android3.1版本后，每个App都必须要有至少有一个Activity，并且需要配置intent-filter，
 * 配置中的内容可以修改，比如设置 后看不见软件的图标
 * <intent-filter>
 * <action android:name="android.intent.action.MAIN" />
 * <category android:name="android.intent.category.LAUNCHER" />
 * </intent-filter>
 */
@SuppressLint("Registered")
public class BaseBuglyApplication extends MultiDexApplication {
    public static final String APP_CHANNEL = "DEBUG";
    private static final String TAG = "OnUILifecycleListener";//升级的 生命周期
    private static final boolean DEBUG_MODE = false;
    protected static final boolean COMMON_DEBUG_MODE = false;//普通的调试模式，不需要Tinker功能

    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        // 安装tinker， 不同的方式选择不一样。两种方式
        if (!COMMON_DEBUG_MODE) {
            Beta.installTinker();
        }
//        Beta.installTinker(this);
    }

//    @Override
//    public void onCreate() {
//        super.onCreate();
//        setStrictMode();
//        /* ***************** Bugly高级设置 **************/
//        initUpdateConfig();
//        initBackListener();
//        //  设置是否为上报进程
//        CrashReport.UserStrategy strategy = new CrashReport.UserStrategy(getApplicationContext());
//        //  设置app渠道号
//        //  strategy.setAppChannel(APP_CHANNEL);
//        initCrash(strategy);
//        /**
//         * 已经接入Bugly用户改用上面的初始化方法,不影响原有的crash上报功能;
//         * init方法会自动检测更新，不需要再手动调用Beta.checkUpdate(),如需增加自动检查时机可以使用Beta.checkUpdate(false,false);
//         * 参数1： applicationContext
//         * 参数2：appId
//         * 参数3：是否开启debug
//         */
//        initPatch();
//        Bugly.init(getApplicationContext(), APP_ID, DEBUG_MODE, strategy);
//
//        initCallBack();
//
//        //熄屏注册
//        IntentFilter screenOffFilter = new IntentFilter(Intent.ACTION_SCREEN_OFF);// 数据
//
//    }


    protected BuglyUPHelper buglyUPHelper;
    /***  升级更新  区  Start ***********************************************************************************************************/
    /**
     * 等级一  初级普通的升级策略，已经自动合成上报系统
     */
    public void onCreate1() {
        super.onCreate();
        buglyUPHelper = new BuglyUPHelper();
        if (!COMMON_DEBUG_MODE) {
            Bugly.init(getApplicationContext(), BuglyUPHelper.APP_ID, DEBUG_MODE);
        }
    }

    /**
     * 等级一  高级的升级策略，已经自动合成上报系统
     */
    public void onCreate() {
        super.onCreate();
        buglyUPHelper = new BuglyUPHelper();

        if (!COMMON_DEBUG_MODE) {
            BuglyStrategy strategy = new BuglyStrategy();
            // 设置app渠道号，设定了渠道号
            strategy.setAppChannel(APP_CHANNEL);
            buglyUPHelper.setContext(getApplicationContext());
            //需要发在初始化的后面

//            buglyUPHelper.setUpgradeListener();
            buglyUPHelper.setUILifecycleListener();
//            buglyUPHelper.setUpgradeStateListener();
            buglyUPHelper.initBugly(getApplicationContext(), strategy);
            buglyUPHelper.initUpdateConfig();
        }
    }


    /**
     * 初始化 监听回调   主要为Activity生命周期回调 和  ComponentCallbacks2回调
     */
    private void initCallBack() {
//        unregisterActivityLifecycleCallbacks()  对应的 生命周期监听
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                Log.d(TAG, "onActivityCreated: " + activity.getLocalClassName());
            }

            @Override
            public void onActivityStarted(Activity activity) {
                Log.d(TAG, "onActivityStarted: " + activity.getLocalClassName());
            }

            @Override
            public void onActivityResumed(Activity activity) {
                Log.d(TAG, "onActivityResumed: " + activity.getLocalClassName());
            }

            @Override
            public void onActivityPaused(Activity activity) {
                Log.d(TAG, "onActivityPaused: " + activity.getLocalClassName());
            }

            @Override
            public void onActivityStopped(Activity activity) {
                Log.d(TAG, "onActivityStopped: " + activity.getLocalClassName());
            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                Log.d(TAG, "onActivityDestroyed: " + activity.getLocalClassName());
            }
        });

//        unregisterComponentCallbacks();// ComponentCallbacks2 取消注册
        registerComponentCallbacks(new ComponentCallbacks2() {
            /*
                   当开发者的app正在运行:
                   Trim_memory_running_moderate：设备开始运行缓慢，当前app正在运行，不会被kill
                   Trim_memory_running_low：设备运行更缓慢了，当前app正在运行，不会被kill。但是请回收unused资源，以便提升系统的性能。
                   Trim_memory_running_critical：设备运行特别慢，当前app还不会被杀死，但是如果此app没有释放资源，系统将会kill后台进程

                   当开发者的app's visibility 改变:
                   Trim_memory_ui_hidden：当前app UI不再可见，这是一个回收大个资源的好时机

                   当开发者的应用进程被置于background LRU list:
                   trim_memory_background：系统运行慢，并且进程位于LRU list的上端。尽管app不处于高风险被kill。当前app应该释放那些容易恢复的资源
                   trim_memory_moderate：系统运行缓慢，当前进程已经位于LRU list的中部，如果系统进一步变慢，便会有被kill的可能
                   trim_memory_complete：系统运行慢，当前进程是第一批将被系统kill的进程。此app应该释放一切可以释放的资源。低于api 14的，用户可以使用onLowMemory回调。
                   */
            @Override
            public void onTrimMemory(int level) {//参数等级 参考如下
//                ComponentCallbacks2.
                Mylog.log("ComponentCallbacks==   onTrimMemory, level=" + level);
            }

            @Override
            public void onConfigurationChanged(Configuration newConfig) {
                Mylog.log("ComponentCallbacks==   onConfigurationChanged, Configuration=");
            }

            @Override
            public void onLowMemory() {
                Mylog.log("ComponentCallbacks==   onLowMemory");
            }
        });
    }

    @Override
    public void onTerminate() {
        // 程序终止的时候执行, 这个方法只用于Android仿真机测试的时候，真机中不会出现
//        Log.d(TAG, "onTerminate");
        super.onTerminate();
    }

    @Override
    public void onLowMemory() {
        // 低内存的时候执行
//        Log.d(TAG, "onLowMemory");
        super.onLowMemory();
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        // 程序在内存清理的时候执行
/*        Log.d(TAG, "onTrimMemory");
        switch (level) {
            case TRIM_MEMORY_UI_HIDDEN:
                //TODO  应用进入隐藏模式
                break;
        }*/


    }

    /**
     * 初始化补丁包
     */
    private void initPatch() {
        // 设置是否开启热更新能力，默认为true
        Beta.enableHotfix = true;
        // 设置是否自动下载补丁
        Beta.canAutoDownloadPatch = true;
        // 设置是否提示用户重启
        Beta.canNotifyUserRestart = true;
        // 设置是否自动合成补丁
        Beta.canAutoPatch = true;

        //补丁回调接口，可以监听补丁接收、下载、合成的回调
        Beta.betaPatchListener = new BetaPatchListener() {
            @Override
            public void onPatchReceived(String patchFileUrl) {
                Toast.makeText(getApplicationContext(), patchFileUrl, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDownloadReceived(long savedLength, long totalLength) {
                Toast.makeText(getApplicationContext(), String.format(Locale.getDefault(),
                        "%s %d%%",
                        Beta.strNotificationDownloading,
                        (int) (totalLength == 0 ? 0 : savedLength * 100 / totalLength)), Toast.LENGTH_SHORT).show();
            }

            /**
             *
             * @param patchFilePath   补丁的位置
             */
            @Override
            public void onDownloadSuccess(String patchFilePath) {
                Toast.makeText(getApplicationContext(), patchFilePath, Toast.LENGTH_SHORT).show();
                Beta.applyDownloadedPatch();
            }

            @Override
            public void onDownloadFailure(String msg) {
                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onApplySuccess(String msg) {
                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onApplyFailure(String msg) {
                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPatchRollback() {
                Toast.makeText(getApplicationContext(), "onPatchRollback", Toast.LENGTH_SHORT).show();
            }
        };

//        long start = System.currentTimeMillis();
//        Bugly.setUserId(this, "falue");
//        Bugly.setUserTag(this, 123456);
//        Bugly.putUserData(this, "key1", "123");
//        Bugly.setAppChannel(this, "bugly");

    }


    /**
     * 初始化 Crash 上报设置
     * 如果想自定义策略，按照如下方式设置
     * 设置该策略后 Crash上报只在主线程上报,避免不必要的志愿浪费，否则会在每个线程中初始化Bugly,并上报
     *
     * @param strategy 上报策略
     */
    private void initCrash(BuglyStrategy strategy) {
        // 获取当前包名
        String packageName = getApplicationContext().getPackageName();
        // 获取当前进程名
        String processName = getProcessName(android.os.Process.myPid());
        strategy.setUploadProcess(processName == null || processName.equals(packageName));

        // Bugly会在启动10s后联网同步数据。 这样会加快打开速度
        strategy.setAppReportDelay(5000);   //改为20s

        // Carsh回调类  ，使用最好配合自定义Map参数方式  使用
        strategy.setCrashHandleCallback(new CrashReport.CrashHandleCallback() {
            public Map<String, String> onCrashHandleStart(int crashType, String errorType,
                                                          String errorMessage, String errorStack) {
                LinkedHashMap<String, String> map = new LinkedHashMap<>();
                map.put("Key", "Value");
                return map;
            }

            @Override
            public byte[] onCrashHandleStart2GetExtraDatas(int crashType, String errorType,
                                                           String errorMessage, String errorStack) {
                try {
                    return "Extra data.".getBytes("UTF-8");
                } catch (Exception e) {
                    return null;
                }
            }

        });

        //当调试开关打开了 ，就认为它是调试设备，否则就是正式发布了的设备
        CrashReport.setIsDevelopmentDevice(getApplicationContext(), DEBUG_MODE);
        // 要在 init之前 使用该设置，根据标识设置是否 打开调试开关
//        CrashReport.setIsDevelopmentDevice(getApplicationContext(), BuildConfig.DEBUG);
//        strategy.setAppChannel("myChannel");  //设置渠道
//        strategy.setAppVersion("1.0.1");      //App的版本
//        strategy.setAppPackageName("com.tencent.xx");  //App的包名
        // 初始化Bugly
        // CrashReport.initCrashReport(context, "注册时申请的APPID", false, strategy);
    }

    /**
     * 获取进程号对应的进程名
     *
     * @param pid 进程号
     * @return 进程名
     */
    private static String getProcessName(int pid) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("/proc/" + pid + "/cmdline"));
            String processName = reader.readLine();
            if (!TextUtils.isEmpty(processName)) {
                processName = processName.trim();
            }
            return processName;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 初始化Bugly
     */
    private void initBugly() {


        /* *********************** Beta高级设置   ，高级设置需要放在初始化之前 ************************/
        // 设置是否开启热更新能力，默认为true。开启才能热更新
        Beta.enableHotfix = true;
        // 设置是否自动下载补丁，
        Beta.canAutoDownloadPatch = true;
        // 设置是否提示用户重启， 建议不要提示用户，无痕更新
        Beta.canNotifyUserRestart = false;
        // 设置是否自动合成补丁，建议 自动合成
        Beta.canAutoPatch = true;

        /**
         * true表示app启动自动初始化升级模块; false不会自动初始化;
         * 开发者如果担心sdk初始化影响app启动速度，可以设置为false，
         * 在后面某个时刻手动调用Beta.init(getApplicationContext(),false);
         */
        Beta.autoInit = true;

        /**
         * true表示初始化时自动检查升级; false表示不会自动检查升级,需要手动调用Beta.checkUpgrade()方法;
         */
        Beta.autoCheckUpgrade = true;

        /**
         * 设置升级检查周期为60s(默认检查周期为0s)，60s内SDK不重复向后台请求策略);
         */
        Beta.upgradeCheckPeriod = 30 * 1000;
        /**
         * 设置启动延时为1s（默认延时3s），APP启动1s后初始化SDK，避免影响APP启动速度;
         */
        Beta.initDelay = 10 * 1000;
//        /**
//         * 设置通知栏大图标，largeIconId为项目中的图片资源;
//         */
//        Beta.largeIconId = R.drawable.ic_launcher;
//        /**
//         * 设置状态栏小图标，smallIconId为项目中的图片资源Id;
//         */
//        Beta.smallIconId = R.drawable.ic_launcher;
//        /**
//         * 设置更新弹窗默认展示的banner，defaultBannerId为项目中的图片资源Id;
//         * 当后台配置的banner拉取失败时显示此banner，默认不设置则展示“loading“;
//         */
//        Beta.defaultBannerId = R.drawable.ic_launcher;
//        /**
//         * 设置sd卡的Download为更新资源保存目录;
//         * 后续更新资源会保存在此目录，需要在manifest中添加WRITE_EXTERNAL_STORAGE权限;
//         */
        Beta.storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        /**
         * 已经确认过的弹窗在APP下次启动自动检查更新时会再次显示;
         */
        Beta.showInterruptedStrategy = true;
//        /**
//         * 只允许在MainActivity上显示更新弹窗，其他activity上不显示弹窗; 不设置会默认所有activity都可以显示弹窗;
//         */
//        Beta.canShowUpgradeActs.add(MyBuglyActivity.class);
//
//        /*********** Bugly高级设置 ***********************************/
        BuglyStrategy strategy = new BuglyStrategy();
        /*  未使用的设置   **************************************************/
        // 设置app渠道号
//        strategy.setAppChannel(APP_CHANNEL);

//        initUpgradeListener();
        initPatchListener();
        /***** 统一初始化Bugly产品，包含Beta *****/
        Bugly.init(getApplicationContext(), BuglyUPHelper.APP_ID, false, strategy);
    }

    /**
     * 补丁回调接口，可以监听补丁接收、下载、合成的回调
     */
    private void initPatchListener() {

        Beta.betaPatchListener = new BetaPatchListener() {
            @Override
            public void onPatchReceived(String patchFileUrl) {
                Toast.makeText(getApplicationContext(), patchFileUrl, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDownloadReceived(long savedLength, long totalLength) {
                Toast.makeText(getApplicationContext(), String.format(Locale.getDefault(), "%s %d%%",
                        Beta.strNotificationDownloading,
                        (int) (totalLength == 0 ? 0 : savedLength * 100 / totalLength)), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDownloadSuccess(String patchFilePath) {
                Toast.makeText(getApplicationContext(), patchFilePath, Toast.LENGTH_SHORT).show();
//                Beta.applyDownloadedPatch();
            }

            @Override
            public void onDownloadFailure(String msg) {
                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onApplySuccess(String msg) {
                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onApplyFailure(String msg) {
                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPatchRollback() {
                Toast.makeText(getApplicationContext(), "onPatchRollback", Toast.LENGTH_SHORT).show();
            }
        };
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // 配置修改
        //
    }


}

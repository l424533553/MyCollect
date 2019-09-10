package com.collect.user_luo.mycollect.activity.webview;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.hardware.SensorManager;
import android.provider.Settings;
import android.util.Log;
import android.view.OrientationEventListener;


import com.collect.user_luo.BuildConfig;

import java.lang.ref.WeakReference;


/**
 * @author : Created by zhangqiang
 * desc    :通过重力传感器切换横竖屏放向
 * 在activity的ondestory()方法里面或者back键的监听里面禁用屏幕监听
 */
public class MySensorHelper {

    private static final String TAG = "MySensorHelper";
    private OrientationEventListener mOrientationEventListener;
    private WeakReference<Activity> mActivityWeakRef;
    private Activity mActivity;
    //竖直锁定
    private boolean isPortLock = false;
    //横屏锁定
    private boolean isLandLock = false;
    //是否在全屏模式
    private boolean isFullScreen = false;
    //屏幕方向
    private int mOrientation = -1;
    //屏幕状态 (横屏 / 竖屏等)
    private ScreenState mScreenState;

    public MySensorHelper(final Activity activity) {
        this.mActivityWeakRef = new WeakReference(activity);
        mActivity = activity;
        //横屏感应
        this.mOrientationEventListener = new OrientationEventListener(activity, SensorManager.SENSOR_DELAY_NORMAL) {
            @Override
            public void onOrientationChanged(int orientation) {
                if (BuildConfig.DEBUG) {
                    Log.v(MySensorHelper.TAG, "mOrientationEventListener:" + orientation);
                }

//                //若未开启重力感应则不作处理
//                if (!ishaveSensor()) {
//                    return;
//                }
                //横屏感应
                if (orientation < 100 && orientation > 80 || orientation < 280 && orientation > 260) {

                    if (!MySensorHelper.this.isLandLock) {
                        Activity mActivity = (Activity) MySensorHelper.this.mActivityWeakRef.get();
                        if (mActivity != null) {
                            if (orientation < 280 && orientation > 260) {
                                /* 在全屏模式下 或者 开启了重力感应下 才进入旋转 */
                                if (isFullScreen || ishaveSensor()) {
                                    //设置横屏
                                    mActivity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                                    if (BuildConfig.DEBUG) {
                                        Log.w(MySensorHelper.TAG, "转到了横屏##################");
                                    }
                                }

                            } else if (orientation < 100 && orientation > 80) {
                                //在全屏模式下 或者 开启了重力感应下 才进入旋转
                                if (isFullScreen || ishaveSensor()) {
                                    //设置反向横屏
                                    mActivity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE);
                                    if (BuildConfig.DEBUG) {
                                        Log.w(MySensorHelper.TAG, "转到了横屏-反向 ##################");
                                    }
                                }

                            }
                            isLandLock = true;
                            isPortLock = false;
                        }
                    }
                }

                // 竖屏感应
                if (orientation < 10 || orientation > 350 || orientation < 190 && orientation > 170) {
                    if (!MySensorHelper.this.isPortLock) {
                        Activity mActivity = (Activity) MySensorHelper.this.mActivityWeakRef.get();
                        if (mActivity != null) {

                            if (isFullScreen) {
                                //全屏下 若未开启方向锁定 调用webview全屏消失
                                if (mScreenState != null && ishaveSensor()) {
                                    mScreenState.OnScreenPortrait();
                                    mActivity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT);
                                    if (BuildConfig.DEBUG) {
                                        Log.w(MySensorHelper.TAG, "全屏下   转到了竖屏!!!!!!!!!!!!!!!!!!!!!!");
                                    }
                                }
                            } else {
                                //非全屏下才处理 感应旋转
                                mActivity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT);
                                if (BuildConfig.DEBUG) {
                                    Log.w(MySensorHelper.TAG, "非全屏下   转到了竖屏!!!!!!!!!!!!!!!!!!!!!!");
                                }
                            }

                            isPortLock = true;
                            isLandLock = false;
                        }
                    }
                }
            }
        };
    }

    /**
     * 禁用切换屏幕的开关
     */
    public void disable() {
        Log.e(TAG, "disable");
        //禁用横屏感应
        if (mOrientationEventListener != null) {
            this.mOrientationEventListener.disable();
        }
    }

    /**
     * 开启横竖屏切换的开关
     */
    public void enable() {
        //横屏感应
        if (mOrientationEventListener != null) {
            this.mOrientationEventListener.enable();
        }
    }

    /**
     * 设置竖屏是否上锁，true锁定屏幕,fanle解锁
     *
     * @param lockFlag
     */
    public void setPortLock(boolean lockFlag) {
        this.isPortLock = lockFlag;
    }

    /**
     * 设置横屏是否锁定，true锁定，false解锁
     *
     * @param isLandLock
     */
    public void setLandLock(boolean isLandLock) {
        this.isLandLock = isLandLock;
    }


    /**
     * 设置当前是否全屏
     *
     * @param fullScreen
     */
    public void setFullScreen(boolean fullScreen) {
        this.isFullScreen = fullScreen;
    }

    /**
     * 获取系统重力感应的开关状态
     * 0表示关闭，1表示开启
     */
    public boolean ishaveSensor() {
        int sensor = 0;
        try {
            sensor = Settings.System.getInt(mActivity.getContentResolver(), Settings.System.ACCELEROMETER_ROTATION);
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
        }
        if (sensor == 0) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Activity当前屏幕方向的属性值
     *
     * @return 0 横屏 , 1 竖屏
     */
    public int getOrientation() {
        if (mActivity != null && !mActivity.isFinishing()) {
            mOrientation = mActivity.getResources().getConfiguration().orientation;
            return mOrientation;
        }
        return -1;
    }

    /**
     * 设置Activity的的屏幕方向属性值
     *
     * @param orientation ActivityInfo.xxx 0 横屏 , 1 竖屏 如ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
     *                    <p>
     *                    screenOrientations属性共有7中可选值(常量定义在 android.content.pm.ActivityInfo类中 ) ：
     *                    0-landscape：横屏(风景照) ，显示时宽度大于高度；
     *                    portrait：竖屏 (肖像照) ， 显示时 高 度大于 宽 度 ；
     *                    user：用户当前的首选方向；
     *                    behind：继承Activity堆栈中当前Activity下面的那个Activity的方向；
     *                    sensor：由物理感应器决定显示方向，它取决于用户如何持有设备，当 设备 被旋转时方向会随之变化——在横屏与竖屏之间；
     *                    nosensor：忽略物理感应器——即显示方向与物理感应器无关，不管用户如何旋转设备显示方向都不会随着改变("unspecified"设置除外)；
     *                    unspecified ：未指定，此为默认值，由Android系统自己选择适当的方向，选择策略视具体设备的配置情况而定，因此不同的设备会有不同的方向选择；
     *                    以上配置值会反映在Activity.getRequestedOrientation()方法的返回值中，与之对应的setRequestedOrientation()方法可以通过API的方式动态改变该属性的值，如以下示例将在横屏/竖屏两个方向上进行切换
     */
    public void setOrientation(int orientation) {
        mOrientation = orientation;
        mActivity.setRequestedOrientation(orientation);
    }

    /**
     * 设置屏幕状态(横屏/竖屏监听)
     *
     * @param screenState
     */
    public void setScreenStateListener(ScreenState screenState) {
        mScreenState = screenState;
    }

    /**
     * 屏幕状态接口类
     */
    public interface ScreenState {
        /**
         * 竖屏状态
         */
        void OnScreenPortrait();
    }


}

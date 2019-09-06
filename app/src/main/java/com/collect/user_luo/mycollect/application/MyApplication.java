package com.collect.user_luo.mycollect.application;

import android.annotation.SuppressLint;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.app.job.JobWorkItem;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.WindowManager;

import com.collect.user_luo.mycollect.MainActivity;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.xuanyuan.library.base.application.BaseVolleyApplication;

import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * 说明： 继承自 基础的 Application中
 * 作者：User_luo on 2018/5/16 15:20
 * 邮箱：424533553@qq.com
 */
public class MyApplication extends BaseVolleyApplication {


    private WindowManager.LayoutParams windowParams = new WindowManager.LayoutParams();

    public WindowManager.LayoutParams getWindowParams() {
        return windowParams;
    }

    @SuppressLint("StaticFieldLeak")
    private static MyApplication sInstance;

    @Override
    protected boolean isOpenVolley() {
        return true;
    }

    @Override
    protected boolean isOpenRetryPolicy() {
        return true;
    }

    @Override
    protected boolean isOpenCrashHandler() {
        return false;
    }

    @Override
    protected boolean isDebugMode() {
        return true;
    }

    @Override
    protected boolean isOpenBugly() {
        return true;
    }

    /**
     * 是用开发测试的结果
     */
    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
        sInstance = this;
    }

    public static Context getInstance() {
        return sInstance;
    }


    /**
     * 测试结果 和 检测数据
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @SuppressLint("WrongConstant")
    private void testVig() {
        JobScheduler job = new JobScheduler() {
            @Override
            public int schedule(@NotNull JobInfo job) {
                return 0;
            }

            @Override
            public int enqueue(@NotNull JobInfo job, JobWorkItem work) {
                return 0;
            }

            @Override
            public void cancel(int jobId) {
            }

            @Override
            public void cancelAll() {
            }

            @Override
            public List<JobInfo> getAllPendingJobs() {
                return null;
            }

            @Override
            public JobInfo getPendingJob(int jobId) {
                if (jobId == 1001) {
                    // 线程号对的上，
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                }
                return null;
            }
        };
    }
}

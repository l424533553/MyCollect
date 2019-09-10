package com.collect.user_luo.mycollect.activity.bugly;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.collect.user_luo.R;
import com.tencent.bugly.beta.Beta;
import com.tencent.bugly.beta.UpgradeInfo;
import com.tencent.bugly.crashreport.CrashReport;
import com.xuanyuan.library.utils.log.MyLog;

public class MyBuglyActivity extends AppCompatActivity implements View.OnClickListener {

//    /**
//     * 如果想更新so，可以将System.loadLibrary替换成Beta.loadLibrary
//     */
//    static {
//        Beta.loadLibrary("mylib");
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_bugly);

        TextView tvCurrentVersion = findViewById(R.id.tvCurrentVersion);
        Button btnShowToast = findViewById(R.id.btnShowToast);
        btnShowToast.setOnClickListener(this);
        Button btnKillSelf = findViewById(R.id.btnKillSelf);
        btnKillSelf.setOnClickListener(this);
        Button btnLoadPatch = findViewById(R.id.btnLoadPatch);
        btnLoadPatch.setOnClickListener(this);
        Button btnLoadLibrary = findViewById(R.id.btnLoadLibrary);
        btnLoadLibrary.setOnClickListener(this);
        Button btnDownloadPatch = findViewById(R.id.btnDownloadPatch);
        btnDownloadPatch.setOnClickListener(this);
        Button btnUserPatch = findViewById(R.id.btnPatchDownloaded);
        btnUserPatch.setOnClickListener(this);
        Button btnCheckUpgrade = findViewById(R.id.btnCheckUpgrade);
        btnCheckUpgrade.setOnClickListener(this);

        tvCurrentVersion.setText("当前版本：" + getCurrentVersion(this));

//        findViewById(R.id.tvTest).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(MyBuglyActivity.this,"新增的功能",Toast.LENGTH_SHORT).show();
//            }
//        });


        Button btnCheckUpdate = findViewById(R.id.btnCheckUpdate);
        Button btnLoadUpgradeInfo = findViewById(R.id.btnLoadUpdateInfo);
        tvUpgradeInfo = findViewById(R.id.tvUpgradeInfo);
        btnCheckUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Beta.checkUpgrade();

            }
        });
        btnLoadUpgradeInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadUpgradeInfo();
//                int aa = 1 / 0;
            }
        });

        findViewById(R.id.btnJavaCrash).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CrashReport.testJavaCrash();
            }
        });

        TextView textView = findViewById(R.id.tvVersionInfo);
        textView.setText(getCurrentVersion(this));
        findViewById(R.id.btnJumpUpgrade).setOnClickListener(this);
        findViewById(R.id.btnMadeCarsh).setOnClickListener(this);
    }

    private TextView tvUpgradeInfo;

    private void loadUpgradeInfo() {
        if (tvUpgradeInfo == null)
            return;

        /***** 获取升级信息 *****/
        UpgradeInfo upgradeInfo = Beta.getUpgradeInfo();
        if (upgradeInfo == null) {
            tvUpgradeInfo.setText("无升级信息");
            return;
        }

        MyLog.blue(upgradeInfo.toString());
        StringBuilder info = new StringBuilder();
        info.append("id: ").append(upgradeInfo.id).append("\n");
        info.append("标题: ").append(upgradeInfo.title).append("\n");
        info.append("升级说明: ").append(upgradeInfo.newFeature).append("\n");
        info.append("versionCode: ").append(upgradeInfo.versionCode).append("\n");
        info.append("versionName: ").append(upgradeInfo.versionName).append("\n");
        info.append("发布时间: ").append(upgradeInfo.publishTime).append("\n");
        info.append("安装包Md5: ").append(upgradeInfo.apkMd5).append("\n");
        info.append("安装包下载地址: ").append(upgradeInfo.apkUrl).append("\n");
        info.append("安装包大小: ").append(upgradeInfo.fileSize).append("\n");
        info.append("弹窗间隔（ms）: ").append(upgradeInfo.popInterval).append("\n");
        info.append("弹窗次数: ").append(upgradeInfo.popTimes).append("\n");
        info.append("发布类型（0:测试 1:正式）: ").append(upgradeInfo.publishType).append("\n");
        info.append("弹窗类型（1:建议 2:强制 3:手工）: ").append(upgradeInfo.upgradeType);
        info.append("图片地址：").append(upgradeInfo.imageUrl);
        tvUpgradeInfo.setText(info);
    }

    /**
     * 根据应用patch包前后来测试是否应用patch包成功.
     * <p>
     * 应用patch包前，提示"This is a bug class"
     * 应用patch包之后，提示"The bug has fixed"
     */
    public void testToast() {
        Toast.makeText(this, "是否成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnShowToast:  // 测试热更新功能
                testToast();
                break;
            case R.id.btnKillSelf: // 杀死进程
                android.os.Process.killProcess(android.os.Process.myPid());
                break;
            case R.id.btnLoadPatch: // 本地加载补丁测试
                String pathFile = Environment.getExternalStorageDirectory().getAbsolutePath() + "/patch_signed_7zip.apk";
                Beta.applyTinkerPatch(getApplicationContext(), pathFile);
                break;
            case R.id.btnLoadLibrary: // 本地加载so库测试
//                TestJNI testJNI = new TestJNI();
//                testJNI.createANativeCrash();
                break;
            case R.id.btnDownloadPatch:
                Beta.downloadPatch();
                break;
            case R.id.btnJumpUpgrade://跳转到 UpgradeActivity中
                Intent intent = new Intent(this, UpgradeActivity.class);
                startActivity(intent);
                break;
            case R.id.btnMadeCarsh://跳转到 UpgradeActivity中
                int a = 5 / 0;
                break;
            case R.id.btnPatchDownloaded:
                Beta.applyDownloadedPatch();//应用
                break;
            case R.id.btnCheckUpgrade:
                Beta.checkUpgrade();
                UpgradeInfo upgradeInfo = Beta.getUpgradeInfo();
//                if (upgradeInfo != null) {
//                    //进来就检测是否有新版本 有就提醒更新并显示红点
//                    checkVersionUpdate();
//                    loadUpgradeInfo();
//                } else {
//                    //没有新版本
//                    mUpdateVersionView.setDesc("已是最新版本");
//                    mUpdateVersionView.setGone();//隐藏红点提示
//                }

                break;
        }
    }

    /**
     * 获取当前版本.
     *
     * @param context 上下文对象
     * @return 返回当前版本
     */
    public String getCurrentVersion(Context context) {
        try {
            PackageInfo packageInfo =
                    context.getPackageManager().getPackageInfo(this.getPackageName(),
                            PackageManager.GET_CONFIGURATIONS);
            int versionCode = packageInfo.versionCode;
            String versionName = packageInfo.versionName;

            return versionName + "." + versionCode;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

}

/*
    UpgradeInfo类的属性
    public String id = "";//唯一标识
    public String title = "";//升级提示标题
    public String newFeature = "";//升级特性描述
    public long publishTime = 0;//升级发布时间,ms
    public int publishType = 0;//升级类型 0测试 1正式
    public int upgradeType = 1;//升级策略 1建议 2强制 3手工
    public int popTimes = 0;//提醒次数
    public long popInterval = 0;//提醒间隔
    public int versionCode;
    public String versionName = "";
    public String apkMd5;//包md5值
    public String apkUrl;//APK的CDN外网下载地址
    public long fileSize;//APK文件的大小
    pubilc String imageUrl; // 图片url*/


//
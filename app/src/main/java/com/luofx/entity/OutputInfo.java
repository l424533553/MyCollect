package com.luofx.entity;

import android.databinding.BaseObservable;
import android.databinding.Bindable;



/**
 * 说明：
 * 作者：User_luo on 2018/7/25 11:10
 * 邮箱：424533553@qq.com
 */
public class OutputInfo extends BaseObservable {

    //如果是 private 修饰符，则在成员变量的 get 方法上添加 @Bindable 注解
    private String versionName;
    private int versionCode;
    //如果是 public 修饰符，则可以直接在成员变量上方加上 @Bindable 注解
    @Bindable
    public String apkName;
    private String versionDesc;
    private String downloadUrl;
    private String versionSize;

    @Bindable
    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
        //更新所有字段
        notifyChange();
    }

    public int getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(int versionCode) {
        this.versionCode = versionCode;
    }

    public String getApkName() {
        return apkName;
    }

    public void setApkName(String apkName) {
        this.apkName = apkName;
        //只更新本字段
//        notifyPropertyChanged(outputInfo.BR.apkName);
    }

    public String getVersionDesc() {
        return versionDesc;
    }

    public void setVersionDesc(String versionDesc) {
        this.versionDesc = versionDesc;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    public String getVersionSize() {
        return versionSize;
    }

    public void setVersionSize(String versionSize) {
        this.versionSize = versionSize;
    }
}

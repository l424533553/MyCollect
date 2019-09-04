package com.collect.user_luo.mycollect.activity.bugly;

import com.tencent.bugly.beta.UpgradeInfo;
import com.tencent.bugly.proguard.y;

public class MyUpgradeInfo extends UpgradeInfo {

    public MyUpgradeInfo(y detail) {
        super(detail);
    }

    @Override
    public String toString() {
        return "MyUpgradeInfo{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", newFeature='" + newFeature + '\'' +
                ", publishTime=" + publishTime +
                ", publishType=" + publishType +
                ", upgradeType=" + upgradeType +
                ", popTimes=" + popTimes +
                ", popInterval=" + popInterval +
                ", versionCode=" + versionCode +
                ", versionName='" + versionName + '\'' +
                ", apkMd5='" + apkMd5 + '\'' +
                ", apkUrl='" + apkUrl + '\'' +
                ", fileSize=" + fileSize +
                ", imageUrl='" + imageUrl + '\'' +
                ", updateType=" + updateType +
                '}';
    }
}

package com.collect.user_luo.mycollect.activity.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.text.TextUtils;
import android.util.Log;

/**
 * 说明： 一個開機自動  鏈接 自定義 Wifi 的接收者
 * 需要注册 静态广播  开机广播
 * <p>
 * 作者：User_luo on 2018/5/22 15:04
 * 邮箱：424533553@qq.com
 */
public class PresetWifiInfoBootBroadcastReceiver extends BroadcastReceiver {
    private WifiManager wifiManager;
//    private WifiTracker mWifiTracker;
    private String ACTION = "android.intent.action.BOOT_COMPLETED";
    private String TAG = "PresetWifiInfoBootBroadcastReceiver";

    public enum WifiCipherType {
        WIFICIPHER_WEP, WIFICIPHER_WPA, WIFICIPHER_NOPASS, WIFICIPHER_INVALID
    }

    @Override
    public void onReceive(final Context context, Intent intent) {
        if (intent.getAction().equals(ACTION)) {
            Log.e(TAG, "preset wifi into android.intent.action.BOOT_COMPLETED");

//            mWifiTracker = new WifiTracker(context, null, true, true, false);
//            wifiManager = mWifiTracker.getManager();
//            // WifiConfiguration configuration=createWifiInfo("网络名称SSID","WIFI密码",安全类型)
//            WifiConfiguration configuration = createWifiInfo("icon", "12345678", WifiCipherType.WIFICIPHER_WPA);
//            wifiManager.save(configuration, null);
//            Log.e(TAG, "PresetWifiInfoBootBroadcastReceiver  configuration" + configuration);

        }
    }

    private WifiConfiguration createWifiInfo(String SSID, String Password,
                                             WifiCipherType Type) {
        WifiConfiguration config = new WifiConfiguration();
        config.allowedAuthAlgorithms.clear();
        config.allowedGroupCiphers.clear();
        config.allowedKeyManagement.clear();
        config.allowedPairwiseCiphers.clear();
        config.allowedProtocols.clear();
        config.SSID = "\"" + SSID + "\"";
        // NOPASS
        if (Type == WifiCipherType.WIFICIPHER_NOPASS) {
            config.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.NONE);
        }
        // WEP
        if (Type == WifiCipherType.WIFICIPHER_WEP) {
            if (!TextUtils.isEmpty(Password)) {
                if (isHexWepKey(Password)) {
                    config.wepKeys[0] = Password;
                } else {
                    config.wepKeys[0] = "\"" + Password + "\"";
                }
            }
            config.allowedAuthAlgorithms.set(WifiConfiguration.AuthAlgorithm.OPEN);
            config.allowedAuthAlgorithms.set(WifiConfiguration.AuthAlgorithm.SHARED);
            config.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.NONE);
            config.wepTxKeyIndex = 0;
        }
        // WPA
        if (Type == WifiCipherType.WIFICIPHER_WPA) {
            config.preSharedKey = "\"" + Password + "\"";
            config.hiddenSSID = true;
            config.allowedAuthAlgorithms
                    .set(WifiConfiguration.AuthAlgorithm.OPEN);
            config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.TKIP);
            config.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.WPA_PSK);
            config.allowedPairwiseCiphers
                    .set(WifiConfiguration.PairwiseCipher.TKIP);
            // 此处需要修改否则不能自动重联
            // config.allowedProtocols.set(WifiConfiguration.Protocol.WPA);
            config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.CCMP);
            config.allowedPairwiseCiphers
                    .set(WifiConfiguration.PairwiseCipher.CCMP);
            config.status = WifiConfiguration.Status.ENABLED;
        }
        return config;
    }

    private static boolean isHexWepKey(String wepKey) {
        final int len = wepKey.length();
        // WEP-40, WEP-104, and some vendors using 256-bit WEP (WEP-232?)
        if (len != 10 && len != 26 && len != 58) {
            return false;
        }
        return isHex(wepKey);
    }

    private static boolean isHex(String key) {
        for (int i = key.length() - 1; i >= 0; i--) {
            final char c = key.charAt(i);
            if (!(c >= '0' && c <= '9' || c >= 'A' && c <= 'F' || c >= 'a'
                    && c <= 'f')) {
                return false;
            }
        }
        return true;
    }
}
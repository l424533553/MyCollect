package com.luofx.utils.system;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;

import java.util.List;

/**
 * wifi 使用工具 ，第一步就是初始化
 */
public class WifiUtils {

    /**
     * wifi 管理器
     */
    private WifiManager wifiManager;
    private Context context;

    public void init() {
        wifiManager = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        if (wifiManager != null && !wifiManager.isWifiEnabled()) {
            wifiManager.setWifiEnabled(true);//wifi处于可用状态
        }

        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = null;
        if (connectivityManager != null) {
            networkInfo = connectivityManager.getActiveNetworkInfo();
        }
        if (networkInfo != null && networkInfo.isAvailable()) {

        }
    }


    private BroadcastReceiver netWorkReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
        }
    };


    public WifiConfiguration createWifiInfo(String SSID, String Password, int Type) {
        WifiConfiguration config = new WifiConfiguration();
        config.allowedAuthAlgorithms.clear();
        config.allowedGroupCiphers.clear();
        config.allowedKeyManagement.clear();
        config.allowedPairwiseCiphers.clear();
        config.allowedProtocols.clear();
        config.SSID = "\"" + SSID + "\"";

        WifiConfiguration tempConfig = this.IsExsits(SSID);
        if (tempConfig != null) {
            wifiManager.removeNetwork(tempConfig.networkId);
        }

        if (Type == 1) // WIFICIPHER_NOPASS
        {
            config.wepKeys[0] = "";
            config.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.NONE);
            config.wepTxKeyIndex = 0;
        }
        if (Type == 2) // WIFICIPHER_WEP
        {
            config.hiddenSSID = true;
            config.wepKeys[0] = "\"" + Password + "\"";
            config.allowedAuthAlgorithms.set(WifiConfiguration.AuthAlgorithm.SHARED);
            config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.CCMP);
            config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.TKIP);
            config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.WEP40);
            config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.WEP104);
            config.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.NONE);
            config.wepTxKeyIndex = 0;
        }
        if (Type == 3) // WIFICIPHER_WPA
        {
            config.preSharedKey = "\"" + Password + "\"";
            config.hiddenSSID = true;
            config.allowedAuthAlgorithms.set(WifiConfiguration.AuthAlgorithm.OPEN);
            config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.TKIP);
            config.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.WPA_PSK);
            config.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.TKIP);
            // config.allowedProtocols.set(WifiConfiguration.Protocol.WPA);
            config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.CCMP);
            config.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.CCMP);
            config.status = WifiConfiguration.Status.ENABLED;
        }
        return config;
    }

    /**
     *
     * @param SSID
     * @param password  密码
     */
    private void Wificonnect(String SSID, String password) {
        // 连接到外网
        WifiConfiguration mWifiConfiguration;
        //检测指定SSID的WifiConfiguration 是否存在
        WifiConfiguration tempConfig = IsExsits(SSID);
        if (tempConfig == null) {
            //创建一个新的WifiConfiguration ，CreateWifiInfo()需要自己实现
            mWifiConfiguration = createWifiInfo(SSID, password, 3);
            int wcgID = wifiManager.addNetwork(mWifiConfiguration);
            boolean b = wifiManager.enableNetwork(wcgID, true);
        } else {
            //发现指定WiFi，并且这个WiFi以前连接成功过
            mWifiConfiguration = tempConfig;
            boolean b = wifiManager.enableNetwork(mWifiConfiguration.networkId, true);
        }

    }

    //判断曾经连接过得WiFi中是否存在指定SSID的WifiConfiguration
    public WifiConfiguration IsExsits(String SSID) {
        List<WifiConfiguration> existingConfigs = wifiManager
                .getConfiguredNetworks();
        for (WifiConfiguration existingConfig : existingConfigs) {
            if (existingConfig.SSID.equals("\"" + SSID + "\"")) {
                return existingConfig;
            }
        }
        return null;
    }

    public void startScan(Context context) {
        wifiManager.startScan();
        //得到扫描结果
        List<ScanResult> results = wifiManager.getScanResults();
        // 得到配置好的网络连接
        List<WifiConfiguration> WifiConfiguration = wifiManager.getConfiguredNetworks();
//        if (results == null) {
//            if (wifiManager.getWifiState() == 3) {
//                Toast.makeText(context, "当前区域没有无线网络", Toast.LENGTH_SHORT).show();
//            } else if (wifiManager.getWifiState() == 2) {
//                Toast.makeText(context, "wifi正在开启，请稍后扫描", Toast.LENGTH_SHORT).show();
//            } else {
//                Toast.makeText(context, "WiFi没有开启", Toast.LENGTH_SHORT).show();
//            }
//        }


    }


}

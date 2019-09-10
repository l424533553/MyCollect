package com.collect.user_luo.mycollect.data;

import com.collect.user_luo.R;
import com.collect.user_luo.mycollect.MainActivity;
import com.collect.user_luo.mytest.TestActivity;
import com.collect.user_luo.mycollect.activity.RecyclerViewActivity;
import com.collect.user_luo.mycollect.activity.webview.WebActivity;
import com.collect.user_luo.mycollect.activity.bluetooth.BlueToothActivity;
import com.collect.user_luo.mycollect.activity.bugly.MyBuglyActivity;
import com.collect.user_luo.mycollect.activity.eventbus.EventBusActivity;
import com.collect.user_luo.mycollect.activity.mine.MineActivity;
import com.collect.user_luo.mycollect.activity.picture.fresco.FrescoActivity;
import com.collect.user_luo.mycollect.activity.progress.ProgressBarActivity;
import com.collect.user_luo.mycollect.activity.refresh.RefreshActivity;
//import com.collect.user_luo.mycollect.activity.retrofit_rxjava.RetrofitOrRxjavaActivity;
import com.collect.user_luo.mycollect.activity.service.MyServiceActivity;
import com.collect.user_luo.mycollect.activity.socket.SocketTestActivity;
import com.collect.user_luo.mycollect.activity.sqlite.SqliteActivity;
import com.collect.user_luo.mycollect.activity.system.SystemActivity;
import com.collect.user_luo.mycollect.activity.tools.ToolsActivity;
import com.collect.user_luo.mycollect.activity.tools.Word2HtmlActivity;
import com.collect.user_luo.mycollect.activity.view.ViewActivity;
import com.collect.user_luo.mycollect.activity.webview.WebMenuActivity;
import com.collect.user_luo.mycollect.activity.webview.WebViewActivity;
import com.collect.user_luo.mycollect.activity.webview.WebWhatActivity;
import com.collect.user_luo.mycollect.apk_update.ApkUpdateActivity;
import com.collect.user_luo.mycollect.config.IDataConstants;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class HomeData implements IDataConstants {

    /**
     * 获取功能界面
     *
     * @return 返回
     */
    public static List<String> getHomeData() {
        List<String> data = new ArrayList<>();
        data.add("界面刷新");
        return data;

    }

    public static Map<String, Class> getHomeMapData() {
        Map<String, Class> map = new HashMap<>();
        map.put("界面刷新", RefreshActivity.class);
        map.put("Sqlite操作", SqliteActivity.class);
        map.put("Service操作", MyServiceActivity.class);
        map.put("RecyclerView", RecyclerViewActivity.class);
        map.put("蓝牙", BlueToothActivity.class);
        map.put("进度刷新", ProgressBarActivity.class);
        map.put("Socket测试", SocketTestActivity.class);
        map.put("Kotlin测试", MainActivity.class);
        map.put("图片测试", FrescoActivity.class);
        map.put("自定义View", ViewActivity.class);
        map.put("EventBus测试", EventBusActivity.class);
        map.put("Replugin插件化", EventBusActivity.class);
        map.put("WebView菜单", WebMenuActivity.class);
        map.put("工具类测试", ToolsActivity.class);
//        map.put("Retrofit功能", RetrofitOrRxjavaActivity.class);
        map.put("手机硬件信息", SystemActivity.class);
        map.put("APK下载更新测试", ApkUpdateActivity.class);
        map.put("Bugly框架测试", MyBuglyActivity.class);

        map.put("Test测试", TestActivity.class);
        map.put("我的设置页面", MineActivity.class);
   
//        map.put("LiveEventBus", MineActivity.class);
        return map;
    }

    /**  //
     * Webmenu 中的数据源
     */
    public static Map<String, Class> getWebMapData() {
        Map<String, Class> map = new HashMap<>();
        map.put("WebView测试", WebWhatActivity.class);
        map.put("WebView使用", WebViewActivity.class);
        map.put("Web页面显示", WebActivity.class);
        return map;
    }

    /**
     *
     * @return  获取html  文件
     */
    public static Map<String, Class> getToolsMapData() {
        Map<String, Class> map = new HashMap<>();
        map.put("Word2Html", Word2HtmlActivity.class);
        return map;
    }

    public static Map<String, Class> getRetrofitMapData() {
        Map<String, Class> map = new HashMap<>();
        map.put("APK下载更新", ApkUpdateActivity.class);
        return map;
    }

    public static List<String> getRefreshActivityData() {
        List<String> data = new ArrayList<>();
        //官方的刷新方法
        data.add(REFRESH_TYPE_SWIPE);
        data.add(REFRESH_TYPE_SMART);
        return data;
    }

    public static List<String> getRefreshData() {
        List<String> data = new ArrayList<>();
        //官方的刷新方法
        data.add("橙子");
        data.add("菠萝");
        data.add("橘子");
        data.add("葡萄");
        data.add("梨");
        data.add("西瓜");
        data.add("香瓜");
        data.add("哈密瓜");
        data.add("芒果");
        data.add("荔枝");
        return data;
    }

    public static List<String> getRefreshData2() {
        List<String> data = new ArrayList<>();
        //官方的刷新方法
        data.add("橙子2");
        data.add("菠萝2");
        data.add("橘子2");
        data.add("葡萄2");
        data.add("梨2");
        data.add("西瓜2");
        data.add("香瓜2");
        data.add("哈密瓜2");
        data.add("芒果2");
        data.add("荔枝2");
        return data;
    }

    /**
     * @return id  图片的id
     */
    public static List<Integer> getResources() {
        List<Integer> data = new ArrayList<>();
        //官方的刷新方法
        data.add(R.mipmap.a741);
        data.add(R.mipmap.a742);
        data.add(R.mipmap.ic_launcher);
        data.add(R.mipmap.ic_launcher_round);
        return data;
    }

}

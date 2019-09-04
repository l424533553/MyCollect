package com.collect.user_luo.mycollect.activity.webview

import android.content.pm.ActivityInfo
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.webkit.*
import android.widget.FrameLayout
import com.collect.user_luo.mycollect.R

@Suppress("DEPRECATION")
class WebViewActivity : AppCompatActivity() {

    lateinit var webView: WebView

    fun initView() {

        var xwebchromeclient = XWebChromeClient();
//setWebChromeClient主要处理解析，渲染网页等浏览器做的事情
//这个方法必须有，就算类中没有函数也可以，不然视频播放不了
        //WebChromeClient是辅助WebView处理Javascript的对话框，网站图标，网站title，加载进度等
        webView.setWebChromeClient(xwebchromeclient);
        //主要处理解析，渲染页等浏览器要做的事情  。设置该处后 ，才会覆盖默认浏览器打开网页，改为WebView打开网页
        webView.setWebViewClient(WebViewClient());

    }

    fun initWebView() {

        webView.setSaveEnabled(false)
        // 常用
        val ws = webView.settings as WebSettings
        // 网页内容的宽度是否可大于WebView控件的宽度
        //setUseWideViewPort方法设置webview推荐使用的窗口。
        // setLoadWithOverviewMode方法是设置webview加载的页面的模式。
        ws.loadWithOverviewMode = false
        ws.javaScriptCanOpenWindowsAutomatically = true;
        ws.pluginState = WebSettings.PluginState.ON
        // 保存表单数据
        ws.saveFormData = true
        // 是否应该支持使用其屏幕缩放控件和手势缩放
        ws.setSupportZoom(true)
        ws.builtInZoomControls = true
        ws.displayZoomControls = false
        // 启动应用缓存
        ws.setAppCacheEnabled(true)
        // 设置缓存模式
        ws.cacheMode = WebSettings.LOAD_DEFAULT
        // setDefaultZoom api19被弃用
        // 设置此属性，可任意比例缩放。
        ws.useWideViewPort = true //可任意比例缩放
        // 不缩放
        webView.setInitialScale(100);
        // 告诉WebView启用JavaScript执行。默认的是false。 网页需要支持JavaScript,则需要设置为true
        ws.javaScriptEnabled = true
        // 页面加载好以后，再放开图片
        ws.blockNetworkImage = false
        // 使用localStorage则必须打开
        ws.domStorageEnabled = true
        // 排版适应屏幕
        ws.layoutAlgorithm = (WebSettings.LayoutAlgorithm.NARROW_COLUMNS)//排版适应屏幕
        // WebView是否新窗口打开(加了后可能打不开网页)
        ws.setSupportMultipleWindows(true);
        // webview从5.0开始默认不允许混合模式,https中不能加载http资源,需要设置开启。
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ws.mixedContentMode = (WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }

        ws.allowFileAccess = (true)
        ws.savePassword = (true)

        /** 设置字体默认缩放大小(改变网页字体大小,setTextSize api14被弃用)*/
        ws.setTextZoom(100)
    }


    //重力传感器
    var sensorHelper: MySensorHelper? = null

    fun initData() {
        //初始化重力感应,传入activity
        sensorHelper = MySensorHelper(this);
        //开启重力感应
        sensorHelper!!.enable()
    }

    override fun onDestroy() {
        super.onDestroy()
        //关闭重力传感器
        sensorHelper?.disable()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)
        webView = findViewById(R.id.webView)
        webView.loadUrl("http://baidu.com")//加载Web资源
//        数据分心功能的
//        initWebView()
//        initView()
//         数据功能   测试结果    功能完善
//        webView.loadUrl("file:///android_asset/example.html")//加载本地资源
//        initData()
    }

    //
    fun void(){

    }
    // 数据功能  // 数据 天气

    fun desty() {
        System.out.println()
        println("数据功能")
        print("" + sensorHelper.apply {
            MySensorHelper.ScreenState {
                println("ceshu  数据")
                print("数据")
            }
        })

        // 数据 ,  测试 功能
//        println("shuju" + R.id.Circle)
        //数据功能 打印机
    }

    // 测试功能  数据
    fun test() {
        //数据，只能
        // 测试数据功能
    }

    fun  shuju(){
        println("message ")
    }

    //
//    class  Xxx: WebViewClient() {
//        override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
//            return super.shouldOverrideUrlLoading(view, request)
//        }
//    }




/*    // 进入全屏的时候
    @Override
    public void onShowCustomView(View view, CustomViewCallback callback) {
// 赋值给callback
        customViewCallback = callback;
// 设置webView隐藏
        webview.setVisibility(View.GONE);
// 声明video，把之后的视频放到这里面去
        FrameLayout video = (FrameLayout) findViewById(R.id.video);
// 将video放到当前视图中
        video.addView(view);
// 横屏显示
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
// 设置全屏
        setFullScreen();
    }
// 退出全屏的时候
    @Override
    public void onHideCustomView() {
        if (customViewCallback != null) {
// 隐藏掉
            customViewCallback.onCustomViewHidden();
        }
// 用户当前的首选方向
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_USER);
// 退出全屏
        quitFullScreen();
// 设置WebView可见
        webview.setVisibility(View.VISIBLE);
    }*/


}

class XWebChromeClient : WebChromeClient() {
    override fun onProgressChanged(view: WebView?, newProgress: Int) {
        super.onProgressChanged(view, newProgress)
        if (newProgress > 20) {
//            webView.loadUrl(BrowserJsInject.fullScreenByJs(luyan_bean.videoPath));


        }
    }

}

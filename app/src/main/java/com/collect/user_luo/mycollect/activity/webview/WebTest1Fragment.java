package com.collect.user_luo.mycollect.activity.webview;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.collect.user_luo.mycollect.R;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Set;


/**
 * 测试webView 的测试例子
 */
public class WebTest1Fragment extends Fragment {
    WebView mWebview;
    WebSettings mWebSettings;
    TextView beginLoading, endLoading, loading, mtitle;


    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_web_test1, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mWebview = view.findViewById(R.id.webView1);
        beginLoading = view.findViewById(R.id.text_beginLoading);
        endLoading = view.findViewById(R.id.text_endLoading);
        loading = view.findViewById(R.id.text_Loading);
        mtitle = view.findViewById(R.id.title);

        mWebSettings = mWebview.getSettings();

        mWebview.loadUrl("https://www.baidu.com/");
//        mWebview.loadUrl("http://www.xitongtiandi.net/soft_yy/2078.html");


        //设置不用系统浏览器打开,直接显示在当前Webview
        mWebview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });

        //设置WebChromeClient类
        mWebview.setWebChromeClient(new WebChromeClient() {
            //获取网站标题
            @Override
            public void onReceivedTitle(WebView view, String title) {
                System.out.println("标题在这里");
                mtitle.setText(title);
            }


            //获取加载进度
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress < 100) {
                    String progress = newProgress + "%";
                    loading.setText(progress);
                } else if (newProgress == 100) {
                    String progress = newProgress + "%";
                    loading.setText(progress);
                }
            }
        });


        //设置WebViewClient类
        mWebview.setWebViewClient(new WebViewClient() {
            //设置加载前的函数
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                System.out.println("开始加载了");
                beginLoading.setText("开始加载了");
            }

            //设置结束加载函数
            @Override
            public void onPageFinished(WebView view, String url) {
                endLoading.setText("结束加载了");
            }
        });
    }


    //销毁Webview
    @Override
    public void onDestroy() {
        if (mWebview != null) {
            mWebview.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
            mWebview.clearHistory();

            ((ViewGroup) mWebview.getParent()).removeView(mWebview);
            mWebview.destroy();
            mWebview = null;
        }
        super.onDestroy();
    }

    private void test() {
        WebView mWebView = null;
               mWebView.setWebChromeClient(
                new WebChromeClient() {
                    // 拦截输入框(原理同方式2)
                    // 参数message:代表promt（）的内容（不是url）
                    // 参数result:代表输入框的返回值
                    @Override
                    public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result) {
                        // 根据协议的参数，判断是否是所需要的url(原理同方式2)
                        // 一般根据scheme（协议格式） & authority（协议名）判断（前两个参数）
                        //假定传入进来的 url = "js://webview?arg1=111&arg2=222"（同时也是约定好的需要拦截的）

                        Uri uri = Uri.parse(message);
                        // 如果url的协议 = 预先约定的 js 协议
                        // 就解析往下解析参数
                        if (uri.getScheme().equals("js")) {
                            // 如果 authority  = 预先约定协议里的 webview，即代表都符合约定的协议
                            // 所以拦截url,下面JS开始调用Android需要的方法
                            if (uri.getAuthority().equals("webview")) {
                                // 执行JS所需要调用的逻辑
                                System.out.println("js调用了Android的方法");
                                // 可以在协议上带有参数并传递到Android上
                                HashMap<String, String> params = new HashMap<>();
                                Set<String> collection = uri.getQueryParameterNames();

                                //参数result:代表消息框的返回值(输入值)
                                result.confirm("js调用了Android的方法成功啦");
                            }
                            return true;
                        }
                        return super.onJsPrompt(view, url, message, defaultValue, result);
                    }

                    // 通过alert()和confirm()拦截的原理相同，此处不作过多讲述
                    // 拦截JS的警告框
                    @Override
                    public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                        return super.onJsAlert(view, url, message, result);
                    }

                    // 拦截JS的确认框
                    @Override
                    public boolean onJsConfirm(WebView view, String url, String message, JsResult result) {
                        return super.onJsConfirm(view, url, message, result);
                    }
                }
        );


    }

}






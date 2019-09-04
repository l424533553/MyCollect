package com.luofx.base;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.luofx.help.CrashHandler;
import com.luofx.listener.VolleyListener;
import com.luofx.listener.VolleyStringListener;
import com.luofx.utils.log.MyLog;
import com.luofx.utils.net.MyNetworkUtil;
import com.xuanyuan.library.help.CharsetJsonRequest;

import org.json.JSONObject;

import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 说明：
 * 作者：User_luo on 2018/7/24 13:52
 * 邮箱：424533553@qq.com
 * 需要导入Volley.jar 或者  远程依赖
 */
@SuppressLint("Registered")
public class MyBaseApplication extends BaseBuglyApplication {

    String TAG = "网络不可用，请检查网络";



    @Override
    public void onCreate() {
        super.onCreate();

    }


    /**
     * Volley Get 请求方式
     *
     * @param url      网址
     * @param listener 监听请求
     * @param flag     旗标
     */
    public void volleyGet(String url, final VolleyListener listener, final int flag) {
        if (MyNetworkUtil.isNetworkAvailable(this)) {
            CharsetJsonRequest request = new CharsetJsonRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject jsonObject) {
                    listener.onResponse(jsonObject, flag);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError volleyError) {
                    listener.onResponse(volleyError, flag);
                }
            });
            getQueues().add(request);
        }
    }

    /**
     * Volley Get 请求方式
     *
     * @param url      网址
     * @param listener 监听请求
     * @param flag     旗标
     */
    public void volleyStringGet(String url, final VolleyStringListener listener, final int flag) {
        if (MyNetworkUtil.isNetworkAvailable(this)) {
            StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    listener.onResponse(response, flag);

//                    MyLog.logTest(response);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    listener.onResponseError(error, flag);
                }
            });
            getQueues().add(request);
        } else {
            Toast.makeText(context, "网络不可用", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Volley Post请求方式
     *
     * @param url            网络地址
     * @param map            post请求参数
     * @param volleyListener 监听接口
     */
    public void volleyPost1(String url, final Map<String, String> map, final VolleyListener volleyListener, final int flag) {
        if (MyNetworkUtil.isNetworkAvailable(this)) {
            CharsetJsonRequest request = new CharsetJsonRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject jsonObject) {
                    volleyListener.onResponse(jsonObject, flag);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError volleyError) {
                    volleyListener.onResponse(volleyError, flag);
                }
            }) {
                @Override
                protected Map<String, String> getParams() {
                    return map;
                }
            };

            MyLog.logTest(request.toString());
            getQueues().add(request);

        } else {
//            Toast.makeText(context, TAG, Toast.LENGTH_SHORT).show();
        }
    }

    public void volleyPost2(String url, JSONObject jsonRequest, final VolleyListener volleyListener, final int flag) {
        if (MyNetworkUtil.isNetworkAvailable(this)) {
            CharsetJsonRequest request = new CharsetJsonRequest(Request.Method.POST, url, jsonRequest, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject jsonObject) {
                    volleyListener.onResponse(jsonObject, flag);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError volleyError) {
                    volleyListener.onResponse(volleyError, flag);
                }
            });
//            RequestBody body = RequestBody.create(MEDia_MEDIA_TYPE_JSON, json);

            MyLog.logTest(request.toString());
            getQueues().add(request);

        } else {
//            Toast.makeText(context, TAG, Toast.LENGTH_SHORT).show();
        }
    }

    public void volleyPost(String url, final Map<String, String> map, final VolleyStringListener volleyListener, final int flag) {
        if (MyNetworkUtil.isNetworkAvailable(this)) {
            StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    volleyListener.onResponse(response, flag);
//                  MyLog.logTest(response);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    volleyListener.onResponseError(error, flag);
                }
            }) {
                @Override
                protected Map<String, String> getParams() {
                    return map;
                }
//                @Override
//                public Map<String, String> getHeaders() {
//                    Map<String, String> headers = new HashMap<>();
////                    headers.put("Charset", "UTF-8");
//                    headers.put("Content-Type", "application/json");
////                    headers.put("Accept-Encoding", "gzip,deflate");
//                    return headers;
//                }
            };

//            CharsetJsonRequest request = new CharsetJsonRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
//                @Override
//                public void onResponse(JSONObject jsonObject) {
//                    volleyListener.onResponse(jsonObject, flag);
//                }
//            }, new Response.ErrorListener() {
//                @Override
//                public void onErrorResponse(VolleyError volleyError) {
//                    volleyListener.onResponse(volleyError, flag);
//                }
//            }) {
//                @Override
//                protected Map<String, String> getParams() {
//                    return map;
//                }
//            };

            getQueues().add(stringRequest);
        } else {
//            Toast.makeText(context, TAG, Toast.LENGTH_SHORT).show();
        }
    }

    public void volleyPostString(String url, final Map<String, String> map, final VolleyStringListener volleyListener, final int flag) {
        if (MyNetworkUtil.isNetworkAvailable(this)) {
            StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    volleyListener.onResponse(response, flag);
//                    MyLog.logTest(response);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    volleyListener.onResponseError(error, flag);
                }
            }) {
                @Override
                protected Map<String, String> getParams() {
                    return map;
                }
            };

//            CharsetJsonRequest request = new CharsetJsonRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
//                @Override
//                public void onResponse(JSONObject jsonObject) {
//                    volleyListener.onResponse(jsonObject, flag);
//                }
//            }, new Response.ErrorListener() {
//                @Override
//                public void onErrorResponse(VolleyError volleyError) {
//                    volleyListener.onResponse(volleyError, flag);
//                }
//            }) {
//                @Override
//                protected Map<String, String> getParams() {
//                    return map;
//                }
//            };
            getQueues().add(stringRequest);

        } else {
//            Toast.makeText(context, TAG, Toast.LENGTH_SHORT).show();
        }
    }


}




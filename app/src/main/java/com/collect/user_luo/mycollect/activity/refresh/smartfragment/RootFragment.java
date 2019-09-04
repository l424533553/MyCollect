package com.collect.user_luo.mycollect.activity.refresh.smartfragment;


import android.content.Context;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.collect.user_luo.mycollect.R;
import com.collect.user_luo.mycollect.utils.Mylog;

/**
 *
 * A simple {@link Fragment} subclass.
 */
public abstract class RootFragment extends Fragment {
    public RootFragment() {
        // Required empty public constructor
    }


/*    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_root, container, false);
    }*/

    //根部view
    private View rootView;
    protected Context context;
    private Boolean hasInitData = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        context = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = initView(inflater,container);
        }
        return rootView;
    }

    /**
     * 初始化过数据就别再初始化啦
     * @param savedInstanceState
     */
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (!hasInitData) {
            initData();
            hasInitData = true;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ((ViewGroup) rootView.getParent()).removeView(rootView);
    }

    /**
     * 子类实现初始化View操作
     */
    protected abstract View initView(LayoutInflater inflater,ViewGroup container);


    /**
     * 子类实现初始化数据操作(子类自己调用)
     */
    public abstract void initData();

//    /**
////     * 封装从网络下载数据
////     */
////    protected void loadData(HttpRequest.HttpMethod method, String url,
////                            RequestParams params, RequestCallBack<String> callback) {
////        if (0 == NetUtils.isNetworkAvailable(getActivity())) {
////            new CustomToast(getActivity(), "无网络，请检查网络连接！", 0).show();
////        } else {
////            NetUtils.loadData(method, url, params, callback);
////        }
////    }


}

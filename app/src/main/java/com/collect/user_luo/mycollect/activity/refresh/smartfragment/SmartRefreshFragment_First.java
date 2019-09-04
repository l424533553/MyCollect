package com.collect.user_luo.mycollect.activity.refresh.smartfragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.collect.user_luo.mycollect.R;
import com.collect.user_luo.mycollect.adapter.StringAdapter;
import com.collect.user_luo.mycollect.data.HomeData;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SmartRefreshFragment_First extends RootFragment {

    private String name;

    public SmartRefreshFragment_First() {
        // Required empty public constructor
    }



//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        View view=inflater.inflate(R.layout.fragment_smart_refresh_fragment__first, container, false);
//        final TextView textView= view.findViewById(R.id.textView);
//        textView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                textView.setText("我改变了，第一个页面");
//            }
//        });
//        return view;
//    }

    private ListView listView;
    private List<String> data;
    private StringAdapter adapter;
    private void initView(View view){
        listView=view.findViewById(R.id.listView);
    }

    private void initRefresh(View view){
        RefreshLayout refreshLayout = view.findViewById(R.id.refreshLayout);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshlayout.finishRefresh(2000/*,false*/);//传入false表示刷新失败,2秒后完成加载
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                refreshlayout.finishLoadMore(2000/*,false*/);//传入false表示加载失败
            }
        });

    }

    @Override
    protected View initView(LayoutInflater inflater,ViewGroup container) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_smart_refresh_fragment__first, container, false);
//        final TextView textView= view.findViewById(R.id.textView);
//        textView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                textView.setText("我改变了，第一个页面");
//            }
//        });
        initRefresh(view);
        initView(view);
        return view;
    }

    @Override
    public void initData() {
        data = new ArrayList<>();
        data.addAll(HomeData.getRefreshData());
        adapter = new StringAdapter(getActivity(), data);
        listView.setAdapter(adapter);
    }

}

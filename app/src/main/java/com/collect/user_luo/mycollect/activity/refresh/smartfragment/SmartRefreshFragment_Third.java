package com.collect.user_luo.mycollect.activity.refresh.smartfragment;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.collect.user_luo.R;
import com.collect.user_luo.mycollect.adapter.StringAdapter;
import com.collect.user_luo.mycollect.data.HomeData;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.header.BezierRadarHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SmartRefreshFragment_Third extends RootFragment {


    public SmartRefreshFragment_Third() {
        // Required empty public constructor
    }

    //
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        View view=inflater.inflate(R.layout.fragment_smart_refresh_fragment__third, container, false);
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

    private void initView(View view) {
        listView = view.findViewById(R.id.listView);
    }


    private void initRefresh(View view) {
        RefreshLayout refreshLayout = view.findViewById(R.id.refreshLayout);
        //设置 Header 为 贝塞尔雷达 样式
        refreshLayout.setRefreshHeader(new BezierRadarHeader(getContext()).setEnableHorizontalDrag(true));
        //设置 Footer 为 球脉冲 样式
        refreshLayout.setRefreshFooter(new BallPulseFooter(getContext()).setSpinnerStyle(SpinnerStyle.Scale));

        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
//                refreshlayout.finishRefresh(2000/*,false*/);//传入false表示刷新失败,2秒后完成加载


                data.clear();
                data.addAll(HomeData.getRefreshData());
                adapter.notifyDataSetChanged();
                refreshlayout.finishRefresh();
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
//                refreshlayout.finishLoadMore(2000/*,false*/);//传入false表示加载失败

                data.clear();
                data.addAll(HomeData.getRefreshData2());
                adapter.notifyDataSetChanged();
                refreshlayout.finishLoadMore();
            }
        });

    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.fragment_smart_refresh_fragment__first, container, false);

        initView(view);
        initRefresh(view);
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

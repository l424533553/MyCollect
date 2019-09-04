package com.collect.user_luo.mycollect.activity.refresh.smartfragment;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.collect.user_luo.mycollect.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SmartRefreshFragment_Fourth extends RootFragment {


    public SmartRefreshFragment_Fourth() {
        // Required empty public constructor
    }


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

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container) {
        View view=inflater.inflate(R.layout.fragment_smart_refresh_fragment__fourth, container, false);
        final TextView textView= view.findViewById(R.id.textView);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText("我改变了，第四个页面");
            }
        });
        return view;
    }

    @Override
    public void initData() {

    }

}

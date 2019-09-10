package com.collect.user_luo.mycollect.activity.refresh.smartfragment;

import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.collect.user_luo.R;


/**
 * 加入指定的 Header 和 Footer
 * 方法二 XML布局文件指定 （优先级中）
 * A simple {@link Fragment} subclass.
 */
public class SmartRefreshFragment_Second extends RootFragment {


    public SmartRefreshFragment_Second() {
        // Required empty public constructor
    }


//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        View view=inflater.inflate(R.layout.fragment_smart_refresh_fragment__second, container, false);
//        final TextView textView= view.findViewById(R.id.textView);
//        textView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                textView.setText("我改变了，第二个页面");
//            }
//        });
//        return view;
//    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container) {
        View view=inflater.inflate(R.layout.fragment_smart_refresh_fragment__second, container, false);
//        final TextView textView= view.findViewById(R.id.textView);
//        textView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                textView.setText("我改变了，第二个页面");
//            }
//        });
        return view;
    }

    @Override
    public void initData() {

    }


}

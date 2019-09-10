package com.collect.user_luo.mycollect.activity.eventbus;


import android.arch.lifecycle.Observer;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.collect.user_luo.R;
import com.collect.user_luo.mycollect.adapter.StringAdapter;
import com.collect.user_luo.mycollect.data.HomeData;
import com.jeremyliao.liveeventbus.LiveEventBus;

import java.util.ArrayList;
import java.util.List;

import static com.collect.user_luo.mycollect.config.IConstants.KEY_TEST_OBSERVE;

/**
 * A simple {@link Fragment} subclass.
 */
public class LiveEventBusFragment extends Fragment implements View.OnClickListener {


    private List<String> data;
    private StringAdapter adapter;

    private Context context;
    private EditText etContent;

    public LiveEventBusFragment() {
        // Required empty public constructor
    }


    private void initView(View view) {
        view.findViewById(R.id.btnSendMessage).setOnClickListener(this);
        etContent = view.findViewById(R.id.etContent);


    }

    private void initData() {
        data = new ArrayList<>();
        data.addAll(HomeData.getRefreshData());
        adapter = new StringAdapter(getActivity(), data);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_live_eventbus, container, false);
        context = getContext();
        initView(view);
        initData();
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSendMessage:
                String message = etContent.getText().toString();
                //发送信息
                LiveEventBus.get().with(KEY_TEST_OBSERVE).post(message);
                break;
        }
    }

}

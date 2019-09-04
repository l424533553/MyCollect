package com.collect.user_luo.mycollect.activity.refresh.smartfragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.collect.user_luo.mycollect.R;
import com.collect.user_luo.mycollect.adapter.StringAdapter;
import com.collect.user_luo.mycollect.data.HomeData;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SwipeRefreshFragment extends Fragment {

    private ListView listView;
    private List<String> data;
    private StringAdapter adapter;

    public SwipeRefreshFragment() {
        // Required empty public constructor
    }


    private void initView(View view) {
        listView = view.findViewById(R.id.listView);

    }

    private void initData() {
        data = new ArrayList<>();
        data.addAll(HomeData.getRefreshData());
        adapter = new StringAdapter(getActivity(), data);
        listView.setAdapter(adapter);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_refresh_swipe, container, false);
        initView(view);
        initData();

        return view;
    }

}

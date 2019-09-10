package com.collect.user_luo.mycollect.activity.refresh;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.collect.user_luo.R;
import com.collect.user_luo.mycollect.WhatActivity;
import com.collect.user_luo.mycollect.adapter.StringAdapter;
import com.collect.user_luo.mycollect.config.IConstants;
import com.collect.user_luo.mycollect.data.HomeData;

import java.util.List;

public class RefreshActivity extends AppCompatActivity implements IConstants, AdapterView.OnItemClickListener{


    private GridView gridView;
    private StringAdapter adapter;
    private List<String> data;

    private void initView(){
        gridView=findViewById(R.id.gridView);

    }
    private void initData(){
        data= HomeData.getRefreshActivityData();
        adapter=new StringAdapter(this,data);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(this);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refresh);
        initView();
        initData();


    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String stringTag=data.get(position);
        Intent intent=new Intent(this, WhatActivity.class);
        //从Refresh界面中跳转的数据
        intent.putExtra(JUMP_FROM_SOURCE,JUMP_FROM_SOURCE_REFRESH);
        intent.putExtra(JUMP_FROM_SOURCE_REFRESH,stringTag);
        startActivity(intent);
    }
}

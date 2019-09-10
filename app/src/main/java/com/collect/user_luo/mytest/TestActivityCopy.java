package com.collect.user_luo.mytest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.collect.user_luo.R;
import com.collect.user_luo.mycollect.view.ItemGroup;

public class TestActivityCopy extends AppCompatActivity {
    private ItemGroup igHome;
    private ItemGroup igSoft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_test);
        igHome = findViewById(R.id.ig_home);
        igSoft = findViewById(R.id.ig_soft);
        igHome.setItemOnClickListener(new ItemGroup.ItemOnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(TestActivityCopy.this, "当前点击了首页", Toast.LENGTH_SHORT).show();
            }
        });
        igSoft.setItemOnClickListener(new ItemGroup.ItemOnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(TestActivityCopy.this, "当前点击了软件介绍", Toast.LENGTH_SHORT).show();
            }
        });

        int a=4;
        int b=7;
        int sum=a>b?0:5;

    }
}

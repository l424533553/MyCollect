package com.collect.user_luo.mytest;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.collect.user_luo.R;
import com.collect.user_luo.mycollect.view.ItemGroup;

public class TestActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ItemGroup igHome = findViewById(R.id.ig_home);
        ItemGroup igSoft = findViewById(R.id.ig_soft);

        findViewById(R.id.btnStartIntent1).setOnClickListener(this);

        ViewPager.DecorView view;

        // 使用 功能

//      使用  管理费



    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnStartIntent1:
                Intent intent = new Intent("com.xuanyuan.xinyu.ACTION_START");     //隐式Intent;
                startActivity(intent);

                /* 打开 隐式意图的其他方式 ，这是 打开浏览器的方式  *  ********************/
//                Intent intent1 = new Intent(Intent.ACTION_VIEW);             //更多隐式Intent;
//                intent1.setData(Uri.parse("http://www.baidu.com"));
//                startActivity(intent1);

                break;
            default:
                break;
        }

    }
}

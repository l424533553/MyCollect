package com.collect.user_luo.mycollect.activity.mine;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.collect.user_luo.mycollect.R;
import com.xuanyuan.library.MyTestActivity;
import com.xuanyuan.library.base.activity.MyAppCompatActivity;


public class MineActivity extends MyAppCompatActivity implements View.OnClickListener {

    @Override
    public boolean isCheckPermissions() {
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine);

        final ImageView iv = findViewById(R.id.ivAnimate);
        //将animated-selector设置为控件图像
        Drawable drawable = getResources().getDrawable(R.drawable.animated_selector);
        iv.setImageDrawable(drawable);

        findViewById(R.id.btnOn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //将控件设置为打开状态
                iv.setSelected(true);
            }
        });


        // 测试开发是
        findViewById(R.id.btnOff).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //将控件设置为关闭状态
                iv.setSelected(false);
            }
        });

        findViewById(R.id.btnHardwareInfo).setOnClickListener(this);
        findViewById(R.id.btnFuncTest).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnHardwareInfo:
                jumpActivity(HardwareActivity.class, false, false);
                break;
            case R.id.btnFuncTest:
                jumpActivity(MyTestActivity.class, false, false);
                break;
            default:
                break;
        }
    }
}

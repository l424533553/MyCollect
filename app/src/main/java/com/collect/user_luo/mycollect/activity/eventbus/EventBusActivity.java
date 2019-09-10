package com.collect.user_luo.mycollect.activity.eventbus;

import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.collect.user_luo.R;
import com.collect.user_luo.mycollect.WhatActivity;
import com.jeremyliao.liveeventbus.LiveEventBus;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import static com.collect.user_luo.mycollect.config.IConstants.JUMP_FROM_SOURCE;
import static com.collect.user_luo.mycollect.config.IConstants.JUMP_FROM_SOURCE_EVENTBUS;
import static com.collect.user_luo.mycollect.config.IConstants.JUMP_FROM_SOURCE_REFRESH;
import static com.collect.user_luo.mycollect.config.IConstants.KEY_TEST_OBSERVE;

/**
 * EventBus 总线
 * 与handler 相比各有优势
 * Handler : 清楚数据源、数据状态、数据状态     EventBus :每个订阅者都会收到消息，情况多了 会比较麻烦， 但是开销小 ，适合
 * <p>
 * <p>
 * EventBus 这个可以用到夜间模式，或者在app中切换语言的时候中，就不会出现闪屏的问题；
 */
public class EventBusActivity extends AppCompatActivity implements View.OnClickListener {


    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_bus);

        findViewById(R.id.btnEventBus).setOnClickListener(this);

        EventBus.getDefault().register(this);

        textView = findViewById(R.id.textView);
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new MessageEvent("Mr.sorrow", "123456"));
            }
        });

        //观察者注册,订阅消息  ，不需要主动取消订阅
        LiveEventBus.get()
                .with(KEY_TEST_OBSERVE, String.class)
                .observe(this, new Observer<String>() {
                    @Override
                    public void onChanged(@Nullable String s) {
                        Toast.makeText(EventBusActivity.this, s, Toast.LENGTH_SHORT).show();
                        textView.setText(s);
                    }
                });

        LiveEventBus.get()
                .with("sticky_key", String.class)
                .observeStickyForever(new Observer<String>() {
                    @Override
                    public void onChanged(@Nullable String s) {

                    }
                });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(MessageEvent event) {
//      tv_c_result.setText(event.msg);
        textView.setText(event.getName());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    // 数据功能测试  Test
    private void testTest() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnEventBus:

                Intent intent = new Intent(this, WhatActivity.class);
                //从Refresh界面中跳转的数据
                intent.putExtra(JUMP_FROM_SOURCE, JUMP_FROM_SOURCE_EVENTBUS);
                startActivity(intent);
                break;
        }

    }
}

package com.collect.user_luo.mycollect.activity.eventbus;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.jeremyliao.liveeventbus.LiveEventBus;

import static com.collect.user_luo.mycollect.config.IConstants.KEY_TEST_OBSERVE;

/**
 * 作者：罗发新
 * 时间：2019/4/25 0025    星期四
 * 邮件：424533553@qq.com
 * 说明：
 */
public class LiveBusHelper {

    private void ffa(final Context context, LifecycleOwner owner) {
        //观察者注册,订阅消息  ，不需要主动取消订阅
        LiveEventBus.get()
                .with(KEY_TEST_OBSERVE, String.class)
                .observe(owner, new Observer<String>() {
                    @Override
                    public void onChanged(@Nullable String s) {
                        Toast.makeText(context, s, Toast.LENGTH_SHORT).show();

                    }
                });

        // 带有observeForever的不具有生命感知，需要手动取消订阅，
        Observer observer = null;
        LiveEventBus.get()
                .with(KEY_TEST_OBSERVE, String.class)
                .observeForever(observer);
        LiveEventBus.get()
                .with(KEY_TEST_OBSERVE, String.class)
                .removeObserver(observer);

        LiveEventBus.get()
                .with(KEY_TEST_OBSERVE, String.class)
                .observeSticky(owner, new Observer<String>() {
                    @Override
                    public void onChanged(@Nullable String s) {
                        Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
                    }
                });

        // 支持设置Sticky模式，这样订阅者可以接收到订阅之前发送的消息
        LiveEventBus.get()
                .with("sticky_key", String.class)
                .observeStickyForever(new Observer<String>() {
                    @Override
                    public void onChanged(@Nullable String s) {
                    }
                });
    }

    private void testasf(){
        // 测试应用工鞥
    }

}

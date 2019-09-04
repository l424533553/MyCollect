package com.collect.user_luo.mycollect.activity.mine;

import android.annotation.SuppressLint;
import android.os.BatteryManager;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.CompoundButton;
import android.widget.ScrollView;
import android.widget.Switch;
import android.widget.TextView;

import com.collect.user_luo.mycollect.R;
import com.collect.user_luo.mycollect.config.IConstants;
import com.xuanyuan.library.utils.MyPreferenceUtils;
import com.xuanyuan.library.base.activity.MyAppCompatActivity;
import com.xuanyuan.library.help.HardwareHelper;

import static com.xuanyuan.library.config.IConfig.SP_FLAG_BOOLEAN_SCREEN_STATE;

/**
 * 作者：罗发新
 * 时间：2019/4/24 0024    星期三
 * 邮件：424533553@qq.com
 * 说明：1.其中包含了一项节约电量：
 * 方法一：进行屏幕待机和唤醒管控
 */
public class HardwareActivity extends MyAppCompatActivity implements View.OnClickListener, IConstants {

    @Override
    public boolean isCheckPermissions() {
        return false;
    }

    private TextView tvChargeState;
    private Switch stScreenState;
    private boolean screenState;

    /**
     * 硬件信息帮助类
     */
    private HardwareHelper hardwareHelper;

    @SuppressLint("SetTextI18n")
    private void initView() {
        findViewById(R.id.btnChargeState).setOnClickListener(this);
        tvChargeState = findViewById(R.id.tvChargeState);
        setViewChargeState();

        stScreenState = findViewById(R.id.stScreenState);
        setViewScreenState();
        scrollView = findViewById(R.id.slView);

    }

    ScrollView scrollView;

    /**
     * 设置屏幕状态的,设置
     * getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);  // 保持屏幕长亮
     */
    private void setViewScreenState() {
        stScreenState.setChecked(screenState);
        stScreenState.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                MyPreferenceUtils.getSp(context).edit().putBoolean(SP_FLAG_BOOLEAN_SCREEN_STATE, isChecked).apply();
            }
        });
    }

    /**
     * 设置充电状态
     */
    private void setViewChargeState() {
        switch (hardwareHelper.getChargeState()) {
            case BatteryManager.BATTERY_PLUGGED_AC:
                tvChargeState.setText("有线充电状态");
                break;
            case BatteryManager.BATTERY_PLUGGED_USB:
                tvChargeState.setText("USB充电状态");
                break;
            case BatteryManager.BATTERY_PLUGGED_WIRELESS:
                tvChargeState.setText("无线充电状态");
                break;
            default:
                tvChargeState.setText("充电状态未知");
                break;
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 设置没有标题头
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_hardware);
        hardwareHelper = new HardwareHelper(context);
        initView();
    }

    @Override
    public void cancelScreenOn() {

    }

    @Override
    public void setTitel() {
        TextView tvTitle = findViewById(R.id.tv_title);
        tvTitle.setText("设备信息");
    }

    @Override
    public void doBack(View view) {
        onBackPressed();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnChargeState:
                setViewChargeState();
                break;
            default:
                break;
        }

    }
}

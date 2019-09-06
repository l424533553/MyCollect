package com.collect.user_luo.mycollect.activity.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.collect.user_luo.mycollect.R;
import com.collect.user_luo.mycollect.utils.BlueToothUtils;

public class BlueToothActivity extends AppCompatActivity {
    private static BroadcastReceiver mReceiver1 = null;
    private TextView tvTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blue_tooth);

        tvTextView = findViewById(R.id.tv);

//        BlueToothReceiver btReceiver = new BlueToothReceiver();
//        Intent intent = getIntent();
//        Bundle bundle = intent.getExtras();
//        if (bundle != null) {
//            blueString = bundle.getString("Bluetooth");
//            tvTextView.setText(blueString);
//            Toast.makeText(this, blueString, Toast.LENGTH_LONG).show();
//        }

        boolean isBlueToothAble = BlueToothUtils.checkBluetoothValid(this, this);
        Mylog.log("蓝牙设备是否可用" + isBlueToothAble);

        mReceiver1 = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                Mylog.log("蓝牙动作" + action);

                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                Toast.makeText(BlueToothActivity.this, "蓝牙状态改变广播 !", Toast.LENGTH_LONG).show();

                // 蓝牙状态 改变了
                if (BluetoothAdapter.ACTION_STATE_CHANGED.equals(action)) {
                    Mylog.log("蓝牙状态变了");
                    //获取 蓝牙的状态信息
                    int state = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, BluetoothAdapter.ERROR);

                    Mylog.log("蓝牙的状态" + state);
                    switch (state) {
                        case BluetoothAdapter.STATE_OFF:
                            Log.d("aaa", "STATE_OFF 手机蓝牙关闭");
                            break;
                        case BluetoothAdapter.STATE_TURNING_OFF:
                            Log.d("aaa", "STATE_TURNING_OFF 手机蓝牙正在关闭");
                            break;
                        case BluetoothAdapter.STATE_ON:
                            Log.d("aaa", "STATE_ON 手机蓝牙开启");
                            break;
                        case BluetoothAdapter.STATE_TURNING_ON:
                            Log.d("aaa", "STATE_TURNING_ON 手机蓝牙正在开启");
                            break;
                    }

                }

                if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                    Toast.makeText(BlueToothActivity.this, device.getName() + " 设备已发现！！", Toast.LENGTH_LONG).show();
                    Mylog.log(device.getName() + " 设备已发现");
                    tvTextView.setText("设备已发现");
                } else if (BluetoothDevice.ACTION_ACL_CONNECTED.equals(action)) {
                    tvTextView.setText("蓝牙已连接");
                    Mylog.log(device.getName() + " 蓝牙已连接");
                    Toast.makeText(BlueToothActivity.this, device.getName() + "已连接", Toast.LENGTH_LONG).show();
                } else if (BluetoothDevice.ACTION_ACL_DISCONNECT_REQUESTED.equals(action)) {
                    Toast.makeText(BlueToothActivity.this, device.getName() + "正在断开蓝牙连接。。。", Toast.LENGTH_LONG).show();
                    Mylog.log(device.getName() + " 正在断开蓝牙连接");

                } else if (BluetoothDevice.ACTION_ACL_DISCONNECTED.equals(action)) {
                    Toast.makeText(BlueToothActivity.this, device.getName() + "蓝牙连接已断开！！！", Toast.LENGTH_LONG).show();

                    Mylog.log(device.getName() + " 蓝牙连接已断开");
                    tvTextView.setText("蓝牙连接已断开");
                } else if (action.equals(BluetoothAdapter.STATE_OFF)) {
                    Toast.makeText(BlueToothActivity.this, "蓝牙已关闭", Toast.LENGTH_LONG).show();
                    Mylog.log(device.getName() + " 蓝牙已关闭");
                    tvTextView.setText("蓝牙已关闭！！");
                } else if (action.equals(BluetoothAdapter.STATE_ON)) {
                    Mylog.log(device.getName() + " 蓝牙打开");
                    Toast.makeText(BlueToothActivity.this, "蓝牙打开", Toast.LENGTH_LONG).show();
                    tvTextView.setText("蓝牙已打开！！");
                }
            }
        };

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.bluetooth.a2dp.profile.action.CONNECTION_STATE_CHANGED");
        intentFilter.addAction(android.bluetooth.BluetoothDevice.ACTION_ACL_DISCONNECTED);
        intentFilter.addAction(android.bluetooth.BluetoothDevice.ACTION_ACL_CONNECTED);
        intentFilter.addAction(android.bluetooth.BluetoothDevice.ACTION_ACL_DISCONNECT_REQUESTED);
        intentFilter.addAction("android.bluetooth.BluetoothAdapter.STATE_ON");
        intentFilter.addAction("android.bluetooth.BluetoothAdapter.STATE_OFF");
        intentFilter.addAction(BluetoothAdapter.ACTION_STATE_CHANGED);

        this.registerReceiver(mReceiver1, intentFilter);
    }

    /**
     * 蓝牙模块测试分析 模块
     */
    private void BlueToothTest() {

//        如果BluetoothAdapter 为null，说明android手机没有蓝牙模块。
//        判断蓝牙模块是否开启，blueadapter.isEnabled() true表示已经开启，false表示蓝牙并没启用。
//        启动配置蓝牙可见模式，即进入可配对模式Intent in=new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
//        in.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 200);
//        startActivity(in);  ，200就表示200秒。
//        获取蓝牙适配器中已经配对的设备Set<BluetoothDevice> device=blueadapter.getBondedDevices();
//        还需要在androidManifest.xml中声明蓝牙的权限

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mReceiver1);
    }
}

package com.collect.user_luo.mycollect.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

/**
 * 说明：
 * 作者：User_luo on 2018/5/10 09:55
 * 邮箱：424533553@qq.com
 */
public class BlueToothUtils {

    /**
     * 蓝牙是否  打开可用
     *  需要  判断返回结果  进行下一步  处理
     * @param context
     */
    public static boolean checkBluetoothValid(Context context, final Activity activity) {
        final BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();
        if (adapter == null) {
            AlertDialog dialog = new AlertDialog.Builder(context).setTitle("错误").setMessage("你的设备不具备蓝牙功能!").create();
            dialog.show();
            return false;
        }

        if (!adapter.isEnabled()) {
            AlertDialog dialog = new AlertDialog.Builder(context).setTitle("提示")
                    .setMessage("蓝牙设备未打开,请开启此功能后重试!")
                    .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface arg0, int arg1) {
                            Intent mIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                            activity.startActivityForResult(mIntent, 1);
                        }
                    })
                    .create();
            //  不可以取消 对话框
            dialog.setCancelable(false);
            dialog.show();
            return false;
        }
        return true;
    }


}

package com.luofx.utils.system;

import android.content.Context;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbManager;

import java.util.HashMap;
import java.util.Objects;

/**
 * author: luofaxin
 * date： 2018/11/6 0006.
 * email:424533553@qq.com
 * describe:
 */
public class HardwareUtils {

    public static UsbDevice getUsbDeviceFromName(Context context, String usbName) {
        UsbManager usbManager = (UsbManager) context.getSystemService(Context.USB_SERVICE);
        HashMap<String,UsbDevice> usbDeviceList = Objects.requireNonNull(usbManager).getDeviceList();
        return usbDeviceList.get(usbName);
    }
}

package com.luofx.utils.view;

/**
 * Created by Administrator on 2018/7/28.
 * 按键狂点处理判断
 */
public class ButtonUtils {
    private static long lastClickTime = 0;
    private static long DIFF = 2000;
    private static int lastButtonId = -1;

    public static boolean isFastDoubleClick() {
        return isFastDoubleClick(-1, DIFF);
    }

    public static boolean isFastDoubleClick(int buttonId) {
        return isFastDoubleClick(buttonId, DIFF);
    }

    /**
     * @param diff  不同时间点击
     * @return      是否快速点击
     */
    public static boolean isFastDoubleClick(int buttonId, long diff) {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        if (lastButtonId == buttonId && lastClickTime > 0 && timeD < diff) {
            return true;
        }
        lastClickTime = time;
        lastButtonId = buttonId;
        return false;
    }
}
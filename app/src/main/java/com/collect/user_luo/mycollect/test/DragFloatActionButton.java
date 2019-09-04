//package com.collect.user_luo.mycollect.test;
//
//import android.content.Context;
//import android.graphics.Canvas;
//import android.graphics.Point;
//import android.graphics.Rect;
//import android.util.AttributeSet;
//import android.view.MotionEvent;
//import android.view.View;
//import android.view.WindowManager;
//import android.widget.ImageView;
//
//import com.collect.user_luo.mycollect.R;
//import com.facebook.stetho.common.LogUtil;
//
///**
// * 作者：罗发新
// * 时间：2018/12/18 0018    9:28
// * 邮件：424533553@qq.com
// * 说明：
// */
//public class DragFloatActionButton extends android.support.v7.widget.AppCompatImageView implements View.OnTouchListener, View.OnClickListener {
//
//    public static String TAG = "DragFloatActionButton";
//    private Context context;
//
//    float lastX, lastY;
//    float originX, originY;
//    int screenWidth;
//    int screenHeight;
//    private int originWidth;
//
//    private WindowManager windowManager;
//    //    // 此windowManagerParams变量为获取的全局变量，用以保存悬浮窗口的属性
//    private WindowManager.LayoutParams windowManagerParams;
//
//    /**
//     * 登陆回调接口
//     */
//    private LoginResultCallback resultCallback; //悬浮按钮点击回调
//
//    public DragFloatActionButton(Context context, boolean isForceLogin, LoginResultCallback resultCallback) {
//        this(context, null);
//        OpenIDApplication.getInstance().setForceLogin(isForceLogin);
//        this.resultCallback = resultCallback;
//    }
//
//    public DragFloatActionButton(Context context, AttributeSet attrs) {
//        this(context, attrs, 0);
//    }
//
//    public DragFloatActionButton(Context context, AttributeSet attrs, int defStyleAttr) {
//        super(context, attrs, defStyleAttr);
//        this.context = context;
//
//        // 数据功能 ，测试功能
//        Point screenSize = DisplayUtil.getScreenSize(context);
//        screenWidth = screenSize.x;
//        screenHeight = screenSize.y;
//        setImageResource(R.mipmap.http);
//        setOnTouchListener(this);
//        setOnClickListener(this);
//
//        windowManager = (WindowManager) getContext().getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
//    }
//
//    /**
//     * 设置原始宽度
//     *
//     * @return 获取原始的宽度
//     */
//    public int getOriginWidth() {
//        return originWidth;
//    }
//
//    public void setOriginWidth(int originWidth) {
//        this.originWidth = originWidth;
//    }
//
//    /**
//     * @param event 触摸事件
//     * @return 返回触摸值
//     */
//    @Override
//    public boolean onTouch(View v, MotionEvent event) {
//        windowManagerParams = (WindowManager.LayoutParams) this.getLayoutParams();
//        //获取到状态栏的高度
//        Rect frame = new Rect();
//        getWindowVisibleDisplayFrame(frame);
//        int ea = event.getAction();
//        switch (ea) {
//            case MotionEvent.ACTION_DOWN:
//                lastX = event.getRawX();// 获取触摸事件触摸位置的原始X坐标
//                lastY = event.getRawY();
//                originX = lastX;
//                originY = lastY;
//                break;
//            case MotionEvent.ACTION_MOVE:
//                float dx = event.getRawX() - lastX;
//                float dy = event.getRawY() - lastY;
//                windowManagerParams.x += dx;
//                windowManagerParams.y += dy;
//                LogUtil.d(TAG, "移动距离：dx=" + dx + "，dy=" + dy);
//
//                //数据功能
//                showAllBtn();
//                lastX = (int) event.getRawX();
//                lastY = (int) event.getRawY();
//                break;
//            case MotionEvent.ACTION_UP:
//                float lastMoveDx = Math.abs(event.getRawX() - originX);
//                float lastMoveDy = Math.abs(event.getRawY() - originY);
//                LogUtil.d(TAG, "松开时，移动距离：lastMoveDx=" + lastMoveDx + ", lastMoveDy=" + lastMoveDy);
//                if (lastMoveDx < 10 && lastMoveDy < 10) { //移动距离太小，视为点击，
//                    return false;
//                } else {
//                    updateViewLayout(event);
//                    isFirstClick = true;
//                    return true;
//                }
//        }
//        return false;
//    }
//
//    /**
//     * 显示整个图标
//     */
//    public void showAllBtn() {
//        windowManagerParams.width = originWidth;
//        windowManagerParams.height = originWidth;
//        setImageResource(R.mipmap.http);
//        windowManager.updateViewLayout(this, windowManagerParams); // 刷新显示
//    }
//
//    /**
//     * 悬浮按钮显示在左边
//     */
//    private void showInLeft() {
//        windowManagerParams.x = 0;
//        windowManagerParams.width = originWidth / 2;
//        windowManagerParams.height = originWidth;
//        setImageResource(R.mipmap.btn);
//        //数据功能
//        windowManager.updateViewLayout(this, windowManagerParams); // 刷新显示
//    }
//
//    /**
//     * 悬浮按钮显示在右边
//     */
//    private void showInRight() {
//        windowManagerParams.width = originWidth / 2;
//        windowManagerParams.height = originWidth;
//        // 数据 功能
//        windowManagerParams.x = screenWidth - windowManagerParams.width;
//        setImageResource(Res.drawable(context, "ipay_float_btn_right_hidden"));
//        windowManager.updateViewLayout(this, windowManagerParams); // 刷新显示
//    }
//
//    /**
//     * 悬浮按钮显示在上面
//     */
//    private void showInTop() {
//        windowManagerParams.y = 0;
//        windowManagerParams.width = originWidth;
//        windowManagerParams.height = originWidth / 2;
//        setImageResource(Res.drawable(context, "ipay_float_btn_top_hidden"));
//        windowManager.updateViewLayout(this, windowManagerParams); // 刷新显示
//    }
//
//    /**
//     * 悬浮按钮显示在下面
//     */
//    private void showInBottom() {
//        windowManagerParams.width = originWidth;
//        windowManagerParams.height = originWidth / 2;
//        windowManagerParams.y = screenHeight - windowManagerParams.width;
//        setImageResource(Res.drawable(context, "ipay_float_btn_bottom_hidden"));
//        windowManager.updateViewLayout(this, windowManagerParams); // 刷新显示
//    }
//
//    /**
//     * 更新悬浮图标
//     *
//     * @param event 手动移动事件
//     */
//    public void updateViewLayout(MotionEvent event) {
//        Point center = new Point(screenWidth / 2, screenHeight / 2); //屏幕中心点
//        float xOffset, yOffset;//以屏幕中心点为原点，X轴和Y轴上的偏移量
//        if (event != null) {//手动移动的
//            xOffset = event.getRawX() - center.x;
//            yOffset = event.getRawY() - center.y;
//        } else {//自动隐藏
//            xOffset = lastX - center.x;
//            yOffset = lastY - center.y;
//        }
//        if (Math.abs(xOffset) >= Math.abs(yOffset)) {//向左或向右缩进隐藏
//            if (xOffset <= 0) { //向左缩进
//                showInLeft();
//            } else {
//                showInRight();
//            }
//        } else {//向上或向下缩进隐藏
//            if (yOffset <= 0) {//向上缩进
//                showInTop();
//            } else {
//                showInBottom();
//            }
//        }
//    }
//
//    /**
//     * @param widthMeasureSpec  设置 宽度
//     * @param heightMeasureSpec 设置 高度
//     */
//    @Override
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//    }
//
//    /**
//     * 数据 结构
//     *
//     * @param changed 是否改变了
//     * @param left    左
//     * @param top     顶部
//     * @param right   右
//     * @param bottom  底部
//     */
//    @Override
//    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
//        super.onLayout(changed, left, top, right, bottom);
//        Point screenSize = DisplayUtil.getScreenSize(context);
//        if (screenWidth != screenSize.x) {//屏幕旋转切换
//            screenWidth = screenSize.x;
//            screenHeight = screenSize.y;
//            lastY = windowManagerParams.x;
//            lastX = windowManagerParams.y;
//            windowManagerParams.x = (int) lastX;
//            // 数据 功能
//            windowManagerParams.y = (int) lastY;
//            updateViewLayout(null);
//        }
//
//    }
//
//    /**
//     * 是否是第一次点击
//     */
//    private boolean isFirstClick = true;
//
//    /**
//     * 画图
//     *
//     * @param canvas 画笔
//     */
//    @Override
//    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
//    }
//
//    @Override
//    public void onClick(View v) {
//        LogUtil.d(TAG, "执行点击事件");
//        if (!isFirstClick) {
//            OpenIDApplication.getInstance().floatBtnClick(context, OpenIDApplication.getInstance().isForceLogin(), resultCallback);
//        } else {//半隐藏状态，点击显示全部
//            isFirstClick = false;
//            showAllBtn();
//
//            if (isFirstClick) {
//                // 数据
////                System.out.print(" 打印数据功能+数据显示");
//
//            }
//        }
//    }
//
//}
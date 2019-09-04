package com.collect.user_luo.mycollect.test;

import android.content.Context;
import android.graphics.PixelFormat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;

import com.collect.user_luo.mycollect.R;

/**
 * @author manymore13
 * @version 1.0
 * @Blog <a
 * href="http://blog.csdn.net/manymore13">http://blog.csdn.net/manymore13
 * </a>
 */
public class FloatView extends android.support.v7.widget.AppCompatImageView implements OnTouchListener {

    private float mTouchX;
    private float mTouchY;
    private float x;
    private float y;
    private int x0;
    private int y0;
    int startX;
    int startY;
    private Context mContext;
    private static float cx;
    private static float cy;
    private static float cx1;
    private static float cy1;
    private static float cx2;
    private static float cy2;
    private int imgId = R.mipmap.http;
    private int screenWidth;
    boolean isShow = false;
    private OnClickListener mClickListener;

    private WindowManager windowManager;

    private LayoutParams windowManagerParams = new LayoutParams();


    public FloatView(Context context, AttributeSet attrs) {
        super(context, attrs);
        Log.d("lq", "FloatView0");
        mContext = context;
        initView();
    }

    public FloatView(Context c) {
        super(c);
        mContext = c;
        Log.d("lq", "FloatView");
        initView();
    }

    public FloatView(Context c, int x, int y) {
        super(c);
        x0 = x;
        y0 = y;
        mContext = c;
        Log.d("lq", "FloatView");
        initView();
    }

    //
    public void initView() {
        Log.d("lq", "FloatView initView");
        windowManager = (WindowManager) mContext.getApplicationContext()
                .getSystemService(Context.WINDOW_SERVICE);
        screenWidth = windowManager.getDefaultDisplay().getWidth();
        this.setImageResource(imgId);
        windowManagerParams.type = LayoutParams.TYPE_PHONE;
        windowManagerParams.format = PixelFormat.RGBA_8888; //
        windowManagerParams.flags = LayoutParams.FLAG_NOT_TOUCH_MODAL
                | LayoutParams.FLAG_NOT_FOCUSABLE;
        //
        windowManagerParams.gravity = Gravity.LEFT | Gravity.TOP;
        //
        windowManagerParams.x = x0;
        windowManagerParams.y = y0;
        //
        windowManagerParams.width = LayoutParams.WRAP_CONTENT;
        windowManagerParams.height = LayoutParams.WRAP_CONTENT;
        setOnTouchListener(this);

    }

    public void setImgResource(int id) {
        imgId = id;
    }


    //
    public void hide() {
        if (isShow) {
            windowManager.removeView(this);
            isShow = false;
        }

    }

    //
    public void show() {
        if (isShow == false) {
            windowManager.addView(this, windowManagerParams);
            isShow = true;
        }

    }

    @Override
    public void setOnClickListener(OnClickListener l) {
        this.mClickListener = l;
    }

    public void updateViewPosition() {
        windowManagerParams.x = (int) (x - mTouchX);
        windowManagerParams.y = (int) (y - mTouchY);
        windowManager.updateViewLayout(this, windowManagerParams);
    }

    public void updateViewPosition2(View view) {
        //
        if (view == operatePanel.backView) {
            windowManagerParams.x = (int) (cx - 100 - mTouchX);
            windowManagerParams.y = (int) (cy - mTouchY);
        } else if (view == operatePanel.menuView) {
            windowManagerParams.x = (int) (cx + 100 - mTouchX);
            windowManagerParams.y = (int) (cy - mTouchY);
        } else {
            if (operatePanel.backView != null) {
                Log.d("lq", "updateViewPosition2 backView");
                operatePanel.backView.windowManagerParams.x = (int) (cx - 100 - mTouchX);
                operatePanel.backView.windowManagerParams.y = (int) (cy - mTouchY);
                operatePanel.backView.windowManager.updateViewLayout(operatePanel.backView,
                        operatePanel.backView.windowManagerParams);
            }
            if (operatePanel.menuView != null) {
                Log.d("lq", "updateViewPosition2 backView");
                operatePanel.menuView.windowManagerParams.x = (int) (cx + 100 - mTouchX);
                operatePanel.menuView.windowManagerParams.y = (int) (cy - mTouchY);
                operatePanel.menuView.windowManager.updateViewLayout(operatePanel.menuView,
                        operatePanel.menuView.windowManagerParams);
            }
            windowManagerParams.x = (int) (x - mTouchX);
            windowManagerParams.y = (int) (y - mTouchY);
        }
        windowManager.updateViewLayout(this, windowManagerParams);
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        // TODO Auto-generated method stub
        //Log.d("lq", "onTouch");


        x = event.getRawX();
        y = event.getRawY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                //Log.d("lq", "onTouchEvent down");
                mTouchX = event.getX();
                mTouchY = event.getY();
                startX = (int) event.getRawX();
                startY = (int) event.getRawY();
                break;

            }
            case MotionEvent.ACTION_MOVE: {
                //Log.d("lq", "onTouchEvent move");
                updateViewPosition();
                break;
            }
            case MotionEvent.ACTION_UP: {
                //Log.d("lq", "onTouchEvent up");

                int controlledSpace = 20;
                if (Math.abs(x - startX) < controlledSpace
                        && Math.abs(y - startY) < controlledSpace) {
                    // Instrumentation inst = new Instrumentation();
                    // inst.sendKeyDownUpSync(KeyEvent.KEYCODE_BACK);
                    //Log.d("lq", "simulateKey");
                    // simulateKey(KeyEvent.KEYCODE_BACK);
                    if (mClickListener != null) {
                        mClickListener.onClick(this);
                    }
                }
                Log.i("lq", "FloatView x=" + x + " startX+" + startX + " y=" + y + " startY="
                        + startY);

                if (v == operatePanel.fv) {
                    cx = x;
                    cy = y;
                }

                updateViewPosition2(v);

                break;
            }
        }
        return false;
    }


}

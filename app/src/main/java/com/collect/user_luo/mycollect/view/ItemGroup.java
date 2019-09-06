package com.collect.user_luo.mycollect.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.collect.user_luo.R;


/**
 * 说明：自定义的导航 控件
 * 作者：User_luo on 2018/5/15 16:43
 * 邮箱：424533553@qq.com
 */
public class ItemGroup extends FrameLayout {
    private RelativeLayout rlItemGroup;
    private View mView;
    private TextView tvTitle;
    private ImageView ivRight;
    private View line;

    /**
     * 标题
     */
    private String title;
    private Drawable drawableLeft;
    private Drawable drawableRight;
    /**
     * 行高
     */
    private int lineHeight;
    private int lineColor;

    public ItemGroup(@NonNull Context context) {
        super(context);
    }

    public ItemGroup(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public ItemGroup(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        initView(context);
        initAttrs(context, attrs);
        setData();
    }


    /**
     * 初始化，引入相关属性
     *
     * @param context
     * @param attrs
     */
    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ItemGroup);
        title = typedArray.getString(R.styleable.ItemGroup_title);
        drawableLeft = typedArray.getDrawable(R.styleable.ItemGroup_drawable_left);
        drawableRight = typedArray.getDrawable(R.styleable.ItemGroup_drawable_right);
        lineHeight = typedArray.getInt(R.styleable.ItemGroup_line_height, 1);
        lineColor = typedArray.getColor(R.styleable.ItemGroup_line_color, 0xff999999);
        //这句不可少 ，会造成 泄露
        typedArray.recycle();
    }

    /**
     * 控件初始化
     */
    private void initView(Context context) {
        mView = LayoutInflater.from(context).inflate(R.layout.view_item_group, null);
        rlItemGroup = mView.findViewById(R.id.rl_item_group);
        tvTitle = mView.findViewById(R.id.tv_title);
        ivRight = mView.findViewById(R.id.iv_right);
        line = mView.findViewById(R.id.line);
        this.addView(mView);

        rlItemGroup.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemOnClickListener != null) {
                    itemOnClickListener.onClick(v);
                }
            }
        });
    }

    private void setData() {
        tvTitle.setText(title);
        if (drawableLeft != null) {
            drawableLeft.setBounds(0, 0, 72, 72);
            tvTitle.setCompoundDrawables(drawableLeft, null, null, null);
        }
        if (drawableRight != null) {
            ivRight.setImageDrawable(drawableRight);
        }
        line.setBackgroundColor(lineColor);
        line.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, lineHeight));
        rlItemGroup.setBackgroundResource(R.mipmap.a741);
    }

    /**
     * 创建点击事件监听
     */
    public interface ItemOnClickListener {
        public void onClick(View v);
    }

    ItemOnClickListener itemOnClickListener;

    public void setItemOnClickListener(ItemOnClickListener itemOnClickListener) {
        this.itemOnClickListener = itemOnClickListener;
    }


}

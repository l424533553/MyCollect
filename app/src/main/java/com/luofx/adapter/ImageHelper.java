package com.luofx.adapter;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.collect.user_luo.mycollect.R;

/**
 * 作者：罗发新
 * 时间：2019/5/30 0030    星期四
 * 邮件：424533553@qq.com
 * 说明：
 */
public class ImageHelper {

    /**
     * 1.加载图片,无需手动调用此方法
     * 2.使用@BindingAdapter注解设置自定义属性的名称，imageUrl就是属性的名称，
     * 当ImageView中使用imageUrl属性时，会自动调用loadImage方法，
     *
     * @param imageView ImageView
     * @param url       图片地址
     */
    @BindingAdapter({"imageUrl"})
    public static void loadImage(ImageView imageView, String url) {
        Glide.with(imageView.getContext()).load(url)
                .placeholder(R.mipmap.ic_card_24dp)
                .error(R.mipmap.ic_launcher)
                .into(imageView);
    }
}

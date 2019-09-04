package leavesc.hello.databindingsamples.common.adapter;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

/**
 * 作者：罗发新
 * 时间：2019/5/30 0030    星期四
 * 邮件：424533553@qq.com
 * 说明：
 */
public class BindingUtil {
    @BindingAdapter("studentAvatar")
    public static void showImageByUrl(final ImageView imageView, int resId) {
        imageView.setImageResource(resId);
    }


}

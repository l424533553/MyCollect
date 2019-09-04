package leavesc.hello.databindingsamples.common.adapter;


import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * 作者：罗发新
 * 时间：2019/5/30 0030    星期四
 * 邮件：424533553@qq.com
 * 说明：
 */
public class CommonAdapter<Student> extends SimpleAdapter<Student> {

    public CommonAdapter(List mDatas, int layoutId, int brId) {
        super(mDatas, layoutId, brId);
    }
}







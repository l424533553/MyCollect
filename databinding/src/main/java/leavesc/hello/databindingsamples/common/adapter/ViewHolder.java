package leavesc.hello.databindingsamples.common.adapter;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * 作者：罗发新
 * 时间：2019/5/30 0030    星期四
 * 邮件：424533553@qq.com
 * 说明：
 */
public class ViewHolder extends RecyclerView.ViewHolder {

    private ViewDataBinding binding;

    public ViewDataBinding getBinding() {
        return binding;
    }

    public void setBinding(ViewDataBinding binding) {
        this.binding = binding;
    }

    public ViewHolder(View itemView) {
        super(itemView);
    }
}

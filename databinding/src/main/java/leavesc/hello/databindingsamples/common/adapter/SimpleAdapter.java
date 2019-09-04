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
public class SimpleAdapter<T> extends RecyclerView.Adapter<ViewHolder> {

    private List<T> mDatas;

    private int layoutId;

    private int brId;

    public SimpleAdapter(List<T> mDatas, int layoutId, int brId) {
        this.mDatas = mDatas;
        this.layoutId = layoutId;
        this.brId = brId;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ViewDataBinding binding = DataBindingUtil.inflate(inflater, layoutId, parent, false);
        ViewHolder viewHolder = new ViewHolder(binding.getRoot());
        viewHolder.setBinding(binding);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.getBinding().setVariable(brId, mDatas.get(position));
        holder.getBinding().executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return mDatas == null ? 0 : mDatas.size();
    }
}





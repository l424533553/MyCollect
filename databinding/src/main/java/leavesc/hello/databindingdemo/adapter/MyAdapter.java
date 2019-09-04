package leavesc.hello.databindingdemo.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;



import java.util.List;

import leavesc.hello.databindingdemo.base.BaseBindRecyclerViewAdapter;
import leavesc.hello.databindingdemo.base.BaseBindingViewHolder;
import leavesc.hello.databindingdemo.bean.FruitItem;
import leavesc.hello.databindingsamples.BR;
import leavesc.hello.databindingsamples.R;

/**
 * 作者： 周旭 on 2017年10月11日 0011.
 * 邮箱：374952705@qq.com
 * 博客：http://www.jianshu.com/u/56db5d78044d
 */

public class MyAdapter extends BaseBindRecyclerViewAdapter<FruitItem> {

    public MyAdapter(Context context, List<FruitItem> mList) {
        super(context, mList);
    }

    @Override
    public RecyclerView.ViewHolder onCreateMyViewHolder(ViewGroup parent, int viewType) {
        //ItemFruitRvBinding包含了Layout中所有的控件；
        ViewDataBinding binding = DataBindingUtil.inflate(inflater, R.layout.item_fruit, parent, false);
        return new BaseBindingViewHolder(binding); //binding.getRoot():获取item的根布局
    }

    @Override
    public void onBindMyViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewDataBinding binding = DataBindingUtil.getBinding(holder.itemView);
        binding.setVariable(BR.item,mList.get(position));
        binding.executePendingBindings(); //数据改变时立即刷新数据
    }
}

package leavesc.hello.databindingdemo.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

import leavesc.hello.databindingdemo.base.BaseBindRecyclerViewAdapter;
import leavesc.hello.databindingdemo.bean.FruitItem;
import leavesc.hello.databindingdemo.bean.IBaseBindingAdapterItem;
import leavesc.hello.databindingdemo.bean.TextItem;
import leavesc.hello.databindingsamples.R;
import leavesc.hello.databindingsamples.databinding.ItemFruitBinding;
import leavesc.hello.databindingsamples.databinding.ItemTextBinding;

/**
 * RecyclerView多布局的适配器
 * <p>
 * 作者： 周旭 on 2017年10月11日 0011.
 * 邮箱：374952705@qq.com
 * 博客：http://www.jianshu.com/u/56db5d78044d
 */

public class MultiItemAdapter extends BaseBindRecyclerViewAdapter<IBaseBindingAdapterItem> {

    public MultiItemAdapter(Context context, List<IBaseBindingAdapterItem> mList) {
        super(context, mList);
    }

    @Override
    public int getItemViewType(int position) {
        return mList.get(position).getItemViewType();
    }

    @Override
    public RecyclerView.ViewHolder onCreateMyViewHolder(ViewGroup parent, int viewType) {
        if (viewType == R.layout.item_fruit) {
            ItemFruitBinding itemFruitBinding = DataBindingUtil.inflate(inflater, R.layout.item_fruit, parent, false);
            return new FruitViewHolder(itemFruitBinding);
        } else if (viewType == R.layout.item_text) {
            ItemTextBinding itemTextBinding = DataBindingUtil.inflate(inflater, R.layout.item_text, parent, false);
            return new TextViewHolder(itemTextBinding);
        }
        ItemFruitBinding binding = DataBindingUtil.inflate(inflater, R.layout.item_fruit, parent, false);
        return new FruitViewHolder(binding);
    }

    @Override
    public void onBindMyViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof FruitViewHolder) {
            FruitItem fruitBean = (FruitItem) mList.get(position);
            ((FruitViewHolder) holder).getBinding().setItem(fruitBean);
            ((FruitViewHolder) holder).getBinding().executePendingBindings(); //解决databinding闪烁问题
        } else if (holder instanceof TextViewHolder) {
            TextItem textBean = (TextItem) mList.get(position);
            ((TextViewHolder) holder).getBinding().setItem(textBean);
            ((TextViewHolder) holder).getBinding().executePendingBindings(); //解决databinding闪烁问题
        }
    }

    class FruitViewHolder extends RecyclerView.ViewHolder {
        private ItemFruitBinding binding;

        public ItemFruitBinding getBinding() {
            return binding;
        }

        public FruitViewHolder(ItemFruitBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    class TextViewHolder extends RecyclerView.ViewHolder {
        private ItemTextBinding binding;

        public ItemTextBinding getBinding() {
            return binding;
        }

        public TextViewHolder(ItemTextBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }


}

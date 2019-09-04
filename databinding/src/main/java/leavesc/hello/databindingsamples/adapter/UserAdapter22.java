package leavesc.hello.databindingsamples.adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import leavesc.hello.databindingsamples.R;
import leavesc.hello.databindingsamples.databinding.ItemUser22Binding;
import leavesc.hello.databindingsamples.databinding.ItemUserBinding;
import leavesc.hello.databindingsamples.model.UserMode;

/**
 * 作者：leavesC
 * 时间：2019/2/27 21:36
 * 描述：
 * GitHub：https://github.com/leavesC
 * Blog：https://www.jianshu.com/u/9df45b87cfdf
 */
public class UserAdapter22 extends RecyclerView.Adapter<UserAdapter22.UserAdapterHolder> {

    private List<UserMode> userList;

    public UserAdapter22(List<UserMode> userList) {
        this.userList = userList;
    }

    @NonNull
    @Override
    public UserAdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemUser22Binding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_user22, parent, false);
        return new UserAdapterHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapterHolder holder, int position) {
        holder.getBinding().setUser(userList.get(position));
        //recyclerView  防止闪烁
        holder.getBinding().executePendingBindings();
    }

    @Override
    public int getItemCount() {
        if (userList == null) {
            return 0;
        }
        return userList.size();
    }

    class UserAdapterHolder extends RecyclerView.ViewHolder {

        private ItemUser22Binding binding;

        UserAdapterHolder(ItemUser22Binding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public ItemUser22Binding getBinding() {
            return binding;
        }
    }

}

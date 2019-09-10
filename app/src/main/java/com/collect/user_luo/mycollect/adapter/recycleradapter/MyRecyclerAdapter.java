package com.collect.user_luo.mycollect.adapter.recycleradapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.collect.user_luo.R;
import com.collect.user_luo.mycollect.listener.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * 说明： recyclerView  需要自己实现OnitemClick事件
 * 作者：User_luo on 2018/5/4 16:16
 * 邮箱：424533553@qq.com
 */
public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.MyHolder> {


    private OnItemClickListener mItemClickListener;
    //item的回调接口


    //定义一个设置点击监听器的方法
    public void setOnItemClickListener(OnItemClickListener itemClickListener) {
        this.mItemClickListener = itemClickListener;
    }

    private Context mContext;
    private List<Integer> mDatas;
    private List<Integer> heights;

    public MyRecyclerAdapter(Context context, List<Integer> datas) {
        super();
        this.mContext = context;
        this.mDatas = datas;
        getRandomHeight(this.mDatas);
    }

    @Override
    public int getItemCount() {
        // TODO Auto-generated method stub
        return mDatas == null ? 0 : mDatas.size();
    }

    @Override
    // 填充onCreateViewHolder方法返回的holder中的控件
    public void onBindViewHolder(final MyHolder holder, final int position) {
//        // TODO Auto-generated method stub
////        holder.imageView.setText(mDatas.get(position));
//
//        holder.imageView.setImageResource(mDatas.get(position));
////        如果设置了回调，则设置点击事件
///*        if(mItemClickListener != null){
//            holder.itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    mItemClickListener.onItemClick(holder.itemView, position);
//                }
//            });
//        }*/
//
//        int firstItemPosition = layoutManager.findFirstVisibleItemPosition();
//        if (position - firstItemPosition >= 0) {
//            //得到要更新的item的view
//            View view = mRecyclerView.getChildAt(position - firstItemPosition+1);
//
//            }



//        // TODO Auto-generated method stub
//        //得到item的LayoutParams布局参数
//        ViewGroup.LayoutParams params= holder.itemView.getLayoutParams();
//        //把随机的高度赋予item布局
//        params.height = heights.get(position);
//        //把params设置给item布局
//        holder.itemView.setLayoutParams(params);


        //为控件绑定数据
        holder.imageView.setImageResource(mDatas.get(position));
        //如果设置了监听那么它就不为空，然后回调相应的方法
        if(mItemClickListener!=null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    //得到当前点击item的位置pos
                    int position = holder.getLayoutPosition();
                    //把事件交给我们实现的接口那里处理
                    mItemClickListener.onItemClick(holder.itemView, position);
                }
            });
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {

                @Override
                public boolean onLongClick(View v) {
                    //得到当前点击item的位置pos
                    int position = holder.getLayoutPosition();
                    //把事件交给我们实现的接口那里处理
                    mItemClickListener.onLongClick(holder.itemView, position);
                    return true;
                }
            });
        }

    }



    @Override
    // 重写onCreateViewHolder方法，返回一个自定义的ViewHolder
    public MyHolder onCreateViewHolder(ViewGroup arg0, int arg1) {
        // 填充布局
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_recycler_adapter_simple, null);
        MyHolder holder = new MyHolder(view);
        return holder;
    }

    // 定义内部类继承ViewHolder
    class MyHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        public MyHolder(View view) {
            super(view);
            imageView = view.findViewById(R.id.imageView);
        }

    }

    //得到随机item的高度
    private void getRandomHeight(List<Integer> datas) {
        heights = new ArrayList();
        for (int i = 0; i < datas.size(); i++) {
            heights.add((int) (200+Math.random()*100));
        }
    }


}


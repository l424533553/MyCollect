package com.collect.user_luo.mycollect.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.collect.user_luo.mycollect.R;
import com.collect.user_luo.mycollect.adapter.recycleradapter.MyRecyclerAdapter;
import com.collect.user_luo.mycollect.data.HomeData;
import com.collect.user_luo.mycollect.listener.OnItemClickListener;

import java.util.List;

public class RecyclerViewActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private List<Integer> data;
    private MyRecyclerAdapter adapter;

    private void initRecyclerView() {
        // 得到控件
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        // 设置布局管理器   线性的布局 横向或纵向
        //默认的  item管理器
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
//        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
//        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        data = HomeData.getResources();
        // 设置适配器
        adapter = new MyRecyclerAdapter(this, data);
        mRecyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(View view, int position) {
                // TODO Auto-generated method stub
                Toast.makeText(RecyclerViewActivity.this, "点击了：" + position, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(View view, int position) {
                //长按删除
                data.remove(position);
                adapter.notifyItemRemoved(position);
            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);
        initRecyclerView();


    }
}

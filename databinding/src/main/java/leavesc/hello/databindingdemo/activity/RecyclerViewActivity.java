package leavesc.hello.databindingdemo.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;

import java.util.ArrayList;
import java.util.List;

import leavesc.hello.databindingdemo.adapter.MultiItemAdapter;
import leavesc.hello.databindingdemo.bean.FruitItem;
import leavesc.hello.databindingdemo.bean.IBaseBindingAdapterItem;
import leavesc.hello.databindingdemo.bean.TextItem;
import leavesc.hello.databindingsamples.R;
import leavesc.hello.databindingsamples.databinding.ActivityRecyclerBinding;

public class RecyclerViewActivity extends AppCompatActivity {

    private MultiItemAdapter mAdapter;
    private List<IBaseBindingAdapterItem> mList = new ArrayList<>(); //数据源

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityRecyclerBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_recycler);

        initData();
        mAdapter = new MultiItemAdapter(this, mList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, OrientationHelper.VERTICAL, false);
        binding.recyclerView.setLayoutManager(layoutManager);
        binding.recyclerView.setAdapter(mAdapter);
    }

    private void initData() {
        mList.add(new TextItem("标题1"));
        mList.add(new FruitItem(R.mipmap.fruit, "苹果"));
        mList.add(new FruitItem(R.mipmap.fruit, "香蕉"));

        mList.add(new TextItem("标题2"));
        mList.add(new TextItem("标题3"));

        mList.add(new FruitItem(R.mipmap.fruit, "桃子"));

        mList.add(new TextItem("标题4"));
        mList.add(new FruitItem(R.mipmap.fruit, "梨"));
        mList.add(new TextItem("标题5"));
    }
}

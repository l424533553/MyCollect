package com.collect.user_luo.mycollect.test;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.collect.user_luo.R;
import com.collect.user_luo.databinding.FragmentTest1Binding;


/**
 * 作者：leavesC
 * 时间：2019/2/27 21:36
 * 描述：
 * GitHub：https://github.com/leavesC
 * Blog：https://www.jianshu.com/u/9df45b87cfdf
 */
public class Test1Fragment extends Fragment {

    public Test1Fragment() {

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FragmentTest1Binding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_test1, container, false);

        initRecyclerView(binding);
        return binding.getRoot();
    }

    private void initRecyclerView(FragmentTest1Binding binding) {
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

//        newsAdapter = new NewsAdapter(this);
//        binding.recyclerView.setAdapter(newsAdapter);

    }



}

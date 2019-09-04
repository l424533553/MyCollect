package com.collect.user_luo.mycollect.activity.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

import com.collect.user_luo.mycollect.R;
import com.collect.user_luo.mycollect.activity.view.fragment.OneFragment;
import com.collect.user_luo.mycollect.activity.view.fragment.SecondFragment;
import com.collect.user_luo.mycollect.adapter.ViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ViewActivity extends FragmentActivity {


    ViewPagerAdapter adapter;
    ViewPager myViewPager;
    private List<Fragment> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        myViewPager = (ViewPager) findViewById(R.id.myViewPager);

        //把Fragment添加到List集合里面
        list = new ArrayList<>();
        list.add(new OneFragment());
        list.add(new SecondFragment());


        adapter = new ViewPagerAdapter(getSupportFragmentManager(), list);
        myViewPager.setAdapter(adapter);
        myViewPager.setCurrentItem(0);  //初始化显示第一个页面


//        tv_item_one.setBackgroundColor(Color.RED);//被选中就为红色

//        ImageView btn1=findViewById(R.id.btn1);
//        ImageView btn2=findViewById(R.id.btn2);
//        ImageView btn3=findViewById(R.id.btn3);
//        ImageView btn4=findViewById(R.id.btn4);
//        Drawable orgDrawable = this.getResources().getDrawable(R.mipmap.home_message);
//        Drawable orgDrawabl2 = this.getResources().getDrawable(R.mipmap.icon_clear);
//
//        int color= Color.parseColor("#AA49CB5F");
//        Drawable drawable1 = DrawableLess.$tint(orgDrawable,color, PorterDuff.Mode.ADD);
//        Drawable drawable2= DrawableLess.$tint(orgDrawabl2,color);
//        Drawable drawable4= DrawableLess.$tint(orgDrawable,color, PorterDuff.Mode.DARKEN);
//        btn1.setImageDrawable(drawable1);
//        btn2.setImageDrawable(drawable2);
//        btn3.setImageDrawable(orgDrawabl2);
//        btn4.setImageDrawable(drawable4);

//        btn1.setBackground(orgDrawable);
//        btn2.setImageDrawable(orgDrawable);
//        btn3.setImageResource(R.mipmap.home_message);

    }
}

package com.collect.user_luo.mycollect.activity.refresh.smartfragment;


//import android.app.Fragment;

import android.os.Bundle;


import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.collect.user_luo.mycollect.R;
import com.collect.user_luo.mycollect.adapter.MyPagerAdapter_Advance;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */

/*********
 * 加入指定的 Header 和 Footer
 * 方法一（优先级最低，如果有其他方法，会被覆盖）：如下
 *方法二 XML布局文件指定 （优先级中）
 *
 */

/*public class App extends Application {
    //static 代码段可以防止内存泄露
    static {
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreator(new DefaultRefreshHeaderCreator() {
            @Override
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
                layout.setPrimaryColorsId(R.color.colorPrimary, android.R.color.white);//全局设置主题颜色
                return new ClassicsHeader(context);//.setTimeFormat(new DynamicTimeFormat("更新于 %s"));//指定为经典Header，默认是 贝塞尔雷达Header
            }
        });
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreator(new DefaultRefreshFooterCreator() {
            @Override
            public RefreshFooter createRefreshFooter(Context context, RefreshLayout layout) {
                //指定为经典Footer，默认是 BallPulseFooter
                return new ClassicsFooter(context).setDrawableSize(20);
            }
        });
    }
}*/
public class SmartRefreshFragment extends Fragment implements View.OnClickListener, ViewPager.OnPageChangeListener {
    //    RefreshLayout refreshLayout;
//    private ListView listView;
//    private List<String> data;
//    private StringAdapter adapter;
//三个fragment
//    private SmartRefreshFragment_First smartRefreshFragment_first;
//    private SmartRefreshFragment_Second smartRefreshFragment_second;
//    private SmartRefreshFragment_Third smartRefreshFragment_third;

    private Button btnFirst, btnSecond, btnThird, btnFourth;


//    private FragmentManager fragmentManager;

    public SmartRefreshFragment() {
        // Required empty public constructor
    }

    private void initView(View view) {
        btnFirst = view.findViewById(R.id.btnFirst);
        btnSecond = view.findViewById(R.id.btnSecond);
        btnThird = view.findViewById(R.id.btnThird);
        btnFourth = view.findViewById(R.id.btnFourth);
        btnFirst.setOnClickListener(this);
        btnSecond.setOnClickListener(this);
        btnThird.setOnClickListener(this);
        btnFourth.setOnClickListener(this);
    }

    /*******  Fragment + viewpage 管理   **************************/
    List<Fragment> fragments;
    private MyPagerAdapter_Advance pagerAdapter;
    private ViewPager viewPager;

    private void initViewPage(View view) {
        viewPager = view.findViewById(R.id.viewpage);
        fragments = new ArrayList<>();
        fragments.add(new SmartRefreshFragment_First());
        fragments.add(new SmartRefreshFragment_Second());
        fragments.add(new SmartRefreshFragment_Third());
        fragments.add(new SmartRefreshFragment_Fourth());
        pagerAdapter = new MyPagerAdapter_Advance(getFragmentManager(), fragments);
        viewPager.setAdapter(pagerAdapter);
        viewPager.setCurrentItem(0);  //初始化显示第一个页面
        viewPager.addOnPageChangeListener(this);
    }

    /****************************************************************/


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_smart_refresh, container, false);
        initView(view);
        initViewPage(view);
        return view;
    }

    @Override
    public void onClick(View v) {
//        FragmentManager fragmentManager = getChildFragmentManager();
        switch (v.getId()) {
            case R.id.btnFirst:
                viewPager.setCurrentItem(0);
                break;
            case R.id.btnSecond:
                viewPager.setCurrentItem(1);
                break;
            case R.id.btnThird:
                viewPager.setCurrentItem(2);
                Mylog.log("点击第三个按钮");
//                initFragment3();
                break;
            case R.id.btnFourth:
                viewPager.setCurrentItem(3);
                Mylog.log("点击第四个按钮");
//                initFragment3();
                break;
            default:
                break;
        }
    }

    /**
     * 设置一个ViewPager的侦听事件，当左右滑动ViewPager时菜单栏被选中状态跟着改变
     */
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        switch (position) { //显示的Fragment 的position
            case 0:
                break;
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
        }
        Toast.makeText(getContext(), "第" + (position + 1) + "页!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }


}

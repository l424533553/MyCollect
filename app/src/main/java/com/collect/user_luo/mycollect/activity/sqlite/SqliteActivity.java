package com.collect.user_luo.mycollect.activity.sqlite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.collect.user_luo.mycollect.R;
import com.collect.user_luo.mycollect.sqlite.ormlite.ArticleBean;
import com.collect.user_luo.mycollect.sqlite.ormlite.ArticleDao;
import com.collect.user_luo.mycollect.sqlite.ormlite.MyTest;
import com.collect.user_luo.mycollect.sqlite.ormlite.MyTestDao;
import com.j256.ormlite.dao.ForeignCollection;

import java.util.Iterator;

public class SqliteActivity extends AppCompatActivity {
    StringBuffer contentBuffer;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);
        contentBuffer = new StringBuffer();

        textView=findViewById(R.id.textView);
        MyTestDao dao1=new MyTestDao(this);
        Mylog.log("mytest表是否存在1111111="+dao1.isTableExists());

        initData();
        initView();

        Mylog.log("mytest表是否存在2222222="+dao1.isTableExists());



    }

    // 初始化数据
    private void initData() {
        // 添加用户数据
        MyTest userData = new MyTest("张三", 18);
        new MyTestDao(this).insert(userData);
        // 添加文章数据
        ArticleBean articleData = new ArticleBean("标题", "内容内容内容内容内容内容", userData);
        new ArticleDao(this).insert(articleData);
    }

    // 初始化视图
    private void initView() {
        // 从数据库中根据ID取出文章信息
        ArticleBean articleBean = new ArticleDao(this).queryById(1);
        contentBuffer.append(articleBean.toString());
        // 根据取出的用户id查询用户信息
        MyTest userBean = new MyTestDao(this).queryById(articleBean.getUser().getId());
        contentBuffer.append("\n\n" + userBean.toString());
        // 从用户信息中取出关联的文章列表信息
        ForeignCollection<ArticleBean> articles = userBean.getArticles();
        Iterator<ArticleBean> iterator = articles.iterator();
        contentBuffer.append("\n\n");
        while (iterator.hasNext()) {
            ArticleBean article = iterator.next();
            contentBuffer.append(article.toString() + "\n");
        }
        // 设置TextView的文本
        textView.setText(contentBuffer.toString());
    }

}

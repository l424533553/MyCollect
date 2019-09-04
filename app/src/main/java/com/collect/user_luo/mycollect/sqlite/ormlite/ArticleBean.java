package com.collect.user_luo.mycollect.sqlite.ormlite;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * 说明：
 * 作者：User_luo on 2018/4/23 10:40
 * 邮箱：424533553@qq.com
 */

/**
 * ArticleBean实体类，存储article数据表中的数据
 * 数据库中的article表和user表是关联的，因此我们需要在article表中配置外键
 * <p>
 * foreignColumnName：外键约束指向的类中的属性名
 * foreign：当前字段是否是外键
 * foreignAutoRefresh：如果这个属性设置为true，在关联查询的时候就不需要再调用refresh()方法了
 */
@DatabaseTable(tableName = "article")
public class ArticleBean {
    // article表中各个字段的名称
    public static final String COLUMNNAME_ID = "id";
    public static final String COLUMNNAME_TITLE = "title";
    public static final String COLUMNNAME_CONTENT = "content";
    public static final String COLUMNNAME_USER = "user_id";

    @DatabaseField(generatedId = true, columnName = COLUMNNAME_ID, useGetSet = true)
    private int id;
    @DatabaseField(columnName = COLUMNNAME_TITLE, useGetSet = true, canBeNull = false, unique = true)
    private String title;
    @DatabaseField(columnName = COLUMNNAME_CONTENT, useGetSet = true)
    private String content;
    @DatabaseField(columnName = COLUMNNAME_USER, foreign = true, foreignAutoRefresh = true, foreignAutoCreate = true,
            foreignColumnName = MyTest.COLUMNNAME_ID)
    private MyTest user_id;

    public ArticleBean() {
    }

    public ArticleBean(String title, String content, MyTest user) {
        this.title = title;
        this.content = content;
        this.user_id = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public MyTest getUser() {
        return user_id;
    }

    public void setUser(MyTest user) {
        this.user_id = user;
    }

    @Override
    public String toString() {
        return "ArticleBean{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", user=" + user_id +
                '}';
    }


}
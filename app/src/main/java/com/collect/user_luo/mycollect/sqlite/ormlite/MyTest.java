package com.collect.user_luo.mycollect.sqlite.ormlite;

/**
 * 说明： 测试的实体类
 * 作者：User_luo on 2018/4/23 10:27
 * 邮箱：424533553@qq.com
 */

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * UserBean实体类，存储数据库中user表中的数据
 * <p>
 * 注解：
 * DatabaseTable：通过其中的tableName属性指定数据库名称
 * DatabaseField：代表数据表中的一个字段
 * ForeignCollectionField：一对多关联，表示一个UserBean关联着多个ArticleBean（必须使用ForeignCollection集合）
 * <p>
 * 属性：
 * id：当前字段是不是id字段（一个实体类中只能设置一个id字段）
 * columnName：表示当前属性在表中代表哪个字段
 * generatedId：设置属性值在数据表中的数据是否自增
 * useGetSet：是否使用Getter/Setter方法来访问这个字段
 * canBeNull：字段是否可以为空，默认值是true
 * unique：是否唯一
 * defaultValue：  设置这个字段的默认值
 */

/**
 * ArticleBean实体类，存储article数据表中的数据
 * 数据库中的article表和user表是关联的，因此我们需要在article表中配置外键
 * <p>
 * foreignColumnName：外键约束指向的类中的属性名
 * foreign：当前字段是否是外键
 * foreignAutoRefresh：如果这个属性设置为true，在关联查询的时候就不需要再调用refresh()方法了
 */

@DatabaseTable(tableName = "mytest") // 指定数据表的名称
public class MyTest {
    // 定义字段在数据库中的字段名
    public static final String COLUMNNAME_NAME = "name";
    //如果不设置 columnName 则就是默认的列名
    public static final String COLUMNNAME_AGE = "tb_age";
    public static final String COLUMNNAME_ID = "id";


    @DatabaseField(generatedId = true,columnName = COLUMNNAME_ID, useGetSet = true)
    private int id;
    @DatabaseField(columnName = COLUMNNAME_NAME, useGetSet = true, canBeNull = false, unique = true)
    String nmae;
    @DatabaseField(columnName = COLUMNNAME_AGE, useGetSet = true, defaultValue = "10")
    int age;


    @ForeignCollectionField(eager = true)
    private ForeignCollection<ArticleBean> articles;


    public MyTest() {
    }

    public MyTest(String nmae, int age) {
        this.nmae = nmae;
        this.age = age;
    }

    public String getNmae() {
        return nmae;
    }

    public void setNmae(String nmae) {
        this.nmae = nmae;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ForeignCollection<ArticleBean> getArticles() {
        return articles;
    }

    public void setArticles(ForeignCollection<ArticleBean> articles) {
        this.articles = articles;
    }
}

package com.collect.user_luo.mycollect.sqlite.ormlite;

/**
 * 说明：
 * 作者：User_luo on 2018/4/23 10:45
 * 邮箱：424533553@qq.com
 */

import android.content.Context;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

/**
 * 操作User数据表的Dao类，封装这操作User表的所有操作
 * 通过DatabaseHelper类中的方法获取ORMLite内置的DAO类进行数据库中数据的操作
 * <p>
 * 调用dao的create()方法向表中添加数据
 * 调用dao的delete()方法删除表中的数据
 * 调用dao的update()方法修改表中的数据
 * 调用dao的queryForAll()方法查询表中的所有数据
 */
public class MyTestDao {
    private Context context;
    // ORMLite提供的DAO类对象，第一个泛型是要操作的数据表映射成的实体类；第二个泛型是这个实体类中ID的数据类型
    private Dao<MyTest, Integer> dao;

    public MyTestDao(Context context) {
        this.context = context;
        try {
            this.dao = OrmliteBaseHelper.getInstance(context).getDao(MyTest.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean isTableExists(){
        try {
          return   dao.isTableExists();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 向user表中添加一条数据
    public void insert(MyTest data) {
        try {
            dao.create(data);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 删除user表中的一条数据
    public void delete(MyTest data) {
        try {
            dao.delete(data);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 修改user表中的一条数据
    public void update(MyTest data) {
        try {
            dao.update(data);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 查询user表中的所有数据
    public List<MyTest> selectAll() {
        List<MyTest> users = null;
        try {
            users = dao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    // 根据ID取出用户信息
    public MyTest queryById(int id) {
        MyTest user = null;
        try {
            user = dao.queryForId(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
}

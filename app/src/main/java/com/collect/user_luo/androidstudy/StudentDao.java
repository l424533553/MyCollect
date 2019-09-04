package com.collect.user_luo.androidstudy;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

/**
 * 作者：罗发新
 * 时间：2019/7/4 0004    星期四
 * 邮件：424533553@qq.com
 * 说明：
 */
public class StudentDao {
    public static StudentDao getInstance(Context context) {
        return new StudentDao();
    }

    public Cursor queryStudent() {
        return null;
    }

    public void insertStudent(ContentValues values) {

    }

    public int deleteStudent() {
        return 0;
    }

}

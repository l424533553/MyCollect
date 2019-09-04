package com.collect.user_luo.androidstudy;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * 作者：罗发新
 * 时间：2019/7/4 0004    星期四
 * 邮件：424533553@qq.com
 * 说明：自定义contentProvider,作为四大组件之一，同样需要在清单文件中注册
 */
public class StudentContentProvider extends ContentProvider {


    private static final String AUTHORITY = "com.jrmf360.studentProvider";
    //匹配成功后的匹配码
    private static final int MATCH_CODE = 100;
    private static UriMatcher uriMatcher;
    private StudentDao studentDao;
    //数据改变后指定通知的Uri
    private static final Uri NOTIFY_URI = Uri.parse("content://" + AUTHORITY + "/student");

    static {
        //匹配不成功返回NO_MATCH(-1)
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        // 添加我们需要匹配的uri
        uriMatcher.addURI(AUTHORITY, "student", MATCH_CODE);
    }

    @Override
    public boolean onCreate() {
        studentDao = StudentDao.getInstance(getContext());
        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        int match = uriMatcher.match(uri);
        if (match == MATCH_CODE) {
            Cursor cursor = studentDao.queryStudent();
            return cursor;
        }
        return null;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        if (uriMatcher.match(uri) == MATCH_CODE) {
            studentDao.insertStudent(values);
            notifyChange();
        }
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        if (uriMatcher.match(uri) == MATCH_CODE) {
            int delCount = studentDao.deleteStudent();
            notifyChange();
            return delCount;
        }
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    /**
     *
     */
    private void notifyChange() {
        getContext().getContentResolver().notifyChange(NOTIFY_URI, null);
    }


}

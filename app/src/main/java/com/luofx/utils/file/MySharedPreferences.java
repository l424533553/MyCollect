package com.luofx.utils.file;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Map;


/**
 * 偏好设置 工具类  ,建议只保存简单的基本类型数值，不必保存复杂的 对象文件
 * 私有模式
 * Context.MODE_PRIVATE 的值是 0;
 * ①只能被创建这个文件的当前应用访问
 * ②若文件不存在会创建文件；若创建的文件已存在则会覆盖掉原来的文件
 *
 * 追加模式
 * Context.MODE_APPEND 的值是 32768;
 * ①只能被创建这个文件的当前应用访问
 * ②若文件不存在会创建文件；若文件存在则在文件的末尾进行追加内容
 *
 * 可读模式
 * Context.MODE_WORLD_READABLE的值是1;
 * ①创建出来的文件可以被其他应用所读取
 *
 * 可写模式
 * Context.MODE_WORLD_WRITEABLE的值是2
 * ①允许其他应用对其进行写入。
 *
 */
public class MySharedPreferences {

    /**
     * 保存在手机里面的文件名
     */
    private static final String FILE_NAME = "user";

    /**
     * 获取 SharedPreferences
     *
     * @param context
     * @return
     */
    public static SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
    }


    /**
     * 移除某个key值已经对应的值
     *
     * @param context
     * @param key
     */
    public static void remove(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.remove(key).apply();
    }

    /**
     * 清除所有数据
     *
     * @param context  上下文
     */
    public static void clear(Context context) {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear().apply();
    }

    /**
     * 查询某个key是否已经存在
     *
     * @param context    上下文
     * @param key        key
     * @return          是否包含值
     */
    public static boolean contains(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        return sp.contains(key);
    }

    /**
     * 返回所有的键值对
     *
     * @param context  获得所有的 内容键值对
     * @return
     */
    public static Map<String, ?> getAll(Context context) {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME,
                Context.MODE_PRIVATE);
        return sp.getAll();
    }












}

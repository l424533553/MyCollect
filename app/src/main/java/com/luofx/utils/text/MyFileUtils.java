package com.luofx.utils.text;

import android.content.Context;
import android.os.Environment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Created by Longer on 2016/10/26.
 */
public class MyFileUtils {

    public static final String DOWNLOAD_DIR = "download";
    private static final String CACHE_DIR = "cache";
    private static final String ICON_DIR = "icon";

    /**
     * 判断SD卡是否挂载
     */
    public static boolean isSDCardAvailable() {
        if (Environment.MEDIA_MOUNTED.equals(Environment
                .getExternalStorageState())) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 获取下载目录
     */
    public static String getDownloadDir(Context context, String DIR_TYPE) {
        switch (DIR_TYPE) {
            case DOWNLOAD_DIR:
                //获取下载目录
                return getDir(context, DOWNLOAD_DIR);
            case CACHE_DIR:
                // 获取缓存目录
                return getDir(context, CACHE_DIR);
            case ICON_DIR:
                //获取icon 目录
                return getDir(context, ICON_DIR);
            default:
                return null;
        }
    }

    /**
     * 获取应用目录，当SD卡存在时，获取SD卡上的目录，当SD卡不存在时，获取应用的cache目录
     */
    public static String getDir(Context context, String name) {
        StringBuilder sb = new StringBuilder();

        //TODO 暂时屏蔽掉了
//        if (isSDCardAvailable()) {
//            sb.append(getExternalStoragePath(context));
//        } else {
//            sb.append(getCachePath(context));
//        }

        sb.append(getCachePath(context));
        sb.append(name);
        sb.append(File.separator);
        String path = sb.toString();
        if (createDirs(path)) {
            return path;
        } else {
            return null;
        }
    }

    /**
     * 获取SD下的应用目录
     */
    public static String getExternalStoragePath(Context context) {
        StringBuilder sb = new StringBuilder();
        sb.append(Environment.getExternalStorageDirectory().getAbsolutePath());
        sb.append(File.separator);
        sb.append("Android/data/").append(context.getPackageName());
        sb.append(File.separator);
        return sb.toString();
    }

    /**
     * 获取应用的cache目录
     */
    public static String getCachePath(Context context) {
        File f = context.getCacheDir();
        if (null == f) {
            return null;
        } else {
            return f.getAbsolutePath() + "/";
        }
    }


    /**
     * 创建文件夹
     */
    public static boolean createDirs(String dirPath) {
        File file = new File(dirPath);
        if (!file.exists() || !file.isDirectory()) {
            return file.mkdirs();
        }
        return true;
    }


    public static String getTimeFileSuffix(String date) {
        String[] split = date.split("\\-");
        String retStr = "";
        retStr += split[0] + "/";
        retStr += split[1];
        String[] split1 = split[2].split("\\ ");
        retStr += split1[0] + "/" + split1[1] + "/";
        return retStr;
    }

    /**
     * 保存对象
     *
     * @param ser  要保存的序列化对象
     * @param file 保存在本地的文件名
     * @throws IOException
     */
    public static boolean saveObject(Context context, Serializable ser,
                                     String file) {
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = context.openFileOutput(file, Context.MODE_PRIVATE);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(ser);
            oos.flush();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                oos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                fos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static Serializable readObject(Context context, String file) {
        if (!isExistDataCache(context, file))
            return null;
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            fis = context.openFileInput(file);
            ois = new ObjectInputStream(fis);
            return (Serializable) ois.readObject();
        } catch (FileNotFoundException e) {
        } catch (Exception e) {
            e.printStackTrace();
            // 反序列化失败 - 删除缓存文件
            if (e instanceof InvalidClassException) {
                File data = context.getFileStreamPath(file);
                data.delete();
            }
        } finally {
            try {
                ois.close();
            } catch (Exception e) {
            }
            try {
                fis.close();
            } catch (Exception e) {
            }
        }
        return null;
    }

    public static boolean isExistDataCache(Context context, String cachefile) {
        if (context == null) return false;
        boolean exist = false;
        File data = context.getFileStreamPath(cachefile);
        if (data.exists()) exist = true;
        return exist;
    }


    public static boolean deleteDir(String path) {
        File file = new File(path);
        if (!file.exists()) {//判断是否待删除目录是否存在
            System.err.println("The dir are not exists!");
            return false;
        }

        String[] content = file.list();//取得当前目录下所有文件和文件夹
        for (String name : content) {
            File temp = new File(path, name);
            if (temp.isDirectory()) {//判断是否是目录
                deleteDir(temp.getAbsolutePath());//递归调用，删除目录里的内容
                temp.delete();//删除空目录
            } else {
                if (!temp.delete()) {//直接删除文件
                    System.err.println("Failed to delete " + name);
                }
            }
        }
        return true;
    }


}

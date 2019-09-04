package com.collect.user_luo.mycollect.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

/**
 * 作者：罗发新
 * 时间：2018/12/5 0005    16:17
 * 邮件：424533553@qq.com
 * 说明：牛逼的 xml 生成适配器 ，自动生成对应的像素适配文件
 */
public class XmlGenerateUtils {

    private final static String rootPath = "C:\\Users\\Administrator\\Desktop\\layoutroot\\values-{0}x{1}\\";
    private final static float dw = 720f;
    private final static float dh = 1280f;

    private final static String WTemplate = "<dimen name=\"x{0}\">{1}px</dimen>\n";
    private final static String HTemplate = "<dimen name=\"y{0}\">{1}px</dimen>\n";

    /**
     * 这是类的主方法，可以用来接受用户的输入，并将输入数据保存到一个String类型的数组里
     */
    public static void main(String[] args) {
//        makeString(320, 480);
//        makeString(720, 1366);
//        makeString(768, 1366);
//        makeString(1008, 1920);
//        makeString(1080, 1920);
//        makeString(480, 1280);

        makeString(480, 800);
//        makeString(480, 854);
//        makeString(540, 960);
//        makeString(600, 1024);
//        makeString(720, 1184);
//        makeString(720, 1196);
//        makeString(720, 1280);
//        makeString(768, 1024);
//        makeString(800, 1280);
//        makeString(1080, 1812);
//        makeString(1080, 1920);
//        makeString(1440, 2560);


    }

    public static void makeString(int w, int h) {

        StringBuffer sb = new StringBuffer();
        sb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n");
        sb.append("<resources>");
        float cellw = w / dw;
        for (int i = 1; i < 720; i++) {
            sb.append(WTemplate.replace("{0}", i + "").replace("{1}",
                    change(cellw * i) + ""));
        }
        sb.append(WTemplate.replace("{0}", "720").replace("{1}", w + ""));
        sb.append("</resources>");

        StringBuffer sb2 = new StringBuffer();
        sb2.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n");
        sb2.append("<resources>");
        float cellh = h / dh;
        for (int i = 1; i < 1280; i++) {
            sb2.append(HTemplate.replace("{0}", i + "").replace("{1}",
                    change(cellh * i) + ""));
        }
        sb2.append(HTemplate.replace("{0}", "1280").replace("{1}", h + ""));
        sb2.append("</resources>");

        String path = rootPath.replace("{0}", h + "").replace("{1}", w + "");
        File rootFile = new File(path);
        if (!rootFile.exists()) {
            boolean mkdirs = rootFile.mkdirs();
        }
        File layxFile = new File(path + "lay_x.xml");
        File layyFile = new File(path + "lay_y.xml");
        try {
            PrintWriter pw = new PrintWriter(new FileOutputStream(layxFile));
            pw.print(sb.toString());
            pw.close();
            pw = new PrintWriter(new FileOutputStream(layyFile));
            pw.print(sb2.toString());
            pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static float change(float a) {
        int temp = (int) (a * 100);
        return temp / 100f;
    }
    //


}



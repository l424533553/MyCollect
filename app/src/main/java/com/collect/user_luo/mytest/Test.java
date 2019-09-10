package com.collect.user_luo.mytest;


import com.xuanyuan.library.utils.text.StringChangeUtils;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;


/**
 * 作者：罗发新
 * 时间：2018/12/21 0021    16:23
 * 邮件：424533553@qq.com
 * 说明：
 */


public class Test {

    public static void main(String[] args) {

        try {
            String test = encryptThreeDESECB("service=sign&cmd=login&authenCode=fpms_vender_axb&password=h79OpV3MtCfiZHcu", "F7AD4703F4520AFDB0216339");
            System.out.print(test);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 3DESECB加密,key必须是长度大于等于 3*8 = 24 位哈
    public static String encryptThreeDESECB(final String src, final String key) throws Exception {
        final DESedeKeySpec dks = new DESedeKeySpec(key.getBytes("UTF-8"));
        final SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DESede");
        final SecretKey securekey = keyFactory.generateSecret(dks);

        final Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, securekey);
        final byte[] b = cipher.doFinal(src.getBytes());
        return StringChangeUtils.bytes2HexString(b);
    }


}

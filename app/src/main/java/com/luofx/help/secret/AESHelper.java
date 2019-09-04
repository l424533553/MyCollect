package com.luofx.help.secret;

import java.io.UnsupportedEncodingException;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.SecretKeySpec;

import io.reactivex.observers.BaseTestConsumer;

import static com.luofx.utils.system.ByteUtil.hex2byte;
import static com.luofx.utils.text.StringChangeUtils.byte2hex;

//** AES对称加密解密类 **/
public class AESHelper {

    // /** 算法/模式/填充 **/
    private static final String CipherMode = "AES/ECB/PKCS5Padding";

    ///** 创建密钥 **/
    private static SecretKeySpec createKey(String password) {
        byte[] data = null;
        if (password == null) {
            password = "";
        }
        StringBuffer sb = new StringBuffer(32);
        sb.append(password);
        while (sb.length() < 32) {
            sb.append("0");
        }
        if (sb.length() > 32) {
            sb.setLength(32);
        }

        try {
            data = sb.toString().getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return new SecretKeySpec(data, "AES");
    }

    // /** 加密字节数据 **/
    public static byte[] encrypt(byte[] content, String password) {
        try {
            SecretKeySpec key = createKey(password);
            System.out.println(key);
            Cipher cipher = Cipher.getInstance(CipherMode);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] result = cipher.doFinal(content);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    ///** 加密(结果为16进制字符串) **/
    public static String encrypt(String content, String password) {
        byte[] data = null;
        try {
            data = content.getBytes("UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        data = encrypt(data, password);
        String result = byte2hex(data);
        return result;
    }

    // /** 解密字节数组 **/
    public static byte[] decrypt(byte[] content, String password) {
        try {
            SecretKeySpec key = createKey(password);
            Cipher cipher = Cipher.getInstance(CipherMode);
            cipher.init(Cipher.DECRYPT_MODE, key);
            return cipher.doFinal(content);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    ///** 解密16进制的字符串为字符串 **/
    public static String decrypt(String content, String password) {
        byte[] data = null;
        try {
            data = hex2byte(content);
        } catch (Exception e) {
            e.printStackTrace();
        }
        data = decrypt(data, password);
        if (data == null)
            return null;
        String result = null;
        try {
            result = new String(data, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }

    // 3DESECB加密,key必须是长度大于等于 3*8 = 24 位哈
    /**
     * 采用3DES 加密  Android 中默认的加密方法就是 ECB模式，该模式事实上是不安全的
     * ECB模式中  只有PKCS5Padding 模式在Android中被支持，PKCS7Padding会报错
     * @param src   加密数据源
     * @param key   加密秘钥
     * @return      返回16进制的加密数据
     * @throws Exception 数据格式异常 和 转换异常
     */
    public static  String encryptDESedeECB(final String src, final String key) throws Exception {
        final DESedeKeySpec dks = new DESedeKeySpec(key.getBytes("UTF-8"));
        final SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DESede");
        final SecretKey securekey = keyFactory.generateSecret(dks);

        final Cipher cipher =  Cipher.getInstance("DESede/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, securekey);
        final byte[] b = cipher.doFinal(src.getBytes());
        return byte2hex(b);
    }

//    http://fpms.chinaap.com/admin/trade?executor=http&appCode=FPMSWS&data=1338ec0cf5d288b7f5694100704a1978e7e2506c8d7ee17e94fd4bfa7c6ad12ec00ea1f620e91fbd40a3acb407a2e0d048e0a976cec4ab9a5656a17bb56a2755007bc3f4e143ad90a4df50eafdbf1a13
//    http://fpms.chinaap.com/admin/trade?executor=http&appCode=FPMSWS&data=1338ec0cf5d288b7f5694100704a1978e7e2506c8d7ee17e94fd4bfa7c6ad12ec00ea1f620e91fbd40a3acb407a2e0d048e0a976cec4ab9a5656a17bb56a2755007bc3f4e143ad90a4df50eafdbf1a13

    /**
     * test11
     */
    private  void  test11(){
        //使用测试

    }
}

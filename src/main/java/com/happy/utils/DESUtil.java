package com.happy.utils;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.security.SecureRandom;

/**
 * @author jun.wang
 * @title: DESUtil
 * @projectName ownerpro
 * @description: TODO
 * @date 2019/8/6 9:51
 */
public class DESUtil {

    public static final String password = "xdgfsx!q";

    private static final String char_set= "utf-8";


    /**
     * des加密
     * @param source
     * @param password
     * @return
     */
    public static String encode(String source, String password) {
        try {
            SecureRandom random = new SecureRandom();
            DESKeySpec desKey = new DESKeySpec(password.getBytes(char_set));
            //创建一个密匙工厂，然后用它把DESKeySpec转换成
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey secretkey = keyFactory.generateSecret(desKey);
            //Cipher对象实际完成加密操作
            Cipher cipher = Cipher.getInstance("DES");
            //用密匙初始化Cipher对象
            cipher.init(Cipher.ENCRYPT_MODE, secretkey, random);
            //现在，获取数据并加密
            //正式执行加密操作
            return Base64.encodeBase64String(cipher.doFinal(source.getBytes(char_set)));
        }
        catch (Exception e) {
            throw new RuntimeException("des encrypt fail", e);
        }
    }

    /**
     * des解密
     * @param encodeStr
     * @param password
     * @return
     */
    public static String decode(String encodeStr, String password) {
        try {
            // DES算法要求有一个可信任的随机数源
            SecureRandom random = new SecureRandom();
            // 创建一个DESKeySpec对象
            DESKeySpec desKey = new DESKeySpec(password.getBytes());
            // 创建一个密匙工厂
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            // 将DESKeySpec对象转换成SecretKey对象
            SecretKey secretkey = keyFactory.generateSecret(desKey);
            // Cipher对象实际完成解密操作
            Cipher cipher = Cipher.getInstance("DES");
            // 用密匙初始化Cipher对象
            cipher.init(Cipher.DECRYPT_MODE, secretkey, random);
            // 真正开始解密操作
            return new String(cipher.doFinal(Base64.decodeBase64(encodeStr)), char_set);
        }
        catch (Exception e) {
            throw new RuntimeException("des decrypt fail", e);
        }
    }

    public static void main(String args[]) {
        String text = "wangjun";
        String encodeStr = encode(text, password);
        System.out.println(encodeStr);
        String decodeStr = decode(encodeStr, password);
        System.out.println(decodeStr);
    }
}

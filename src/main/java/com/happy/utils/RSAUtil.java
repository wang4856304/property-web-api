package com.happy.utils;


import com.sun.org.apache.xml.internal.security.utils.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * @author jun.wang
 * @title: RSAUtil
 * @projectName ownerpro
 * @description: TODO
 * @date 2019/8/5 15:03
 */
public class RSAUtil {

    /**
     * 字节数据转字符串专用集合
     */
    private static final char[] HEX_CHAR = { '0', '1', '2', '3', '4', '5', '6',
            '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
    private static final String CHAR_SET = "UTF-8";

    private static final String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCLpOoWPaXx2IsEr87YF8ZPIa/w8W+uvAP7gihB\n" +
            "3V4IV48nAq0tzdqjLQWDcjFlQObx8FC8bAmjzfG79/T8J2eJsmB9uRoNKtM/ILygelaVSg8IEWq3\n" +
            "ZcnJTv/zbVfiHI3zwxTUxXeA/EVtoFRd8dLgfuGie4yaJAVno6D7H9IT7wIDAQAB";
    private static final String privateKey = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAIuk6hY9pfHYiwSvztgXxk8hr/Dx\n" +
            "b668A/uCKEHdXghXjycCrS3N2qMtBYNyMWVA5vHwULxsCaPN8bv39PwnZ4myYH25Gg0q0z8gvKB6\n" +
            "VpVKDwgRardlyclO//NtV+IcjfPDFNTFd4D8RW2gVF3x0uB+4aJ7jJokBWejoPsf0hPvAgMBAAEC\n" +
            "gYAo+pw3KqAn+IPTPERW5aMsVZZr/m3nuQTyKZ/mtC+EAy3fJMntRxuCBxQ3HqugKwuatAr9QDcT\n" +
            "+ruVjY428NlQ/6lT+yYCvAcAPk2qH4godd1cBu96DzynwhyfruyjJPuJPLvDvRoUnJ0SEg7eVcJl\n" +
            "7y45YTZsBfQYse7HFAYOAQJBANBYBRxT4vbVMwbFnGtvcnHEYzlnh9+lEy5JtSCJ8Noy8Oo7i73X\n" +
            "MzxB/xFSvioziRC9Pm8eoXbR84lek7feb+kCQQCrlg2jPlAWC7o/Q5NuEuc8vWoBLVeUv+Eqc1gh\n" +
            "MWAzCePa6XcgyWK+7RYvJ1DqiobnRYBh1ieUfnCdWsbIeBYXAkAhWEURIJftu3ecONptE3w0myLq\n" +
            "WBiWAbyNJvzXRVyH/6kxQ/B8pMmtdj6VjjGjXXYteKxaaoObGcUpkjqALhipAkA29dlocI02gosl\n" +
            "uH+Awo1O510NA3kKLGvzLP8NosMtlf1mIWIHHnqCrW5trCo+9iA/9AMrU21aIyXvQSRrEgwBAkAR\n" +
            "NvzOGw8hXVwbtlShknLunDA+DHEPawIlBcPqe3pAiNZnQzExi7CPL7O2LCWjbEppApdBmigbG0Hz\n" +
            "ZHPaIC7l";

    /**
     * 随机生成密钥对
     */
    public static void genKeyPair() {
        // KeyPairGenerator类用于生成公钥和私钥对，基于RSA算法生成对象
        KeyPairGenerator keyPairGen = null;
        try {
            keyPairGen = KeyPairGenerator.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // 初始化密钥对生成器，密钥大小为96-1024位
        keyPairGen.initialize(1024,new SecureRandom());
        // 生成一个密钥对，保存在keyPair中
        KeyPair keyPair = keyPairGen.generateKeyPair();
        // 得到私钥
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        // 得到公钥
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        try {
            // 得到公钥字符串
            String publicKeyString = Base64.encode(publicKey.getEncoded());
            // 得到私钥字符串
            String privateKeyString = Base64.encode(privateKey.getEncoded());

            System.out.println("publicKeyString=" + publicKeyString);
            System.out.println("privateKeyString=" + privateKeyString);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 字符串生成公钥
     * @param publicKeyStr
     * @return
     * @throws Exception
     */
    public static RSAPublicKey loadPublicKeyByStr(String publicKeyStr)
            throws Exception {
        try {
            byte[] buffer = Base64.decode(publicKeyStr);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(buffer);
            return (RSAPublicKey) keyFactory.generatePublic(keySpec);
        } catch (NoSuchAlgorithmException e) {
            throw new Exception("无此算法");
        } catch (InvalidKeySpecException e) {
            throw new Exception("公钥非法");
        } catch (NullPointerException e) {
            throw new Exception("公钥数据为空");
        }
    }

    /**
     * 字符串生成私钥
     * @param privateKeyStr
     * @return
     * @throws Exception
     */
    public static RSAPrivateKey loadPrivateKeyByStr(String privateKeyStr)
            throws Exception {
        try {
            byte[] buffer = Base64.decode(privateKeyStr);
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(buffer);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            return (RSAPrivateKey) keyFactory.generatePrivate(keySpec);
        } catch (NoSuchAlgorithmException e) {
            throw new Exception("无此算法");
        } catch (InvalidKeySpecException e) {
            throw new Exception("私钥非法");
        } catch (NullPointerException e) {
            throw new Exception("私钥数据为空");
        }
    }

    /**
     * 公钥加密
     * @param publicKey
     * @param text
     * @return
     * @throws Exception
     */
    public static String encrypt(RSAPublicKey publicKey, String text)
            throws Exception {
        if (publicKey == null) {
            throw new Exception("加密公钥为空, 请设置");
        }
        byte[] plainTextData = text.getBytes(CHAR_SET);
        Cipher cipher = null;
        try {
            // 使用默认RSA
            cipher = Cipher.getInstance("RSA");
            // cipher= Cipher.getInstance("RSA", new BouncyCastleProvider());
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            byte[] output = cipher.doFinal(plainTextData);
            return Base64.encode(output);
        } catch (NoSuchAlgorithmException e) {
            throw new Exception("无此加密算法");
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
            return null;
        } catch (InvalidKeyException e) {
            throw new Exception("加密公钥非法,请检查");
        } catch (IllegalBlockSizeException e) {
            throw new Exception("明文长度非法");
        } catch (BadPaddingException e) {
            throw new Exception("明文数据已损坏");
        }
    }

    /**
     * 私钥解密
     * @param privateKey
     * @param encodeStr
     * @return
     * @throws Exception
     */
    public static String decrypt(RSAPrivateKey privateKey, String encodeStr)
            throws Exception {
        if (privateKey == null) {
            throw new Exception("解密私钥为空, 请设置");
        }
        byte[] cipherData = Base64.decode(encodeStr.getBytes(CHAR_SET));
        Cipher cipher = null;
        try {
            // 使用默认RSA
            cipher = Cipher.getInstance("RSA");
            // cipher= Cipher.getInstance("RSA", new BouncyCastleProvider());
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            byte[] output = cipher.doFinal(cipherData);
            return new String(output, CHAR_SET);
        } catch (NoSuchAlgorithmException e) {
            throw new Exception("无此解密算法");
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
            return null;
        } catch (InvalidKeyException e) {
            throw new Exception("解密私钥非法,请检查");
        } catch (IllegalBlockSizeException e) {
            throw new Exception("密文长度非法");
        } catch (BadPaddingException e) {
            throw new Exception("密文数据已损坏");
        }
    }

    public static void main(String args[]) throws Exception {
        //genKeyPair();
        String encodeStr = encrypt(loadPublicKeyByStr(publicKey), "wangjun");
        System.out.println(encodeStr);
        String decodeStr = decrypt(loadPrivateKeyByStr(privateKey), encodeStr);
        System.out.println(decodeStr);
    }
}

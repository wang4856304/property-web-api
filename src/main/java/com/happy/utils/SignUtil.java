package com.happy.utils;

import com.happy.exception.BusinessException;

import java.security.MessageDigest;

public class SignUtil {

    public static final int MD5 = 1;
    public static final int SHA = 2;

    public static String getMD5Sign(String value) {
        MessageDigest md5 = null;
        StringBuffer hexValue = new StringBuffer();
        try {
            md5 = MessageDigest.getInstance("MD5");
            byte[] byteArray = value.getBytes("UTF-8");
            byte[] md5Bytes = md5.digest(byteArray);
            for (int i = 0; i < md5Bytes.length; i++) {
                int val = ((int) md5Bytes[i]) & 0xff;
                if (val < 16) {
                    hexValue.append("0");
                }
                hexValue.append(Integer.toHexString(val));
            }
        } catch (Exception e) {
            throw new BusinessException("md5加密错误", e);
        }
        return hexValue.toString().toUpperCase();
    }

    public static String getSHASign(String value) {
        MessageDigest sha = null;
        StringBuffer hexValue = new StringBuffer();
        try {
            sha = MessageDigest.getInstance("SHA");
            byte[] byteArray = value.getBytes("UTF-8");
            byte[] md5Bytes = sha.digest(byteArray);

            for (int i = 0; i < md5Bytes.length; i++) {
                int val = ((int) md5Bytes[i]) & 0xff;
                if (val < 16) {
                    hexValue.append("0");
                }
                hexValue.append(Integer.toHexString(val));
            }
        } catch (Exception e) {
            throw new BusinessException("sha加密错误", e);
        }
        return hexValue.toString().toUpperCase();
    }

    public static String signature(String value, int mode) {
        String signValue = null;
        if (mode == MD5) {
            signValue = getMD5Sign(value);
        }
        else if (mode == SHA) {
            signValue = getSHASign(value);
        }
        return signValue;
    }
}
